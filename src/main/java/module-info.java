module app {
    requires spring.data.mongodb;
    requires spring.context;
    requires spring.data.commons;
    requires org.junit.jupiter.api;
    requires java.mail;
    requires javafx.controls;
    requires org.mongodb.driver.core;
    requires org.mongodb.driver.sync.client;

    opens core to javafx.fxml;
    opens config;
    opens entities.tracks;
    opens entities.artists;
    opens entities.disks;
    exports core;
}