package com.example.bibliotech;
import com.example.bibliotech.medium.Buch;
import com.example.bibliotech.medium.DVD;
import com.example.bibliotech.medium.IMedium;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.net.URL;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    private ObservableList<IMedium> medienListe = FXCollections.observableArrayList();

    public Button addMediaButton;
    public Button searchButton;
    public Button manageButton;
    public Button addButton;
    public Button deleteButton;
    public TextField YearTF;
    public TextField AuthorTF;
    public TextField TitleTF;
    public TableView<IMedium> mediaTable;
    public TableColumn<IMedium, String> titleColumn;
    public TableColumn<IMedium, String> authorColumn;
    public TableColumn<IMedium, Integer> yearColumn;
    public TableColumn<IMedium, String> typColumn;
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

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        titleColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getTitel()));
        authorColumn.setCellValueFactory(cellData -> new ReadOnlyStringWrapper(cellData.getValue().getAutor()));
        yearColumn.setCellValueFactory(cellData -> new ReadOnlyObjectWrapper<>(cellData.getValue().getErscheinungsjahr()));

        typColumn.setCellValueFactory(cellData -> {
            if (cellData.getValue() instanceof Buch) {
                return new ReadOnlyStringWrapper("Buch");
            } else if (cellData.getValue() instanceof DVD) {
                return new ReadOnlyStringWrapper("DVD");
            } else {
                return new ReadOnlyStringWrapper("Unbekannt");
            }
        });

        mediaTable.setItems(medienListe);
    }


    @FXML
    protected void onAddButtonClick() {
        String titel = TitleTF.getText();
        String autor = AuthorTF.getText();
        int jahr;

        try {
            jahr = Integer.parseInt(YearTF.getText());
        } catch (NumberFormatException e) {
            System.out.println("Ungültiges Jahr");
            return;
        }

        if (titel.isEmpty() || autor.isEmpty()) {
            System.out.println("Bitte alle Felder ausfüllen");
            return;
        }

        IMedium medium;

        if (buchRB.isSelected()) {
            medium = new Buch(titel, autor, jahr);
        } else if (dvdRB.isSelected()) {
            medium = new DVD(titel, autor, jahr); // Dauer vorerst fix oder später als Eingabe
        } else {
            System.out.println("Kein Medientyp ausgewählt");
            return;
        }

        medienListe.add(medium);
        TitleTF.clear();
        AuthorTF.clear();
        YearTF.clear();
    }



}