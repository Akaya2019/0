//This is the header file boardgame2d.h
#ifndef BOARDGAME2D_H
#define BOARDGAME2D_H

#include<iostream>
#include<vector>
using namespace std;

namespace boardgame2d
{
    class BoardGame2D
    {
    public:

        BoardGame2D();

        vector< vector<int> > getBoard() {return board;}

        virtual void playUser(const string &move) = 0;     //takes a string as a parameter and plays the game accordingly.
        virtual int playUser() final;                      //it takes a string from the user for the next move in a loop and plays the game until it is over.
        virtual void playAuto() = 0;                       //plays the game by the computer for one move.
        virtual void playAutoAll() final;                  //It plays the game until it is over.
        virtual int oneMove(const string &move) = 0;       //it plays just one move.

        virtual int errorCheck(const string &move) = 0;
        virtual int is_moveValid(const string& move , int rondomRow , int rondomColumn ) = 0;

        virtual void print() = 0;
        friend ostream& operator <<(ostream &out , const BoardGame2D& other);

        virtual bool endGame() = 0;                         //returns true if the game is ended.

        virtual int boardScore() = 0;                       //returns a positive integer that indicates the goodness of the current board.
        virtual void initialize();                          //initializes the board.

        void guiding();
        static void playVector(vector< BoardGame2D * > boardVec);    //plays all the games in the vector until they end.

    protected:
        vector< vector<int> > board;
    };

    
}// boardgame2d



#endif //BOARDGAME2D_H