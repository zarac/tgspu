using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework;

namespace ETalisman
{
    class Map
    {
        private ETalisman eTalisman;

        Texture2D texture;

        public Map(ETalisman eTalisman)
        {
            this.eTalisman = eTalisman;

            texture = eTalisman.Content.Load<Texture2D>(@"gfx/1x1");
        }

        internal void Draw(GameTime gameTime)
        {
            eTalisman.spriteBatch.Begin();
            eTalisman.spriteBatch.Draw(texture, new Rectangle(100, 100, 640, 480), Color.Azure);
            eTalisman.spriteBatch.End();
        }

        internal void Update(GameTime gameTime)
        {
        }
    }
}
