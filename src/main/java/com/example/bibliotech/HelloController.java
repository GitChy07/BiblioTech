package com.example.bibliotech;

import javafx.fxml.FXML;
import javafx.scene.control.*;

public class HelloController {
    public Button addMediaButton;
    public Button searchButton;
    public Button manageButton;
    public Button addButton;
    public Button deleteButton;
    public TextField YearTF;
    public TextField AuthorTF;
    public TextField TitleTF;
    public TableView mediaTable;
    public TableColumn titleColumn;
    public TableColumn yearColumn;
    public TableColumn authorColumn;
    public TableColumn typColumn;
    public RadioButton buchRB;
    public RadioButton dvdRB;

    @FXML
    protected void toggleA() {
        if (dvdRB.isSelected()) {
            dvdRB.setSelected(false);
            buchRB.setSelected(true);
        }
    }
    @FXML
    protected void toggleB() {
        if (buchRB.isSelected()) {
            buchRB.setSelected(false);
            dvdRB.setSelected(true);
        }
    }
}