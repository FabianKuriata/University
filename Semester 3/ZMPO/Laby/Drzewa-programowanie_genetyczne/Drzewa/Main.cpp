
//#include <math.h>
#include "Interface.h"
#include "ForestExpressions.h"

using namespace std;

int main()
{
	srand(time(NULL));
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
	ifstream infile(currentFile);
	

	//string line;
	//while (getline(infile, line))
	//{
		//istringstream iss(line);	
		//cout << line << endl;
	//}

	int sizeOfPopulation = 100;
	int iterNumber = 50;
	int probCross = 100; // prawdopodobienstwo krzyzowania
	int probMutation = 100; // prawdopodobienstwo mutacji
	
	ForestExpressions forest(sizeOfPopulation, iterNumber, probCross, probMutation, currentFile);
	forest.findPropertyExpression();
	//Interface *interface = new Interface();
	//(*interface).run();
	//delete interface;
	cout << endl << endl;
	
	getchar();

	return 0;
}

