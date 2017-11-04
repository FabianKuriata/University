#include "interfejs.h"
#include "main.h"


using namespace std;


bool isNumeric(string word) {

	for (int i = 0; i < word.size(); i++) {
		if (!isdigit(word[i])) {
			return false;
		}
	}
	return true;
}


bool consoleInterface(vector <SparseMatrix> &matrixes) {

	string command;
	string partOfCommand;
	vector <string> commandSeparated;
	istringstream ssCommand;
	getline(cin, command);
	ssCommand.str(command);

	while (getline(ssCommand, partOfCommand, ' ')) {  /// Dzielenie komendy na czesci
		commandSeparated.push_back(partOfCommand);
	}


	////////////////////           INTERFEJS          /////////////////////////////////////////////////////////////////////////

		//else if (command.find("del") != string::npos) {   ////////////////// DODAJE WARTOSC NA PODANA POZYCJE
			//if (commandSeparated[0] == "del" && commandSeparated.size() == 1) {
			//	if (offValLength > 0) {
			//		del(offsets, values, offValLength);
			//	}
				//else {
					//cout << "Wektor nie istenieje. Utworz nowy za pomoca komendy 'mvec <len> <def>'" << endl;
			//	}
			//}
			//else {
				//cout << "Niepoprawnie wpisana komenda <del>" << endl;
			//}
		//}
	if (command.find("addmat") != string::npos) {
		if (commandSeparated[0] == "addmat") {
			if (commandSeparated.size() > 3 && isNumeric(commandSeparated[1])) {
				int dimensions = stoi(commandSeparated[1]);
				if (commandSeparated.size() > dimensions + 4) {
					cout << "Za duzo danych" << endl;
				}
				else if (commandSeparated.size() == dimensions + 3 || commandSeparated.size() == dimensions + 4) {
					//Sprawdzenie czy wszystkie zakresy sa liczbami dodatnimi
					bool allRangesAreLegal = true;
					for (int i = 2; i < dimensions + 2; i++) {
						if (!isNumeric(commandSeparated[i]) || stoi(commandSeparated[i]) < 0) {
							allRangesAreLegal = false;
							i = dimensions + 2;
						}
					}

					if (allRangesAreLegal && isNumeric(commandSeparated[dimensions + 2])) {
						SparseMatrix matrix;
						if (commandSeparated.size() == dimensions + 4) {			// Jesli podano nazwe matrycy
							matrix.setName(commandSeparated.back());

						}
						else {
							//matrix = new SparseMatrix();
						}

						int *rangesOfDimensions = new int[dimensions];
						for (int i = 0; i < dimensions; i++) {
							rangesOfDimensions[i] = stoi(commandSeparated[2 + i]);
						}

						matrix.setDimensionsRanges(dimensions, rangesOfDimensions);
						//cout << "Z domyslna nazwa" << endl;
						matrix.setDefVal(stoi(commandSeparated[dimensions + 2]));

						matrixes.push_back(matrix);
						//	matrixes.at((*matrix).getId()).getDimensionsRanges();
					}
					else if (!allRangesAreLegal) {
						cout << "W zakresach podaj liczby dodatnie" << endl;
					}
					else {
						cout << "W wartosci domyslnej podaj liczbe" << endl;
					}
				}
				else {
					cout << "Za malo danych dla " << dimensions << " wymiarow" << endl;
				}
			}
			else {
				cout << "Tak nie dziala komenda addmat" << endl;
			}
		}
		else {
			cout << "Czy chodzilo ci o komende addmat <dimNum> <dim0Size> ... <dimNum-1Size> <def> <!name!>";
		}
	}
	else if (command.find("list") != string::npos) {
		if (commandSeparated[0] == "list") {
			if (!matrixes.empty()) {
				cout << matrixes.at(0).IdCounter << " matrixes" << endl;
				for (int i = 0; i < matrixes.size(); i++) {
					cout << "[" << matrixes.at(i).getId() << "] -" << '"' << matrixes.at(i).getName() << '"';
					cout << " size: [";
					matrixes.at(i).printRanges();
					cout << "]" << endl;
				}
			}
			else {
				cout << "Nie ma utworzonych zadnych matryc" << endl;
			}

		}
		else {
			cout << "Czy chodzilo ci o komende list ?" << endl;
		}
	}
	else if (command.find("del") != string::npos) {
		if (commandSeparated[0] == "delall") {
			matrixes.clear();
			cout << "Wyszyczono matryce" << endl;
		}

		if (commandSeparated[0] == "del") {

		}
	}
	else if (command.find("def") != string::npos) {
		if (commandSeparated[0] == "def") {
			bool allDimensionsAreLegal = true;
			for (int i = 2; i < commandSeparated.size(); i++) {
				if (!isNumeric(commandSeparated[i])) {
					allDimensionsAreLegal = false;
					i = commandSeparated.size();
				}
			}
			if (stoi(commandSeparated[1]) <= 0 || stoi(commandSeparated[1]) >= matrixes.size()) {
				allDimensionsAreLegal = false;
			}

			int dimensionsRequired = matrixes.at(stoi(commandSeparated[1])).getDimensions();
			if (allDimensionsAreLegal && commandSeparated.size() - 3 == dimensionsRequired) {
				int *coordinates = new int[dimensionsRequired];
				for (int i = 2; i < commandSeparated.size() - 1; i++) {
					coordinates[i] = stoi(commandSeparated[i]);
				}
				//matrixes.at(stoi(commandSeparated[1])).addCell(coordinates, stoi(commandSeparated.back()));
			}
			else {
				cout << "Podano zle dane" << endl;
			}
		}
	}
	else if (command.find("print") != string::npos) {
		if (commandSeparated[0] == "print") {
			if (commandSeparated.size() == 2 && isNumeric(commandSeparated[1])) {
				int offset = stoi(commandSeparated[1]);
				if (matrixes.size() >= offset + 1) {
					cout << matrixes.at(offset).getName() << endl;
					//cout << matrixes.at(offset).;
				}
				else {
					cout << "Nie ma matrycy o takim offsecie" << endl;
				}
			}
			else {
				cout << "Tak nie dziala komenda print" << endl;
				}
		}
	}
	else if (command.find("clone") != string::npos) {
		if (commandSeparated[0] == "clone") {
			if (commandSeparated.size() < 3) {
				if (isNumeric(commandSeparated[1])){
					if (matrixes.size() > stoi(commandSeparated[1])) {
						//SparseMatrix *pointer = new SparseMatrix();
						//pointer = &matrixes.at(stoi(commandSeparated[1]));
						//SparseMatrix *objectPointer = &;
						SparseMatrix clone(matrixes.at(stoi(commandSeparated[1])));
						//clone = &matrixes.at(stoi(commandSeparated[1]));
						//clone = new SparseMatrix(matrixes.at(stoi(commandSeparated[1])), 0);
						matrixes.push_back(clone);
					}
					else {
						cout << "Nie wektora o podanym offsecie" << endl;
					}
				}
				else {
					cout << "Podano bledne wartosci" << endl;
				}
			}
			else {
				cout << "Za duzo parametrow" << endl;
			}
		}
	}
	else if (command == "help") {
		cout << " mvec <len> <def> - utworzenie wektora o podanej dlugosci i domyslnej wartosci (usuwa isteniejacy wektor)" << endl;
		cout << " len <len> - zmiana dlugosci wektora rzadkiego" << endl;
		cout << " def <off> <val> - ustalenie wartosci dla offsetu wektora rzadkiego" << endl;
		cout << " print - wypisanie aktualnego stanu wektora rzadkiego" << endl;
		cout << " print <off> - wypisanie wartosci na okreslonej pozycji" << endl;
		cout << " del - usuwa wszystkie dynamicznie alokowane zmienne dla wektora rzadkiego" << endl;
		cout << " quit - wyjdz z programu" << endl;
	}
	else if (command == "quit") {
		return false;
	}
	else {
		cout << "Bledna komenda" << endl;
	}
	

	
	return true;
}

