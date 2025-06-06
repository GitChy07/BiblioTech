package com.example.bibliotech;
import com.example.bibliotech.medium.Buch;
import com.example.bibliotech.medium.DVD;
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

    private final ObservableList<IMedium> medienListe = FXCollections.observableArrayList();

    public Button addMediaButton;
    public Button searchButton;
    public Button manageButton;

    public TableView<IMedium> mediaTable;

    public TableColumn<IMedium, String> titleColumn;
    public TableColumn<IMedium, String> authorColumn;
    public TableColumn<IMedium, Integer> yearColumn;
    public TableColumn<IMedium, String> typColumn;

    public TextField YearTF;
    public TextField AuthorTF;
    public TextField TitleTF;
    public RadioButton buchRB;
    public RadioButton dvdRB;

    public Button addButton;
    public Button deleteButton;

    public Label errorLabel;

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

        if (titel.isEmpty() || autor.isEmpty() || YearTF.getText().isEmpty()) {
            if (!YearTF.getText().isEmpty()) {
                try {
                    Integer.parseInt(YearTF.getText());
                } catch (NumberFormatException e) {
                    System.out.println("Ungültiges Jahr");
                    errorLabel.setText("Ungültiges Jahr");
                    return;
                }
                return;
            } else {
                System.out.println("Bitte alle Felder ausfüllen");
                errorLabel.setText("Bitte alle Felder ausfüllen");
                return;
            }
        }

        try {
            Integer.parseInt(YearTF.getText());
        } catch (NumberFormatException e) {
            System.out.println("Ungültiges Jahr");
            errorLabel.setText("Ungültiges Jahr");
            return;
        }

        jahr = Integer.parseInt(YearTF.getText());

        if (jahr == 0) {
            System.out.println("Ungültiges Jahr");
            errorLabel.setText("Ungültiges Jahr");
            return;
        }

        IMedium medium = null;

        if (buchRB.isSelected()) {
            medium = new Buch(titel, autor, jahr);
            Database.insertMedium(medium, "Buch");
        } else if (dvdRB.isSelected()) {
            medium = new DVD(titel, autor, jahr);
            Database.insertMedium(medium, "DVD");
        } else {
            System.out.println("Medien Typ auswählen");
            errorLabel.setText("Medien Typ auswählen");
            return;
        }

        medienListe.add(medium);
        TitleTF.clear();
        AuthorTF.clear();
        YearTF.clear();
        errorLabel.setText("");

    }

    @FXML
    protected void onDeleteButtonClick() {
        IMedium ausgewähltesMedium = mediaTable.getSelectionModel().getSelectedItem();

        if (ausgewähltesMedium != null) {
            Alert bestaetigung = new Alert(AlertType.CONFIRMATION);
            bestaetigung.setTitle("Löschen bestätigen");
            bestaetigung.setHeaderText("Medium wirklich löschen?");
            bestaetigung.setContentText("Möchtest du das Medium \"" + ausgewähltesMedium.getTitel() + "\" wirklich entfernen?");

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