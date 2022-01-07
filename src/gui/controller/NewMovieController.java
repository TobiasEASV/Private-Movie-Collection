package gui.controller;


import be.Movie;
import be.MovieException;
import gui.model.MovieListModel;
import gui.model.MovieModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class NewMovieController {

    final FileChooser fileChooser;
    public Button saveBtn;
    private File file;

    @FXML
    public TextField txtTitle;

    @FXML
    public TextField txtPersonalScore;

    @FXML
    private TextField txtImdb;

    @FXML
    private TextField txtGenre;

    @FXML
    private GridPane gridPaneId;

    @FXML
    private TextField txtChooseFile;

    MovieListModel movieListModel;

    public NewMovieController() {
    fileChooser = new FileChooser();
    }

    public void initialize() throws IOException, MovieException {
    movieListModel = new MovieListModel();
    }

    public void handleChooseBtn(ActionEvent actionEvent) {
        Stage stage = (Stage) gridPaneId.getScene().getWindow();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Movie", "*.mp4", "*.mpeg4"));
        File file = fileChooser.showOpenDialog(stage); // assigns the chosen file to the file
        if (file != null) { //DirectoryChooser returns null if the user closes the browse window
            txtChooseFile.setText(file.getAbsolutePath().replace("\\", "/"));
        }
    }

    public void uploadMovieInfo(String Title, Double PersonalScore, String Imdb)throws IOException, MovieException {
        movieListModel.createMovie(Title, PersonalScore, Imdb);

    }

    public void getMovieInfo()throws IOException, MovieException {
        String uploadTitle = Title();
        Double uploadPersonalScore = PersonalScore();
        String uploadImdb = Imdb();
        uploadMovieInfo(uploadTitle, uploadPersonalScore, uploadImdb);
    }

    public String Title(){
    String titleTemp = txtTitle.getText();
    return titleTemp;
    }

    public Double PersonalScore(){
        Double personalScoreTemp = Double.parseDouble(txtPersonalScore.getText());
        return personalScoreTemp;
    }

    public String Imdb(){
        String imdbTemp = txtImdb.getText();
        return imdbTemp;
    }
}

