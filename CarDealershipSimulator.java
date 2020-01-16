import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.lang.NumberFormatException;
import java.util.*;

public class CarDealershipSimulator{

	/**
	 * the main methods runs the whole program
	 */
	public static void main(String[] args){
	  	CarDealership carDealer = new CarDealership();
		ArrayList<Car> carList = new ArrayList<Car>();
		Car boughtCars = null;
		boolean alreadyReturned = false;
		int currentBuyID = -1;

		// calls helper method to read from files
		getCarsFromFile(carList);
		// calls helper method to run the simulator and get user inputs
		cmdLine(carDealer, carList, boughtCars, alreadyReturned, currentBuyID);
	}

	/**
	 * gets inputs and preforms the task, also does sanity checking to make sure inputs are valid
	 * 
	 * @param carDealer a CarDealership object 
	 * @param carList an arrayList of cars 
	 * @param boughtCars an arrayList of all cars that have been bought
	 * @param alreadyReturned a boolean to check if a car has already been returned
	 * @param currentBuyID the id of the current car 
	 * @see CarDealership  
	 */
	public static void cmdLine(CarDealership carDealer, ArrayList<Car> carList, Car boughtCars, boolean alreadyReturned, int currentBuyID){
		String cmd;
		Scanner input = new Scanner(System.in);
		// String[] temp is used for multiple input on the same line
		String [] temp;

		System.out.print("Enter a cmd: ");
		cmd  = input.nextLine().toUpperCase(); 
		
		// keeps running until input is "Q"
		while(!cmd.equals("Q")){
			try{
				temp = new String[4];
				// if the input has spaces then split it
				// used to determine the cmd type
				if (cmd.contains(" ")){
					temp = cmd.split("\\s+");

					if (temp.length > 0 && (temp[0].equals("FPR") || temp[0].equals("BUY") || temp[0].equals("SALES") || temp[0].equals("SALE"))){
						cmd = temp[0];
					}else {
						cmd = "";
					}
				}

				if (cmd.equals("L")){
					carDealer.displayInventory();
				}else if (cmd.equals("BUY")){
					try{
						if (carDealer.numCars() > -1 && temp[1] != null){
							System.out.println(carDealer.buyCar(Integer.parseInt(temp[1])));
							currentBuyID = carDealer.getCurrentBuyID();
						}else if (temp[1] == null){
							System.err.println("No VIN entered");
						}
					}catch(Exception e){
						System.err.println("Invalid Token");
					}
				}else if (cmd.equals("RET")){
					try{
						if (currentBuyID != -1){
							System.out.println("The returned car transaction: "+carDealer.getTransactionFromAccountingSystem(currentBuyID));
							carDealer.returnCar(currentBuyID);
							carDealer.resetFilteredList();
							currentBuyID = -1;
						}else{
							System.out.println("Error, cannot return car");
						}
					}catch(Exception e){
						System.err.println("Invalid Token");
					}
				}else if (cmd.equals("ADD")){
					carDealer.addCars(carList);
				}else if (cmd.equals("SPR")){
					carDealer.sortByPrice();
				}else if (cmd.equals("SSR")){
					carDealer.sortBySafetyRating();
				}else if (cmd.equals("SMR")){
					carDealer.sortByMaxRange();
				}else if (cmd.equals("FPR")){
						try{
							carDealer.filterByPrice(Double.parseDouble(temp[1]), Double.parseDouble(temp[2]));		
						}catch(Exception e){
							System.err.println("Invalid Token");
						}
				}else if (cmd.equals("FEL")){
					// checks if this filter is already in use
					if (!carDealer.getAlreadyFilteredByElectric()){
						carDealer.filterByElectric();
					}
				}else if (cmd.equals("FAW")){
					// checks if this filter is already in use
					if (!carDealer.getAlreadyFilteredByAWD()){
						carDealer.filterByAWD();
					}
				}else if (cmd.equals("FCL")){
					carDealer.filtersClear();
				}else if (cmd.equals("SALES") || cmd.equals("SALE")){
					if (temp.length == 1 || temp[1] == null){
						// prints all transactions in 2019
						try{
							LinkedList<Transaction> tempTransaction = carDealer.getTransactionByTime(-1, true);

							for(int x = 0; x < tempTransaction.size(); x++){
								System.out.println(tempTransaction.get(x).display());
							}
						}catch(Exception e){
							System.err.println("Invalid Token. Error: " + e );
						}
					}else if (temp[1] != null && temp[1].equals("TEAM")){
						System.out.println("The sales team contains: " + carDealer.getSalesTeam().display());
					}else if (temp[1] != null && temp[1].equals("TOPSP")){
						System.out.println("The top saler(s) is: " + carDealer.getTopSalers());
						if (carDealer.getTopSalers().contains(" ") && !carDealer.getTopSalers().equals("None")){
							System.out.println("They sold " + carDealer.getSalesRecord()+ " car(s) each");
						}else if (!carDealer.getTopSalers().equals("None")) {
							System.out.println(carDealer.getTopSalers()+ " sold "+carDealer.getSalesRecord() + " car(s)");
						}
					}else if (temp[1] != null && temp[1].equals("STATS")){
						//total sales 
						System.out.printf("\nTotal Sales ($): %.2f \n", carDealer.getTotalSalesProfit());
						// sales per month (total sales / 12)
						System.out.printf("Total Sales ($) per month: %.2f \n", (carDealer.getTotalSalesProfit() / 12));
						//total number of cars sold 
						System.out.println("Total Amount of Cars Sold: "+ carDealer.getTotalSalesCount());
						//the highest sales month 
						System.out.println("Highest Sales in: "+ carDealer.getHighestSalesMonth());
						//total cars returned
						System.out.println("Total Amount of Cars Returned: "+carDealer.getTotalReturnedCars());
					}else if (temp[1] != null && !temp[1].equals("")){
						// print all transaction that was bought in m month
						try{
							int month = Integer.parseInt(temp[1]);
							if ( month > -1 && month < 13){
								LinkedList<Transaction> tempTransaction = carDealer.getTransactionByTime(month - 1, false);

								for (int x = 0; x < tempTransaction.size(); x++){
									System.out.println(tempTransaction.get(x).display());
								}
							}else if (month < 1 || month > 12){
								System.out.println("Invalid month");
							}
						}catch(Exception e){
							System.err.println("Invalid Token. Error: " + e);
						}
					}
				}else{
					System.out.println("Not a valid input");
				}
				// gets next input
				System.out.print("\nEnter a cmd: ");
				cmd = input.nextLine().toUpperCase();
			}catch(Exception e){
				System.err.println("Error: " + e);
			}
		}
		input.close();
	}

	/**
	 * reads car values from a file
	 * 
	 * @param carList an arrayList of cars
	 */
	public static void getCarsFromFile(ArrayList<Car> carList){
		try{
			// opens file called "cars.txt"
			Scanner carFile = new Scanner(new File("cars.txt"));
			// keep running until the file is done
			while(carFile.hasNext()){
				Car carFromFile;
				// reads the whole line in the file and split all whitespaces
				String[] temp = carFile.nextLine().split("\\s+");
				// set boolean values based on file input
				boolean isElectric = (temp[3].equals("ELECTRIC_MOTOR"))? true : false;
				boolean isAWD = (temp[6].equals("AWD"))? true : false;
				int modelType;

				// setting model for the car
				if (temp[2].equals("SEDAN")){
					modelType = Car.SEADAN;
				}else if (temp[2].equals("SUV")){
					modelType = Car.SPORTS;
				}else if (temp[2].equals("SPORTS")){
					modelType = Car.SPORTS;
				}else {
					modelType = Car.MINIVAN;
				}

				// making appropriate car types 
				if (isElectric){
					carFromFile = new ElectricCar(temp[0], temp[1], 0, modelType, Integer.parseInt(temp[5]), Double.parseDouble(temp[4]), isAWD, Double.parseDouble(temp[7]), Integer.parseInt(temp[8]));
				}else{
					carFromFile = new Car(temp[0], temp[1], 1, modelType, Integer.parseInt(temp[5]), Double.parseDouble(temp[4]), isAWD, Double.parseDouble(temp[7]));
				}
				// adds Car objects into carList so that it can be added to the CarDealership object
				carList.add(carFromFile);
			}
			carFile.close();
		}catch(IOException e){
			System.err.println("Error: IO Exception or File Not Found");
		}catch(NumberFormatException e){
			System.err.println("Error: Number Format Exception");
		}catch(Exception e){
			throw e;
		}
	}
}