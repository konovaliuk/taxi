package jdbc.dto;

import java.io.Serializable;

public class Car extends Dto implements Serializable, Cloneable {

    private Integer carId;
    private Integer type;
    private String state;
    private String regNumber;
    private String model;

    public Car() {
    }

    public Integer getCarId() {
        return carId;
    }

    public void setCarId(Integer carId) {
        this.carId = carId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getRegNumber() {
        return regNumber;
    }

    public void setRegNumber(String regNumber) {
        this.regNumber = regNumber;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Object clone() {
        Car o = new Car();
        o.setCarId(this.getCarId());
        o.setType(this.getType());
        o.setState(this.getState());
        o.setRegNumber(this.getRegNumber());
        o.setModel(this.getModel());
        return o;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ( !(o instanceof Car))
            return false;
        Car that = (Car) o;
        return (
                compare(this.getCarId(), that.getCarId()) &&
                compare(this.getType(), that.getType()) &&
                compare(this.getState(), that.getState()) &&
                compare(this.getRegNumber(), that.getRegNumber()) &&
                compare(this.getModel(), that.getModel())
                );
    }

    public int hashCode() {
        int result;
        result = hash(SEED, this.getCarId());
        result = hash(result, this.getType());
        result = hash(result, this.getState());
        result = hash(result, this.getRegNumber());
        result = hash(result, this.getModel());
        return result;
    }



}
