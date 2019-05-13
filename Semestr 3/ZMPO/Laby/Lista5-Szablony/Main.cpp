#include <iostream>
#include <string>
#include "TemplTree.h"
#include "Test.h"

using namespace std;

int main() {

	string expressionInt = "+ 3 - 2 * 2 3";
	string expressionDouble = "* 3.2 - 2.1 / 2.2 + 1.2 2.7";
	string expressionString = "* akotekmlotek + mlotek - ek / aaasa a";
	
	Test::testTrees();
	Test::myExp(expressionString, "string");
	Test::myExp(expressionDouble, "double");
	Test::myExp(expressionInt, "int");

	TemplTree<string> tree1;
	TemplTree<char> tree2;
	

	getchar();
	return 0;
}