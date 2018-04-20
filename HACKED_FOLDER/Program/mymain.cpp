#include <iostream>
#include <iomanip>
#include <stdlib.h>
#include <time.h>
#include <cmath>
#include "myDate.h"

using namespace std;

struct Student
{
    char name[20];
    int id;
    char grade;
    myDate birthday;
    char homeTown[20];
};

void bubbleSortName(Student *arr[], int n);
void bubbleSortID(Student *arr[], int n);
void bubbleSortGrade(Student *arr[], int n);
void bubbleSortBday(Student *arr[], int n);
void bubbleSortTown(Student *arr[], int n);

int strcmpr (char *s1, char *s2);

void display(Student *st[]);

void swap(Student *xp, Student *yp);

void strcopy(char *, char *);

void populate(Student *st[]);

void constructStudent(Student *st, char *right, char grade, char *homeTown);

myDate randomBirthDate();

int randomID();

int getLength(char *str);

int main()
{
    Student *st[10];
    populate(st);
    
    int ch;
    cout << "1) Display list sorted by Name: " << endl;
    cout << "2) Display list sorted by StudentID: " << endl;
    cout << "3) Display list sorted by Grade: " << endl;
    cout << "4) Display list sorted by Birthday: " << endl;
    cout << "5) Display list sorted by HomeTown: " << endl;
    cout << "6) Exit: " << endl;
    cin >> ch;
    while(ch < 6)
    {
        if(ch == 1)
            bubbleSortName(st, 10);
        else if(ch == 2)
            bubbleSortID(st, 10);
        else if(ch == 3)
            bubbleSortGrade(st, 10);
        else if(ch == 4)
            bubbleSortBday(st, 10);
        else if(ch == 5)
            bubbleSortTown(st, 10);
        display(st);
        cout << endl;
        cout << "1) Display list sorted by Name: " << endl;
        cout << "2) Display list sorted by StudentID: " << endl;
        cout << "3) Display list sorted by Grade: " << endl;
        cout << "4) Display list sorted by Birthday: " << endl;
        cout << "5) Display list sorted by HomeTown: " << endl;
        cout << "6) Exit: " << endl;
        cin >> ch;
    }
    return 0;
}

void strcopy(char *left, char *right)
{
    int i = 0;
    while(right[i] != '\0') {
        left[i] = right[i];
        i++;
    }
    left[i] = right[i];
}

void display(Student *st[])
{
    cout << "Name                Student ID         Grade          Birthday             Home Town" << endl;
    for(int i = 0; i < 10; i++)
    {
        cout << st[i]->name
        << right << setw(14-abs(getLength("Jimmy Chao") - getLength(st[i]->name))) << st[i]->id
        << "                " << left << setw(14) << st[i]->grade
        << left << setw(14) << st[i]->birthday.toString()
        << right << setw(15) << setw(15) << st[i]->homeTown << endl;
    }
}

void populate(Student *s[])
{
    srand(time(NULL));
    for(int i = 0; i < 10; i++)
    {
        s[i] = new Student;
        s[i]->id = rand() % 9999 + 1;
    }
    constructStudent(s[0], "Benson Smith", 'B', "New York City");
    constructStudent(s[1], "John Silver", 'F', "Los Angeles");
    constructStudent(s[2], "Jimmy Chao", 'C', "Houston");
    constructStudent(s[3], "Barack Obama", 'D', "Oklahoma");
    constructStudent(s[4], "George Bush", 'A', "Brooklyn");
    constructStudent(s[5], "Donald Trump", 'B', "New Jersey");
    constructStudent(s[6], "Arman Almanzor", 'F', "Tokyo");
    constructStudent(s[7], "Jimbo Rock", 'D', "Hong Kong");
    constructStudent(s[8], "Rico Blanco", 'A', "Beijing");
    constructStudent(s[9], "Aristotle Pollisco", 'A', "Manila");
}

void constructStudent(Student *st, char *right, char grade, char *homeTown)
{
    strcopy(st->name, right);
    strcopy(st->homeTown, homeTown);
    st->id = randomID();
    st->grade = grade;
    st->birthday = randomBirthDate();
}

myDate randomBirthDate()
{
    return *(new myDate(rand() % 12 + 1,rand() % 30 + 1,rand() % 4 + 1998));
}

int randomID()
{
    return 1000 + rand() % 8889;
}

int getLength(char *str)
{
    int i = 0;
    while(str[i] != '\0')
        i++;
    return i;
}

void swap(Student *xp, Student *yp)
{
    Student temp = *xp;
    *xp = *yp;
    *yp = temp;
}

void bubbleSortName(Student *arr[], int n)
{
    int i, j;
    for (i = 0; i < n-1; i++)
        for (j = 0; j < n-i-1; j++)
            if (strcmpr(arr[j]->name, arr[j+1]->name) > 0)
                swap(arr[j], arr[j+1]);
}

void bubbleSortID(Student *arr[], int n)
{
    int i, j;
    for (i = 0; i < n-1; i++)
        for (j = 0; j < n-i-1; j++)
            if (arr[j]->id > arr[j+1]->id)
                swap(arr[j], arr[j+1]);
}

void bubbleSortGrade(Student *arr[], int n)
{
    int i, j;
    for (i = 0; i < n-1; i++)
        for (j = 0; j < n-i-1; j++)
            if (arr[j]->grade > arr[j+1]->grade)
                swap(arr[j], arr[j+1]);
}

void bubbleSortBday(Student *arr[], int n)
{
    int i, j;
    for (i = 0; i < n-1; i++)
        for (j = 0; j < n-i-1; j++)
            if (arr[j]->birthday.daysBetween(arr[j+1]->birthday) < 0)
                swap(arr[j], arr[j+1]);
}

void bubbleSortTown(Student *arr[], int n)
{
    int i, j;
    for (i = 0; i < n-1; i++)
        for (j = 0; j < n-i-1; j++)
            if (strcmpr(arr[j]->homeTown, arr[j+1]->homeTown) > 0)
                swap(arr[j], arr[j+1]);
}

int strcmpr (char *s1, char *s2) {
    char *p1 = s1;
    char *p2 = s2;
    
    while (*p1 != '\0') {
        if (*p2 == '\0') return  1;
        if (*p2 > *p1)   return -1;
        if (*p1 > *p2)   return  1;
        
        p1++;
        p2++;
    }
    
    if (*p2 != '\0') return -1;
    
    return 0;
}
