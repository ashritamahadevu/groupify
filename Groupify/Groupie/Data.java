import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File; 

public class Data
{
    // instance variables - replace the example below with your own
    private int x;
    public static void main(String[] args) throws Exception{
        Scanner fin = null;
        try{
            fin = new Scanner(new File("dataExampleB.txt"));
        }
        catch(FileNotFoundException e)
    /**
     * Constructor for objects of class Data
     */
    public Data()
    {
        // initialise instance variables
        x = 0;
    }

    /**
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return x + y;
    }
}
