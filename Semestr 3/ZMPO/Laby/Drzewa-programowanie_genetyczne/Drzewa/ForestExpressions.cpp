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

	//inicjalizacja - stworzneie 'sizeOfPopulation' losowych drzew
	for (int i = 0; i < sizeOfPopulation; i++) {
		Tree* newTree;
		newTree = new Tree();
		newTree->createRandomTree();
		forestOfExp.push_back(newTree);
		//forestOfExp[i]->printInfix();
		//cout << endl;
		//cout<<forestOfExp[i]->variables.size();
		//cout << forestOfExp[i]->getSize();
	}
	evaluation();
}

ForestExpressions::~ForestExpressions()
{
}

Tree* ForestExpressions::findPropertyExpression()
{
	Tree* mostAccurate = new Tree();  // drzewo bliskie idealnemu

	//szukanie
	int randomValue = 0;
	for (int i = 0; i < iterNumber; i++) {
		selection();
		randomValue = rand() % 100;
		cout << randomValue << endl;
		// Krzyzowanie
		if (probCross > randomValue) {
			int ranTree1 = 0; 
			int ranTree2 = 0;
			for (int t = 0; t < forestOfExp.size(); t++) {
				ranTree1 = rand() % sizeOfPopulation;
				do {
					ranTree2 = rand() % sizeOfPopulation;
				} while (ranTree1 == ranTree2);

				//forestOfExp[ranTree1]->crossBreed(forestOfExp[ranTree2]);  TODODODODO  
			}
			
		}
		
		randomValue = rand() % 100;
		// Mutacja do zrobienia druga mutacja
		if (probMutation > randomValue) {
			int ranTree1 = 0;

			for (int t = 0; t < forestOfExp.size(); t++) {
				ranTree1 = rand() % sizeOfPopulation;

				forestOfExp[ranTree1]->performMutation(); 
			}

		}
		evaluation();
	}
	//szukanie najlepszego kandydata do bycia wyrazeniem pod wprowadzone dane
	if (!forestOfExp.empty()) {
		mostAccurate = forestOfExp[0];
		for (int t = 0; t < forestOfExp.size(); t++) {
			if (forestOfExp[t]->levelOfAdaptation < mostAccurate->levelOfAdaptation && forestOfExp[t]->levelOfAdaptation >= 0 || mostAccurate->levelOfAdaptation == -1)
				mostAccurate = forestOfExp[t];
		}
	}
	
	return mostAccurate;
}


void ForestExpressions::evaluation()
{
	ifstream varsAndResult(pathOfFile);
	for (int i = 0; i < sizeOfPopulation; i++) {
		varsAndResult.clear();
		varsAndResult.seekg(0, ios::beg);

		//forestOfExp[i]->printPrefix();
		//cout << endl;
		//cout << "rozmiar zmienny:"<<forestOfExp[i]->variables.size() << endl;
		forestOfExp[i]->levelOfAdaptation = 0;
		string line;
		vector<string> vars;
		while (getline(varsAndResult, line)) {
			// 'split' pociecie linii pliku na elementy
			replace(line.begin(), line.end(), ';', ' ');
			stringstream str(line);
			vector<string> data;
			string val;
			while (str >> val)
				data.push_back(val);

			for (int p = 0; p < data.size(); p++) {
				//cout << "Dane: " << data[p] << ", ";
			}

			for (int m = 0; m < (forestOfExp[i]->variables.size()); m++) {
				vars.push_back(data[m]);
			}
			vector<double> resultFromTree = forestOfExp[i]->compute(vars); 
			double resultFromFile = stod(data[2]);
			if (resultFromTree[1] == 0)
				//cout << "Wynik :" << resultFromTree[0] << endl;

			for (int v = 0; v < forestOfExp[i]->variables.size(); v++) {
				//cout << "Zmienna: " << forestOfExp[i]->variables[v] << endl;
			}
			if (resultFromTree[1] == 0)
				forestOfExp[i]->countAdaptation(resultFromFile, resultFromTree[0]); // drzewo poprawne
			else
				forestOfExp[i]->levelOfAdaptation = -1; // gdy w drzewie sa niepoprawane operacje - chore drzewo
			vars.clear();
			//cout << endl;
		}
		//cout << endl;
	}
}

void ForestExpressions::selection()
{
	vector<Tree*> forestToSelection = forestOfExp;

	for (int i = 0; i < sizeOfPopulation; i++) {
		int ranTree1 = rand() % sizeOfPopulation;
		int ranTree2 = rand() % sizeOfPopulation;
		if (forestToSelection[ranTree1]->levelOfAdaptation < forestToSelection[ranTree2]->levelOfAdaptation && forestOfExp[ranTree1]->levelOfAdaptation != -1) {
			
			Tree* newTree = new Tree(*forestOfExp[ranTree1]);
			
			*forestOfExp[ranTree2] = *newTree; // mozliwy wyciek
		}
		else if (forestToSelection[ranTree2]->levelOfAdaptation != -1) {
			Tree* newTree = new Tree(*forestOfExp[ranTree2]);
			*forestOfExp[ranTree1] = *newTree;
		} // mozliwy wyciek
	}
	//cout << "Selekcja" << endl;
	for (int i = 0; i < sizeOfPopulation; i++) {
		//forestToSelection[i]->printPrefix();
		//cout << "  =====>>>>>> ";
		//forestOfExp[i]->printPrefix();
		//cout << endl << endl;
	}
}
