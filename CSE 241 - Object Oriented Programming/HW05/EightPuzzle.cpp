//This is the implementation file EightPuzzle.cpp

#include<iostream>
#include<vector>
#include<string>
#include<ctime>
#include<cstdlib>

#include"EightPuzzle.h"

using namespace std;


namespace boardgame2d
{
    EightPuzzle :: EightPuzzle()
    {
        initialize();
    }

	EightPuzzle& EightPuzzle :: operator=(const EightPuzzle& other)
	{
		for (int i = 0; i < board.size(); i++)
		{
			for (int j = 0; j < board[i].size(); j++)
			{
				board[i][j] = other.board[i][j];
			}
		}

		return *this;
	}

	int EightPuzzle :: solvable()
	{
		int i , j , ans = 0;
		vector<int> temp;
		int inversions = 0;

		for ( i = 1; i < board.size()-1; i++)
		{
			for ( j = 1; j < board[i].size()-1; j++)
			{
				temp.push_back(board[i][j]);
			}
		}

		for (i = 0; i < temp.size(); i++)
		{
			for ( j = i; j < temp.size(); j++)
			{
				if (temp[i] != 9 && temp[j] != 9 && temp[i] > temp[j])
				{
					inversions += 1;
				}		
			}
		}
		if(inversions%2 == 0){ans = 1;}
		else ans = 0; 

		return ans;
	}

    void EightPuzzle :: playUser(const string &move)
    {
		oneMove(move);
		print();
    }

	void EightPuzzle :: playAuto()
	{
		vector<char>num = {'1' , '2' , '3' , '4' , '5' , '6' , '7' , '8'};
		vector<string>way = {"RIGHT" , "LEFT" , "UP" , "DOWN"};

		string move_cmp;  //for computer game
		int i , j;
		int rondomNum , rondomColumn , rondomWay;
		int counter= 0;

		EightPuzzle temp;
		temp = *this;

		while(1)
		{
			while(1)
			{

				move_cmp.resize(0);
				rondomNum = rand() % 8 + 1;     	//generates a random number for the number
				move_cmp.push_back(num[rondomNum-1]);

				move_cmp.push_back(' ');

				rondomWay = rand() % 4 + 1;                     //generates a random number for the way number
				move_cmp = move_cmp + way[rondomWay-1];

				if (temp.oneMove(move_cmp) == 1){
					break;
				}
				
			}	 
			counter++;
			if (temp.boardScore() < boardScore()){
				*this = temp;
				break;
			}
		}
		
		print();
		cout << "number of moves: " << counter << endl ;
		cout << "move made: " << move_cmp << endl;		
	}


	int EightPuzzle :: oneMove(const string &move)
	{
		int ans = 0; 
        int i , j;
		int rowVariable , columnVariable;
        int flag = 0;

		if(errorCheck(move) == 0) return 0;	

        for (i = 1; i < board.size()-1; i++){
            for (j = 1; j < board[i].size()-1; j++)
            {
                if((move[0] - '0') == board[i][j]){
                    rowVariable = i;
                    columnVariable = j;
                    flag = 1;
                    break;
                }
            }
            if(flag == 1)break;
        }
		
		if((move.substr(2,move.length()-2)) == "RIGHT")
		{
			swap(board[rowVariable][columnVariable],board[rowVariable][columnVariable+1]);
			ans = 1;
		}
	
		else if((move.substr(2,move.length()-2)) == "LEFT")
		{
			swap(board[rowVariable][columnVariable],board[rowVariable][columnVariable-1]);
			ans = 1;
		}

		else if((move.substr(2,move.length()-2)) == "UP")
		{
			swap(board[rowVariable][columnVariable],board[rowVariable-1][columnVariable]);
			ans = 1;
		}

		else if((move.substr(2,move.length()-2)) == "DOWN")
		{
			swap(board[rowVariable][columnVariable],board[rowVariable+1][columnVariable]);
			ans = 1;
		}
        return ans;
	}

    int EightPuzzle :: errorCheck(const string &move)
    {
        int i , j;
		int ans = 1;
        int flag = 0;
        
	    vector<char>num = {'1' , '2' , '3' , '4' , '5' , '6' , '7' , '8'};
		vector<string>way = {"RIGHT" , "LEFT" , "UP" , "DOWN"};

        int rowVariable;      //row number of chosen cell
	    int columnVariable;   //column number of chosen cell

    	int is_firstParameter = 0;
	    int is_secondParameter = 0;
	    int is_thirdParameter = 0;


		for (int k = 0; k < num.size(); ++k){
			if (move[0] == num[k]){
				is_firstParameter = 1;
                break;
			}	
		}

        if (is_firstParameter == 1)
        {
            for (i = 0; i < board.size(); i++){
                for (j = 0; j < board[i].size(); j++)
                {
                    if((move[0] - '0') == board[i][j]){
                        rowVariable = i;
                        columnVariable = j;
                        flag = 1;
                        break;
                    }
                }
                if(flag == 1)break;
            }
        }
        
		if (move[1] == ' '){is_secondParameter = 1;}
			
		for (i = 0; i < 4; ++i){
			if (move.substr(2,move.length()-2) == way[i]){
				is_thirdParameter = 1;
			}
		}

		//cout << is_firstParameter << is_secondParameter << is_thirdParameter << endl;

		if ( !is_firstParameter || !is_secondParameter || !is_thirdParameter || !is_moveValid( move , rowVariable , columnVariable )){
			ans = 0;
		}

		return ans;
    }

	int EightPuzzle :: is_moveValid(const string& move , int rondomRow , int rondomColumn )
    {
		int ans = 0;
		int is_chosenPiece = 0;
		int is_targetEmpty = 0;

		if (board[rondomRow][rondomColumn] != 9 && board[rondomRow][rondomColumn] != wallS){
			is_chosenPiece = 1;
		}

		if((move.substr(2,move.length()-2)) == "RIGHT")
		{
			if (board[rondomRow][rondomColumn+1] == 9)
				is_targetEmpty = 1;
		}
	
		else if((move.substr(2,move.length()-2)) == "LEFT")
		{
			if (board[rondomRow][rondomColumn-1] == 9)
				is_targetEmpty = 1;
		}

		else if((move.substr(2,move.length()-2)) == "UP")
		{
			if (board[rondomRow-1][rondomColumn] == 9)
				is_targetEmpty = 1;
		}

		else if((move.substr(2,move.length()-2)) == "DOWN")
		{
			if (board[rondomRow+1][rondomColumn] == 9)
				is_targetEmpty = 1;
		}

		if ((is_chosenPiece && is_targetEmpty) == 1){
			ans = 1;
		}
		else{
			ans = 0;
		}	

		//cout << rondomRow <<  rondomColumn << endl;
		return ans;
	}

	bool EightPuzzle :: endGame()
	{
        int i , j;
		int flag = 0; 
		bool out = 0;
        int counter = 0;

    	for (i = 1; i < board.size()-1; i++)
    	{
        	for (j = 1; j < board[i].size()-1; j++) 
        	{
           	 	if (board[i][j] != ++counter){
                    out = 1;
                    flag = 1;
                    break;
                }    
			}
            if(flag == 1) {break;} 
    	}        
	
		return out;
	}	

	void EightPuzzle :: print()
	{
    	int i = 0 , j = 0;

        cout << "-------------" << endl;

    	for (i = 1; i < board.size()-1; i++)
    	{
            cout << "| ";
        	for (j = 1; j < board[0].size()-1; j++) 
        	{
           	 	if (board[i][j] == 9)
					cout << "  | ";

				// else if (board[i][j] == wallS){
				// 	cout << "  | ";
				// }
                else
					cout << board[i][j] << " | ";	
			}
            cout << endl << "-------------";
            cout << endl;              
    	}
		cout << "SCORE : " << boardScore() << endl;
	}

    int EightPuzzle :: boardScore()
    {
		int counter = 0 , flag = 0;
		int rowVariable , columnVariable;

        for (int k = 1; k <= 9; k++)
		{	
			flag = 0;
			for (int i = 1; i < board.size()-1; i++){
                for (int j = 1; j < board[i].size()-1; j++)
                {
                    if(board[i][j] == k){
                        rowVariable = i;
                        columnVariable = j;
                        flag = 1;
                        break;
                    }
                }
                if(flag == 1)break;
            }

			if(k == 1) 		{counter += abs(rowVariable - 1) + abs(columnVariable - 1);}
			else if(k == 2) {counter += abs(rowVariable - 1) + abs(columnVariable - 2);}
			else if(k == 3) {counter += abs(rowVariable - 1) + abs(columnVariable - 3);}
			else if(k == 4) {counter += abs(rowVariable - 2) + abs(columnVariable - 1);}
			else if(k == 5) {counter += abs(rowVariable - 2) + abs(columnVariable - 2);}
			else if(k == 6) {counter += abs(rowVariable - 2) + abs(columnVariable - 3);}
			else if(k == 7) {counter += abs(rowVariable - 3) + abs(columnVariable - 1);}
			else if(k == 8) {counter += abs(rowVariable - 3) + abs(columnVariable - 2);}
		}

		return counter;
		
    }

    void EightPuzzle :: initialize()
    {
        int i , j , s = 0;
        int randomPiece;
        int flag_used = 0;
        vector<int> usedNums;

		while(1)
		{
			usedNums.clear();
			flag_used = 0;
			board.resize(5);
	    	for (i = 0; i < 5; ++i){
    	    	board[i].resize(5);
	    	}

        	for (int i = 0; i < board.size(); i++)
        	{
            	for (int j = 0; j < board[i].size(); j++)
            	{
                	board[i][j] = wallS;
            	}
        	}

        	for (int i = 1; i < board.size()-1; i++)
        	{
            	for (int j = 1; j < board[i].size()-1; j++)
            	{
                	randomPiece = rand() % 9 + 1;

                	flag_used = 0;
                	for (int k = 0; k < usedNums.size(); k++)
                	{
                   	 	if (randomPiece == usedNums[k]){
                        	flag_used = 1;
                        	break;
                    	}
                	}
                	if(flag_used == 1){
                    	j--;
                    	continue;
                	}
                	else{
                    	board[i][j] = randomPiece;
                    	usedNums.push_back(randomPiece);
                	} 
            	}
        	}
			s = solvable();
			if (s == 0){
				continue;
			}
			else break;
		}
    }
}