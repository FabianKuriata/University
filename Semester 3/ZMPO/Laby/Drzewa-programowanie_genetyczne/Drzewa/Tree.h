#pragma once
#include <vector>
#include <stdlib.h>
#include <ctime> 
#include "Node.h"


using namespace std;

class Tree
{
public:
	Tree();
	Tree(Tree &other);
	~Tree();
	Tree & operator=(Tree &other);
	Tree & operator+(Tree &other);
	void createTree(vector<string> formula);
	void createRandomTree();
	void compute(vector<string> formula);
	void join(vector<string> formula);
	void printPrefix();
	void printVariables();
	
	void performMutation();
	void crossBreed();
	double levelOfAdaptation;
private:
	
	enum
	{
		OPERATOR_2ARG,		//operacja dwuargumentowa (+,-,*,/)
		OPERATOR_1ARG,		//operacja jednoargumentowa (sin, cos)
		VARIABLE,			//zmienna
		NUMBER,				//liczba
		OPERATOR_3ARG
	};
	const int LETTER_A = 65;
	const int LETTER_Z = 90;
	const int LETTER_a = 97;
	const int LETTER_z = 122;
	Node *root;
	vector<string> formula;
	vector<string> variables;
	void checkFormula(vector<string> &formula);
	void printFormula();
	vector<string> randomExpression();
	int checkSymbol(string &symbol);
	bool isNumeric(string symbol);
	bool isNumeric(char character);
	bool isLetter(char character);
	bool isVariable(string symbol);		//sprwdza czy symbol jest zmienn¹, zmienna zawiera liczby lub litery 
	bool isVariable(char character);
	void fixVariable(string &symbol);
	void copy(Tree &other);
};