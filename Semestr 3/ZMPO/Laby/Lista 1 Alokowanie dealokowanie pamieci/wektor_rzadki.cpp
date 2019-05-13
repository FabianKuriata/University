

#include "wektor_rzadki.h"

using namespace std;


const int VECTOR_LENGTH_DEFAULT = 100;
const int OFFSETS_VALUES_LENGTH_DEFAULT = 5;

int main() {
	bool working = true;
	int defaultValue = 0;
	int vectorLength = VECTOR_LENGTH_DEFAULT;
	int offValLength = OFFSETS_VALUES_LENGTH_DEFAULT;
	int *offsets = new int[offValLength];
	int *values = new int[offValLength];
	int occupiedOffsets = 0;
	int lastNumber = 0;

	int *wsk = new int[5];
	for (int i = 0; i < 10; i++) {
		wsk[i] = 10;
		cout << wsk[i] << endl;
	}

	char a[4];
	strcpy_s(a, "a string longer than 4 characters"); // write past end of buffer (buffer overflow)
	printf("%s\n", a[6]);

	while (working) {
		
		//working = consoleInterface(defaultValue, vectorLength, offsets, values, offValLength, occupiedOffsets, lastNumber);		
		
	}
	return 0;
}




