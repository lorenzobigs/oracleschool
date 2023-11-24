package labs.pm.app;

import labs.pm.app.data.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Locale;


public class Shop {
    public static void main(String[] args) {

        ProductManager pm = new ProductManager("en-GB");
        Product p1 = pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.FOUR_STAR);
        Product p2 = pm.createProduct(102, "TeaCup", BigDecimal.valueOf(3.99), Rating.FOUR_STAR);
        Product p4 = p2.applyRating(Rating.TWO_STAR);
        pm.reviewProduct(p2,Rating.FIVE_STAR,"TOP");
        pm.printProductReport(p1);
        p1=pm.reviewProduct(101,Rating.FIVE_STAR,"Top");
        p1=pm.reviewProduct(p1,Rating.FOUR_STAR,"Nice");
        p1=pm.reviewProduct(p1,Rating.TWO_STAR,"Meh");
        p1=pm.reviewProduct(101,Rating.FIVE_STAR,"Top");
        p1=pm.reviewProduct(p1,Rating.ONE_STAR,"Bad");
        p1=pm.reviewProduct(p1,Rating.FIVE_STAR,"Top");
        pm.printProductReport(p1);
        pm.findProduct(101);
        pm.changeLocale("it-IT");
        pm.printProductReport(p1);











        /*
        Product p3 = pm.createProduct(103,"Cake",BigDecimal.valueOf(3.99),Rating.FIVE_STAR, LocalDate.now().plusDays(2));

        Product p6 = pm.createProduct(104, "Chocolate", BigDecimal.valueOf(3.99), Rating.FIVE_STAR);
        Product p7 = pm.createProduct(104, "Chocolate", BigDecimal.valueOf(3.99), Rating.FIVE_STAR, LocalDate.now().plusDays(3));
//        System.out.println(p1.getId() + " " + p1.getName() + " " + p1.getPrice() + " " + p1.getDiscount() + " " + p1.getRating().getStars());
//        System.out.println(p2.getId() + " " + p2.getName() + " " + p2.getPrice() + " " + p2.getDiscount() + " " + p2.getRating().getStars());
//        System.out.println(p3.getId() + " " + p3.getName() + " " + p3.getPrice() + " " + p3.getDiscount() + " " + p3.getRating().getStars());
//        System.out.println(p4.getId() + " " + p4.getName() + " " + p4.getPrice() + " " + p4.getDiscount() + " " + p4.getRating().getStars());
        Product p8 = ProductManager.createStaticProduct(102, "TeaCup", BigDecimal.valueOf(3.99), Rating.FOUR_STAR);
        //Overriden toString method
        System.out.println(p1);
        System.out.println(p2);
        System.out.println(p3);
        System.out.println(p4);
        System.out.println(p6.equals(p7));
        System.out.println(p8);
*/

        }

    }
