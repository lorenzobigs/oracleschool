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
import java.util.*;

/**
 * @author lorenzo.grandi
 **/
public class ProductManager {
    private Product product;
    private Review[] review = new Review[5];
    private Map<Product, List<Review>> products = new HashMap<>();
    private ResourceFormatter formatter;
    private static Map<String,ResourceFormatter> formatters = Map.of(
            "en-GB", new ResourceFormatter(Locale.UK),
            "it-IT", new ResourceFormatter(Locale.ITALIAN)
    );


    public Product createProduct(int id, String name, BigDecimal price, Rating rating, LocalDate bestBefore){
        product = new Food(id,name,price,rating,bestBefore);
        products.putIfAbsent(product,new ArrayList<>());
        return product;
    }

    public Product createProduct(int id, String name, BigDecimal price, Rating rating){
        product =  new Drink(id,name,price,rating);
        products.putIfAbsent(product,new ArrayList<>());
        return product;
    }

    public Product reviewProduct(int id, Rating rating, String comments){
        return reviewProduct(findProduct(id),rating,comments);
    }

    public Product reviewProduct(Product product, Rating rating, String comments){
        List<Review> reviews = products.get(product);
        products.remove(product,reviews);
        reviews.add(new Review(rating,comments));
        int sum = 0;
        for(Review review : reviews) {
            sum += review.getRating().ordinal();
        }
        product = product.applyRating(Rateable.convert(Math.round((float) sum/reviews.size())));
        products.put(product,reviews);
        return product;
    }
    public void doSomething(Product product){
        System.out.println(product.toString());
    }
    public void printProductReport(int id){
        printProductReport(findProduct(id));
    }

    public void printProductReport(Product product){
        List<Review> reviews = products.get(product);
        Collections.sort(reviews);
        StringBuilder txt = new StringBuilder();
        txt.append(formatter.formatProduct(product));
        txt.append("\n");
        for(Review r :  reviews){

            txt.append(formatter.formatReview(r));
            txt.append("\n");
            if(reviews.isEmpty())
            {
                txt.append(formatter.getText("no.reviews"));
                txt.append("\n");
            }

        }


        System.out.println(txt);
    }

    public Product  findProduct(int id){
            Product result = null;

            for(Product product : products.keySet()){
                if(product.getId() == id) {
                    result = product;
                    break;
                }
            }
            return result;
    }

    public static Product createStaticProduct(int id, String name, BigDecimal price, Rating rating){
        return new Drink(id,name,price,rating);
    }

    public ProductManager(Locale locale) {
        this(locale.toLanguageTag());
    }

    public ProductManager() {

    }
    public ProductManager(String languageTag) {
       changeLocale(languageTag);
    }

    public void changeLocale(String languageTag){
        this.formatter = formatters.getOrDefault(languageTag,formatters.get("it-IT"));
    }

    public static Set<String> getSupportedLocales(){
        return formatters.keySet();
    }

    private static class ResourceFormatter{
        private Locale locale;
        private ResourceBundle resourceBundle;
        private DateTimeFormatter dateTimeFormatter;
        private NumberFormat numberFormat;

        private String formatProduct(Product product){
            return MessageFormat.format(resourceBundle.getString("product"),
                    product.getName(),
                    numberFormat.format(product.getPrice()),
                    product.getRating().getStars(),
                    dateTimeFormatter.format(product.getBestBefore()));
        }

        private String formatReview(Review r){
           return MessageFormat.format(resourceBundle.getString("review"),
                    r.getRating().getStars(),
                    r.getComment());
        }

        private String getText(String s){
            return resourceBundle.getString("no.reviews");
        }

        private ResourceFormatter(Locale locale){
            this.locale = locale;
            resourceBundle = ResourceBundle.getBundle("resources", this.locale);
            dateTimeFormatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT).localizedBy(this.locale);
            numberFormat = NumberFormat.getCurrencyInstance(this.locale);
        }
    }
}
