//This is the header file dyearset.h
#ifndef DYEARSET_H
#define DYEARSET_H

#include<iostream>
#include<vector>
using namespace std;

namespace DYearSet
{
    class DayOfYearSet
    {
    public:

        class DayOfYear              //inner class
        {
        public:
            DayOfYear();                                    //automatically initiates date January 1
            DayOfYear(int inMonth , int inDay);             //initiates date based on user input

            int getDay(){return day;}                       //return day number
            int getMonth(){return month;}                   //return month number

            void setDay(int inDay) {day = inDay;}           //set day to inDay which is input
            void setMonth(int inMonth) {month = inMonth;}   //set month to inMonth which is input
            void setDate(int inMonth , int inDay) {month = inMonth; day = inDay;}   //set day to inMonth and inDay at the same time

        private:
            int day;
            int month;
        };

        DayOfYearSet();                                     //automatically initiates capacity to 10
        DayOfYearSet(int size);                             //initiates capacity to size which is specified by user
        DayOfYearSet(const DayOfYearSet& obj);              //copy constructor
        //DayOfYearSet(vector<DayOfYear>);

        DayOfYear* getPtr()const {return ptr;}              //returns ptr which is specifying a dynamic array
        int getCapacity()const {return capacity;}           //returns capacity 
        int getUsed()const {return used;}                   //returns used slots

        friend ostream& operator <<(ostream &out , const DayOfYearSet& other);              
        friend bool operator ==(const DayOfYearSet &object1 ,const DayOfYearSet &object2);
        friend bool operator !=(const DayOfYearSet &object1 ,const DayOfYearSet &object2);

        void add(int month , int day);      //adds an element to the set
        void remove();   //removes an element from the set
        const int size() {return used;}

        friend DayOfYearSet operator+(const DayOfYearSet &object1 ,const DayOfYearSet &object2);        //returns the union set
        friend DayOfYearSet operator-(const DayOfYearSet &object1 ,const DayOfYearSet &object2);        //returns the difference set.
        friend DayOfYearSet operator^(const DayOfYearSet &object1 ,const DayOfYearSet &object2);        //returns the intersection set.
        friend DayOfYearSet operator!(const DayOfYearSet &object1);                                     //returns the complement set.
        DayOfYear& operator[](int index);

        static int totalAliveObj(const vector<DayOfYearSet> &activeSets);                               //returns total DayOfYear objects

        ~DayOfYearSet();        //destructor

    private:
        DayOfYear * ptr;    //for an array of doubles  
        int capacity;       //for the size of the array
        int used;           //for the number of array position currrently in use
    };

    
}// DYearSet



#endif //DYEARSET_H