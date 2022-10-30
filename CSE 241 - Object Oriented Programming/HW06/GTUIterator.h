//This is the header file GTUIterator.h
#ifndef GTUITERATOR_H
#define GTUITERATOR_H

#include <memory>
#include "Iterator.h"

using namespace std;

namespace GTU
{
    template<class T>
    class GTUIterator : public Iterator<T>
    {
    public:
        GTUIterator();
        GTUIterator(shared_ptr< GNode<T> > adress);   

        T& operator*();                     //dereference operator
        T* operator->();                    //arrow operator
        GTUIterator<T> operator++();        //preincrement
        GTUIterator<T> operator++(int);     //postincrement
        GTUIterator<T> operator--();        //predecrement 
        GTUIterator<T> operator--(int);     //postincrement

        bool operator==(const GTUIterator<T>& other)const;      //equal
        bool operator!=(const GTUIterator<T>& other)const;      //not equal
        GTUIterator<T> operator=(const GTUIterator<T>& other);  //assignment operator

    };

    template<class T>
    GTUIterator<T> :: GTUIterator()
                                    :Iterator<T>()
    {} 

    template<class T>
    GTUIterator<T> :: GTUIterator(shared_ptr< GNode<T> > adress)
                                                                :Iterator<T>(adress)
    {}   

    template<class T>
    T& GTUIterator<T> :: operator*()
    {
        return this->iptr->idata;
    }

    template<class T>
    T* GTUIterator<T> :: operator->()
    {
        return &(this->iptr->idata);
    }

    template<class T>
    GTUIterator<T> GTUIterator<T> :: operator++()
    {
        this->iptr = this->iptr->inext;
        return *this;
    }

    template<class T>
    GTUIterator<T> GTUIterator<T> :: operator++(int)
    {
        GTUIterator<T> tmp = (*this);
        this->iptr = (this->iptr->inext);
        return tmp;
    }

    template<class T>
    GTUIterator<T> GTUIterator<T> :: operator--()
    {
        this->iptr = this->iptr->iprev;
        return *this;
    }

    template<class T>
    GTUIterator<T> GTUIterator<T> :: operator--(int)
    {
        GTUIterator<T> tmp = (*this);
        this->iptr = this->iptr->iprev;
        return tmp;
    }

    template<class T>
    bool GTUIterator<T> :: operator==(const GTUIterator<T>& other)const
    {
        return (this->iptr == other.iptr);
    }

    template<class T>
    bool GTUIterator<T> :: operator!=(const GTUIterator<T>& other)const
    {
        return !(this->iptr == other.iptr);
    }

    template<class T>
    GTUIterator<T> GTUIterator<T> :: operator=(const GTUIterator<T>& other)
    {
        this->iptr = nullptr;
        *this = other;
        return *this;
    }

}   //GTU 

#endif // GTUITERATOR_H
