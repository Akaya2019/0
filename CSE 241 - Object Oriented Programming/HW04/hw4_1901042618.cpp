//This is the application file hw4_1901042618.cpp

#include<iostream>
#include<vector>
#include<fstream>
#include"dyearset.h"


int main(int argc, char const *argv[])
{
    using DYearSet::DayOfYearSet;

    vector<DayOfYearSet> activeSets;

    DayOfYearSet A;                             //program takes input : month , day format
    A.add(1,32);                                //program ignores unreal input ex: january 32 , february 29
    A.add(2,29);
    A.add(2,25);
    A.add(3,4);
    A.add(4,7);
    A.add(5,23);
    //cout << "Set A: " << endl << A << endl ;

    DayOfYearSet B(4);                          //testing second constructor
    B.add(3,4);
    B.add(4,7);
    B.add(7,15);
    B.add(8,23);
    //cout << "Set B: " << endl << B << endl ;

    DayOfYearSet C;
    C.add(9,1);
    C.add(10,23);
    C.add(11,14);
    C.add(5,2);
    //cout << "Set C: " << endl << C << endl ;

    DayOfYearSet D;
    D.add(5,3);
    D.add(6,30);
    D.add(7,31);
    D.add(8,28);
    //cout << "Set D: " << endl << D << endl ;

    DayOfYearSet E;
    E.add(12,1);
    E.add(2,12);
    E.add(8,23);
    E.add(7,15);
    //cout << "Set E: " << endl << E << endl ;

    DayOfYearSet unionSet_1 = A + B;                                //testing union sets
    cout << "union set: A + B" << endl;
    cout <<  unionSet_1 << endl << endl;
    cout << "size of this set:"<< unionSet_1.size();                //testing size function
    activeSets.push_back(unionSet_1);

    DayOfYearSet unionSet_2 = C + D;
    cout << "union set: C + D" << endl;
    cout <<  unionSet_2 << endl << endl;
    cout << "size of this set:" << unionSet_2.size();               //testing size function
    activeSets.push_back(unionSet_2);

    A.remove();                                                     //testing remove func
    E.remove();                                                     //testing remove func


    DayOfYearSet intersectionSet_1 = A ^ B;                         //testing intersection sets
    cout << "intersection set: A ^ B" << endl;
    cout << intersectionSet_1 << endl << endl;
    activeSets.push_back(intersectionSet_1);

    DayOfYearSet intersectionSet_2 = E ^ B;
    cout << "intersection set: E ^ B" << endl;
    cout << intersectionSet_2 << endl << endl;
    activeSets.push_back(intersectionSet_2);


    DayOfYearSet differenceSet_1 = A - B;                           //testing difference sets
    cout << "difference set: A-B" << endl;
    cout << differenceSet_1 << endl << endl;
    activeSets.push_back(differenceSet_1);

    DayOfYearSet differenceSet_2 = B - A;
    cout << "difference set: B-A" << endl;
    cout << differenceSet_2 << endl << endl;
    activeSets.push_back(differenceSet_2);


    ofstream file1("DeMorgan_1.txt");
    DayOfYearSet DeMorgan_1 = !(A + B);                             //testing De Morgan rule
    cout << "De Morgan Rule_1 results printed txt " << endl;
    file1 << DeMorgan_1 << endl;
    activeSets.push_back(DeMorgan_1);
    file1.close();

    ofstream file2("DeMorgan_2.txt");
    DayOfYearSet DeMorgan_2 = !(B + C);
    cout << "De Morgan Rule_2 results printed txt " << endl;
    file2 << DeMorgan_2 << endl << endl;
    activeSets.push_back(DeMorgan_2);
    file2.close();

    ofstream file3("DeMorgan_3.txt");
    DayOfYearSet DeMorgan_3 = !(C + D);
    cout << "De Morgan Rule_3 results printed txt " << endl;
    file3 << DeMorgan_3 << endl << endl;
    activeSets.push_back(DeMorgan_3);
    file3.close();

    ofstream file4("DeMorgan_4.txt");
    DayOfYearSet DeMorgan_4 = !(D + E);
    cout << "De Morgan Rule_4 results printed txt " << endl;
    file4 << DeMorgan_4 << endl << endl;
    activeSets.push_back(DeMorgan_4);
    file4.close();

    ofstream file5("DeMorgan_5.txt");
    DayOfYearSet DeMorgan_5 = !(E + A);
    cout << "De Morgan Rule_5 results printed txt " << endl;
    file5 << DeMorgan_5 << endl << endl;
    activeSets.push_back(DeMorgan_5);
    file5.close();   


    // DayOfYearSet complementSet_1 = !A;                                //testing complement sets
    // cout << complementSet_1 << endl << endl;
    // activeSets.push_back(complementSet_1);

    // DayOfYearSet complementSet_2 = !E;
    // cout << complementSet_2 << endl << endl;
    // activeSets.push_back(complementSet_2);

    if (!(E + A) == (!E ^ !A))
    {
        cout << "1" << endl;
    }
    


    cout << endl << "alive objects in all sets : " << endl;
    cout << DayOfYearSet :: totalAliveObj(activeSets) << endl;        //prints tottal DayOfYear objects


    return 0;
}
