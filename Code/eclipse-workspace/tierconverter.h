#pragma once

//Here we need about 3 functions
//1 to take in the data and reorganize it
//2 to then figure out which ones will fit into tiers
//3 return tiers and maybe attach them to the thing?
#include <iostream>
#include <fstream>
#include <string>
#include <string.h>
#include <vector>
#include <stdlib.h>
#include <ctype.h>

using namespace std;

// food for thought, would it be wiser to create a class for this?

//So here we need to create a struct to create an array to work with stuff
struct unitPiece {
	string name;
	int value;
	string tier = "null";
};

//Vector of type struct unitPiece which should be established. This will be populated for later based off of CSV files
vector<unitPiece> arrayMess;

//for now now we will use 4 tiers, but this value will be addressed by the data base or us to use later
int tiers = 4;
int size = 20;
string localCurrency = "$";					//Here we are using the default currency for testing

//Assuming we have the list, we need to sort out the list
void sortList(vector<unitPiece> messVector) {

	int idx = 0;

	//while loop created to go through the entire vector
	while (isSorted(messVector) == false)				//If the list isn't sorted, we loop through again
	{
		//We will need a for loop to go down the list and start switching. But this will be based on the index at hand. Going to look up sorted loops

		if (idx == messVector.size())
		{
			sorted = true;
		}
		else 
		{

		}
	}

}

//This will take in an argument vector and check if it is sorted or not
//else it will return false
bool isSorted(vector<unitPiece> arguVector)
{
	//We are assuming that the enture list is false for default values
	bool vectorBool = false;

	for (int i = 1; i < arguVector.size(); i++)
	{
		//Will go through the list and check if the individual values are in order
		//If at any point it isn't in order when comparing pairs of values, program will automatically return false
		if (arguVector[i - 1].value <= arguVector[i].value)
		{
			vectorBool = true;
		}
		else
			return false;
	}
	return vectorBool;					//We assume that if it hasn't already returned false, then we are gunna return true
}

//Here we are definitely gunna need a function to populate the vector to be used