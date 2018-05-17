package jdbc.dto;

import java.io.Serializable;

public class RoutePoint extends Dto implements Serializable, Cloneable {

    private Integer routePointId;
    private Integer pointsId;
    private Integer orderId;
    private String type;
    private Integer visitingOrder;

    public RoutePoint() {
    }

    public Integer getRoutePointId() {
        return routePointId;
    }

    public void setRoutePointId(Integer routePointId) {
        this.routePointId = routePointId;
    }

    public Integer getPointsId() {
        return pointsId;
    }

    public void setPointsId(Integer pointsId) {
        this.pointsId = pointsId;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Integer getVisitingOrder() {
        return visitingOrder;
    }

    public void setVisitingOrder(Integer visitingOrder) {
        this.visitingOrder = visitingOrder;
    }

    public Object clone() {
        RoutePoint o = new RoutePoint();
        o.setRoutePointId(this.getRoutePointId());
        o.setPointsId(this.getPointsId());
        o.setOrderId(this.getOrderId());
        o.setType(this.getType());
        o.setVisitingOrder(this.getVisitingOrder());
        return o;
    }

    public boolean equals(Object o) {
        if (this == o)
            return true;
        if ( !(o instanceof RoutePoint))
            return false;
        RoutePoint that = (RoutePoint) o;
        return (
                compare(this.getRoutePointId(), that.getRoutePointId()) &&
                compare(this.getPointsId(), that.getPointsId()) &&
                compare(this.getOrderId(), that.getOrderId()) &&
                compare(this.getType(), that.getType()) &&
                compare(this.getVisitingOrder(), that.getVisitingOrder())
                );
    }

    public int hashCode() {
        int result;
        result = hash(SEED, this.getRoutePointId());
        result = hash(result, this.getPointsId());
        result = hash(result, this.getOrderId());
        result = hash(result, this.getType());
        result = hash(result, this.getVisitingOrder());
        return result;
    }
}
