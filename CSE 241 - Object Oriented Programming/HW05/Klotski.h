//This is the header file Klotski.h

#ifndef KLOTSKI_H
#define KLOTSKI_H

#include<iostream>
#include<vector>

#include "boardgame2d.h"

using namespace std;

namespace boardgame2d
{

    class Klotski : public BoardGame2D
    {
    public:

        Klotski();      //constructor

        Klotski& operator=(const Klotski& other);      //assignment operator overload

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

#endif //KLOTSKI_H