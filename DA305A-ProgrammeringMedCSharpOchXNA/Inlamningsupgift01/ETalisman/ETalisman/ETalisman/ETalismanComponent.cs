using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework;

namespace ETalisman
{
    abstract class ETalismanComponent
    {
        protected ETalisman eTalisman;
        protected SpriteBatch spriteBatch;

        public ETalismanComponent(ETalisman eTalisman)
        {
            this.eTalisman = eTalisman;
            spriteBatch = eTalisman.spriteBatch;
        }

        public abstract void Draw(GameTime gameTime);

        public abstract void Update(GameTime gameTime);
    }
}
