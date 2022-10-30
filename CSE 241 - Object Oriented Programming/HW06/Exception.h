#ifndef EXCEPTION_H
#define EXCEPTION_H

#include <iostream>
#include <string>

using namespace std;
//using std::logic_error;

class out_of_rangeException : public logic_error{
public:
    out_of_rangeException()
                :logic_error("out of range exception detected") {}
};

#endif // EXCEPTION_H