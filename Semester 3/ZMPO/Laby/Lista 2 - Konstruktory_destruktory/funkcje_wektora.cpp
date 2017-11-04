
#include "wektor_rzadki.h"

using namespace std;

void del(int *&offsets, int *&values, int &offValLength) {

		delete[] offsets;
		delete[] values;
		offValLength = 0;
		cout << endl << "Usunieto wektor" << endl << endl;
}
void mvec(int length, int value, int &vectorLength, int &defaultValue, int *&offsets, int *&values, int &offValLength, int &occupiedOffsets) {
	if (offValLength > 0) {
 		del(offsets, values, offValLength);
	}

	offValLength = 5;
	offsets = new int[offValLength];
	values = new int[offValLength];
	
	occupiedOffsets = 0;
	vectorLength = length;
	defaultValue = value;
	cout << endl << "Utworzono wektor o dlugosci " << vectorLength << " i wartosci domyslnej " << defaultValue << endl << endl;
	
};

void len(int newLength, int &vectorLength, int *&offsets, int *&values, int &occupiedOffsets, int &offValLength) {

	if (newLength < vectorLength) {
		int toDelete = 0;
		for (int i = 0; i < occupiedOffsets; i++) {
			if (offsets[i] > newLength) {
				toDelete++;
			}
		}
		int *offsetsNew = new int[offValLength];
		int *valuesNew = new int[offValLength];

		for (int i = 0; i < (occupiedOffsets-toDelete); i++) {
			offsetsNew[i] = offsets[i];
			valuesNew[i] = values[i];
		}

		delete[] offsets;
		delete[] values;

		offsets = offsetsNew;
		values = valuesNew;
	}

	vectorLength = newLength;

}

void reallocat(int *&offsets, int *&values, int &offValLength) {
	int *offsetsNew = new int[offValLength * 2];
	int *valuesNew = new int[offValLength * 2];
	for (int i = 0; i < offValLength; i++) {
		offsetsNew[i] = offsets[i];
		valuesNew[i] = values[i];
	}
	offValLength *= 2;

	delete[] offsets;
	delete[] values;

	offsets = offsetsNew;
	values = valuesNew;
}

void def(int offset, int value, int *&offsets, int *&values, int &offValLength, int &occupiedOffsets, int &defaultValue, int &vectorLength) {

	if (offset < vectorLength) {
		bool isOffsetExist = false;
		for (int i = 0; i < occupiedOffsets; i++) {
			if (offsets[i] == offset) {
				isOffsetExist = true;
				values[i] = value;
				if (values[i] == defaultValue) {
					int *offsetsNew = new int[offValLength];
					int *valuesNew = new int[offValLength];
					for (int j = 0; j < occupiedOffsets-1; j++) {
						int k = j;
						if (offsets[i] == offsets[j]) {
							k++;
						}
						else {
							offsetsNew[j] = offsets[k];
							valuesNew[j] = values[k];
						}
					}
				}
			}
		}
		if (value != defaultValue && !isOffsetExist) {

			offsets[occupiedOffsets] = offset;
			values[occupiedOffsets] = value;

			occupiedOffsets++;

			int j, tempOff, tempVal;

			for (int i = 0; i < occupiedOffsets; i++) {
				j = i;

				while (j > 0 && offsets[j] < offsets[j - 1]) {
					tempOff = offsets[j];
					tempVal = values[j];
					offsets[j] = offsets[j - 1];
					values[j] = values[j - 1];
					offsets[j - 1] = tempOff;
					values[j - 1] = tempVal;
					j--;
				}
			}

			if (occupiedOffsets >= offValLength) {
				// realokacja
				reallocat(offsets, values, offValLength);
			}

			cout << endl << "Dodano " << value << " na pozycje " << offset << endl << endl;
		}
		else if (value == defaultValue && !isOffsetExist) {
			cout << "Podana wartosc jest wartoscia domyslna" << endl << endl;
		}
		else if( value == defaultValue){
			cout << "Usunieto z dynamicznej tablicy z racji podania domyslnej wartosc" << endl << endl;
			occupiedOffsets--;
		}
	}
	else {
		cout << "Zbyt wysoka wartosc pozycji" << endl << endl;
	}

}

///// wypisuje caly wektor
void print(int &vectorLength, int &defaultValue, int *&offsets, int *&values, int &occupiedOffsets, int &offValLength) {

	if (offValLength > 0) {
		bool isOffset = false;
		cout << "len: " << vectorLength << " values: ";
		int j = 0;
		for (int i = 0; i < vectorLength; i++) {
			if (occupiedOffsets > 0 && i == offsets[j]) {
				cout << values[j];
				j++;
			}
			else {
				cout << defaultValue;
			}

			if (i < vectorLength - 1) {
				cout << ", ";
			}
		}
	}
	else {
		cout << "Wektor nie istenieje. Utworz nowy za pomoca komendy 'mvec <len> <def>'" << endl << endl;
	}
}

//// Wypisuje okreslony index wektora
void print(int &vectorLength, int &defaultValue, int *&offsets, int *&values, int &occupiedOffsets, int &offValLength, int index) {
	if (offValLength > 0) {
		bool isDefault = true;
		for (int i = 0; i < occupiedOffsets; i++) {
			if (index == offsets[i]) {
				isDefault = false;
				cout << "Na pozycji " << index << " znajduje sie wartosc " << values[i] << endl;
			}
		}
		if (isDefault) {
			cout << "Na pozycji " << index << " znajduje sie wartosc " << defaultValue << endl;
		}
	}
	else {
		cout << "Wektor nie istenieje. Utworz nowy za pomoca komendy 'mvec <len> <def>'";
	}


}

void defStart(int value, int number, int *&offsets, int *&values, int &offValLength, int &defaultValue, int &vectorLength, int &occupiedOffsets, int &lastNumber) {

	if (value != defaultValue && number < vectorLength) {
		
		while (number > offValLength){
			reallocat(offsets, values, offValLength);
		}
		occupiedOffsets += (number-lastNumber);
		for (int i = 0; i < number; i++) {
			offsets[i] = i;
			values[i] = value;
		}

	}
	else if(value == defaultValue){
		cout << "Wprowadzono wartosc domyslna" << endl << endl;
	}
	else {
		cout << "Wektor jest za krotki" << endl;
	}
}

