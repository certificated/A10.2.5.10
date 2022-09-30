module application.blueprint {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;
    requires transitive java.sql.rowset;
	
	requires java.desktop;
	requires java.sql;
	requires transitive javafx.base;

    opens application.blueprint to javafx.fxml;
    exports application.blueprint;
}

