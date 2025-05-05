module com.example.bibliotech {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens com.example.bibliotech to javafx.fxml;
    exports com.example.bibliotech;
    exports com.example.bibliotech.medium;
    opens com.example.bibliotech.medium to javafx.fxml;
}