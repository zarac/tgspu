using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework;

namespace ETalisman.Pong
{
    class Paddle
    {
        ETalisman eTalisman;
        SpriteBatch spriteBatch;

        Texture2D graphic;
        int positionX, positionY, width, height, top, bottom;


        public Paddle(ETalisman eTalisman, int positionX, int positionY, int width, int height, int top, int bottom)
        {
            this.eTalisman = eTalisman;
            spriteBatch = eTalisman.spriteBatch;

            this.positionX = positionX;
            this.positionY = positionY;
            this.width = width;
            this.height = height;
            this.top = top;
            this.bottom = bottom;

            graphic = eTalisman.Content.Load<Texture2D>(@"gfx/paddle");
        }

        public void Draw(GameTime gameTime)
        {
            spriteBatch.Begin();
            spriteBatch.Draw(graphic, new Rectangle((int)positionX, (int)positionY, width, height), Color.White);
            spriteBatch.End();
        }

        public void Update(GameTime gameTime)
        {
        }
    }
}
