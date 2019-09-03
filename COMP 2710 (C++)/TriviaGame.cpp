/*
 * Author: Alex Lewin
 * Course: Comp 2710
 * 
 */
#include <iostream>
#include <string>
#include <stdlib.h>
#define UNIT_TESTING
#define assert(condition) {if(!(condition)) {std::cerr << "Warning. The following condition was not met: " << #condition << std::endl;return;}}
struct TriviaNode {
  std::string question;
  std::string answer;
  int points;
  TriviaNode *next = NULL;
};


void insert(TriviaNode *head, std::string quest, std::string ans, int poin) {
  
  TriviaNode *curr = head;
   
  while (curr->next != NULL){
    curr = curr->next;
  } 
  TriviaNode *temp = new TriviaNode;
  temp->question = quest;
  temp->answer = ans;
  temp->points = poin;
  curr->next = temp;
}

int ask(TriviaNode *node) {
  std::cout << node->question << "\nAnswer: ";
  std::string in;
  std::cin >> in;
  return (in == node->answer ? node->points : 0); 
}

void get(TriviaNode *head, int num_ques_asked) {
  TriviaNode *curr = head;
  int len = 0;
  for (;curr->next != NULL;len++) curr=curr->next;
  assert(num_ques_asked > 0); 
  assert(len >= num_ques_asked);
  curr = head;
  int score = 0;
  for (int i = 0; i < num_ques_asked; i++)  {
    curr = curr->next;
    int points = ask(curr);
    score += points;
    if (points) std::cout << "\nYour answer is correct. You recieve " << points << " points." << std::endl;
    else std::cout << "\nYour answer is wrong. The correct answer is: " << curr->answer << std::endl;
    std::cout << "Your total points: " << score << std::endl << std::endl;
  }
}
void play(TriviaNode *head) {
  int len = 0;
  TriviaNode *curr = head;
  for (;curr->next != NULL;len++)  curr=curr->next;
  
  
  get(head, len);
}

bool isnum(std::string s){ 
  return s.find_first_not_of( "0123456789" ) == std::string::npos;
}

void add(TriviaNode *head) {
  bool conta = false;
  do {
    std::cout << "Enter a question: ";
    std::string quest;
    std::cin >> quest;
    std::cout << "Enter an answer: ";
    std::string ans;
    std::cin >> ans;
    std::cout << "Enter award points: ";
    int val;
    std::string inp;
    std::cin >> inp;
    while (!isnum(inp)){
      std::cout << "Argument invalid.\nEnter award points: ";
      std::cin>>inp;
    }
    val = std::stoi(inp);
    insert(head, quest, ans, val);
    std::cout << "Continue? (Yes/No) ";
    std::string cont;
    std::cin >> cont;
    while (cont != "Yes" && cont != "No") {
      std::cout << "Argument invalid." << std::endl;
      std::cout << "Continue? (Yes/No) ";
      std::cin >> cont;
    } 
    conta = (cont == "Yes") ? true : false;
  } while (conta);
  std::cout<<std::endl;
}



int main_game(){
  std::cout << "*** Welcome to Alex's trivia quiz game ***" << std::endl;
  TriviaNode *head = new TriviaNode;
  add(head);
  play(head); 
  std::cout << "*** Thank you for playing the trivia quiz game. Goodbye! ***";
  return 0;
}

int main_debug(){
  std::cout << "*** This is a debug version ***" << std::endl;
  TriviaNode *head = new TriviaNode;
  insert(head, "How long was the shortest war on record? (Hint: how many minutes)", "38", 100);
  insert(head,  "What was Bank of America's original name? (Hint: Bank of Italy or Bank of Germany)", "Bank of Italy", 50);
  insert(head, "What is the best-selling video game of all time? (Hint: Minecraft or Tetris)", "Tetris", 20);
  
  std::cout << "\nUnit Test Case 1: Ask no questions. The program should give a warning message."  << std::endl;
  get(head, 0);
  std::cout << "\nUnit Test Case 2.1: Ask 1 question in the linked list. The tester enters an incorrect answer.\n";
  std::cout << (ask(head->next) != 0 ? "Case 2.1 Failed" : "Case 2.1 Passed") << std::endl; 
  std::cout << "\nUnit Test Case 2.2: Ask 1 question in the linked list. The tester enters a correct answer.\n";
  std::cout << (ask(head->next) != 0 ?  "Case 2.1 Passed" : "Case 2.1 Failed") << std::endl;
  std::cout << "\nUnit Test Case 3: Ask the last question in the linked list.\n";
  ask(head->next->next->next);
  std::cout << "\nUnit Test Case 4: Ask 5 questions in the linked list.\n";
  get(head,5);
  std::cout << "\n\n*** End of the Debug Version ***";
  return 0;
}

int main() {
#ifdef UNIT_TESTING 
  main_debug();
#else 
  main_game(); 
#endif
}
