//This is the header file Iterator.h
#ifndef ITERATOR_H
#define ITERATOR_H

#include <memory>

using namespace std;

namespace GTU
{
    template<class T>
    class GNode
    {
    public:
        GNode()
                :iprev(nullptr) , inext(nullptr)
        {}

        T idata;

        shared_ptr< GNode<T> > iprev;
        shared_ptr< GNode<T> > inext;
    };

    template<class T>
    class Iterator
    {
    public:

        Iterator();
        Iterator(shared_ptr< GNode<T> > adress);

    protected:
        shared_ptr< GNode<T> > iptr;
    };

    template<class T>
    Iterator<T> :: Iterator(){
        iptr = nullptr;
    }

    template<class T>
    Iterator<T> :: Iterator(shared_ptr< GNode<T> > adress){
        iptr = adress;
    }

}   //GTU 

#endif // ITERATOR_H
