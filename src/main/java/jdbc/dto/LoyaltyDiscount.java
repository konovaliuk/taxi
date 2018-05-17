package jdbc.dto;

import java.io.Serializable;

public class LoyaltyDiscount extends Dto implements Serializable, Cloneable {

    private Integer loyaltyDiscountId;
    private Integer ordersThreshold;
    private Float costMultiplier;

    public LoyaltyDiscount() {
    }

    public Integer getLoyaltyDiscountId() {
        return loyaltyDiscountId;
    }

    public void setLoyaltyDiscountId(Integer loyaltyDiscountId) {
        this.loyaltyDiscountId = loyaltyDiscountId;
    }

    public Integer getOrdersThreshold() {
        return ordersThreshold;
    }

    public void setOrdersThreshold(Integer ordersThreshold) {
        this.ordersThreshold = ordersThreshold;
    }

    public Float getCostMultiplier() {
        return costMultiplier;
    }

    public void setCostMultiplier(Float costMultiplier) {
        this.costMultiplier = costMultiplier;
    }

    public Object clone() {
        LoyaltyDiscount o = new LoyaltyDiscount();
        o.setLoyaltyDiscountId(this.getLoyaltyDiscountId());
        o.setOrdersThreshold(this.getOrdersThreshold());
        o.setCostMultiplier(this.getCostMultiplier());
        return o;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ( !(o instanceof LoyaltyDiscount))
            return false;
        LoyaltyDiscount that = (LoyaltyDiscount) o;
        return (
                compare(this.getLoyaltyDiscountId(), that.getLoyaltyDiscountId()) &&
                compare(this.getOrdersThreshold(), that.getOrdersThreshold()) &&
                compare(this.getCostMultiplier(), that.getCostMultiplier())
                );
    }

    public int hashCode() {
        int result;
        result = hash(SEED, this.getLoyaltyDiscountId());
        result = hash(result, this.getOrdersThreshold());
        result = hash(result, this.getCostMultiplier());
        return result;
    }
}
