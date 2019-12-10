Hello and Thank you for Using My Majeks Currency!

To operate the program, you will need an Environment with Java Installed and a Java Compiler.(We used Eclipse JEE 2019)

You will first need to run the main.java file. This will start the program



The program will begin by loading the pre-set currencies (based off real exchange rates)



You will then be prompted to select 1 of 6 options:
	1. Display current Currencies 
	2. Print Currency Value 
	3. Compare Two Currency Values 
	4. Run Iterations 
	5. Should you Invest? 
	6. Exit 


1.) Display Current Currencies:
	This will display the loaded currencies that currently exist within the Chain

2.) Print Currency Value:
	This function will prompt the user to select a currency (Based off the ISO) from the list

	When a currency is selected, the current exchange rate in USD will be displayed

3.) Compare two Currency Values:
	Similar to Print Currency, This function will prompt the user to select two 
		currencies (Based off the ISO) from the list

	When both are selected, the exchange rate of the Second will be displayed relative to the
		value of the 2nd.

4.) Run Iterations:
	**This function is when the AI is really starting to kick in**
	
	The purpose of this function is to simulate a learning environment for the AI. You will be prompted to select
		the number of iterations you would like like the AI to run(The Larger the number the longer the
		AI has to run)
		Note: 	1000 or less ~~ instant
			10,000-100,000 ~~ 3-5seconds
			1,000,000 - 10,000,000 ~~ 30 seconds - 5 minutes
			Completion rate depends on speed of the device -- time may vary
			between 10,000 iterations is ideal
	
	After all iterations are ran, the AI will prompt the user that all the iterations have completed.

5.) Should you invest?
	This function will respond with a value based off the current AI. If the AI value looks to be positive,
		a positive investment response will be displayed
	If the AI value is low, a negative response will be displayed.

6.) Exit
	Exit the program