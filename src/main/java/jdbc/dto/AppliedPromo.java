package jdbc.dto;

import java.io.Serializable;

public class AppliedPromo extends Dto implements Serializable, Cloneable {

    private Integer appliedPromoId;
    private Integer promoId;
    private Integer ordersOrderId;

    public AppliedPromo() {
    }

    public Integer getAppliedPromoId() {
        return appliedPromoId;
    }

    public void setAppliedPromoId(Integer appliedPromoId) {
        this.appliedPromoId = appliedPromoId;
    }

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }

    public Integer getOrdersOrderId() {
        return ordersOrderId;
    }

    public void setOrdersOrderId(Integer ordersOrderId) {
        this.ordersOrderId = ordersOrderId;
    }



    public Object clone() {
        AppliedPromo o = new AppliedPromo();
        o.setAppliedPromoId(this.getAppliedPromoId());
        o.setPromoId(this.getPromoId());
        o.setOrdersOrderId(this.getOrdersOrderId());
        return o;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ( !(o instanceof AppliedPromo))
            return false;
        AppliedPromo that = (AppliedPromo) o;
        return (
                compare(this.getAppliedPromoId(), that.getAppliedPromoId()) &&
                compare(this.getPromoId(), that.getPromoId()) &&
                compare(this.getOrdersOrderId(), that.getOrdersOrderId())
                );
    }

    public int hashCode() {
        int result;
        result = hash(SEED, this.getAppliedPromoId());
        result = hash(result, this.getPromoId());
        result = hash(result, this.getOrdersOrderId());
        return result;
    }


}
