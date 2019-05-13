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
	parent = NULL;
	int childrenVectorLength = other.children.size();
	Node *childPointer;
	for (int i = 0; i < childrenVectorLength; i++)
	{
		childPointer = new Node(*other.children[i]);
		childPointer->parent = this;
		children.push_back(childPointer);
	}
}

Node::~Node()
{
	int childrenSize = children.size();
	for (int i = 0; i < childrenSize; i++)
	{
		if (children[i] != NULL)
		{
			delete children[i];
			//childrenVector[i] = NULL;
		}
	}
	children.clear();
}

Node & Node::operator=(Node &other)
{
	value = other.value;
	valueType = other.valueType;
	parent = NULL;
	int childrenSize = other.children.size();
	for (int i = 0; i < children.size(); i++) {
		delete children[i];
	}
	children.clear();

	for (int i = 0; i < childrenSize; i++) {
		children.push_back(new Node(*other.children[i]));
		children[i]->parent = this;
	}
		
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
		children.push_back(child);
		(*children[0]).createTree(formula, iterator);
	} break;
	case OPERATOR_2ARG:
	{
		Node *child;
		for (int i = 0; i < 2; i++) {
			child = new Node();
			child->parent = this;
			children.push_back(child);

			(*children[i]).createTree(formula, iterator);
		}
		
	} break;
	case OPERATOR_3ARG:
	{
		Node *child;
		for (int i = 0; i < 3; i++) {
			child = new Node();
			child->parent = this;
			children.push_back(child);

			(*children[i]).createTree(formula, iterator);
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
		(*children[0]).saveVariables(variables);
		break;
	case OPERATOR_2ARG:
	{
		(*children[0]).saveVariables(variables);
		(*children[1]).saveVariables(variables);
	} break;
	case OPERATOR_3ARG:
	{
		(*children[0]).saveVariables(variables);
		(*children[1]).saveVariables(variables);
		(*children[2]).saveVariables(variables);
	}	break;
	}
}

void Node::saveFormula(vector<string> &formula)
{
	switch (valueType)
	{
	case NUMBER:
	case VARIABLE:
		formula.push_back(value);
		break;
	case OPERATOR_1ARG:
	{
		formula.push_back(value);
		children[0]->saveFormula(formula);
	}   break;
	case OPERATOR_2ARG:
	{
		formula.push_back(value);
		children[0]->saveFormula(formula);
		children[1]->saveFormula(formula);
	}   break;
	case OPERATOR_3ARG:
	{
		formula.push_back(value);
		children[0]->saveFormula(formula);
		children[1]->saveFormula(formula);
		children[2]->saveFormula(formula);
	}   break;
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
		double childResult = (*children[0]).compute(variables, values, error);
		if (error)
			result = 0;
		else if (value == "sin")
			result = sin(childResult);
		else
			result = cos(childResult);
	} break;
	case OPERATOR_2ARG:
	{
		double firstChildResult = (*children[0]).compute(variables, values, error);
		double secondChildResult = (*children[1]).compute(variables, values, error);
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
		double firstChildResult = (*children[0]).compute(variables, values, error);
		double secondChildResult = (*children[1]).compute(variables, values, error);
		double thirdChildResult = (*children[2]).compute(variables, values, error);
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
		(*children[0]).join(node, flag);
		if (flag)
		{
			(*children[0]) = *node;
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
	while (i < symbolLength && (isdigit(symbol[i]) || symbol[i] == '.' || symbol[0] == '-'))
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
		(*children[0]).printPrefix();
	} break;
	case OPERATOR_2ARG:
	{
		cout << value << " ";
		(*children[0]).printPrefix();
		(*children[1]).printPrefix();
	} break;
	case OPERATOR_3ARG:
	{
		cout << value << " ";
		(*children[0]).printPrefix();
		(*children[1]).printPrefix();
		(*children[2]).printPrefix();
	}
	}
}

bool Node::isAnyOperWithTwoArg()
{
	switch (valueType)
	{
	case VARIABLE:
	case NUMBER:
		break;
	case OPERATOR_1ARG:
	{
		//cout << value << " ";
		return (*children[0]).isAnyOperWithTwoArg();
	} break;
	case OPERATOR_2ARG:
	{
		//cout << value << " ";
		return true;
		
	} break;
	case OPERATOR_3ARG:
	{
		//cout << value << " ";
		return true;
	}
	}
	
	//return false;
}

bool Node::isAncestor(Node * other)
{
	if (other->parent != NULL) {
		if (this == other->parent) {
			return true;
		}
		else
			isAncestor(other->parent);
	}
	else if(other->parent == NULL)
		return false;
}

void Node::printInfix()
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
		(*children[0]).printInfix();
	} break;
	case OPERATOR_2ARG:
	{
		(*children[0]).printInfix();
		cout << value << " ";
		(*children[1]).printInfix();
	} break;
	case OPERATOR_3ARG:
	{
		
		(*children[0]).printPrefix();
		(*children[1]).printPrefix();
		cout << value << " ";
		(*children[2]).printPrefix();
	}
	}
}

int Node::size()
{
	switch (valueType)
	{
	case VARIABLE:
	case NUMBER:
		return 1;
		break;
	case OPERATOR_1ARG:
	{
		//cout << value << " ";
		return 1 + (*children[0]).size();
		
	} break;
	case OPERATOR_2ARG:
	{
		return 1 + (*children[0]).size() + (*children[1]).size();
		//cout << value << " ";
	} break;
	case OPERATOR_3ARG:
	{

		return 1 + (*children[0]).size() + (*children[1]).size() + (*children[2]).size();
	}
	}
	return 0;
}

Node* Node::getNode(int index, int &iter, bool flag, Node *&a)
{

	
	//cout <<"Wartosc: "<< this->value << "  ";
	//cout << "It: " << iter << endl;

	if (index == iter) {
		flag = true;
		a = this;
		//cout << "ZNALAZLO SIE" << "   " << a->value << endl << endl;
	}	
	else {
		//cout <<endl <<"WItA ELSE" << endl << "Index: " << index << "   ";
		//cout << "It: " << iter << endl;

		//cout << "SZUKAMY DALEJ!" << endl;
	}
	iter++;

	switch (valueType)
	{
		
	case VARIABLE:
	case NUMBER:
		//cout << value << " ";
		//iter++;
		break;
	case OPERATOR_1ARG:
	{
		//cout << value << " ";

		children[0]->getNode(index, iter, false, a);
	} break;
	case OPERATOR_2ARG:
	{

		//iter++;
		if (flag) {
			//return this;
		}
		children[0]->getNode(index, iter, false, a);
		children[1]->getNode(index, iter, false, a);
	
	
	} break;
	case OPERATOR_3ARG:
	{
		//iter++;
		//children[0]->getNode(index, iter, false);
		//children[1]->getNode(index, iter, false);
		//children[2]->getNode(index, iter, false);
	}
	}
	if (flag)
		return a;
}

void Node::swapNodes(Node *other)                        //zamienia wêz³y miejscmi, zamienia rodziców a rodzicom dzieci
{
	Node *thisParent = parent;
	int otherId;                                         //index w wektorze dzieci na którym wysêpuje wêze³ other u sowjego rodzica
	int thisId;                                          //index w wektorze dzieci na którym wysêpuje wêze³ this u sowjego rodzica  

	if (this->parent != NULL && other->parent != NULL)
	{
		otherId = (*other->parent).findChildId(other);
		thisId = (*this->parent).findChildId(this);
		this->parent = other->parent;
		other->parent = thisParent;
		(*this->parent).children[otherId] = this;
		(*other->parent).children[thisId] = other;
	}
	else if (this->parent == NULL && other->parent != NULL)
	{
		otherId = (*other->parent).findChildId(other);
		Node *thisCopy = new Node(*this);
		thisCopy->parent = other->parent;
		other->parent = thisParent;
		(*thisCopy->parent).children[otherId] = thisCopy;
		*this = *other;
	}
	else if (this->parent != NULL && other->parent == NULL)
	{
		thisId = (*this->parent).findChildId(this);
		Node *otherCopy = new Node(*other);
		otherCopy->parent = this->parent;
		this->parent = other->parent;
		(*otherCopy->parent).children[thisId] = otherCopy;
		*other = *this;
	}
	else
	{
		Node *thisCopy = new Node(*this);
		*this = *other;
		*other = *thisCopy;
		delete thisCopy;
	}
}

int Node::findChildId(Node *child)                   //szuka indeksu z wektora dzieci który wskazuje na podane dziecko, jeœli nie takiego dzeicka zwraca -1
{
	int childrenSize = children.size();
	int i = 0;
	while (i < childrenSize && children[i] != child)
		i++;
	return i == childrenSize ? -1 : i;
}