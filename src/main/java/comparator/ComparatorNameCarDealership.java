package comparator;

import java.util.Comparator;

import aiss.model.CarDealership;

public  class ComparatorNameCarDealership implements Comparator<CarDealership> {


	    public int compare(CarDealership cd1, CarDealership cd2) {
	        return cd1.getName().compareTo(cd2.getName());

	    }

}
	
