import java.awt.Graphics;
import java.util.Arrays;
import java.awt.Polygon;
import java.awt.Color;

/**The Shape class should be extended by more specific subclasses
    The Shape class might be useful to extend for any shape which
    could be benefit from storing a set of distinct points and a color.
    default functionality is provided for specifying and storing points,
    checking validity of the shape (same point does not appear more than once)
    and specifying and setting the color.  
    accessor and mutator methods are provided.
    area() and perimeter() methods
    are declared but should be overriden by any direct subchild of Shape
**/

public class Shape //extends Object (this is implicit - does not have to be explicitly written)
{
    private final Point[] points; //points should be immutable -- no mutator method for points
    private Color color;
    
    //version of constructor where an array of points and a color are specified.
    public Shape(Point[] points, Color color)
    {
        //make a copy so that if the original array is modifed after construction outside of this code
        //, it doesn't affect this shape
         this.points = Arrays.copyOf(points, points.length);
         this.color = color;
         checkValidity();
    }
    //version of constructor where an array of points are specified.  Color is set to BLACK
    public Shape(Point[] points)
    {
        //use the prior Shape constructor with color of black 
        this(points, Color.BLACK);  
    }
    
    public void checkValidity()
    {
        //compare each point to every other point and make sure they are not equal.
        for (int i=0; i<points.length-1; i++)
            for (int j=i+1; j<points.length; j++)
                if (points[i].equals(points[j]))
                {
                    throw new IllegalArgumentException("Error: Points sent to Constructor not all distinct.");
                }
    }
    
    //mutator for color instance variable
    public void setColor(Color color)
    {
        this.color = color;
    }
    
    //accessor for one of the Point objects in the Shape
    public Point point(int i)
    {
        if (i < points.length)
            return points[i];
        else throw new IndexOutOfBoundsException("No Point " + i + " in this Shape");
    }
    
    //accessor for one of the Point object in Shape -- takes a character:
    //'A' is point 0, 'B' is point 1, 'C' is point 2, etc.
    public Point point(char c)
    {
        if (c >= 65 && c <= 90)
            return point(c-65);
        else if (c >= 97 && c <= 122)
            return point(c-97);
        else throw new IndexOutOfBoundsException("No Point " + c + " in this Shape");
    }
    
    //accessor for current color attribute
    public Color getColor()
    {
        return color;
    }
    
    //draw method sufficient for drawing any polygon.  
    //For other types of shape, draw should be overridden
    public void draw(Graphics g)
    {
        int nPoints = points.length;
        int[] xPoints = new int[nPoints];
        int[] yPoints = new int[nPoints];
        for (int i=0; i<nPoints; i++)
        {
            xPoints[i] = (int)(points[i].x());
            yPoints[i] = (int)(points[i].y());
        }
        
        Polygon shape = new Polygon(xPoints,yPoints,nPoints);

        g.setColor(this.color);
        g.fillPolygon(shape);
    }
    //this should be overridden by sub-classes
    public double area()
    {
        throw new UnsupportedOperationException("area not implemented for generic shape.  Should be overridden in subclasses");
    }
    //this should be overridden by sub-classes
    public double perimeter()
    {
        throw new UnsupportedOperationException("perimeter not implemented for generic shape.  Should be overridden in subclasses");
    }
    
}