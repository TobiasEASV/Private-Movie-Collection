package gui.controller;

import be.MovieException;
import gui.model.MovieListModel;
import be.MovieModel;
import gui.util.SceneSwapper;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import static be.DisplayMessage.displayMessage;

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
    private Button btnEdit;
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
        tcCategory.setCellValueFactory(addMovie -> addMovie.getValue().getAllCategorysAsString());
        tcRating.setCellValueFactory(addMovie -> addMovie.getValue().getIMDBRatingProperty().asObject());

        txtTitle.setDisable(true);
        txtPersonalRating.setDisable(true);
        txtCategory.setDisable(true);
        txtimdbRating.setDisable(true);

        tvMovies.getSelectionModel().selectedItemProperty().addListener((observableValue, oldValue, newValue) -> {
            txtTitle.setText(newValue.getNameProperty().get());
            txtCategory.setText(newValue.getAllCategorysAsString().get());
            txtimdbRating.setText(String.valueOf(newValue.getIMDBRatingProperty().get()));
            txtPersonalRating.setText(String.valueOf(newValue.getPersonalRatingProperty().get()));
        });




        // Search in all Movies
        txtSearch.textProperty().addListener((observableValue, oldValue, newValue) -> {
            try {
                boolean isTitleOn =  cbTitle.isSelected();
                boolean isCatOn = cbCategory.isSelected();
                boolean isRatingOn = cbRating.isSelected();
                movieListModel.searchSong(newValue, isTitleOn, isCatOn, isRatingOn);
            } catch (MovieException e) {
                e.printStackTrace();
            }
        });
    }

    public void handlePlayMovie(ActionEvent actionEvent) {
    }

    public void handleAddMovie(ActionEvent actionEvent) {
    }

    public void handleEditMovie(ActionEvent actionEvent){
        vBoxControllMenu.getChildren().remove(btnAddMovie);
        vBoxControllMenu.getChildren().remove(btnDeleteMovie);
        vBoxControllMenu.getChildren().add(btnEditSave);
        vBoxControllMenu.getChildren().add(btnEditCancel);

        txtTitle.setDisable(false);
        txtPersonalRating.setDisable(false);
        txtCategory.setDisable(false);
        txtimdbRating.setDisable(false);

        btnEdit.setDisable(true);

    }

    public void handleDeleteMovie(ActionEvent actionEvent) throws MovieException {
        MovieModel movie = tvMovies.getSelectionModel().selectedItemProperty().get();
        movieListModel.deleteMovie(movie);
    }

    public void handleEditSave(ActionEvent actionEvent) throws MovieException {
        if( tvMovies.getSelectionModel().selectedItemProperty().get() != null){
            MovieModel movie = tvMovies.getSelectionModel().selectedItemProperty().get();
            movieListModel.updateMovie(movie,txtTitle.getText(), Double.parseDouble(txtimdbRating.getText()), Double.parseDouble(txtPersonalRating.getText()) );

            vBoxControllMenu.getChildren().remove(btnEditSave);
            vBoxControllMenu.getChildren().remove(btnEditCancel);
            vBoxControllMenu.getChildren().add(btnAddMovie);
            vBoxControllMenu.getChildren().add(btnDeleteMovie);

            txtTitle.setDisable(true);
            txtPersonalRating.setDisable(true);
            txtCategory.setDisable(true);
            txtimdbRating.setDisable(true);

            btnEdit.setDisable(false);
        }else{
            displayMessage("Du skal vælge en film først");
        }

    }

    public void handleEditCancel(ActionEvent actionEvent) {
        vBoxControllMenu.getChildren().remove(btnEditSave);
        vBoxControllMenu.getChildren().remove(btnEditCancel);
        vBoxControllMenu.getChildren().add(btnAddMovie);
        vBoxControllMenu.getChildren().add(btnDeleteMovie);

        txtTitle.setDisable(true);
        txtPersonalRating.setDisable(true);
        txtCategory.setDisable(true);
        txtimdbRating.setDisable(true);

        btnEdit.setDisable(false);
    }

}
