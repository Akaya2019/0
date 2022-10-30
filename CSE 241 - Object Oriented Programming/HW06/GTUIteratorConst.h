//This is the header file GTUIteratorConst.h
#ifndef GTUITERATORCONST_H
#define GTUITERATORCONST_H

#include <memory>
#include "Iterator.h"

using namespace std;

namespace GTU
{
    template<class T>
    class GTUIteratorConst : public Iterator<T>
    {
    public:
        GTUIteratorConst();
        GTUIteratorConst(shared_ptr< GNode<T> > adress);

        const T& operator*();
        const T* operator->();
        GTUIteratorConst<T> operator++();
        GTUIteratorConst<T> operator++(int);
        GTUIteratorConst<T> operator--();
        GTUIteratorConst<T> operator--(int);

        GTUIteratorConst<T> operator+(int value);        //+ operator overload
        GTUIteratorConst<T> operator-(int value);        //- operator overload

        bool operator==(const GTUIteratorConst<T>& other)const;
        bool operator!=(const GTUIteratorConst<T>& other)const;
        GTUIteratorConst<T> operator=(const GTUIteratorConst<T>& other);

    };

    template<class T>
    GTUIteratorConst<T> :: GTUIteratorConst()
                                    :Iterator<T>()
    {}

    template<class T>
    GTUIteratorConst<T> :: GTUIteratorConst(shared_ptr< GNode<T> > adress)
                                    :Iterator<T>(adress)
    {}    

    template<class T>
    const T& GTUIteratorConst<T> :: operator*()
    {
        return this->iptr->idata;
    }

    template<class T>
    const T* GTUIteratorConst<T> :: operator->()
    {
        return &(this->iptr->idata);
    }

    template<class T>
    GTUIteratorConst<T> GTUIteratorConst<T> :: operator++()
    {
        this->iptr = this->iptr->inext;
        return *this;
    }

    template<class T>
    GTUIteratorConst<T> GTUIteratorConst<T> :: operator++(int)
    {
        GTUIterator<T> tmp = (*this);
        this->iptr = (this->iptr->inext);
        return tmp;
    }

    template<class T>
    GTUIteratorConst<T> GTUIteratorConst<T> :: operator--()
    {
        this->iptr = this->iptr->iprev;
        return *this;
    }

    template<class T>
    GTUIteratorConst<T> GTUIteratorConst<T> :: operator--(int)
    {
        GTUIterator<T> tmp = (*this);
        this->iptr = this->iptr->iprev;
        return tmp;
    }

    template<class T>
    bool GTUIteratorConst<T> :: operator==(const GTUIteratorConst<T>& other)const
    {
        return (this->iptr == other.iptr);
    }

    template<class T>
    bool GTUIteratorConst<T> :: operator!=(const GTUIteratorConst<T>& other)const
    {
        return !(this->iptr == other.iptr);
    }

    template<class T>
    GTUIteratorConst<T> GTUIteratorConst<T> :: operator=(const GTUIteratorConst<T>& other)
    {
        this->iptr = nullptr;
        *this = other;
        return *this;
    }

}   //GTU

#endif // GTUITERATORCONST_H
