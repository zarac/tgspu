// helloworld.cpp : Defines the entry point for the console application.
//

#include "stdafx.h"
#include <iostream>
#include "Pong.h"

using namespace std;
int _tmain(int argc, _TCHAR* argv[])
{
	//string poo = "";
	//int i;
	//cout << "hello world" << endl;
	//cin >> i;
	//cout << i << endl;
	Pong *pong = new Pong();
	pong->Init();
	pong->Play();
	system("pause");
	return 0;
}