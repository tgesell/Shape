import javax.swing.JPanel;
import javax.swing.JTextArea;
import java.awt.Graphics;
import java.awt.Color;
import java.util.ArrayList;

public class MyDrawingExample extends JPanel {
    
    ArrayList<Shape> listOfShapes;
    JTextArea shapeInfo;

    public MyDrawingExample()
    {
        // Call the super class constructor.  Happens automatically if you are calling a version of 
        // the super class constructor that takes no parameters, but good practice anyway
        super();
        setBackground(Color.WHITE);
        listOfShapes = new ArrayList<Shape>();
        shapeInfo = new JTextArea();
        this.add(shapeInfo); //be default will add to top center
        createShapes();//you need to write this number below.
    }

    public void paintComponent( Graphics g)
    {
        super.paintComponent(g);
        //clear text area before re-writing its info
        shapeInfo.setText("");
        for (Shape shape : listOfShapes)
        {
            shape.draw(g);
            shapeInfo.setText(shapeInfo.getText() + shape.getClass() + shape.toString() + "\n");
        }
    }
    
    //We can hard code the construction of Shapes in this method, OR we could read from a file, accept user input in a variety of different ways, etc.
    public void createShapes()
    {
        //Some Point object refernce variables to work with:
        Point p1, p2, p3, p4;
        //SHAPE 1
        p1 = new Point(200,200);
        p2 = new Point(200,500);
        p3 = new Point(600,500);
        Triangle t1 = new Triangle(p1,p2,p3);
        //Shape t1 = new Triangle(p1,p2,p3); //this also works
        listOfShapes.add(t1);
    
        //SHAPE 2
        Circle c1 = new Circle(p2,75);
        listOfShapes.add(c1);
        
        //SHAPE 3
        p1 = new Point(300,100);
        //p2 = new Point(250,250);
        //p2 = new Point(100,200);
        p2 = new Point(500, 100);
        p3 = new Point(400,550);
        //p3 = new Point(220,400);
        p4 = new Point(400,200);
        //p4 = new Point(400,250);
        Shape s;
        s = new Quadrilateral(p1, p2, p3, p4);
//        s = new Quadrilateral(p1, p4, p3, p2);
//        s = new Quadrilateral(p1, p2, p4, p3);
//        s = new Quadrilateral(p1, p4, p3, p2);
//        s = new Quadrilateral(p1, p3, p2, p4);
//        s = new Quadrilateral(p1, p4, p3, p2);
//        s = new Quadrilateral(p1, p3, p4, p2);
//        s = new Quadrilateral(p1, p4, p3, p2);
//        s = new Quadrilateral(p1, p4, p2, p3);
//        s = new Quadrilateral(p1, p4, p3, p2);
        listOfShapes.add(s);
    }
}