package jdbc.dto;

import java.io.Serializable;

public class CarType extends Dto implements Serializable, Cloneable {

    private Integer carTypeId;
    private String name;
    private Float costMultiplier;

    public CarType() {
    }

    public Integer getCarTypeId() {
        return carTypeId;
    }

    public void setCarTypeId(Integer carTypeId) {
        this.carTypeId = carTypeId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getCostMultiplier() {
        return costMultiplier;
    }

    public void setCostMultiplier(Float costMultiplier) {
        this.costMultiplier = costMultiplier;
    }

    public Object clone() {
        CarType o = new CarType();
        o.setCarTypeId(this.getCarTypeId());
        o.setName(this.getName());
        o.setCostMultiplier(this.getCostMultiplier());
        return o;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ( !(o instanceof CarType))
            return false;
        CarType that = (CarType) o;
        return (
                compare(this.getCarTypeId(), that.getCarTypeId()) &&
                compare(this.getName(), that.getName()) &&
                compare(this.getCostMultiplier(), that.getCostMultiplier())
                );
    }

    public int hashCode() {
        int result;
        result = hash(SEED, this.getCarTypeId());
        result = hash(result, this.getName());
        result = hash(result, this.getCostMultiplier());
        return result;
    }
}
