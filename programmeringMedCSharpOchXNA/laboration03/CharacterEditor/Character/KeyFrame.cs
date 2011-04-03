using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CharacterEditor.Character
{
    class KeyFrame
    {
        int frameRef;
        int duration;
        String[] scripts;

        public KeyFrame()
        {
            frameRef = -1;
            duration = 0;
            scripts = new String[4];
            for (int i = 0; i < scripts.Length; i++)
            {
                scripts[i] = String.Empty;
            }
        }
    }
}