//This is the header file IterableBase.h
#ifndef ITERABLEBASE_H
#define ITERABLEBASE_H

#include <iostream>
#include <memory>
#include "Iterator.h"
#include "GTUIterator.h"
#include "GTUIteratorConst.h"

using namespace std;

namespace GTU
{
    template<class T>
    class IterableBase
    {
    public:

        virtual bool empty() const = 0;
        virtual int size() const = 0;
        virtual void erase (GTUIterator<T> position) = 0;
        virtual void clear() = 0;
        virtual GTUIterator<T> begin() = 0;
        virtual GTUIterator<T> end() = 0;
        virtual GTUIteratorConst<T> cbegin() const = 0;
        virtual GTUIteratorConst<T> cend() = 0;
    };

}

#endif // ITERABLEBASE_H