using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;

using ETalisman.Character;

namespace ETalisman
{
    enum Step
    {
        NEW_CHARACTER = 0,
        MOVING = 1,
        FIGHTING = 2,
    }

    /// <summary>
    /// I guess this kinda controls the different stages of the game...
    /// </summary>
    public class Adventure : DrawableGameComponent
    {
        // the player(s?), kinda
        public Character.Character character;

        internal Creater creater;

        ETalisman eTalisman;

        Step step;


        public Adventure(ETalisman eTalisman) : base(eTalisman)
        {
            this.eTalisman = eTalisman;

            creater = new Creater(eTalisman);
        }

        public override void Draw(GameTime gameTime)
        {
            if (step == Step.NEW_CHARACTER)
                creater.Draw(gameTime);

            base.Draw(gameTime);
        }

        public override void Update(GameTime gameTime)
        {
            if (step == Step.NEW_CHARACTER)
                creater.Update(gameTime);
            
            base.Update(gameTime);
        }
    }
}
