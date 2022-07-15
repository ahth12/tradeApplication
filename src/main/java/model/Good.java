package model;

import javafx.beans.property.*;

public class Good {
    private IntegerProperty goodId;
    private SimpleStringProperty goodName;
    private DoubleProperty goodCost;

    public Good() {
        this.goodId = new SimpleIntegerProperty();
        this.goodName = new SimpleStringProperty();
        this.goodCost = new SimpleDoubleProperty();
    }

    public Good(IntegerProperty goodId, SimpleStringProperty goodName, DoubleProperty goodCost) {
        this.goodId = goodId;
        this.goodName = goodName;
        this.goodCost = goodCost;
    }

    public int getGoodId() {
        return goodId.get();
    }

    public IntegerProperty goodIdProperty() {
        return goodId;
    }

    public void setGoodId(int goodId) {
        this.goodId.set(goodId);
    }

    public String getGoodName() {
        return goodName.get();
    }

    public SimpleStringProperty goodNameProperty() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName.set(goodName);
    }

    public double getGoodCost() {
        return goodCost.get();
    }

    public DoubleProperty goodCostProperty() {
        return goodCost;
    }

    public void setGoodCost(double goodCost) {
        this.goodCost.set(goodCost);
    }

    @Override
    public String toString() {
        return this.getGoodName();
    }
}
