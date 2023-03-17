package application.models;

public class Coin {
	private String uuid;
	private String symbol;
	private String name;
	private String icon;
	private Double marketCap;
	private Double rank;
	private Double price;

	public Coin() {
		
	}
	
	public Coin(String uuid, String symbol, String name, String icon, Double marketCap, Double rank, Double price) {
		super();
		this.uuid = uuid;
		this.symbol = symbol;
		this.name = name;
		this.icon = icon;
		this.marketCap = marketCap;
		this.rank = rank;
		this.price = price;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getSymbol() {
		return symbol;
	}

	public void setSymbol(String symbol) {
		this.symbol = symbol;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public Double getMarketCap() {
		return marketCap;
	}

	public void setMarketCap(Double marketCap) {
		this.marketCap = marketCap;
	}

	public Double getRank() {
		return rank;
	}

	public void setRank(Double rank) {
		this.rank = rank;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Coin [uuid=" + uuid + ", symbol=" + symbol + ", name=" + name + ", icon=" + icon + ", marketCap="
				+ marketCap + ", rank=" + rank + ", price=" + price + "]";
	}
}
