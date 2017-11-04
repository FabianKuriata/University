#include "interfejs.h"
#include "wektor_rzadki.h"
#include "funkcje_wektora.h"

using namespace std;


bool isNumeric(string word) {

	for (int i = 0; i < word.size(); i++) {
		if (!isdigit(word[i])) {
			return false;
		}
	}
	return true;
}


bool consoleInterface(int &defaultValue, int &vectorLength, int *&offsets, int *&values, int &offValLength, int &occupiedOffsets, int &lastNumber) {

	string command;
	string partOfCommand;
	const vector <string> commandSeparated;
	istringstream ssCommand;
	getline(cin, command);
	ssCommand.str(command);

	while (getline(ssCommand, partOfCommand, ' ')) {  /// Dzielenie komendy na czesci
		//commandSeparated.push_back(partOfCommand);
	}

	////////////////////           INTERFEJS          /////////////////////////////////////////////////////////////////////////
	if (commandSeparated.size() > 3) {
		cout << "Podano za duzo parametrow" << endl << endl;
	}
	else {
		if (command.find("mvec") != string::npos) {

			if (commandSeparated[0] == "mvec") {      /////////////// TWORZY NOWY WEKTOR O DANEJ DLUGOSCI I DOMYSLNEJ WARTOSCI
				if (commandSeparated.size() == 3) {
					if (isNumeric(commandSeparated[1]) && isNumeric(commandSeparated[2])) {
						mvec(stoi(commandSeparated[1]), stoi(commandSeparated[2]), vectorLength, defaultValue, offsets, values, offValLength, occupiedOffsets);
					}
					else {
						cout << "Parametry musza byc liczbami" << endl;
					}
				}
				else {
					cout << "Nie podano wystarczajacej ilosci parametrow dla tej komendy" << endl;
				}

			}
			else {
				cout << "Czy chodzilo ci o komende:     mvec <len> <def>?" << endl;
			}
		}
		else if (command.find("len") != string::npos) {   ////////////////// ZMIENIA DLUGOSC WEKTORA
			if (commandSeparated.size() == 2) {
				if (commandSeparated[0] == "len") {
						
						if (isNumeric(commandSeparated[1])) {
							if (offValLength > 0) {
								len(stoi(commandSeparated[1]), vectorLength, offsets, values, occupiedOffsets, offValLength);
							}
							else {
								cout << "Wektor nie istenieje. Utworz nowy za pomoca komendy 'mvec <len> <def>'" << endl;
							}
						}
								
				}
			}
			else {
				cout << "Czy chodzilo ci o komende:     len <len>?" << endl;
			}
		}
		else if (command.find("defStart") != string::npos) {
			if (commandSeparated[0] == "defStart") {      /////////////// TWORZY NOWY WEKTOR O DANEJ DLUGOSCI I DOMYSLNEJ WARTOSCI
				if (commandSeparated.size() == 3) {
					if (isNumeric(commandSeparated[1]) && isNumeric(commandSeparated[2])) {
						if (offValLength > 0) {
							defStart(stoi(commandSeparated[1]), stoi(commandSeparated[2]), offsets, values, offValLength, defaultValue, vectorLength, occupiedOffsets, lastNumber);
						}
						else {
							cout << "Wektor nie istenieje. Utworz nowy za pomoca komendy 'mvec <len> <def>'";
						}
					}
					else {
						cout << "Parametry musza byc liczbami" << endl;
					}
				}
				else {
					cout << "Nie podano wystarczajacej ilosci parametrow dla tej komendy" << endl;
				}

			}
			else {
				cout << "Czy chodzilo ci o komende:     defStart <off> <val>?" << endl;
			}
		}
		else if (command.find("def") != string::npos) {
			if (commandSeparated[0] == "def") {      /////////////// TWORZY NOWY WEKTOR O DANEJ DLUGOSCI I DOMYSLNEJ WARTOSCI
				if (commandSeparated.size() == 3) {
					if (isNumeric(commandSeparated[1]) && isNumeric(commandSeparated[2])) {
						if (offValLength > 0) {
							def(stoi(commandSeparated[1]), stoi(commandSeparated[2]), offsets, values, offValLength, occupiedOffsets, defaultValue, vectorLength);
						}
						else {
							cout << "Wektor nie istenieje. Utworz nowy za pomoca komendy 'mvec <len> <def>'";
						}
						
					}
					else {
						cout << "Parametry musza byc liczbami" << endl;
					}
				}
				else {
					cout << "Nie podano wystarczajacej ilosci parametrow dla tej komendy" << endl;
				}

			}
			else {
				cout << "Czy chodzilo ci o komende:     def <off> <val>?" << endl;
			}
		}
		else if (command.find("print") != string::npos) {   ////////////////// WYPISUJE WEKTOR
			if (commandSeparated[0] == "print" && commandSeparated.size() < 3) {
				if (commandSeparated.size() == 2 && isNumeric(commandSeparated[1])) {

					print(vectorLength, defaultValue, offsets, values, occupiedOffsets, offValLength, stoi(commandSeparated[1]));
					cout << endl << endl;
				}
				else if(commandSeparated.size() == 1){
		
					print(vectorLength, defaultValue, offsets, values, occupiedOffsets, offValLength);
					cout << endl << endl;
				}
			
			}
			else {
				cout << "Czy chodzilo ci o komende:     print?" << endl;
			}
		}
		else if (command.find("del") != string::npos) {   ////////////////// DODAJE WARTOSC NA PODANA POZYCJE
			if (commandSeparated[0] == "del" && commandSeparated.size() == 1) {
				if (offValLength > 0) {
					del(offsets, values, offValLength);
				}
				else {
					cout << "Wektor nie istenieje. Utworz nowy za pomoca komendy 'mvec <len> <def>'" << endl;
				}
			}
			else {
				cout << "Niepoprawnie wpisana komenda <del>" << endl;
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
			return 0;
		}
		else {
			cout << "Bledna komenda" << endl;
		}
	}

	
	return 1;
}

