//This is the implementation file EightPuzzle.cpp

#include<iostream>
#include<vector>
#include<string>
#include<ctime>
#include<cstdlib>

#include"Klotski.h"

using namespace std;


namespace boardgame2d
{
    Klotski :: Klotski()
    {
        initialize();
    }

    Klotski& Klotski :: operator=(const Klotski& other)
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

    void Klotski :: playUser(const string &move)
    {
        oneMove(move);
        print();
    }

    void Klotski :: playAuto()
    {
        vector<int>num = { 0 , 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9};
        vector<string>way = {"RIGHT" , "LEFT" , "UP" , "DOWN"};

        string move_cmp;  //for computer game
        int i , j;
        int rondomNum , rondomColumn , rondomWay;

        Klotski temp;
        temp = *this;

        while(1)
        {
            while(1)
            {
                move_cmp.resize(0);
                rondomNum = rand() % 10 + 1;         //generates a random number for the number
                move_cmp.push_back(num[rondomNum-1] + '0');

                move_cmp.push_back(' ');

                rondomWay = rand() % 4 + 1;                     //generates a random number for the way number
                move_cmp = move_cmp + way[rondomWay-1];

                if (temp.oneMove(move_cmp) == 1){
                    break;
                }
            }    

            if (temp.boardScore() < boardScore()){
                *this = temp;
                break;
            }
        }
        
        print();
        cout << "move made: " << move_cmp << endl;      
    }


    int Klotski :: oneMove(const string &move)
    {
        int ans = 0; 
        int i , j;
        int rowVariable , columnVariable;
        int flag = 0;
        int chosenNum = (move[0] - '0');

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
            if (chosenNum == 1 || chosenNum == 3 || chosenNum == 4 || chosenNum == 6){
                swap(board[rowVariable][columnVariable] , board[rowVariable][columnVariable+1]);
                swap(board[rowVariable+1][columnVariable] , board[rowVariable+1][columnVariable+1]);
                ans = 1;
            }
            else if(chosenNum == 7 || chosenNum == 8 || chosenNum == 9 || chosenNum == 0){
                swap(board[rowVariable][columnVariable] , board[rowVariable][columnVariable+1]);
                ans = 1;
            }
            else if(chosenNum == 2){
                swap(board[rowVariable][columnVariable+1] , board[rowVariable][columnVariable+2]);
                swap(board[rowVariable][columnVariable] , board[rowVariable][columnVariable+1]);
                swap(board[rowVariable+1][columnVariable+1] , board[rowVariable+1][columnVariable+2]);
                swap(board[rowVariable+1][columnVariable] , board[rowVariable+1][columnVariable+1]);
                ans = 1;
            }
            else if(chosenNum == 5){
                swap(board[rowVariable][columnVariable+1] , board[rowVariable][columnVariable+2]);
                swap(board[rowVariable][columnVariable] , board[rowVariable][columnVariable+1]);
                ans = 1;
            }
        }
    
        else if((move.substr(2,move.length()-2)) == "LEFT")
        {
            if (chosenNum == 1 || chosenNum == 3 || chosenNum == 4 || chosenNum == 6){
                swap(board[rowVariable][columnVariable] , board[rowVariable][columnVariable-1]);
                swap(board[rowVariable+1][columnVariable] , board[rowVariable+1][columnVariable-1]);
                ans = 1;
            }
            else if(chosenNum == 7 || chosenNum == 8 || chosenNum == 9 || chosenNum == 0){
                swap(board[rowVariable][columnVariable] , board[rowVariable][columnVariable-1]);
                ans = 1;
            }
            else if(chosenNum == 2){
                swap(board[rowVariable][columnVariable] , board[rowVariable][columnVariable-1]);
                swap(board[rowVariable][columnVariable+1] , board[rowVariable][columnVariable]);
                swap(board[rowVariable+1][columnVariable] , board[rowVariable+1][columnVariable-1]);
                swap(board[rowVariable+1][columnVariable+1] , board[rowVariable+1][columnVariable]);
                ans = 1;
            }
            else if(chosenNum == 5){
                swap(board[rowVariable][columnVariable] , board[rowVariable][columnVariable-1]);
                swap(board[rowVariable][columnVariable+1] , board[rowVariable][columnVariable]);
                ans = 1;
            }
        }

        else if((move.substr(2,move.length()-2)) == "UP")
        {
            if (chosenNum == 1 || chosenNum == 3 || chosenNum == 4 || chosenNum == 6){
                swap(board[rowVariable][columnVariable] , board[rowVariable-1][columnVariable]);
                swap(board[rowVariable+1][columnVariable] , board[rowVariable][columnVariable]);
                ans = 1;
            }
            else if(chosenNum == 7 || chosenNum == 8 || chosenNum == 9 || chosenNum == 0){
                swap(board[rowVariable][columnVariable] , board[rowVariable-1][columnVariable]);
                ans = 1;
            }
            else if(chosenNum == 2){
                swap(board[rowVariable][columnVariable] , board[rowVariable-1][columnVariable]);
                swap(board[rowVariable][columnVariable+1] , board[rowVariable-1][columnVariable+1]);
                swap(board[rowVariable+1][columnVariable] , board[rowVariable][columnVariable]);
                swap(board[rowVariable+1][columnVariable+1] , board[rowVariable][columnVariable+1]);
                ans = 1;
            }
            else if(chosenNum == 5){
                swap(board[rowVariable][columnVariable] , board[rowVariable-1][columnVariable]);
                swap(board[rowVariable][columnVariable+1] , board[rowVariable-1][columnVariable+1]);
                ans = 1;
            }
        }

        else if((move.substr(2,move.length()-2)) == "DOWN")
        {
            if (chosenNum == 1 || chosenNum == 3 || chosenNum == 4 || chosenNum == 6){
                swap(board[rowVariable+1][columnVariable] , board[rowVariable+2][columnVariable]);
                swap(board[rowVariable][columnVariable] , board[rowVariable+1][columnVariable]);
                ans = 1;
            }
            else if(chosenNum == 7 || chosenNum == 8 || chosenNum == 9 || chosenNum == 0){
                swap(board[rowVariable][columnVariable] , board[rowVariable+1][columnVariable]);
                ans = 1;
            }
            else if(chosenNum == 2){
                swap(board[rowVariable+1][columnVariable] , board[rowVariable+2][columnVariable]);
                swap(board[rowVariable+1][columnVariable+1] , board[rowVariable+2][columnVariable+1]);
                swap(board[rowVariable][columnVariable] , board[rowVariable+1][columnVariable]);
                swap(board[rowVariable][columnVariable+1] , board[rowVariable+1][columnVariable+1]);
                ans = 1;
            }
            else if(chosenNum == 5){
                swap(board[rowVariable][columnVariable] , board[rowVariable+1][columnVariable]);
                swap(board[rowVariable][columnVariable+1] , board[rowVariable+1][columnVariable+1]);
                ans = 1;
            }
        }
        return ans;
    }

    int Klotski :: errorCheck(const string &move)
    {
        int i , j;
        int ans = 1;
        int flag = 0;
        
        vector<int>num = { 0, 1 , 2 , 3 , 4 , 5 , 6 , 7 , 8 , 9 };
        vector<string>way = {"RIGHT" , "LEFT" , "UP" , "DOWN"};

        int rowVariable;      //row number of chosen cell
        int columnVariable;   //column number of chosen cell

        int is_firstParameter = 0;
        int is_secondParameter = 0;
        int is_thirdParameter = 0;


        for (int k = 0; k < num.size(); ++k){
            if (move[0] == num[k] + '0'){
                is_firstParameter = 1;
                break;
            }   
        }

        if (is_firstParameter == 1)
        {
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
        }
        
        if (move[1] == ' '){is_secondParameter = 1;}
            
        for (i = 0; i < 4; ++i){
            if (move.substr(2,move.length()-2) == way[i]){
                is_thirdParameter = 1;
            }
        }

        if ( !is_firstParameter || !is_secondParameter || !is_thirdParameter || !is_moveValid( move , rowVariable , columnVariable )){
            ans = 0;
        }

        return ans;
    }

    int Klotski :: is_moveValid(const string& move , int rondomRow , int rondomColumn )
    {
        int ans = 0 , flag = 0;
        int is_chosenPiece = 0;
        int is_targetEmpty = 0;
        int chosenNum = (move[0] - '0');

        int rowVariable;      //row number of chosen cell
        int columnVariable;   //column number of chosen cell

        if (board[rondomRow][rondomColumn] != -2 && board[rondomRow][rondomColumn] != -1){
            is_chosenPiece = 1;
        }

        if (is_chosenPiece == 1)                        //finding cordinates of chosenNum
        {
            for (int i = 1; i < board.size()-1; i++){
                for (int j = 1; j < board[i].size()-1; j++)
                {
                    if((chosenNum) == board[i][j]){
                        rowVariable = i;
                        columnVariable = j;
                        flag = 1;
                        break;
                    }
                }
                if(flag == 1)break;
            }
        }
        
        if((move.substr(2,move.length()-2)) == "RIGHT")
        {
            if (chosenNum == 1 || chosenNum == 3 || chosenNum == 4 || chosenNum == 6){
                if((board[rowVariable][columnVariable+1] == -2 && board[rowVariable+1][columnVariable+1] == -2)){is_targetEmpty = 1;}
            }
            else if(chosenNum == 7 || chosenNum == 8 || chosenNum == 9 || chosenNum == 0){
                if(board[rowVariable][columnVariable+1] == -2){is_targetEmpty = 1;}
            }
            else if(chosenNum == 2){
                if((board[rowVariable][columnVariable+2] == -2 && board[rowVariable+1][columnVariable+2] == -2)){is_targetEmpty = 1;}
            }
            else if(chosenNum == 5){
                if((board[rowVariable][columnVariable+2] == -2)){is_targetEmpty = 1;}
            }
        }
    
        else if((move.substr(2,move.length()-2)) == "LEFT")
        {
            if (chosenNum == 1 || chosenNum == 3 || chosenNum == 4 || chosenNum == 6){
                if((board[rowVariable][columnVariable-1] == -2 && board[rowVariable+1][columnVariable-1] == -2)){is_targetEmpty = 1;}
            }
            else if(chosenNum == 7 || chosenNum == 8 || chosenNum == 9 || chosenNum == 0){
                if(board[rowVariable][columnVariable-1] == -2){is_targetEmpty = 1;}
            }
            else if(chosenNum == 2){
                if((board[rowVariable][columnVariable-1] == -2 && board[rowVariable+1][columnVariable-1] == -2)){is_targetEmpty = 1;}
            }
            else if(chosenNum == 5){
                if((board[rowVariable][columnVariable-1] == -2)){is_targetEmpty = 1;}
            }
        }

        else if((move.substr(2,move.length()-2)) == "UP")
        {
            if (chosenNum == 1 || chosenNum == 3 || chosenNum == 4 || chosenNum == 6){
                if(board[rowVariable-1][columnVariable] == -2){is_targetEmpty = 1;}
            }
            else if(chosenNum == 7 || chosenNum == 8 || chosenNum == 9 || chosenNum == 0){
                if(board[rowVariable-1][columnVariable] == -2){is_targetEmpty = 1;}
            }
            else if(chosenNum == 2){
                if((board[rowVariable-1][columnVariable] == -2 && board[rowVariable-1][columnVariable+1] == -2)){is_targetEmpty = 1;}
            }
            else if(chosenNum == 5){
                if((board[rowVariable-1][columnVariable] == -2 && board[rowVariable-1][columnVariable+1] == -2)){is_targetEmpty = 1;}
            }
        }

        else if((move.substr(2,move.length()-2)) == "DOWN")
        {
            if (chosenNum == 1 || chosenNum == 3 || chosenNum == 4 || chosenNum == 6){
                if(board[rowVariable+2][columnVariable] == -2){is_targetEmpty = 1;}
            }
            else if(chosenNum == 7 || chosenNum == 8 || chosenNum == 9 || chosenNum == 0){
                if(board[rowVariable+1][columnVariable] == -2){is_targetEmpty = 1;}
            }
            else if(chosenNum == 2){
                if((board[rowVariable+2][columnVariable] == -2 && board[rowVariable+2][columnVariable+1] == -2)){is_targetEmpty = 1;}
            }
            else if(chosenNum == 5){
                if((board[rowVariable+1][columnVariable] == -2 && board[rowVariable+1][columnVariable+1] == -2)){is_targetEmpty = 1;}
            }
        }

        if ((is_chosenPiece && is_targetEmpty) == 1){
            ans = 1;
        }
        else{
            ans = 0;
        }   

        return ans;
    }

    bool Klotski :: endGame()
    {
        bool ans = 0;

        if (boardScore() == 0)
        {
            ans = 0;
        }
        else
            ans = 1;

        return ans;
    }   

    void Klotski :: print()
    {
        int i = 0 , j = 0;
        int counter = 1;

        cout << endl;
        cout << " ---1--2--3--4---" << endl;
        cout << " ----------------" << endl;

        for (i = 1; i < board.size()-1; i++)
        {
            cout << counter << "| ";
            counter++;
            for (j = 1; j < board[0].size()-1; j++) 
            {
                switch (board[i][j])
                {   
                case-2 : cout << "   "; break;    
                case 1 : cout << "\033[1;31m 1 \033[0m"; break;
                case 2 : cout << "\033[1;32m 2 \033[0m"; break;
                case 3 : cout << "\033[1;33m 3 \033[0m"; break;
                case 4 : cout << "\033[1;34m 4 \033[0m"; break;
                case 5 : cout << "\033[1;35m 5 \033[0m"; break;
                case 6 : cout << "\033[1;36m 6 \033[0m"; break;
                case 7 : cout << "\033[1;30m 7 \033[0m"; break;
                case 8 : cout << "\033[1;31m 8 \033[0m"; break;
                case 9 : cout << "\033[1;32m 9 \033[0m"; break;
                case 0 : cout << "\033[1;33m 0 \033[0m"; break;
                }
            }
            cout << " |" << endl;              
        }
        cout << " -----      -----";
        cout << endl;
        cout << "SCORE : " << boardScore() << endl;
    }

    int Klotski :: boardScore()
    {
        int rowVariable , columnVariable;
        int flag = 0;
        int score = 0;

        for (int i = 1; i < board.size()-1; i++){
            for (int j = 1; j < board[i].size()-1; j++)
            {
                if(2 == board[i][j]){
                    rowVariable = i;
                    columnVariable = j;
                    flag = 1;
                    break;
                }
            }
            if(flag == 1)break;
        }

        score += abs(rowVariable - 4) + abs(columnVariable - 2);

        return score;       
    }

    void Klotski :: initialize()
    {
        int i , j;
        board.resize(7);
        for (i = 0; i < 7; ++i){
            board[i].resize(6);
        }

        for (int i = 0; i < board.size(); i++)
        {
            for (int j = 0; j < board[i].size(); j++)
            {
                board[i][j] = -1;
            }
        }

        board[5][2] = -2 , board[5][3] = -2;
        board[1][1] = 1 , board[2][1] = 1;
        board[1][2] = 2 , board[1][3] = 2 , board[2][2] = 2 , board[2][3] = 2;
        board[1][4] = 3 , board[2][4] = 3;
        board[3][1] = 4 , board[4][1] = 4;
        board[3][2] = 5 , board[3][3] = 5;
        board[3][4] = 6 , board[4][4] = 6;
        board[4][2] = 7 , board[4][3] = 8 , board[5][1] = 9 , board[5][4] = 0;

    }
}