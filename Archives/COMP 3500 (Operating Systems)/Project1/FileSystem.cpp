#include <iostream>
#include <fstream>
#include <istream>
#include <string.h>
using namespace std;

bool WriteWord(int nAddress, const char * nWord);
char* readWord(int nAddress);
bool EraseAllSectors();
bool EraseSector(int nSectorNr);
void openFile();
bool fexists();
fstream memory;
const char * fname = "memory.bin";
const int SECTIONS = 20;
const int BLOCK_SIZE = 65635;
const int MaxAddress = 1310718;

int main() {
	openFile();
	WriteWord(16, "aa");
	cout<<readWord(16)<<endl;
	EraseSector(0);
	cout<<readWord(16)<<endl;
	WriteWord(16, "xx");
	EraseAllSectors();
	cout<<readWord(16)<<endl;
	return 0;
}

bool fexists()
{
  ifstream file(fname);
  return file.good();
}

bool WriteWord(int nAddress, const char * nWord) {
	if (strlen(nWord) != 2) {
		cout << "Input is not 2 bytes and thus not a word" << endl;
		return false;
	}
	//Checks to see if a file needs to be made
  if (!fexists()) {
    cout << "ERROR\nFile not found" << endl;
    return false;
  }
  fstream memory(fname);
	//Stores the current word at nAddress
	char* fileWord = readWord(nAddress);
	
	//Runs a Bitwise AND on both variables
	if (*fileWord & *nWord);
	//Places your postion in the file at nAddress
	memory.seekg(nAddress);
	//Writes newly "And'ed" nWord to file postion
	memory << nWord;
  memory.close();
	return true;
}

char* readWord(int nAddress) {
  if (!fexists()) {
    cout << "ERROR\nFile not found" << endl;
    return false;
  }
  fstream memory(fname);
	char* temp = 0;
	char* poo = new char[2];

	//Checks to see if the address is in bounds
	if (nAddress < 0 || nAddress > MaxAddress) {
		cout << "Inputed address is out of bounds" << endl;
		return poo;
	}
	// Checks to see if the address is on the WORD boundary
	else if (nAddress % 2 != 0) {
		cout << "Inputed address is odd and thus not the start of a word" << endl;
		return poo;
	}
	else {
		//Temporary place for the addressed word
		memory.seekg(nAddress);
		temp = new char[2];
		memory.read(temp, 2);
	}
	return temp;
}


bool EraseAllSectors() {

  ofstream memory(fname, ios::out | ios::binary);
  char ones[BLOCK_SIZE * SECTIONS];
	for (int i = 0; i <= ones.size; i++)
	{
		ones[i] = 0xFF;
	}

  file.write(ones, size);
  file.close();

//	memory.open("memory.bin", ios::out | ios::binary);
//	for (int i = 0; i < 50; i++) {
//		memory << char(255);
//	}
	return true;
}

bool EraseSector(int nSectorNr) {

	if (! fexists())
	{
		EraseAllSectors();
		return false;
	}

	ofstream file(fname, ios::out | ios::binary);
	file.seekp(nSectorNr * BLOCK_SIZE);

	char ones[BLOCK_SIZE];
	for (int i = 0; i < BLOCK_SIZE; i++) {
		ones[i] = 0xFF;
	}

	file.write(ones, BLOCK_SIZE);
	file.close();
	return true;
}


bool fexists()
{
	ifstream file(fname);
	return file.good();
}
