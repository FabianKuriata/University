#pragma once
#include <vector>

using namespace std;

class Node
{
public:
	Node();
	Node(Node &other);
	~Node();
	Node & operator=(Node &Node);
	void printPrefix();
	bool isAnyOperWithTwoArg();
	bool isAncestor(Node* other);
	void printInfix();
	int size();
	Node* getNode(int index, int &iter, bool flag, Node*& a);

	void swapNodes(Node * other);
	int findChildId(Node * child);
	void createTree(vector<string> formula, int &iterator);
	void saveVariables(vector<string> &variables);
	void saveFormula(vector<string>& formula);
	double compute(vector<string> &variables, vector<string> &values, bool &error);
	void join(Node *node, bool &flag);
	Node *parent;
	string value;
	vector<Node*> children;
private:
	enum
	{
		OPERATOR_2ARG,		//operacja dwuargumentowa (+,-,*,/)
		OPERATOR_1ARG,		//operacja jednoargumentowa (sin, cos)
		VARIABLE,			//zmienna
		NUMBER,				//liczba
		OPERATOR_3ARG
	};
	
	int valueType;
	void checkValueType(string value);
	bool isNumeric(string symbol);
};