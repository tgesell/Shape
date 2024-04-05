public class LineSeg
{
    //instance data (attributes)
    private final Point p1, p2;
    public static final int NOSLOPE = Integer.MAX_VALUE;

    //Constructor:  takes 2 Point objects
    public LineSeg( Point A, Point B)
    {
        this.p1 = A;
        this.p2 = B;
    }

    public Point A() {return p1; }

    public Point B() {return p2; }

    //Override Object's public String toString() method
    @Override
    public String toString() 
    {
        return "[" + p1 + "," + p2 + "]";
    }    

    //Override Object's public boolean equals(Object) method
    @Override
    public boolean equals(Object other)
    {
        //if other is null or not a LineSegment object, then return false.
        if (!(other instanceof LineSeg))
        {
            return false;
        }

        LineSeg ls = (LineSeg) other;

        return this.equals(ls); //since l is a LineSeg object, will call the overloaded version
    }

    //overloaded equals method - different parameter type
    public boolean equals(LineSeg l)
    {
        return l != null && 
                (
                    this.A().equals(l.A()) && this.B().equals(l.B())
                ||
                    this.A().equals(l.B()) && this.B().equals(l.A())
                );
    }
    
    public boolean parallelTo(LineSeg other)
    {
        //return this.slope() == other.slope()
        //return Math.abs(this.slope() - other.slope()) < Point.EQUALITYDELTA;
        return MathFunctions.doubleEquals(this.slope(), other.slope(), Point.EQUALITYDELTA); 
    }

    // returns the length of the line segment (distance formula)
    public double length()
    {
        double y2MinusY1 = p2.y() - p1.y();
        double x2MinusX1 = p2.x() - p1.x();
        return Math.sqrt(Math.pow(y2MinusY1,2) + Math.pow(x2MinusX1,2));
    }

    // returns the slope of the line segment
    public double slope()
    {
        double y2MinusY1 = p2.y() - p1.y();
        double x2MinusX1 = p2.x() - p1.x();
        if (Math.abs(y2MinusY1) <= Point.EQUALITYDELTA)
            return 0;
        else if (Math.abs(x2MinusX1) <= Point.EQUALITYDELTA)
            return LineSeg.NOSLOPE;
        else return y2MinusY1/x2MinusX1;
    }

    //returns the y intercept of the line that corresponds to this line segment
    //NOTE:  The Point(x,y) is on the corresponding line, but not necessarily on the Line Segment
    public double yInt()
    {
        //y = mx + b so b = -mx + y
        //b = -mx + y
        //can use (x,y) of either point
        double m = slope();
        double x = p1.x();
        double y = p1.y();

        double b = -1*m*x + y;

        return b;
    }

    //f(x)
    //given an x value, returns the y value that gives a point (x,y) on the line corresponding to this line segment.
    //NOTE:  The Point(x,y) is on the corresponding line, but not necessarily on the Line Segment
    public double f(double x)
    {
        //f(x) = mx + b
        return slope() * x + yInt();
    }

    //returns the point of intersection between the line corresponding to this line segment, 
    // and the line corresponding to the other parameter Line Segment.
    //point does not need to appear on the line segments themselves
    public Point intersection(LineSeg other)
    {
        //Point of Intersection of y = ax + c AND y = bx + d is {x = (d-c)/(a-b), f(x))
        double a = this.slope();
        double b = other.slope();
        double c = this.yInt();
        double d = other.yInt();

        // if (a == b)
        if (MathFunctions.doubleEquals(a,b,Point.EQUALITYDELTA))
            return null; // parallel lines
        
        double x = (d-c)/(a-b);
        return new Point(x, f(x));
    }
    
    public boolean isPointOnLineSeg(Point p)
    {
        double minx = Math.min(p1.x(), p2.x());
        double maxx = Math.max(p1.x(), p2.x());
        double miny = Math.min(p1.y(), p2.y());
        double maxy = Math.max(p1.y(), p2.y());
        if (MathFunctions.doubleEquals(f(p.x()), p.y(), Point.EQUALITYDELTA)// if (f(x) == y)
            && p.x() >= minx && p.x() <= maxx && p.y() >= miny && p.y() <= maxy)
            return true;
        return false;
        
    }
    
    public boolean intersects(LineSeg other)
    {
        Point intersectionPoint = this.intersection(other);
        //case of parallel lines, where intersectionPoint is null
        if (intersectionPoint == null)
            return false;
        //case of non-parallel lines
        return this.isPointOnLineSeg(intersectionPoint) &&
               other.isPointOnLineSeg(intersectionPoint);       
    }

    // midpoint
    public Point midpoint()
    {
        return new Point((p2.x() - p1.x())/2,(p2.y() - p1.y())/2);
    }

    // point on line that forms a line segment with p3 that is perpendicular to this line segment 
    public Point perpendicularTo( Point p3 )
    {
        //if p3 is on this line (in other words f(p3_x) == p3_y)) then just return p3
        //if f(p3.x) = p3.y
        if (MathFunctions.doubleEquals(f(p3.x()),p3.y(),Point.EQUALITYDELTA))
            return p3;
        else if (slope() == 0)//if this is horizontal line segment use p3s x value and the constant y value on line seg
        {
            return new Point(p3.x(), p1.y());
        }
        else if (slope() == LineSeg.NOSLOPE)
        {
            return new Point(p1.x(), p3.y());
        }
        else
        {
            //find perpendicular slope
            double m = -1 / slope();

            //find y-int of line going through p3 with above perpendicular slope
            double x = p3.x();
            double y = p3.y();
            double b = -1*m*x + y;
            //find another point on above line (besides p3) using y = mx+b
            double x2 = p3.x() + 10;
            double y2 = m*x2 + b;

            Point p4 = new Point(x2, y2);

            //construct Line Segment between p3 and the other point just computed
            LineSeg perpendicularLineSegment = new LineSeg(p3, p4);
            //find point of intersection between this Line Segment, and the perpendicular Line Segment
            Point intersectionPoint = this.intersection(perpendicularLineSegment);
            return intersectionPoint;
        }
    }
}