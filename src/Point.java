public class Point
{
    //instance data (attributes)
    private final double x, y;
    public static final double EQUALITYDELTA = .01;

    //constructors
    public Point( double a, double b )
    {
        x = a;
        y = b;
    }
    
    //accessors
    public double x() { return x; }
    public double y() { return y; }

    //Override Object's public String toString() method
    @Override
    public String toString()
    {
        return String.format("(%.0f,%.0f)",x(),y());
    }

    //Override Object's public boolean equals(Object) method
    @Override
    public boolean equals(Object other)
    {
        if (other == null)
        {
            return false;
        }

        if (this.getClass() != other.getClass())
        {
            return false;
        }

        Point p = (Point) other;
        
        return this.equals(p); 
    }
    
    //overloaded equals() method
    //Note: it is always a bad idea to compare double values with == because of lack of precision.
    //Here, MathFunction.doubleEquals() is used to see if two values are within 
    //some distance (EQUALITYDELTA) of each other
    public boolean equals(Point other)
    {
        return other != null && 
           MathFunctions.doubleEquals(this.x, other.x, EQUALITYDELTA) && //this.x == other.x
           MathFunctions.doubleEquals(this.y, other.y, EQUALITYDELTA); //this.y == other.y
    }

    //returns true if this point is in Q1, false otherwise
    public boolean inQuadrant(int quadrant)
    {
        if (quadrant == 1)
            return x > 0 && y > 0;
        else if (quadrant == 2)
            return x < 0 && y > 0;
        else if (quadrant == 3)
            return x < 0 && y < 0;
        else if (quadrant == 4)
            return x > 0 && y < 0;
        else
            return false;  
    }
}