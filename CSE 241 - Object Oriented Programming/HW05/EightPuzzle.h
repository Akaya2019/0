//This is the header file EightPuzzle.h

#ifndef EIGHTPUZZLE_H
#define EIGHTPUZZLE_H

#include<iostream>
#include<vector>

#include "boardgame2d.h"

using namespace std;

namespace boardgame2d
{
    enum slotState
    {
	    wallS = 0
    };

    class EightPuzzle : public BoardGame2D
    {
    public:

        EightPuzzle();

        EightPuzzle& operator=(const EightPuzzle& other);   //assignment operator overload

        int solvable();

        virtual void playUser(const string &move);     //takes a string as a parameter and plays the game accordingly.
        virtual void playAuto();
        virtual int oneMove(const string &move);

        virtual int errorCheck(const string &move);
        virtual int is_moveValid(const string& move , int rondomRow , int rondomColumn );

        virtual void print();

        virtual bool endGame();
        virtual int boardScore();
        virtual void initialize();
    };

}

#endif //EIGHTPUZZLE.H