//This is the implementation file boardgame2d.cpp

#include<iostream>
#include<vector>
#include<string>
#include<ctime>

#include"boardgame2d.h"

using namespace std;

namespace boardgame2d
{
    BoardGame2D :: BoardGame2D()
    {
        initialize();
    }

    int BoardGame2D :: playUser()
    {
        string move;
        cout << endl;
        cout << "Input Examples ; " << endl 
             << "PegSolitaire => 2B UP" << endl
             << "EightPuzzle => 2 UP" << endl
             << "Klotski => 2 UP" << endl << endl;
        
        do
        {
            do
            {
                cout << "please enter your move: ";
                getline(cin >> std::ws, move);    				//ws consumes whitespace from an input stream

                if (!errorCheck(move))
                {
                    cerr << "Try Again.." << endl;
                }
                
            } while (!errorCheck(move));

            playUser(move);          //polymorphic call

        } while (endGame());
        return 0;
    }

    void BoardGame2D :: playAutoAll()
    {
        do
        {
            playAuto();
        } while (endGame());
    }

    // ostream& operator <<(ostream &out , const BoardGame2D& other)
    // {
    //     for (int i = 0; i < other.getVector().size(); i++)
    //     {
    //         for (int j = 0; j < count; j++)
    //         {
    //             /* code */
    //         }
            
    //     }

    //     return out;    
    // }

    void BoardGame2D :: initialize()
    {
	    for (int i = 0; i < board.size(); ++i){
    	    board[i].resize(0);
	    }
        board.resize(0);
    }

    void BoardGame2D :: playVector(vector< BoardGame2D * > boardVec)
    {
        for (int i = 0; i < boardVec.size(); i++)
        {
            cout << endl << "Loading to the next game.." << endl;
            boardVec[i]->guiding();
        }
    }

    void BoardGame2D :: guiding()
    {
		string str_opt_gameType;  //options for game type takes string because of validity of the user inputs
		char opt_gameType;

		string str;

		print();

		while (1)
		{
    		do
			{	
				cout << endl << endl;
				cout << "1-> User plays the game until it is over" << endl
					 << "2-> User plays one move" << endl
					 << "3-> Computer plays the game until it is over" << endl
					 << "4-> Computer plays one move" << endl
					 << "5-> Exit " << endl
					 << "Please select the Game type: ";
				cin >> str_opt_gameType;

				if ((str_opt_gameType.size() != 1) || str_opt_gameType[0] < '1' || str_opt_gameType[0] > '5')
					cerr << endl << "TRY AGAIN.." << endl;

			} while ((str_opt_gameType.size() != 1) || str_opt_gameType[0] < '1' || str_opt_gameType[0] > '5');

			opt_gameType = str_opt_gameType[0];

			switch(opt_gameType)
			{
				case '1':		//Human player game
					BoardGame2D :: playUser();
					break;

				case '2':		//User plays one move
			    	do
            		{
                		cout << "please enter your move: ";
                		getline(cin >> std::ws, str);    				//ws consumes whitespace from an input stream

                		if (!errorCheck(str))
                		{
                    		cerr << "Try Again.." << endl;
                		}
                
            		} while (!errorCheck(str));

            		playUser(str);
					break;

				case '3':		//Computer plays until game is over
					playAutoAll();
					break;

				case '4':		//Computer plays one move
					playAuto();
					break;		
			}

			if (opt_gameType == '5' || !endGame()){
				break;
			}				
		}
    }      
                         
} //boardgame2d