//Intro to Operating Systems Project (Part 1)
//
//Members: Payton Davis, Ethan Johnson, Chase Dumbacher,
//         Charles Painter, Alex Lewin,
//         Daniel Corbett, Austin Newkirk
//
//Due Date: 2/17/20

#include <iostream>
#include <fstream>
#include <istream>
#include <string.h>
#include <sys/stat.h>
using namespace std;

bool WriteWord(int nAddress, const char* nWord);
char* ReadWord(int nAddress);
bool EraseAllSectors();
bool EraseSector(int nSectorNr);
fstream memory;

const char* FNAME = "memory.bin";
const int SECTIONS = 20;
const int BLOCK_SIZE = 65536;
const int MAXADDRESS = 1310718;
const int BYTEOFONES = 255;

int main() {

	return 0;
}

//Erases only 1 sector
//Parameter nSectorNr sector you want to delete
bool EraseSector(int nSectorNr) {
	//Opening stream to then check if the file is created
	memory.open(FNAME);
	if (!memory)
	{
		EraseAllSectors();
		cout << "File could not be found so the file was created." << endl;
		return false;
	}
	int size = nSectorNr * BLOCK_SIZE;
	//Set location to the correct sector
	memory.seekg(size);
	for (int i = 0; i < BLOCK_SIZE; i++) 
	{
		memory << (char)BYTEOFONES;
	}
	memory.close();
	return true;
}

//Erases all 20 sectors in the file
bool EraseAllSectors() {
	//Opening stream to then check if the file is created
	memory.open(FNAME);
	if (!memory) 
	{
		fstream memory(FNAME, ios::out | ios::binary);
		
	}
	int size = BLOCK_SIZE * SECTIONS;
	//Fills the file with (char)255 which is equivilant to a byte of 1's.
	for (int i = 0; i < size; i++)
	{
		memory << (char)BYTEOFONES;
	}
	memory.close();
	return true;
}

//Reads a word from the specified address.
//Parameter nAddress must be in the bounds and even
char* ReadWord(int nAddress) {
 	char* empty = new char[2];
	//Opening stream to then check if the file is created
	memory.open(FNAME);
  if (!memory) 
  {
	  fstream memory(FNAME, ios::in, ios::binary);
	  cout << "File could not be found so the file was created." << endl;
	  EraseAllSectors();
  }
	char* temp = 0;

	//Checks to see if the address is in bounds
	if (nAddress < 0 || nAddress > MAXADDRESS) 
	{
		cout << "Inputed address is out of bounds" << endl;
		return empty;
	}
	// Checks to see if the address is on the WORD boundary
	else if (nAddress % 2 != 0) 
	{
		cout << "Inputed address is odd and thus not the start of a word" << endl;
		return empty;
	}
	else 
	{
		//Temporary place for the addressed word
		memory.seekg(nAddress);
		temp = new char[2];
		memory.read(temp, 2);
	}
	//Closing stream since the function is finished
	memory.close();
	return temp;
}

//Writes a word to the specified address
//Parameter nAddress must be in the boundsand even
//Parameter nWord must be eactly two bytes in length
bool WriteWord(int nAddress, const char* nWord) {
	if (strlen(nWord) != 2) 
	{
		cout << "Input is not 2 bytes and thus not a word" << endl;
		return false;
	}
	//Checks to see if a file needs to be made
	memory.open(FNAME);
	if (!memory) 
	{
		fstream memory(FNAME, ios::out | ios::binary);
		cout << "File could not be found so the file was created." << endl;
		EraseAllSectors();
	}
	// Closing file before going to readword
	memory.close();
	//Stores the current word at nAddress
	char* fileWord = ReadWord(nAddress);
	//Re-Opening file since the file is closed at the end of ReadWord
	memory.open(FNAME);

	//Runs a Bitwise AND on both variables
	if (*fileWord & *nWord);
	//Places your postion in the file at nAddress
	memory.seekg(nAddress);
	//Writes newly "And'ed" nWord to file postion
	memory << nWord;
	memory.close();
	return true;
}
