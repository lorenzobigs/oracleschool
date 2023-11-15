/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 */

package labs.pm.app.data;

import java.math.BigDecimal;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * @author lorenzo.grandi
 **/
public class ProductManager {
    private Product product;
    private Review review;
    private Locale locale;
    private ResourceBundle resourceBundle;
    private DateTimeFormatter dateTimeFormatter;
    private NumberFormat numberFormat;


    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore){
        product = new Food(id,name,price,rating,bestBefore);
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating){
        product =  new Drink(id,name,price,rating);
        return product;
    }

    public Product reviewProduct(Product product, Rating rating, String comments){
        review = new Review(rating,comments);
        this.product = product.applyRating(rating);
        return this.product;
    }

    public void printProductReport(){
        StringBuilder txt = new StringBuilder();
        txt.append(MessageFormat.format(resourceBundle.getString("product"),
                product.getName(),
                numberFormat.format(product.getPrice()),
                product.getRating().getStars(),
                dateTimeFormatter.format(product.getBestBefore())));
        txt.append("\n");
        if(review != null){
            txt.append(MessageFormat.format(resourceBundle.getString("review"),
                    review.getRating().getStars(),
                    review.getComment()
                    ));
        } else{
            txt.append(resourceBundle.getString("no.reviews"));
        }
        txt.append("\n");
        System.out.println(txt);
    }

    public static Product createStaticProduct(int id, String name, BigDecimal price, Rating rating){
        return new Drink(id,name,price,rating);
    }

    public ProductManager(Locale locale) {
        this.locale = locale;
        resourceBundle = ResourceBundle.getBundle("resources",locale);
        dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(locale);
        numberFormat = NumberFormat.getCurrencyInstance(locale);
    }

    public ProductManager() {
        this.locale = new Locale("en","EN");
        resourceBundle = ResourceBundle.getBundle("resources", this.locale);
        dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(this.locale);
        numberFormat = NumberFormat.getCurrencyInstance(this.locale);
    }
}
