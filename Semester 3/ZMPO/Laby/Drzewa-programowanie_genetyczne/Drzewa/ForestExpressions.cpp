#include "ForestExpressions.h"

using namespace std;

ForestExpressions::ForestExpressions()
{
}

ForestExpressions::ForestExpressions(int sizeOfPop, int iterNum, int probC, int probMut, string path)
{
	sizeOfPopulation = sizeOfPop;
	iterNumber = iterNum;
	probCross = probC;
	probMutation = probMut;
	pathOfFile = path;
}

ForestExpressions::~ForestExpressions()
{
}

Tree* ForestExpressions::findPropertyExpression()
{
	Tree* mostAccurate = new Tree();
	for (int i = 0; i < sizeOfPopulation; i++) {
		Tree* newTree;
		newTree = new Tree();
		newTree->createRandomTree();
		forest.push_back(newTree);
	}

	for (int i = 0; i < sizeOfPopulation; i++) {
		forest[i]->printPrefix();
		cout << "=";
		//forest[i]->compute(forest[i]); /// compute przyjmuje parametr ktory trzeba wyrzucic oraz zrobic metode do wytworzenia formuly/wyrazenia z drzewa
		cout << endl;
	}

	return mostAccurate;
}
