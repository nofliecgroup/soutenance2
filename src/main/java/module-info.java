module fr.greta.soutenance2application {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires java.sql;
    requires mysql.connector.java;


    requires htmlunit;
    requires sib.api.v3.sdk;

    opens fr.greta.soutenance2application to javafx.fxml;
    exports fr.greta.soutenance2application;
}