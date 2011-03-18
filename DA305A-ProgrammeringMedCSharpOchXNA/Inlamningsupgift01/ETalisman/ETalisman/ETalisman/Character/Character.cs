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

        Stats stats;

        public Character(ETalisman eTalisman) : base(eTalisman)
        {
            this.eTalisman = eTalisman;

            stats = new Stats(eTalisman);
        }

        public override void Draw(GameTime gameTime)
        {
            base.Draw(gameTime);
        }

        public override void Update(GameTime gameTime)
        {
            base.Update(gameTime);
        }
    }
}
