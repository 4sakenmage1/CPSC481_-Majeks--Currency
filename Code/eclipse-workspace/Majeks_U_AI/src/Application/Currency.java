package Application;
import java.util.Random;
import java.util.Scanner;



/*----------------------Currency Class -------------------------------
 * The Currency class acts as a vehicle for storing local data of the currencies.
 * It will be encapsulating a good portion of the functionality for the AI rather
 * than creating the functions in main.java.
 * 
 * 
 * **********ALL INFORMATION IS STORED INTERNALLY*****
 * 
 * Class was created as a linked list so that the ADMIN could add currency freely without having
 * to redefine the code and just to the chain.
 * 
 * The Class its self consists of:
 * 
 * currName = The currency name (Information Stored locally so Actual Currency names are not available)
 * ISO 		= The ISO of the currency (Unique key)
 * value	= The exchange rate of the Currency Object (Relative to Dollar[USD])
 * MUX_vale = Relational Value that AI uses to assess whether the user should invest (Only For U-Currency[MUX])
 * count 	= Keep track of "Hits"  (Used for Scaler function to decide which Scaler to return)
 * CheckNeg = Keep Track of increase or decrease of currency relative to previous value 
 * nextPtr	= Keeps track of next Object Stored in the Chain
 * head 	= Chain list head stored internally to avoid information being public.
 * ---------------------Current Class---------------------------------*/


public class Currency {
	Scanner inputScanner = new Scanner(System.in); // input
	
	//Format is Name/ISO/Scaled Value
	String currName;
	String ISO;
	double value;
	double MUX_value;
	int count;
	boolean checkNeg[] = {false,false};	// array used to keep track of when a generated value changes sign (+ or -)

	//Temporary pointer with attributes of currency
	public Currency nextPtr = null;
	public Currency head = null;
	
	// Constructor created to initialize values of the class.
	public Currency(Currency head, String currName, String ISO, double value) {
		this.head = head;
		this.currName = currName;
		this.ISO = ISO;
		this.value = value;
		this.MUX_value = 0.0;
		this.count = 0;
		
	}
	/*public int CurrencyCount(Currency head)
	{
		int count = 0;
		Currency currentPtr = head;
		
		if(currentPtr != null) {
			while(currentPtr.nextPtr != null)
			{
				count++;
				currentPtr = currentPtr.nextPtr;
			}
		}
		else
		{
			count = 0;
		}
		return count; 
	}	*/
	
	//Adds a currency to the Chain
	
	
	/*------------------Add Currency--------------------
	 * Add currency is an all purpose method of adding objects to the chain into local memory.
	 * The method is made to take in user input value/read in value from a CSV, and load them in
	 * currently the manual input has been disabled to avoid user intervention.
	 * -----------------Add Currency -------------------*/
	
	public void AddCurrency(Currency head, String currName, String ISO, double value) { //Working
		/*System.out.print("Please Input the Currency Name: ");
		String tempCurrName = inputScanner.nextLine();
		System.out.print("Please Input the ISO: ");
		String tempISO = inputScanner.next();
		System.out.print("Please Input the value: ");
		double tempvalue = inputScanner.nextDouble();
		*/
		
		if(head == null) {
			head = new Currency(head, currName, ISO, value);
		}
		else {
			boolean ToF = true;
			Currency currentPtr = head;
			
			while(currentPtr.nextPtr != null)
			{
				if(currentPtr.CheckList(currentPtr, currName, ISO, value)) {
					ToF = false;
					break;
				}
				currentPtr = currentPtr.nextPtr;
			}
			
			if(ToF == true) {
				currentPtr.nextPtr = new Currency(currentPtr.head, currName, ISO, value);
				System.out.println(currName + " has been added to the currencyList");
			}
			else {
				System.out.println("Currency Already Exists Within Chain! ");
			}
		}
	}
	//Prints the currency list for the viewer
	
	/*--------------Print Current List------------------------
	 * Print all objects in the chain as currName[ISO]
	 * -------------Print Current List ----------------------*/
	public void PrintCurrentList(Currency head) { //Working
		
		Currency currentPtr = head;
		
		System.out.println("Currency-Name[ISO]");
		if(currentPtr != null) {
			while(currentPtr != null)
			{
				System.out.println(currentPtr.currName + "[" + currentPtr.ISO + "]");

				currentPtr = currentPtr.nextPtr;
			}
		}
		else
		{
			System.out.println("Chain is Empty!");
		}
	}
	
	public void PrintCurrencyValue(Currency head) { //Working
		PrintCurrentList(head);
		System.out.println("Please Select a ISO from the list: ");
		String ISO = inputScanner.next().toUpperCase();
		Boolean ToF = false;
		Currency currentPtr = head;
		
		if(currentPtr != null) {
			while(currentPtr != null)
			{
				if( ISO.equals(currentPtr.ISO))
				{
					//System.out.print(ToF);
					System.out.println("The current value of " + currentPtr.currName + "[" + currentPtr.ISO + "] is " + currentPtr.value +"\n");
					ToF = true;
					break;
				}
				currentPtr = currentPtr.nextPtr;
			}
			//System.out.print(ToF);

			if(ToF == false) {
			System.out.println("ISO does not exist in Chain!\n");
			}
		}
		else
		{
			System.out.println("Cannot Find ISO is Chain! Chain is Empty!");
		}
	}
	
	//creates new values for currency to replace databases we do not have access too
	
	/*-------------------Run Iterations---------------------------
	 * 
	 * User defines how many iterations AI will run. Method calls Scaling_Values
	 * method inside which is the heart of the AI. This portion would be considered out dated
	 * if automatic API integration is integrated and the AI will iterate based off API pull
	 * request
	 * ------------------Run Iterations--------------------------*/
	public void RunIterations(Currency head) {
		
		System.out.print("Please Enter the number of Iterations you would like to run: ");
		int tempNumOfIterations = inputScanner.nextInt();
		
		for(int i = 0; i < tempNumOfIterations; i++) {
			System.out.println("Iteration [" + (i+1)+ "] Started!!");
			Scaling_Values(head);
			System.out.println("Iteration [" + (i+1)+ "] complete!");
			
		}
		System.out.println("All " + tempNumOfIterations+ " succesfully completed!");		
	}
	
	//Takes two currencies and shows the conversion. First -> second
	public void PrintCurrencyConversion(Currency head) { //Working
		PrintCurrentList(head);
		System.out.println("Please Select 2 ISOs from the Currency List: ");
		System.out.print("From [ISO]: ");
		String ISO_1 = inputScanner.next().toUpperCase();
		
		System.out.print("To [ISO]: ");
		String ISO_2 = inputScanner.next().toUpperCase();
		
		CompareValues(head, ISO_1, ISO_2);
	}
	
	//This is our AI. It will study the changes in currency and give advice if
	//the user should continue investing or not.
	//I.E... AI Print display
	public void Invest(Currency head) { // working
		Currency currentPtr = head;
		
		System.out.println();
		if(currentPtr != null) {
			while(currentPtr != null)
			{
				
				if(currentPtr.ISO.equals("MUX"))
				{
					System.out.println(currentPtr.currName + "[" + currentPtr.ISO + "] -- MUX Value: " + currentPtr.MUX_value);

					if(currentPtr.MUX_value <= -.75)
					{
						System.out.println("You're f*cked if you do, Bro.");
					}
					else if(currentPtr.MUX_value <= -.5 && currentPtr.MUX_value > -.75)
					{
						System.out.println("Don't Invest");
					}
					else if(currentPtr.MUX_value <= -.25 && currentPtr.MUX_value > -.5)
					{
						System.out.println("Meh, Not the best. But it could be worse. Probably not though.");
					}
					else if(currentPtr.MUX_value <= 0  && currentPtr.MUX_value > -.25)
					{
						System.out.println("Could be worse. Sit on it. I would say its okay!");
					}
					else if(currentPtr.MUX_value <= .25  && currentPtr.MUX_value > 0)
					{
						System.out.println("Wouldn't hurt. Go for it!");
					}
					else if(currentPtr.MUX_value <= .5  && currentPtr.MUX_value > .25)
					{
						System.out.println("Looking Good! Invest!");
					}
					else if(currentPtr.MUX_value <= .75  && currentPtr.MUX_value > .5)
					{
						System.out.println("Why haven't you invested? This shit is golden");
					}
					else if(currentPtr.MUX_value <= 1  && currentPtr.MUX_value > .75)
					{
						System.out.println("BOI, PUT YO DAMN DOLLAR IN THE MUX!!! IT'S GOLDEN HOUR!!!");

					}

				}
				
				//System.out.println(currentPtr.currName + "[" + currentPtr.ISO + "]");

				currentPtr = currentPtr.nextPtr;
			}
		}
		else
		{
			System.out.println("Chain is Empty!");
		}
		
	}
	
	//public void PrintCurrencyTable(head);
	//Takes in two currencies and compares their values
	private void CompareValues(Currency head, String ISO_1, String ISO_2) {	//Working
		double conversion = 0.0;
		double tempISO[] = {0,0};
		boolean ToF[] = {false, false};
		Currency currentPtr = head;
	
		if(currentPtr != null) {
			while(currentPtr != null)
			{
				if(ToF[0]== true && ToF[1] ==true ) {
					break;
				}
				if(currentPtr.ISO.equals(ISO_1))
				{
					tempISO[0] = currentPtr.value;
					ToF[0] = true;
				}
				if(currentPtr.ISO.equals(ISO_2))
				{
					tempISO[1] = currentPtr.value;
					ToF[1] = true;
				}	
				currentPtr = currentPtr.nextPtr;
			}
		}
		else
		{
			System.out.println("Cannot Find ISO is Chain! Chain is Empty!");
		}
		
		if(ToF[0] == false)
			System.out.println( ISO_1+ " does not exist in Chain!\n");

		if(ToF[1] == false)
			System.out.println( ISO_2 + " does not exist in Chain!\n");
		
		if(ToF[0]== true && ToF[1] ==true ) {
			conversion = (1 / tempISO[0])/(1/tempISO[1]);
			System.out.println("is [" + ISO_2 + "]"+ conversion + " per [" + ISO_1 + "]" );
		}
	}
	
	//Randomly adjusts the values for iteration tests
	private void Scaling_Values (Currency head) {
		Random rand = new Random();				// random variable generation
		int rand_item = 0;						// random value that is generated 
		boolean rand_bool = false;				// random bool that determines if variable will be positive or negative
		double rand_double = 0.00;				// double generated from rand_item 

		Currency currentPtr = head;
		
		if(currentPtr != null) {
			while(currentPtr != null)
			{
				if(currentPtr.ISO.equals("USD"))
				{
					//do nothing
					//SSSSystem.out.println("Ignore " + currentPtr.ISO);

				}
				else if (currentPtr.ISO.equals("MUX"))
				{
					Currency MUX_Ptr = head;
					double average = 0;
					
					for(int i = 0; i < 5; i++)
					{
						average = average + MUX_Ptr.value;
						MUX_Ptr = MUX_Ptr.nextPtr;
					}
					average = (average/5);
					System.out.println(average);
					
					if(average <= currentPtr.value) 
					{
						//System.out.print("Rand_Bool made this Positive \t");
						double difference = average - currentPtr.value;
						System.out.println("Difference is "  + difference);
						
						currentPtr.MUX_value = Scaler(currentPtr.count, currentPtr.MUX_value, difference);
						System.out.println("MUX Value is "  + currentPtr.MUX_value);

						currentPtr.checkNeg[0] = currentPtr.checkNeg[1];
						currentPtr.checkNeg[1] = false;

					}
					else
					{						
						//System.out.print("Rand_Bool made this negative \t");
						double difference = average - currentPtr.value;
						System.out.println("Difference is "  + difference);

						currentPtr.MUX_value = Scaler(currentPtr.count, currentPtr.MUX_value, difference);
						System.out.println("MUX Value is "  + currentPtr.MUX_value);

						currentPtr.checkNeg[0] = currentPtr.checkNeg[1];
						currentPtr.checkNeg[1] = true;
					}
					if(checkNeg[0] == checkNeg[1]){
						currentPtr.count++;
					}
					else {
						currentPtr.count = 1;
					}
					
					currentPtr.value = average;
				}
				else {
					rand_item = rand.nextInt(5);
					rand_double = (double)rand_item/1000;
					rand_bool = rand.nextBoolean();
					if(rand_bool == true) {
						//System.out.print("Rand_Bool made this negative \t");
						rand_double = rand_double* -1;
						if(currentPtr.value >= .05)
							currentPtr.value =  currentPtr.value+ rand_double;
						
					}
					else
					{
						//System.out.print("Rand_Bool made this Positive \t");
						if(currentPtr.value >= .05)
							currentPtr.value =  currentPtr.value+ rand_double;
					}
				}
				currentPtr = currentPtr.nextPtr;
			}
		}
		else
		{
			System.out.println("Chain is Empty!");
		}
	}
		
	//Check the list if it is valid and returns a boolean value
	private boolean CheckList(Currency currentPtr, String currName, String ISO, double value) {
		boolean ToF = false;
		if(currentPtr.currName == currName || currentPtr.ISO == ISO || currentPtr.value == value) {
			ToF = true;
		}
		return ToF;
	}
	
	//Checking the scaler value
	private double Scaler(int count, double graph, double magnitude) {
		
		double scaler = 0.0;
		if (count  < 2 ) {
			scaler = .50;
		}
		else if(count < 3) {
			scaler = 1.0;
		}
		else if(count < 4) {
			scaler = 2.5;
		}
		else if(count < 5) {
			scaler = 5.0;
		}
		else if (count < 10) {
			scaler = 10.0;
		}
		else {
			scaler = 15.0;
		}
		graph += (-1 *scaler) * magnitude;

		if (graph <= -1.0) {
			//System.out.print("graph value is lower than the expected -1");
			return -1.0;
		}
		else if (graph >= 1.0) {
			//System.out.print("graph value is higher than the expected -1");
			return 1.0;
		}
		
		return graph;
	}
	
}