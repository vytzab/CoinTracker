package application;

import application.api.CointrackingAPI;
import application.models.Coin;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class BrowseSceneController {

	@FXML
	private TextField searchField;
	@FXML
	private Button searchButton;
	@FXML
	private Label searchLabel;
	@FXML
	private Button addToPersonalButton;
	@FXML
	private Button personalButton;
	@FXML
	private VBox dataContainer;
	@SuppressWarnings("rawtypes")
	@FXML
	private TableView tableView;

	private ObservableList<Coin> masterData = FXCollections.observableArrayList();
	private ObservableList<Coin> results = FXCollections.observableList(masterData);
	
	// list to hold personal coin list
	private ObservableList<Coin> personalList = FXCollections.observableArrayList();

	public BrowseSceneController() {
		masterData.add(new Coin("uuid", "BTC", "Bitcoin", "iconpath", 111.1, 11.1, 1.1));
		masterData.add(new Coin("uuid", "ETH", "Ethereum", "iconpath", 111.1, 11.1, 1.1));
		masterData.add(new Coin("uuid", "XRP", "Ripple", "iconpath", 111.1, 11.1, 1.1));
	}

	@FXML
	private void initialize() {
		// search panel
		searchButton.setOnAction(event -> loadData());
		searchButton.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");

		searchField.setOnKeyPressed(event -> {
			if (event.getCode().equals(KeyCode.ENTER)) {
				loadData();
			}
		});

		searchField.textProperty().addListener((observable, oldValue, newValue) -> {
			searchLabel.setText(newValue);
		});

		// add to personal

		// switch to personal
		personalButton.setOnAction(event -> {
			try {
				switchToPersonal(event);
			} catch (Exception e) {
				log.info("Unsuccessfull switch to personal scene " + e);
			}
		});

		initTable();
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initTable() {
		tableView = new TableView<>(FXCollections.observableList(masterData));
		tableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

		TableColumn name = new TableColumn("Name");
		name.setCellValueFactory(new PropertyValueFactory("name"));
		TableColumn symbol = new TableColumn("Symbol");
		symbol.setCellValueFactory(new PropertyValueFactory("symbol"));
		TableColumn price = new TableColumn("Price");
		price.setCellValueFactory(new PropertyValueFactory("price"));
		TableColumn marketCap = new TableColumn("MarketCap");
		marketCap.setCellValueFactory(new PropertyValueFactory("marketCap"));
		TableColumn rank = new TableColumn("Rank");
		rank.setCellValueFactory(new PropertyValueFactory("rank"));
		tableView.getColumns().addAll(name, symbol, price, marketCap, rank);

		dataContainer.getChildren().add(tableView);
	}

	@SuppressWarnings("unchecked")
	private void loadData() {

		// TODO implement search
//		String searchText = searchField.getText();

		Task<ObservableList<Coin>> task = new Task<ObservableList<Coin>>() {
			@Override
			protected ObservableList<Coin> call() throws Exception {
				updateMessage("Loading data");
				return FXCollections.observableList(CointrackingAPI.getCoins());
			}
		};

		task.setOnSucceeded(event -> {
			results = task.getValue();
			tableView.setItems(results);
		});

		Thread th = new Thread(task);
		th.setDaemon(true);
		th.start();
	}

	private void switchToPersonal(ActionEvent event) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("PersonalScene.fxml"));
		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
	}
	
	private void addToPersonal(Coin coin) {
		personalList.add(coin);
	}
}
