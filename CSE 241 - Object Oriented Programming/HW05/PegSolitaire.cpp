//This is the implementation file PegSolitaire.cpp

#include<iostream>
#include<vector>
#include<string>
#include<ctime>

#include"PegSolitaire.h"

using namespace std;


namespace boardgame2d
{
    PegSolitaire :: PegSolitaire()
    {
        initialize();
    }

    void PegSolitaire :: playUser(const string &move)
    {
		oneMove(move);
    }

	void PegSolitaire :: playAuto()
	{
		vector<char>abc = {'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'J'};
		vector<char>num = {'1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9'};
		vector<string>way = {"RIGHT" , "LEFT" , "UP" , "DOWN"};

		string move_cmp;  //for computer game
		int i , j;
		int rondomRow , rondomColumn , rondomWay;

		do
		{
			move_cmp.resize(0);
			rondomRow = rand() % (board.size()-2) + 1;     	//generates a random number for the row number
			move_cmp.push_back(num[rondomRow-1]);
			
			rondomColumn = rand() % (board[0].size()-2) + 1;  //generates a random number for the column number
			move_cmp.push_back(abc[rondomColumn-1]);

			move_cmp.push_back(' ');

			rondomWay = rand() % 4 + 1;                     //generates a random number for the way number
			move_cmp = move_cmp + way[rondomWay-1];

		} while (!errorCheck(move_cmp));

		oneMove(move_cmp);
		cout << "move made: " << move_cmp << endl;
		
	}


	int PegSolitaire :: oneMove(const string &move)
	{
		int ans = 0;
		vector<char>abc = {'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'J'};
		vector<char>num = {'1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9'};

		int rowVariable , columnVariable;		

		for (int i = 0; i < abc.size(); i++)
		{
			if(move[1] == abc[i]){
				columnVariable = i + 1;
			}
			if(move[0] == num[i]){
				rowVariable = i + 1;
			}
		}
		
		if((move.substr(3,move.length()-3)) == "RIGHT")
		{
			swap(board[rowVariable][columnVariable],board[rowVariable][columnVariable+2]);
			board[rowVariable][columnVariable+1] = empty;
			ans = 1;
			print();
		}
	
		else if((move.substr(3,move.length()-3)) == "LEFT")
		{
			swap(board[rowVariable][columnVariable],board[rowVariable][columnVariable-2]);
			board[rowVariable][columnVariable-1] = empty;
			ans = 1;
			print();
		}

		else if((move.substr(3,move.length()-3)) == "UP")
		{
			swap(board[rowVariable][columnVariable],board[rowVariable-2][columnVariable]);
			board[rowVariable-1][columnVariable] = empty;
			ans = 1;
			print();
		}

		else if((move.substr(3,move.length()-3)) == "DOWN")
		{
			swap(board[rowVariable][columnVariable],board[rowVariable+2][columnVariable]);
			board[rowVariable+1][columnVariable] = empty;
			ans = 1;
			print();
		}
		return ans;	
	}

    int PegSolitaire :: errorCheck(const string &move)
    {
        int i , j;
		int ans = 1;

        vector<char>abc = {'A' , 'B' , 'C' , 'D' , 'E' , 'F' , 'G' , 'H' , 'J'};
	    vector<char>num = {'1' , '2' , '3' , '4' , '5' , '6' , '7' , '8' , '9'};
		vector<string>way = {"RIGHT" , "LEFT" , "UP" , "DOWN"};

        int rowVariable;      //row number of chosen cell
	    int columnVariable;   //column number of chosen cell

    	int is_firstParameter = 0;
	    int is_secondParameter = 0;
	    int is_thirdParameter = 0;
	    int is_fourthParameter = 0;


		for (i = 0; i < board[0].size()-2; ++i){
			if (move[1] == abc[i]){
				is_firstParameter = 1;
				columnVariable = i+1;
			}
			if (move[0] == num[i]){
				is_secondParameter = 1;
				rowVariable = i+1;
			}	
		}
		if (move[2] == ' '){is_thirdParameter = 1;}
			
		for (i = 0; i < 4; ++i){
			if (move.substr(3,move.length()-3) == way[i]){
				is_fourthParameter = 1;
			}
		}

		if ( !is_firstParameter || !is_secondParameter || !is_thirdParameter || !is_fourthParameter || !is_moveValid( move , rowVariable , columnVariable )){
			ans = 0;
		}

		return ans;	   
    }

	int PegSolitaire :: is_moveValid(const string& move , int rondomRow , int rondomColumn ){

		int ans = 0;
		int is_chosenPeg = 0;
		int is_targetEmpty = 0;

		if (board[rondomRow][rondomColumn] == peg){
			is_chosenPeg = 1;
		}

		//cout << move << endl;

		if((move.substr(3,move.length()-3)) == "RIGHT")
		{
			if (!(board[rondomRow][rondomColumn+1] == wall) && board[rondomRow][rondomColumn+2] == empty && board[rondomRow][rondomColumn+1] == peg)
				is_targetEmpty = 1;
		}
	
		else if((move.substr(3,move.length()-3)) == "LEFT")
		{
			if (!(board[rondomRow][rondomColumn-1] == wall) && board[rondomRow][rondomColumn-2] == empty && board[rondomRow][rondomColumn-1] == peg)
				is_targetEmpty = 1;
		}

		else if((move.substr(3,move.length()-3)) == "UP")
		{
			if (!(board[rondomRow-1][rondomColumn] == wall) && board[rondomRow-2][rondomColumn] == empty && board[rondomRow-1][rondomColumn] == peg)
				is_targetEmpty = 1;
		}

		else if((move.substr(3,move.length()-3)) == "DOWN")
		{
			if (!(board[rondomRow+1][rondomColumn] == wall) && board[rondomRow+2][rondomColumn] == empty && board[rondomRow+1][rondomColumn] == peg)
				is_targetEmpty = 1;
		}

		if ((is_chosenPeg && is_targetEmpty) == 1){
			ans = 1;
		}
		else{
			ans = 0;
		}	
	
		return ans;
	}

	bool PegSolitaire :: endGame()
	{
		int flag = 0; 
		bool out = 0;
		for (int i = 1; i < board.size()-1; ++i)
		{	
			for (int j = 1; j < board[0].size()-1; ++j)
			{
				if (board[i][j] == peg && !(board[i][j+1] == wall) && board[i][j+2] == empty && board[i][j+1] == peg){        //right movement possible
					out  = 1;
			    	flag = 1;
				}
				else if (board[i][j] == peg && !(board[i][j-1] == wall) && board[i][j-2] == empty && board[i][j-1] == peg){   //left movement possible
					out  = 1;
			    	flag = 1;
				}
				else if (board[i][j] == peg && !(board[i-1][j] == wall) && board[i-2][j] == empty && board[i-1][j] == peg){   //up movement possible
					out  = 1;
			    	flag = 1;
				}
				else if (board[i][j] == peg && !(board[i+1][j] == wall) && board[i+2][j] == empty && board[i+1][j] == peg){   //down movement possible
					out  = 1;
			    	flag = 1;
				}
				if(flag == 1) break;
			}
			if(flag == 1) break;
		}
		if (out == 0){
			cout << "Score is: " << boardScore() << endl;
		}
	
		return out;
	}	

	void PegSolitaire :: print()
	{
    	int i = 0 , j = 0;
		vector<char>abc = {'a' , 'b' , 'c' , 'd' , 'e' , 'f' , 'g' , 'h' , 'j'};                     //for printing the horizontal guide line

    	for (i = 0; i < board.size(); i++)
    	{
        	for (j = 0; j < board[0].size(); j++) 
        	{
           	 	if (board[i][j] == empty)
					cout << ". ";

				if (board[i][j] == peg)
					cout << "P ";

				if (board[i][j] == wall){

					if ((i == 0) && (1<=j && j<=(board[0].size()-2))){ 
						cout << abc[j-1] << " ";               //for printing the horizontal guide line
					}
					else if ((j == 0) && (1<=i && i<=(board.size()-2))){
						cout << i << " ";                      //for printing the vertical guide line
					}
					else
						cout << "  ";
				}	
			}

			cout << endl;                 
    	}
	}

    int PegSolitaire :: boardScore()
    {
		int i , j , counter = 0;

		for (i = 0; i < board.size(); ++i){
			for (j = 0; j < board[0].size(); ++j){
				if(board[i][j] == peg) counter += 1;
			}
		}
		return counter;
    }

    void PegSolitaire :: initialize()
    {
        int i , j;

		board.resize(11);
	    for (i = 0; i < 11; ++i){
    	    board[i].resize(11);
	    }

    	for (i = 0; i < board.size(); i++)
    	{
        	for (j = 0; j < board[0].size(); j++)
        	{
            	board[i][j] = wall;
        	} 
    	}
			
        for (i = 0; i <= 10; ++i)
	    {
		    if (i == 1 || i == 2 || i == 3 || i == 7 || i == 8 || i == 9)
		        for (j = 4; j <= 6; ++j)
			        board[i][j] = peg;
		
		    else if (i==4 || i==5 || i==6)
			    for (j = 1; j <= 9; ++j)
				    board[i][j] = peg;
	    }
	    board[5][5] = empty;         
    }    

}