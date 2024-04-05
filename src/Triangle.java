import java.awt.Color;

public class Triangle extends Shape
{
    //version of constructor where 3 points and a color are specified.
    //throws IllegalArgumentException if Points A, B, and C do not make a triangle.
    public Triangle( Point A, Point B, Point C, Color color )
    {
        super(new Point[] {A,B,C}, color);
        //check Validity of Triangle.  Should not have any collinear points
        checkValidity();
    }
    
    public Triangle( Point A, Point B, Point C)
    {
        this(A, B, C, Color.RED);
    }

    @Override
    public void checkValidity()
    {
        super.checkValidity();  // call superclasses version of checkValidty to make sure points are distinct
        //if the points are co-linear, throw an Exception
        LineSeg AB = new LineSeg(point('A'),point('B'));
        LineSeg BC = new LineSeg(point('B'),point('C'));
        LineSeg CA = new LineSeg(point('C'),point('A'));
        if (AB.parallelTo(BC) || BC.parallelTo(CA) || AB.parallelTo(CA))
            throw new IllegalArgumentException("Error: Points are collinear"); 
    }

    @Override
    public String toString()
    {
        return String.format("[%s,%s,%s]-- Area: %.2f Perimeter %.2f",point('A'),point('B'),point('C'),area(),perimeter()); 
    }

    //returns the lineSegment from point(0) to point(1)
    public LineSeg base()
    {
        return new LineSeg(point('A'), point('B'));
    }
    //returns a line segment from point('C') to a point on
    //base() and which is perpendicular to the 
    //line associated with the linesegment returned by base()
    public LineSeg height()
    {
        return new LineSeg(point('C'), base().perpendicularTo(point('C')));
    }

    @Override
    public double perimeter()
    {
        LineSeg AB = new LineSeg(point('A'),point('B'));
        LineSeg BC = new LineSeg(point('B'),point('C'));
        LineSeg CA = new LineSeg(point('C'),point('A'));

        return AB.length() + BC.length() + CA.length();
    }

    @Override
    public double area()
    {
        LineSeg base = base();
        LineSeg height = height();
        return .5 * base.length() * height.length();
    }    
}