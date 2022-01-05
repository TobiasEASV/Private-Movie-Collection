package dal.interfaces;

import be.Movie;
import be.MovieException;

import java.util.List;

public interface IMovieRepository {

    String ERROR_STRING = "Error: Cannot access database.";

    public List<Movie> getAllMovies() throws MovieException;

    public Movie createMovie(String title, String artist, String genre, double duration, String pathToFile) throws MovieException;

    public void updateMovie(Movie movie) throws MovieException;

    public void deleteMovie(Movie movie) throws MovieException;
}
