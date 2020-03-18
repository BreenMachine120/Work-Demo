/// File: project_mbreen\BoxOfficeStats\main.cpp
/// Name: Michael Breen

/// Import packages
#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>
#include <string>
#include <iomanip>

using namespace std;

#include "Movie.h"

/// Declare functions
void delimiter(string s, char d, vector <string> &Res);
void print(int x, vector <Movie> V);
bool compW(Movie A, Movie B);
bool compD(Movie A, Movie B);
bool compO(Movie A, Movie B);
bool compY_old(Movie A, Movie B);
bool compY_new(Movie A, Movie B);
bool compT(Movie A, Movie B);
void defaultAnswer();
void submenu(int &ans);


int main()
{
    /// Check if file is there
    string filename = "movielist.csv";
    ifstream fin(filename.data());
    if (!fin)
    {
        cout << "File not found" << endl;
        return -1;
    }
    /// Put file in a vector
    vector <Movie> Mlist;
    vector <string> Templist;
    string s;
    while (!fin.eof())
    {
        getline(fin, s);
        delimiter(s, ',', Templist);
        /// Convert strings to ints and floats (title is already a string)
        stringstream val0(Templist[0]);
        stringstream val2(Templist[2]);
        stringstream val3(Templist[3]);
        stringstream val4(Templist[4]);
        stringstream val5(Templist[5]);
        int intRank = 0;
        int intYear = 0;
        float floatWorldwide, floatDomestic, floatOverseas;
        val0 >> intRank;
        val2 >> floatWorldwide;
        val3 >> floatDomestic;
        val4 >> floatOverseas;
        val5 >> intYear;
        Movie m(intRank, Templist[1], floatWorldwide, floatDomestic, floatOverseas, intYear);
        Mlist.push_back(m);
    }
    fin.close();
    /// Fix rank of first movie
    Mlist[0].setRank(1);

    /// Initialize answers
    int ans0 = 1;   // answer for main menu
    int ans1 = 1;   // answer for submenus
    while (ans0 != 0)
    {
        cout << "Box Office Stats \n" <<
                "1. Worldwide \n" <<
                "2. Domestic \n" <<
                "3. Overseas \n" <<
                "4. Year \n" <<
                "5. Alphabetical \n" <<
                "6. About \n" <<
                "0. Exit \n" <<
                "Option: ";
        cin >> ans0;
        cout << endl;
        switch (ans0)
        {
            default:
                defaultAnswer();
                break;
            /// Worldwide
            case 1:
                sort(Mlist.begin(), Mlist.end(), compW);
                submenu(ans1);
                switch (ans1)
                {
                default:
                    defaultAnswer();
                    break;
                /// Print 10
                case 1:
                    print(10, Mlist);
                    continue;
                /// Print all
                case 2:
                    print(Mlist.size(), Mlist);
                    continue;
                case 0:
                    cout << endl;
                    continue;
                }//case 1
            /// Domestic
            case 2:
                sort(Mlist.begin(), Mlist.end(), compD);
                submenu(ans1);
                switch(ans1)
                {
                default:
                    defaultAnswer();
                    break;
                /// Print 10
                case 1:
                    print(10, Mlist);
                    continue;
                /// Print all
                case 2:
                    print(Mlist.size(), Mlist);
                    continue;
                case 0:
                    cout << endl;
                    continue;
                }//case 2
            /// Overseas
            case 3:
                sort(Mlist.begin(), Mlist.end(), compO);
                submenu(ans1);
                switch(ans1)
                {
                default:
                    defaultAnswer();
                    break;
                /// Print 10
                case 1:
                    print(10, Mlist);
                    continue;
                /// Print all
                case 2:
                    print(Mlist.size(), Mlist);
                    continue;
                case 0:
                    cout << endl;
                    continue;
                }//case 3
            /// Year
            case 4:
                cout << "1. Print 10 oldest \n" <<
                        "2. Print 100 oldest \n" <<
                        "3. Print 10 newest \n" <<
                        "4. Print 100 newest \n" <<
                        "0. Back \nOption: ";
                cin >> ans1;
                switch(ans1)
                {
                default:
                    defaultAnswer();
                    break;
                /// Print 10 oldest
                case 1:
                    sort(Mlist.begin(), Mlist.end(), compY_old);
                    print(10, Mlist);
                    continue;
                /// Print 100 oldest
                case 2:
                    sort(Mlist.begin(), Mlist.end(), compY_old);
                    print(Mlist.size(), Mlist);
                    continue;
                /// Print 10 newest
                case 3:
                    sort(Mlist.begin(), Mlist.end(), compY_new);
                    print(10, Mlist);
                    continue;
                /// Print 100 newest
                case 4:
                    sort(Mlist.begin(), Mlist.end(), compY_new);
                    print(Mlist.size(), Mlist);
                    continue;
                case 0:
                    cout << endl;
                    continue;
                }//case 4
            /// Title (print all)
            case 5:
                sort(Mlist.begin(), Mlist.end(), compT);
                print(Mlist.size(), Mlist);
                continue;
            /// About
            case 6:
                cout << "Project: Box Office Stats. Database of 100 highest grossing movies of all time. \n" <<
                        "Author: Michael Breen \n" <<
                        "Source: https://www.boxofficemojo.com/alltime/world/ \n" <<
                        "Last updated: 05/01/2019 08:30 AM EST \n" <<
                        "Note: Captain Marvel and Avengers: Endgame are still in theaters; numbers not final \n" << endl;
            /// Exit
            case 0:
                continue;
        }//switch(ans0)
        cout << endl;
    }//while
    cout << "Ending program..." << endl;

    return 0;
}


/// Writes each line of a .csv to a temporary vector
void delimiter(string s, char d, vector <string> &Res)
{
    Res.clear();
    int pos = 0;
    string r;
    while(true)
    {
        pos = s.find(d);
        if (pos == -1)
        {
            Res.push_back(s);
            break;
        }
        r = s.substr(0,pos);
        Res.push_back(r);
        s = s.substr(pos+1,s.size());
    }
}


/// Prints list of movies
void print(int x, vector <Movie> V)
{
    cout << endl;
    int width = 8;
    /// Find length of longest title
    int longestTitle = 0;
    for (int i = 0; i < x; i++)
    {
        if ((unsigned)longestTitle < V[i].getTitle().size())
        {
            longestTitle = V[i].getTitle().size();
        }
    }
    /// Print table header
    cout << left << setw(width) << "Rank:" <<
            setw(longestTitle + 2) << "Title:" <<
            setw(width) << "WW:" <<
            setw(width) << "DM:" <<
            setw(width) << "OS:" <<
            setw(width) << "Year:" << endl;
    /// Print table contents
    for (int i = 0; i < x; i++)
    {
        cout << left << setw(width) << V[i].getRank() <<
                setw(longestTitle + 2) << V[i].getTitle() <<
                setw(width) << V[i].getWorldwide() <<
                setw(width) << V[i].getDomestic() <<
                setw(width) << V[i].getOverseas() <<
                setw(width) << V[i].getYear() << endl;
    }
    cout << endl;
}


/// Comparative functions
bool compW(Movie A, Movie B)
{
    return A.getWorldwide() > B.getWorldwide();
}

bool compD(Movie A, Movie B)
{
    return A.getDomestic() > B.getDomestic();
}

bool compO(Movie A, Movie B)
{
    return A.getOverseas() > B.getOverseas();
}

bool compY_old(Movie A, Movie B)
{
    return A.getYear() < B.getYear();
}

bool compY_new(Movie A, Movie B)
{
    return A.getYear() > B.getYear();
}

bool compT(Movie A, Movie B)
{
    return A.getTitle() < B.getTitle();
}

/// Prints if any answer is invalid
void defaultAnswer()
{
    cout << "Please select from the available options." << endl;
}

/// Prints submenu for Worldwide, Domestic, and Overseas
void submenu(int &ans)
{
    cout << "1. Print 10 \n2. Print 100 \n0. Back \nOption: ";
    cin >> ans;
}
