
module calculator {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
	requires org.junit.jupiter.api;


    opens calculator to javafx.graphics, javafx.fxml;
    exports calculator;
}