/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 */

package labs.pm.app.data;

import java.math.BigDecimal;
import java.time.LocalTime;

/**
 * @author lorenzo.grandi
 **/
public final class Drink extends Product{
    Drink(int id, String name, BigDecimal price, Rating rating) {
        super(id, name, price, rating);
    }

    @Override
    public Product applyRating(Rating r) {
        return new Drink(getId(),getName(),getPrice(),r);
    }

    @Override
    public BigDecimal getDiscount() {
        LocalTime now = LocalTime.now();
        return  now.isAfter(LocalTime.of(17,30)) && now.isBefore(LocalTime.of(18,30))
                ? super.getDiscount() : BigDecimal.ZERO ;
    }
}
