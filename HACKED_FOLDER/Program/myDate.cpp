    // testdate.cpp
    // Author: Fair Aboshehwa
    // CECS 282-05 (Tu/Th)
    // Program #2 due date: 2/27/2018

#include "myDate.h"

int Greg2Julian(int, int, int);
void Julian2Greg(int, int &, int &, int &);

myDate::myDate()
{
    M = 5;
    D = 11;
    Y = 1959;
}

myDate::myDate(int M, int D, int Y)
{
    if (M < 0 || M > 12 || D > 31 || D < 0 || (M == 2 && D > 28))
    {
        M = 5;
        D = 11;
        Y = 1959;
    }
    this->M = M;
    this->D = D;
    this->Y = Y;
}

void myDate::display()
{
    string month;
    switch (M)
    {
        case 1:
            month = "January";
            break;
        case 2:
            month = "February";
            break;
        case 3:
            month = "March";
            break;
        case 4:
            month = "April";
            break;
        case 5:
            month = "May";
            break;
        case 6:
            month = "June";
            break;
        case 7:
            month = "July";
            break;
        case 8:
            month = "August";
            break;
        case 9:
            month = "September";
            break;
        case 10:
            month = "October";
            break;
        case 11:
            month = "November";
            break;
        case 12:
            month = "December";
            break;
    }
    cout << month << " " << D << ", " << Y;
}

void myDate::increaseDate(int N)
{
    int currentJD = Greg2Julian(M, D, Y);
    Julian2Greg(currentJD + N, M, D, Y);
}

void myDate::decreaseDate(int N)
{
    int currentJD = Greg2Julian(M, D, Y);
    Julian2Greg(currentJD - N, M, D, Y);
}

int myDate::daysBetween(myDate D)
{
    int thisJD = Greg2Julian(M, this->D, Y);
    int othJD = Greg2Julian(D.M, D.D, D.Y);
    return (othJD - thisJD) < 0 ? (othJD - thisJD) - 1 : (othJD - thisJD);
}

int myDate::getMonth()
{
    return M;
}

int myDate::getDay()
{
    return D;
}

int myDate::getYear()
{
    return Y;
}

int myDate::dayOfYear()
{
    return Greg2Julian(M, D, Y) - Greg2Julian(1, 1, Y) + 1;
}

string myDate::toString()
{
    string month;
    switch (M)
    {
        case 1:
            month = "January";
            break;
        case 2:
            month = "February";
            break;
        case 3:
            month = "March";
            break;
        case 4:
            month = "April";
            break;
        case 5:
            month = "May";
            break;
        case 6:
            month = "June";
            break;
        case 7:
            month = "July";
            break;
        case 8:
            month = "August";
            break;
        case 9:
            month = "September";
            break;
        case 10:
            month = "October";
            break;
        case 11:
            month = "November";
            break;
        case 12:
            month = "December";
            break;
    }
    return month + " " + to_string(D) + ", " + to_string(Y);
}

string myDate::dayName()
{
    switch (Greg2Julian(M, D, Y) % 7)
    {
        case 0:
            return "Monday";
        case 1:
            return "Tuesday";
        case 2:
            return "Wednesday";
        case 3:
            return "Thursday";
        case 4:
            return "Friday";
        case 5:
            return "Saturday";
        case 6:
            return "Sunday";
    }
    
}

int Greg2Julian(int month, int day, int year)
{
    int i = year;
    int j = month;
    int k = day;
    
    return k - 32075 + 1461 * (i + 4800 + (j - 14) / 12) / 4 + 367 * (j - 2 - (j - 14) / 12 * 12)/12-3*((i+4900+(j-14)/12)/100)/4;
}
void Julian2Greg(int JD, int &month, int &day, int &year)
{
    int L = JD + 68569;
    int N = 4 * L / 146097;
    L = L - (146097 * N + 3) / 4;
    int I = 4000 * (L + 1) / 1461001;
    L = L - 1461 * I / 4 + 31;
    int J = 80 * L / 2447;
    int K = L - 2447 * J / 80;
    L = J / 11;
    J = J + 2 - 12 * L;
    I = 100 * (N - 49) + I + L;
    
    year = I;
    month = J;
    day = K;
}

