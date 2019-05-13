#include "Tools.h"

using namespace std;


bool isOperator2arg(string val)
{
	if (val == "+" || val == "-" || val == "*" || val == "/")
		return true;
	else
		return false;
}

bool isInt(string val)
{
	bool isNum = false;
	for (int i = 0; i < val.size(); i++) {

		if (isdigit(val[i])) {
			isNum = true;
		}
		else {
			return false;
		}
	}
	return isNum;
}

bool isDouble(string val)
{
	const char DIGIT = '.';
	bool isNum = false;
	for (int i = 0; i < val.size(); i++) {

		if (isdigit(val[i]) || val[i] == DIGIT) {
			isNum = true;
		}
		else {
			return false;
		}
	}
	return isNum;
}

bool isOperator3arg(string val)
{
	if (val == "++" || val == "--" || val == "**" || val == "//") {
		return true;
	}
	else
		return false;
}

bool isWord(string val)
{
	bool isWord = true;
	if (isOperator2arg(val) || isOperator3arg(val)) {
		isWord = false;
	}
	else {
		for (int i = 0; i < val.length(); i++) {
			if (val[i] < 'A' || val[i] > 'z') {
				isWord = false;
			}
		}
	}
		return isWord;
}
