package application;

import java.util.List;

import application.api.CoinsAPIService;
import application.api.CoinsServiceGenerator;
import application.models.Coin;
import application.models.Coins;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

@Slf4j
public class BrowseSceneController {

	@FXML
	private Button personalButton;
	@FXML
	private Button browseButton;
	@FXML
	private Button refreshButton;
	@FXML
	private ChoiceBox<String> searchChoiceBox;
	@FXML
	private TextField searchTextField;
	@FXML
	private VBox dataContainer;
	@FXML
	private TableView tableView;

	private ObservableList<Coin> coinsOL = FXCollections.observableArrayList();

	public BrowseSceneController() {
	}
	
	@FXML
    private void initialize() {
        // search panel
		refreshButton.setText("Search");
//		refreshButton.setOnAction(event -> loadData());
		refreshButton.setStyle("-fx-background-color: #457ecd; -fx-text-fill: #ffffff;");

        initTable();
    }
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	private void initTable() {        
	    tableView = new TableView<>();
		TableColumn icon = new TableColumn("Icon");
		icon.setCellValueFactory(new PropertyValueFactory("icon"));
		TableColumn name = new TableColumn("Name");
		icon.setCellValueFactory(new PropertyValueFactory("name"));
		TableColumn symbol = new TableColumn("Symbol");
		icon.setCellValueFactory(new PropertyValueFactory("symbol"));
		TableColumn price = new TableColumn("Price");
		icon.setCellValueFactory(new PropertyValueFactory("price"));
		TableColumn marketCap = new TableColumn("MarketCap");
		icon.setCellValueFactory(new PropertyValueFactory("marketCap"));
		TableColumn rank = new TableColumn("Rank");
		icon.setCellValueFactory(new PropertyValueFactory("rank"));
	    dataContainer.getChildren().add(tableView);
	}

//	@FXML
//	private void initialize() {
//		personalButton.setOnAction(event -> {
//			try {
//				switchToPersonal(event);
//			} catch (Exception e) {
//				log.info("Unsuccessfull switch to personal scene " + e);
//			}
//		});
//
//		browseButton.setOnAction(event -> {
//			getCoins();
//		});
//
//		initTable();
//	}
//
//	private void switchToPersonal(ActionEvent event) throws Exception {
//		Parent root = FXMLLoader.load(getClass().getResource("PersonalScene.fxml"));
//		Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
//		Scene scene = new Scene(root);
//		stage.setScene(scene);
//		stage.show();
//	}
//
//	@SuppressWarnings({ "unchecked", "rawtypes" })
//	private void initTable() {
//		tableView = new TableView<>(FXCollections.observableList(coinsOL));
//		TableColumn icon = new TableColumn("Icon");
//		icon.setCellValueFactory(new PropertyValueFactory("icon"));
//		TableColumn name = new TableColumn("Name");
//		icon.setCellValueFactory(new PropertyValueFactory("name"));
//		TableColumn symbol = new TableColumn("Symbol");
//		icon.setCellValueFactory(new PropertyValueFactory("symbol"));
//		TableColumn price = new TableColumn("Price");
//		icon.setCellValueFactory(new PropertyValueFactory("price"));
//		TableColumn marketCap = new TableColumn("MarketCap");
//		icon.setCellValueFactory(new PropertyValueFactory("marketCap"));
//		TableColumn rank = new TableColumn("Rank");
//		icon.setCellValueFactory(new PropertyValueFactory("rank"));
//		tableView.getColumns().addAll(icon, name, symbol, price, marketCap, rank);
//
//	}
//
//	private void getCoins() {
//		CoinsAPIService coinsService = CoinsServiceGenerator.createService(CoinsAPIService.class);
//
//		Call<Coins> call = coinsService.getCoins();
//		call.enqueue(new Callback<Coins>() {
//
//			@Override
//			public void onResponse(Call<Coins> call, Response<Coins> response) {
//				log.info("Get Coins Success");
//				Coins coins = response.body();
//				List<Coin> coinsList = coins.getCoinsList();
//				ObservableList<Coin> coinss = FXCollections.observableArrayList(coinsList);
//				System.out.println(coinss);
//
//			}
//
//			@Override
//			public void onFailure(Call<Coins> call, Throwable t) {
//				log.info("Get Coins Failure");
//			}
//		});
//	}
}
