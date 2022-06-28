package de.exxcellent.challenge.service;

import de.exxcellent.challenge.pojo.DayWeather;

import java.util.Comparator;
import java.util.List;
import java.util.function.ToIntFunction;

public class DifferenceCalculator {

    /**
     * finds the entity with the smallest difference between two values inside an entity list
     * @return the entity with the smallest difference between the two values
     */
    public <E> E findEntityWithSmallestDifference( List<E> entityList, ToIntFunction<E> value1, ToIntFunction<E> value2 ){
        return entityList.stream()
            .min(
                Comparator.comparingInt(
                    (E e) -> Math.abs( value1.applyAsInt(e) - value2.applyAsInt(e) )
                )
            ).orElse(null);
    }

}
