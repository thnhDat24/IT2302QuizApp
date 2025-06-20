module com.ltd.quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.base;
    requires lombok;
    requires java.sql;

    opens com.ltd.quizapp to javafx.fxml;
    exports com.ltd.quizapp;
}
