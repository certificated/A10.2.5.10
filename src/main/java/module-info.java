module application.blueprint {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive java.sql.rowset;
	
	requires java.desktop;
	requires java.sql;
	requires transitive javafx.base;
	requires jsch;

	opens DAO to javafx.fxml;
	opens controller to javafx.fxml;
	opens application.blueprint to javafx.fxml;
     exports controller;
    exports application.blueprint;
    exports DAO;
    opens Class to javafx.fxml;
    exports Class;
}

