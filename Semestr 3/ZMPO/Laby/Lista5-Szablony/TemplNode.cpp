#include "TemplNode.h"
#include <string>

using namespace std;

template<class T>
TemplNode<T>::TemplNode()
{
	//children = vector<Node<T>>();
}

template<class T>
TemplNode<T>::~TemplNode()
{
}

template<class T>
void TemplNode<T>::buildTree(vector<string> nodes, int &iterator)
{
	//value = nodes[iterator];
	setNodeType(nodes[iterator]);
	iterator++;
	switch (nodeType) 
	{
		case OPERATOR_2ARG:
		{
			
			for (int i = 0; i < 2; i++) {
				TemplNode<T> child;
				children.push_back(child);

				children[i].buildTree(nodes, iterator);
			}
		} break;
		case OPERATOR_3ARG:
		{
			for (int i = 0; i < 3; i++) {
				TemplNode<T> child;
				children.push_back(child);

				children[i].buildTree(nodes, iterator);
			}
		} break;
	}
}

template<class T>
void TemplNode<T>::setNodeType(string node)
{
	if (isOperator2arg(node)) {
		nodeType = OPERATOR_2ARG;
		oper = node;
	}
	else if (isOperator3arg(node)) {
		nodeType = OPERATOR_3ARG;
		oper = node;
	}
	else if (isInt(node)) {
		nodeType = NUMBER;
		value = stoi(node);
	}
	else if (isDouble(node)) {
		nodeType = NUMBER;
		value = stod(node);
	}
}

template<class T>
T TemplNode<T>::compute()
{
	T result;

	switch (nodeType)
	{
	case NUMBER: {
		result = value;
	} break;
	case OPERATOR_2ARG: {
		T firstChildResult = children[0].compute();
		T secondChildResult = children[1].compute();

		if (oper == "+") {
			result = firstChildResult + secondChildResult;
		}
		else if (oper == "-") {
			result = firstChildResult - secondChildResult;
		}
		else if (oper == "*") {
			result = firstChildResult * secondChildResult;
		}
		else if (oper == "/") {
			result = firstChildResult / secondChildResult;
		}
	} break;
	case OPERATOR_3ARG: {
		double firstChildResult = children[0].compute();
		double secondChildResult = children[1].compute();
		double thirdChildResult = children[2].compute();
		result = firstChildResult + secondChildResult + thirdChildResult;
	} break;
		
	}

	return result;
}

template<class T>
void TemplNode<T>::changeToChar()
{
	switch (nodeType)
	{
	case WORD:
	case NUMBER:
		value = value[0];
		//cout << value << " ";
		break;
	case OPERATOR_2ARG:
	{
		oper = oper[0];
		//cout << oper << " ";
		children[0].changeToChar();
		children[1].changeToChar();
	} break;
	case OPERATOR_3ARG:
	{
		cout << oper << " ";
		children[0].changeToChar();
		children[1].changeToChar();
		children[2].changeToChar();
	} break;
	}
}

template<>
string TemplNode<string>::compute()
{
	string result;

	switch (nodeType)
	{
	case WORD: {
		result = value;
	} break;
	case OPERATOR_2ARG: {
		string firstChildResult = children[0].compute();
		string secondChildResult = children[1].compute();
		
		
		if (oper == "+") {
			//result = firstChildResult + secondChildResult;
			result = 'a';
		}
		else if (oper == "-") {
			// usuwa pierwsze wystapienie "secondChildResult" w "firstChildResult"
			int fPos = 0;
			int sPos = 0;
			bool isFound = false; // czy znaleziono w pierwszym slowie drugie slowo

			if (firstChildResult.length() >= secondChildResult.length()) {
				while (!isFound && fPos < firstChildResult.length()) {
					if (firstChildResult[fPos] == secondChildResult[sPos]) {
						sPos++;
						if (sPos == secondChildResult.length()) {
							isFound = true;
						}
					}
					else {
						sPos = 0;
					}
					fPos++;
				}
			}
			result = firstChildResult;
			
			if (isFound) {
				result.replace(fPos-secondChildResult.length(), secondChildResult.length(), "");
			}
			
			//result = firstChildResult - secondChildResult;
		}
		else if (oper == "*") {
			// Wyciagniecie czesci wspolnej dwoch wyrazow przyklad "* alamakotka kotek = kot" - najdluzszy ciag znakow
			string shorter;
			string longer;
			if (firstChildResult.length() < secondChildResult.length()) {
				shorter = firstChildResult;
				longer = secondChildResult;
			}
			else {
				shorter = secondChildResult;
				longer = firstChildResult;
			}

			bool isFound = false;
			string temp = "";
			string longestCommonPart = "";
			int pos = 0;
			int len = 1;

			while (!isFound) {
				temp = shorter.substr(pos, len);
				if (longer.find(temp) != string::npos) {
					if (temp.length() > longestCommonPart.length()) {
						longestCommonPart = temp;
					}
					len++;
				}
				else {
					pos++;
					len = 1;
				}
				if ((pos + len) > shorter.length()) {
					isFound = true;
				}
			}
			
			result = longestCommonPart;

		}
		else if (oper == "/") {
			// Wynikiem operacji jest ilosc wystapien drugiego wyrazu w pierwszym
			string shorter;
			string longer;
			if (firstChildResult.length() < secondChildResult.length()) {
				shorter = firstChildResult;
				longer = secondChildResult;
			}
			else {
				shorter = secondChildResult;
				longer = firstChildResult;
			}
			
			int counter = 0;
			
			while (longer.length() >= shorter.length()) {
				string temp = longer.substr(0, shorter.length());
				
				if (temp == shorter) {
					counter++;
				}
				longer.erase(0,1);
			}
			
			result = to_string(counter);
		}
		//result = 'a';
	} break;
	case OPERATOR_3ARG: {
		string firstChildResult = children[0].compute();
		string secondChildResult = children[1].compute();
		string thirdChildResult = children[2].compute();
		result = firstChildResult + secondChildResult + thirdChildResult;
	} break;

	}

	return result;
}




void TemplNode<string>::setNodeType(string node)
{
	if (isOperator2arg(node)) {
		nodeType = OPERATOR_2ARG;
		oper = node;
	}
	else if (isOperator3arg(node)) {
		nodeType = OPERATOR_3ARG;
		oper = node;
	}
	else {
		nodeType = WORD;
		value = node;
	}
}


template<class T>
void TemplNode<T>::printPrefix()
{
	switch (nodeType)
	{
	case WORD:
	case NUMBER:
		cout << value << " ";
		break;
	case OPERATOR_2ARG:
	{
		cout << oper << " ";
		children[0].printPrefix();
		children[1].printPrefix();
	} break;
	case OPERATOR_3ARG:
	{
		cout << oper << " ";
		children[0].printPrefix();
		children[1].printPrefix();
		children[2].printPrefix();
	} break;
	}
}

template<class T>
void TemplNode<T>::printInfix()
{
	switch (nodeType)
	{
	case WORD:
	case NUMBER:
		cout << value << " ";
		break;
	case OPERATOR_2ARG:
	{
		children[0].printInfix();
		cout << oper << " ";
		children[1].printInfix();
		
	} break;
	case OPERATOR_3ARG:
	{
		cout << oper << " ";
		children[0].printInfix();
		children[1].printInfix();
		children[2].printInfix();
	} break;
	}
}




template class TemplNode<int>;
template class TemplNode<double>;
template class TemplNode<string>;
template class TemplNode<char>;