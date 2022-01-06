package bll;

import be.Movie;
import be.MovieException;
import bll.util.ISearcher;
import bll.util.MovieSearcher;
import dal.db.DAOMovie;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class MovieManager {
    private DAOMovie daoMovie;
    private ISearcher movieSearcher;

    public MovieManager() throws IOException {
        movieSearcher = new MovieSearcher();
        daoMovie = new DAOMovie();
    }

    public Movie createMovie(String name, double imdbRating, String pathToFile) throws MovieException {
        return daoMovie.createMovie(name, imdbRating, pathToFile);
    }

    public List<Movie> getAllSongs() throws MovieException {
        return daoMovie.getAllMovies();
    }

    public void deleteMovie(Movie movie) throws MovieException {
        daoMovie.deleteMovie(movie);
    }

    public void updateMovie(Movie movie) throws MovieException {
        daoMovie.updateMovie(movie);
    }

    /**
     * Searches through song list, to find a song that matches the key word

     * @param query the key word, to search for
     * @return a list of songs that fit, the key word
     */
    public List<Movie> searchSong(String query) throws MovieException {
        List<Movie> allSongs = daoMovie.getAllMovies();
        List<Movie> searchResult = movieSearcher.search(allSongs, query);
        return searchResult;
    }
}
