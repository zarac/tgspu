#pragma once
class Pong
{
	int cycle;
	int height;
	int width;
	bool playing;
	char *pixels;
	//char pixels[47][120];
public:
	Pong(void);
	~Pong(void);
	void Init(void);
	void Loop(void);
	void Play(void);
};

