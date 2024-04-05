import java.awt.Graphics;
import java.awt.Color;
public class Circle extends Shape
{
    private final double radius;
    
    //version of constructor where centerPoint, radius and a color are specified.
    public Circle( Point middle, double rad, Color color )
    {
        super(new Point[]{middle},color);
        radius = rad;
        if (radius <= 0)
            throw new IllegalArgumentException("Error: Center point of Circle must be positive"); 
    }
    //version of constructor where centerPoint and radius are specified, and color is set to YELLOW
    public Circle( Point middle, double rad )
    {
        this(middle, rad, Color.YELLOW);
    }
    // accessors and toString
    public Point center(){
        return point(0);
    }
    
    public double radius()
    {
        return radius;
    }
    
    @Override
    public double perimeter()
    {
        return 0;
    }
    
    //returns same value as perimeter(), but provided for semantic reasons.
    public double circumference()
    {
        return perimeter();
    }
    
    @Override
    public double area()
    {
        return 0;
    }
     
    @Override
    public void draw(Graphics g)
    {
        g.setColor(getColor());
        g.fillOval((int)(center().x()-radius), (int)(center().y()-radius), (int)Math.round(radius*2), (int)Math.round(radius*2));
    }
}
