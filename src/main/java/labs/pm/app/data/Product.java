/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 */

package labs.pm.app.data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

import static java.math.RoundingMode.HALF_UP;
/**
 * {@code Product} class represent properties and behaviours of
 * product objects
 * <br>
 * Each product can have a discount calculated based on
 * {@link DISCOUNT_RATE discount rate}
 * @version 4.0
 * @author lorenzo.grandi

 **/
public sealed class Product implements Rateable<Product> permits Food,Drink{

    private final int id;
    private final String name;
    private final BigDecimal price;
    private final Rating rating;
    /**
     * Constant that defines a
     * {@link java.math.BigDecimal BigDecimal} value of the discount rate
     * <br>
     * Discount rate is 10%
     */
    public static final BigDecimal DISCOUNT_RATE = BigDecimal.valueOf(0.1);

    public int getId() {
        return id;
    }



    public String getName() {
        return name;
    }



    public BigDecimal getPrice() {
        return price;
    }


    /**
     *
     * @return a {@link BigDecimal} value of the discount
     */
    public BigDecimal getDiscount(){
        return price.multiply(DISCOUNT_RATE).setScale(2, HALF_UP);
    }

    public Rating getRating() {
        return rating;
    }

    public Product applyRating(Rating r){
        return new Product(getId(),getName(),getPrice(),r);
    }
    Product(int id, String name, BigDecimal price, Rating rating) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
    }

//    public Product(int id, String name, BigDecimal price) {
//        //reuse of the existing constructor
//       this(id,name,price,Rating.NOT_RATED);
//    }
//
//    public Product(){
//       this(0,"no name",BigDecimal.ZERO);
//    }

    public LocalDate getBestBefore() {
        return LocalDate.now();
    }

    @Override
    public String  toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", rating=" + rating.getStars() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
//        if (o == null || getClass() != o.getClass()) return false;
        if(o instanceof Product product) {
//            Product product = (Product) o;
            return id == product.id ;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }


}
