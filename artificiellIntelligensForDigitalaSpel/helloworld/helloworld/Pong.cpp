#include "Pong.h"
#include <iostream>

Pong::Pong(void)
{
	cycle = 0;
	height = 47;
	width = 120; 
	playing = false;
	pixels = new char[height*width]; 
}

void Pong::Init(void)
{
	for (int h = 0; h < height; h++)
	{
		for (int w = 0; w < width; w++)
		{
			pixels[h*width+w] = ' ';
		}
	}
	pixels[3*width+10] = '|';
	pixels[4*width+10] = '|';
	pixels[5*width+10] = '|';
	pixels[6*width+10] = '|';
	pixels[(height/2)*width+(width/2)] = 'O';
	pixels[3*width+110] = '|';
	pixels[4*width+110] = '|';
	pixels[5*width+110] = '|';
	pixels[6*width+110] = '|';
}

void Pong::Play(void)
{
	pixels[4*width+5] = 0;
	playing = true;
	while (playing)
		Loop();
}

void Pong::Loop()
{
	cycle++;
	std::cout << "cycle: " << cycle << std::endl;
	for (int h = 0; h < height; h++)
	{
		for (int w = 0; w < width; w++)
		{
			std::cout << pixels[h*width+w];
		}
		//std::cout << std::endl;
	}
	if (cycle >= 1)
		playing = false;
}