
import java.io.IOException; 

public interface PegSolitaireGame 
{
    void GameSelect();
    void BoardPrinter();

    void oneplay();
    int is_moveValid( int rondomRowF , int rondomColumnF , int rondomRowS , int rondomColumnS );
    int move();
    int move_6();
    void moveBack();
    void moveBack_6();

    int save(String saveTXT) throws IOException;
    int load(String loadTXT) throws IOException;

    int NumberOfPegs();
    int NumberOfEmptyCells();
    int NumberOfPegsTaken();
    boolean endGame();
    boolean endGame_6();
    void BoardInitializer();
    
}
