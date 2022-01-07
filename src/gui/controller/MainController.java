package gui.controller;

import be.MovieException;
import gui.model.MovieListModel;
import gui.model.MovieModel;
import gui.util.SceneSwapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {
    @FXML
    private TableView<MovieModel> tvMovies;
    @FXML
    private TableColumn<MovieModel, String> tcTitle;
    @FXML
    private TableColumn<MovieModel, String> tcCategory;
    @FXML
    private TableColumn<MovieModel, Double> tcRating;

    @FXML
    private CheckBox cbTitle;
    @FXML
    private CheckBox cbCategory;
    @FXML
    private CheckBox cbRating;

    @FXML
    private TextField txtSearch;
    @FXML
    private TextField txtTitle;
    @FXML
    private TextField txtPersonalRating;
    @FXML
    private TextField txtCategory;
    @FXML
    private TextField txtimdbRating;

    @FXML
    private VBox vBoxControllMenu;

    @FXML
    private Button btnAddMovie;
    @FXML
    private Button btnDeleteMovie;
    @FXML
    private Button btnEditSave;
    @FXML
    private Button btnEditCancel;

    private MovieListModel movieListModel;
    private SceneSwapper sceneSwapper;

    public MainController() throws IOException, MovieException {
        movieListModel = new MovieListModel();
        sceneSwapper = new SceneSwapper();


    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        vBoxControllMenu.getChildren().remove(btnEditCancel);
        vBoxControllMenu.getChildren().remove(btnEditSave);

        tvMovies.setPlaceholder(new Label("No movies found in Database"));

        tvMovies.setItems(movieListModel.getMovieList());
        tcTitle.setCellValueFactory(addMovie -> addMovie.getValue().getNameProperty());
        tcCategory.setCellValueFactory(addMovie -> addMovie.getValue().getCategorys());
        tcRating.setCellValueFactory(addMovie -> addMovie.getValue().getIMDBRatingProperty().asObject());



    }

    public void handlePlayMovie(ActionEvent actionEvent) {
    }

    public void handleAddMovie(ActionEvent actionEvent) {
    }

    public void handleEditMovie(ActionEvent actionEvent) {
        vBoxControllMenu.getChildren().remove(btnAddMovie);
        vBoxControllMenu.getChildren().remove(btnDeleteMovie);
        vBoxControllMenu.getChildren().add(btnEditSave);
        vBoxControllMenu.getChildren().add(btnEditCancel);
    }

    public void handleDeleteMovie(ActionEvent actionEvent) {
    }

    public void handleEditSave(ActionEvent actionEvent) {
        vBoxControllMenu.getChildren().remove(btnEditSave);
        vBoxControllMenu.getChildren().remove(btnEditCancel);
        vBoxControllMenu.getChildren().add(btnAddMovie);
        vBoxControllMenu.getChildren().add(btnDeleteMovie);
    }

    public void handleEditCancel(ActionEvent actionEvent) {
        vBoxControllMenu.getChildren().remove(btnEditSave);
        vBoxControllMenu.getChildren().remove(btnEditCancel);
        vBoxControllMenu.getChildren().add(btnAddMovie);
        vBoxControllMenu.getChildren().add(btnDeleteMovie);
    }

}
