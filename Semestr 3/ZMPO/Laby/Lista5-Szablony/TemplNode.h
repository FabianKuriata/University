#pragma once
#include <vector>
#include "Tools.cpp"

using namespace std;

template<class T>
class TemplNode
{
private:
	enum Type {
		NUMBER,
		WORD,
		OPERATOR_2ARG,
		OPERATOR_3ARG
	};
	Type nodeType;
public:
	TemplNode();
	~TemplNode();
	
	T value;
	string oper;
	vector<TemplNode<T>> children;
	void buildTree(vector<string> nodes, int &iterator);
	void setNodeType(string node);
	T compute();
	void changeToChar();
	void printPrefix();
	void printInfix();
	//T getNode();
};

