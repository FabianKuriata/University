#pragma once

#include "interfejs.h"
#include "wektor_rzadki.h"
#include "funkcje_wektora.cpp"

void mvec(int length, int value, int &vectorLength, int &defaultValue, int *&offsets, int *&values, int &offValLength, int &occupiedOffsets);
void len(int newLength, int &vectorLength, int *&offsets, int *&values, int &occupiedOffsets, int &offValLength);
void def(int offset, int value, int *&offsets, int *&values, int &offValLength, int &occupiedOffsets, int &defaultValue, int &vectorLength);
void print(int &vectorLength, int &defaultValue, int *&offsets, int *&values, int &occupiedOffsets, int &offValLength);
void print(int &vectorLength, int &defaultValue, int *&offsets, int *&values, int &occupiedOffsets, int &offValLength, int index);
void del(int *&offsets, int *&values, int &offValLength);
void defStart(int value, int number, int *&offsets, int *&values, int &offValLength, int &defaultValue, int &vectorLength, int &occupiedOffsets);
void reallocat(int *&offsets, int *&values, int &offValLength, int &lastNumber);