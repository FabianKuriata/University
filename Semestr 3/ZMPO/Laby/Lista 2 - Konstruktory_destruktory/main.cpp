
#include "main.h"

using namespace std;

int main() {
	bool running = true;
	//int **wsk;
	//wsk[0] = new int[10];
	//wsk[1] = new int[20];
	//wsk[2] = new int[30];
	
	//SparseMatrix matrix;
	//SparseCell cell;
	vector <SparseMatrix> matrixes;
	int *coo = new int[5];
	for (int i = 0; i < 5; i++) {
		coo[i] = i+3;
	}
	SparseCell cell(coo, 2, 5);
	cell.showCoordinates();
	//atrixes.push_back(matrix);
	cout << "pop232dwa" << endl;
	
	int **arr = new int*[5];
	for (int i = 0; i < 5; i++) {
		arr[i] = new int[5];
	}
	for (int i = 0; i < 5; i++) {
		for (int j = 0; j < 5; j++) {
			arr[i][j] = 10;
		}
	}
	//cout << matrixes.at(0).getName()<< endl << endl;
	while (running) {
		running = consoleInterface(matrixes);
	}
	
	return 0;
}