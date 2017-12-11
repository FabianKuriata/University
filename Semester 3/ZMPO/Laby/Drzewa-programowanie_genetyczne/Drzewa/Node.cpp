#include <iostream>
#include <string>
#include <cmath>
#include "Node.h"

Node::Node()
{
	value = "";
	valueType = -1;
}

Node::Node(Node &other)
{
	value = other.value;
	valueType = other.valueType;
	int childrenVectorLength = other.childrenVector.size();
	Node *childPointer;
	for (int i = 0; i < childrenVectorLength; i++)
	{
		childPointer = new Node(*childrenVector[i]);
		childrenVector.push_back(childPointer);
	}
}

Node::~Node()
{
	int childrenVectorSize = childrenVector.size();
	for (int i = 0; i < childrenVectorSize; i++)
	{
		if (childrenVector[i] != NULL)
		{
			delete childrenVector[i];
			childrenVector[i] = NULL;
		}
	}
}

Node & Node::operator=(Node &other)
{
	value = other.value;
	valueType = other.valueType;
	int childrenVectorSize = other.childrenVector.size();
	for (int i = 0; i < childrenVectorSize; i++)
		childrenVector.push_back(new Node(*other.childrenVector[i]));
	return (*this);
}

void Node::createTree(vector<string> formula, int &iterator)
{
	value = formula[iterator];
	checkValueType(value);
	iterator++;
	switch (valueType)
	{
	case OPERATOR_1ARG:
	{
		Node *child = new Node();
		child->parent = this;
		childrenVector.push_back(child);
		(*childrenVector[0]).createTree(formula, iterator);
	} break;
	case OPERATOR_2ARG:
	{
		Node *child;
		for (int i = 0; i < 2; i++) {
			child = new Node();
			child->parent = this;
			childrenVector.push_back(child);

			(*childrenVector[i]).createTree(formula, iterator);
		}
		
	} break;
	case OPERATOR_3ARG:
	{
		Node *child;
		for (int i = 0; i < 3; i++) {
			child = new Node();
			child->parent = this;
			childrenVector.push_back(child);

			(*childrenVector[i]).createTree(formula, iterator);
		}
	}	break;
	}
}

void Node::saveVariables(vector<string> &variables)
{
	switch (valueType)
	{
	case VARIABLE:
		if (find(variables.begin(), variables.end(), value) == variables.end())
			variables.push_back(value);
		break;
	case OPERATOR_1ARG:
		(*childrenVector[0]).saveVariables(variables);
		break;
	case OPERATOR_2ARG:
	{
		(*childrenVector[0]).saveVariables(variables);
		(*childrenVector[1]).saveVariables(variables);
	} break;
	case OPERATOR_3ARG:
	{
		(*childrenVector[0]).saveVariables(variables);
		(*childrenVector[1]).saveVariables(variables);
		(*childrenVector[2]).saveVariables(variables);
	}	break;
	}
}

double Node::compute(vector<string> &variables, vector<string> &values, bool &error)
{
	double result;
	switch (valueType)
	{
	case NUMBER:
		result = stod(value.c_str());
		break;
	case VARIABLE:
	{
		int variablesSize = variables.size();
		int iter = 0;
		while (iter < variablesSize && value != variables[iter])
			iter++;
		result = stod(values[iter].c_str());
	} break;
	case OPERATOR_1ARG:
	{
		double childResult = (*childrenVector[0]).compute(variables, values, error);
		if (error)
			result = 0;
		else if (value == "sin")
			result = sin(childResult);
		else
			result = cos(childResult);
	} break;
	case OPERATOR_2ARG:
	{
		double firstChildResult = (*childrenVector[0]).compute(variables, values, error);
		double secondChildResult = (*childrenVector[1]).compute(variables, values, error);
		if (error)
			result = 0;
		else if (value == "+")
			result = firstChildResult + secondChildResult;
		else if (value == "-")
			result = firstChildResult - secondChildResult;
		else if (value == "*")
			result = firstChildResult * secondChildResult;
		else if (value == "/" && secondChildResult != 0)
			result = firstChildResult / secondChildResult;
		else
		{
			error = true;
			result = 0;
		}
	} break;
	case OPERATOR_3ARG:
	{
		double firstChildResult = (*childrenVector[0]).compute(variables, values, error);
		double secondChildResult = (*childrenVector[1]).compute(variables, values, error);
		double thirdChildResult = (*childrenVector[2]).compute(variables, values, error);
		result = firstChildResult + secondChildResult + thirdChildResult;
	} break;
	}
	return result;
}

void Node::join(Node *node, bool &flag)
{
	switch (valueType)
	{
	case VARIABLE:
	case NUMBER:
	{
		flag = true;
	}	break;
	case OPERATOR_1ARG:
	case OPERATOR_2ARG:
	case OPERATOR_3ARG:
	{
		(*childrenVector[0]).join(node, flag);
		if (flag)
		{
			(*childrenVector[0]) = *node;
			flag = false;
		}
	}	break;
	}
}

void Node::checkValueType(string value)
{
	if (value == "+" || value == "-" || value == "*" || value == "/")
		valueType = OPERATOR_2ARG;
	else if (value == "sin" || value == "cos")
		valueType = OPERATOR_1ARG;
	else if (value == "++")
		valueType = OPERATOR_3ARG;
	else if (isNumeric(value))
		valueType = NUMBER;
	else
		valueType = VARIABLE;
}

bool Node::isNumeric(string symbol)
{
	int symbolLength = symbol.length();
	int i = 0;
	while (i < symbolLength && (isdigit(symbol[i]) || symbol[i] == '.'))
		i++;
	return i == symbolLength;
}

void Node::printPrefix()
{
	switch (valueType)
	{
	case VARIABLE:
	case NUMBER:
		cout << value << " ";
		break;
	case OPERATOR_1ARG:
	{
		cout << value << " ";
		(*childrenVector[0]).printPrefix();
	} break;
	case OPERATOR_2ARG:
	{
		cout << value << " ";
		(*childrenVector[0]).printPrefix();
		(*childrenVector[1]).printPrefix();
	} break;
	case OPERATOR_3ARG:
	{
		cout << value << " ";
		(*childrenVector[0]).printPrefix();
		(*childrenVector[1]).printPrefix();
		(*childrenVector[2]).printPrefix();
	}
	}
}