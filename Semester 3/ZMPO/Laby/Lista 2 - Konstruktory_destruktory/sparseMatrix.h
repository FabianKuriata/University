#pragma once
#include "main.h"
#include "SparseCell.h"
#include <string>
using namespace std;

class SparseMatrix
{	
	//friend SparseCell;
	int id;
	int currentOffsetCell;
	int currentOffsetDimensions;
	int sizeTabCell;
	string name;
	int dimensions;
	int *ranges;
	int defaultVal;
	SparseCell **cells;

public:
	static int IdCounter;
	
	SparseMatrix();
	SparseMatrix(string);
	SparseMatrix(const SparseMatrix &c);
	~SparseMatrix();

	int getId();
	void setName(string n);
	string getName();
	void setDimensionsRanges(int dim, int *&ran);
	int addCell(SparseCell cell);
	void printRanges();
	int *getRanges();
	int getDimensions();
	void setDefVal(int defVal);
	int getDefVal();
	void printCells();
};

