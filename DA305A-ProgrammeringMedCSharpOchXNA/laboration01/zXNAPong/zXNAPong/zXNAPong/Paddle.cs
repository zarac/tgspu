using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;

namespace zXNAPong
{
    class Paddle
    {
        public float X
        {
            get { return x; }
            set { x = value; }
        }

        public float MidX
        {
            get { return x + width/2; }
        }

        public float Y
        {
            get { return y; }
            set { y = value; }
        }

        public float MidY
        {
            get { return y + height/2; }
        }

        public float DefaultX
        {
            get { return defaultX; }
            set { defaultX = value; }
        }

        public float DefaultY
        {
            get { return defaultY; }
            set { defaultY = value; }
        }

        public Keys UpKey
        {
            get { return upKey; }
            set { UpKey = value; }
        }

        public Keys DownKey
        {
            get { return downKey; }
            set { downKey = value; }
        }

        public int Width
        {
            get { return width; }
            set { width = value; }
        }

        public int Height
        {
            get { return height; }
            set { height = value; }
        }

        public float Force
        {
            get { return force; }
            set { force = value; }
        }

        //public Vector2 location;
        float x;
        float y;
        float defaultX;
        float defaultY;
        int width;
        int height;
        float speed;
        PlayerIndex player;
        SpriteBatch batch;
        Texture2D texture;
        Rectangle textureBounds;

        Keys upKey = Keys.A;
        Keys downKey = Keys.Z;

        float maxTop;
        float maxBottom;
        float force;

        public Paddle(
            PlayerIndex player,
            SpriteBatch batch,
            Texture2D texture,
            Rectangle textureBounds,
            float maxTop,
            float maxBottom,
            float x,
            int width,
            int height,
            Keys upKey,
            Keys downKey)
        {
            this.player = player;
            this.batch = batch;
            this.texture = texture;
            this.textureBounds = textureBounds;
            this.maxTop = maxTop;
            this.maxBottom = maxBottom;
            this.upKey = upKey;
            this.downKey = downKey;

            this.width = width;
            this.height = height;
            defaultX = x;
            defaultY = maxTop + (maxBottom - maxTop) / 2 - height;
            speed = 5.0f;

            Reset();
        }

        public void Draw()
        {
            batch.Begin(SpriteSortMode.Deferred, BlendState.AlphaBlend);
            //batch.Begin();

            Rectangle destRect = new Rectangle((int)x, (int)y, width, height);
            batch.Draw(texture,
                destRect,
                textureBounds,
                Color.White);

            batch.End();
        }

        public void Reset()
        {
            x = defaultX;
            y = defaultY;
        }

        public void Update(GameTime gameTime)
        {
            // Keyboard
            KeyboardState keyboardState = Keyboard.GetState();

            // Move up?
            if (keyboardState.IsKeyDown(upKey))
                y = (y - speed);

            // Move down?
            if (keyboardState.IsKeyDown(downKey))
                y += speed;

            // GamePad
            GamePadState state = GamePad.GetState(player);
            //    paddleLoc[i] -= state.ThumbSticks.Left.Y * (float)gameTime.ElapsedGameTime.Milliseconds * 0.5f;
            if (force > 0.0f)
                force -= (float)gameTime.ElapsedGameTime.Milliseconds;
            float t = force / 50.0f;
            if (t > 1.0f)
                t = 1.0f;
            if (t < 1.0f)
                t = 0.0f;
            GamePad.SetVibration(player, t, t);

            // Check if out of bounds up
            if (y < maxTop)
                y = maxTop;

            // Check if out of bounds down
            if (y + height > maxBottom)
                y = maxBottom - height;
        }

        public Rectangle GetBoundary()
        {
            return new Rectangle((int)X, (int)Y, width, height);
        }
    }
}
