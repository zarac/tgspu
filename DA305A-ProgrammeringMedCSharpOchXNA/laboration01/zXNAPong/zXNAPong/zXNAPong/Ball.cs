using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace zXNAPong
{
    class Ball
    {
        public Vector2 Location
        {
            get { return location; }
            set { location = value; }
        }

        public float LocationX
        {
            get { return location.X; }
            set { location.X = value; }
        }

        public float MidX
        {
            get { return location.X + width / 2; }
        }

        public float MidY
        {
            get { return location.Y + height / 2; }
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
        public float LocationY
        {
            get { return location.Y; }
            set { location.Y = value; }
        }

        public float DefaultLocationX
        {
            get { return defaultLocationX; }
            set { defaultLocationX = value; }
        }

        public float LocationDefaultY
        {
            get { return defaultLocationY; }
            set { defaultLocationY = value; }
        }
        
        public Vector2 Trajectory
        {
            get { return trajectory; }
            set { trajectory = value; }
        }

        public float TrajectoryX
        {
            get { return trajectory.X; }
            set { trajectory.X = value; }
        }

        public float TrajectoryY
        {
            get { return trajectory.Y; }
            set { trajectory.Y = value; }
        }

        public float Acceleration
        {
            get { return acceleration; }
            set { acceleration = value; }
        }

        float defaultLocationX;
        float defaultLocationY;
        float acceleration;
        public Vector2 location;
        public Vector2 trajectory;
        private int width;
        private int height;
        private SpriteBatch batch;
        private Texture2D texture;

        public Ball(SpriteBatch batch,
            Texture2D texture,
            float maxUp,
            float maxRight,
            float maxDown,
            float maxLeft,
            float acceleration)
        {
            this.batch = batch;
            this.texture = texture;

            width = 10;
            height = 10;
            this.acceleration = acceleration;
            defaultLocationX = maxLeft + (maxRight + maxLeft)/2 - width;
            defaultLocationY = maxUp + (maxUp + maxDown)/2 - height;
            //defaultLocationX = 400.0f;
            //defaultLocationY = 300.0f;

            Reset();
        }

        public void Draw()
        {
            batch.Begin(SpriteSortMode.Deferred, BlendState.AlphaBlend);
            //batch.Begin();

            Rectangle destRect = new Rectangle((int)location.X, (int)location.Y, width, height);
            batch.Draw(texture,
                new Rectangle((int)location.X - 16, (int)location.Y - 16, 32, 32),
                new Rectangle(128, 0, 64, 64),
                Color.White);

            batch.End();
        }

        public void Reset()
        {
            location.X = defaultLocationX;
            location.Y = defaultLocationY;
            trajectory.X = (0 - 0.5f) * -0.5f;
            trajectory.Y = (0 - 0.5f) * 0.5f;
        }

        public void Update(GameTime gameTime)
        {
            location += trajectory * (float)gameTime.ElapsedGameTime.Milliseconds;

            //if (location.X > maxDown
        }

        public Rectangle GetBoundary()
        {
            return new Rectangle((int)location.X, (int)location.Y, width, height);
        }
    }
}
