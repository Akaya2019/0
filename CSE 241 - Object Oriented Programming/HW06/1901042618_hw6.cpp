
#include <iostream>
#include <vector>
#include <set>

#include "IterableBase.h"
#include "Iterable.h"
#include "Iterator.h"
#include "GTUIterator.h"
#include "GTUIteratorConst.h"
#include "GTUVector.h"
#include "GTUSet.h"
#include "GTUArray.h"
#include "Exception.h"

using namespace std;
using namespace GTU;

int main()
{

    cout << "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~TESTING GTUVector Class~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"<< endl;
    cout << "Let open an object : "<< endl;

    GTUVector<int> A(3);
    cout << "when we open it class' constructor fills it with zeros :"<< endl << endl;

    for (int i = 0; i < A.size(); i++){
        cout << "A["  <<  i  << "] = " << A[i] << endl;
    }
    cout << endl;
    
    cout << "we put some data inside the GTUVector : "<< endl << endl;
    A[0] = 5;
    A[1] = 783465;
    A[2] = 43214;

    for (int i = 0; i < A.size(); i++){
        cout << "A["  <<  i  << "] = " << A[i] << endl;
    }
    cout << endl;

    cout << "testing push_back and add functions : "<< endl ;
    A.push_back(50);
    A.push_back(60);
    A.add(55);
    for (int i = 0; i < A.size(); i++){
        cout << "A["  <<  i  << "] = " << A[i] << endl;
    }
    cout << endl;

    cout << "testing pop_back functions last 2 slots will be cleared soon :"<< endl ;
    A.pop_back();
    A.pop_back();
    for (int i = 0; i < A.size(); i++){
        cout << "A["  <<  i  << "] = " << A[i] << endl;
    }
    cout << endl;
 
    cout << "testing erase() function erasing beginning of container : "<< endl ;
    A.erase (A.begin());
    for (int i = 0; i < A.size(); i++){
        cout << "A["  <<  i  << "] = " << A[i] << endl;
    }
    cout << endl;

    cout << "testing resize() function resizing with 5 : "<< endl ;
    A.resize(5);
    for (int i = 0; i < A.size(); i++){
        cout << "A["  <<  i  << "] = " << A[i] << endl;
    }
    cout << endl;

    cout << "testing resize() function again : resizing with 3 : "<< endl ;
    A.resize(3);
    for (int i = 0; i < A.size(); i++){
        cout << "A["  <<  i  << "] = " << A[i] << endl;
    }
    cout << endl;

    // try
    // {
    //     cout << A.at(0) << endl;
    //     cout << A.at(2) << endl;
    //     cout << A.at(4) << endl;
    // }
    // catch(out_of_rangeException& exception)                          //THIS part is exception handling part
    // {
    //     cout << "Exception occured: " 
    //         << exception.what();
    // }

    cout << "testing front() and back() function :  "<< endl ;
    cout << endl;
    cout << A.front() << endl;
    cout << A.back() << endl;



    cout << "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~TESTING GTUArray Class~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"<< endl;

    cout << "Let open an object : "<< endl;

    GTUArray<int , 7> array1;           //second parameter is size of array

    cout << "when we open it , class' constructor sizing it with 7 and fill it with zeros at the beginning : "<< endl << endl;

    for (int i = 0; i < array1.size(); i++){
        cout << "array1["  <<  i  << "] = " << array1[i] << endl;
    }
    cout << endl;
    
    cout << "we put some data inside the GTUArray : "<< endl << endl;

    array1[0] = 5;
    array1[1] = 6;
    array1[2] = 7;
    array1[3] = 12;
    array1[4] = 34;
    array1[5] = 43;
    array1[6] = 764;

    for (int i = 0; i < array1.size(); i++){
        cout << "array1["  <<  i  << "] = " << array1[i] << endl;
    }
    cout << endl;

    cout << "testing fill() function : it fills all datas with given value"<< endl ;
    array1.fill(7);
    for (int i = 0; i < array1.size(); i++){
        cout << "array1["  <<  i  << "] = " << array1[i] << endl;
    }
    cout << endl;
 
    cout << "testing erase() function erasing beginning of container : "<< endl ;
    array1.erase (array1.begin());
    for (int i = 0; i < array1.size(); i++){
        cout << "array1["  <<  i  << "] = " << array1[i] << endl;
    }
    cout << endl;


    cout << "testing front() and back() function :  "<< endl ;
    cout << endl;
    cout << array1.front() << endl;
    cout << array1.back() << endl;


    cout << "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~TESTING GTUSet Class~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~"<< endl;

    cout << "Let open an object : "<< endl;
    GTUSet<int> set1(1);
    cout << "when we open it , class' constructor fill it with zeros at the beginning : "<< endl << endl;

    cout << "we put some data inside the GTUSet : "<< endl << endl;
    set1.add(1);
    set1.add(2);
    set1.add(3);
    set1.add(4);
    set1.add(5);

    Iterable<int> *set = &set1;                     //we also test here to dereference operator of Iteraror classes
    GTUIterator<int> yaz = set->begin();            //also test begin() function

    for (int i = 0; i < set1.size(); i++){
        cout << i+1 << ". element = " <<  *yaz << endl;
        yaz++;
    }

    cout << "testing deleteF() function erasing value that stated  : "<< endl ;
    cout << "this function also uses search function to find adress of given data" << endl;

    set1.deleteF(4);

    for (int i = 0; i < set1.size(); i++){
        cout << i+1 << ". element = " <<  *yaz << endl;
        yaz++;
    }           

    GTUSet<int> set2(1);

    set2.add(6);
    set2.add(7);
    set2.add(8);
    set2.add(9);

    // GTUSet<int> set3  = set2.unionSet(set1);

    // Iterable<int> *set = &set3;
    // GTUIterator<int> yaz = set->begin();

    // for (int i = 0; i < set3.size(); i++){
    //     cout << *yaz << endl;
    // }
    // cout << endl;    

    return 0;
}