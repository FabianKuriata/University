#pragma once
#include "TemplNode.h"

using namespace std;

template<class T>
class TemplTree
{
private:
	
	//string expression;
	vector<string> expression;
public:
	TemplNode<T> *root;
	TemplTree();
	~TemplTree();
	//TemplTree<string> & operator=(TemplTree<char> &other);
	bool build(string prefixExp);
	void repairExp(string & prefixExp, vector<string> &expSplit, int toCorrect);
	bool isCorrectExp(vector<string> &expSplit, int &quantOper2arg, int &quantOper3arg, int &quantNum, int &words, int &quantToCorrect);
	T compute();
	TemplTree<char> getCharTree(TemplNode<string>*& r);
	void changeToChar();
	
	void printPrefix();
	void printInfix();
	void split(string &exp, vector<string> &splittedExp);
	
};

