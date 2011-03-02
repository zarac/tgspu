using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework;

namespace DeluxeAdventures.Character
{
    class Generator : XNAGamePart
    {
        SpriteBatch spriteBatch;

        public Generator(SpriteBatch spriteBatch)
        {
            this.spriteBatch = spriteBatch;
        }

        public void Draw(GameTime gameTime)
        {
            spriteBatch.Begin();
            //spriteBatch.Draw();
            spriteBatch.End();
        }

        public void LoadContent()
        {
        }

        public void Update(GameTime gameTime)
        {
        }
    }
}
