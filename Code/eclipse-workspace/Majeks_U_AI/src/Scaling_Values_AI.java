import java.util.*;



public class Scaling_Values_AI {

	
	// Function returns the weighted value of the scaler based on number of occurrances
	// Many positive occurances increase magnitude towards 1 
	// Many negative occurances increase magnitude towards -1   
	public static double Scaler(int count) {
		double scaler = 0.0;
		
		if (count>= 1 && count <= 10)
		{
			scaler = .5;
		}
		else if (count>= 11 && count <= 25)
		{
			scaler = 1.0;
		}
		else if (count>= 26 && count <= 50)
		{
			scaler = 2.5;
		}
		else if (count>= 51 && count <= 100)
		{
			scaler = 5.0;
		}
		else
		{
			scaler = 10.0;
		}
		
		return scaler;
	}


		
		
		// Main function
		public static void main(String[] args) {
			// TODO Auto-generated method stub
			
			Scanner cin = new Scanner(System.in);
			// Declare variables used to generate a random input value for this implementation
			Random rand = new Random();
			int rand_item = 0;
			Boolean rand_bool = false;
			double rand_double = 0.00;
			double initial_U = 1.02;
			int count = 0;
			double myScaler= 0;
			Boolean[] checkNeg = new Boolean[2];
			double graph = 0.00;
			double[] arr_U = new double[6];
			
			
			arr_U[0] = initial_U;
			
			for (int i = 1; i < 600; i ++) {
				
				System.out.print(i + ".) ");

				rand_item = rand.nextInt(5);
				System.out.print("Rand_Item = " + rand_item + ",\t\t");
				rand_double = (double)rand_item/1000;
				System.out.print("Rand_Double = " + rand_double + ",\t\t");
				rand_bool = rand.nextBoolean();
				System.out.print("Rand_Bool = " + rand_bool + "\t");
				
				
				if(rand_bool == true)
				{
					rand_double = rand_double* -1;
					checkNeg[0] = checkNeg[1];
					checkNeg[1] = false;
				}
				else
				{
					checkNeg[0] = checkNeg[1];
					checkNeg[1] = true;
				}
				
				
				if(checkNeg[0] == checkNeg[1])
				{
					count++;
				}
				else
				{
					count = 1;
				}
				
				myScaler = Scaler(count);
				System.out.print("Count = "+ count + ",\t");
				
				graph += myScaler * rand_double;
				System.out.print("Graph = "+ graph + ",\t\t");

				
				arr_U[i] = arr_U[i-1] + rand_double;
				System.out.println("ArrayU[" + i + "] = " + arr_U[i]);
				
				

			}
			

		}
		// TODO Auto-generated method stub
}


