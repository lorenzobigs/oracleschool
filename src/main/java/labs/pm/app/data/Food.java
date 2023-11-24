/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 */

package labs.pm.app.data;

import java.math.BigDecimal;
import java.time.LocalDate;
/**
 * @author lorenzo.grandi
 **/
public final class Food extends Product{

    private LocalDate bestBefore;
    private String quality;

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public LocalDate getBestBefore() {
        return bestBefore;
    }

    @Override
    public String toString() {
        return super.toString() + ", Food{" +
                "bestBefore=" + bestBefore +
                '}';
    }

    @Override
    public BigDecimal getDiscount() {
        return bestBefore.isEqual(LocalDate.now())
                ? super.getDiscount() : BigDecimal.ZERO;
    }

    Food(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore) {
        super(id, name, price, rating);
        this.bestBefore = bestBefore;
    }

    @Override
    public Product applyRating(Rating r) {
        return new Food(getId(),getName(),getPrice(),r,bestBefore);
    }
}
