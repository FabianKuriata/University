#pragma once
#include <vector>
#include "Tree.h"

using namespace std;

class Interface
{
public:
	Interface();
	~Interface();
	void run();
private:
	enum
	{
		COMMAND_ENTER,
		COMMAND_VARS,
		COMMAND_PRINT,
		COMMAND_COMP,
		COMMAND_JOIN,
		COMMAND_EXIT,
		COMMAND_INCORRECT = -1
	};
	const char SPACE = ' ';
	Tree* tree;
	vector <string> split(string input, char character);
	int checkCommand(string command);
	bool menu();
	void enter(vector <string> &commandsVector);
	void vars(vector <string> &commandsVector);
	void print(vector <string> &commandsVector);
	void comp(vector <string> &commandsVector);
	void join(vector <string> &commandsVector);
	void printMenu();
};