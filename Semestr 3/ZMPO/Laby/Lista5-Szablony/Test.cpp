#include "Test.h"
#include <iostream>

using namespace std;

void Test::testTreeString()
{
	TemplTree<string> treeString;
	//bool isCorrect = true;
	const string add = "+ smak osz";
	const string sub = "- smak mak";
	const string mul = "* alabama obama";
	const string div = "/ alalalabamba la";
	cout << "\n-------TEST Drzewo STRING----------" << endl;
	treeString.build(add);
	//if (!isCorrect) {
	//	cout << endl << "Bledne wyrazenie. Poprawiono na : " << endl;
	//}
	treeString.printInfix();
	cout << "= " << treeString.compute() << endl;
	
	treeString.build(sub);
	cout << endl;
	treeString.printInfix();
	cout << "= " << treeString.compute() << endl;
	
	treeString.build(mul);
	cout << endl;
	treeString.printInfix();
	cout << "= " << treeString.compute() << endl;
	
	treeString.build(div);
	cout << endl;
	treeString.printInfix();
	cout << "= " << treeString.compute() << endl;
}

void Test::testTreeInt()
{
	TemplTree<int> treeInt;

	const string add = "+ 3 3";
	const string sub = "- 3 3";
	const string mul = "* 3 3";
	const string div = "/ 3 3";

	cout << "\n-------TEST Drzewo INT----------" << endl;
	treeInt.build(add);
	treeInt.printInfix();
	cout << "= " << treeInt.compute() << endl;

	treeInt.build(sub);
	treeInt.printInfix();
	cout << "= " << treeInt.compute() << endl;

	treeInt.build(mul);
	treeInt.printInfix();
	cout << "= " << treeInt.compute() << endl;

	treeInt.build(div);
	treeInt.printInfix();
	cout << "= " << treeInt.compute() << endl;
}

void Test::testTreeDouble()
{
	TemplTree<double> treeDouble;

	const string add = "+ 3.2 3.1";
	const string sub = "- 3.1 3.3";
	const string mul = "* 4.5 3.2";
	const string div = "/ 3.2 3.5";

	cout << "\n-------TEST Drzewo DOUBLE----------" << endl;
	treeDouble.build(add);
	treeDouble.printInfix();
	cout << "= " << treeDouble.compute() << endl;

	treeDouble.build(sub);
	treeDouble.printInfix();
	cout << "= " << treeDouble.compute() << endl;

	treeDouble.build(mul);
	treeDouble.printInfix();
	cout << "= " << treeDouble.compute() << endl;

	treeDouble.build(div);
	treeDouble.printInfix();
	cout << "= " << treeDouble.compute() << endl;
}


void Test::testTrees()
{
	testTreeInt();
	testTreeDouble();
	testTreeString();
}




void Test::myExp(string & myExp, string type)
{
	//TemplTree<int> myTree;
	cout << endl << "--------------------------" << endl;
	if (type == "int") {
		TemplTree<int> myTree;
		bool isCorrect = true;
		isCorrect = myTree.build(myExp);
		if (!isCorrect) {
			cout << endl << "Bledne wyrazenie. Poprawiono na : " << endl;
		}

		myTree.printInfix();
		cout << "= " << myTree.compute() << endl;
	}
	else if (type == "double") {
		TemplTree<double> myTree;
		bool isCorrect = true;
		isCorrect = myTree.build(myExp);
		if (!isCorrect) {
			cout << endl << "Bledne wyrazenie. Poprawiono na : " << endl;
		}

		myTree.printInfix();
		cout << "= " << myTree.compute() << endl;
	}
	else if (type == "string") {
		TemplTree<string> myTree;
		bool isCorrect = true;
		isCorrect = myTree.build(myExp);
		if (!isCorrect) {
			cout << endl << "Bledne wyrazenie. Poprawiono na : " << endl;
		}

		myTree.printInfix();
		cout << "= " << myTree.compute() << endl;
	}
	else
		cout << "Typ drzewa nie obslugiwany" << endl;
		

	
}

