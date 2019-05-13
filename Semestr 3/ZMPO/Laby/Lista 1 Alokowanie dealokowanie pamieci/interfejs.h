#pragma once
#include "wektor_rzadki.h"
#include "interfejs.cpp"
#include "funkcje_wektora.h"

using namespace std;

bool isNumeric(string word);
bool consoleInterface(int &defaultValue, int &vector, int *&offsets, int *&values, int &offValLength, int &occupiedOffsets, int &lastNumber);

