using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;

namespace ETalisman.Pong
{
    class Ball
    {
        ETalisman eTalisman;
        public float positionX;
        public float positionY;
        public float velocityX;
        public float velocityY;

        int top, bottom, right, left, height, width;

        bool isMoving;

        Texture2D graphic;

        SpriteBatch spriteBatch;

        public Ball(ETalisman eTalisman, Vector2 position, Vector2 velocity)
        {
            this.eTalisman = eTalisman;
            spriteBatch = eTalisman.spriteBatch;
            
            this.positionX = position.X;
            this.positionY = position.Y;
            this.velocityX = velocity.X;
            this.velocityY = velocity.Y;

            top = 0;
            left = 0;
            right = eTalisman.graphics.PreferredBackBufferWidth;
            bottom = eTalisman.graphics.PreferredBackBufferHeight;

            LoadContent();
        }

        protected void LoadContent()
        {
            graphic = eTalisman.Content.Load<Texture2D>(@"gfx/ball");

            width = graphic.Width;
            height = graphic.Height;
        }

        public void Draw(GameTime gameTime)
        {
            eTalisman.spriteBatch.Begin();
            eTalisman.spriteBatch.Draw(graphic, new Rectangle((int)positionX, (int)positionY, 20, 20), Color.White);
            eTalisman.spriteBatch.End();
        }

        public void Update(GameTime gameTime)
        {
            if (eTalisman.input.keyboardState.IsKeyDown(Keys.S)
                && eTalisman.input.lastKeyboardState.IsKeyDown(Keys.S))
                isMoving = false;

            if (eTalisman.input.keyboardState.IsKeyDown(Keys.Space)
                && eTalisman.input.lastKeyboardState.IsKeyDown(Keys.Space))
                isMoving = true;

            if (isMoving)
            {
                // check bounds
                if (positionX <= left)
                    velocityX = -velocityX;
                if (positionY <= top)
                    velocityY = -velocityY;
                if (positionX >= right - width)
                    velocityX = -velocityX;
                if (positionY >= bottom - height)
                    velocityY = -velocityY;

                positionX += velocityX;
                positionY += velocityY;
            }
        }
    }
}
