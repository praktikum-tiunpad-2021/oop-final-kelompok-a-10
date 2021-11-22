module com.snakegame {
    // requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive javafx.controls;

    opens com.snakegame to javafx.fxml;
    exports com.snakegame;
}