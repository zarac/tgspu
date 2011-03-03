using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.GamerServices;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Media;

using HannesXNA.GUI;

namespace HannesXNA.Logger
{
    public class Logger
    {
        Font text;
        int x, y;
        String log;

        public Logger(Font text, int x, int y)
        {
            this.text = text;
            this.x = x;
            this.y = y;
        }

        public void Log(String str)
        {
            log += str + "\n";
        }

        public void Draw(GameTime gameTime)
        {
            text.Draw(log, x, y);
        }

        public void Clear()
        {
            log = "";
        }
    }
}
