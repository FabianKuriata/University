
//#include <math.h>

#include "Interface.h"
#include "ForestExpressions.h"

using namespace std;

int main()
{
	srand(time(NULL));
	cout << "13" << endl;
	Tree* tree = new Tree();
	tree->createRandomTree();
	tree->printPrefix();
	cout << endl;
	Node *a = new Node();
	for (int i = 0; i < 10; i++) {
		tree->createRandomTree();
		tree->printPrefix();
		cout << endl;
		for (int j = 0; j < tree->getSize(); j++) {
			int iter = 0;
			tree->root->getNode(j, iter, false, a);
			cout << a->value << " ";
		}
		cout << endl << endl << endl << endl << "NEXT TREE" << endl << endl << endl;
		//cout << endl << "SZUKAM ELEMENTU NR: " << i << "  ";
		
	}
	cout << endl;
	const int quantityOfData = 7;
	const string fileNames[quantityOfData] = {"ZMPO_lista_4_00_punkt.txt", "ZMPO_lista_4_01_2_punkty.txt", "ZMPO_lista_4_02_3_punkty.txt", "ZMPO_lista_4_03_sin_x.txt", "ZMPO_lista_4_04_sin_x_y.txt", "ZMPO_lista_4_05_x_y_pl_x_x.txt", "ZMPO_lista_4_06_x_y_pl_x_x_noise.txt"};
	cout << "Wybierz dane, do ktorych ma byc znaleziona funkcja" << endl;
	for (int i = 0; i < quantityOfData; i++) {
		cout << i << ". " << fileNames[i] << endl;
	}
	
	cout << endl;

	string chosenFile;
	getline(cin, chosenFile);
	int chosen = stoi(chosenFile);

	string currentFile = fileNames[chosen];
	
	string sizeOfPop;
	string iterNum;
	string pCross;
	string pMut;
	cout << "Rozmiar populacji: ";
	getline(cin, sizeOfPop);
	cout << endl << "Liczba iteracji,pokolen: ";
	getline(cin, iterNum);
	cout << endl<< "Prawdopodobienstwo krzyzowania: ";
	getline(cin, pCross);
	cout << endl << "Prawdopodobienstwo mutacji: ";
	getline(cin, pMut);

	int sizeOfPopulation = stoi(sizeOfPop); // rozmiar populacji
	int iterNumber = stoi(iterNum); // liczba iteracji, pokolen
	int probCross = stoi(pCross); // prawdopodobienstwo krzyzowania
	int probMutation = stoi(pMut); // prawdopodobienstwo mutacji
	
	ForestExpressions forestOfExp(sizeOfPopulation, iterNumber, probCross, probMutation, currentFile);
	Tree* mostAccurateExp;
	mostAccurateExp = forestOfExp.findPropertyExpression();

	cout << "Znaleziona funkcja najbardziej zblizona do danych :";
	//mostAccurateExp->printPrefix();
	cout << endl;
	mostAccurateExp->printExpression();// TODO zmienne lacza sie z doublami z niewiadomych przyczyn
	cout << endl << endl;
	cout << "Poziom dostosowania do funkcji do danych (blizej 0 tym lepsza) : " << mostAccurateExp->levelOfAdaptation;
									   
	cout << endl << endl;
	
	getchar();

	return 0;
}

