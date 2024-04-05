import java.awt.Color;

public class Quadrilateral extends Shape
{   
    //version of constructor where 4 points and a color are specified.
    public Quadrilateral( Point A, Point B, Point C, Point D, Color color )
    {
        //buildPointList arrages the points so that sides P1P2, P2P3, P3P4, and P4P1 do not intersect
        //super() calls the super classes constructor.  In this case, it takes a Point[] 
        //since that is what buildPointArray returns
        super(buildPointArray(A, B, C, D), color);//you can call a static method within super - but not a non-static one.
        
        checkValidity();   
    }
    
    //version of constructor where 4 points are specified.  Color is set to BLUE
    public Quadrilateral( Point A, Point B, Point C, Point D)
    {
        //use the prior Quadrliateral constructor with color of BLUE
        this(A, B, C, D, Color.BLUE);
    }
    //orders points such that quadrilateral will not be constructed with intersecting lines
    //either AB should connect, or they should not. 
    private static Point[] buildPointArray(Point A, Point B, Point C, Point D)
    {
        LineSeg l1 = new LineSeg(A,B);
        LineSeg l2 = new LineSeg(B,C);
        LineSeg l3 = new LineSeg(C,D);
        LineSeg l4 = new LineSeg(D,A);
        
        if (l1.intersects(l3)) //sides AB and CD intersect. switch B and C 
        {
            return new Point[]{A,C,B,D};
        }
        else if (l2.intersects(l4)) //sides AD and BC intersect.  switch C and D
        {
            return new Point[]{A,B,D,C};
        }
        else //none of the sides intersect with current arrangement
            return new Point[]{A,B,C,D};
    }
    
    //A quadrilateral should not have 3 co-linear points
    @Override
    public void checkValidity()
    {
        
        super.checkValidity();  // call superclasses version of checkValidty to make sure this is a valid Quadrilateral
        
        //if any 3 of the 4 points are co-linear (on same line) then they cannot form a quadrilateral
        // in this case, throw an exception
        //  this would be true if any 2 line segments that share a point
        //  have the same slope
        LineSeg l1 = new LineSeg(point(0),point(1));
        LineSeg l2 = new LineSeg(point(0),point(2));
        LineSeg l3 = new LineSeg(point(0),point(3));
        LineSeg l4 = new LineSeg(point(1),point(2));
        LineSeg l5 = new LineSeg(point(1),point(3));
        LineSeg l6 = new LineSeg(point(2),point(3));
        
        //case where shape is not valid
        if (l1.parallelTo(l2) || l1.parallelTo(l3) || l2.parallelTo(l3)
         || l1.parallelTo(l4) || l1.parallelTo(l5) || l4.parallelTo(l5)
         || l2.parallelTo(l4) || l2.parallelTo(l6) || l4.parallelTo(l6))
            throw new IllegalArgumentException("Bad parameters: 3 points or more are co-linear"); 
        
    }
    
    @Override
    public double perimeter()
    {
        return 0.0;
    }
    @Override
    public double area()
    {
        //for a convex quadrilateral, the sum of 
        //Area(ABC) + Area(ACD) should be the same as the sum of
        //Area(ABD) + Area(BCD)
        //either one of those sums could be returned as the Area.
        
        //for a concave quadrilateral, one of those two sums will be the correct area, but the other will not.
        //the smaller of the two sums will be the correct area.
        Triangle ABC = new Triangle(point('A'), point('B'), point('C'));
        Triangle ACD = new Triangle(point('A'), point('C'), point('D'));
        double areaSum1 = ABC.area() + ACD.area();
        Triangle ABD = new Triangle(point('A'), point('B'), point('D'));
        Triangle BCD = new Triangle(point('B'), point('C'), point('D'));
        double areaSum2 = ABD.area() + BCD.area();
        return Math.min(areaSum1, areaSum2);
    }   
}
