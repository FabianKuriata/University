#include <iostream>
#include <string>
#include "Tree.h"

Tree::Tree()
{
	root = new Node();
}

Tree::Tree(Tree &other)
{
	copy(other);
}

Tree::~Tree()
{
	if (root != NULL)
	{
		delete root;
		root = NULL;
	}
}

Tree & Tree::operator=(Tree &other)
{
	copy(other);
	return (*this);
}

Tree & Tree::operator+(Tree &other)
{
	Tree *newTree = new Tree(*this);
	(*newTree).join(other.formula);
	return (*newTree);
}

void Tree::copy(Tree &other)
{
	formula = other.formula;
	variables = other.variables;
	delete root;
	root = new Node();
	*root = *(other.root);
}

void Tree::createTree(vector<string> formula)
{
	checkFormula(formula);
	(*this).formula = formula;

	//cout << "Bedzie przetwarzana formula: "; printFormula();
	root = new Node();
	root->parent = NULL;
	int startIterator = 0;
	(*root).createTree(formula, startIterator);
	(*root).saveVariables(variables);
}

void Tree::createRandomTree() {
	createTree(randomExpression());
}

vector<double> Tree::compute(vector <string> values)
{
	int iter = 0;
	int valuesSize = values.size();
	bool error = false;
	double result = 0;
	vector<double> checkedResult;
	while (iter < valuesSize && isNumeric(values[iter]))
		iter++;
	if (iter != valuesSize) {
		//cout << "Wartosci musz¹ byæ liczbami" << endl;
	}
	else if (valuesSize != variables.size()) {
		//cout << "Liczba wartosci nie zgadza sie z liczb¹ zmiennych" << endl;
	}
	else
	{
		//cout << "Proba wyliczenia wartosci dla " << endl;
		//for (iter = 0; iter <= valuesSize; iter++) // cenna poprawka =
			//cout << variables[iter] << " = " << values[iter] << endl;
		result = (*root).compute(variables, values, error);
		if (error) {
			//cout << "Przy obliczaniu nast¹pi³o dzielenie przez zero." << endl;
			 // drzewo gdy bedzie zawieralo dzielenie przez 0 zostanie najprawdopodobniej wyrzucone z populacji
		}
		else {
			//cout << "Wynik: " << result << endl;
		}
			
	}
	checkedResult.push_back(result);
	checkedResult.push_back(error);
	return checkedResult;
}

void Tree::join(vector<string> formula)
{
	Tree *newTree = new Tree();
	(*newTree).createTree(formula);
	Node *newRoot = (*newTree).root;
	bool flag = false;
	(*root).join(newRoot, flag);
}

void Tree::checkFormula(vector<string> &formula)
{
	int operator3ArgCounter = 0;
	int operator2ArgCounter = 0;
	int variableNumberCounter = 0;
	int formulaIter = 0;
	int formulaLength = formula.size();
	string currentSymbol;
	int symbolType;
	bool formulaComplete = false;
	while (formulaIter < formulaLength && !formulaComplete)
	{
		currentSymbol = formula[formulaIter];
		symbolType = checkSymbol(currentSymbol);		// jeœli currentSymbol to zmienna z niedozwolonymi znakami to s¹ one zastêpywane przez x
		if (formula[formulaIter] != currentSymbol)		// w takim wypadku zapisuje poprawion¹ zmienn¹ do wektora formula
			formula[formulaIter] = currentSymbol;
		if (symbolType == OPERATOR_2ARG)
			operator2ArgCounter++;
		else if (symbolType == VARIABLE || symbolType == NUMBER)
			variableNumberCounter++;
		else if (symbolType == OPERATOR_3ARG)
			operator3ArgCounter++;
		if (variableNumberCounter == operator3ArgCounter * 2 + 1 + operator2ArgCounter)
			formulaComplete = true;
		formulaIter++;
	}
	while (formulaIter < formulaLength)
	{
		formula.erase(formula.begin() + formulaIter);
		formulaLength--;
	}
	while (variableNumberCounter < operator3ArgCounter * 2 + 1 + operator2ArgCounter)
	{
		formula.push_back("1");
		variableNumberCounter++;
	}
}

int Tree::getSize()
{
	int size = 0;
	size = root->size();
	return size;
}

void Tree::countAdaptation(double valFile, double valTree)
{
	levelOfAdaptation += pow((valFile - valTree), 2);  // wzor na poziom adaptacji (doskonalosci)
}



void Tree::printExpression()
{
	//inOrder(root); // TODODODODODOD
	printInfix();
}






int Tree::checkSymbol(string &symbol)
{
	int symbolType;
	if (symbol == "+" || symbol == "-" || symbol == "*" || symbol == "/")
		symbolType = OPERATOR_2ARG;
	else if (symbol == "sin" || symbol == "cos")
		symbolType = OPERATOR_1ARG;
	else if (symbol == "++")
		symbolType = OPERATOR_3ARG;
	else if (isNumeric(symbol))
		symbolType = NUMBER;
	else if (isVariable(symbol))
		symbolType = VARIABLE;
	else
	{
		fixVariable(symbol);
		symbolType = VARIABLE;
	}
	return symbolType;
}

bool Tree::isNumeric(string symbol)
{
	int symbolLength = symbol.length();
	int i = 0;
	while (i < symbolLength && isNumeric(symbol[i]))
		i++;
	return i == symbolLength;
}

bool Tree::isNumeric(char character)
{
	return isdigit(character) || character == '.' || character == '-'; //
}

bool Tree::isLetter(char character)
{
	return ((int)character >= LETTER_A && (int)character <= LETTER_Z) || ((int)character >= LETTER_a && (int)character <= LETTER_z);
}

bool Tree::isVariable(string symbol)
{
	int symbolLength = symbol.length();
	int i = 0;
	while (i < symbolLength && isVariable(symbol[i]))
		i++;
	return i == symbolLength;
}

bool Tree::isVariable(char character)
{
	return isNumeric(character) || isLetter(character);
}

void Tree::fixVariable(string &symbol)
{
	int symbolLength = symbol.length();
	for (int i = 0; i < symbolLength; i++)
		if (!isVariable(symbol[i]))
			symbol[i] = 'x';
}

void Tree::printPrefix()
{
	(*root).printPrefix();
}

bool Tree::isAnyOperWithTwoArg()
{
	bool isAnyOper = root->isAnyOperWithTwoArg();
	return isAnyOper;
}

void Tree::printInfix()
{
	(*root).printInfix();
}

void Tree::printVariables()
{
	int variablesSize = variables.size();
	int variablesIter;
	cout << "zmienne: ";
	for (variablesIter = 0; variablesIter < variablesSize; variablesIter++)
		cout << variables[variablesIter] << " ";
}



void Tree::printFormula()
{
	int formulaSize = formula.size();
	int formulaIter;
	for (formulaIter = 0; formulaIter < formulaSize; formulaIter++)
		cout << formula[formulaIter] << " ";
}

vector<string> Tree::randomExpression() {
	int amountSigns;
	vector<string> expression;

	amountSigns = rand() % 10 + 1;  // dlugosc wyrazenia
	enum WhatSign {
		PLUS = 1,
		MINUS,
		MULTIPLE,
		DIVIDE,
		SINUS,
		COSINUS,
		XX,
		YY,
	};

	for (int i = 0; i < amountSigns; i++) {
		int sig = rand() % 9 + 1; // prawdopodobienstwo liczb i operatorow
		WhatSign sign = static_cast<WhatSign>(sig);
		switch (sign) {
			case PLUS: expression.push_back("+");
						break;
			case MINUS: expression.push_back("-");
				break;
			case MULTIPLE: expression.push_back("*");
				break;
			case DIVIDE: expression.push_back("/");
				break;
			case SINUS: expression.push_back("sin");
				break;
			case COSINUS: expression.push_back("cos");
				break;
			case XX: expression.push_back("x");
				break;
			case YY: {
				bool firstVarExist = false;
				for (int i = 0; i < expression.size(); i++) {
					if (expression[i] == "x") {
						firstVarExist = true;
						i = expression.size();
					}
				}
				if (firstVarExist)
					expression.push_back("y");
				else
					expression.push_back("x");
			}
				break;
			default: {
				int numm = rand() - 2; // zakres wartosci
				double number = (double)rand() / RAND_MAX;// ta zmienna mozna zmienic
				number += numm + number;
				string num = to_string(number);
				expression.push_back(num);

			} break;
		}
	}
	return expression;
}

void Tree::performMutation()
{
	
	int drawMutation = rand() % 1;
	if (drawMutation == 0) {
		// mutacja z dwoma drzewami
		Tree *newRandomTree = new Tree();
		newRandomTree->createRandomTree();
		Node *randomN = randomNode();
		//cout << "randomNode: " << randomN->value << endl;
		//cout << "randomTree: "; newRandomTree->printPrefix(); cout << endl;
		randomN->swapNodes(newRandomTree->root);
		variables.clear();
		formula.clear();
		root->saveFormula(formula);
		root->saveVariables(variables);
	}
	else {
		// mutacja w jednym drzewie, zamiana wezlow
		vMutateInside();
		
	}

}

void Tree::vMutateInside()
{
	bool isAnyOper = false;
	isAnyOper = root->isAnyOperWithTwoArg();

	if (isAnyOper) {
		
		vector<Node*> possibleToChoose;
		//possibleToChoose.clear();
		Node* randomN = this->randomNode();
		Node* node;
		cout << "Losowy wezel : "<< randomN->value << endl;
		if (randomN != root) {
			int size = getSize();
			for (int ii = 0; ii < size; ii++) {
				int iter1 = 0;
				// jesli nie jest przodkiem ani nie jest tym samym wezlem to dodaj go do mozliwych
				cout << "iter: " << iter1 << endl;
				Node* n = new Node();
				node = root->getNode(ii, iter1, false, n);
				cout <<"id" << ii  << "iter: " << iter1 <<"wezly: " << node->value << endl;
				if (randomN != node) {
					bool ancestor1 = node->isAncestor(randomN);
					bool ancestor2 = randomN->isAncestor(node);
					if (!ancestor1 && !ancestor2) {
						possibleToChoose.push_back(node);
					}
				}
				
			}
			if (possibleToChoose.size() > 0) {
				int ran = rand() % possibleToChoose.size();
				randomN->swapNodes(possibleToChoose[ran]);
			}
		}

	}
}

void Tree::crossBreed(Tree* partner)
{
	
	if ((this->root)->children.empty() || (partner->root)->children.empty())
		return;
	else
	{
		Node *thisRandomNode = this->randomNode();
		Node *partnerRandomNode = partner->randomNode();
		while (thisRandomNode == this->root)
			thisRandomNode = this->randomNode();
		while (partnerRandomNode == partner->root)
			partnerRandomNode = partner->randomNode();
		thisRandomNode->swapNodes(partnerRandomNode);
	}

}

Node* Tree::randomNode()
{
	int sizeOfExp = getSize();
	//cout << "rozmiar: " << sizeOfExp << endl;
	int idNode = rand() % sizeOfExp;
	//cout << "id: " << idNode << endl;
	int iter = 0;
	Node* n = new Node();
	root->getNode(idNode, iter, false, n);
	Node* random = n;
	//cout << "Wartosc: " << random->value << endl;
	return random;
}