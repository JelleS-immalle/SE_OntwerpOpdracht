package be.kdg.deliDish.business.domain.order;

import be.kdg.deliDish.business.domain.restaurant.Dish;
import be.kdg.foundation.contact.Position;

import java.io.Serializable;

public class OrderLine implements Serializable {
    private Dish dish;
    private int quantity;
    private String remark;

    public OrderLine(Dish dish, int quantity, String remark) {
        this.dish = dish;
        this.quantity = quantity;
        this.remark = remark;
    }

    public Position getPosition(){
        return dish.getPosition();
    }

    public int getProductionTime(){
        return dish.getProductionTime();
    }

}
