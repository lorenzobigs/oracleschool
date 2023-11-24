/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 */

package labs.pm.app.optional;

import labs.pm.app.data.*;

import java.io.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.ToIntFunction;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.logging.*;

/**
 * @author lorenzo.grandi
 **/
public class TestGenerics {

    private static Logger logger = Logger.getLogger(TestGenerics.class.getName());

    public static void main(String[] args){
        logger.setLevel(Level.FINE);
        logger.info("i am loggingggggggggggg");
        Some<String> someString = new Some<>();
        Some<Product> someProduct = new Some<>();
        ProductManager pm = new ProductManager();
        Product p1 = pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.FOUR_STAR);
        Product p2 = pm.createProduct(102, "TeaCup", BigDecimal.valueOf(3.99), Rating.FOUR_STAR);
        Product p3 = pm.createProduct(103,"Cake",BigDecimal.valueOf(3.99),Rating.FIVE_STAR, LocalDate.now().plusDays(2));
        Product p4 = pm.createProduct(101, "Tea", BigDecimal.valueOf(1.99), Rating.FOUR_STAR);
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

        Map<Boolean,List<Product>> prodTypes = list.stream().collect(Collectors.partitioningBy(p -> p instanceof Food));
        System.out.println(prodTypes.keySet());
        System.out.println("prodTypes " + prodTypes.get(true).get(0).getName());
        System.out.println("prodTypes " + prodTypes.get(false).get(0).getName());

        Consumer<Product> doSomething = (product -> pm.doSomething(product));
        Predicate<Product> isFood = (p -> p instanceof Food);
        Predicate<Product> isDrink = (p -> p instanceof Drink);
        Predicate<Product> isEqual = (p -> Objects.equals(p,p4));
        Function<Product,String> nameMapper = p -> p.getName();
        ToIntFunction<Product> intMapper = p -> p.getName().length();
        Function<List<Product>, Stream> flat = l -> l.stream();


        list.stream().filter(isFood.negate().and(isEqual)).peek(doSomething).collect(Collectors.toList());
        List<String> newList = list.stream().map(nameMapper).collect(Collectors.toList());
        newList.forEach(s-> System.out.println(s));

        Stream<Product> ms = list.stream().filter(isFood);
        //System.out.println("count " + ms.count());
        System.out.println("length " + ms.mapToInt(intMapper).sum());
        System.out.println("parallel " +list.stream().parallel().filter(isDrink).mapToInt(intMapper).sum());
        List<Integer> integerList = list.stream().parallel().map(p -> p.getName().length()).collect(Collectors.toList());
        integerList.stream().peek( p-> System.out.println(p));
        integerList.forEach( x -> System.out.println(x));

        Spliterator s = list.spliterator();






        try {
            FileHandler fh;
            // This block configure the logger with handler and formatter
            fh = new FileHandler("./logs/logfile.log",true);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages
            logger.info("My second log");

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            InputStream in = new FileInputStream("logfile.log");
            BufferedReader bin = new BufferedReader(new InputStreamReader(in));

            while(bin.readLine() != null){
                System.out.println(bin.readLine());
            }
        } catch (FileNotFoundException e) {
            logger.severe(e.getMessage());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /*
        Scanner scanner = new Scanner(System.in);
        String txt = null;
        System.out.println("to quit type exit");
        System.out.println("type and press enter");
        while (!(txt=scanner.nextLine()).equals("exit")){
            System.out.println("Echo: " + txt);
        }
        */



    }
         private static class MyFunction implements Function<Product,String>{
             @Override
             public String apply(Product product) {
                 return product.getName();
             }
         }
         private class ProductException extends Exception{
            public ProductException(){
                super();
            }
         }

}
