#pragma once
#include "Tree.h"
#include <iostream>
#include <cstdlib>
#include <fstream>
#include <sstream>

class ForestExpressions
{
public:
	ForestExpressions();
	ForestExpressions(int sizeOfPop, int iterNum, int probC, int probMut, string pathOfFile);
	~ForestExpressions();
	Tree* findPropertyExpression();
private:
	int sizeOfPopulation;
	int iterNumber;
	int probCross;
	int probMutation;
	string pathOfFile;
	
	vector<Tree*> forest;

};

