package application.models;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class Coins {

    @SerializedName("Basic")
    public List<Coin> coinList;

    public List<Coin> getCoinsList() {
        return coinList;
    }

    public void setCoinsList(List<Coin> coinList) {
        this.coinList = coinList;
    }

	@Override
	public String toString() {
		return "Coins [coinList=" + coinList + "]";
	}
}
