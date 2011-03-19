using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;

namespace ETalisman.Character
{
    public class Character : DrawableGameComponent
    {
        ETalisman eTalisman;

        public Stats stats;
        public String name;

        // TODO : ? Class should be static
        public Class class_;

        public Character(ETalisman eTalisman) : base(eTalisman)
        {
            this.eTalisman = eTalisman;

            stats = new Stats(eTalisman);
        }
    }
}
