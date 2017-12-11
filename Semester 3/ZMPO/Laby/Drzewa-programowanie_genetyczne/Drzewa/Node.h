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
	void createTree(vector<string> formula, int &iterator);
	void saveVariables(vector<string> &variables);
	double compute(vector<string> &variables, vector<string> &values, bool &error);
	void join(Node *node, bool &flag);
	Node *parent;
private:
	enum
	{
		OPERATOR_2ARG,		//operacja dwuargumentowa (+,-,*,/)
		OPERATOR_1ARG,		//operacja jednoargumentowa (sin, cos)
		VARIABLE,			//zmienna
		NUMBER,				//liczba
		OPERATOR_3ARG
	};
	string value;
	int valueType;
	vector<Node*> childrenVector;
	void checkValueType(string value);
	bool isNumeric(string symbol);
};