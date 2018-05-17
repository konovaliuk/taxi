package jdbc.dto;

import java.io.Serializable;

public class Order extends Dto implements Serializable, Cloneable {

    private Integer orderId;
    private Integer clientId;
    private Integer carId;
    private Integer loyaltyDiscount;
    private String orderState;
    private Float distanceCost;

    public Order() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getLoyaltyDiscount() {
        return loyaltyDiscount;
    }

    public void setLoyaltyDiscount(Integer loyaltyDiscount) {
        this.loyaltyDiscount = loyaltyDiscount;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public Float getDistanceCost() {
        return distanceCost;
    }

    public void setDistanceCost(Float distanceCost) {
        this.distanceCost = distanceCost;
    }

    public Object clone() {
        Order o = new Order();
        o.setOrderId(this.getOrderId());
        o.setClientId(this.getClientId());
        o.setCarId(this.getCarId());
        o.setLoyaltyDiscount(this.getLoyaltyDiscount());
        o.setOrderState(this.getOrderState());
        o.setDistanceCost(this.getDistanceCost());
        return o;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ( !(o instanceof Order))
            return false;
        Order that = (Order) o;
        return (
                compare(this.getOrderId(), that.getOrderId()) &&
                compare(this.getClientId(), that.getClientId()) &&
                compare(this.getCarId(), that.getCarId()) &&
                compare(this.getLoyaltyDiscount(), that.getLoyaltyDiscount()) &&
                compare(this.getOrderState(), that.getOrderState()) &&
                compare(this.getDistanceCost(), that.getDistanceCost())
                );
    }

    public int hashCode() {
        int result;
        result = hash(SEED, this.getOrderId());
        result = hash(result, this.getClientId());
        result = hash(result, this.getCarId());
        result = hash(result, this.getLoyaltyDiscount());
        result = hash(result, this.getOrderState());
        result = hash(result, this.getDistanceCost());
        return result;
    }
}
