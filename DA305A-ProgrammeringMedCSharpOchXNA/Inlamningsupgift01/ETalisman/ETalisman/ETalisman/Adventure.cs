using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;

using ETalisman.Character;

namespace ETalisman
{
    /// <summary>
    /// I guess this kinda controls the different stages of the game...
    /// </summary>
    public class Adventure : DrawableGameComponent
    {
        // the player(s?), kinda
        public Character.Character character;

        internal Creater creater;
        Map map;

        ETalisman eTalisman;

        public Step step;
        public enum Step
        {
            NONE = 0,
            NEW_CHARACTER = 1,
            MAP = 2,
            FIGHT = 3,
        }


        public Adventure(ETalisman eTalisman) : base(eTalisman)
        {
            this.eTalisman = eTalisman;
            step = Step.NONE;

            creater = new Creater(eTalisman);
            map = new Map(eTalisman);
        }

        public override void Draw(GameTime gameTime)
        {
            if (step == Step.NEW_CHARACTER)
                creater.Draw(gameTime);

            if (step == Step.MAP)
                map.Draw(gameTime);

            base.Draw(gameTime);
        }

        public override void Update(GameTime gameTime)
        {
            if (step == Step.NEW_CHARACTER)
                creater.Update(gameTime);

            if (step == Step.MAP)
                map.Update(gameTime);
            
            base.Update(gameTime);
        }
    }
}
