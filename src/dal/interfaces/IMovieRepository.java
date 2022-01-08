package dal.interfaces;

import be.MovieException;
import be.MovieModel;

import java.util.List;

public interface IMovieRepository {

    String ERROR_STRING = "Error: Cannot access database.";

    public List<MovieModel> getAllMovies() throws MovieException;

    public MovieModel createMovie(String name, double IMDBRating, String pathToFile) throws MovieException;

    public void updateMovie(MovieModel movie) throws MovieException;

    public void deleteMovie(MovieModel movie) throws MovieException;
}
