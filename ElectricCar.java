public class ElectricCar extends Car{
    private int rechargeTime;
    private String batteryType;

     /**
     * Electric car constructor that takes in nothing
     */
    public ElectricCar(){
        super();
        rechargeTime = 0;
        batteryType = "";
    }

    /**
     * Electrice car constructor that takes in mfr name, color, type of engine,
     * model, range, safety, awd, price, and recharge time 
     * 
     * @param mfr a string mfr name 
     * @param color a string colour 
     * @param power an integer that is either 1 or 0.
     * @param modelType an integer of the model 
     * @param range an integer of the range of the car 
     * @param sRating a double number of the safety rating 
     * @param AWD true if the car has AWD else false 
     * @param price a double of the price of the car
     * @param rechage an integer of the recharge time
     */
    public ElectricCar(String mfr, String color, int power, int modelName, int range, double sRating, boolean isAWD, double price, int recharge){
        super(mfr, color, 0, modelName, range, sRating, isAWD, price);
        rechargeTime = recharge;
        batteryType = "Lithium";
    }

    /**
     * set recharge time
     * 
     * @param r an int value of the recharge time
     */
    public void setRechargeTime(int r){
        rechargeTime = r;
    }

    /**
     * return recharge time
     * 
     * @return an integer value of the recharge time
     */
    public int getRechargeTime(){
        return rechargeTime;
    }

    /**
     * set battery type
     * 
     * @param bType a string value of battery type
     */
    public void setBatterType(String bType){
        batteryType = bType;
    }

    /**
     * return battery type
     * 
     * @return a string of the battery type
     */
    public String getBatteryType(){
        return batteryType;
    }

    /**
     * return super.display(), battery type, and recharge time as a string
     * 
     * @return a string of all super.display(), battery type, and recharge time
     * @see Car
     * @see Vehicle
     */
    @Override
    public String display(){
        return super.display() + " BAT: " + batteryType + " RCH: " + rechargeTime;
    }
}