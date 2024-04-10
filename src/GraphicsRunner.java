import javax.swing.JFrame;

public class GraphicsRunner extends JFrame
{
    private static final int WIDTH = 1000;
    private static final int HEIGHT = 750;

    public GraphicsRunner()
    {
        super("Graphics Runner");

        setSize(WIDTH,HEIGHT);

        getContentPane().add(new MyDrawingExample());
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main( String[] args )
    {
        GraphicsRunner run = new GraphicsRunner();
    }
}