using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework;

namespace ETalisman.Character
{
    class Class
    {
        public Stats stats;
        public String name;
        public Texture2D texture;
        public Rectangle bounds;

        public Class(Stats stats, String name)
        {
            this.stats = stats;
            this.name = name;
        }
    }
}
