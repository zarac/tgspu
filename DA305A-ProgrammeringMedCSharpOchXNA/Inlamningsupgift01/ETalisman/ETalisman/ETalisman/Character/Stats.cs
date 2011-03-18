using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace ETalisman.Character
{
    class Stats : ETalismanComponent
    {
        // diablo ii style ; )
        public int health, mana;
        public int strength, dexterity, vitality, energy;

        public Stats(ETalisman eTalisman) : base(eTalisman)
        {
            health = 100;
            mana = 100;
            strength = 10;
            dexterity = 10;
            vitality = 10;
            energy = 10;
        }

        public override void Draw(GameTime gameTime)
        {
            spriteBatch.Begin();
            spriteBatch.End();
        }

        public override void Update(GameTime gameTime)
        {
            throw new NotImplementedException();
        }
    }
}
