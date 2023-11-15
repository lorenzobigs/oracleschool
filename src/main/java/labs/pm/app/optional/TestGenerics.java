/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 */

package labs.pm.app.optional;

import labs.pm.app.data.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * @author lorenzo.grandi
 **/
public class TestGenerics {
    public static void main(String[] args){
        Some<String> someString = new Some<>();
        Some<Product> someProduct = new Some<>();
        ProductManager pm = new ProductManager();
        Product p1 = pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.FOUR_STAR);
        Product p2 = pm.createProduct(102, "TeaCup", BigDecimal.valueOf(3.99), Rating.FOUR_STAR);
        Product p3 = pm.createProduct(103,"Cake",BigDecimal.valueOf(3.99),Rating.FIVE_STAR, LocalDate.now().plusDays(2));

        someString.setT("t");
        someProduct.setT(ProductManager.createStaticProduct(102, "TeaCup", BigDecimal.valueOf(3.99), Rating.FOUR_STAR));
        System.out.println(someProduct.getT() instanceof Product);
        System.out.println(someProduct.getT() instanceof Drink);
        System.out.println(someProduct instanceof Some<Product>);
        System.out.println(someProduct.getT().getId());

        List<Product> list = new ArrayList<>();
        list.add(p1);
        list.add(p2);
        list.add(p3);
        someProduct.verifiyList(list);
    }
}
