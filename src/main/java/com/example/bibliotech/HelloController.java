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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;


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
        medienListe.addAll(Database.getAllMedien());
        mediaTable.setItems(medienListe);

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
            Alert warnung = new Alert(AlertType.WARNING);
            warnung.setTitle("Fehler");
            warnung.setHeaderText("FEHLER!!!!!");
            warnung.setContentText("Bitte gültiges Jahr eingeben. dangge duusig!");
            warnung.show();
            return;
        }

        if (titel.isEmpty() || autor.isEmpty()) {
            Alert warnung2 = new Alert(AlertType.WARNING);
            warnung2.setTitle("Fehler");
            warnung2.setHeaderText("FEHLER!!!!!");
            warnung2.setContentText("Bitte alle Felder ausfüllen. dangge duusig!");
            warnung2.show();
            return;
        }

        IMedium medium = null;

        if (buchRB.isSelected()) {
            medium = new Buch(titel, autor, jahr);
            Database.insertMedium(medium, "Buch");
            medienListe.add(medium);
            TitleTF.clear();
            AuthorTF.clear();
            YearTF.clear();
        } else if (dvdRB.isSelected()) {
            medium = new DVD(titel, autor, jahr);
            Database.insertMedium(medium, "DVD");
            medienListe.add(medium);
            TitleTF.clear();
            AuthorTF.clear();
            YearTF.clear();
        } else {
            Alert warnung3 = new Alert(AlertType.WARNING);
            warnung3.setTitle("Fehler");
            warnung3.setHeaderText("FEHLER!!!!!");
            warnung3.setContentText("Bitte Medium auswählen. dangge duusig!");
            warnung3.show();
        }
    }

    @FXML
    protected void onDeleteButtonClick() {
        IMedium ausgewähltesMedium = mediaTable.getSelectionModel().getSelectedItem();

        if (ausgewähltesMedium != null) {
            Alert bestaetigung = new Alert(AlertType.CONFIRMATION);
            bestaetigung.setTitle("Löschen bestätigen");
            bestaetigung.setHeaderText("Medium entfernen");
            bestaetigung.setContentText("Medium \"" + ausgewähltesMedium.getTitel() + "\" löschen?");

            // Warten auf die Antwort des Benutzers
            bestaetigung.showAndWait().ifPresent(response -> {
                if (response == ButtonType.OK) {
                    medienListe.remove(ausgewähltesMedium);
                    Database.deleteMedium(ausgewähltesMedium);
                }
            });
        } else {
            Alert warnung = new Alert(AlertType.WARNING);
            warnung.setTitle("Keine Auswahl");
            warnung.setHeaderText("Kein Medium ausgewählt");
            warnung.setContentText("Bitte wähle zuerst ein Medium in der Tabelle aus.");
            warnung.show();
        }
    }




}