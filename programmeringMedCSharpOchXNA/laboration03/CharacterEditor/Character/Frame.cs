using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CharacterEditor.Character
{
    class Frame
    {
        Part[] parts;
        public String name;

        public Frame()
        {
            parts = new Part[16];
            for (int i = 0; i < parts.Length; i++)
            {
                parts[i] = new Part();
            }
            name = String.Empty;
        }

        public Part[] Parts
        {
            get
            {
                return Parts;
            }
        }
    }
}