//This is the header file Iterable.h
#ifndef ITERABLE_H
#define ITERABLE_H

#include <iostream>
#include <memory>
#include "IterableBase.h"
#include "Iterator.h"
#include "GTUIterator.h"
#include "GTUIteratorConst.h"
//#include "GTUException.h"

using namespace std;

namespace GTU
{
    template<class T>
    class Iterable : public IterableBase<T>
    {
    public:
        Iterable();                                  //no parameter constructor
        Iterable(int newSize);
        Iterable(const Iterable& other);             //copy constructor
        Iterable(Iterable&& other) noexcept;         //move constructor

        Iterable<T>& operator=(const Iterable& other);           //assignment operator overload
        Iterable<T>& operator=(Iterable&& other) noexcept;       //move assignment

        virtual bool empty() const final;
        virtual int size() const final;
        virtual void erase (GTUIterator<T> position) final;
        virtual void clear() final;
        virtual GTUIterator<T> begin() final;
        virtual GTUIterator<T> end() final;
        virtual GTUIteratorConst<T> cbegin() const final;
        virtual GTUIteratorConst<T> cend() final;

        ~Iterable();

    protected:
        void setSize(int newSize);      
        shared_ptr< GNode<T> > head;     //head of the iterable container
		shared_ptr< GNode<T> > ending;   //end of the iterable container
        int cap;                        //capacity of the iterable container
    };

    template<class T>
    Iterable<T> :: Iterable()
    {
        ending = make_shared< GNode<T> >();
        setSize(0);
    }

    template<class T>
    Iterable<T> :: Iterable(int newSize)
    {
        ending = make_shared< GNode<T> >();
        setSize(newSize);
    }

    template<class T>
    Iterable<T> :: Iterable(const Iterable& other)
    {
        ending = make_shared< GNode<T> >();
        setSize(other.size());

        if (size() != 0)
        {
            shared_ptr< GNode<T> > newHead(new GNode<T>() );    //calls GNode's constructor to create a new head
            shared_ptr< GNode<T> > temp = newHead;

            for (unsigned int i = 0; i < size() - 1; ++i)
            {
                shared_ptr< GNode<T> > newGNode(new GNode<T>());        //creating a list which has same lengt with other 
                temp->inext = newGNode;
                newGNode->iprev = temp;
                temp = temp->inext;
            }

            head = newHead;
            ending = temp->inext;

            shared_ptr< GNode<T> > headTemp = head;
            shared_ptr< GNode<T> > otherheadTemp = other.head;

            for (unsigned int i = 0; i < size(); ++i)
            {
                headTemp->idata = otherheadTemp->idata;
                headTemp = headTemp->inext;                              //copying datas from other to our 
                otherheadTemp = otherheadTemp->inext;
            }
        }   
    }

    template<class T>
    Iterable<T> :: Iterable(Iterable&& other) noexcept
                                            :head(other.head) , ending(other.ending) , cap(other.cap)
    {
        other.head = nullptr;
        other.ending = nullptr;
        other.cap = 0;
    }

    template<class T>
    Iterable<T>& Iterable<T> :: operator=(const Iterable& other)
    {
        if (&other == this){        //self assignment control
            return *this;
        }
        
        clear();                //clearing our object's inside
        setSize(other.size());

        if (size() != 0)
        {
            shared_ptr< GNode<T> > newHead(new GNode<T>() );    //calls GNode's constructor to create a new head
            shared_ptr< GNode<T> > temp = newHead;

            for (unsigned int i = 0; i < size() - 1; ++i)
            {
                shared_ptr< GNode<T> > newGNode(new GNode<T>());        //creating a list which has same lengt with other 
                temp->inext = newGNode;
                newGNode->iprev = temp;
                temp = temp->inext;
            }

            head = newHead;
            ending = temp->inext;

            shared_ptr< GNode<T> > headTemp = head;
            shared_ptr< GNode<T> > otherheadTemp = other.head;

            for (unsigned int i = 0; i < size(); ++i)
            {
                headTemp->idata = otherheadTemp->idata;
                headTemp = headTemp->inext;                              //copying datas from other to our 
                otherheadTemp = otherheadTemp->inext;
            }
        }

        return *this;
    } 

    template<class T>
    Iterable<T>& Iterable<T> :: operator=(Iterable&& other) noexcept
    {
        if (&other == this){        //self assignment control
            return *this;
        }        

        clear();                //clearing our object's inside
        setSize(other.size());

        head = other.head ; 
        ending = other.ending ; 
        cap = other.cap ;

        other.head = nullptr;
        other.ending = nullptr;
        other.cap = 0;

        return *this; 
    }

    template<class T>
    bool Iterable<T> :: empty() const
    {
        bool ans;
        if (size() == 0)
        {
            ans = true;
        }
        else
            ans = false;
        
        return ans;
    }

    template<class T>
    int Iterable<T> :: size() const
    {
        return cap;
    }

    template<class T>
    void Iterable<T> :: erase (GTUIterator<T> position)
    {
		if(size() == 1)
		{
			if(position == begin() || position == end())
			{
				head = nullptr , ending->iprev = nullptr;
				setSize(size()-1);
			}
		}

		else if(size() > 0)
		{
			if(position == begin())
			{
				head->inext->iprev = nullptr;
				head = head->inext;
				setSize(size()-1);
			}
			else
			{
				shared_ptr< GNode<T> > temp = head;

				// for(auto p = begin() ; p != position ; ++p , temp = temp->inext)
				// 	if(p == end());
				// 		throw(bad_pafram(" There is no data such that in the container..\n"));

				temp->iprev->inext = temp->inext;
				temp->inext->iprev = temp->iprev;
				temp->inext = nullptr;
				temp->iprev = nullptr;
				setSize(size()-1);
			}
		}
    }

    template<class T>
    void Iterable<T> :: clear()
	{
		shared_ptr< GNode<T> > temp;

		while(head != nullptr)
		{
			temp = head;
			head = head->inext;
			temp->iprev = nullptr;
			temp->inext = nullptr;
		}

		head = nullptr;
		setSize(0);
	}

    template<class T>
    GTUIterator<T> Iterable<T> :: begin()
    {
        return GTUIterator<T>(head);
    }

    template<class T>
    GTUIterator<T> Iterable<T> :: end()
    {
        shared_ptr< GNode<T> > temp = head;

        for (unsigned int i = 0; i < size() - 1; i++)
        {
            temp = temp->inext;
        }
        ending = temp;

        return GTUIterator<T>(ending);
    }

    template<class T>
    GTUIteratorConst<T> Iterable<T> :: cbegin() const
    {
        return GTUIteratorConst<T>(head);
    }

    template<class T>
    GTUIteratorConst<T> Iterable<T> :: cend()
    {
        shared_ptr< GNode<T> > temp = head;

        for (unsigned int i = 0; i < size() - 1; i++)
        {
            temp = temp->inext;
        }
        ending = temp;

        return GTUIteratorConst<T>(temp);
    }

    template<typename T>
	void Iterable<T>::setSize(int newSize)
	{
		if(newSize < 0 )
			newSize = 0;

		cap = newSize;
	}

    template<class T>
    Iterable<T> :: ~Iterable()
    {
        ending = nullptr;
    }     
    
}   //GTU 

#endif // ITERABLE_H
