package application.api;

import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import application.models.Coin;
import application.Constants;

public class CointrackingAPI {

	public static Coin getCoin(String uuid) throws IOException, InterruptedException, URISyntaxException {
		Coin coin = new Coin();
		coin.setUuid(uuid);
		Gson gson = new Gson();
		String jsonRequest = gson.toJson(coin);

		System.out.println(jsonRequest);

		HttpClient httpClient = HttpClient.newHttpClient();

		HttpRequest getRequest = HttpRequest.newBuilder().uri(new URI(Constants.BASE_URL + "coin/" + coin.getUuid()))
				.header("X-RapidAPI-Key", Constants.X_RAPIDAPI_KEY).header("X-RapidAPI-Host", Constants.X_RAPIDAPI_HOST).GET().build();

		HttpResponse<String> getResponse = httpClient.send(getRequest, BodyHandlers.ofString());
		System.out.println(getResponse.toString());

		coin = gson.fromJson(
				JsonParser.parseString(getResponse.body()).getAsJsonObject().get("data").getAsJsonObject().get("coin"),
				Coin.class);

		return coin;
	}

	public static List<Coin> getCoins() throws IOException, InterruptedException, URISyntaxException {
		List<Coin> coins = new ArrayList<Coin>();
		Gson gson = new Gson();

		HttpClient httpClient = HttpClient.newHttpClient();

		HttpRequest getRequest = HttpRequest.newBuilder().uri(new URI(Constants.BASE_URL + "coins/"))
				.header("X-RapidAPI-Key", Constants.X_RAPIDAPI_KEY).header("X-RapidAPI-Host", Constants.X_RAPIDAPI_HOST).GET().build();

		HttpResponse<String> getResponse = httpClient.send(getRequest, BodyHandlers.ofString());
		System.out.println(getResponse.toString());

		Type listType = new TypeToken<ArrayList<Coin>>() {
		}.getType();

		coins = gson.fromJson(JsonParser.parseString(getResponse.body()).getAsJsonObject().get("data").getAsJsonObject()
				.getAsJsonArray("coins"), listType);

		return coins;
	}
}
