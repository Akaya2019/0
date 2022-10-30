#include<iostream>
#include<vector>
#include<string>
#include<ctime>
#include<conio.h>
using namespace std;

enum cellState
{
	empty = 0,
	peg ,
	wall 
};

void printVector(vector< vector<cellState> >vec , int row, int column , int specialSituation_6 = 0);             //prints current state of board 
int is_anyMovePossible(const vector<vector<cellState>>& vec);                                                    //controls whether any movement possible on board
int is_moveValid(const vector<vector<cellState>>& vec , string move , int rowVariable , int columnVariable);
void valid_move_Function(char gameType , vector<vector<cellState>>& vec);
void boardStyle_1(char gameType);
void boardStyle_2(char gameType);
void boardStyle_3(char gameType);
void boardStyle_4(char gameType);
void boardStyle_5(char gameType);
int is_anyMovePossible_6(const vector<vector<cellState>>& vec , int row , int column);
int is_moveValid_6(const vector<vector<cellState>>& vec , string move , int rowVariable , int columnVariable);   //special func for 6th board
void make_move_6(vector<vector<cellState>>& vec , string move , int rowVariable , int columnVariable);
void boardStyle_6(char gameType);


int main()
{
	char opt_boardType; //options for board type ,takes a char because it gives error if user enters a char  
	char opt_gameType; //options for game type

	do
	{
		cout << "Please select the Board type: ";
		cin >> opt_boardType;

		if (opt_boardType < '1' || opt_boardType > '6')
			cout << endl << "TRY AGAIN.." << endl;

	} while (opt_boardType < '1' || opt_boardType > '6');

	do
	{
		cout << "1-> Human player game" << endl
			 << "2-> Computer game" << endl
			 << "Please select the Game type: ";
		cin >> opt_gameType;

		if (opt_gameType < '1' || opt_gameType > '2')
			cout << endl << "TRY AGAIN.." << endl;

	} while (opt_gameType < '1' || opt_gameType > '2');

	switch(opt_boardType)
	{
		case '1':
			boardStyle_1(opt_gameType);
			break;

		case '2':
			boardStyle_2(opt_gameType);
			break;

		case '3':
			boardStyle_3(opt_gameType);
			break;

		case '4':
			boardStyle_4(opt_gameType);
			break;

		case '5':
			boardStyle_5(opt_gameType);
			break;

		case '6':
			boardStyle_6(opt_gameType);
			break;				
	}

    getch();
	return 0;
}

void printVector(vector< vector<cellState> >vec , int r , int c , int specialSituation_6) //takes vector, row and column
{// if specialSituation_6 takes a value other than 0 then there is a change in the printing of the guide lines
	int i, j;
	vector<char>abc = {'a' , 'b' , 'c' , 'd' , 'e' , 'f' , 'g' , 'h' , 'j'}; //for printing the horizontal guide line

	cout << endl;
	for (i = 0; i < r; ++i)
	{
		for (j = 0; j < c; ++j)
		{
			if (vec[i][j] == empty)
				cout << ". ";

			if (vec[i][j] == peg)
				cout << "P ";

			if (vec[i][j] == wall){
				if (specialSituation_6 != 0){

					if ((i == 1) && (2<=j && j<(c-2))){ 
					cout << abc[j-2] << " ";               //for printing the horizontal guide line
					}
					else if ((j == 1) && (2<=i && i<(r-2))){
						cout << i-1 << " ";                      //for printing the vertical guide line
					}
					else
						cout << "  ";
				}
				
				else{

					if ((i == 0) && (1<=j && j<=(c-2))){ 
						cout << abc[j-1] << " ";               //for printing the horizontal guide line
					}
					else if ((j == 0) && (1<=i && i<=(r-2))){
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
int is_anyMovePossible(const vector<vector<cellState>>& vec){

	int flag = 0 , out = 0;
	for (int i = 1; i < vec[0].size()-1; ++i)
	{
		for (int j = 1; j < vec[0].size()-1; ++j)
		{
			if (vec[i][j] == peg && vec[i][j+1] != wall && vec[i][j+2] == empty && vec[i][j+1] == peg){        //right movement possible
				out  = 1;
			    flag = 1;
			}
			else if (vec[i][j] == peg && vec[i][j-1] != wall && vec[i][j-2] == empty && vec[i][j-1] == peg){   //left movement possible
				out  = 1;
			    flag = 1;
			}
			else if (vec[i][j] == peg && vec[i-1][j] != wall && vec[i-2][j] == empty && vec[i-1][j] == peg){   //up movement possible
				out  = 1;
			    flag = 1;
			}
			else if (vec[i][j] == peg && vec[i+1][j] != wall && vec[i+2][j] == empty && vec[i+1][j] == peg){   //down movement possible
				out  = 1;
			    flag = 1;
			}
			if(flag == 1) break;
		}
		if(flag == 1) break;
	}
	return out;
}

int is_moveValid(const vector<vector<cellState>>& vec , string move , int rowVariable , int columnVariable ){

	int ans = 0;
	int is_chosenPeg = 0;
	int is_targetEmpty = 0;

	if (vec[rowVariable][columnVariable] == peg){
		is_chosenPeg = 1;
	}

	switch (move[3])
	{
	case 'R':
		if (vec[rowVariable][columnVariable+1] != wall && vec[rowVariable][columnVariable+2] == empty && vec[rowVariable][columnVariable+1] == peg)
			is_targetEmpty = 1;
		break;

	case 'L':
		if (vec[rowVariable][columnVariable-1] != wall && vec[rowVariable][columnVariable-2] == empty && vec[rowVariable][columnVariable-1] == peg)
			is_targetEmpty = 1;
		break;

	case 'U':
		if (vec[rowVariable-1][columnVariable] != wall && vec[rowVariable-2][columnVariable] == empty && vec[rowVariable-1][columnVariable] == peg)
			is_targetEmpty = 1;
		break;

	case 'D':
		if (vec[rowVariable+1][columnVariable] != wall && vec[rowVariable+2][columnVariable] == empty && vec[rowVariable+1][columnVariable] == peg)
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

void valid_move_Function(char gameType , vector<vector<cellState>>& vec){

	vector<char>abc = {'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'J'};
	vector<char>num = {'1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9'};
	vector<char>way = {'R' , 'L' , 'U' , 'D'};
	string move = "AAAA";
	int i , j , counter = 0;
	int rowVariable;      //row number of chosen cell
	int columnVariable;   //column number of chosen cell
	int rondomRow , rondomColumn , rondomWay;

	int is_firstParameter = 0;
	int is_secondParameter = 0;
	int is_thirdParameter = 0;
	int is_fourthParameter = 0;

	if (gameType == '1'){ //if game type is human player game

		do
		{
			do{
				cout << "Make your move: ";
				cin >> move;

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

				if ((move.size() > 4) || !is_firstParameter || !is_secondParameter || !is_thirdParameter || !is_fourthParameter || !is_moveValid(vec , move , rowVariable , columnVariable )){
					cout << "Try Again.." << endl;
					is_firstParameter = 0;
					is_secondParameter = 0;
					is_thirdParameter = 0;
					is_fourthParameter = 0;
				}
		
			} while ((move.size() > 4) || !is_firstParameter || !is_secondParameter || !is_thirdParameter || !is_fourthParameter || !is_moveValid(vec , move , rowVariable , columnVariable ));

			switch (move[3])
			{
				case 'R':
					swap(vec[rowVariable][columnVariable],vec[rowVariable][columnVariable+2]);
					vec[rowVariable][columnVariable+1] = empty;
					printVector(vec , vec[0].size() , vec[0].size());
					break;

				case 'L':
					swap(vec[rowVariable][columnVariable],vec[rowVariable][columnVariable-2]);
					vec[rowVariable][columnVariable-1] = empty;
					printVector(vec , vec[0].size() , vec[0].size());
					break;

				case 'U':
					swap(vec[rowVariable][columnVariable],vec[rowVariable-2][columnVariable]);
					vec[rowVariable-1][columnVariable] = empty;
					printVector(vec , vec[0].size() , vec[0].size());
					break;

				case 'D':
					swap(vec[rowVariable][columnVariable],vec[rowVariable+2][columnVariable]);
					vec[rowVariable+1][columnVariable] = empty;
					printVector(vec , vec[0].size() , vec[0].size());
					break;
			}

		} while (is_anyMovePossible(vec) != 0); //if there is any movement which is possible program keeps going to ask user to input
		
		for (i = 0; i < vec[0].size(); ++i){
			for (j = 0; j < vec[0].size(); ++j){
				if(vec[i][j] == peg) counter += 1;
			}
		}
		cout << "Your Score is: " << counter << endl;
	}
	else if (gameType == '2'){ //if game type is computer game
		
		srand (time(NULL)); //use of random number generator
		do
		{
			do
			{
				rondomColumn = rand() % (vec[0].size()-2) + 1;  //generates a random number for the column number
				move[0] = abc[rondomColumn-1];
				rondomRow = rand() % (vec[0].size()-2) + 1;     //generates a random number for the row number
				move[1] = num[rondomRow-1];
				move[2] = '-';
				rondomWay = rand() % 4 + 1;                     //generates a random number for the way number
				move[3] = way[rondomWay-1];
				move[4] = '\0';

			} while (!is_moveValid(vec , move , rondomRow , rondomColumn));

			switch (move[3])
			{
				case 'R':
					swap(vec[rondomRow][rondomColumn],vec[rondomRow][rondomColumn+2]);
					vec[rondomRow][rondomColumn+1] = empty;
					printVector(vec , vec[0].size() , vec[0].size());
					cout << "move made: " << move << endl;
					break;

				case 'L':
					swap(vec[rondomRow][rondomColumn],vec[rondomRow][rondomColumn-2]);
					vec[rondomRow][rondomColumn-1] = empty;
					printVector(vec , vec[0].size() , vec[0].size());
					cout << "move made: " << move << endl;
					break;

				case 'U':
					swap(vec[rondomRow][rondomColumn],vec[rondomRow-2][rondomColumn]);
					vec[rondomRow-1][rondomColumn] = empty;
					printVector(vec , vec[0].size() , vec[0].size());
					cout << "move made: " << move << endl;
					break;

				case 'D':
					swap(vec[rondomRow][rondomColumn],vec[rondomRow+2][rondomColumn]);
					vec[rondomRow+1][rondomColumn] = empty;
					printVector(vec , vec[0].size() , vec[0].size());
					cout << "move made: " << move << endl;
					break;
			}
			
		} while (is_anyMovePossible(vec) != 0);
		
		for (i = 0; i < vec[0].size(); ++i){
			for (j = 0; j < vec[0].size(); ++j){
				if(vec[i][j] == peg) counter += 1;
			}
		}
		cout << "Score is: " << counter << endl;
	}
}

void boardStyle_1(char gameType)
{
	int i , j , random;
	vector< vector<cellState> > myVector_1(9, vector<cellState>(9, wall)); //creates a 9x9 vector and fills all its cells with wall

	srand (time(NULL)); //use of random number generator
	random = rand() % 2 + 1;

	for (i = 0; i <= 8; ++i)
	{
		if (i == 1 || i == 7)
			for (j = 3; j <= 5; ++j)
				myVector_1[i][j] = peg;

		else if (i == 2 || i == 6)
			for (j = 2; j <= 6; ++j)
				myVector_1[i][j] = peg;
		
		else if (i==3 || i==4 || i==5)
			for (j = 1; j <= 7; ++j)
				myVector_1[i][j] = peg;
	}

	if (random == 1){
		myVector_1[3][4] = empty;
	}
	else{
		myVector_1[5][4] = empty;			
	}

	printVector(myVector_1 , 9 , 9);
	valid_move_Function(gameType , myVector_1);
}

void boardStyle_2(char gameType)
{
	int i , j;
	vector< vector<cellState> > myVector_2(11, vector<cellState>(11, wall));  //creates a 11x11 vector and fills all its cells with wall

	for (i = 0; i <= 10; ++i)
	{
		if (i == 1 || i == 2 || i == 3 || i == 7 || i == 8 || i == 9)
			for (j = 4; j <= 6; ++j)
				myVector_2[i][j] = peg;
		
		else if (i==4 || i==5 || i==6)
			for (j = 1; j <= 9; ++j)
				myVector_2[i][j] = peg;
	}
	myVector_2[5][5] = empty;

	printVector(myVector_2 , 11 , 11);
	valid_move_Function(gameType , myVector_2);
}

void boardStyle_3(char gameType)
{
	int i , j;
	vector< vector<cellState> > myVector_3(10, vector<cellState>(10, wall));   //creates a 10x10 vector and fills all its cells with wall

	for (i = 0; i <= 10; ++i)
	{
		if (i == 1 || i == 2 || i == 3 || i == 7 || i == 8)
			for (j = 3; j <= 5; ++j)
				myVector_3[i][j] = peg;
		
		else if (i==4 || i==5 || i==6)
			for (j = 1; j <= 8; ++j)
				myVector_3[i][j] = peg;
	}
	myVector_3[5][4] = empty;

	printVector(myVector_3 , 10 , 10);
	valid_move_Function(gameType , myVector_3);
}

void boardStyle_4(char gameType)
{
	int i , j;
	vector< vector<cellState> > myVector_4(9, vector<cellState>(9, wall));   //creates a 9x9 vector and fills all its cells with wall

	for (i = 0; i <= 8; ++i)
	{
		if (i == 1 || i == 2 || i == 6 || i == 7)
			for (j = 3; j <= 5; ++j)
				myVector_4[i][j] = peg;
		
		else if (i==3 || i==4 || i==5)
			for (j = 1; j <= 7; ++j)
				myVector_4[i][j] = peg;
	}
	myVector_4[4][4] = empty;

	printVector(myVector_4 , 9 , 9);
	valid_move_Function(gameType , myVector_4);
}

void boardStyle_5(char gameType)
{
	int i , j;
	vector< vector<cellState> > myVector_5(11, vector<cellState>(11, wall));  //creates a 11x11 vector and fills all its cells with wall

	for (i = 0; i <= 10; ++i)
	{
		if (i == 1 || i == 9)
			myVector_5[i][5] = peg;

		else if (i == 2 || i == 8)
			for (j = 4; j <= 6; ++j)
				myVector_5[i][j] = peg;

		else if (i == 3 || i == 7)
			for (j = 3; j <= 7; ++j)
				myVector_5[i][j] = peg;

		else if (i == 4 || i == 6)
			for (j = 2; j <= 8; ++j)
				myVector_5[i][j] = peg;						
		
		else if (i==5)
			for (j = 1; j <= 9; ++j)
				myVector_5[i][j] = peg;
	}
	myVector_5[5][5] = empty;

	printVector(myVector_5 , 11 , 11);
	valid_move_Function(gameType , myVector_5);
}

int is_anyMovePossible_6(const vector<vector<cellState>>& vec , int row , int column){

	int flag = 0 , out = 0;
	for (int i = 2; i < row-2; ++i)
	{
		for (int j = 2; j < column-2; ++j)
		{
			if (vec[i][j] == peg && vec[i][j+2] != wall && vec[i][j+4] == empty && vec[i][j+2] == peg){        //right movement possible
				out  = 1;
			    flag = 1;
			}
			else if (vec[i][j] == peg && vec[i][j-2] != wall && vec[i][j-4] == empty && vec[i][j-2] == peg){   //left movement possible
				out  = 1;
			    flag = 1;
			}
			else if (vec[i][j] == peg && (4 <= i && i <= 6) && vec[i-2][j-2] == empty && vec[i-1][j-1] == peg){   //up-left movement possible
				out  = 1;
			    flag = 1;
			}
			else if (vec[i][j] == peg && (4 <= i && i <= 6) && vec[i-2][j+2] == empty && vec[i-1][j+1] == peg){   //up-right movement possible
				out  = 1;
			    flag = 1;
			}
			else if (vec[i][j] == peg && (2 <= i && i <= 4) && vec[i+2][j-2] == empty && vec[i+1][j-1] == peg){   //down-left movement possible
				out  = 1;
			    flag = 1;
			}
			else if (vec[i][j] == peg && (2 <= i && i <= 4) && vec[i+2][j+2] == empty && vec[i+1][j+1] == peg){   //down-right movement possible
				out  = 1;
			    flag = 1;
			}
			if(flag == 1) break;
		}
		if(flag == 1) break;
	}
	return out;
}

int is_moveValid_6(const vector<vector<cellState>>& vec , string move , int rowVariable , int columnVariable){

	int ans = 0;
	int is_chosenPeg = 0;
	int is_targetEmpty = 0;

	if (vec[rowVariable][columnVariable] == peg){
		is_chosenPeg = 1;}

	switch (move[3])
	{
	case 'R':
		if (vec[rowVariable][columnVariable+2] != wall && vec[rowVariable][columnVariable+4] == empty && vec[rowVariable][columnVariable+2] == peg)
			is_targetEmpty = 1;
		break;

	case 'L':
		if (vec[rowVariable][columnVariable-2] != wall && vec[rowVariable][columnVariable-4] == empty && vec[rowVariable][columnVariable-2] == peg)
			is_targetEmpty = 1;
		break;

	case 'U':
		if ((4 <= rowVariable && rowVariable <= 6) && (vec[rowVariable-2][columnVariable+2] == empty && vec[rowVariable-1][columnVariable+1] == peg 
												   ||  vec[rowVariable-2][columnVariable-2] == empty && vec[rowVariable-1][columnVariable-1] == peg))
			is_targetEmpty = 1;
		break;

	case 'D':
		if ((2 <= rowVariable && rowVariable <= 4) && (vec[rowVariable+2][columnVariable+2] == empty && vec[rowVariable+1][columnVariable+1] == peg
												   ||  vec[rowVariable+2][columnVariable-2] == empty && vec[rowVariable+1][columnVariable-1] == peg))
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

void make_move_6(vector<vector<cellState>>& vec , string move , int rowVariable , int columnVariable){

	switch (move[3])
	{
		case 'R':
			swap(vec[rowVariable][columnVariable],vec[rowVariable][columnVariable+4]);
			vec[rowVariable][columnVariable+2] = empty;
			printVector(vec , 9 , vec[0].size() , 6);
			break;

		case 'L':
			swap(vec[rowVariable][columnVariable],vec[rowVariable][columnVariable-4]);
			vec[rowVariable][columnVariable-2] = empty;
			printVector(vec , 9 , vec[0].size() , 6);
			break;

		case 'U':
			if (vec[rowVariable-2][columnVariable-2] == empty){									//left side priority for upward movement
				swap(vec[rowVariable][columnVariable],vec[rowVariable-2][columnVariable-2]);
				vec[rowVariable-1][columnVariable-1] = empty;
			}
			else{
				swap(vec[rowVariable][columnVariable],vec[rowVariable-2][columnVariable+2]);
				vec[rowVariable-1][columnVariable+1] = empty;}
			printVector(vec , 9 , vec[0].size() , 6);
			break;

		case 'D':
			if (vec[rowVariable+2][columnVariable-2] == empty){									//left side priority for downward movement
				swap(vec[rowVariable][columnVariable],vec[rowVariable+2][columnVariable-2]);
				vec[rowVariable+1][columnVariable-1] = empty;
			}
			else{
				swap(vec[rowVariable][columnVariable],vec[rowVariable+2][columnVariable+2]);
				vec[rowVariable+1][columnVariable+1] = empty;}
			printVector(vec , 9 , vec[0].size() , 6);
			break;
	}
}

void boardStyle_6(char gameType)
{
	int i , j , counter = 0;
	vector< vector<cellState> > myVector_6(9, vector<cellState>(13, wall));  //creates a 11x11 vector and fills all its cells with wall

	vector<char>abc = {'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'J'};
	vector<char>num = {'1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9'};
	vector<char>way = {'R' , 'L' , 'U' , 'D'};
	string move = "AAAA";

	int rowVariable;      //row number of chosen cell
	int columnVariable;   //column number of chosen cell
	int rondomRow , rondomColumn , rondomWay;

	int is_firstParameter = 0;
	int is_secondParameter = 0;
	int is_thirdParameter = 0;
	int is_fourthParameter = 0;

	myVector_6[2][6] = empty;

	for (i = 3 , j = 5; j <= 7; ++j){
		if(j % 2 == 1) myVector_6[i][j] = peg;
		else myVector_6[i][j] = wall;
	}
	for (i = 4 , j = 4; j <= 8; ++j){
		if(j % 2 == 0) myVector_6[i][j] = peg;
		else myVector_6[i][j] = wall;
	}
	for (i = 5 , j = 3; j <= 9; ++j){
		if(j % 2 == 1) myVector_6[i][j] = peg;
		else myVector_6[i][j] = wall;						
	}
	for (i = 6 , j = 2; j <= 10; ++j){
		if(j % 2 == 0) myVector_6[i][j] = peg;
		else myVector_6[i][j] = wall;
	}
	printVector(myVector_6 , 9 , 13 , 6);

	if (gameType == '1'){  //if game type is human player game
		do
		{
			do
			{
				cout << "Make your move: ";
				cin >> move;

				for (i = 0; i < myVector_6[0].size()-4; ++i){
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

				if ((move.size() > 4) || !is_firstParameter || !is_secondParameter || !is_thirdParameter || !is_fourthParameter || !is_moveValid_6(myVector_6 , move , rowVariable , columnVariable )){
					cout << "Try Again.." << endl;
					is_firstParameter = 0;
					is_secondParameter = 0;
					is_thirdParameter = 0;
					is_fourthParameter = 0;
				}
			} while ((move.size() > 4) || !is_firstParameter || !is_secondParameter || !is_thirdParameter || !is_fourthParameter || !is_moveValid_6(myVector_6 , move , rowVariable , columnVariable ));
		
			make_move_6(myVector_6 , move , rowVariable , columnVariable);

		} while (is_anyMovePossible_6(myVector_6 , 9 , 13));					//number of rows of the vector=9 , number of columns of the vector=13

		for (i = 0; i < 9; ++i){
			for (j = 0; j < 13; ++j){
				if(myVector_6[i][j] == peg) counter += 1;
			}
		}
		cout << "Your Score is: " << counter << endl;
	}

	else if (gameType == '2'){ //if game type is computer game
		
		srand (time(NULL)); //use of random number generator
		do
		{
			do
			{
				rondomColumn = rand() % 10 + 2;    //generates a random number for the column number
				move[0] = abc[rondomColumn-2];
				rondomRow = rand() % 6 + 2;        //generates a random number for the row number
				move[1] = num[rondomRow-2];
				move[2] = '-';
				rondomWay = rand() % 4 + 1;        //generates a random number for the way number
				move[3] = way[rondomWay-1];
				move[4] = '\0';

			} while (!is_moveValid_6(myVector_6 , move , rondomRow , rondomColumn));

			make_move_6(myVector_6 , move , rondomRow , rondomColumn);
			
		} while (is_anyMovePossible_6(myVector_6 , 9 , 13));
		
		for (i = 0; i < 9; ++i){
			for (j = 0; j < 13; ++j){
				if(myVector_6[i][j] == peg) counter += 1;
			}
		}
		cout << "Score is: " << counter << endl;
	}

}
