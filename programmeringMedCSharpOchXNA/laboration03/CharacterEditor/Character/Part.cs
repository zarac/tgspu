﻿using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;

namespace CharacterEditor.Character
{
    class Part
    {
        public Vector2 Location;
        public Vector2 Scaling;
        public float Rotation;
        public int Index;
        public int Flip;

        public Part()
        {
            Index = -1;
            Scaling = new Vector2(1.0f, 1.0f);
        }
    }
}
