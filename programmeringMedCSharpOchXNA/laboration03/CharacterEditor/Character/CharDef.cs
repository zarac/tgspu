using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CharacterEditor.Character
{
    class CharDef
    {
        Animation[] animations;
        Frame[] frames;
        public String path;
        public int headIndex;
        public int torsoIndex;
        public int legsIndex;
        public int weaponIndex;

        public CharDef()
        {
            animations = new Animation[64];
            for (int i = 0; i < animations.Length; i++)
            {
                animations[i] = new Animation();
            }

            frames = new Frame[512];
            for (int i = 0; i < frames.Length; i++)
            {
                frames[i] = new Frame();
            }

            path = "character";
        }

        public Animation[] Animations
        {
            get
            {
                return animations;
            }
            set
            {
                animations = value;
            }
        }

        public Frame[] Frames
        {
            get
            {
                return frames;
            }
            set
            {
                frames = value;
            }
        }
    }
}
