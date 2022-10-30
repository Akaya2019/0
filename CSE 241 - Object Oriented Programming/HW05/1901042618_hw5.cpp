//This is the application file hw5_1901042618.cpp

#include<iostream>
#include<vector>
#include<fstream>
#include<ctime>

#include"boardgame2d.h"
#include"PegSolitaire.h"
#include"EightPuzzle.h"
#include"Klotski.h"


using namespace std;
using namespace boardgame2d;

int main()
{
    srand (time(NULL)); //use of random number generator

    vector< BoardGame2D * > boardVec (6);
    PegSolitaire A;
    PegSolitaire B;
    EightPuzzle C;
    EightPuzzle D;
    Klotski E;
    Klotski F;    

    boardVec[0] = &A;
    boardVec[1] = &B;
    boardVec[2] = &C;
    boardVec[3] = &D;
    boardVec[4] = &E;
    boardVec[5] = &F;

    BoardGame2D :: playVector(boardVec);

    return 0;
}