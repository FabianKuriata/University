#include <string>
#include <sstream>
#include <iostream>
#include <typeinfo>
#include <vector>
#include "TemplTree.h"


using namespace std;

template<class T >
TemplTree<T>::TemplTree()
{
	root = new TemplNode<T>;
}

template<class T>
TemplTree<T>::~TemplTree()
{
	delete root;
}

//template<class T>
//TemplTree<char> & TemplTree<string>::operator=(TemplTree<char> & other)
//{
	//return this;
	// TODO: insert return statement here
//}

template<class T>
bool TemplTree<T>::build(string prefixExp)
{	
	// podzielenie wyrazenia na elementy 
	vector<string> expSplitted; // przechowuje podzielone wyrazenia
	split(prefixExp, expSplitted);
	
	// sprawdzenie czy poprawne
	bool isCorrect = true;

	int operators2arg = 0, operators3arg = 0, numbers = 0, words = 0, toCorrect = 0;
	isCorrect = isCorrectExp(expSplitted, operators2arg, operators3arg, numbers, words, toCorrect);
	// Napraw wyrazenie gdy jest cos do poprawy toCorrect < 0 - zabierz "toCorrect" liczb, toCorrect > 0 dodaj DEFAULT_VALUE "toCorrect" razy
	if (toCorrect != 0) {
		repairExp(prefixExp, expSplitted, toCorrect);
	}
	// popraw wyrazenie ktore uleglo zmianie
	if (!isCorrect) {
		prefixExp = "";
		for (int i = 0; i < expSplitted.size(); i++) {
			prefixExp += expSplitted[i] + " ";
		}
	}
	expression = expSplitted;
	
	int startIterator = 0;
	(*root).buildTree(expSplitted, startIterator);
	
	if (typeid(T).name() == typeid(string).name()) {
		getCharTree(root);
	}

	return isCorrect;

	///// TWORZENIE DRZEWA //////
	
}

template<class T>
void TemplTree<T>::split(string & exp, vector<string> &splittedExp)
{
	char splitChar = ' '; // znak dzielacy

	istringstream sp(exp);
	string each;
	while (getline(sp, each, splitChar)) {
		splittedExp.push_back(each);
	}
}



template<class T>
void TemplTree<T>::repairExp(string & prefixExp, vector<string> &expSplit, int toCorrect)
{
	// wartosc jaka zostanie dodana gdy za duzo operatorow a za malo liczb
	const string DEFAULT_VALUE = (typeid(T).name() == typeid(string).name()) ? "a" : "1";
	
	
	if (isInt(expSplit[0])|| isDouble(expSplit[0])) {
		int toDelete = expSplit.size() - 1;
		for (int i = 0; i < toDelete; i++) {
			expSplit.pop_back();
		}
	}
	else if (toCorrect > 0) {
		for (int i = 0; i < toCorrect; i++) {
			expSplit.push_back(DEFAULT_VALUE);
		}
	}
	else if (toCorrect < 0){
		for (int i = 0; i < toCorrect*-1; i++) {
			expSplit.pop_back();
		}
	}
	
}

template<class T>
bool TemplTree<T>::isCorrectExp(vector<string> &expSplit, int & quantOper2arg, int & quantOper3arg, int & quantNum, int & words, int & quantToCorrect)
{
	// operatory na lewo , liczby na prawo
	// licz liczbe poszczegolnych elementow
	vector<string> operators;
	vector<string> numbers;
	bool isCorrect = true;

	for (int i = 0; i < expSplit.size(); i++) {
		if (isOperator2arg(expSplit[i])) {
			operators.push_back(expSplit[i]);
			quantOper2arg++;
		}
		else if (isOperator3arg(expSplit[i])) {
			operators.push_back(expSplit[i]);
			quantOper3arg++;
		}
		else if (isDouble(expSplit[i])) {
			if (typeid(T).name() == typeid(int).name()) {
				if (!isInt(expSplit[i])) {
					int val = stoi(expSplit[i]);
					expSplit[i] = to_string(val);
					isCorrect = false;
				}
			}
			numbers.push_back(expSplit[i]);
			quantNum++;
		}
		else
			isCorrect = false;
	}
	expSplit.clear();
	for (int i = 0; i < operators.size(); i++) {
		expSplit.push_back(operators[i]);
	}
	for (int i = 0; i < numbers.size(); i++) {
		expSplit.push_back(numbers[i]);
	}

	//sprawdz ilosc potrzebnych liczb
	int elemNeeded;
	if (quantOper3arg > 0) {
		elemNeeded = (quantOper3arg * 3) - ((quantOper3arg % (quantOper3arg * 3)) - 1) + quantOper2arg; // ilosc potrzebnych elementow do poprawnego wyrazenia/drzewa
	}
	else
		elemNeeded = quantOper2arg + 1;
	
	quantToCorrect = elemNeeded - quantNum;

	if (quantNum != elemNeeded) {
		isCorrect = false;
		//return true;
	}
	
	return isCorrect;
}

template<class T>
T TemplTree<T>::compute()
{
	T result;	
	result = (*root).compute();
	
	return result;
}

template <>
TemplTree<char> TemplTree<string>::getCharTree(TemplNode<string> *& r)
{ 
	r->changeToChar();
	return TemplTree<char>();
}



template<class T>
void TemplTree<T>::printPrefix()
{
	(*root).printPrefix();
}

template<class T>
void TemplTree<T>::printInfix()
{
	(*root).printInfix();
}


bool TemplTree<string>::isCorrectExp(vector<string> &expSplit, int & quantOper2arg, int & quantOper3arg, int & quantNum, int & words, int & quantToCorrect)
{
	// operatory na lewo , liczby na prawo
	// licz liczbe poszczegolnych elementow
	vector<string> operators;
	vector<string> allWords;
	bool isCorrect = true;

	for (int i = 0; i < expSplit.size(); i++) {
		if (isOperator2arg(expSplit[i])) {
			operators.push_back(expSplit[i]);
			quantOper2arg++;
		}
		else if (isOperator3arg(expSplit[i])) {
			operators.push_back(expSplit[i]);
			quantOper3arg++;
		}
		else if (isWord(expSplit[i])) {
			allWords.push_back(expSplit[i]);
			words++;
		}
		else
			isCorrect = false;
	}
	
	expSplit.clear();
	for (int i = 0; i < operators.size(); i++) {
		expSplit.push_back(operators[i]);
	}
	for (int i = 0; i < allWords.size(); i++) {
		expSplit.push_back(allWords[i]);
	}
	////////////////////////////////////////////////

	//sprawdz ilosc potrzebnych liczb
	int elemNeeded;
	if (quantOper3arg > 0) {
		elemNeeded = (quantOper3arg * 3) - ((quantOper3arg % (quantOper3arg * 3)) - 1) + quantOper2arg; // ilosc potrzebnych elementow do poprawnego wyrazenia/drzewa
	}
	else
		elemNeeded = quantOper2arg + 1;

	quantToCorrect = elemNeeded - words;

	if (words != elemNeeded) {
		isCorrect = false;
	}

	return isCorrect;
}


template class TemplTree<string>;
template class TemplTree<int>;
template class TemplTree<double>;
template class TemplTree<char>;
