import java.util.Comparator;
import java.lang.Comparable;

public class Car extends Vehicle implements Comparable<Car>{
    private int model;
    private int maxRange;
    private double safetyRating;
    private boolean awd;
    private double price;
    
    public static final int SEADAN = 0;
    public static final int SUV = 1;
    public static final int SPORTS = 2;
    public static final int MINIVAN = 3;

    /**
     * Car constructor that takes in nothing
     */
    public Car(){
        super();
        model = 0;
        maxRange = 0;
        safetyRating = 0.0;
        awd = false;
        price = 0.0;
    }

    /**
     * Car constructor that takes in mfr name, color, type of engine, model, range, safety, awd, and price 
     * 
     * @param mfr a string mfr name 
     * @param color a string colour 
     * @param power an integer that is either 1 or 0.
     * @param modelType an integer of the model 
     * @param range an integer of the range of the car 
     * @param sRating a double number of the safety rating 
     * @param AWD true if the car has AWD else false 
     * @param price a double of the price of the car
     */
    public Car(String mfr, String color, int power, int modelType, int range, double sRating, boolean AWD, double price){
        super(mfr, color, power);
        model = modelType;
        maxRange = range;
        safetyRating = sRating;
        this.price = price;
        awd = AWD;
    }

    /**
     * return the model 
     * 
     * @return an integer value of the model type
     */
    public int getModel(){
        return model;
    }

    /**
     * return true if car has all wheel drive 
     * 
     * @return true if the car is all wheel drive else false
     */
    public boolean getAWD(){
        return awd;
    }

    /**
     * returns the price of the car 
     * 
     * @return a double value of the price
     */
    public double getPrice(){
        return price;
    }

    /**
     * return the safety rating 
     * 
     * @return a double value of the safety rating
     */
    public double getSafetyRating(){
        return safetyRating;
    }

    /**
     * return the max range of the car 
     * 
     * @return an integer value of the max range
     */
    public int getMaxRange(){
        return maxRange;
    }

    /**
     * figures out the model type then returns a string value of 
     * super.display(), model, price, safety rating, and max range
     * 
     * @return a string value of super.display(), model, price, safety rating, and max range
     * @see Vehicle
     */
    public String display(){
        String modelName = "";
        switch (model){
            case 0: modelName = "SEDAN";
                    break;
            case 1: modelName = "SUV";
                    break;
            case 2: modelName = "SPORTS";
                    break;
            case 3: modelName = "MINIVAN";
                    break; 
        }
        
        return super.display()+" "+modelName +" "+price+"$ SF: "+safetyRating+" RNG: "+maxRange;
    }

    /**
     * compares the two Cars by their model, AWD, and if the super properties
     * 
     * @param other an object that can be casted into a Car object
     * @return true if the two Cars are the same else false
     * @see Vehicle
     */
    @Override
    public boolean equals(Object other){
        Car otherCar = (Car) other;
        return (this.model == otherCar.getModel() && this.awd == otherCar.getAWD() && super.equals(otherCar));
       
    }

    /**
     * overrides the Comparable<T> interface and compares the
     * prices of two Cars using Double.compare() and returns an integer value
     * 
     * @param other a Car object
     * @return an integer value 
     * @see Double
     */
    @Override
    public int compareTo(Car other){
        return Double.compare(this.price, other.getPrice());
    }

    public static class SortBySaftey implements Comparator<Car>{
        /**
         * overrides the Comparator<T> interface and compares the safety rating of 
         * two Cars using Double.compare() and returns an integer value
         * 
         * @param car1 the first Car object
         * @param car2 the second Car object 
         * @return an integer value 
         * @see Double 
         */
        @Override
        public int compare(Car car1, Car car2){
            return Double.compare(car1.getSafetyRating(), car2.getSafetyRating());
        }
    }

    public static class SortByRange implements Comparator<Car>{
        /**
         * overrides the Comparator<T> interface and compares the max range of 
         * two Cars using Double.compare() and returns an integer value
         * 
         * @param car1 the first Car object
         * @param car2 the second Car object 
         * @return an integer value 
         * @see Double 
         */
        @Override
        public int compare(Car car1, Car car2){
            return Integer.compare(car1.getMaxRange(), car2.getMaxRange());
        }
    }
}