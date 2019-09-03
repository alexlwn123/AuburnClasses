/*
 * Alexander Lewin
 * asl0028
 * 
 * File Name: hw4_asl0028.cpp
 * input files: input1.txt , input2.txt
 *
 * Compile Instructions:
 *
 *  $ g++ hw4_asl0028.cpp
 *  $ a.exe
 *
 *
 */

#include <iostream>
#include <cstdlib>
#include <string>
#include <fstream>
using namespace std;

int readfile(int *inputArray, fstream &intstream) {
  string line;
  int count;
  for (count = 0; getline(intstream, line); count++){
    inputArray[count] = stoi(line);
  } 
  return count;
}

int sort(int *inputArray, int inputArray_size) {
  for (int i = 0; i < inputArray_size; i++){
    for (int j = i+1; j < inputArray_size; j++) {
      if (inputArray[i] > inputArray[j]){
        int tmp = inputArray[i];
        inputArray[i] = inputArray[j];
        inputArray[j] = tmp;
      } 
    }
  }
  return inputArray_size;
}

void writefile(int *outputarray, int outputArray_size, ofstream &sout) {
  cout << "\nThe sorted list of " << outputArray_size << " numbers is: ";
  for (int i = 0; i < outputArray_size; i++) {
    sout << outputarray[i] << " ";
    cout << outputarray[i] << " ";
  }
  sout.close();
}


int main() {
  cout << "*** Welcome to Alex Lewin's Sorting Program ***" << endl;
  cout << "Enter the first input file name: ";
  string name;
  cin >> name;
  string filetext;
  fstream filestream;
  filestream.open(name);
  if (filestream.fail()) {
    cout << "Input file opening failed." << endl;
    exit(1);
  }
  int inputArray[99];
  int firstSize = readfile(inputArray, filestream);
  
  cout << "The list of " << firstSize << " numbers in file " << name << " is:" << endl;
  for (int i = 0; i < firstSize; i++) {
    cout << inputArray[i] << endl;
  } 
  filestream.close();
  
  cout << "\nEnter the second input file name: "; 
  string name1;
  cin >> name1;
  string filetext1;
  filestream.open(name1);
  if (filestream.fail()) {
    cout << "Input file opening failed." << endl;
    exit(1);
  }
  int totalSize = firstSize + readfile(inputArray+firstSize, filestream);
  filestream.close();
  cout << "The list of " << totalSize - firstSize << " numbers in file " << name1 << " is:" << endl;
  for (int i = firstSize; i < totalSize; i++) {
    cout << inputArray[i] << endl;
  }
  sort(inputArray, totalSize);
  ofstream outstream;
  outstream.open("output.txt");
  writefile(inputArray, totalSize, outstream);
  
  return 0;

}
