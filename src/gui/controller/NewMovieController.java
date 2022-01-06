package gui.controller;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;

public class NewMovieController {

    final FileChooser fileChooser;
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

    public NewMovieController() {
    fileChooser = new FileChooser();
    }

    public void handleChooseBtn(ActionEvent actionEvent) {
        Stage stage = (Stage) gridPaneId.getScene().getWindow();
        fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("Movie", "*.mp4", "*.mpeg4"));
        File file = fileChooser.showOpenDialog(stage); // assigns the chosen file to the file
        if (file != null) { //DirectoryChooser returns null if the user closes the browse window
            txtChooseFile.setText(file.getAbsolutePath().replace("\\", "/"));
        }
    }
}

