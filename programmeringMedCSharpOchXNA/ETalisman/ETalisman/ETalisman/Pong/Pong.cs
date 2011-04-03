using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;

namespace ETalisman.Pong
{
    class Pong : DrawableGameComponent
    {
        ETalisman eTalisman;

        Rectangle bounds;

        Ball ball;
        Paddle paddleLeft;
        Paddle paddleRight;

        bool enabled;

        public Pong(ETalisman eTalisman)
            : base(eTalisman)
        {
            this.eTalisman = eTalisman;

            bounds = new Rectangle(10, 10, eTalisman.graphics.PreferredBackBufferWidth, eTalisman.graphics.PreferredBackBufferHeight);

            Disable();
            LoadContent();
        }

        protected override void LoadContent()
        {
            ball = new Ball(eTalisman, new Vector2(50, 50), new Vector2(5, 5));

            int paddleWidth = 30;
            int paddleHeight = 100;

            paddleLeft = new Paddle(eTalisman,
                bounds.X + 10,
                bounds.Y + 10,
                paddleWidth,
                paddleHeight,
                bounds.X - (paddleHeight / 2),
                bounds.Height - (paddleHeight / 2));
            paddleRight = new Paddle(eTalisman,
                bounds.Width - 10 - paddleWidth,
                bounds.Y + 10,
                paddleWidth,
                paddleHeight,
                bounds.X - (paddleHeight / 2),
                bounds.Height - (paddleHeight / 2));

            base.LoadContent();
        }

        public override void Draw(GameTime gameTime)
        {
            ball.Draw(gameTime);
            paddleLeft.Draw(gameTime);
            paddleRight.Draw(gameTime);

            base.Draw(gameTime);
        }

        public override void Update(GameTime gameTime)
        {
            ball.Update(gameTime);
            paddleLeft.Update(gameTime);
            paddleRight.Update(gameTime);

            base.Update(gameTime);
        }

        public bool Toggle()
        {
            if (enabled)
            {
                Disable();
                return enabled;
            }
            else
            {
                Enable();
                return enabled;
            }
        }

        public void Enable()
        {
            Enabled = true;
            Visible = true;
            enabled = true;
        }

        public void Disable()
        {
            Enabled = false;
            Visible = false;
            enabled = false;
        }
    }
}
