//This is the header file PegSolitaire.h
#ifndef PEGSOLITAIRE_H
#define PEGSOLITAIRE_H

#include<iostream>
#include<vector>

#include "boardgame2d.h"

using namespace std;

namespace boardgame2d
{
    enum cellState
    {
	    empty = 0,
	    peg ,
	    wall 
    };

    class PegSolitaire : public BoardGame2D
    {
    public:

        PegSolitaire();

        virtual void playUser(const string &move);      //takes a string as a parameter and plays the game accordingly.
        virtual void playAuto();                        //plays the game by the computer for one move.
        virtual int oneMove(const string &move);        //it plays just one move.

        virtual int errorCheck(const string &move);     //checks the input errors.
        virtual int is_moveValid(const string& move , int rondomRow , int rondomColumn );   //checks whether input is valid or not

        virtual void print();                           //prints the board.

        virtual bool endGame();                         //controls is game is ended
        virtual int boardScore();                       //returns score of current board
        virtual void initialize();                      //initializes the board

    };

}// boardgame2d


#endif //PEGSOLITAIRE_H