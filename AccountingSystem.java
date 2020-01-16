import java.util.*;

public class AccountingSystem{
    
    // accSystem MAPS AN INTEGER TRANSACTION ID TO TRANSACTION OBJECT
    // <transaction id, transaction object>
    private Map<Integer, Transaction> accSystem;

    // <month, car price>
    private Map<Integer, Integer> highestSalesMonth;
    // <month, list of transactions>
    private Map<Integer, LinkedList<Transaction>> transactionByTime;
    private ArrayList<Integer> allTransactionsID;
    private int id;
    private static int totalSalesCount;
    private static int totalReturnCount;
    private static Double totalSaleProfit;
    private int currentBuyID;

    /**
     * Constructor for Accounting System
     */
    public AccountingSystem(){
        accSystem = new HashMap<Integer, Transaction>();
        allTransactionsID = new ArrayList<Integer>();
        highestSalesMonth = new HashMap<Integer, Integer>();
        transactionByTime = new LinkedHashMap<Integer, LinkedList<Transaction>>();
        totalSalesCount = 0;
        totalReturnCount = 0;
        totalSaleProfit = 0.0;
        currentBuyID = -1;
    }

    /**
     * adds a new Transaction object into the map of transactions
     * 
     * @param calendar a Calendar object
     * @param car a Car object 
     * @param salesPerson a string of a sales person
     * @param type a string of the type of transaction
     * @param salesPrice a Double of the price of the car
     * @return a string of the transaction
     */
    public String add(Calendar calendar, Car car, String salesPerson, String type, Double salePrice){
        id = (int)(Math.random() * 300);
        Transaction newTransaction = new Transaction(id, calendar,car, salesPerson, type, salePrice);
        LinkedList<Transaction> tempTransactionByTime = new LinkedList<Transaction>();
        int m = calendar.get(Calendar.MONTH);
        int tempCount = 0;
        accSystem.put(id, newTransaction);
        allTransactionsID.add(id);

        if (type.equals("BUY")){
            currentBuyID = id;
            totalSalesCount++;
            totalSaleProfit += car.getPrice();
            if (highestSalesMonth.get(m) != null){
                tempCount = highestSalesMonth.get(m) + 1;
            }
        }else if (type.equals("RETURN") || type.equals("RET")){
            currentBuyID = -1;
            totalSaleProfit -= car.getPrice();
            totalSalesCount--;
            totalReturnCount++;
            if (highestSalesMonth.get(m) != null){
                tempCount = highestSalesMonth.get(m) - 1;
            }
        }
        highestSalesMonth.put(m, tempCount);
       
        if (transactionByTime.get(m) != null){
            tempTransactionByTime.addAll(transactionByTime.get(m));   
        }
        tempTransactionByTime.add(newTransaction);
        transactionByTime.put(m, tempTransactionByTime);

        return newTransaction.display();
    }

    /**
     * returns an int value of the id of the bought car 
     * 
     * @return an int value of the id
     */
    public int getCurrentBuyID(){
        return currentBuyID;
    }

    /**
     * returns transaction based on id 
     * 
     * @param id an int value of the car id 
     * @return a Transaction object based on the id
     */
    public Transaction getTransaction(int id){
        return accSystem.get(id);
    }

    /**
     * returns true if the vin is valid else false
     * 
     * @return true if the vin is valid else false
     */
    public boolean checkKeyValue(Integer vinKey){
        return accSystem.containsKey(vinKey);
    }

    /**
     * an integer value of the total sales count
     * 
     * @return an integer value 
     */
    public int getTotalSalesCount(){
        return totalSalesCount;
    }

    /**
     * returns the total profit as a double
     * 
     * @return a double of total profit
     */
    public double getTotalSalesProfit(){
        return totalSaleProfit;
    }

    /**
     * return the total amount of cars returned
     * 
     * @return an integer value of total amount of cars returned 
     */
    public int getTotalReturnCount(){
        return totalReturnCount;
    }

    /**
     * returns a list of all transaction id
     * 
     * @return an arrayList of all ids
     */
    public ArrayList<Integer> getAllTransactionsID(){
        return allTransactionsID;
    }

    /**
     * returns the months with the highest sales 
     * 
     * @return a string value of the months with the highest sales
     */
    public String getHighestSalesMonth(){
        ArrayList<Integer> monthSold = new ArrayList<Integer>();
        Integer maxSold = 0;
        String monthString = "";

        // adds values to array list
        for (Integer value: highestSalesMonth.values()){
            monthSold.add(value);
        } 
        if (monthSold.isEmpty()){
            return "None";
        }
        // fins the max value in the list
        maxSold = Collections.max(monthSold);

        // converts integer value of the month into a string equivalent
        for (int x = 0; x < 12; x++){
            if (highestSalesMonth.containsKey(x) && highestSalesMonth.get(x) == maxSold){
                switch (x) {
                    case 0:  monthString = "January";
                             break;
                    case 1:  monthString = "February";
                             break;
                    case 2:  monthString = "March";
                             break;
                    case 3:  monthString = "April";
                             break;
                    case 4:  monthString = "May";
                             break;
                    case 5:  monthString = "June";
                             break;
                    case 6:  monthString = "July";
                             break;
                    case 7:  monthString = "August";
                             break;
                    case 8:  monthString = "September";
                             break;
                    case 9: monthString = "October";
                             break;
                    case 10: monthString = "November";
                             break;
                    case 11: monthString = "December";
                             break;
                    default: monthString = "Invalid month";
                             break;
                }
                monthString = monthString + " ";
            }
        }
        return monthString;       
    }

    /**
     * finds all transactions by month or by year
     * 
     * @param month an integer of the month 
     * @param byYear true if you want all transaction by year else false by months 
     * @return a list of all transactions based on the params
     */
    public LinkedList<Transaction> getTransactionByTime(int month, boolean byYear){
        LinkedList<Transaction> temp = new LinkedList<Transaction>();

        if (month != -1 && !byYear){
            if (transactionByTime.containsKey(month) && transactionByTime.get(month) != null){
                return transactionByTime.get(month);
            }
        }else if (byYear){
            for (Integer x : allTransactionsID){
                temp.add(getTransaction(x));
            }
        }
        return temp;
    }
}