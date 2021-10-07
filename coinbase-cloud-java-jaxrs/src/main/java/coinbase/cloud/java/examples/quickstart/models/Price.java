package coinbase.cloud.java.examples.quickstart.models;

import com.google.gson.annotations.SerializedName;

public class Price {
    @SerializedName("base")
    private String base;

    @SerializedName("amount")
    private String amount;

    @SerializedName("currency")
    private String currency;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Price{" +
                "base='" + base + '\'' +
                ", amount='" + amount + '\'' +
                ", currency='" + currency + '\'' +
                '}';
    }
}
