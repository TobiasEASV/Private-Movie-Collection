package gui.model;

import be.MovieException;
import bll.MovieManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.io.IOException;
import java.util.List;

public class MovieListModel {
    private MovieManager movieManager;
    private ObservableList<MovieModel> movieList;

    public MovieListModel() throws IOException, MovieException {
        movieManager = new MovieManager();
        movieList = FXCollections.observableArrayList(movieManager.getAllSongs().stream().map(movie -> new MovieModel(movie)).toList());
    }

    /**
     * Get all the movies that should be display in the view.
     * @return ObservableList of Songmodel
     */
    public ObservableList<MovieModel> getMovieList() {
        return movieList;
    }

    /**
     * Create new song to be display in main view
     * @param name
     * @param IMDBRating
     * @param pathToFile
     */
    public void createMovie(String name, double IMDBRating, String pathToFile) throws MovieException {
        movieList.add(new MovieModel(movieManager.createMovie(name, IMDBRating, pathToFile)));
    }

    /**
     * Delete the song in mainview and database
     * @param movieModel
     */
    public void deleteMovie(MovieModel movieModel) throws MovieException {
        movieManager.deleteMovie(movieModel.convertToMovie());
        movieList.remove(movieModel);
    }

    /**
     * Update the song in mainview and database
     * @param movieModel
     * @param name
     * @param imdbRating
     * @param personalRating
     */
    public void updateSong(MovieModel movieModel, String name, double imdbRating, double personalRating) throws MovieException {
        movieModel.setNameProperty(name);
        movieModel.setIMDBRatingProperty(imdbRating);
        movieModel.setPersonalRatingProperty(personalRating);
        movieManager.updateMovie(movieModel.convertToMovie());
    }

    /**
     * Searches through song list, to find a Movie that matches the key word
     * @param query the key word, to search for
     */
    public void searchSong(String query){
        List<MovieModel> searchResults = movieManager.searchSong(query).stream().map(song ->
                new MovieModel(movie)).toList();

        movieList.clear();
        movieList.addAll((searchResults));
    }
}
