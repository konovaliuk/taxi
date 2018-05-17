package jdbc.dto;

import java.io.Serializable;

public class Point extends Dto implements Serializable, Cloneable {

    private Integer pointId;
    private String name;
    private Float xCor;
    private Float yCor;

    public Point() {
    }

    public Integer getPointId() {
        return pointId;
    }

    public void setPointId(Integer pointId) {
        this.pointId = pointId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getXCor() {
        return xCor;
    }

    public void setXCor(Float xCor) {
        this.xCor = xCor;
    }

    public Float getYCor() {
        return yCor;
    }

    public void setYCor(Float yCor) {
        this.yCor = yCor;
    }

    public Object clone() {
        Point o = new Point();
        o.setPointId(this.getPointId());
        o.setName(this.getName());
        o.setXCor(this.getXCor());
        o.setYCor(this.getYCor());
        return o;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ( !(o instanceof Point))
            return false;
        Point that = (Point) o;
        return (
                compare(this.getPointId(), that.getPointId()) &&
                compare(this.getName(), that.getName()) &&
                compare(this.getXCor(), that.getXCor()) &&
                compare(this.getYCor(), that.getYCor())
                );
    }

    public int hashCode() {
        int result;
        result = hash(SEED, this.getPointId());
        result = hash(result, this.getName());
        result = hash(result, this.getXCor());
        result = hash(result, this.getYCor());
        return result;
    }
}
