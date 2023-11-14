/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 */

package labs.pm.app.data;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * @author lorenzo.grandi
 **/
public class ProductManager {
    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore){
        return new Food(id,name,price,rating,bestBefore);
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating){
        return new Drink(id,name,price,rating);
    }

    public static Product createStaticProduct(int id, String name, BigDecimal price, Rating rating){
        return new Drink(id,name,price,rating);
    }
}
