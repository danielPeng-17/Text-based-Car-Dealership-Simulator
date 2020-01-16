import java.util.*;

public class SalesTeam{
    private LinkedList<String> salesTeam;
    private Map<String, Integer> namesToSales;
    private Integer salesRecord;

    /**
     * Constructor for SalesTeam
     */
    public SalesTeam(){
        salesTeam = new LinkedList<String>();
        namesToSales = new HashMap<String, Integer>();
        salesRecord = 0;

        // loads sales team names
        String [] names = {"Tom", "Jim", "Bob", "Jerry", "Jack", "Dave"};
        for (int x = 0; x < names.length; x++){
            salesTeam.add(names[x]);
            namesToSales.put(names[x], 0);
        }
    }

    /**
     * returns the size of the sales team
     * 
     * @return an integer value of the size
     */
    public int getSize(){
        return salesTeam.size();
    }

    /**
     * sets the top sallers 
     * isSelling -> true if BUY
     * isSelling -> false is RET
     * 
     * @param name a string value of the sales person name 
     * @param isSelling a boolean; true = buy; false = ret
     */
    public void setTopSeller(String name, boolean isSelling){
        int t = 0;
        if (namesToSales.containsKey(name)){
            if (namesToSales.get(name) != null && isSelling){
                t = namesToSales.get(name) + 1;
            }else if (namesToSales.get(name) != null && !isSelling){
                t = namesToSales.get(name) - 1;
            }else {
                t = 1;
            }
        }
        namesToSales.put(name, t);
    }

    /**
     * returns a the top seller(s)
     * 
     * @return a list of the top seller(s)
     */
    public LinkedList<String> getTopSaler(){
        LinkedList<Integer> maxSold = new LinkedList<Integer>();
        LinkedList<String> topSaler = new LinkedList<String>();
        Set<String> names = namesToSales.keySet();
        Integer maxValue = 0;

        for (String name : names){
            maxSold.add(namesToSales.get(name));
        }
        maxValue = Collections.max(maxSold);

        if (maxValue == 0){
            topSaler.add("None");
            return topSaler;
        }
        setSalesRecord(maxValue);

        for (String name : names){
            if (namesToSales.get(name) == maxValue){
                topSaler.add(name);
            }
        }
        return topSaler;
    }

    /**
     * sets the highest sales 
     * 
     * @param x an Integer of the highest amount sold
     */
    public void setSalesRecord(Integer x){
        salesRecord = x;
    }

    /**
     * returns the highest sales record
     * 
     * @return an Integer object of the sales record
     */
    public Integer getSalesRecord(){
        return salesRecord;
    }

    /**
     * returns a sales person name
     * 
     * @param index an int value of the index 
     * @return a string of the sales person name
     */
    public String getPerson(int index){
        ListIterator<String> iterator = salesTeam.listIterator(index);
        return iterator.next();
    }

    /**
     * displays all information of the sales team
     * 
     * @return a string value of the sales team
     */
    public String display(){
        ListIterator<String> iterator = salesTeam.listIterator();
        String temp = "";

        for (int x = 0; x < salesTeam.size() && iterator.hasNext(); x++){
            if (x == salesTeam.size() - 1){
                temp += iterator.next();
            }else{
                temp += iterator.next() + ", ";
            }
        }
        return (temp);
    }
}