public class Vehicle{
    private String mfr;
    private String color;
    private int power;
    private int numWheels;
    private int vin; 
    public static final int ELECTRIC_MOTOR = 0;
    public static final int GAS_ENGINE = 1;

     /**
     * Vehicle constructor that takes in nothing
     */
    public Vehicle(){
        mfr = "";
        color = "";
        power = 0;
        numWheels = 4;
        vin = (int)(Math.random() * 499) + 100;
    }
    
    /**
     * Vehicle constructor that takes in the mfr name, the color, and power 
     * 
     * @param mfrName a string of the mfr name 
     * @param color a string of the color
     * @param power an integer of the type of engine 
     */
    public Vehicle(String mfrName, String color, int power){
        mfr = mfrName;
        this.color = color;
        this.power = power;
        numWheels = 4;
        vin = (int)(Math.random() * 900) + 100;
    }

    /**
     * returns vin number
     * @return an integer value of the vin number
     */
    public int getVIN(){
        return vin;
    }

    /**
     * set the manufacture name 
     * 
     * @param newMfr a string value of the manufacture 
     */
    public void setMfr(String newMfr){
        mfr = newMfr;
    }

    /**
     * returns the manufacture name 
     * 
     * @return a string value of the manufacture name
     */
    public String getMfr(){
        return mfr;
    }

    /**
     * set the colour of the car
     * 
     * @param newColor a string value of a colour 
     */
    public void setColor(String newColor){
        color = newColor;
    }    

    /**
     * returns the colour of the car 
     * 
     * @return a string value of the colour
     */
    public String getColor(){
        return color;
    }

    /**
     * set the power 
     * 
     * @param newPower a integer value of the power type of the car (electric or gas engine)
     */
    public void setPower(int newPower){
        power = newPower;
    }

    /**
     * return the engine type (gas or electric)
     * 
     * @return the int value of the engine type 
     */
    public int getPower(){
        if (power == GAS_ENGINE){
            return 1;
        } 
        return 0;
    }

    /**
     * set the number of wheels
     * 
     * @param newNumWheels integer value of the number of wheels
     */
    public void setNumWheels(int newNumWheels){
        numWheels = newNumWheels;
    }

    /**
     * return the number of wheels 
     * 
     * @return the integer value of the number of wheels
     */
    public int getNumWheels(){
        return numWheels;
    }

    /**
     * returns manufacture and colour of the car 
     * 
     * @return a string value of manufacture and colour
     */
    public String display(){
        return "VIN: " + vin + " " + mfr + " " + color;
    }

    /**
     * compares two Vehicle object and returns true if it is the objects are the same
     * 
     * @return true if the Vehicle objects are the same or false if they are not 
     */
    public boolean equals(Object other){
        Vehicle otherVehicle = (Vehicle) other;
        return (this.mfr.equals(otherVehicle.getMfr()) && this.power == otherVehicle.getPower() && this.numWheels == otherVehicle.getNumWheels());
    }
}