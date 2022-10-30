//This is the header file GTUSet.h
#ifndef GTUSET_H
#define GTUSET_H

#include<iostream>
#include<vector>
#include "Iterable.h"
using namespace std;

namespace GTU
{
    template<class T>
    class GTUSet : public Iterable<T>
    {
    public:

        GTUSet(int newSize = 0);
        GTUSet(const GTUSet<T>& other);
        GTUSet(GTUSet<T>&& other);

        GTUSet<T>& operator=(const GTUSet<T>& other);
        GTUSet<T>& operator=(GTUSet<T>&& other);

        GTUIterator<T> search (const T& value);
        void add(const T& value);
        void deleteF (GTUIteratorConst<T> position);
        void deleteF (const T& value);

        GTUSet<T>& intersectSet(const GTUSet<T>& other);
        GTUSet<T>& unionSet(const GTUSet<T>& other);

        GTUIterator<T> rbegin() ;               //returns reverse begin
        GTUIterator<T> rend() ;                 //returns reverse end
        GTUIteratorConst<T> crbegin() const ;   //returns constant reverse begin
        GTUIteratorConst<T> crend() const ;     //returns constant reverse end
        
        ~GTUSet();

        private:
            int used;
    };

    template<class T>
    GTUSet<T> :: GTUSet(int newSize)
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
    GTUSet<T> :: GTUSet(const GTUSet<T>& other)
                                                    :Iterable<T>(other)
    {} 

    template<class T>
    GTUSet<T> :: GTUSet(GTUSet<T>&& other)
                                                    :Iterable<T>(move(other))
    {}

    template<class T>
    GTUSet<T>& GTUSet<T> :: operator=(const GTUSet<T>& other)
    {
        Iterable<T> :: operator=(other);
        return *this;
    } 

    template<class T>
    GTUSet<T>& GTUSet<T> :: operator=(GTUSet<T>&& other)
    {
        Iterable<T> :: operator=(move(other));
        return *this;
    }

    template<class T>
    GTUIterator<T> GTUSet<T> :: search (const T& value)
    {
        bool flag = 0;
        shared_ptr< GNode<T> >temp = this->head;

        for (int i = 0; i < this->size(); i++)
        {
            if (value == temp.idata)
            {
                flag = 1;
                break;
            }
            temp = temp->inext;
        }

        if(flag == 1)
        {
            return temp;
        }
        else{
            GTUIterator<T> Null;
            return Null;
        }    
    }

    template<class T>
    void GTUSet<T> :: add (const T& value)
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
    void GTUSet<T> :: deleteF (GTUIteratorConst<T> position)
    {
        if(this->size() == 1)
		{
			if(position == this->begin() || position == this->end())
			{
				this->head = nullptr , this->ending->iprev = nullptr;
				this->setSize(this->size()-1);
			}
		}

		else if(this->size() > 0)
		{
			if(position == this->begin())
			{
				this->head->inext->iprev = nullptr;
				this->head = this->head->inext;
				this->setSize(this->size()-1);
			}
			else
			{
				shared_ptr< GNode<T> > temp = this->head;

				temp->iprev->inext = temp->inext;
				temp->inext->iprev = temp->iprev;
				temp->inext = nullptr;
				temp->iprev = nullptr;
				this->setSize(this->size()-1);
			}
		}
    }

    template<class T>
    void GTUSet<T> :: deleteF(const T& value)
    {
        GTUIterator<T> position = GTUSet<T> :: search (value);

        if (position.iptr == nullptr)
        {
            cout << "There is no data such that !! " << endl;
        }
        else 
             GTUSet<T> :: deleteF (position);
    }

    template<class T>
    GTUSet<T>& GTUSet<T> :: intersectSet(const GTUSet<T>& other)
    {

    }

    template<class T>
    GTUSet<T>& GTUSet<T> :: unionSet(const GTUSet<T>& other)
    {
        int i;
        int flag = 0;

         GTUSet<T> temp (this->size() + other.size()); 

         shared_ptr< GNode<T> > tempHead = temp.head;
         shared_ptr< GNode<T> > tempThisHead = this->head;
         shared_ptr< GNode<T> > tempOtherHead = other.head;

        for (i = 0; i < this->size(); i++)
        {
            tempHead->idata = tempThisHead->idata;
            tempHead = tempHead->inext;
            tempThisHead = tempThisHead->inext;
        }
        tempThisHead = this->head;

        for (i = 0; i < other.size(); i++)
        {
            for (int j = 0; j < this->size(); j++)
            {
                if (tempOtherHead->idata == tempThisHead->idata)
                {
                    flag  = 1;
                    break;
                }
                tempThisHead = tempThisHead->inext;
            }

            if (flag != 1)
            {
                tempHead->idata = tempOtherHead->idata;
                // New.used++;
            }
            flag = 0;
            tempOtherHead = tempOtherHead->inext;
        }
        *this = temp;
        return *this;
    }

    template<class T>
    GTUIterator<T> GTUSet<T> :: rbegin()
    {
        return this->end();
    }

    template<class T>
    GTUIterator<T> GTUSet<T> :: rend()
    {
        return this->begin();
    }

    template<class T>
    GTUIteratorConst<T> GTUSet<T> :: crbegin() const
    {
        return this->cend();
    }

    template<class T>
    GTUIteratorConst<T> GTUSet<T> :: crend() const
    {
        return this->cbegin();
    }

    template<class T>
    GTUSet<T> :: ~GTUSet<T>(){
        this->clear();
    }
    
}// GTU



#endif //GTUSET_H