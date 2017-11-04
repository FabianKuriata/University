#pragma once
#include "main.h"
using namespace std;

class SparseCell
{
	int *coordinates;
	int value;
	int dimensions;

public:
	friend class SparseMatrix;
	SparseCell();
	SparseCell(int *&coord, int val, int dim);
	~SparseCell();
	int tak;
	void showCoordinates();
};

