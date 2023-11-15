/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 */

package labs.pm.app.optional;

import labs.pm.app.data.Food;
import labs.pm.app.data.Drink;
import java.util.List;

/**
 * @author lorenzo.grandi
 **/
public class Some<T>{
    private T t;


    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public void verifiyList(List<T> list){
        list.forEach( x -> {
            if (x instanceof Food f){
                System.out.println("Food " + f.getName());
            }
            if (x instanceof Drink d){
                System.out.println("Drink " + d.getName());
            }
        });
    }
}
