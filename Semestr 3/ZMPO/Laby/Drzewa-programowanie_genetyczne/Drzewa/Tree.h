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
	vector<double> compute(vector<string> formula);
	void join(vector<string> formula);
	void printPrefix();
	bool isAnyOperWithTwoArg();
	void printInfix();
	void printVariables();
	
	void performMutation(); // mutacja
	void vMutateInside();
	void crossBreed(Tree* partner); // krzyzowanie

	int getSize();
	void countAdaptation(double valFile, double valTree);
	double levelOfAdaptation;
	//vector<string> getFormula();
	vector<string> variables;
	void printExpression();
	Node* randomNode();
	Node *root;
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
	//Node *root;
	vector<string> formula;
	void checkFormula(vector<string> &formula);
	//void preOrder(Node * n, vector<string> &formula);
	//void inOrder(Node* n);
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