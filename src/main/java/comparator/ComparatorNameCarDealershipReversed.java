package comparator;

import java.util.Comparator;
import aiss.model.CarDealership;

public  class ComparatorNameCarDealershipReversed implements Comparator<CarDealership> {

    public int compare(CarDealership cd1, CarDealership cd2) {
        return cd2.getName().compareTo(cd1.getName());

    }

}