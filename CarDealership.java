import java.util.ArrayList;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.lang.Math;
import java.util.*;

public class CarDealership{
    private ArrayList<Car> cars;
    private boolean filterByElectric = false;
    private boolean filterByAWD = false;
    private boolean alreadyFilteredByAWD = false;
    private boolean alreadyFilteredByElectric = false;
    private boolean alreadyFilteredByPrice = false;
    private double maxPrice = 0.0;
    private double minPrice = 0.0;
    private boolean filterByPrice = false;
    private ArrayList<Car> filteredList = new ArrayList<Car>();
    private SalesTeam salesTeam;
    private Calendar calendar;
    private AccountingSystem accSys;

    /**
     * initialize the cars arrayList
     */
    public CarDealership(){
        cars = new ArrayList<Car>();
        salesTeam = new SalesTeam();
        accSys = new AccountingSystem();
    }

    /**
     * adds all new cars into the cars arrayList
     * 
     * @param newCars an arrayList<Car> of all the new cars
     */
    public void addCars(ArrayList<Car> newCars){
        cars.addAll(newCars);
    }
    
    /**
     * returns the size of cars arrayList 
     * 
     * @return the size of cars arrayList
     */
    public int numCars(){
        return cars.size();
    }

    /**
     * uses an Integer vin to 'buy' a car from the list and throws 
     * an exception if the car is not found
     * 
     * @param vin an Integer object of the vin number
     * @exception Exeption throws an exception if vin not found
     * @return a string value of the Transaction 
     */
    public String buyCar(Integer vin) throws Exception{
        Car temp = null;
        boolean isValid = false;
        // check if vin is in filterlist when fiteredlist is populated
        if (!filteredList.isEmpty()){
            for (int x = 0; x < filteredList.size(); x++){
                if (filteredList.get(x).getVIN() == vin){
                    filteredList.remove(x);
                    isValid = true;
                }
            }
            if (!isValid){
                throw new Exception();
            }
        }

        for (int k = 0; k < cars.size(); k++){
            if (cars.get(k).getVIN() == vin){
                temp = cars.remove(k);                         
            }
        }
        if (temp == null){
            throw new Exception();
        }
        
        calendar = new GregorianCalendar(2019, (int)(Math.random() * 12), (int)(Math.random() * 31));
        String salesPerson = salesTeam.getPerson((int)(Math.random()*salesTeam.getSize()));
        salesTeam.setTopSeller(salesPerson, true);
        return accSys.add(calendar, temp, salesPerson, "BUY", temp.getPrice());
    }

    /**
     * returns a car object back into the list
     * 
     * @param transactionID an integer of the id 
     */
	public void returnCar(int transactionID){
        if(accSys.checkKeyValue(transactionID)){
            Transaction temp = accSys.getTransaction(transactionID);
            salesTeam.setTopSeller(temp.getName(), false);
            cars.add(temp.getCar());
            int year = calendar.get(temp.getDate().YEAR);
            int month = calendar.get(temp.getDate().MONTH);
            int day = calendar.get(temp.getDate().DAY_OF_MONTH);
            calendar = new GregorianCalendar(year, month, (int)(Math.random() * (31-day))+ day);
            accSys.add(calendar, temp.getCar(), temp.getName(), "RETURN", temp.getPrice());
        }
        resetFilteredList();
    }

    public String getTransactionFromAccountingSystem(int id){
        return accSys.getTransaction(id).display();
    }

    /**
     * returns an int value of the id of the bought car 
     * 
     * @return an int value of the id
     * @see AccountingSystem
     */
    public int getCurrentBuyID(){
        return accSys.getCurrentBuyID();
    }
    
    /**
     * return the sales team
     * 
     * @return returns a salesTeam object
     */
    public SalesTeam getSalesTeam(){
        return salesTeam;
    }

    /**
     * get a list of top salers and returns a string value
     * 
     * @return a string value of the top salers
     */
    public String getTopSalers(){
        LinkedList<String> ts = salesTeam.getTopSaler();
        String topSaler = "";

        for (int x = 0; x < ts.size(); x++){
            if (x == ts.size()-1){
                topSaler += ts.get(x);
            }else{
                topSaler += ts.get(x) + ", ";
            }
        }
        return topSaler;
    }

    /**
     * returns an Integer of the highest sales record 
     * 
     * @return an Integer of the top sales record from salesTeam
     * @see SalesTeam
     */
    public Integer getSalesRecord(){
        return salesTeam.getSalesRecord();
    }
   
    /**
     * returns a list of all transaction based on time 
     * 
     * @param month an integer value of the month 
     * @param byYear true if you want to Transaction by year else false for transaction by month 
     * @return a list of all the transactions
     */
    public LinkedList<Transaction> getTransactionByTime(int month, boolean byYear){
        return accSys.getTransactionByTime(month, byYear);
    }

    /**
     * returns the hightest sales month
     * 
     * @return a string of the hightest sales month
     */
    public String getHighestSalesMonth(){
        return accSys.getHighestSalesMonth();
    }


    /**
     * return the total sales 
     * 
     * @return a integer value of the total sales count
     */
    public int getTotalSalesCount(){
        return accSys.getTotalSalesCount();
    }

    /**
     * returns the total profit 
     * 
     * @return a double of the total sales
     */
    public double getTotalSalesProfit(){
        return accSys.getTotalSalesProfit();
    }

    /**
     * return the total amount of returned cars 
     * 
     * @return a integer value of the amount of returned cars
     */
    public int getTotalReturnedCars(){
        return accSys.getTotalReturnCount();
    }


    /**
     * set filterByElectric to true
     */
    public void filterByElectric(){
        filterByElectric = true;
    }

    /**
     * set filterByAWD to true
     */
    public void filterByAWD(){
        filterByAWD = true;
    }

    /**
     * set filterByPrice to true
     * 
     * @param num a double number of price A
     * @param num1 a double number of price B
     */
    public void filterByPrice(double num, double num1){
        filterByPrice = true;
        resetFilteredList();
        this.maxPrice = Math.max(num, num1) + 0.0;
        this.minPrice = Math.min(num, num1) + 0.0;
    }

    /**
     * clear all filters
     */
    public void filtersClear(){
        filterByElectric = false;
        filterByAWD = false;
        maxPrice = 0.0;
        minPrice = 0.0;
        resetFilteredList();
        filterByPrice = false;
    }

    /**
     * reset filtered List
     */
    public void resetFilteredList(){
        filteredList.clear();
        alreadyFilteredByAWD = false;
        alreadyFilteredByElectric = false;
        alreadyFilteredByPrice = false;
    }

    /**
     * checks if you applied an AWD filter already
     * 
     * @return alreadyFilteredByAWD is true when filter is already applied else false
     */
    public boolean getAlreadyFilteredByAWD(){
        return alreadyFilteredByAWD;
    }

    /**
     * checks if you applied an electric filter already
     * 
     * @return alreadyFilteredByElectric is true when filter is already applied else false
     */
    public boolean getAlreadyFilteredByElectric(){
        return alreadyFilteredByElectric;
    }


    /**
     * if no filters then sort cars arrayList by price,
     * else sort filtered list by price
     */
    public void sortByPrice(){ 
        if (!filteredList.isEmpty()){
            Collections.sort(filteredList);
        }
        Collections.sort(cars);
    }

    /**
     * if no filters applied then sort cars arrayList by safety rating,
     * else sort filtered list by safety rating
     */
    public void sortBySafetyRating(){
        if (!filteredList.isEmpty()){
            Collections.sort(filteredList, new Car.SortBySaftey());
        }
        Collections.sort(cars, new Car.SortBySaftey());
    }

    /**
     * if filters have been applied then sort filtered list by max range,
     * else sort the cars arrayList by max range
     */
    public void sortByMaxRange(){           
        if (!filteredList.isEmpty()){
            Collections.sort(filteredList, new Car.SortByRange());
        }
        Collections.sort(cars, new Car.SortByRange());
    }

    /**
     * uses cars arrayList and filteredList to filter cars by AWD
     */
    private void filterCarsByAWD(){
        // if no filters then filter out from cars arraylist
        if (filteredList.isEmpty()){
            for (int x = 0; x < cars.size(); x++){
                if (cars.get(x).getAWD()){
                    filteredList.add(cars.get(x));
                }
            }
        }else {
            // filter out from the filtered list
            for (int y = 0; y < 2; y++){
                for (int x = 0; x < filteredList.size(); x++){
                    if (!filteredList.get(x).getAWD()){
                        filteredList.remove(x);
                    }
                }
            }
        }
    }

     /**
     * uses cars arrayList and filteredList to filter cars by electric
     */
    private void filterCarsByElectric(){
        // if no filter applied then filter out of cars arraylist
        if (filteredList.isEmpty()){
            for (Car x : cars){
                if (x.getPower() == Car.ELECTRIC_MOTOR){
                    filteredList.add(x);
                }
            }
        }else {
            // if pre-existing filters then filter out of filteredList
            for (int p = 0; p < 2; p++){
                for (int x = 0; x < filteredList.size(); x++){
                    if (filteredList.get(x).getPower() != Car.ELECTRIC_MOTOR){
                        filteredList.remove(x);
                    }
                }
            }
        }
    }

     /**
     * uses cars arrayList and filteredList to filter cars by price
     */
    private void filterCarsByPrice(){
        // if no filters set then filter out of cars arrayList
        if (filteredList.isEmpty()){
            for (int x = 0; x < cars.size(); x++){
                if (cars.get(x).getPrice() >= minPrice && cars.get(x).getPrice() <= maxPrice){
                    filteredList.add(cars.get(x));
                }
            }
        }else {
            // if filtered are applied then filter the filteredList arrayList
            for (int k = 0; k < 3; k++){
                for (int x = 0; x < filteredList.size(); x++){
                    if (filteredList.get(x).getPrice() < minPrice || filteredList.get(x).getPrice() > maxPrice){
                        filteredList.remove(x); 
                    }
                }
            }
        }
    }

    /**
     * filters cars arrayList then prints the filtered list, 
     * if no filters are applied then print cars arrayList in order
     */
    public void displayInventory(){

        if (filterByAWD && !alreadyFilteredByAWD){
            filterCarsByAWD();
            alreadyFilteredByAWD = true;
        }

        if (filterByElectric && !alreadyFilteredByElectric){
            filterCarsByElectric();
            alreadyFilteredByElectric = true;
        }

        if (filterByPrice && !alreadyFilteredByPrice){
            filterCarsByPrice();
            alreadyFilteredByPrice = true;
        }

        // print list
        System.out.println("\n");
        if (filteredList.isEmpty() && filterByAWD == false && filterByElectric == false && filterByPrice == false){
            for (int a = 0; a < cars.size(); a++){
                System.out.println(cars.get(a).display());
            }
        }else if (!filteredList.isEmpty()){
            for (int b = 0; b < filteredList.size(); b++){
                System.out.println(filteredList.get(b).display());
            }
        }
    }
}