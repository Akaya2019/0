
import javax.swing.JFrame;
import java.io.IOException; 

/**
 * <h1>Main</h1>
 * <p>The Main program implements an application that
 * tests SelectFrame class
 * @author Ali KAYA
 * @version 1.0
 * @since 2022-01-28
 */
public class Main 
{
    /**
    * This is the main method which makes use of DayofYearSet class.
    * @param args Unused.
    * @exception IOException On input error.
    * @see IOException
    */
    public static void main( String args[] ) throws IOException
    {
        SelectFrame selectFrame = new SelectFrame();
        selectFrame.setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        selectFrame.setSize(400 , 400);
        selectFrame.setVisible( true );
    }
}
