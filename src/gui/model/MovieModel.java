package gui.model;

import be.Movie;
import javafx.beans.property.*;

import java.text.DecimalFormat;
import java.util.Date;

public class MovieModel {
    private IntegerProperty id = new SimpleIntegerProperty();
    private StringProperty name = new SimpleStringProperty();
    private DoubleProperty IMDBRating = new SimpleDoubleProperty();
    private StringProperty pathToFile = new SimpleStringProperty();
    private ObjectProperty lastView = new SimpleObjectProperty<Date>();
    private DoubleProperty personalRating = new SimpleDoubleProperty();

    private DecimalFormat oneDecimal = new DecimalFormat("#.#");

    public MovieModel(Movie movie){
        setIdProperty(movie.getId());
        setNameProperty(movie.getName());
        setIMDBRatingProperty(movie.getIMDBRating());
        setPathToFileProperty(movie.getPathToFile());
        setLastView(movie.getLastView());
    }

    /**
     * Used for setting the id of the Movie, only used when a movie is created.
     * @param id
     */
    private void setIdProperty(int id){
        getIdProperty().set(id);
    }

    /**
     * Used for getting the id of the movie.
     * @return
     */
    public IntegerProperty getIdProperty() {
        return id;
    }

    /**
     * Used for setting the name of the movie.
     * @param name
     */
    public void setNameProperty(String name){
        getNameProperty().set(name);
    }

    /**
     * Used for getting the name of the movie.
     * @return the name of the movie
     */
    public StringProperty getNameProperty(){
        return name;
    }

    /**
     * Used for getting the IMDB rating of the movie.
     * @param IMDBRating
     */
    public void setIMDBRatingProperty(double IMDBRating){
        getIMDBRatingProperty().set(IMDBRating);
    }

    /**
     * Used for getting the IMDB rating of the movie.
     * @return the IMDB rating of the movie
     */
    private DoubleProperty getIMDBRatingProperty() {
        return IMDBRating;
    }

    /**
     * Used for setting the path of the movie locally.
     * @param pathToFile
     */
    public void setPathToFileProperty(String pathToFile){
        getPathToFileProperty().set(pathToFile);
    }

    /**
     * Used for getting the path of the movie.
     * @return the file path of the movie
     */
    private StringProperty getPathToFileProperty() {
        return pathToFile;
    }

    /**
     * used for getting the Date of when the song was viewed last.
     * @return returns a ObjectProperty of type Date of when the movie was last viewed.
     */
    public ObjectProperty getLastView() {
        return lastView;
    }

    /**
     * sets the Date of when the movie was last viewed.
     * @param lastView
     */
    public void setLastView(Date lastView) {
        getLastView().set(lastView);
    }

    /**
     * Used for converting a movieModel into a movie object, mainly for storage in DB
     * @return a movie object with the same fields as the movieModel
     */
    public Movie convertToMovie(){
        return new Movie(id.get(), name.get(), IMDBRating.get(), pathToFile.get(), (Date)lastView.get());
    }
}
