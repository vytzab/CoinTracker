module CoinTracker {
	requires javafx.controls;
	requires javafx.graphics;
	requires javafx.fxml;
	requires com.google.gson;
	requires lombok;
    requires org.slf4j;
	requires retrofit2;
	requires logging.interceptor;
	requires okhttp3;
	requires retrofit2.converter.gson;
	requires unirest.java;

	opens application to javafx.graphics, javafx.fxml, com.google.gson;
	opens application.models to com.google.gson;
}