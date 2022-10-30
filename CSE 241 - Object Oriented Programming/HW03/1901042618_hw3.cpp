#include<iostream>
#include<vector>
#include<string>
#include<ctime>
#include<fstream>
#include<conio.h>
using namespace std;

enum cellState
{
	empty = 0,
	peg ,
	wall 
};

class PegSolitaire
{
public:

	PegSolitaire();													//for loading boards
    PegSolitaire(char opt_boardType , char opt_gameType);
    PegSolitaire(char opt_boardType , char opt_gameType ,  int x);

    class Cell
    {
    public:
        //Cell();  //constructors
        
        void setState(cellState cellState) {state = cellState;}  
        void setPos(char ch) {pos = ch;}                            //setters
        void setRowNumber(int num) {rowNumber = num;}

        cellState getState() {return state;}  
        char getPos() {return pos;}                                 //getters
        int getRowNumber() {return rowNumber;}

        bool operator== (PegSolitaire :: Cell A);                   //operator overload
        
    private:
    char pos;
    int rowNumber;
    cellState state; 

    };

	//const vector< vector<Cell> > &getVec() {return vec;}
	const char getboardType() {return boardType;}					//returns object's boardType
    void vectorSetter();											//sets the vector for beginning
    void vectorPrinter(int specialSituation_6 = 0);					//prints current state of board

	int human();													//only user can play
	int humanComputer();											//user can play with computer help

	void play();													
	int moveInput();
	void play(string move);
	void playGame(char boardType);

	const int is_anyMovePossible();									//controls whether any movement possible on board
	const int is_anyMovePossible_6();								//controls whether any movement possible on board sixth

	const int load(string str_txt);									//loads the specified file of board
	const int save(string str_txt);									//saves the current board and related informations 

	const bool compareObjects(PegSolitaire &other);
	static int pegsOfAllBoards(const vector<PegSolitaire> &activeSessions);

	const int getNumberOfPegs();								//returns how many pegs left on board
	const int getNumberOfEmptyCells();							//returns how many empty cell left on board
	const int getNumberOfPegsTaken();							//returns how many pegs taken out from board


private:
vector< vector<Cell> > vec;
int numberOfMoves = 0;
char gameType;
char boardType;

PegSolitaire :: Cell emptyCell;
PegSolitaire :: Cell pegCell;
PegSolitaire :: Cell wallCell;

const int is_moveValid(string move_cmp , int rondomRow , int  rondomColumn);
const int is_moveValid_6(string move_cmp , int rondomRow , int  rondomColumn);

};

//--------------------------------------------
int main()
{
    char opt_boardType;
	char opt_gameType;
	string str_opt_boardType; //options for board type takes string because of validity of the user inputs  
	string str_opt_gameType;  //options for game type takes string because of validity of the user inputs

    string str_txt;
	string lors; //is input's head is load or save 
	int flagLoad = 0 , x = 0;

	int ans = 1;
	vector<PegSolitaire> boardVec;
	vector<PegSolitaire> activeSessions;
	int selectSes = 0;
	int allPegs = 0;

    cout << "Welcome to the game of Peg Solitaire.." << endl << endl
		 << "At any step You can LOAD the game" << endl
		 << "and any step after the selection of the game type you can SAVE the game too.." << endl << endl;

	do
	{
		do
		{	cout << endl << endl;
			cout << "You can exit the program by typing 'exit'." << endl
				 << "You can continue on active sessions by typing 'cont'." << endl;
			cout << "Please select the Board type from 1 to 6: ";
			getline(cin >> std::ws, str_opt_boardType);    				//ws consumes whitespace from an input stream

			lors = str_opt_boardType.substr(0,4);
			if(lors == "exit") {exit(1);}
			else if(lors == "cont")
			{
				if(activeSessions.size() == 0){
					cout << "You don't have any active sessions" << endl;
				}
				else{
					cout << "You have " << activeSessions.size() << " options to select." << " Choose one of them: ";
					cin >> selectSes;
					if(selectSes < 1 || selectSes > activeSessions.size()){cout << "Choosen session is not in active sessions Try again later..";continue;}
					else{
						boardVec.push_back(activeSessions[selectSes-1]);
						flagLoad = 1;
					}
				}
			}
			else if (lors == "LOAD"){													//LOADING 

				flagLoad = 0;
				str_txt = str_opt_boardType.substr(5,str_opt_boardType.length()-5);
				PegSolitaire temp_load;
				flagLoad = temp_load.load( str_txt );
				boardVec.push_back(temp_load);
			}
			if(flagLoad == 1)break;

			if ((str_opt_boardType.size() != 1) || str_opt_boardType[0] < '1' || str_opt_boardType[0] > '6')
				cerr << endl << "TRY AGAIN.." << endl;

		} while ((str_opt_boardType.size() != 1) || str_opt_boardType[0] < '1' || str_opt_boardType[0] > '6');

    	do
		{	
			cout << endl << endl;
			cout << "1-> Human player game" << endl
				 << "2-> Human player + Computer help game" << endl
				 << "3-> Computer game" << endl
				 << "Please select the Game type: ";
			cin >> str_opt_gameType;

			if ((str_opt_gameType.size() != 1) || str_opt_gameType[0] < '1' || str_opt_gameType[0] > '3')
				cerr << endl << "TRY AGAIN.." << endl;

		} while ((str_opt_gameType.size() != 1) || str_opt_gameType[0] < '1' || str_opt_gameType[0] > '3');

		opt_boardType = str_opt_boardType[0];
		opt_gameType = str_opt_gameType[0];

		if (flagLoad == 1)
		{
			PegSolitaire temp = boardVec[0];
			if(temp.getboardType() == '6') { opt_boardType = '6';temp.vectorPrinter(6);}
			else{opt_boardType = '1';temp.vectorPrinter();}
		}
		else
		{
    		if(opt_boardType != '6'){ 
    	    	PegSolitaire game(opt_boardType , opt_gameType);
				cout << endl << endl;
				game.vectorPrinter();
				boardVec.push_back(game);

   		 	}    
    		else{
    	    	PegSolitaire game(opt_boardType , opt_gameType ,  6);
				cout << endl << endl;
				game.vectorPrinter(6);
				boardVec.push_back(game); 
    		} 
		}

		PegSolitaire temp = boardVec[0];
		switch(opt_gameType)
		{
			case '1':
				ans = temp.human();
				break;

			case '2':
				ans = temp.humanComputer();
				break;

			case '3':
				temp.playGame(opt_boardType);
				break;				
		}

		if (ans == 0){
			activeSessions.push_back(temp);
			boardVec.pop_back();
		}

		ans = 1;
		flagLoad = 0 , x = 0;
		allPegs = 0;

		if (activeSessions.size() == 2)			//if active sessions number is 2 this if automatically works
		{
			if(activeSessions[0].compareObjects(activeSessions[1])){
				cout << endl << "First sessions has more pegs.." << endl;	
			}
			else{cout << endl << "Second sessions has more pegs.." << endl;}
		}

		allPegs = PegSolitaire :: pegsOfAllBoards(activeSessions);

		cout << endl << "Total number of pegs in all Active Games is : "<< allPegs << endl; 

	} while (1); 


	

    getch();
    return 0;
}
//--------------------------------------------

int PegSolitaire :: human()
{
	int ans = 1;

	if(boardType == '6')
	{
		do
		{
			ans = moveInput();
			if(ans == 0){return 0;}

		}while (is_anyMovePossible_6() != 0);
	}
	else
	{
		do
		{
			ans = moveInput();
			if(ans == 0){return 0;}

		}while (is_anyMovePossible() != 0);
	}
	return 1;
}

int PegSolitaire :: humanComputer()
{
	string str_opt_option;

	string str_txt;
	string lors; //is input's head is load or save 
	int flagSave = 0;

	if(boardType == '6')
	{
		do
		{
		    do
			{	
				cout << endl << endl
					 << "If you want to save the game on active sessions press 0.." << endl
					 << "1-> Human" << endl
			 		 << "2-> Computer" << endl
			 		 << "Who going to play? : ";
				getline(cin >> std::ws, str_opt_option);    //ws consumes whitespace from an input stream

				lors = str_opt_option.substr(0,4);
				if (lors == "SAVE"){							//if user want to SAVE the game on txt this part works
					str_txt = str_opt_option.substr(5,str_opt_option.length()-5);
					flagSave = save( str_txt );
					if(flagSave == 1){
						cout << endl << "Your game has SAVED , Program being terminated.." << endl;
						cin.get();
						exit(1);
					} 
				}				

				if ((str_opt_option.size() != 1) || str_opt_option[0] < '0' || str_opt_option[0] > '2')
				cerr << endl << "TRY AGAIN.." << endl;

			} while ((str_opt_option.size() != 1) || str_opt_option[0] < '0' || str_opt_option[0] > '2');

			if (str_opt_option[0] == '0') {return 0;}
			else if (str_opt_option[0] == '1') {moveInput();}
			else if (str_opt_option[0] == '2') {play();}
				

		}while (is_anyMovePossible_6() != 0);
	}
	else{
		do
		{
		    do
			{
				cout << endl << endl
					 << "If you want to save the game on active sessions press 0.." << endl
					 << "1-> Human" << endl
			 		 << "2-> Computer" << endl
			 		 << "Who going to play? : ";
				getline(cin >> std::ws, str_opt_option);    //ws consumes whitespace from an input stream

				lors = str_opt_option.substr(0,4);
				if (lors == "SAVE"){							//if user want to SAVE the game on txt this part works
					str_txt = str_opt_option.substr(5,str_opt_option.length()-5);
					flagSave = save( str_txt );
					if(flagSave == 1){
						cout << endl << "Your game has SAVED , Program being terminated.." << endl;
						cin.get();
						exit(1);
					} 
				}				

				if ((str_opt_option.size() != 1) || str_opt_option[0] < '0' || str_opt_option[0] > '2')
				cerr << endl << "TRY AGAIN.." << endl;

			} while ((str_opt_option.size() != 1) || str_opt_option[0] < '0' || str_opt_option[0] > '2');

			if (str_opt_option[0] == '0') {return 0;}
			else if (str_opt_option[0] == '1') {moveInput();}
			else if (str_opt_option[0] == '2') {play();}

		}while (is_anyMovePossible() != 0);
	}
	return 1;	
}

PegSolitaire :: PegSolitaire()
{
    emptyCell.setState(empty);
    pegCell.setState(peg);
    wallCell.setState(wall);

	vectorSetter();

	if(vec[4][5] == wallCell)
	{
		boardType = '6';
	}
}	

PegSolitaire :: PegSolitaire(char opt_boardType , char opt_gameType)
{
    int i , j , random;
    emptyCell.setState(empty);
    pegCell.setState(peg);
    wallCell.setState(wall);

	boardType = opt_boardType;
	gameType = opt_gameType;

    vectorSetter();

	switch(boardType)
	{
		case '1':
			srand (time(NULL)); //use of random number generator
	        random = rand() % 2 + 1;

            vec.resize(9);
	        for (i = 0; i < 9; ++i){
    	        vec[i].resize(9);
	        }
            
        	for (i = 0; i <= 8; ++i)
	        {
		        if (i == 1 || i == 7)
			        for (j = 3; j <= 5; ++j)
				        vec[i][j] = pegCell;

		        else if (i == 2 || i == 6)
			        for (j = 2; j <= 6; ++j)
				        vec[i][j] = pegCell;
		
		        else if (i==3 || i==4 || i==5)
			        for (j = 1; j <= 7; ++j)
				        vec[i][j] = pegCell;
	        }

	        if (random == 1){
		        vec[3][4] = emptyCell;
	        }
	        else{
		        vec[5][4] = emptyCell;			
	        }    
			break;

		case '2':

            vec.resize(11);
	        for (i = 0; i < 11; ++i){
    	        vec[i].resize(11);
	        }
			
        	for (i = 0; i <= 10; ++i)
	        {
		        if (i == 1 || i == 2 || i == 3 || i == 7 || i == 8 || i == 9)
			        for (j = 4; j <= 6; ++j)
				        vec[i][j] = pegCell;
		
		        else if (i==4 || i==5 || i==6)
			        for (j = 1; j <= 9; ++j)
				        vec[i][j] = pegCell;
	        }
	        vec[5][5] = emptyCell;    

			break;

		case '3':

            vec.resize(10);
	        for (i = 0; i < 10; ++i){
    	        vec[i].resize(10);
	        }

			for (i = 0; i <= 10; ++i)
	        {
		        if (i == 1 || i == 2 || i == 3 || i == 7 || i == 8)
			        for (j = 3; j <= 5; ++j)
				        vec[i][j] = pegCell;
		
		        else if (i==4 || i==5 || i==6)
			        for (j = 1; j <= 8; ++j)
				        vec[i][j] = pegCell;
	        }
	        vec[5][4] = emptyCell;
			break;

		case '4':

            vec.resize(9);
	        for (i = 0; i < 9; ++i){
    	        vec[i].resize(9);
	        }        
			for (i = 0; i <= 8; ++i)
	        {
		        if (i == 1 || i == 2 || i == 6 || i == 7)
			        for (j = 3; j <= 5; ++j)
				        vec[i][j] = pegCell;
		
		        else if (i==3 || i==4 || i==5)
			        for (j = 1; j <= 7; ++j)
				        vec[i][j] = pegCell;
	        }
	        vec[4][4] = emptyCell;
			break;

		case '5':

            vec.resize(11);
	        for (i = 0; i < 11; ++i){
    	        vec[i].resize(11);
	        } 

        	for (i = 0; i <= 10; ++i)
	        {
		        if (i == 1 || i == 9)
			        vec[i][5] = pegCell;

		        else if (i == 2 || i == 8)
			        for (j = 4; j <= 6; ++j)
				        vec[i][j] = pegCell;

		        else if (i == 3 || i == 7)
			        for (j = 3; j <= 7; ++j)
				        vec[i][j] = pegCell;

		        else if (i == 4 || i == 6)
			        for (j = 2; j <= 8; ++j)
				        vec[i][j] = pegCell;						
		
		        else if (i==5)
			        for (j = 1; j <= 9; ++j)
				        vec[i][j] = pegCell;
	        }
	        vec[5][5] = emptyCell;
			break;				
	}    
}

PegSolitaire :: PegSolitaire(char opt_boardType , char opt_gameType ,  int x)
{
	int i , j ;

    vectorSetter();

	boardType = opt_boardType;
	gameType = opt_gameType;

    vec.resize(9);
	for (i = 0; i < 9; ++i){
    	vec[i].resize(13);
	}    

	vec[2][6] = emptyCell;

	for (i = 3 , j = 5; j <= 7; ++j){
		if(j % 2 == 1) vec[i][j] = pegCell;
		else vec[i][j] = wallCell;
	}
	for (i = 4 , j = 4; j <= 8; ++j){
		if(j % 2 == 0) vec[i][j] = pegCell;
		else vec[i][j] = wallCell;
	}
	for (i = 5 , j = 3; j <= 9; ++j){
		if(j % 2 == 1) vec[i][j] = pegCell;
		else vec[i][j] = wallCell;						
	}
	for (i = 6 , j = 2; j <= 10; ++j){
		if(j % 2 == 0) vec[i][j] = pegCell;
		else vec[i][j] = wallCell;
	}    
}

void PegSolitaire :: vectorSetter()
{
    //vec(6, vector<Cell>(6, Cell(wall)));
    int i = 0 , j = 0;

    vec.resize(13);
	for (i = 0; i < 13; ++i){
    	vec[i].resize(13);
	}

    for (i = 0; i < vec.size(); i++)
    {
        for (j = 0; j < vec[0].size(); j++)
        {
            vec[i][j] = wallCell;
        } 
    }
}

void PegSolitaire :: vectorPrinter(int specialSituation_6)
{
    int i = 0 , j = 0;
	vector<char>abc = {'a' , 'b' , 'c' , 'd' , 'e' , 'f' , 'g' , 'h' , 'j'};                     //for printing the horizontal guide line

    for (i = 0; i < vec.size(); i++)
    {
        for (j = 0; j < vec[0].size(); j++) 
        {
            if (vec[i][j] == emptyCell)
				cout << ". ";

			if (vec[i][j] == pegCell)
				cout << "P ";

			if (vec[i][j] == wallCell){
				if (specialSituation_6 != 0){

					if ((i == 1) && (2<=j && j<(vec[0].size()-2))){ 
						cout << abc[j-2] << " ";               //for printing the horizontal guide line on sixth board
					}
					else if ((j == 1) && (2<=i && i<(vec.size()-2))){
						cout << i-1 << " ";                //for printing the vertical guide line on sixth board
					}
					else
						cout << "  ";
				}
				
				else{

					if ((i == 0) && (1<=j && j<=(vec[0].size()-2))){ 
						cout << abc[j-1] << " ";               //for printing the horizontal guide line
					}
					else if ((j == 0) && (1<=i && i<=(vec.size()-2))){
						cout << i << " ";                      //for printing the vertical guide line
					}
					else
						cout << "  ";
				}
			}	
		}

		cout << endl;                 
    }
}

void PegSolitaire :: play()
{
	vector<char>abc = {'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'J'};
	vector<char>num = {'1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9'};
	vector<char>way = {'R' , 'L' , 'U' , 'D'};
	string move_cmp = "AAAA";  //for computer game 
	string next;
	int i , j;
	int rondomRow , rondomColumn , rondomWay;

	string str_txt;
	string lors; //is input's head is load or save 
	int flagLoad = 0 , flagSave = 0;

	//cout << endl << "At any step You can SAVE or LOAD the game.." << endl;

	srand (time(NULL)); //use of random number generator

	if (vec[4][5] == wallCell)										//if board is sixth one
	{
		do
		{
			rondomColumn = rand() % 10 + 2;    //generates a random number for the column number
			move_cmp[0] = abc[rondomColumn-2];
			rondomRow = rand() % 6 + 2;        //generates a random number for the row number
			move_cmp[1] = num[rondomRow-2];
			move_cmp[2] = '-';
			rondomWay = rand() % 4 + 1;        //generates a random number for the way number
			move_cmp[3] = way[rondomWay-1];
			move_cmp[4] = '\0';

		} while (!is_moveValid_6( move_cmp , rondomRow , rondomColumn));

		play(move_cmp);
		cout << "move made: " << move_cmp << endl;			
	}
	else
	{
		do
		{
			rondomColumn = rand() % (vec[0].size()-2) + 1;  //generates a random number for the column number
			move_cmp[0] = abc[rondomColumn-1];
			rondomRow = rand() % (vec.size()-2) + 1;     	//generates a random number for the row number
			move_cmp[1] = num[rondomRow-1];
			move_cmp[2] = '-';
			rondomWay = rand() % 4 + 1;                     //generates a random number for the way number
			move_cmp[3] = way[rondomWay-1];
			move_cmp[4] = '\0';

		} while (!is_moveValid( move_cmp , rondomRow , rondomColumn));

		numberOfMoves++;
		play(move_cmp);
		cout << "move made: " << move_cmp << endl;
		
	}
}

int PegSolitaire :: moveInput()											//user plays th
{
	int i , j , counter = 0;
	
	vector<char>abc = {'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'J'};
	vector<char>num = {'1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9'};
	vector<char>way = {'R' , 'L' , 'U' , 'D'};
	string move = "AAAA";
	string move_cmp = "AAAA";  //for computer game
	string next;

	int rowVariable;      //row number of chosen cell
	int columnVariable;   //column number of chosen cell
	int rondomRow , rondomColumn , rondomWay;

	int is_firstParameter = 0;
	int is_secondParameter = 0;
	int is_thirdParameter = 0;
	int is_fourthParameter = 0;

	string str_txt;
	string lors; //is input's head is load or save 
	int flagLoad = 0 , flagSave = 0;

	//cout << endl << "At any step You can SAVE or LOAD the game.." << endl;

	if (vec[4][5] == wallCell)										//if board is sixth one
	{
		do
		{
			cout << "If you want to save the game on active sessions press 0.." << endl;
			cout << "Make your move: ";						//Left side priority for upward and downward movement
			getline(cin >> std::ws, move);    				//ws consumes whitespace from an input stream

			if(move.size() == 1 && move[0] == '0'){return 0;}

			lors = move.substr(0,4);
			if (lors == "LOAD"){								//LOADING 
				str_txt = move.substr(5,move.length()-5);
				flagLoad = load( str_txt );
			}
			else if (lors == "SAVE"){							//SAVING
				str_txt = move.substr(5,move.length()-5);
				flagSave = save( str_txt );
				if(flagSave == 1){
					cout << endl << "Your game has SAVED , Program being terminated.." << endl;
					cin.get();
					exit(1);
				} 
			}

			else{
				for (i = 0; i < vec[0].size()-4; ++i){
					if (move[0] == abc[i]){
						is_firstParameter = 1;
						columnVariable = i+2;
					}
				}
				for (i = 0; i < 5; ++i){
					if (move[1] == num[i]){
						is_secondParameter = 1;
						rowVariable = i+2;
					}	
				}
				if (move[2] == '-'){is_thirdParameter = 1;}
				
				for (i = 0; i < 4; ++i){
					if (move[3] == way[i])
						is_fourthParameter = 1;
				}

				if ((move.size() > 4) || !is_firstParameter || !is_secondParameter || !is_thirdParameter || !is_fourthParameter || !is_moveValid_6( move , rowVariable , columnVariable )){
					cerr << "Try Again.." << endl;
					is_firstParameter = 0;
					is_secondParameter = 0;
					is_thirdParameter = 0;
					is_fourthParameter = 0;
				}
			}	
		} while ((move.size() > 4) || !is_firstParameter || !is_secondParameter || !is_thirdParameter || !is_fourthParameter || !is_moveValid_6( move , rowVariable , columnVariable ));
		
		play(move);
		numberOfMoves++;
	}
	else
	{
		do{
			cout << "If you want to save the game on active sessions press 0.." << endl;
			cout << "Make your move: ";
			getline(cin >> std::ws, move);    //ws consumes whitespace from an input stream

			if(move.size() == 1 && move[0] == '0'){return 0;}

			lors = move.substr(0,4);
			if (lors == "LOAD"){								//LOADING 
				str_txt = move.substr(5,move.length()-5);
				flagLoad = load ( str_txt );
			}
			else if (lors == "SAVE"){							//SAVING
				str_txt = move.substr(5,move.length()-5);
				flagSave = save( str_txt );
				if(flagSave == 1){
					cout << endl << "Your game has SAVED , Program being terminated.." << endl;
					cin.get();
					exit(1);
				} 
			}	
				
			else{
				for (i = 0; i < vec[0].size()-2; ++i){
					if (move[0] == abc[i]){
						is_firstParameter = 1;
						columnVariable = i+1;
					}
					if (move[1] == num[i]){
						is_secondParameter = 1;
						rowVariable = i+1;
					}	
				}
				if (move[2] == '-'){is_thirdParameter = 1;}
			
				for (i = 0; i < 4; ++i){
					if (move[3] == way[i]){
						is_fourthParameter = 1;
					}
				}

				if ((move.size() > 4) || !is_firstParameter || !is_secondParameter || !is_thirdParameter || !is_fourthParameter || !is_moveValid( move , rowVariable , columnVariable )){
					cerr << "Try Again.." << endl;
					is_firstParameter = 0;
					is_secondParameter = 0;
					is_thirdParameter = 0;
					is_fourthParameter = 0;
				}
			}
		} while ((move.size() > 4) || !is_firstParameter || !is_secondParameter || !is_thirdParameter || !is_fourthParameter || !is_moveValid( move , rowVariable , columnVariable ));

		play(move);
		numberOfMoves++;
	}
	return 1;
}

void PegSolitaire :: play(string move_cmp)
{
	vector<char>abc = {'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'J'};
	vector<char>num = {'1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9'};

	int rowVariable , columnVariable;

	if(vec[4][5] == wallCell)					//if board is sixth board
	{
		for (int i = 0; i < abc.size(); i++)
		{
			if(move_cmp[0] == abc[i]){
				columnVariable = i + 2;
			}
			if(move_cmp[1] == num[i]){
				rowVariable = i + 2;
			}
		}

		switch (move_cmp[3])
		{
			case 'R':
				swap(vec[rowVariable][columnVariable],vec[rowVariable][columnVariable+4]);
				vec[rowVariable][columnVariable+2] = emptyCell;
				vectorPrinter(6);
				break;

			case 'L':
				swap(vec[rowVariable][columnVariable],vec[rowVariable][columnVariable-4]);
				vec[rowVariable][columnVariable-2] = emptyCell;
				vectorPrinter(6);
				break;

			case 'U':
				if (vec[rowVariable-2][columnVariable-2] == emptyCell){									//left side priority for upward movement
					swap(vec[rowVariable][columnVariable],vec[rowVariable-2][columnVariable-2]);
					vec[rowVariable-1][columnVariable-1] = emptyCell;
				}
				else{
					swap(vec[rowVariable][columnVariable],vec[rowVariable-2][columnVariable+2]);
					vec[rowVariable-1][columnVariable+1] = emptyCell;}
				vectorPrinter(6);
				break;

			case 'D':
				if (vec[rowVariable+2][columnVariable-2] == emptyCell){									//left side priority for downward movement
					swap(vec[rowVariable][columnVariable],vec[rowVariable+2][columnVariable-2]);
					vec[rowVariable+1][columnVariable-1] = emptyCell;
				}
				else{
					swap(vec[rowVariable][columnVariable],vec[rowVariable+2][columnVariable+2]);
					vec[rowVariable+1][columnVariable+1] = emptyCell;}
				vectorPrinter(6);
				break;
		}
	}

	else
	{
		for (int i = 0; i < abc.size(); i++)
		{
			if(move_cmp[0] == abc[i]){
				columnVariable = i + 1;
			}
			if(move_cmp[1] == num[i]){
				rowVariable = i + 1;
			}
		}

		switch (move_cmp[3])
		{
			case 'R':
				swap(vec[rowVariable][columnVariable],vec[rowVariable][columnVariable+2]);
				vec[rowVariable][columnVariable+1] = emptyCell;
				vectorPrinter();
				break;

			case 'L':
				swap(vec[rowVariable][columnVariable],vec[rowVariable][columnVariable-2]);
				vec[rowVariable][columnVariable-1] = emptyCell;
				vectorPrinter();
				break;

			case 'U':
				swap(vec[rowVariable][columnVariable],vec[rowVariable-2][columnVariable]);
				vec[rowVariable-1][columnVariable] = emptyCell;
				vectorPrinter();
				break;

			case 'D':
				swap(vec[rowVariable][columnVariable],vec[rowVariable+2][columnVariable]);
				vec[rowVariable+1][columnVariable] = emptyCell;
				vectorPrinter();
				break;
		}
	}
}

void PegSolitaire :: playGame(char boardType)
{

	if(boardType == '6')
	{
		do
		{
			play();

		}while (is_anyMovePossible_6() != 0);
	}
	else
	{
		do
		{
			play();

		}while (is_anyMovePossible() != 0);
	}	
}

const int PegSolitaire :: is_moveValid(string move , int rondomRow , int rondomColumn ){

	int ans = 0;
	int is_chosenPeg = 0;
	int is_targetEmpty = 0;

	if (vec[rondomRow][rondomColumn] == pegCell){
		is_chosenPeg = 1;
	}

	switch (move[3])
	{
	case 'R':
		if (!(vec[rondomRow][rondomColumn+1] == wallCell) && vec[rondomRow][rondomColumn+2] == emptyCell && vec[rondomRow][rondomColumn+1] == pegCell)
			is_targetEmpty = 1;
		break;

	case 'L':
		if (!(vec[rondomRow][rondomColumn-1] == wallCell) && vec[rondomRow][rondomColumn-2] == emptyCell && vec[rondomRow][rondomColumn-1] == pegCell)
			is_targetEmpty = 1;
		break;

	case 'U':
		if (!(vec[rondomRow-1][rondomColumn] == wallCell) && vec[rondomRow-2][rondomColumn] == emptyCell && vec[rondomRow-1][rondomColumn] == pegCell)
			is_targetEmpty = 1;
		break;

	case 'D':
		if (!(vec[rondomRow+1][rondomColumn] == wallCell) && vec[rondomRow+2][rondomColumn] == emptyCell && vec[rondomRow+1][rondomColumn] == pegCell)
			is_targetEmpty = 1;
		break;			
	}
	if ((is_chosenPeg && is_targetEmpty) == 1){
		ans = 1;
	}
	else{
		ans = 0;
	}	
	
	return ans;
}

const int PegSolitaire :: is_moveValid_6(string move , int rondomRow , int rondomColumn ){

	int ans = 0;
	int is_chosenPeg = 0;
	int is_targetEmpty = 0;

	if (vec[rondomRow][rondomColumn] == pegCell){
		is_chosenPeg = 1;}

	switch (move[3])
	{
	case 'R':
		if (!(vec[rondomRow][rondomColumn+2] == wallCell) && vec[rondomRow][rondomColumn+4] == emptyCell && vec[rondomRow][rondomColumn+2] == pegCell)
			is_targetEmpty = 1;
		break;

	case 'L':
		if (!(vec[rondomRow][rondomColumn-2] == wallCell) && vec[rondomRow][rondomColumn-4] == emptyCell && vec[rondomRow][rondomColumn-2] == pegCell)
			is_targetEmpty = 1;
		break;

	case 'U':
		if ((4 <= rondomRow && rondomRow <= 6) && (vec[rondomRow-2][rondomColumn+2] == emptyCell && vec[rondomRow-1][rondomColumn+1] == pegCell 
												   ||  vec[rondomRow-2][rondomColumn-2] == emptyCell && vec[rondomRow-1][rondomColumn-1] == pegCell))
			is_targetEmpty = 1;
		break;

	case 'D':
		if ((2 <= rondomRow && rondomRow <= 4) && (vec[rondomRow+2][rondomColumn+2] == emptyCell && vec[rondomRow+1][rondomColumn+1] == pegCell
												   ||  vec[rondomRow+2][rondomColumn-2] == emptyCell && vec[rondomRow+1][rondomColumn-1] == pegCell))
			is_targetEmpty = 1;
		break;			
	}
	if ((is_chosenPeg && is_targetEmpty) == 1){
		ans = 1;
	}
	else{
		ans = 0;
	}	
	return ans;

}

const int PegSolitaire :: is_anyMovePossible()
{
	int flag = 0 , out = 0;
	for (int i = 1; i < vec.size()-1; ++i)
	{
		for (int j = 1; j < vec[0].size()-1; ++j)
		{
			if (vec[i][j] == pegCell && !(vec[i][j+1] == wallCell) && vec[i][j+2] == emptyCell && vec[i][j+1] == pegCell){        //right movement possible
				out  = 1;
			    flag = 1;
			}
			else if (vec[i][j] == pegCell && !(vec[i][j-1] == wallCell) && vec[i][j-2] == emptyCell && vec[i][j-1] == pegCell){   //left movement possible
				out  = 1;
			    flag = 1;
			}
			else if (vec[i][j] == pegCell && !(vec[i-1][j] == wallCell) && vec[i-2][j] == emptyCell && vec[i-1][j] == pegCell){   //up movement possible
				out  = 1;
			    flag = 1;
			}
			else if (vec[i][j] == pegCell && !(vec[i+1][j] == wallCell) && vec[i+2][j] == emptyCell && vec[i+1][j] == pegCell){   //down movement possible
				out  = 1;
			    flag = 1;
			}
			if(flag == 1) break;
		}
		if(flag == 1) break;
	}
	if (out == 0){
		cout << "Score is: " << getNumberOfPegs() << endl;
	}
	
	return out;
}

const int PegSolitaire :: is_anyMovePossible_6()
{
	int flag = 0 , out = 0;
	for (int i = 2; i < vec.size()-2; ++i)
	{
		for (int j = 2; j < vec[0].size()-2; ++j)
		{
			if (vec[i][j] == pegCell && !(vec[i][j+2] == wallCell) && vec[i][j+4] == emptyCell && vec[i][j+2] == pegCell){        //right movement possible
				out  = 1;
			    flag = 1;
			}
			else if (vec[i][j] == pegCell && !(vec[i][j-2] == wallCell) && vec[i][j-4] == emptyCell && vec[i][j-2] == pegCell){   //left movement possible
				out  = 1;
			    flag = 1;
			}
			else if (vec[i][j] == pegCell && (4 <= i && i <= 6) && vec[i-2][j-2] == emptyCell && vec[i-1][j-1] == pegCell){   //up-left movement possible
				out  = 1;
			    flag = 1;
			}
			else if (vec[i][j] == pegCell && (4 <= i && i <= 6) && vec[i-2][j+2] == emptyCell && vec[i-1][j+1] == pegCell){   //up-right movement possible
				out  = 1;
			    flag = 1;
			}
			else if (vec[i][j] == pegCell && (2 <= i && i <= 4) && vec[i+2][j-2] == emptyCell && vec[i+1][j-1] == pegCell){   //down-left movement possible
				out  = 1;
			    flag = 1;
			}
			else if (vec[i][j] == pegCell && (2 <= i && i <= 4) && vec[i+2][j+2] == emptyCell && vec[i+1][j+1] == pegCell){   //down-right movement possible
				out  = 1;
			    flag = 1;
			}
			if(flag == 1) break;
		}
		if(flag == 1) break;
	}

	if (out == 0){
		cout << "Score is: " << getNumberOfPegs() << endl;
	}
	return out;
}

const int PegSolitaire :: load(string str_txt)
{
	int r = 0 , c = 0 , ans = 0;
	int i = 0;
	decltype(i) j = 0;
	char ch ;
	int temp_gameType = 0;
	string str_opt_gameType;   

	ifstream file(str_txt.c_str());
	if (file)
  	{	
		file >> r;
		file >> c;
		file >> temp_gameType;
		file >> numberOfMoves;

		vec.resize(r);
		for (i = 0; i < r; ++i){
    		vec[i].resize(c);
		}

		if(temp_gameType == 1)gameType = '1';
		else if(temp_gameType == 2)gameType = '2';

      	for (i = 0; i < r; ++i)
		{
			for (j = 0; j < c; ++j)
			{
				file >> ch;
				if(ch == '0') vec[i][j] = emptyCell;
				else if(ch == '1') vec[i][j] = pegCell;
				else if(ch == '2') vec[i][j] = wallCell;
			}
		}
		
		cout << "Loaded board is printing.." << endl << endl;
		if (vec[4][5] == wallCell){											//if board is sixth one
			vectorPrinter(6);
		}
		else
			vectorPrinter();

		gameType = temp_gameType;
		
		ans = 1;
    	file.close();
 	}
 	else{ 
	 	cerr << "Unable to open file" << endl;
		ans = 0;
	}
	return ans;
}

const int PegSolitaire :: save(string str_txt)
{
	int r = 0 , c = 0 , ans = 0;

	if (str_txt.substr(str_txt.size()-4 , 4) != ".txt"){   //if the file extension is not .txt give error
		cerr << "Wrong filename try again!!" << endl;
		ans = 0;
	} 	
	else{
		ofstream file(str_txt.c_str());

		for(auto &row : vec){
			c=0;
    		for(auto col : row){
				c++;
			}
			r++;	  
		}

		file << r << endl << c << endl << gameType;		//prints row, column number and gameType of the vector on txt file
		file << endl << numberOfMoves;					//prints how many moves are made on the board
		file << endl;

		for(auto &row : vec){
			c=0;
    		for(auto col : row){
    			file << col.getState();				//prints the vector on txt file
				c++;
			}
			file << endl;
			r++;	  
		}
		file.close();
		ans = 1;
	}
	return ans;
}

const bool PegSolitaire :: compareObjects(PegSolitaire &other)
{
	if (getNumberOfPegs() >= other.getNumberOfPegs())
	{
		return true;
	}
	else
		return false;

}

int PegSolitaire :: pegsOfAllBoards(const vector<PegSolitaire> &activeSessions)
{	
	int counter = 0;
	PegSolitaire temp;
	for (int i = 0; i < activeSessions.size(); i++)
	{
		temp = activeSessions[i];
		counter += temp.getNumberOfPegs();
	}

	return counter;
}

const int PegSolitaire :: getNumberOfPegs()
{
	int i , j , counter = 0;

	for (i = 0; i < vec.size(); ++i){
		for (j = 0; j < vec[0].size(); ++j){
			if(vec[i][j] == pegCell) counter += 1;
		}
	}
	return counter;
}

const int PegSolitaire :: getNumberOfEmptyCells()
{
	int i , j , counter = 0;

	for (i = 0; i < vec.size(); ++i){
		for (j = 0; j < vec[0].size(); ++j){
			if(vec[i][j] == emptyCell) counter += 1;
		}
	}
	return counter;
}

const int PegSolitaire :: getNumberOfPegsTaken()
{
	int i , j , counter = 0;

	for (i = 0; i < vec.size(); ++i){
		for (j = 0; j < vec[0].size(); ++j){
			if(vec[i][j] == emptyCell) counter += 1;
		}
	}
	return counter - 1;	    //returns counter - 1 because board has 1 empty cell at the starting of the game	
}

bool PegSolitaire :: Cell :: operator== (PegSolitaire :: Cell A)
{
    if (state == A.state)
    {
        return 1;
    }
    return 0;
}

