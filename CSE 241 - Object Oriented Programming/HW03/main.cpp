    #include <iostream>
    #include <string>
    #include <vector>
    #include <fstream>
    #include <ctime> /*C library to generate random numbers*/
    #include <conio.h>

    using namespace std;

    /*Enum types to represent cell states*/
    enum cellState {wall,blank,peg,blank2};

    class PegSolitaire {

        public:
            //Constructer 1
            PegSolitaire() {
                cellWall.setState(wall);
                cellPeg.setState(peg);
                cellBlank.setState(blank);
                cellBlank2.setState(blank2);
                fillVector();
            }

            class Cell {
                public:

                    Cell() {/*Constructer*/}

                    cellState getCell() {return state;} //Getter function to get cell state.
                    int getRow() {return row;} //Getter function to get row.
                    int getColumn() {return column;} //Getter function to get column.

                    void setState(cellState a) {state=a;}
                    void setRow(int a) {row=a;}
                    void setColumn(int a) {column=a;}

                private:
                    cellState state;
                    int row;
                    int column;
            };




            //PegSolitaire::Cell example;
            void fillVector(); /*Create and fills the board with walls*/
            void changeVector(int i,int j);
            void createBoards(const int chosenBoard=1); /*To create intended boards*/
            void printBoard(const int chosenBoard);
            int checkSolved(); /*To check if the puzzle is solved and return the score*/
            void resetBoard(); /*To reset the board after switching to another*/

            /*Getter functions*/
            vector<vector <Cell>> getVector() {return board;}
            Cell get_cellWall() {return cellWall;}
            Cell get_cellPeg() {return cellPeg;}
            Cell get_cellBlank() {return cellBlank;}
            Cell get_cellBlank2() {return cellBlank2;}

            int moveUP(const int row, const int column,const int ai=0);   /*To move up   */
            int moveDOWN(const int row, const int column,const int ai=0); /*To move down */
            int moveRIGHT(const int row, const int column,const int ai=0);/*To move right*/
            int moveLEFT(const int row, const int column,const int ai=0); /*To move left */

            /*Since board 6 has unique pattern out of ordinary, it has its own functions for each move.*/
            int board6LEFT(const int row, const int column, const int ai=0);
            int board6RIGHT(const int row, const int column, const int ai=0);
            int board6UPRIGHT(const int row, const int column, const int ai=0);
            int board6UPLEFT(const int row, const int column, const int ai=0);
            int board6DOWNLEFT(const int row, const int column, const int ai=0);
            int board6DOWNRIGHT(const int row, const int column, const int ai=0);

            /*Functions for both saving and loading.*/
            void saveBoard(  string &inputFile, const int chosenBoard, const int ai, const int numofMoves,const bool boardSix);
            int  loadBoard(  string &inputFile, int &chosenBoard, int &ai, int &numofMoves, bool &boardSix);
            /*Loading function returns an integer to see if the file has opened correctly.*/



        private:
            PegSolitaire::Cell cellWall;
            PegSolitaire::Cell cellPeg;
            PegSolitaire::Cell cellBlank;
            PegSolitaire::Cell cellBlank2;

            vector<vector <Cell>> board;


            //static const int sayi;
    };


    //    bool operator == (cellState a, cellState b) {
    //                if(a==b)return 1;
    //                else return 0;}
    /*--------------------------------------------------------------------------------------------*/


    bool operator == (PegSolitaire::Cell a, PegSolitaire::Cell b) {
        if(a.getCell()==b.getCell()) {
            return 1;
        }
        else {
            return 0;
        }
    }
    bool operator != (PegSolitaire::Cell a, PegSolitaire::Cell b) {
        if(a.getCell()==b.getCell()) {
            return 0;
        }
        else {
            return 1;
        }
    }
    /*--------------------------------------------------------------------------------------------*/



    int main()
    {


        PegSolitaire ali;





        /*Reaching every-single elements in the vector to print*/

        //        PegSolitaire::Cell naber;
        //        PegSolitaire::Cell iyi;
        //        PegSolitaire::Cell sen;
        //        naber.setState(wall);
        //        iyi.setState(blank);
        //        sen.setState(peg);
        //
        //        umut.board.push_back(naber);
        //        umut.board.push_back(iyi);
        //        umut.board.push_back(sen);
        //
        //        if(umut.board[0]==naber) {
        //            cout<<"sen";
        //        }
        //        if(umut.board[1]==sen) {
        //            cout<<"naber";
        //        }


        getch();
        return 0;
    }

    /*--------------------------------------------------------------------------------------------*/
    void PegSolitaire::changeVector(int i, int j) {

        PegSolitaire::Cell temp;
        temp.setRow(i);
        temp.setColumn(j);
        board[i][j]=temp;
    }
    /*--------------------------------------------------------------------------------------------*/

    void PegSolitaire::fillVector() {

        PegSolitaire::Cell shadowCell;
        shadowCell.setState(wall);

        for(int i=0; i<11; i++)
        {
            vector<Cell> temp;
            for(int j=0; j<11; j++) {
                temp.push_back(shadowCell);
            }

            board.push_back(temp);
        }
    }
    /*--------------------------------------------------------------------------------------------*/

    void PegSolitaire::printBoard(const int chosenBoard)
    {
        int row=1; /*Row numbers to print*/

        /*Printing the appropriate letters*/
        if(chosenBoard==1||chosenBoard==4) {
            cout<<"   A B C D E F G";
        }
        if(chosenBoard==2||chosenBoard==5) {
            cout<<"   A B C D E F G H I";
        }
        if(chosenBoard==3) {
            cout<<"   A B C D E F G H";
        }
        if(chosenBoard==6) {
            cout<<"   A B C D E F G H I";
        }



        /*Reaching every-single elements in the vector to print*/
        for(int i=0; i<11; i++)
        {
            for(int j=0; j<11; j++)
            {

                if(board[i][j]==cellWall)
                { cout<<"  "; }
                else
                    if(board[i][j]==cellBlank)
                    { cout<<". "; }
                    else
                        if(board[i][j]==cellPeg)
                        { cout<<"P "; }
                        else {
                            cout<<"  ";
                        }
            }

            cout<<endl;
            if(i!=10)  /*This has been put here since board[11] not exist and it makes program to crash*/
            {
                if(board[i+1][5]!=cellWall)  /*Not to exceed the row number the current board has*/
                {
                    cout<<row;
                    row++;
                }
            }
        }
    }
    /*--------------------------------------------------------------------------------------------*/

    void PegSolitaire::createBoards(const int chosenBoard)
    {

        switch(chosenBoard)
        {

            case 1: /*CREATING BOARD 1*/
                /*Filling necessary places with pegs*/
                for(int i=3; i<6; i++)
                {
                    board[1][i]=cellPeg;
                }
                for(int i=2; i<7; i++)
                {
                    board[2][i]=cellPeg;
                }
                for(int i=1; i<8; i++)
                {
                    board[3][i]=cellPeg;
                }
                for(int i=1; i<8; i++)
                {
                    board[4][i]=cellPeg;
                }
                for(int i=1; i<8; i++)
                {
                    board[5][i]=cellPeg;
                }
                for(int i=2; i<7; i++)
                {
                    board[6][i]=cellPeg;
                }
                for(int i=3; i<6; i++)
                {
                    board[7][i]=cellPeg;
                }
                /*Marking blank spot*/
                board[3][4]=cellBlank;
                break;



            case 2: /*CREATING BOARD 2*/
                for(int i=4; i<7; i++)
                {
                    board[1][i]=cellPeg;
                }
                for(int i=4; i<7; i++)
                {
                    board[2][i]=cellPeg;
                }
                for(int i=4; i<7; i++)
                {
                    board[3][i]=cellPeg;
                }
                for(int i=1; i<10; i++)
                {
                    board[4][i]=cellPeg;
                }
                for(int i=1; i<10; i++)
                {
                    board[5][i]=cellPeg;
                }
                for(int i=1; i<10; i++)
                {
                    board[6][i]=cellPeg;
                }
                for(int i=4; i<7; i++)
                {
                    board[7][i]=cellPeg;
                }
                for(int i=4; i<7; i++)
                {
                    board[8][i]=cellPeg;
                }
                for(int i=4; i<7; i++)
                {
                    board[9][i]=cellPeg;
                }
                /*Marking blank spot*/
                board[5][5]=cellBlank;
                break;

            case 3: /*CREATING BOARD 3*/
                for(int i=3; i<6; i++)
                {
                    board[1][i]=cellPeg;
                }
                for(int i=3; i<6; i++)
                {
                    board[2][i]=cellPeg;
                }
                for(int i=3; i<6; i++)
                {
                    board[3][i]=cellPeg;
                }
                for(int i=1; i<9; i++)
                {
                    board[4][i]=cellPeg;
                }
                for(int i=1; i<9; i++)
                {
                    board[5][i]=cellPeg;
                }
                for(int i=1; i<9; i++)
                {
                    board[6][i]=cellPeg;
                }
                for(int i=3; i<6; i++)
                {
                    board[7][i]=cellPeg;
                }
                for(int i=3; i<6; i++)
                {
                    board[8][i]=cellPeg;
                }
                /*Marking blank spot*/
                board[5][4]=cellBlank;
                break;

            case 4: /*CREATING BOARD 4*/
                for(int i=3; i<6; i++)
                {
                    board[1][i]=cellPeg;
                }
                for(int i=3; i<6; i++)
                {
                    board[2][i]=cellPeg;
                }
                for(int i=1; i<8; i++)
                {
                    board[3][i]=cellPeg;
                }
                for(int i=1; i<8; i++)
                {
                    board[4][i]=cellPeg;
                }
                for(int i=1; i<8; i++)
                {
                    board[5][i]=cellPeg;
                }
                for(int i=3; i<6; i++)
                {
                    board[6][i]=cellPeg;
                }
                for(int i=3; i<6; i++)
                {
                    board[7][i]=cellPeg;
                }
                /*Marking blank spot*/
                board[4][4]=cellBlank;
                break;

            case 5: /*CREATING BOARD 5*/
                for(int i=5; i<6; i++)
                {
                    board[1][i]=cellPeg;
                }
                for(int i=4; i<7; i++)
                {
                    board[2][i]=cellPeg;
                }
                for(int i=3; i<8; i++)
                {
                    board[3][i]=cellPeg;
                }
                for(int i=2; i<9; i++)
                {
                    board[4][i]=cellPeg;
                }
                for(int i=1; i<10; i++)
                {
                    board[5][i]=cellPeg;
                }
                for(int i=2; i<9; i++)
                {
                    board[6][i]=cellPeg;
                }
                for(int i=3; i<8; i++)
                {
                    board[7][i]=cellPeg;
                }
                for(int i=4; i<7; i++)
                {
                    board[8][i]=cellPeg;
                }
                for(int i=5; i<6; i++)
                {
                    board[9][i]=cellPeg;
                }
                /*Marking blank spot*/
                board[5][5]=cellBlank;
                break;

            /*CREATING BOARD 6*/
            /*Since it is hard to fill it with for loops it has filled by hand*/
            case 6:
                board[1][5]=cellBlank;
                board[2][4]=cellPeg;
                board[2][6]=cellPeg;
                board[3][3]=cellPeg;
                board[3][5]=cellPeg;
                board[3][7]=cellPeg;
                board[4][2]=cellPeg;
                board[4][4]=cellPeg;
                board[4][6]=cellPeg;
                board[4][8]=cellPeg;
                board[5][1]=cellPeg;
                board[5][3]=cellPeg;
                board[5][5]=cellPeg;
                board[5][7]=cellPeg;
                board[5][9]=cellPeg;
                board[2][5]=cellBlank2;
                board[4][5]=cellBlank2;
                break;
        }

    }
    /*--------------------------------------------------------------------------------------------*/

    int PegSolitaire::checkSolved()
    {
        int numofPeg=0;
        for(int i=0; i<11; i++)
        {
            for(int j=0; j<11; j++)
            {
                if(board[i][j]==cellPeg) {
                    numofPeg++;
                }
            }
        }

        return numofPeg; /*This can also be used as a sount for peg.*/
    }

    /*--------------------------------------------------------------------------------------------*/

    void PegSolitaire::resetBoard()
    {

        for(int i=0; i<11; i++)
        {
            for(int j=0; j<11; j++) {
                board[i][j]=cellWall;
            }
        }

    }

    /*--------------------------------------------------------------------------------------------*/

    int PegSolitaire::moveRIGHT(const int row, const int column,const int ai)
    {


        if(column==9) {
            return 0;    /*Since it is impossible to make a move from this coordinate it just ends the function*/
        }
        /*1-Checking if the current place has "peg" 2-Checking the place to go if its free 3-Checking the neighbor location*/
        if(board[row][column]==cellPeg&& board[row][column+2]==cellBlank && board[row][column+1]==cellPeg)
        {
            /*Doing the necessary swaps*/
            board[row][column]=cellBlank;
            board[row][column+1]=cellBlank;
            board[row][column+2]=cellPeg;
            return 1;
        }
        /*Printing error message if player has done the move*/
        else
            if(ai==1) {
                cerr<<endl<<"[This move can not happen. Please enter valid location and move.]"<<endl;
            }
        return 0;

    }

    /*--------------------------------------------------------------------------------------------*/

    int PegSolitaire::moveDOWN(const int row, int column,const int ai)
    {

        if(row==9) {
            return 0;    /*Since it is impossible to make a move from this coordinate it just ends the function*/
        }

        /*1-Checking if the current place has "peg" 2-Checking the place to go if its free 3-Checking the neighbor location*/
        if(board[row][column]==cellPeg&& board[row+2][column]==cellBlank && board[row+1][column]==cellPeg)
        {
            /*Doing the necessary swaps*/
            board[row][column]=cellBlank;
            board[row+1][column]=cellBlank;
            board[row+2][column]=cellPeg;
            return 1;
        }
        /*Printing error message if player has done the move*/
        else
            if(ai==1) {
                cerr<<endl<<"[This move can not happen. Please enter valid location and move.]"<<endl;
            }
        return 0;
    }

    /*--------------------------------------------------------------------------------------------*/

    int PegSolitaire::moveUP(const int row, const int column,const int ai)
    {

        if(row==1) {
            return 0;    /*Since it is impossible to make a move from this coordinate it just ends the function*/
        }

        /*1-Checking if the current place has "peg" 2-Checking the place to go if its free 3-Checking the neighbor location*/
        if(board[row][column]==cellPeg&& board[row-2][column]==cellBlank && board[row-1][column]==cellPeg)
        {
            /*Doing the necessary swaps*/
            board[row][column]=cellBlank;
            board[row-1][column]=cellBlank;
            board[row-2][column]=cellPeg;
            return 1;
        }
        /*Printing error message if player has done the move*/
        else
            if(ai==1) {
                cerr<<endl<<"[This move can not happen. Please enter valid location and move.]"<<endl;
            }
        return 0;
    }

    /*--------------------------------------------------------------------------------------------*/

    int PegSolitaire::moveLEFT(const int row, const int column,const int ai)
    {


        if(column==1) {
            return 0;    /*Since it is impossible to make a move from this coordinate it just ends the function*/
        }
        /*1-Checking if the current place has "peg" 2-Checking the place to go if its free 3-Checking the neighbor location*/
        if(board[row][column]==cellPeg&& board[row][column-2]==cellBlank && board[row][column-1]==cellPeg)
        {
            /*Doing the necessary swaps*/
            board[row][column]=cellBlank;
            board[row][column-1]=cellBlank;
            board[row][column-2]=cellPeg;
            return 1;
        }
        /*Printing error message if player has done the move*/
        else
            if(ai==1) {
                cerr<<endl<<"[This move can not happen. Please enter valid location and move.]"<<endl;
            }
        return 0;
    }

    /*--------------------------------------------------------------------------------------------*/

    int PegSolitaire::board6LEFT(const int row, const int column, const int ai)
    {


        if(column==3) {
            return 0;    /*Since it is impossible to make a move from this coordinate it just ends the function*/
        }
        /*1-Checking if the current place has "cellPeg" 2-Checking the place to go if its free 3-Checking the neighbor location*/
        if(board[row][column]==cellPeg && board[row][column-4]==cellBlank && board[row][column-2]==cellPeg)
        {
            /*Doing the necessary swaps*/
            board[row][column]=cellBlank;
            board[row][column-2]=cellBlank;
            board[row][column-4]=cellPeg;
            return 1;
        }
        /*Printing error message if player has done the move*/
        else
            if(ai==1) {
                cerr<<endl<<"[This move can not happen. Please enter valid location and move.]"<<endl;
            }
        return 0;


    }

    /*--------------------------------------------------------------------------------------------*/

    int PegSolitaire::board6RIGHT(const int row, const int column, const int ai)
    {

        if(column==7) {
            return 0;    /*Since it is impossible to make a move from this coordinate it just ends the function*/
        }
        /*1-Checking if the current place has "cellPeg" 2-Checking the place to go if its free 3-Checking the neighbor location*/
        if(board[row][column]==cellPeg&& board[row][column+4]==cellBlank && board[row][column+2]==cellPeg)
        {
            /*Doing the necessary swaps*/
            board[row][column]=cellBlank;
            board[row][column+2]=cellBlank;
            board[row][column+4]=cellPeg;
            return 1;
        }
        /*Printing error message if player has done the move*/
        else
            if(ai==1) {
                cerr<<endl<<"[This move can not happen. Please enter valid location and move.]"<<endl;
            }
        return 0;
    }

    /*--------------------------------------------------------------------------------------------*/

    int PegSolitaire::board6UPRIGHT(const int row, const int column, const int ai)
    {

        /*Since it is impossible to make a move from this coordinate it just ends the function*/
        if(column==9) {
            return 0;
        }
        if(row==1) {
            return 0;
        }

        /*1-Checking if the current place has "cellPeg" 2-Checking the place to go if its free 3-Checking the neighbor location*/
        if(board[row][column]==cellPeg&& board[row-2][column+2]==cellBlank && board[row-1][column+1]==cellPeg)
        {
            /*Doing the necessary swaps*/
            board[row][column]=cellBlank;
            board[row-1][column+1]=cellBlank;
            board[row-2][column+2]=cellPeg;
            return 1;
        }
        else {
            return 0;
        }


    }

    /*--------------------------------------------------------------------------------------------*/

    int PegSolitaire::board6UPLEFT(int row, int column, int ai)
    {

        /*Since it is impossible to make a move from this coordinate it just ends the function*/
        if(column==1) {
            return 0;
        }
        if(row==1) {
            return 0;
        }

        /*1-Checking if the current place has "cellPeg" 2-Checking the place to go if its free 3-Checking the neighbor location*/
        if(board[row][column]==cellPeg&& board[row-2][column-2]==cellBlank && board[row-1][column-1]==cellPeg)
        {
            /*Doing the necessary swaps*/
            board[row][column]=cellBlank;
            board[row-1][column-1]=cellBlank;
            board[row-2][column-2]=cellPeg;
            return 1;
        }
        else {
            return 0;
        }
    }

    /*--------------------------------------------------------------------------------------------*/

    int PegSolitaire::board6DOWNLEFT(int row, int column, int ai)
    {

        /*Since it is impossible to make a move from this coordinate it just ends the function*/
        if(column==1) {
            return 0;
        }
        if(row==9) {
            return 0;
        }

        if(board[row][column]==cellPeg&& board[row+2][column-2]==cellBlank && board[row+1][column-1]==cellPeg)
        {
            /*Doing the necessary swaps*/
            board[row][column]=cellBlank;
            board[row+1][column-1]=cellBlank;
            board[row+2][column-2]=cellPeg;
            return 1;
        }
        else {
            return 0;
        }
    }

    /*--------------------------------------------------------------------------------------------*/

    int PegSolitaire::board6DOWNRIGHT(int row, int column, int ai)
    {

        if(column==9) {
            return 0;
        }
        if(row==9) {
            return 0;
        }
        /*1-Checking if the current place has "cellPeg" 2-Checking the place to go if its free 3-Checking the neighbor location*/
        if(board[row][column]==cellPeg&& board[row+2][column+2]==cellBlank && board[row+1][column+1]==cellPeg)
        {
            /*Doing the necessary swaps*/
            board[row][column]=cellBlank;
            board[row+1][column+1]=cellBlank;
            board[row+2][column+2]=cellPeg;
            return 1;
        }
        else {
            return 0;
        }
    }

    /*--------------------------------------------------------------------------------------------*/

    void PegSolitaire::saveBoard(  string &inputFile,int chosenBoard,int ai,int numofMoves,bool boardSix)
    {

        string whichFile; /*String variable to hold file name*/

        inputFile.erase(0,5); /*Erasing "SAVE" part*/

        /*Erasing .TXT part*/
        inputFile.pop_back();
        inputFile.pop_back();
        inputFile.pop_back();
        inputFile.pop_back();

        /*Replacing with the file name*/
        whichFile.replace(whichFile.begin(),whichFile.end(),inputFile);

        /*Opening file*/
        fstream saveFile;
        saveFile.open((whichFile + ".txt"),ios::out );


        /*Saving the necessary information to text file*/

        /*Reaching every-single elements in the vector to print*/
        for(int i=0; i<11; i++)
        {
            for(int j=0; j<11; j++)
            {
                if(board[i][j]==cellWall)
                { saveFile<<"0 "; }
                else
                    if(board[i][j]== cellBlank)
                    { saveFile<<". "; }
                    else
                        if(board[i][j]== cellPeg)
                        { saveFile<<"P "; }
                        else {
                            saveFile<<"  ";
                        }
            }

            saveFile<<endl;


        }

        /*Saving information values to file*/
        saveFile<<chosenBoard<<ai<<boardSix<<numofMoves;
        saveFile.close(); /*Closing the file*/


        inputFile='\0'; /*Resetting the string*/
        cout<<endl<<"[The game has been saved successfully.]"<<endl<<endl;
    }


    int PegSolitaire::loadBoard(  string &inputFile, int &chosenBoard, int &ai, int &numofMoves, bool &boardSix)
    {
        /*Opening file*/
        string whichFile; /*String variable to hold file name*/
        char ch;

        if(inputFile.size()<9) {
            return 0;
        }

        inputFile.erase(0,5);/*Erasing "SAVE" part*/

        /*Erasing .TXT part*/
        inputFile.pop_back();
        inputFile.pop_back();
        inputFile.pop_back();
        inputFile.pop_back();

        /*Replacing with the file name*/
        whichFile.replace(whichFile.begin(),whichFile.end(),inputFile);

        fstream loadFile;


        /*THIS PARTS GETs THE BOOLEAN VALUE WHICH INDICATES BOARD 6 IS ACTIVE OR NOT*/
        int k=0; /*Counter*/
        loadFile.open((whichFile + ".txt"),ios::in);
        if(!loadFile.is_open()) {
            return 0;    /*Returns 0 if file can not open.*/
        }
        while(!loadFile.eof())
        {
            k++;
            loadFile >> ch;
        }

        if(k==124) {
            boardSix=1;
        }
        else {
            boardSix=0;
        }
        loadFile.close();



        /*Openin the file again*/

        loadFile.open((whichFile + ".txt"),ios::in);

        if(!loadFile.is_open()) {
            return 0;    /*Returns 0 if file can not open.*/
        }

        /*Checking every element and appointing them to appropriate places in vector*/
        if(!boardSix)
        {
            for(int i=0; i<11; i++)
            {

                for(int j=0; j<11; j++)
                {
                    loadFile >> ch;
                    switch(ch)
                    {
                        case '0':
                            board[i][j]=cellWall;
                            break;
                        case 'P':
                            board[i][j]= cellPeg;
                            break;
                        case '.':
                            board[i][j]= cellBlank;
                            break;
                    }
                }
            }
        }

        /*This is where the things get worse*/
        /*If board 6 is loading, programs do this by manually checking every spot.*/
        if(boardSix)
        {

            for (int i=0; i<17; i++) {
                loadFile >> ch;
            }
            if(ch=='P') {
                board[1][5]= cellPeg;
            }
            if(ch=='.') {
                board[1][5]= cellBlank;
            }

            for (int i=0; i<10; i++) {
                loadFile >> ch;
            }
            if(ch=='P') {
                board[2][4]= cellPeg;
            }
            if(ch=='.') {
                board[2][4]= cellBlank;
            }

            loadFile >> ch;
            if(ch=='P') {
                board[2][6]= cellPeg;
            }
            if(ch=='.') {
                board[2][6]= cellBlank;
            }

            for (int i=0; i<8; i++) {
                loadFile >> ch;
            }
            if(ch=='P') {
                board[3][3]= cellPeg;
            }
            if(ch=='.') {
                board[3][3]= cellBlank;
            }

            loadFile >> ch;
            loadFile >> ch;
            if(ch=='P') {
                board[3][5]= cellPeg;
            }
            if(ch=='.') {
                board[3][5]= cellBlank;
            }

            loadFile >> ch;
            loadFile >> ch;
            if(ch=='P') {
                board[3][7]= cellPeg;
            }
            if(ch=='.') {
                board[3][7]= cellBlank;
            }

            for (int i=0; i<6; i++) {
                loadFile >> ch;
            }
            if(ch=='P') {
                board[4][2]= cellPeg;
            }
            if(ch=='.') {
                board[4][2]= cellBlank;
            }

            loadFile >> ch;
            loadFile >> ch;
            if(ch=='P') {
                board[4][4]= cellPeg;
            }
            if(ch=='.') {
                board[4][4]= cellBlank;
            }

            loadFile >> ch;
            if(ch=='P') {
                board[4][6]= cellPeg;
            }
            if(ch=='.') {
                board[4][6]= cellBlank;
            }

            loadFile >> ch;
            loadFile >> ch;
            if(ch=='P') {
                board[4][8]= cellPeg;
            }
            if(ch=='.') {
                board[4][8]= cellBlank;
            }

            loadFile >> ch;
            loadFile >> ch;
            loadFile >> ch;
            loadFile >> ch;
            if(ch=='P') {
                board[5][1]= cellPeg;
            }
            if(ch=='.') {
                board[5][1]= cellBlank;
            }

            loadFile >> ch;
            loadFile >> ch;
            if(ch=='P') {
                board[5][3]= cellPeg;
            }
            if(ch=='.') {
                board[5][3]= cellBlank;
            }

            loadFile >> ch;
            loadFile >> ch;
            if(ch=='P') {
                board[5][5]= cellPeg;
            }
            if(ch=='.') {
                board[5][5]= cellBlank;
            }

            loadFile >> ch;
            loadFile >> ch;
            if(ch=='P') {
                board[5][7]= cellPeg;
            }
            if(ch=='.') {
                board[5][7]= cellBlank;
            }

            loadFile >> ch;
            loadFile >> ch;
            if(ch=='P') {
                board[5][9]= cellPeg;
            }
            if(ch=='.') {
                board[5][9]= cellBlank;
            }

            for (int i=0; i<56; i++) {
                loadFile >> ch;
            }

            board[2][5]= cellBlank2;
            board[4][5]= cellBlank2;


        }

        /*Reading relevant information*/
        loadFile >> ch;
        chosenBoard=ch-'0';
        loadFile >> ch;
        ai=ch-'0';
        loadFile >> ch;

        /*Reading the number of moves made*/
        string temp;
        while(!loadFile.eof())
        {

            loadFile >> ch;
            temp+=ch;
        }

        temp.pop_back();
        numofMoves=stoi(temp);

        loadFile.close(); /*Closing the file.*/

        return 1;
    }
