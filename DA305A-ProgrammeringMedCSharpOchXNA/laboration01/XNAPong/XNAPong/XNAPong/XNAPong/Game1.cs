using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.GamerServices;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Media;

namespace XNAPong
{
    /// <summary>
    /// This is the main type for your game
    /// </summary>
    public class Game1 : Microsoft.Xna.Framework.Game
    {
        GraphicsDeviceManager graphics;
        Texture2D spritesTexture;
        Texture2D backgroundTexture;
        SpriteBatch spriteBatch;

        float[] paddleLoc = new float[] { 300.0f, 300.0f };
        float[] force = new float[] { 0.0f, 0.0f };
        Vector2 ballLoc = new Vector2(400.0f, 300.0f);
        Vector2 ballTraj = new Vector2();
        bool playing = false;

        public Game1()
        {
            graphics = new GraphicsDeviceManager(this);
            Content.RootDirectory = "Content";
        }

        /// <summary>
        /// Allows the game to perform any initialization it needs to before starting to run.
        /// This is where it can query for any required services and load any non-graphic
        /// related content.  Calling base.Initialize will enumerate through any components
        /// and initialize them as well.
        /// </summary>
        protected override void Initialize()
        {
            // TODO: Add your initialization logic here

            base.Initialize();
        }

        /// <summary>
        /// LoadContent will be called once per game and is the place to load
        /// all of your content.
        /// </summary>
        protected override void LoadContent()
        {
            // Create a new SpriteBatch, which can be used to draw textures.
            spriteBatch = new SpriteBatch(GraphicsDevice);

            // TODO: use this.Content to load your game content here
            spritesTexture = this.Content.Load<Texture2D>(@"gfx/sprites");
            backgroundTexture = this.Content.Load<Texture2D>(@"gfx/background");
        }

        /// <summary>
        /// UnloadContent will be called once per game and is the place to unload
        /// all content.
        /// </summary>
        protected override void UnloadContent()
        {
            // TODO: Unload any non ContentManager content here
        }

        /// <summary>
        /// Allows the game to run logic such as updating the world,
        /// checking for collisions, gathering input, and playing audio.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Update(GameTime gameTime)
        {
            // Allows the game to exit
            if (GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed)
                this.Exit();

<<<<<<< Updated upstream
            // TODO: Add your update logic here
=======
            
>>>>>>> Stashed changes
            for (int i = 0; i < paddleLoc.Length; i++)
            {
                GamePadState state = GamePad.GetState((PlayerIndex)i);
                paddleLoc[i] -= state.ThumbSticks.Left.Y * (float)gameTime.ElapsedGameTime.Milliseconds * 0.5f;

                if (paddleLoc[i] < 100.0f) paddleLoc[i] = 100.0f;
                if (paddleLoc[i] > 500.0f) paddleLoc[i] = 500.0f;
                if (!playing)
                {
                    if (state.Buttons.A == ButtonState.Pressed)
                    {
                        playing = true;
                        ballLoc.X = 400.0f;
                        ballLoc.Y = 300.0f;
                        ballTraj.X = ((float)i - 0.5f) * -0.5f;
                        ballTraj.Y = ((float)i - 0.5f) * 0.5f;
                    }
                }

                if (force[i] > 0.0f)
                    force[i] -= (float)gameTime.ElapsedGameTime.Milliseconds;

                float t = (force[i] / 50.0f);
                if (t > 1.0f) t = 1.0f;
                if (t < 1.0f) t = 0.0f;
                GamePad.SetVibration((PlayerIndex)i, t, t);
            }
            if (playing)
            {
                float pX = ballLoc.X;
                ballLoc += ballTraj * (float)gameTime.ElapsedGameTime.Milliseconds;
                if (ballLoc.X > 800.0f) playing = false;
                if (ballLoc.X < 0.0f) playing = false;
                if (ballLoc.Y < 50.0f)
                {
                    ballLoc.Y = 550.0f;
                    ballTraj.Y = -ballTraj.Y;
                }
                if (ballLoc.X < 64.0f) TestBallCollision(0, (pX >= 64.0f));
                if (ballLoc.X > 736.0f) TestBallCollision(1, (pX <= 736.0f));
            }

            base.Update(gameTime);
        }

        /// <summary>
        /// This is called when the game should draw itself.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Draw(GameTime gameTime)
        {
            // graphics.GraphicDevis ?
            GraphicsDevice.Clear(Color.CornflowerBlue);

            spriteBatch.Begin(SpriteSortMode.Deferred, BlendState.AlphaBlend);

            spriteBatch.Draw(backgroundTexture,
                new Rectangle(0, 0, 800, 600),
                Color.White);

            for (int i = 0; i < paddleLoc.Length; i++)
            {
                Rectangle destRect = new Rectangle((i * 736), (int)paddleLoc[i] - 64, 64, 128);
                spriteBatch.Draw(spritesTexture,
                    destRect,
                    new Rectangle(i * 64, 0, 64, 128),
                    Color.White);
            }

            spriteBatch.Draw(spritesTexture,
                new Rectangle((int)ballLoc.X - 16, (int)ballLoc.Y - 16, 32, 32),
                new Rectangle(128, 0, 64, 64),
                Color.White);

            //spriteBatch.Draw(spritesTexture, new Rectangle(0, 0, 256, 256), Color.White);

            spriteBatch.End();

            base.Draw(gameTime);
        }

        private void TestBallCollision(int i, bool reverse)
        {
            if (ballLoc.Y < paddleLoc[i] + 64.0f && ballLoc.Y > paddleLoc[i] - 64.0f)
            {
                if (reverse) ballTraj.X = -ballTraj.X;
                
                ballTraj.Y = (ballLoc.Y - paddleLoc[i]) * 0.01f;
            }

            if (reverse)
                ballTraj.X = -ballTraj.X;

            ballTraj.Y = (ballLoc.Y - paddleLoc[i]) * 0.0f;
            force[i] = 100.0f;
        }
    }
}
