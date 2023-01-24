module inube.mahdi.platformer {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.kordamp.bootstrapfx.core;

    opens inube.mahdi.platformer to javafx.fxml;
    exports inube.mahdi.platformer;
}