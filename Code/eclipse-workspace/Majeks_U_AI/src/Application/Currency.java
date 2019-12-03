package Application;
import java.util.Scanner;



public class Currency {
	
	
	String currName;
	String ISO;
	double valueFromDollar;
	
	public Currency nextPtr = null;
	public Currency head = null;
	
	public Currency(String currName, String ISO, double valueFromDollar) {
		this.currName = currName;
		this.ISO = ISO;
		this.valueFromDollar = valueFromDollar;
		
	}
	
	public int CurrencyCount(Currency head)
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
	}
	
	
	public void AddCurrency(Currency head) {
		Scanner inputScanner = new Scanner(System.in); // input
		
		System.out.print("Please Input the Currency Name: ");
		String tempCurrName = inputScanner.nextLine();
		System.out.print("Please Input the ISO: ");
		String tempISO = inputScanner.next();
		System.out.print("Please Input the ValueFromDollar: ");
		Double tempValueFromDollar = inputScanner.nextDouble();
		
		if(head == null) {
			head = new Currency(tempCurrName, tempISO, tempValueFromDollar);
		}
		else {
			boolean ToF = true;
			Currency currentPtr = head;
			
			while(currentPtr.nextPtr != null)
			{
				if(currentPtr.CheckList(currentPtr, tempCurrName, tempISO, tempValueFromDollar)) {
					ToF = false;
					break;
				}
				currentPtr = currentPtr.nextPtr;
			}
			
			if(ToF == true) {
				currentPtr.nextPtr = new Currency(tempCurrName, tempISO, tempValueFromDollar);
				System.out.println(tempCurrName + " has been added to the currencyList");
			}
			else {
				System.out.println("Currency Already Exists Within Software! ");
			}
		}
	}
	public void PrintCurrencyValue(Currency head, String ISO) {
		
	}
	boolean CheckList(Currency currentPtr, String currName, String ISO, double valueFromDollar) {
		boolean ToF = false;
		if(currentPtr.currName == currName || currentPtr.ISO == ISO || currentPtr.valueFromDollar == valueFromDollar) {
			ToF = true;
		}
		return ToF;
	}
	
	

}