#include "sparseMatrix.h"

using namespace std;

int SparseMatrix::IdCounter = 0;

class SparceCell;

SparseMatrix::SparseMatrix() {
	name = "Fajna_nazwa";
	sizeTabCell = 5;
	currentOffsetCell = 0;
	currentOffsetDimensions = 0;
	dimensions = 10;
	defaultVal = 0;
	id = IdCounter;
	IdCounter++;
	cout << "create: " << " "<< name << endl;
}


SparseMatrix::SparseMatrix(string n) {
	name = n;
	id = IdCounter;
	IdCounter++;
	cout << "create: " << name << endl;
}

SparseMatrix::SparseMatrix(const SparseMatrix &c) {
	id = IdCounter;
	name = c.name + "_copy";
	dimensions = c.dimensions;
	ranges = new int[dimensions];
	for (int i = 0; i < dimensions; i++) {
		ranges[i] = c.ranges[i];
	}
	
	defaultVal = c.defaultVal;
	//**cells = **c.cells;
	IdCounter++;
	cout << "create: " << name << endl;
}

SparseMatrix::~SparseMatrix() {
	delete[] ranges;
	for (int i = 0; i < dimensions; i++) {
		//delete[i] cells;
	}

	cout << "destroy: " << name << endl;
}


int SparseMatrix::getId() {
	return id;
}

void SparseMatrix::setName(string n) {
	name = n;
}

string SparseMatrix::getName() {
	return name;
}

void SparseMatrix::setDimensionsRanges(int dim, int *&ran) {
	dimensions = dim;
	ranges = new int[dimensions];
	cells = new SparseCell*[dimensions];
	for (int i = 0; i < dim; i++) {
		ranges[i] = ran[i];
		cells[i] = new SparseCell[ran[i]];
	}
	for (int i = 0; i < dim; i++) {
		for (int j = 0; j < ran[i]; j++) {
			SparseCell cell;
			cells[i][j] = cell;
			//cells[i]++;
		}
	}
	//if (**cells == 10) {
	//	cout << "NULL" << endl;
	//}
	for (int i = 0; i < dim; i++) {
		cells[i][0].showCoordinates();
	}
}

int SparseMatrix::addCell(SparseCell cell)
{
	return 0;
}

//void SparseMatrix::setDef(int *& coord, int val) {
//	for (int i = 0; i < dimensions; i++) {
//		for (int j = 0; j < ranges[i]; j++) {

//		}
//	}
//	cells[0] = new SparseCell(coord, val, dimensions);
//}

void SparseMatrix::printCells() {

}

void SparseMatrix::printRanges() {
	for (int i = 0; i < dimensions; i++) {
		cout << ranges[i];
		if(i != dimensions-1)
			cout << ", ";
	}
}

int *SparseMatrix::getRanges() {
	return ranges;
}

int SparseMatrix::getDimensions() {
	return dimensions;
}
void SparseMatrix::setDefVal(int defVal) {
	defaultVal = defVal;
}

int SparseMatrix::getDefVal() {
	return defaultVal;
}