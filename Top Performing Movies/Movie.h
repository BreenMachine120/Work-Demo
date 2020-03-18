/// File: project_mbreen\BoxOfficeStats\Movie.h
/// Name: Michael Breen

#ifndef MOVIE_H_INCLUDED
#define MOVIE_H_INCLUDED

class Movie
{
private:
    /// Variables
    int movieRank, year;                    // Worldwide rank and year of release
    string title;                           // Title
    float worldwide, domestic, overseas;    // Worldwide, domestic, and overseas gross in millions

public:
    /// Constructor
    Movie(int movieRank = 0, string title = "", float worldwide = 0.0, float domestic = 0.0, float overseas = 0.0, int year = 0)
    {
        this->movieRank = movieRank;
        this->title = title;
        this->worldwide = worldwide;
        this->domestic = domestic;
        this->overseas = overseas;
        this->year = year;
    }

    /// Getters
    int getRank()
    {
        return movieRank;
    }
    string getTitle()
    {
        return title;
    }
    float getWorldwide()
    {
        return worldwide;
    }
    float getDomestic()
    {
        return domestic;
    }
    float getOverseas()
    {
        return overseas;
    }
    int getYear()
    {
        return year;
    }

    /// Setter (necessary to fix first line imported))
    void setRank(int n)
    {
        movieRank = n;
    }
};

#endif // MOVIE_H_INCLUDED
