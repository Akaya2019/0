//This is the implementation file dyearset.cpp

#include<iostream>
#include<vector>
#include<string>

using namespace std;

#include"dyearset.h"

namespace DYearSet
{
    using a = DayOfYearSet;
    using b = DayOfYearSet::DayOfYear;

    DayOfYearSet::DayOfYear::DayOfYear()                                    //automatically initiates date January 1
                                            :day(1) , month(1)
    {}

    DayOfYearSet::DayOfYear::DayOfYear(int inMonth , int inDay)             //initiates date based on user input
                                            : month(inMonth) , day(inDay)
    {}

    DayOfYearSet::DayOfYearSet()
                                : capacity(10), used(0)
    {
        ptr = new DayOfYear[capacity];
    }

    DayOfYearSet::DayOfYearSet(int size)
                                : capacity(size), used(0)
    {
        ptr = new DayOfYear[capacity];
    }

    DayOfYearSet::DayOfYearSet(const DayOfYearSet& other)
                                :capacity(other.getCapacity()), used(other.getUsed())
    {
        ptr = new DayOfYear[capacity];
        for (int i = 0; i < used; i++)
            ptr[i] = other.ptr[i];
    }

    // DayOfYearSet::DayOfYearSet(vector<DayOfYear>)
    // {

    // }

    ostream& operator<<(ostream &out , const DayOfYearSet& other)
    {
        string months[12] = {"January" , "February" , "March" , "April" , "May" , "June" ,
                             "July" , "August" , "September" , "October" , "November" , "December"};
        for (int i = 0; i < other.getUsed(); i++)
        {
            out << months[other.getPtr()[i].getMonth()-1] << " " ;
            out << other.getPtr()[i].getDay() << endl;
        }

        return out;
    }

    bool operator==(const DayOfYearSet &object1 ,const DayOfYearSet &object2)
    {
        bool ans = true;
        int counter = 0;

        if (object2.getUsed() != object1.getUsed())
        {
            ans = false;
        }
        else
        {
            for (int i = 0; i < object1.used; i++)
            {
                for (int j = 0; j < object2.getUsed(); j++)
                {
                    if (object1.ptr[i].getDay() == object2.ptr[j].getDay() && object1.ptr[i].getMonth() == object2.ptr[j].getMonth())
                    {
                        counter++;
                    }
                }
            }
            if (counter != object1.getUsed())
            {
                ans = false;
            }            
        }

        return ans;
    }

    bool operator!=(const DayOfYearSet &object1 ,const DayOfYearSet &object2)
    {
        return !(object1 == object2);
    }

    void a::add(int month , int day)
    {
        int flag = 0;

        int* daysNum = { new int[12]{31 , 28 , 31 , 30 , 31 , 30 , 31 , 31 , 30 , 31 , 30 , 31} };

        for (int i = 0; i < used; i++)
        {
            if(ptr[i].getDay() == day && ptr[i].getMonth() == month)
            {
                flag = 1;
                break;
            }
        }

        if (day > daysNum[month - 1])
        {
            flag = 1;
        }        

        if (flag != 1)
        {
            if (used >= capacity)
            {
                capacity += 10;

                DayOfYear* temp = new DayOfYear[capacity];
                for (int i = 0; i < used; i++)
                {
                    temp[i] = ptr[i];
                }
                delete [] ptr;
                ptr = temp;
            }

            ptr[used].setDate(month , day); 
            used++;
        }
    
    }

    void DayOfYearSet::remove()
    {
        DayOfYear* temp = new DayOfYear[capacity];
        for (auto i = 0; i < used-1; i++)
        {
            temp[i] = ptr[i];
        }
        delete [] ptr;
        ptr = temp;

        used--;
    }

    DayOfYearSet operator+(const DayOfYearSet &object1 ,const DayOfYearSet &object2)
    {
        int i;
        int flag = 0;

        DayOfYearSet New(object1.getUsed() + object2.getUsed() + 10); 

        for (i = 0; i < object1.getUsed(); i++)
        {
            New.ptr[New.used] = object1.ptr[i];
            New.used++;
        }
        for (i = 0; i < object2.getUsed(); i++)
        {
            for (int j = 0; j < object1.getUsed(); j++)
            {
                if (object2.ptr[i].getMonth() == object1.ptr[j].getMonth() && object2.ptr[i].getDay() == object1.ptr[j].getDay())
                {
                    flag  = 1;
                    break;
                }
            }

            if (flag != 1)
            {
                New.ptr[New.used] = object2.ptr[i];
                New.used++;
            }
            flag = 0;
        }
        
        return New;
    }

    DayOfYearSet operator-(const DayOfYearSet &object1 ,const DayOfYearSet &object2)
    {
        if (object1 == object2){
            cout << "These sets are equal. Try again.." << endl;
        }
        else{ 
            return !object2 ^ object1;
        }    
    }

    DayOfYearSet operator^(const DayOfYearSet &object1 ,const DayOfYearSet &object2)
    {
        int smaller = 0;

        if (object1.used > object2.used){
            smaller = object2.used;
        }
        else{
            smaller = object1.used;
        }

        DayOfYearSet New(smaller);

        for (auto i = 0; i < object1.used; i++)
        {
            for (auto j = 0; j < object2.used; j++)
            {
                if (object1.ptr[i].getMonth() == object2.ptr[j].getMonth() && object1.ptr[i].getDay() == object2.ptr[j].getDay())
                {
                    New.ptr[New.used].setDate( object1.ptr[i].getMonth() , object1.ptr[i].getDay() );
                    New.used++;
                    break;
                }
            }
        }
          
        return New;  
    }

    DayOfYearSet operator!(const DayOfYearSet &object1)
    {
        int flag = 0;
        int i , j ;
        decltype(i) k;

        int* daysNum = { new int[12]{31 , 28 , 31 , 30 , 31 , 30 , 31 , 31 , 30 , 31 , 30 , 31} };

        DayOfYearSet New(365 - object1.getUsed());

        for (i = 1; i <= 12; i++)
        {
            for (j = 1; j <= daysNum[i-1]; j++)
            {
                flag = 0;
                for (k = 0; k < object1.getUsed(); k++)
                {
                    if (object1.ptr[k].getMonth() == i && object1.ptr[k].getDay() == j)
                    {
                        flag = 1;
                    }
                    if(flag == 1)break;
                }

                if (flag != 1)
                {
                    New.ptr[New.used].setDate(i , j);
                    New.used++; 
                }
            }
        }

        return New;
    }

    DayOfYearSet::DayOfYear& DayOfYearSet::operator[](int index)
    {
        if (index >= used)
        {
            cout << "illegal index in DayOfYearSet." << endl;
            exit(0);
        }
        return ptr[index];
    }

    int DayOfYearSet::totalAliveObj(const vector<DayOfYearSet> &activeSets)
    {
        int counter = 0;
        for (auto i = 0; i < activeSets.size(); i++)
        {
            counter = counter + activeSets[i].getUsed();
        }
        
        return counter;
    }

    DayOfYearSet::~DayOfYearSet()  //destructor
    {
        delete [] ptr;
    }
}



