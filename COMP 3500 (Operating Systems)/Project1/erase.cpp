//
// Created by daniel on 2/8/20.
//
#include <iostream>
#include <stdio.h>
#include <fstream>
#include <sys/stat.h>

using namespace std;

const char * fname = "memory.bin";
const int SECN = 20;
const int BLOCK_SIZE = 65635;

bool fexists()
{
  ifstream file(fname);
  return file.good();
}


bool EraseAllSectors()
{
  int size;
  struct stat exists;
	if (fexists()) {
    ofstream file(fname);
    stat(fname, &exists);
  }
  size = exists.st_size;
    
  fstream file(fname, ios::out | ios::binary);
  char ones[size];
	for (int i = 0; i <= size; i++)
	{
		ones[i] = 0xFF;
	}

  file.write(ones, size);
  file.close();
  return true;
  
}

void ersec(int sec)
{

  ofstream file(fname, ios::out | ios::binary); 
  file.seekp(sec * BLOCK_SIZE);

  char ones[BLOCK_SIZE];
  for (int i = 0; i < BLOCK_SIZE; i++) {
    ones[i] = 0xFF;
  }

  file.write(ones, BLOCK_SIZE);
  file.close();
  
}

bool EraseSector(int nSectorNr)
{
	if (!fexists())
	{
		EraseAllSectors();
		return true;
	}
	ersec(nSectorNr);
	return true;
}

int main()
{
   
}
