#include <iostream>
#include <string>
#include "Interface.h"


Interface::Interface()
{
	tree = new Tree();
}

Interface::~Interface()
{
	if (tree != NULL)
	{
		delete tree;
		tree = NULL;
	}
}

void Interface::run()
{
	printMenu();
	bool running;
	do
		running = menu();
	while (running);
}

void Interface::printMenu()
{
	cout << "Command list: " << endl;
	cout << "enter <formula>" << endl;
	cout << "vars" << endl;
	cout << "print" << endl;
	cout << "comp <var0> <var1>...<varN>" << endl;
	cout << "join <formula>" << endl;
	cout << "exit" << endl << endl;
}

bool Interface::menu()
{
	string userInput;
	vector <string> commandsVector;
	getline(cin, userInput);
	commandsVector = split(userInput, SPACE);
	int commandNumber = checkCommand(commandsVector[0]);
	int commandsVectorSize = commandsVector.size();
	switch (commandNumber)
	{
	case COMMAND_ENTER:
	{
		enter(commandsVector);
	} break;
	case COMMAND_VARS:
	{
		vars(commandsVector);
	} break;
	case COMMAND_PRINT:
	{
		print(commandsVector);
	} break;
	case COMMAND_COMP:
	{
		comp(commandsVector);
	} break;
	case COMMAND_JOIN:
	{
		join(commandsVector);
	} break;
	case COMMAND_EXIT:
	{
		return false;
	} break;
	case COMMAND_INCORRECT:
	{
		cout << "Bledna komenda. Sproboj ponownie." << endl;
	} break;
	}
	return true;
}

vector <string> Interface::split(string input, char character)
{
	int inputLength = input.length();
	vector <string> commandsVector;
	string command;
	int i = 0;
	int commandsSize;
	for (i; i < inputLength; i++)
	{
		command = "";
		while (i < inputLength && input[i] != character)
		{
			command += input[i];
			i++;
		}
		commandsVector.push_back(command);
	}
	commandsSize = commandsVector.size();
	for (i = 0; i < commandsSize; i++)
	{
		if (commandsVector[i] == "")
			commandsVector.erase(commandsVector.begin() + i--);
		commandsSize--;
	}
	return commandsVector;
}

int Interface::checkCommand(string command) // -1 if command incorrect
{
	if (command == "enter") return COMMAND_ENTER;
	else if (command == "vars") return COMMAND_VARS;
	else if (command == "print") return COMMAND_PRINT;
	else if (command == "comp") return COMMAND_COMP;
	else if (command == "join") return COMMAND_JOIN;
	else if (command == "exit") return COMMAND_EXIT;
	else return COMMAND_INCORRECT;
}

void Interface::enter(vector <string> &commandsVector)
{
	commandsVector.erase(commandsVector.begin());
	tree = new Tree();
	(*tree).createTree(commandsVector);
	cout << "\n\n";
}

void Interface::vars(vector <string> &commandsVector)
{
	if (commandsVector.size() != 1)
		cout << "Czy chodzi³o ci o komende 'vars'?" << endl;
	else
		(*tree).printVariables();
	cout << "\n\n";
}

void Interface::print(vector <string> &commandsVector)
{
	if (commandsVector.size() != 1)
		cout << "Czy chodzi³o ci o komende 'print'?" << endl;
	else
		(*tree).printPrefix();
	cout << "\n\n";
}

void Interface::comp(vector <string> &commandsVector)
{
	commandsVector.erase(commandsVector.begin());
	(*tree).compute(commandsVector);
	cout << "\n\n";
}

void Interface::join(vector <string> &commandsVector)
{
	commandsVector.erase(commandsVector.begin());
	(*tree).join(commandsVector);
	cout << "\n\n";
}