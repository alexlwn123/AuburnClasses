/*
 * By Alex Lewin
 * asl0028
 * hw3_asl0028.cpp
 *
 * Compile and run code in command line:
 *
 * >> g++ hw3_asl0028.cpp
 * >> a.exe
 */

#include <iostream>
#include <stdlib.h>
#include <assert.h>
#include <ctime>

using namespace std;

bool at_least_two_alive(bool A_alive, bool B_alive, bool C_alive){
/* Input: A_alive indicates whether Aaron is alive 
* B_alive indicates whether Bob is alive 
* C_alive indicates whether Charlie is alive 
* Return: true if at least two are alive *
* otherwise return false 
*/
  return A_alive + B_alive + C_alive >= 2;
}


void Aaron_shoots1(bool& B_alive, bool& C_alive)
/* Strategy 1: Use call by reference
* Input: B_alive indicates whether Bob alive or dead
* C_alive indicates whether Charlie is alive or dead
* Return: Change B_alive into false if Bob is killed.
* Change C_alive into false if Charlie is killed.
*/{
  if (rand() % 3 == 0)
    if (C_alive) C_alive = false;
    else B_alive = false;
}    

void Bob_shoots(bool& A_alive, bool& C_alive)
/* Call by reference
* Input: A_alive indicates if Aaron is alive or dead
* C_alive indicates whether Charlie is alive or dead
* Return: Change A_alive into false if Aaron is killed.
* Change C_alive into false if Charlie is killed.
*/{
  if(rand() % 2 == 0) 
    if (C_alive) C_alive = false;
    else A_alive = false;

}


void Charlie_shoots(bool& A_alive, bool& B_alive)
/* Call by reference
* Input: A_alive indicates if Aaron is alive or dead
* B_alive indicates whether Bob is alive or dead
* Return: Change A_alive into false if Aaron is killed.
* Change B_alive into false if Bob is killed.
*/
{
  if (B_alive) B_alive = false;
  else A_alive = false;
}
void Aaron_shoots2(bool& B_alive, bool& C_alive)
/* Strategy 2: Use call by reference
* Input: B_alive indicates whether Bob alive or dead
* C_alive indicates whether Charlie is alive or dead
* Return: Change B_alive into false if Bob is killed.
* Change C_alive into false if Charlie is killed.
*/
{
  if (B_alive && C_alive)
    return;
  if (rand() % 3 == 0){
    C_alive = false;
    B_alive = false;
    
  }
}

void test_at_least_two_alive() {
  cout << "Unit Testing 1: Function - at_least_two_alive()\n";
  cout << "Case 1: Aaron alive, Bob alive, Charlie alive\n";
  assert(at_least_two_alive(true, true, true));
  cout << "Case passed ...\n";
  cout << "Case 2: Aaron dead, Bob alive, Charlie alive\n";
  assert(at_least_two_alive(false, true, true));
  cout << "Case passed ...\n";
  cout << "Case 3: Aaron alive, Bob dead, Charlie alive\n";
  assert(at_least_two_alive(true, false, true));
  cout << "Case passed ...\n";
  cout << "Case 4: Aaron alive, Bob alive, Charlie dead\n";
  assert(at_least_two_alive(true, true, false));
  cout << "Case passed ...\n";
  cout << "Case 5: Aaron dead, Bob dead, Charlie alive\n";
  assert(!at_least_two_alive(false, false, true));
  cout << "Case passed ...\n";
  cout << "Case 6: Aaron alive, Bob dead, Charlie dead\n";
  assert(!at_least_two_alive(true, false, false));
  cout << "Case passed ...\n";
}


int main() {
  cout << "***Welcome to the Duel Simulator***" << endl;
  test_at_least_two_alive();
  cout << "Press Enter to continue...";
  cin.get();
  cout << "Ready to test strategy 1 (run 10000 times):" << endl;
  cout << "Press Enter to continue...";
  cin.get();

  srand(time(0));
  
  int x = 0, y = 0, z = 0;
  for(int i = 0; i < 10000; i++) {
    bool a = true, b = true, c = true;
    while(at_least_two_alive(a, b, c)) {
      if (a) Aaron_shoots1(b, c);
      if (b) Bob_shoots(a, c);
      if (c) Charlie_shoots(a, b);
    }  
    if(a) x++;
    else if(b) y++;
    else z++;  
  }
  printf("Aaron won %d/10000 duels or %.2f%\n", x, (float) x / 100);
  printf("Bob won %d/10000 duels or %.2f%\n", y, (float) y / 100);
  printf("Charlie won %d/10000 duels or %.2f%\n\n", z, (float) z / 100);
  cout << "Ready to test strategy 2 (run 10000 times):";
  cin.get();
  
  int d = 0, e = 0, f = 0;
  for(int i = 0; i < 10000; i++) {
    bool a = true, b = true, c = true;
    while(at_least_two_alive(a, b, c)) {
      if (a) Aaron_shoots2(b, c);
      if (b) Bob_shoots(a, c);
      if (c) Charlie_shoots(a, b);
    }  
    if(a) d++;
    else if(b) e++;
    else f++;  
  }
  
  printf("Aaron won %d/10000 duels or %.2f%\n", d, (float) d / 100);
  printf("Bob won %d/10000 duels or %.2f%\n", e, (float) e / 100);
  printf("Charlie won %d/10000 duels or %.2f%\n\n", f, (float) f / 100);
  
  if(x > d) cout << "Strategy 1 is better than strategy 2.";
  else cout << "Strategy 2 is better than strategy 1.";
  return 0;
}
