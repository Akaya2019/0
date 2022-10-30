
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.BorderLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JRadioButton;

import java.lang.Math;
import java.io.*;
import java.io.IOException;

/**
 * <h1>PEG SOLITAIRE GAME</h1>
 * <p>The Peg Solitaire Game program implements an application that
 * simply a Game that is Peg Solitaire with 6 board option and 3 game type option.
 * @author Ali KAYA
 * @version 1.0
 * @since 2022-01-28
 */

public class SelectFrame extends JFrame implements PegSolitaireGame , Cloneable
{
    /**Keeps cellState enums*/
    public enum cellState
    {
	    emptyCell ,
	    pegCell ,
	    wallCell 
    }
    /**Keeps type of the board*/
    private int boardType;

    /**Used for label*/
    private JPanel panel1;
    /**Used for board type buttons*/
    private JPanel panel2;
    /**Used for printing some info to the frame*/
    private JLabel label;

    /**Used for Board Type 1*/
    private JRadioButton board1_JRadioButton;
    /**Used for Board Type 2*/
    private JRadioButton board2_JRadioButton;
    /**Used for Board Type 3*/
    private JRadioButton board3_JRadioButton;
    /**Used for Board Type 4*/
    private JRadioButton board4_JRadioButton;
    /**Used for Board Type 5*/
    private JRadioButton board5_JRadioButton;
    /**Used for Board Type 6*/
    private JRadioButton board6_JRadioButton;

    /**Used for label2 and some other JButtons*/
    private JPanel panel3;
    /**Used for game type buttons*/
    private JPanel panel4;
    /**Used for printing some info to the frame*/
    private JLabel label2;
    /**Used for Game Type 1 which is Human Game*/
    private JRadioButton game1_JRadioButton;
    /**Used for Game Type 2 which is Human Game + Computer help*/
    private JRadioButton game2_JRadioButton;
    /**Used for Game Type 3 which is computer plays the until it ends*/
    private JRadioButton game3_JRadioButton;

    /**Used for boards background panel*/
    private JPanel panel5;
    /**keeps cellState imformations buttons array*/
    private cellState[][] arr;
    /**Used for undo feature*/
    private int[][] previousMoves = new int[500][4];
    /**Used for 2d buttons array*/
    private JButton[][] buttons;
    /**Used for previousMoves row count*/
    private int RowP = 0;
    /**Used for counting of pressed buttons*/
    private int pressCount = 0;
    /**Keeps row index of first pressed button*/
    private int RowF = 0;
    /**Keeps column index of first pressed button*/
    private int ColumnF = 0;
    /**Keeps row index of second pressed button*/
    private int RowS = 0;
    /**Keeps column index of second pressed button*/
    private int ColumnS = 0;

    /**Used for undo save reset etc. buttons */
    private JPanel panel6;
    /**Used for undo feature*/
    private JButton undoButton;
    /**Used for Save feature*/
    private JButton saveButton;
    /**Used for Load feature*/
    private JButton loadButton;
    /**Used for Reset feature*/
    private JButton resetButton;
    /**Used for Forward move feature*/
    private JButton forwardButton;
    /**keeps information of game type*/
    private int computerFlag = 0;

    /**Used for labelPegInfo , labelEmptyInfo , and labelTakenInfo labels*/
    private JPanel panel7;
    /**Used for information of PegCell numbers*/
    private JLabel labelPegInfo;
    /**Used for information of EmptyCell numbers*/
    private JLabel labelEmptyInfo;
    /**Used for information of taken pegs numbers*/
    private JLabel labelTakenInfo;

    /**
    * <p>This constructor is no-parameter constructor of SelectFrame class.
    * Automatically initiates capacity to 10.
    */
    public SelectFrame()       //constructor
    {
        super("PegSolitaire Game");
        setLayout(new GridLayout(2 , 1));    //set frame layout

        label = new JLabel("Welcome to the game of Peg Solitaire..\n Please select Board Type..");

        panel1 = new JPanel();
        panel1.setLayout(new FlowLayout());
        panel1.add(label);
        add(panel1);

        board1_JRadioButton = new JRadioButton("Board 1");
        board2_JRadioButton = new JRadioButton("Board 2");
        board3_JRadioButton = new JRadioButton("Board 3");
        board4_JRadioButton = new JRadioButton("Board 4");
        board5_JRadioButton = new JRadioButton("Board 5");
        board6_JRadioButton = new JRadioButton("Board 6");

        panel2 = new JPanel();
        panel2.setLayout(new GridLayout(6, 1));
        panel2.add( board1_JRadioButton );
        panel2.add( board2_JRadioButton );
        panel2.add( board3_JRadioButton );
        panel2.add( board4_JRadioButton );
        panel2.add( board5_JRadioButton );
        panel2.add( board6_JRadioButton );
        add(panel2);

        RadioButtonHandler handler = new RadioButtonHandler();
        board1_JRadioButton.addActionListener(handler);
        board2_JRadioButton.addActionListener(handler);
        board3_JRadioButton.addActionListener(handler);
        board4_JRadioButton.addActionListener(handler);
        board5_JRadioButton.addActionListener(handler);
        board6_JRadioButton.addActionListener(handler);

    }

    /**
     * <p>This class is inner class of SelectFrame
     * class and used for handle of board type buttons.
     */
    private class RadioButtonHandler implements ActionListener
    {
        public void actionPerformed( ActionEvent event )
        {
            if(event.getSource() == board1_JRadioButton)
            {
                getContentPane().removeAll();
                arr = new cellState[9][9];
                boardType = 1;
                GameSelect();
                repaint();
                revalidate();
            }
            else if(event.getSource() == board2_JRadioButton)
            {
                getContentPane().removeAll();
                arr = new cellState[11][11];
                boardType = 2;
                GameSelect();
                repaint();
                revalidate();
            }
            else if(event.getSource() == board3_JRadioButton)
            {
                getContentPane().removeAll();
                arr = new cellState[10][10];
                boardType = 3;
                GameSelect();
                repaint();
                revalidate();
            }
            else if(event.getSource() == board4_JRadioButton)
            {
                getContentPane().removeAll();
                arr = new cellState[9][9];
                boardType = 4;
                GameSelect();
                repaint();
                revalidate();
            }
            else if(event.getSource() == board5_JRadioButton)
            {
                getContentPane().removeAll();
                arr = new cellState[11][11];
                boardType = 5;
                GameSelect();
                repaint();
                revalidate();
            }
            else if(event.getSource() == board6_JRadioButton)
            {
                getContentPane().removeAll();
                arr = new cellState[9][13];
                boardType = 6;
                GameSelect();
                repaint();
                revalidate();
            }
        }
    }

    /**
    * <p>This method used for selection of game types 
    */
    public void GameSelect() 
    {
        label2 = new JLabel( "Please select Game Type..");

        panel3 = new JPanel();
        panel3.setLayout(new FlowLayout());
        panel3.add(label2);
        add(panel3);

        game1_JRadioButton = new JRadioButton(" 1-> Human player game ");
        game2_JRadioButton = new JRadioButton(" 2-> Human player + Computer help game");
        game3_JRadioButton = new JRadioButton(" 3-> Computer plays the game until it ends");

        panel4 = new JPanel();
        panel4.setLayout(new GridLayout(3, 1));
        panel4.add( game1_JRadioButton );
        panel4.add( game2_JRadioButton );
        panel4.add( game3_JRadioButton );
        add(panel4);

        RadioButtonHandler2 handler = new RadioButtonHandler2();
        game1_JRadioButton.addActionListener(handler);
        game2_JRadioButton.addActionListener(handler);
        game3_JRadioButton.addActionListener(handler);

    }

    /**
     * <p>This class is inner class of SelectFrame
     * class and used for handle of game type buttons.
     */
    private class RadioButtonHandler2 implements ActionListener
    {
        public void actionPerformed( ActionEvent event )
        {
            if(event.getSource() == game1_JRadioButton)         //user plays the game
            {
                JOptionPane.showMessageDialog( null, "User is going to play..\nThe first button selected should be Peg and the second button should be Empty.");
                getContentPane().removeAll();
                BoardInitializer();
                BoardPrinter();
                repaint();
                revalidate();
            }
            else if(event.getSource() == game2_JRadioButton)    //Human player + Computer help game
            {
                JOptionPane.showMessageDialog( null, "User is going to play with Computer help(-->)..\nThe first button selected should be Peg and the second button should be Empty.");
                computerFlag = 1;
                getContentPane().removeAll();
                BoardInitializer();
                BoardPrinter();
                repaint();
                revalidate();
            }
            else if(event.getSource() == game3_JRadioButton)    //Computer plays the game until it ends
            {
                computerFlag = 1;
                getContentPane().removeAll();
                BoardInitializer();
                BoardPrinter();
                repaint();
                revalidate();

                if(boardType == 6)
                {
                    do
                    {
                        oneplay();
            
                    }while (endGame_6() != false);
                }
                else
                {
                    do
                    {
                        oneplay();
            
                    }while (endGame() != false);
                }
            }
        }
    }

    /**
    * <p>This method used for Printing of the board and other elements to the screen. 
    */
    public void BoardPrinter()
    {
        int i , j;
        switch (boardType) 
        {
            case 1:
                panel5 = new JPanel();
                panel5.setLayout(new GridLayout(9, 9, 1, 1));
                // setLayout(new GridLayout(9, 9, 1, 1));
                buttons = new JButton[9][9];
                break;
            
            case 2:
                panel5 = new JPanel();
                panel5.setLayout(new GridLayout(11, 11, 1, 1));
                // setLayout(new GridLayout(11, 11, 1, 1));
                buttons = new JButton[11][11];
                break;
                
            case 3:
                panel5 = new JPanel();
                panel5.setLayout(new GridLayout(10, 10, 1, 1));
                // setLayout(new GridLayout(10, 10, 1, 1));
                buttons = new JButton[10][10];
                break;
            
            case 4:
                panel5 = new JPanel();
                panel5.setLayout(new GridLayout(9, 9, 1, 1));
                // setLayout(new GridLayout(9, 9, 1, 1));
                buttons = new JButton[9][9];
                break;
                
            case 5:
                panel5 = new JPanel();
                panel5.setLayout(new GridLayout(11, 11, 1, 1));
                // setLayout(new GridLayout(11, 11, 1, 1));
                buttons = new JButton[11][11];
                break;
            
            case 6:
                panel5 = new JPanel();
                panel5.setLayout(new GridLayout(9, 13, 1, 1));
                // setLayout(new GridLayout(9, 13, 1, 1));
                buttons = new JButton[9][13];
                break;    
        }
        
        ButtonHandler handler = new ButtonHandler();
        for(i = 0; i < buttons.length ; ++i)
        {
            for(j = 0; j < buttons[i].length ; ++j)
            {
                JButton buttonW = new JButton("*");
                JButton buttonP = new JButton("P");
                JButton buttonE = new JButton("   ");
                
                if(arr[i][j] == cellState.wallCell){
                    buttonW.setBackground(Color.BLACK);
                    buttonW.setForeground(Color.GRAY);

                    buttonW.addActionListener( handler );
                    panel5.add(buttonW);                    
                    buttons[i][j] = buttonW;

                    buttonP = null;
                    buttonE = null;
                }    

                else if(arr[i][j] == cellState.pegCell){
                    buttonP.addActionListener( handler );
                    panel5.add(buttonP);
                    buttons[i][j] = buttonP;

                    buttonW = null;
                    buttonE = null;
                }    

                else if(arr[i][j] == cellState.emptyCell){
                    buttonE.addActionListener( handler );
                    panel5.add(buttonE);
                    buttons[i][j] = buttonE;

                    buttonW = null;
                    buttonP = null;
                }        
            }
        }
        add(panel5);
        pack();

        panel3 = new JPanel();
        panel3.setLayout(new GridLayout(2, 1));

        panel6 = new JPanel();
        panel6.setLayout(new BorderLayout());
        undoButton = new JButton(" <-- ");
        saveButton = new JButton(" SAVE ");
        loadButton = new JButton(" LOAD ");
        resetButton = new JButton(" RESET");
        if(computerFlag == 1) forwardButton = new JButton(" --> ");           //for computer games

        ButtonHandler2 handler2 = new ButtonHandler2();
        undoButton.addActionListener( handler2 );
        saveButton.addActionListener( handler2 );
        loadButton.addActionListener( handler2 );
        resetButton.addActionListener( handler2 );
        if(computerFlag == 1) forwardButton.addActionListener( handler2 ); 


        panel6.add(undoButton , BorderLayout.WEST);
        panel6.add(saveButton , BorderLayout.CENTER);
        panel6.add(loadButton , BorderLayout.SOUTH);
        panel6.add(resetButton , BorderLayout.EAST);
        if(computerFlag == 1) panel6.add(forwardButton , BorderLayout.NORTH);
        panel3.add(panel6);

        panel7 = new JPanel();
        panel7.setLayout(new GridLayout(3, 1));
        labelPegInfo = new JLabel("Number of Peg cells: " + NumberOfPegs());
        labelEmptyInfo = new JLabel("Number of Empty cells: " + NumberOfEmptyCells());
        labelTakenInfo = new JLabel("Number of Taken Pegs: " + NumberOfPegsTaken());
        panel7.add(labelPegInfo);
        panel7.add(labelEmptyInfo);
        panel7.add(labelTakenInfo);
        panel3.add(panel7);

        add(panel3);
        pack();
    }

    /**
     * <p>This class is inner class of SelectFrame
     * class and used for handle of JButtons which are PegCell WallCell and EmtyCell.
     */
    private class ButtonHandler implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            int flag = 0;
            pressCount++;
            switch (pressCount) {
                case 1:
                    if(event.getActionCommand() == "P"){
                        for(int i = 0; i < buttons.length ; ++i)
                        {
                            for(int j = 0; j < buttons[i].length ; ++j)
                            {
                                if(buttons[i][j].getText().equals("P") && event.getSource() == buttons[i][j]){
                                    RowF = i;
                                    ColumnF = j;
                                    flag = 1;
                                    break;
                                }
                            }
                            if(flag == 1){break;}
                        }
                        if(flag != 1)pressCount--;
                    }
                    else
                        pressCount--;
                    break;
            
                case 2:
                    if(event.getActionCommand() == "   "){
                        for(int i = 0; i < buttons.length ; ++i)
                        {
                            for(int j = 0; j < buttons[i].length ; ++j)
                            {
                                if(buttons[i][j].getText().equals("   ") && event.getSource() == buttons[i][j]){
                                    RowS = i;
                                    ColumnS = j;
                                    flag = 1;
                                    break;
                                }
                            }
                            if(flag == 1){break;}
                        }
                        if(flag != 1)pressCount--;
                    }
                    else
                        pressCount = 0;
                    break;
            }
            if(pressCount == 2 && boardType != 6)
            {
                if((RowF == RowS && Math.abs(ColumnF - ColumnS) == 2) || (ColumnF == ColumnS && Math.abs(RowF - RowS) == 2)){
                    if(move() == 1) pressCount = 0;
                    if(endGame() == false){
                        JOptionPane.showMessageDialog( null, "You Finished the Game");
                    }
                }
                else
                    pressCount = 0;
            }

            else if(pressCount == 2 && boardType == 6)
            {
                if((RowF == RowS && Math.abs(ColumnF - ColumnS) == 4) || (Math.abs(ColumnF - ColumnS) == 2 && Math.abs(RowF - RowS) == 2)){
                    if(move_6() == 1) pressCount = 0;
                    if(endGame_6() == false){
                        JOptionPane.showMessageDialog( null, "You Finished the Game");
                    }
                }
                else
                    pressCount = 0;
            }
        }
    }
    /**
     * <p>This class is inner class of SelectFrame
     * class and used for handle of JButtons which are undoButton , saveButton , loadButton,
     *  resetButton and forwardButton.
     */
    private class ButtonHandler2 implements ActionListener
    {
        public void actionPerformed( ActionEvent event )
        {
            if(event.getSource() == undoButton)
            {
                if(RowP != 0){
                    if(boardType != 6)
                    {
                        moveBack();
                        RowP--;
                    }
                    else
                    {
                        moveBack_6();
                        RowP--;                        
                    }
                }
            }
            else if(event.getSource() == saveButton)
            {
                String saveTXT = JOptionPane.showInputDialog("Please enter Save txt: ");
                try {
                    save(saveTXT);
                } catch (IOException  e) {
                    System.out.println("Error occurred.");
                }
            }
            else if(event.getSource() == loadButton)
            {
                String loadTXT = JOptionPane.showInputDialog( "Please enter Load txt: " );
                try {
                    getContentPane().removeAll();
                    BoardInitializer();
                    load(loadTXT);
                    RowP = 0;
                    BoardPrinter();
                    repaint();
                    revalidate();

                } catch (IOException  e) {
                    System.out.println("Error occurred. This file doesn't exist!");
                }
            }
            else if(event.getSource() == resetButton)
            {
                RowP = 0;
                getContentPane().removeAll();
                BoardInitializer();
                BoardPrinter();
                repaint();
                revalidate();
            }
            else if(event.getSource() == forwardButton)
            {
                oneplay();
            }
        }
    }

    /**
     * <p>This method is used for creating of one rondom movement.
     */
    public void oneplay()
    {
        int rondomRowF  = 0, rondomColumnF = 0, rondomWay = 0;
        int rondomRowS = 0 , rondomColumnS = 0;
        int rondomX = 0;

        if (boardType == 6)										//if board is sixth one
        {
            do
            {
                rondomRowF = (int)(Math.random() * 5) + 2;        //generates a random number for the row number
                rondomColumnF = (int)(Math.random() * 9) + 2;    //generates a random number for the column number
                rondomWay = (int)(Math.random() * 4) + 1;        //generates a random number for the way number
                rondomX = (int)(Math.random() * 2) + 0;

                if(rondomWay == 1){                     //RIGHT                     
                    rondomRowS = rondomRowF;
                    rondomColumnS = rondomColumnF + 4;
                }
                else if(rondomWay == 2){                //LEFT
                    rondomRowS = rondomRowF;
                    rondomColumnS = rondomColumnF - 4;
                }
                else if(rondomWay == 3){                //UP
                    if(rondomX == 0){
                        rondomRowS = rondomRowF - 2;
                        rondomColumnS = rondomColumnF + 2;}
                    else{
                        rondomRowS = rondomRowF - 2;
                        rondomColumnS = rondomColumnF - 2;
                    }    
                }
                else if(rondomWay == 4){                //DOWN
                    if(rondomX == 0){
                        rondomRowS = rondomRowF + 2;
                        rondomColumnS = rondomColumnF + 2;}
                    else{
                        rondomRowS = rondomRowF + 2;
                        rondomColumnS = rondomColumnF - 2;
                    }    
                }

            } while (is_moveValid( rondomRowF , rondomColumnF , rondomRowS , rondomColumnS ) != 1);
			
        }
        else
        {
            do
            {
                rondomRowF = (int)(Math.random() * arr.length-2) + 1;     	//generates a random number for the row number
                rondomColumnF = (int)(Math.random() * arr[0].length-2) + 1;  //generates a random number for the column number
                rondomWay = (int)(Math.random() * 4) + 1;                     //generates a random number for the way number

                if(rondomWay == 1){                     //RIGHT                     
                    rondomRowS = rondomRowF;
                    rondomColumnS = rondomColumnF + 2;
                }
                else if(rondomWay == 2){                //LEFT
                    rondomRowS = rondomRowF;
                    rondomColumnS = rondomColumnF - 2;
                }
                else if(rondomWay == 3){                //UP
                    rondomRowS = rondomRowF - 2;
                    rondomColumnS = rondomColumnF;
                }
                else if(rondomWay == 4){                //DOWN
                    rondomRowS = rondomRowF + 2;
                    rondomColumnS = rondomColumnF;  
                }

            } while (is_moveValid( rondomRowF , rondomColumnF , rondomRowS , rondomColumnS ) != 1);

        }
    }

    /**
     * <p>This method is used for checking the movement.
     * @param rondomRowF This parameter used for keeping row index of first input
     * @param rondomColumnF This parameter used for keeping column index of first input
     * @param rondomRowS This parameter used for keeping row index of second input
     * @param rondomColumnS This parameter used for keeping column index of second input
     * @return int - returns 1 if move is valid otherwise it returns 0.
     */
    public int is_moveValid( int rondomRowF , int rondomColumnF , int rondomRowS , int rondomColumnS )
    {
        int conditionCounter = 0;
        int ans = 0;

        if(rondomRowF < 0 || rondomColumnF < 0 || rondomRowS < 0 || rondomColumnS < 0) return 0;
        if(rondomRowF > arr.length-1 || rondomColumnF > arr[0].length-1 || rondomRowS > arr.length-1 || rondomColumnS > arr[0].length-1) return 0;

        if(arr[rondomRowF][rondomColumnF] == cellState.pegCell) conditionCounter++;
        if(arr[rondomRowS][rondomColumnS] == cellState.emptyCell) conditionCounter++;

        if(conditionCounter == 2 && boardType != 6)
        {
            if((rondomRowF == rondomRowS && Math.abs(rondomColumnF - rondomColumnS) == 2) || (rondomColumnF == rondomColumnS && Math.abs(rondomRowF - rondomRowS) == 2)){
                RowF = rondomRowF;
                ColumnF = rondomColumnF;
                RowS = rondomRowS;
                ColumnS = rondomColumnS;
                ans = move();
                if(endGame() == false){
                    JOptionPane.showMessageDialog( null, "You Finished the Game");
                }
            }
            else
                ans = 0;
        }

        else if(conditionCounter == 2 && boardType == 6)
        {
            if((rondomRowF == rondomRowS && Math.abs(rondomColumnF - rondomColumnS) == 4) || (Math.abs(rondomColumnF - rondomColumnS) == 2 && Math.abs(rondomRowF - rondomRowS) == 2)){
                RowF = rondomRowF;
                ColumnF = rondomColumnF;
                RowS = rondomRowS;
                ColumnS = rondomColumnS;
                ans = move_6();
                if(endGame_6() == false){
                    JOptionPane.showMessageDialog( null, "You Finished the Game");
                }
            }
            else
                ans = 0;
        }

        return ans;
    }
    /**
     * <p>This method is used for making one valid movement for first 5 boards.
     * @return int - returns 1 if move is done otherwise it returns 0.
     */
    public int move()
    {
        previousMoves[RowP][0] = RowF;
        previousMoves[RowP][1] = ColumnF;
        previousMoves[RowP][2] = RowS;
        previousMoves[RowP][3] = ColumnS;
        RowP++;

        if(RowF == RowS && Math.abs(ColumnF - ColumnS) == 2)        //RIGHT and LEFT
        {
            if(ColumnF < ColumnS){      //RIGHT MOVEMENT
                if(arr[RowF][ColumnF+1] != cellState.pegCell){RowP--;return 0;}
                arr[RowF][ColumnF] = cellState.emptyCell;
				arr[RowF][ColumnF+1] = cellState.emptyCell;
                arr[RowS][ColumnS] = cellState.pegCell;
                getContentPane().removeAll();
				BoardPrinter();
                repaint();
                revalidate();
            }
            else{                       //LEFT MOVEMENT
                if(arr[RowF][ColumnF-1] != cellState.pegCell){RowP--;return 0;}
                arr[RowF][ColumnF] = cellState.emptyCell;
				arr[RowF][ColumnF-1] = cellState.emptyCell;
                arr[RowS][ColumnS] = cellState.pegCell;
                getContentPane().removeAll();
				BoardPrinter();
                repaint();
                revalidate();
            }
        }

        else if(ColumnF == ColumnS && Math.abs(RowF - RowS) == 2)   //UP and DOWN
        {
            if(RowF > RowS){            //UP MOVEMENT
                if(arr[RowF-1][ColumnF] != cellState.pegCell){RowP--;return 0;}
                arr[RowF][ColumnF] = cellState.emptyCell;
				arr[RowF-1][ColumnF] = cellState.emptyCell;
                arr[RowS][ColumnS] = cellState.pegCell;
                getContentPane().removeAll();
				BoardPrinter();
                repaint();
                revalidate();
            }
            else{                       //DOWN MOVEMENT
                if(arr[RowF+1][ColumnF] != cellState.pegCell){RowP--;return 0;}
                arr[RowF][ColumnF] = cellState.emptyCell;
				arr[RowF+1][ColumnF] = cellState.emptyCell;
                arr[RowS][ColumnS] = cellState.pegCell;
                getContentPane().removeAll();
				BoardPrinter();
                repaint();
                revalidate();                
            }
        }
        return 1;
    }

    /**
     * <p>This method is used for making one valid movement for sixth board.
     * @return int - returns 1 if move is done otherwise it returns 0.
     */
    public int move_6()
    {
        previousMoves[RowP][0] = RowF;
        previousMoves[RowP][1] = ColumnF;
        previousMoves[RowP][2] = RowS;
        previousMoves[RowP][3] = ColumnS;
        RowP++;

        if(RowF == RowS && Math.abs(ColumnF - ColumnS) == 4)        //RIGHT and LEFT
        {
            if(ColumnF < ColumnS){      //RIGHT MOVEMENT
                if(arr[RowF][ColumnF+2] != cellState.pegCell){RowP--;return 0;}
                arr[RowF][ColumnF] = cellState.emptyCell;
				arr[RowF][ColumnF+2] = cellState.emptyCell;
                arr[RowS][ColumnS] = cellState.pegCell;
                getContentPane().removeAll();
				BoardPrinter();
                repaint();
                revalidate();
            }
            else{                       //LEFT MOVEMENT
                if(arr[RowF][ColumnF-2] != cellState.pegCell){RowP--;return 0;}
                arr[RowF][ColumnF] = cellState.emptyCell;
				arr[RowF][ColumnF-2] = cellState.emptyCell;
                arr[RowS][ColumnS] = cellState.pegCell;
                getContentPane().removeAll();
				BoardPrinter();
                repaint();
                revalidate();
            }
        }

        else if(Math.abs(ColumnF - ColumnS) == 2 && Math.abs(RowF - RowS) == 2)   //UP and DOWN
        {
            if(RowF > RowS){            //UP MOVEMENT
                if(ColumnF < ColumnS)   //Right-Up
                {
                    if(arr[RowF-1][ColumnF+1] != cellState.pegCell){RowP--;return 0;}
                    arr[RowF][ColumnF] = cellState.emptyCell;
                    arr[RowF-1][ColumnF+1] = cellState.emptyCell;
                    arr[RowS][ColumnS] = cellState.pegCell;
                    getContentPane().removeAll();
                    BoardPrinter();
                    repaint();
                    revalidate();
                }
                else                    //Left-Up
                {
                    if(arr[RowF-1][ColumnF-1] != cellState.pegCell){RowP--;return 0;}
                    arr[RowF][ColumnF] = cellState.emptyCell;
                    arr[RowF-1][ColumnF-1] = cellState.emptyCell;
                    arr[RowS][ColumnS] = cellState.pegCell;
                    getContentPane().removeAll();
                    BoardPrinter();
                    repaint();
                    revalidate();                    
                }
            }
            else{                       //DOWN MOVEMENT
                if(ColumnF < ColumnS)   //Right-Down
                {
                    if(arr[RowF+1][ColumnF+1] != cellState.pegCell){RowP--;return 0;}
                    arr[RowF][ColumnF] = cellState.emptyCell;
                    arr[RowF+1][ColumnF+1] = cellState.emptyCell;
                    arr[RowS][ColumnS] = cellState.pegCell;
                    getContentPane().removeAll();
                    BoardPrinter();
                    repaint();
                    revalidate();
                }
                else                    //Left-Down
                {
                    if(arr[RowF+1][ColumnF-1] != cellState.pegCell){RowP--;return 0;}
                    arr[RowF][ColumnF] = cellState.emptyCell;
                    arr[RowF+1][ColumnF-1] = cellState.emptyCell;
                    arr[RowS][ColumnS] = cellState.pegCell;
                    getContentPane().removeAll();
                    BoardPrinter();
                    repaint();
                    revalidate();                    
                }                
            }
        }
        return 1;
    }

    /**
     * <p>This method is used for making one Undo movement for first 5 boards.
     */
    public void moveBack()
    {
        if(previousMoves[RowP-1][0] == previousMoves[RowP-1][2] && Math.abs(previousMoves[RowP-1][1] - previousMoves[RowP-1][3]) == 2)        //RIGHT and LEFT
        {
            if(previousMoves[RowP-1][1] < previousMoves[RowP-1][3]){      //RIGHT MOVEMENT
                arr[previousMoves[RowP-1][0]][previousMoves[RowP-1][1]] = cellState.pegCell;
				arr[previousMoves[RowP-1][0]][previousMoves[RowP-1][1]+1] = cellState.pegCell;
                arr[previousMoves[RowP-1][2]][previousMoves[RowP-1][3]] = cellState.emptyCell;
                getContentPane().removeAll();
				BoardPrinter();
                repaint();
                revalidate();
            }
            else{                       //LEFT MOVEMENT
                arr[previousMoves[RowP-1][0]][previousMoves[RowP-1][1]] = cellState.pegCell;
				arr[previousMoves[RowP-1][0]][previousMoves[RowP-1][1]-1] = cellState.pegCell;
                arr[previousMoves[RowP-1][2]][previousMoves[RowP-1][3]] = cellState.emptyCell;
                getContentPane().removeAll();
				BoardPrinter();
                repaint();
                revalidate();
            }
        }

        else if(previousMoves[RowP-1][1] == previousMoves[RowP-1][3] && Math.abs(previousMoves[RowP-1][0] - previousMoves[RowP-1][2]) == 2)   //UP and DOWN
        {
            if(previousMoves[RowP-1][0] > previousMoves[RowP-1][2]){            //UP MOVEMENT
                arr[previousMoves[RowP-1][0]][previousMoves[RowP-1][1]] = cellState.pegCell;
				arr[previousMoves[RowP-1][0]-1][previousMoves[RowP-1][1]] = cellState.pegCell;
                arr[previousMoves[RowP-1][2]][previousMoves[RowP-1][3]] = cellState.emptyCell;
                getContentPane().removeAll();
				BoardPrinter();
                repaint();
                revalidate();
            }
            else{                       //DOWN MOVEMENT
                arr[previousMoves[RowP-1][0]][previousMoves[RowP-1][1]] = cellState.pegCell;
				arr[previousMoves[RowP-1][0]+1][previousMoves[RowP-1][1]] = cellState.pegCell;
                arr[previousMoves[RowP-1][2]][previousMoves[RowP-1][3]] = cellState.emptyCell;
                getContentPane().removeAll();
				BoardPrinter();
                repaint();
                revalidate();                
            }
        }
    }
    /**
     * <p>This method is used for making one Undo movement for sixth boards.
     */
    public void moveBack_6()
    {
        if(previousMoves[RowP-1][0] == previousMoves[RowP-1][2] && Math.abs(previousMoves[RowP-1][1] - previousMoves[RowP-1][3]) == 4)        //RIGHT and LEFT
        {
            if(previousMoves[RowP-1][1] < previousMoves[RowP-1][3]){      //RIGHT MOVEMENT
                arr[previousMoves[RowP-1][0]][previousMoves[RowP-1][1]] = cellState.pegCell;
				arr[previousMoves[RowP-1][0]][previousMoves[RowP-1][1]+2] = cellState.pegCell;
                arr[previousMoves[RowP-1][2]][previousMoves[RowP-1][3]] = cellState.emptyCell;
                getContentPane().removeAll();
				BoardPrinter();
                repaint();
                revalidate();
            }
            else{                       //LEFT MOVEMENT
                arr[previousMoves[RowP-1][0]][previousMoves[RowP-1][1]] = cellState.pegCell;
				arr[previousMoves[RowP-1][0]][previousMoves[RowP-1][1]-2] = cellState.pegCell;
                arr[previousMoves[RowP-1][2]][previousMoves[RowP-1][3]] = cellState.emptyCell;
                getContentPane().removeAll();
				BoardPrinter();
                repaint();
                revalidate();
            }
        }

        else if(Math.abs(previousMoves[RowP-1][1] - previousMoves[RowP-1][3]) == 2 && Math.abs(previousMoves[RowP-1][0] - previousMoves[RowP-1][2]) == 2)   //UP and DOWN
        {
            if(previousMoves[RowP-1][0] > previousMoves[RowP-1][2]){            //UP MOVEMENT
                if(previousMoves[RowP-1][1] < previousMoves[RowP-1][3])        //Right-Up
                {
                    arr[previousMoves[RowP-1][0]][previousMoves[RowP-1][1]] = cellState.pegCell;
                    arr[previousMoves[RowP-1][0]-1][previousMoves[RowP-1][1]+1] = cellState.pegCell;
                    arr[previousMoves[RowP-1][2]][previousMoves[RowP-1][3]] = cellState.emptyCell;
                    getContentPane().removeAll();
                    BoardPrinter();
                    repaint();
                    revalidate();
                }
                else        //Left-Up
                {
                    arr[previousMoves[RowP-1][0]][previousMoves[RowP-1][1]] = cellState.pegCell;
                    arr[previousMoves[RowP-1][0]-1][previousMoves[RowP-1][1]-1] = cellState.pegCell;
                    arr[previousMoves[RowP-1][2]][previousMoves[RowP-1][3]] = cellState.emptyCell;
                    getContentPane().removeAll();
                    BoardPrinter();
                    repaint();
                    revalidate();
                }    
            }
            else{                       //DOWN MOVEMENT
                if(previousMoves[RowP-1][1] < previousMoves[RowP-1][3])        //Right-Down
                {
                    arr[previousMoves[RowP-1][0]][previousMoves[RowP-1][1]] = cellState.pegCell;
                    arr[previousMoves[RowP-1][0]+1][previousMoves[RowP-1][1]+1] = cellState.pegCell;
                    arr[previousMoves[RowP-1][2]][previousMoves[RowP-1][3]] = cellState.emptyCell;
                    getContentPane().removeAll();
                    BoardPrinter();
                    repaint();
                    revalidate(); 
                }
                else        //Left-Down
                {
                    arr[previousMoves[RowP-1][0]][previousMoves[RowP-1][1]] = cellState.pegCell;
                    arr[previousMoves[RowP-1][0]+1][previousMoves[RowP-1][1]-1] = cellState.pegCell;
                    arr[previousMoves[RowP-1][2]][previousMoves[RowP-1][3]] = cellState.emptyCell;
                    getContentPane().removeAll();
                    BoardPrinter();
                    repaint();
                    revalidate();                     
                }
            }
        }
    }    

    /**
     * <p>This method is used to save progress in a txt file
     * @param saveTXT - the string which specifies txt files' name.
     * @return int - returns 1 if save is done otherwise it returns 0.
     */
    public int save(String saveTXT) throws IOException
    {
        int ans = 0;
        String subStr = saveTXT.substring(saveTXT.length()-4);

        if (!subStr.equals(".txt")){   //if the file extension is not .txt give error
            JOptionPane.showMessageDialog( null, "Wrong filename try again!!");
            ans = 0;
        } 	
        else{

            try 
            {
                PrintWriter out = new PrintWriter(saveTXT); // for writting in file    
                String newLine = System.getProperty("line.separator"); // taking new line 
    
                out.print(boardType);
                out.print(newLine);
    
                for(var row : arr){
                    for(var col : row){
                        if(col == cellState.emptyCell) out.print(0);				//prints the arr on txt file
                        else if(col == cellState.pegCell) out.print(1);				
                        else if(col == cellState.wallCell) out.print(2);				
                    }
                    out.print(newLine);	  
                }
    
                out.close();
                ans = 1;                
            }
            catch (IOException err)
            {
                System.out.println("Error occurred.");
                err.printStackTrace();
            }

        }
        return ans;
    }

    /**
     * <p>This method is used to load progress and board from a txt file
     * @param loadTXT - the string which specifies txt files' name.
     * @return int - returns 1 if load is done otherwise it returns 0.
     */
    public int load(String loadTXT) throws IOException
    {
        int ans = 0;
        int i = 0;
        int j = 0;
        int e ;  

        Reader reader = new FileReader(loadTXT);
        if (reader.ready())
        {	
            try 
            {
                boardType = reader.read();

                if(boardType == '1'){
                    boardType = 1; 
                    arr = new cellState[9][9];
                }    
                else if(boardType == '2'){
                    boardType = 2;
                    arr = new cellState[11][11];
                }    
                else if(boardType == '3'){
                    boardType = 3;
                    arr = new cellState[10][10];
                }    
                else if(boardType == '4'){
                    boardType = 4;
                    arr = new cellState[9][9];
                }    
                else if(boardType == '5'){
                    boardType = 5;
                    arr = new cellState[11][11];
                }    
                else if(boardType == '6'){
                    boardType = 6;
                    arr = new cellState[9][13];
                }    
                e = reader.read();
                e = reader.read();

                for (i = 0; i < arr.length; ++i){
                    for (j = 0; j < arr[i].length; ++j){
                        e = reader.read();
                        if(e == '0') arr[i][j] = cellState.emptyCell;
                        else if(e == '1') arr[i][j] = cellState.pegCell;
                        else if(e == '2') arr[i][j] = cellState.wallCell;
                    }
                    e = reader.read();
                    e = reader.read();
                }
                ans = 1;
                reader.close();
            } 
            catch (IOException err) 
            {
                System.out.println("Error occurred. This file doesn't exist!");
                err.printStackTrace();
            }
        }
        else{ 
            JOptionPane.showMessageDialog( null, "Unable to Open File!!");
            ans = 0;
        }
        return ans;
    }

    /**
     * <p>This method is used to returning number of Pegs
     * @return int - returns number of PegCell on the board.
     */
    public int NumberOfPegs()
    {
        int i , j , counter = 0;

        for (i = 0; i < arr.length; ++i){
            for (j = 0; j < arr[i].length; ++j){
                if(arr[i][j] == cellState.pegCell) counter += 1;
            }
        }
        return counter;
    }

    /**
     * <p>This method is used to returning number of EmptyCells
     * @return int - returns number of EmptyCells on the board.
     */
    public int NumberOfEmptyCells()
    {
        int i , j , counter = 0;

        for (i = 0; i < arr.length; ++i){
            for (j = 0; j < arr[i].length; ++j){
                if(arr[i][j] == cellState.emptyCell) counter += 1;
            }
        }
        return counter;
    }

    /**
     * <p>This method is used to returning number of taken Pegs from the board
     * @return int - returns number of taken Pegs from the board
     */
    public int NumberOfPegsTaken()
    {
        int i , j , counter = 0;

        for (i = 0; i < arr.length; ++i){
            for (j = 0; j < arr[i].length; ++j){
                if(arr[i][j] == cellState.emptyCell) counter += 1;
            }
        }
        return counter - 1;	    //returns counter - 1 because board has 1 empty cell at the starting of the game	
    }

    /**
     * <p>This method is used to find out if the game is over or not for first 5 boards
     * @return boolean - returns true if game is ended otherwise it returns false
     */
    public boolean endGame()
	{
		int flag = 0; 
		boolean out = false;
		for (int i = 1; i < arr.length-1; ++i)
		{	
			for (int j = 1; j < arr[i].length-1; ++j)
			{
				if (arr[i][j] == cellState.pegCell && !(arr[i][j+1] == cellState.wallCell) && arr[i][j+2] == cellState.emptyCell && arr[i][j+1] == cellState.pegCell){        //right movement possible
					out  = true;
			    	flag = 1;
				}
				else if (arr[i][j] == cellState.pegCell && !(arr[i][j-1] == cellState.wallCell) && arr[i][j-2] == cellState.emptyCell && arr[i][j-1] == cellState.pegCell){   //left movement possible
					out  = true;
			    	flag = 1;
				}
				else if (arr[i][j] == cellState.pegCell && !(arr[i-1][j] == cellState.wallCell) && arr[i-2][j] == cellState.emptyCell && arr[i-1][j] == cellState.pegCell){   //up movement possible
					out  = true;
			    	flag = 1;
				}
				else if (arr[i][j] == cellState.pegCell && !(arr[i+1][j] == cellState.wallCell) && arr[i+2][j] == cellState.emptyCell && arr[i+1][j] == cellState.pegCell){   //down movement possible
					out  = true;
			    	flag = 1;
				}
				if(flag == 1) break;
			}
			if(flag == 1) break;
		}

		return out;
	}

    /**
     * <p>This method is used to find out if the game is over or not for sixth board
     * @return boolean - returns true if game is ended otherwise it returns false
     */
    public boolean endGame_6()
    {
        int flag = 0 ;
        boolean out = false;
        for (int i = 2; i < arr.length-2; ++i)
        {
            for (int j = 2; j < arr[i].length-2; ++j)
            {
                if (arr[i][j] == cellState.pegCell && !(arr[i][j+2] == cellState.wallCell) && arr[i][j+4] == cellState.emptyCell && arr[i][j+2] == cellState.pegCell){        //right movement possible
                    out  = true;
                    flag = 1;
                }
                else if (arr[i][j] == cellState.pegCell && !(arr[i][j-2] == cellState.wallCell) && arr[i][j-4] == cellState.emptyCell && arr[i][j-2] == cellState.pegCell){   //left movement possible
                    out  = true;
                    flag = 1;
                }
                else if (arr[i][j] == cellState.pegCell && (4 <= i && i <= 6) && arr[i-2][j-2] == cellState.emptyCell && arr[i-1][j-1] == cellState.pegCell){   //up-left movement possible
                    out  = true;
                    flag = 1;
                }
                else if (arr[i][j] == cellState.pegCell && (4 <= i && i <= 6) && arr[i-2][j+2] == cellState.emptyCell && arr[i-1][j+1] == cellState.pegCell){   //up-right movement possible
                    out  = true;
                    flag = 1;
                }
                else if (arr[i][j] == cellState.pegCell && (2 <= i && i <= 4) && arr[i+2][j-2] == cellState.emptyCell && arr[i+1][j-1] == cellState.pegCell){   //down-left movement possible
                    out  = true;
                    flag = 1;
                }
                else if (arr[i][j] == cellState.pegCell && (2 <= i && i <= 4) && arr[i+2][j+2] == cellState.emptyCell && arr[i+1][j+1] == cellState.pegCell){   //down-right movement possible
                    out  = true;
                    flag = 1;
                }
                if(flag == 1) break;
            }
            if(flag == 1) break;
        }

        return out;
    }

    /**
     * <p>This method is used to initialize arr field correctly.
     */
    public void BoardInitializer()
    {
        int i , j;
        for (i = 0; i < arr.length; i++)
        {
            for (j = 0; j < arr[i].length; j++)
            {
                arr[i][j] = cellState.wallCell;
            } 
        }

        if(boardType == 1)
        {
            int random = (int)(Math.random() * 2) + 1;

            for (i = 0; i <= 8; ++i)
	        {
		        if (i == 1 || i == 7)
			        for (j = 3; j <= 5; ++j)
				        arr[i][j] = cellState.pegCell;

		        else if (i == 2 || i == 6)
			        for (j = 2; j <= 6; ++j)
				        arr[i][j] = cellState.pegCell;
		
		        else if (i==3 || i==4 || i==5)
			        for (j = 1; j <= 7; ++j)
				        arr[i][j] = cellState.pegCell;
	        }

	        if (random == 1){
		        arr[3][4] = cellState.emptyCell;
	        }
	        else{
		        arr[5][4] = cellState.emptyCell;			
	        }    
        }
        else if(boardType == 2)
        {
        	for (i = 0; i <= 10; ++i)
	        {
		        if (i == 1 || i == 2 || i == 3 || i == 7 || i == 8 || i == 9)
			        for (j = 4; j <= 6; ++j)
				        arr[i][j] = cellState.pegCell;
		
		        else if (i==4 || i==5 || i==6)
			        for (j = 1; j <= 9; ++j)
				        arr[i][j] = cellState.pegCell;
	        }
	        arr[5][5] = cellState.emptyCell;
        }
        else if(boardType == 3)
        {
			for (i = 0; i <= 10; ++i)
	        {
		        if (i == 1 || i == 2 || i == 3 || i == 7 || i == 8)
			        for (j = 3; j <= 5; ++j)
				        arr[i][j] = cellState.pegCell;
		
		        else if (i==4 || i==5 || i==6)
			        for (j = 1; j <= 8; ++j)
				        arr[i][j] = cellState.pegCell;
	        }
	        arr[5][4] = cellState.emptyCell;            
        }
        else if(boardType == 4)
        {
			for (i = 0; i <= 8; ++i)
	        {
		        if (i == 1 || i == 2 || i == 6 || i == 7)
			        for (j = 3; j <= 5; ++j)
				        arr[i][j] = cellState.pegCell;
		
		        else if (i==3 || i==4 || i==5)
			        for (j = 1; j <= 7; ++j)
				        arr[i][j] = cellState.pegCell;
	        }
	        arr[4][4] = cellState.emptyCell;
        }
        else if(boardType == 5)
        {
        	for (i = 0; i <= 10; ++i)
	        {
		        if (i == 1 || i == 9)
			        arr[i][5] = cellState.pegCell;

		        else if (i == 2 || i == 8)
			        for (j = 4; j <= 6; ++j)
				        arr[i][j] = cellState.pegCell;

		        else if (i == 3 || i == 7)
			        for (j = 3; j <= 7; ++j)
				        arr[i][j] = cellState.pegCell;

		        else if (i == 4 || i == 6)
			        for (j = 2; j <= 8; ++j)
				        arr[i][j] = cellState.pegCell;						
		
		        else if (i==5)
			        for (j = 1; j <= 9; ++j)
				        arr[i][j] = cellState.pegCell;
	        }
	        arr[5][5] = cellState.emptyCell;
        }
        else if(boardType == 6)
        {
            arr[2][6] = cellState.emptyCell;

            for (i = 3 , j = 5; j <= 7; ++j){
                if(j % 2 == 1) arr[i][j] = cellState.pegCell;
                else arr[i][j] = cellState.wallCell;
            }
            for (i = 4 , j = 4; j <= 8; ++j){
                if(j % 2 == 0) arr[i][j] = cellState.pegCell;
                else arr[i][j] = cellState.wallCell;
            }
            for (i = 5 , j = 3; j <= 9; ++j){
                if(j % 2 == 1) arr[i][j] = cellState.pegCell;
                else arr[i][j] = cellState.wallCell;						
            }
            for (i = 6 , j = 2; j <= 10; ++j){
                if(j % 2 == 0) arr[i][j] = cellState.pegCell;
                else arr[i][j] = cellState.wallCell;
            }
        }
    }

    /**
     * <p>This method is used to create clone reference of SelectFrame class
     * @return SelectFrame - returns SelectFrame reference which is clone
     */
    public SelectFrame clone ()
    {
        try 
        {
            SelectFrame copy = (SelectFrame) super.clone();

            if(copy.boardType == 1){ 
                copy.arr = new cellState[9][9];
                copy.buttons = new JButton[9][9];
            }    
            else if(copy.boardType == 2){
                copy.arr = new cellState[11][11];
                copy.buttons = new JButton[11][11];
            }    
            else if(copy.boardType == 3){
                copy.arr = new cellState[10][10];
                copy.buttons = new JButton[10][10];
            }    
            else if(copy.boardType == 4){
                copy.arr = new cellState[9][9];
                copy.buttons = new JButton[9][9];
            }    
            else if(copy.boardType == 5){
                copy.arr = new cellState[11][11];
                copy.buttons = new JButton[11][11];
            }    
            else if(copy.boardType == 6){
                copy.arr = new cellState[9][13];
                copy.buttons = new JButton[9][13];
            }
            
            for(int i = 0; i < buttons.length ; ++i){
                for(int j = 0; j < buttons[i].length ; ++j)
                {
                    JButton buttonW = new JButton("*");
                    JButton buttonP = new JButton("P");
                    JButton buttonE = new JButton("   ");
                    
                    if(copy.arr[i][j] == cellState.wallCell){
                        buttonW.setBackground(Color.BLACK);
                        buttonW.setForeground(Color.GRAY);
                       
                        copy.buttons[i][j] = buttonW;
                        buttonP = null;
                        buttonE = null;
                    }    
    
                    else if(copy.arr[i][j] == cellState.pegCell){
                        copy.buttons[i][j] = buttonP;
                        buttonW = null;
                        buttonE = null;
                    }    
    
                    else if(copy.arr[i][j] == cellState.emptyCell){
                        copy.buttons[i][j] = buttonE;
                        buttonW = null;
                        buttonP = null;
                    }        
                }
            }

            copy.previousMoves = new int[500][4];
            return copy;
        } 
        catch (CloneNotSupportedException e) {
            return null;
        }
    }
}
