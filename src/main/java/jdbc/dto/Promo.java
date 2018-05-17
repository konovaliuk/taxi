package jdbc.dto;

import java.io.Serializable;

public class Promo extends Dto implements Serializable, Cloneable {

    private Integer promoId;
    private String name;
    private String conditions;
    private Float costMultiplier;

    public Promo() {
    }

    public Integer getPromoId() {
        return promoId;
    }

    public void setPromoId(Integer promoId) {
        this.promoId = promoId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConditions() {
        return conditions;
    }

    public void setConditions(String conditions) {
        this.conditions = conditions;
    }

    public Float getCostMultiplier() {
        return costMultiplier;
    }

    public void setCostMultiplier(Float costMultiplier) {
        this.costMultiplier = costMultiplier;
    }

    public Object clone() {
        Promo o = new Promo();
        o.setPromoId(this.getPromoId());
        o.setName(this.getName());
        o.setConditions(this.getConditions());
        o.setCostMultiplier(this.getCostMultiplier());
        return o;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ( !(o instanceof Promo))
            return false;
        Promo that = (Promo) o;
        return (
                compare(this.getPromoId(), that.getPromoId()) &&
                compare(this.getName(), that.getName()) &&
                compare(this.getConditions(), that.getConditions()) &&
                compare(this.getCostMultiplier(), that.getCostMultiplier())
                );
    }

    public int hashCode() {
        int result;
        result = hash(SEED, this.getPromoId());
        result = hash(result, this.getName());
        result = hash(result, this.getConditions());
        result = hash(result, this.getCostMultiplier());
        return result;
    }
}
