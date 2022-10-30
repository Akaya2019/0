//This is the header file GTUVector.h
#ifndef GTUVECTOR_H
#define GTUVECTOR_H

#include<iostream>
#include<vector>
#include<utility>
#include "Iterable.h"
#include "Exception.h"
using namespace std;

namespace GTU
{
    template<class T>
    class GTUVector : public Iterable<T>
    {
    public:

        GTUVector();
        GTUVector(int newSize);
        GTUVector(const GTUVector<T>& other);
        GTUVector(GTUVector<T>&& other);

        GTUVector<T>& operator=(const GTUVector<T>& other);
        GTUVector<T>& operator=(GTUVector<T>&& other);

        T& operator[](int index);               //index operator overloads
        const T& operator[](int index) const;

        T& at (int n);
        const T& at (int n) const;

        T& front();
        const T& front() const;

        T& back();
        const T& back() const;

        void push_back (const T& value);
        void pop_back ();
        void add (const T& value);

        void resize (int newSize);              //resize function

        GTUIterator<T> rbegin() ;               //returns reverse begin
        GTUIterator<T> rend() ;                 //returns reverse end
        GTUIteratorConst<T> crbegin() const ;   //returns constant reverse begin
        GTUIteratorConst<T> crend() const ;     //returns constant reverse end

        ~GTUVector();
    };

    template<class T>
    GTUVector<T> :: GTUVector()
                                        :Iterable<T>()
    {}

    template<class T>
    GTUVector<T> :: GTUVector(int newSize)
                                        :Iterable<T>(newSize)
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

    template<class T>
    GTUVector<T> :: GTUVector(const GTUVector<T>& other)
                                                    :Iterable<T>(other)
    {} 

    template<class T>
    GTUVector<T> :: GTUVector(GTUVector<T>&& other)
                                                    :Iterable<T>(move(other))
    {}

    template<class T>
    GTUVector<T>& GTUVector<T> :: operator=(const GTUVector<T>& other)
    {
        Iterable<T> :: operator=(other);
        return *this;
    } 

    template<class T>
    GTUVector<T>& GTUVector<T> :: operator=(GTUVector<T>&& other)
    {
        Iterable<T> :: operator=(move(other));
        return *this;
    }

    template<class T>
    T& GTUVector<T> :: operator[](int index)
    {
        shared_ptr< GNode<T> > temp = this->head;

        for (unsigned int i = 0; i < index; ++i)
        {
            temp = temp->inext;
        }
        
        return temp->idata;
    }

    template<class T>
    const T& GTUVector<T> :: operator[](int index) const
    {
        shared_ptr< GNode<T> > temp = this->head;

        for (unsigned int i = 0; i < index; ++i)
        {
            temp = temp->inext;
        }
        
        return temp->idata;
    }

    template<class T>
    T& GTUVector<T> :: at(int n)
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

    template<class T>
    const T& GTUVector<T> :: at(int n) const
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

    template<class T>
    T& GTUVector<T> :: front()
    {
        return this->head->idata;
    }
    template<class T>
    const T& GTUVector<T> :: front() const
    {
        return this->head->idata;
    }

    template<class T>
    T& GTUVector<T> :: back()
    {
        shared_ptr< GNode<T> > temp = this->head;

        for (unsigned int i = 0; i < this->size() - 1; i++)
        {
            temp = temp->inext;
        }
        this->ending = temp;

        return (this->ending)->idata;
    }
    template<class T>
    const T& GTUVector<T> :: back() const
    {
        shared_ptr< GNode<T> > temp = this->head;

        for (unsigned int i = 0; i < this->size() - 1; i++)
        {
            temp = temp->inext;
        }
        this->ending = temp;
        
        return (this->ending)->idata;
    }        

    template<class T>
    void GTUVector<T> :: push_back (const T& value)
    {
        shared_ptr< GNode<T> > temp = this->head;

        for (unsigned int i = 0; i < this->size() - 1; i++)
        {
            temp = temp->inext;
        }
        shared_ptr< GNode<T> > newGNode(new GNode<T>());

        temp->inext = newGNode;
        newGNode->iprev = temp;
        newGNode->inext = nullptr;

        newGNode->idata = value;

        this->setSize( this->size() + 1);
    }

    template<class T>
    void GTUVector<T> :: pop_back ()
    {
        shared_ptr< GNode<T> > temp = this->head;

        for (unsigned int i = 0; i < this->size() - 1; i++)
        {
            temp = temp->inext;
        }
        temp->iprev = nullptr;
        temp->idata = 0;

        this->setSize( this->size() - 1);
    }    

    template<class T>
    void GTUVector<T> :: add (const T& value)
    {
        GTUVector<T> :: push_back (value);
    }    

    template<class T>
    void GTUVector<T> :: resize (int newSize)
    {
        if (this->size() == newSize)
        {
            this->setSize(newSize);
        }
        
        else if (this->size() < newSize)
        {
            for (int i = newSize; i < this->size(); i++)
            {
                GTUVector<T> :: operator[](i) = 0;
            }
            this->setSize(newSize);
        }

        else
        {
            for (int i = this->size(); i < newSize; i++)
            {
                GTUVector<T> :: operator[](i) = 0;
            }
            this->setSize(newSize);
        }
    }

    template<class T>
    GTUIterator<T> GTUVector<T> :: rbegin()
    {
        return this->end();
    }

    template<class T>
    GTUIterator<T> GTUVector<T> :: rend()
    {
        return this->begin();
    }

    template<class T>
    GTUIteratorConst<T> GTUVector<T> :: crbegin() const
    {
        return this->cend();
    }

    template<class T>
    GTUIteratorConst<T> GTUVector<T> :: crend() const
    {
        return this->cbegin();
    }

    template<class T>
    GTUVector<T> :: ~GTUVector(){
        this->clear();
    }

}// GTU



#endif //GTUVECTOR_H