//This is the header file GTUArray.h
#ifndef GTUARRAY_H
#define GTUARRAY_H

#include<iostream>
#include<vector>
#include "Iterable.h"
using namespace std;

namespace GTU
{
    template<class T , size_t N>
    class GTUArray : public Iterable<T>
    {
    public:

        GTUArray();
        GTUArray(const GTUArray<T , N>& other);
        GTUArray(GTUArray<T , N>&& other);

        GTUArray<T , N>& operator=(const GTUArray<T , N>& other);
        GTUArray<T , N>& operator=(GTUArray<T , N>&& other);

        T& operator[](int index);               //index operator overloads
        const T& operator[](int index) const;

        T& at (int n);
        const T& at (int n) const;

        T& front();
        const T& front() const;

        T& back();
        const T& back() const; 

        void fill (const T& value);       

        GTUIterator<T> rbegin() ;               //returns reverse begin
        GTUIterator<T> rend() ;                 //returns reverse end
        GTUIteratorConst<T> crbegin() const ;   //returns constant reverse begin
        GTUIteratorConst<T> crend() const ;     //returns constant reverse end

        ~GTUArray();

    protected:

    };

    template<class T , size_t N>
    GTUArray<T , N> :: GTUArray()
                                        :Iterable<T>(N)
    {
        if (this->size() != 0)
        {
            shared_ptr< GNode<T> > newHead(new GNode<T>() );            //calls GNode's constructor to create a new head
            shared_ptr< GNode<T> > temp = newHead;

            temp->idata = 0;        //initialize with 0

            for (unsigned int i = 0; i < this->size() - 1; ++i)
            {
                shared_ptr< GNode<T> > newGNode(new GNode<T>());        //creating a list which has same lengt with other 
                newGNode->idata = 0;        //initialize with 0

                temp->inext = newGNode;
                newGNode->iprev = temp;
                temp = temp->inext;
            }

            this->head = newHead;
            this->ending = temp->inext;
        }    
    }    

    template<class T , size_t N>
    GTUArray<T , N> :: GTUArray(const GTUArray<T , N>& other)
                                                    :Iterable<T>(other)
    {} 

    template<class T , size_t N>
    GTUArray<T , N> :: GTUArray(GTUArray<T , N>&& other)
                                                    :Iterable<T>(move(other))
    {}

    template<class T , size_t N>
    GTUArray<T , N>& GTUArray<T , N> :: operator=(const GTUArray<T , N>& other)
    {
        Iterable<T> :: operator=(other);
        return *this;
    } 

    template<class T , size_t N>
    GTUArray<T , N>& GTUArray<T , N> :: operator=(GTUArray<T , N>&& other)
    {
        Iterable<T> :: operator=(move(other));
        return *this;
    }

    template<class T , size_t N>
    T& GTUArray<T , N> :: operator[](int index)
    {
        shared_ptr< GNode<T> > temp = this->head;

        for (unsigned int i = 0; i < index; ++i)
        {
            temp = temp->inext;
        }
        
        return temp->idata;
    }

    template<class T , size_t N>
    const T& GTUArray<T , N> :: operator[](int index) const
    {
        shared_ptr< GNode<T> > temp = this->head;

        for (unsigned int i = 0; i < index; ++i)
        {
            temp = temp->inext;
        }
        
        return temp->idata;
    }

    template<class T , size_t N>
    T& GTUArray<T , N> :: at(int n)
    {
		if(n > this->size())
			throw out_of_rangeException();

        shared_ptr< GNode<T> > temp = this->head;

        for (unsigned int i = 0; i < n; ++i)
        {
            temp = temp->inext;
        }
        
        return temp->idata;
    }

    template<class T , size_t N>
    const T& GTUArray<T , N> :: at(int n) const
    {
		if(n > this->size())
			throw out_of_rangeException();

        shared_ptr< GNode<T> > temp = this->head;

        for (unsigned int i = 0; i < n; ++i)
        {
            temp = temp->inext;
        }
        
        return temp->idata;
    }

    template<class T , size_t N>
    T& GTUArray<T , N> :: front()
    {
        return this->head->idata;
    }
    template<class T , size_t N>
    const T& GTUArray<T , N> :: front() const
    {
        return this->head->idata;
    }

    template<class T , size_t N>
    T& GTUArray<T , N> :: back()
    {
        shared_ptr< GNode<T> > temp = this->head;

        for (unsigned int i = 0; i < this->size() - 1; i++)
        {
            temp = temp->inext;
        }
        this->ending = temp;
        
        return (this->ending)->idata;
    }
    template<class T , size_t N>
    const T& GTUArray<T , N> :: back() const
    {
        shared_ptr< GNode<T> > temp = this->head;

        for (unsigned int i = 0; i < this->size() - 1; i++)
        {
            temp = temp->inext;
        }
        this->ending = temp;
        
        return (this->ending)->idata;
    }

    template<class T , size_t N>
    void GTUArray<T , N> :: fill (const T& value)
    {
        shared_ptr< GNode<T> > temp = this->head;

        for (unsigned int i = 0; i < this->size() - 1; i++)
        {
            temp->idata = value;
            temp = temp->inext;
        }
         temp->idata = value;
    }           

    template<class T , size_t N>
    GTUIterator<T> GTUArray<T , N> :: rbegin()
    {
        return this->end();
    }

    template<class T , size_t N>
    GTUIterator<T> GTUArray<T , N> :: rend()
    {
        return this->begin();
    }

    template<class T , size_t N>
    GTUIteratorConst<T> GTUArray<T , N> :: crbegin() const
    {
        return this->cend();
    }

    template<class T , size_t N>
    GTUIteratorConst<T> GTUArray<T , N> :: crend() const
    {
        return this->cbegin();
    }

    template<class T , size_t N>
    GTUArray<T , N> :: ~GTUArray(){
        this->clear();
    }    

    
}// GTU



#endif //GTUARRAY_H