package bll;

import be.Movie;
import be.MovieException;
import be.MovieModel;
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

    public List<MovieModel> getAllMovies() throws MovieException {
        return daoMovie.getAllMovies();
    }

    public MovieModel createMovie(String name, double imdbRating, String pathToFile) throws MovieException {
        return daoMovie.createMovie(name, imdbRating, pathToFile);
    }

    public void deleteMovie(MovieModel movie) throws MovieException {
        daoMovie.deleteMovie(movie);
    }

    public void updateMovie(MovieModel movie) throws MovieException {
        daoMovie.updateMovie(movie);
    }

    /**
     * Searches through song list, to find a song that matches the key word

     * @param query the key word, to search for
     * @return a list of songs that fit, the key word
     */
    public List<MovieModel> searchMovie(String query) throws MovieException {
        List<MovieModel> allMovies = daoMovie.getAllMovies();
        List<MovieModel> searchResult = movieSearcher.search(allMovies, query);
        return searchResult;
    }
}
