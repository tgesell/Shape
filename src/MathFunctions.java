/** 
 * Place to put my own commonly used mathematical functions that aren't already in the java Math class.
 * and that don't specifically belong in another class.
 * These will all be static.  No instance data here.  No non-static methods.
 */
public class MathFunctions
{
    public static boolean doubleEquals(double d1, double d2, double epsilon)
    {
      return Math.abs(d1 - d2) < epsilon;   
    }
    
}
