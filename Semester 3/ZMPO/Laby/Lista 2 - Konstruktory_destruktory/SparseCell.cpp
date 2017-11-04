#include "SparseCell.h"

using namespace std;

SparseCell::SparseCell()
{

}

SparseCell::SparseCell(int *&coord, int val, int dim)
{
	value = val;
	coordinates = coord;
	dimensions = dim;
}


SparseCell::~SparseCell()
{

}

void SparseCell::showCoordinates()
{
	for (int i = 0; i < dimensions; i++) {
		cout << coordinates[i] << ",";
	}
}
