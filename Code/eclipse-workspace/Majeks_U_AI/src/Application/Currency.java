package Application;
import java.util.Scanner;



public class Currency {
	Scanner inputScanner = new Scanner(System.in); // input
	
	String currName;
	String ISO;
	double valueFromDollar;
	double MUX_value;
	
	
	public Currency nextPtr = null;
	public Currency head = null;
	
	public Currency(Currency head, String currName, String ISO, double valueFromDollar) {
		this.head = head;
		this.currName = currName;
		this.ISO = ISO;
		this.valueFromDollar = valueFromDollar;
		this.MUX_value = 0.0;
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
	
	public void AddCurrency(Currency head, String currName, String ISO, double valueFromDollar) {
		/*System.out.print("Please Input the Currency Name: ");
		String tempCurrName = inputScanner.nextLine();
		System.out.print("Please Input the ISO: ");
		String tempISO = inputScanner.next();
		System.out.print("Please Input the ValueFromDollar: ");
		Double tempValueFromDollar = inputScanner.nextDouble();
		*/
		
		if(head == null) {
			head = new Currency(head, currName, ISO, valueFromDollar);
		}
		else {
			boolean ToF = true;
			Currency currentPtr = head;
			
			while(currentPtr.nextPtr != null)
			{
				if(currentPtr.CheckList(currentPtr, currName, ISO, valueFromDollar)) {
					ToF = false;
					break;
				}
				currentPtr = currentPtr.nextPtr;
			}
			
			if(ToF == true) {
				currentPtr.nextPtr = new Currency(currentPtr.head, currName, ISO, valueFromDollar);
				System.out.println(currName + " has been added to the currencyList");
			}
			else {
				System.out.println("Currency Already Exists Within Chain! ");
			}
		}
	}
	
	public void PrintCurrentList(Currency head) {
		
		Currency currentPtr = head;
		
		System.out.println("Currency-Name[ISO]");
		if(currentPtr != null) {
			while(currentPtr != null)
			{
				if(currentPtr.ISO == ISO)
				{
					System.out.println(currentPtr.currName + "[" + currentPtr.ISO + "]");
				}
				System.out.println();
				currentPtr = currentPtr.nextPtr;
			}
		}
		else
		{
			System.out.println("Chain is Empty!");
		}
	}
	
	public void PrintCurrencyValue(Currency head) {
		
		PrintCurrentList(head);
		System.out.println("Please Select a ISO from the list: ");
		String tempISO = inputScanner.next();
		boolean ToF = false;
		Currency currentPtr = head;
		
		if(currentPtr != null) {
			while(currentPtr != null)
			{
				if(currentPtr.ISO == ISO)
				{
					System.out.println("The current value of " + currentPtr.currName + "[" + currentPtr.ISO + "] is " + currentPtr.valueFromDollar +"\n");
					ToF = true;
				}
				currentPtr = currentPtr.nextPtr;
			}
			if(ToF == false) {
			System.out.println("ISO does not exist in Chain!\n");
			}
		}
		else
		{
			System.out.println("Cannot Find ISO is Chain! Chain is Empty!");
		}
	}
	
	public void RunIterations(Currency head) {
		
		System.out.print("Please Enter the number of Iterations you would like to run: ");
		int tempNumOfIterations = inputScanner.nextInt();
		
		for(int i = 0; i < tempNumOfIterations; i++) {
			Scaling_Values(head);
			System.out.println("Iteration [" + (i+1)+ "] complete!");
			
		}
		System.out.println("All " + tempNumOfIterations+ " succesfully completed!");		
	}
	
	public void PrintCurrencyConversion(Currency head) {
		PrintCurrentList(head);
		System.out.println("Please Select 2 ISOs from the Currency List: ");
		System.out.print("From [ISO]: ");
		String ISO_1 = inputScanner.next();
		
		System.out.print("To [ISO]: ");
		String ISO_2 = inputScanner.next();
		
		
	}
	
	
	private double CompareValues(Currency head, String ISO_1, String ISO_2) {
		double conversion = 0.0;
		
		for(int i = 0; i <2; i++)
		{
		}
	
		return conversion;
	}
	
	private void Scaling_Values (Currency head) {	
	}
	
	private boolean CheckList(Currency currentPtr, String currName, String ISO, double valueFromDollar) {
		boolean ToF = false;
		if(currentPtr.currName == currName || currentPtr.ISO == ISO || currentPtr.valueFromDollar == valueFromDollar) {
			ToF = true;
		}
		return ToF;
	}
	
	

}