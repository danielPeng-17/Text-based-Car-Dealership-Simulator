import java.text.SimpleDateFormat;
import java.util.*;

public class Transaction{
    private int id;
    private Car carObj;
    private String name;
    private String transactionType;
    private double price;
    private Calendar calendar;

    /**
     * contructor for the Transaction class
     */
    public Transaction(int id, Calendar date, Car carObj, String name, String transactionType, Double price){
        this.id = id;
        this.calendar = date;
        this.carObj = carObj;
        this.name = name;
        this.transactionType = transactionType;
        this.price = price;
    }

    /**
     * returns the id number
     * 
     * @return an int value of the id
     */
    public int getID(){
        return id;
    }

    /**
     * returns the car object
     * 
     * @return the car object 
     */
    public Car getCar(){
        return carObj;
    }

    /**
     * returns the name of the sales person
     * 
     * @return a string value of the sales person
     */
    public String getName(){
        return name;
    }

    /**
     * returns the transaction type (buy/ ret)
     * 
     * @return a string value of the transaction type
     */
    public String getTransactionType(){
        return transactionType;
    }

    /**
     * returns the price of the car
     * 
     * @return a double of the price of the car
     */
    public double getPrice(){
        return price;
    }

    /**
     * returns the calendar
     * 
     * @return a Calendar object
     */
    public Calendar getDate(){
        return calendar;
    }

    /**
     * displays information about the transaction
     * 
     * @return a string value of the transaction
     */
    public String display(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy MMMMMMMMMMMMM dd");
        return ("ID: "+id+" "+sdf.format(calendar.getTime())+" "+transactionType+" SalesPerson: "+name+"  "+carObj.display());
    }

}