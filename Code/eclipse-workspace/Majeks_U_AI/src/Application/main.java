package Application;
import java.util.Scanner;
import java.io.*;
import java.math.*;
import java.text.*;



public class main {

	static double[] dollarDataSet = { 1, .908, .775, 1.33, .997, 
			109.032, 1.476, 7.820, 1.550, 9.641, 1172.420, 1.365, 9.180, 19.440, 1.002  };
	static double[][] conversionTable = new double[dollarDataSet.length][dollarDataSet.length];
	static String[] ISOTable =  {"USD", "EUR", "GBP", "CAD", "CHF",
			"JPY","AUD","HKD","NZD","SEK","KRW","SGD","NOK","MXN","MUX",}; 
	//static FileInputStream in = null;
    static FileWriter out = null;
	
	
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		Scanner inputScanner = new Scanner(System.in); // input
		DecimalFormat dtf4 = new DecimalFormat("#.###");
		
		
		
		
		//Initialize Data set
		for (int i = 0; i < 15; i++)
		{
			//System.out.print(dollarDataSet[i] + " ");
		}
		System.out.println();
		System.out.println();
		
		for (int row = 0; row < 15; row++)
		{
			for (int col = 0; col < 15; col++)
			{
				conversionTable[row][col] = 0.00;
				//System.out.print(conversionTable[row][col] + " ");
			}
			//System.out.println();
		}
		
		System.out.println("\n\n");
		
		//Load Original Dollar Data Conversion Data set (imported through API)
		for (int i = 0; i < dollarDataSet.length; i++)
		{
			conversionTable[i][0] = dollarDataSet[i]; //column
			//System.out.print("{ " + conversionTable[i][0] + ", ");
			conversionTable[0][i] = (1 / dollarDataSet[i]); //row
			//System.out.print(conversionTable[0][i] + "} "); 
		}
		
		//Assign Remaining values based off of dollar conversions (determined from dollar Data Set)
		
		// int k = 0;
		for (int row = 1; row < 15; row++)
		{
			for (int col = 1; col < 15; col++)
			{
				conversionTable[col][row] = conversionTable[0][row] / conversionTable[0][col];
			}
		}
		
		
		//File Creation and access
		
		
		for(int n = 0; n< 100; n++)
		{
			out = new FileWriter("ConversionTable"+ n +".csv");

			int k = 0;
			int l = 0;
			int i = 0;
			int j = 0;
				
			for (int row = 0; row < 16; row++)
			{
				for (int col = 0; col < 16; col++)
				{
					if(row == 0 && col == 0)
					{
					//display currency ISO
					out.write("ISO");
					System.out.print("ISO \t");	
					}
					else if(row == 0)
					{
						//display currency ISO
						out.write(ISOTable[k]);
						System.out.print(ISOTable[k++] + "\t");
					}
					else if(col == 0)
					{
						//display currency ISO
						out.write(ISOTable[l]);
						System.out.print(ISOTable[l++] + "\t");
					}
					else
					{
						//display Conversion
						out.write(String.valueOf(conversionTable[row-1][col-1]) );
						System.out.print("{"+ dtf4.format(conversionTable[row-1][col-1]) + "}, ");
					}
			
					//out.write(String.valueOf(conversionTable[row][col]));
				
					if(col != 16 && row != 16)
					{
						out.write(",");
					}		
				}
				out.write("\n");
				System.out.println();
		}
		
		out.close();
		System.out.println("File Closed!");
		
		}

	}

}
