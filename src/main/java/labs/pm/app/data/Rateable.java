/*
 * Copyright (c) 2023. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 */

package labs.pm.app.data;

import javax.swing.plaf.PanelUI;

/**
 * @author lorenzo.grandi
 **/
@FunctionalInterface //cioè un interfaccia che implementa solo un abstract method
public interface Rateable<T> {
    public static final Rating DEFAULT_RATING = Rating.NOT_RATED;
    T applyRating(Rating rating);
    public default Rating getRating(){
        return DEFAULT_RATING;
    }
    public static Rating convert(int stars){
        return (stars>=0&&stars<=5)
                ?Rating.values() [stars] : DEFAULT_RATING;
    }
    public default T applyRating(int stars){
        return applyRating(convert(stars));
    }

}
