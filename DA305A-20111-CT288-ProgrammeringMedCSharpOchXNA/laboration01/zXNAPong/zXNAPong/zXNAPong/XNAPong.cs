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

using GUI;
using ZLogger;

namespace zXNAPong
{
    /// <summary>
    /// This is the main type for your game
    /// </summary>
    public class XNAPong : Microsoft.Xna.Framework.Game
    {
        Logger log;
        GraphicsDeviceManager graphics;
        SpriteBatch spriteBatch;
        SpriteFont arial;
        Texture2D texture;
        Texture2D background;
        int marginTop;
        int paddingTop;
        int marginRight;
        int paddingRight;
        int marginBottom;
        int paddingBottom;
        int marginLeft;
        int paddingLeft;

        Text textTest;
        int testXPos = 0;
        int testYPos = 0;

        // The Game
        bool playing = false;
        List<Player> players;
        List<Ball> balls;
        float maxUp;
        float maxDown;
        float maxLeft;
        float maxRight;
        int paddleWidth;
        int paddleHeight;
        Texture2D redPaddle;
        Texture2D bluePaddle;
        Texture2D white1x1;
        Vector2 spritePosition = Vector2.Zero;
        Vector2 spriteSpeed = Vector2.One;

        // Controls
        private Keys startKey = Keys.Space;
        private Keys exitKey = Keys.Escape;
        private Keys resetKey = Keys.R;

        public XNAPong()
        {
            graphics = new GraphicsDeviceManager(this);
            graphics.PreferredBackBufferWidth = 1024;
            graphics.PreferredBackBufferHeight = 768;
            graphics.ApplyChanges();

            Content.RootDirectory = "Content";

            marginTop = 5;
            marginRight = 0;
            marginBottom = 5;
            marginLeft = 0;
            paddingTop = 0;
            paddingRight = 5;
            paddingBottom = 0;
            paddingLeft = 5;


            paddleWidth = 30;
            paddleHeight = 80;

            maxUp = marginTop + paddingTop;
            maxRight = graphics.PreferredBackBufferWidth - marginRight - paddingRight;
            maxDown = graphics.PreferredBackBufferHeight - marginBottom - paddingBottom;
            maxLeft = marginLeft + paddingLeft;

            Rectangle playingBoundary = new Rectangle(
                marginLeft + paddingLeft,
                marginTop + paddingTop,
                graphics.PreferredBackBufferWidth - marginLeft - paddingLeft - marginRight - paddingRight,
                graphics.PreferredBackBufferHeight - marginTop - paddingTop - marginBottom - paddingBottom
                );
        }

        /// <summary>
        /// Allows the game to perform any initialization it needs to before starting to run.
        /// This is where it can query for any required services and load any non-graphic
        /// related content.  Calling base.Initialize will enumerate through any components
        /// and initialize them as well.
        /// </summary>+
        protected override void Initialize()
        {
            // TODO: Add your initialization logic here
            this.IsMouseVisible = true;

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

            arial = Content.Load<SpriteFont>(@"Fonts/Arial");
            texture = Content.Load<Texture2D>(@"gfx/sprites");
            background = this.Content.Load<Texture2D>(@"gfx/background");

            white1x1 = Content.Load<Texture2D>(@"gfx/1x1white");
            redPaddle = Content.Load<Texture2D>(@"gfx/paddle_red");
            bluePaddle = Content.Load<Texture2D>(@"gfx/paddle_blue");

            textTest = new Text(spriteBatch, arial);
            log = new ZLogger.Logger(textTest, 50, 50);

            //texture = new Texture2D(GraphicsDevice, 10, 10);/

            // add two players, each with a paddle
            players = new List<Player>();
            players.Add(new Player(new Paddle(
                    PlayerIndex.One,
                    spriteBatch,
                    texture,
                    new Rectangle(
                        0,
                        0,
                        64,
                        128),
                    maxUp,
                    maxDown,
                    maxLeft,
                    paddleWidth,
                    paddleHeight,
                    Keys.A,
                    Keys.Z
                    )));

            players.Add(new Player(new Paddle(
                    PlayerIndex.One,
                    spriteBatch,
                    texture,
                    new Rectangle(
                        0,
                        0,
                        64,
                        128),
                    maxUp,
                    maxDown,
                    maxLeft,
                    paddleWidth,
                    paddleHeight,
                    Keys.D,
                    Keys.C
                    )));

            players.Add(new Player(new Paddle(
                PlayerIndex.Two,
                spriteBatch,
                texture,
                new Rectangle(
                    64,
                    0,
                    64,
                    128),
                maxUp,
                maxDown,
                maxRight - paddleWidth,
                paddleWidth,
                paddleHeight,
                Keys.K,
                Keys.M
                )));

            // add a ball
            balls = new List<Ball>();
            balls.Add(new Ball(
                spriteBatch,
                texture,
                maxUp,
                maxRight,
                maxDown,
                maxLeft,
                0.01f
                ));
            // and another ball
            balls.Add(new Ball(
                spriteBatch,
                texture,
                maxUp,
                maxRight,
                maxDown,
                maxLeft,
                0.01f
                ));
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
            // clear log
            log.Clear();

            // get states
            KeyboardState keyboard = Keyboard.GetState();
            GamePadState gamePadOne = GamePad.GetState(PlayerIndex.One);
            GamePadState gamePadTwo = GamePad.GetState(PlayerIndex.Two);

            // allows game to exit
            if (keyboard.IsKeyDown(exitKey)
                || gamePadOne.Buttons.Back == ButtonState.Pressed
                || gamePadTwo.Buttons.Back == ButtonState.Pressed
                )
                this.Exit();

            // enable start
            if (!playing)
                if (keyboard.IsKeyDown(startKey)
                    || gamePadOne.Buttons.A == ButtonState.Pressed
                    || gamePadTwo.Buttons.A == ButtonState.Pressed
                    )
                    StartGame();

            // enable reset
            if (keyboard.IsKeyDown(resetKey))
                ResetGame();

            // update paddles
            for (int i = 0; i < players.Count; i++)
                players[i].paddle.Update(gameTime);

            // update game
            if (playing)
            {
                for (int i = 0; i < balls.Count; i++)
                {
                    // player Left/One wins?
                    if (balls[i].Location.X > maxRight)
                    {
                        NewWinner(players[0]);
                        StopGame();
                    }
                    // player Right/Two wins?
                    if (balls[i].Location.X < maxLeft)
                    {
                        NewWinner(players[1]);
                        StopGame();
                    }

                    // collition with left paddle?
                    if (balls[i].GetBoundary().Intersects(players[0].paddle.GetBoundary()))
                    {
                        balls[i].TrajectoryX = -balls[i].TrajectoryX;
                        balls[i].trajectory.Y = (balls[i].MidY - players[0].paddle.MidY) * 0.01f;
                        players[0].paddle.Force = 100.0f;
                    }

                    // collition with right paddle?
                    if (balls[i].GetBoundary().Intersects(players[1].paddle.GetBoundary()))
                    {
                        balls[i].TrajectoryX = -balls[i].TrajectoryX;
                        balls[i].trajectory.Y = (balls[i].MidY - players[1].paddle.MidY) * balls[i].Acceleration;
                        players[1].paddle.Force = 100.0f;
                    }

                    // bounce top?
                    if (balls[i].Location.Y < maxUp)
                    {
                        balls[i].LocationY = maxUp;
                        balls[i].TrajectoryY = -balls[i].TrajectoryY;
                    }

                    // bounce bottom?
                    if (balls[i].Location.Y > maxDown)
                    {
                        balls[i].LocationY = maxDown;
                        balls[i].TrajectoryY = -balls[i].TrajectoryY;
                    }

                    balls[i].Update(gameTime);
                }
            }

            // Move the sprite by speed, scaled by elapsed time.
            spritePosition += spriteSpeed * (float)gameTime.ElapsedGameTime.TotalSeconds;

            int MaxX = graphics.GraphicsDevice.Viewport.Width - white1x1.Width;
            int MinX = 0;
            int MaxY = graphics.GraphicsDevice.Viewport.Height - white1x1.Height;
            int MinY = 0;

            // Check for bounce.
            if (spritePosition.X > MaxX)
            {
                spriteSpeed.X *= -1;
                spritePosition.X = MaxX;
            }

            else if (spritePosition.X < MinX)
            {
                spriteSpeed.X *= -1;
                spritePosition.X = MinX;
            }

            if (spritePosition.Y > MaxY)
            {
                spriteSpeed.Y *= -1;
                spritePosition.Y = MaxY;
            }

            else if (spritePosition.Y < MinY)
            {
                spriteSpeed.Y *= -1;
                spritePosition.Y = MinY;
            }

            base.Update(gameTime);
        }

        private void NewWinner(Player player)
        {
            player.Score += 1;
        }

        private void RestartGame()
        {
            ResetScores();
            ResetGame();
        }

        private void ResetGame()
        {
            StopGame();
            for (int i = 0; i < balls.Count; i++)
                balls[i].Reset();

            for (int i = 0; i < players.Count; i++)
                players[i].paddle.Reset();
        }

        private void ResetScores()
        {
            for (int i = 0; i < players.Count; i++)
                players[i].ResetScore();
        }

        private void StartGame()
        {
            ResetGame();
            playing = true;
        }

        private void StopGame()
        {
            playing = false;
        }


        /// <summary>
        /// This is called when the game should draw itself.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.CornflowerBlue);

            //// Draw background
            //spriteBatch.Begin(SpriteSortMode.Deferred, BlendState.AlphaBlend);
            //spriteBatch.Draw(background,
            //    new Rectangle(0, 0, 800, 600),
            //    Color.White);
            //spriteBatch.End();

            spriteBatch.Begin(SpriteSortMode.BackToFront, BlendState.AlphaBlend);
            // WUTFUX? Why doesn't using Vector2 and Rectangle update at the same time?
            //spriteBatch.Draw(white1x1, spritePosition, Color.Red);
            //spriteBatch.Draw(white1x1, new Rectangle((int)spritePosition.X + 50, (int)spritePosition.Y + 50, paddleWidth, paddleHeight), Color.Blue);
            spriteBatch.Draw(white1x1, new Rectangle((int)spritePosition.X, (int)spritePosition.Y, paddleWidth, paddleHeight), Color.Red);
            spriteBatch.End();

            // players
            for (int i = 0; i < players.Count; i++)
            {
                //log.Log("[PADDLE[" + i + 1 + "] X: " + players[i].paddle.X + ", Y: " + players[i].paddle.Y + " [SCORE] " + players[i].Score);
                players[i].paddle.Draw();
            }

            // balls
            for (int i = 0; i < balls.Count; i++)
            {
                //log.Log("[BALL LOC.] X: " + balls[i].location.X + ", Y: " + balls[i].location.Y + " [-TRAJ.] X: " + balls[i].trajectory.X + ", Y: " + balls[i].trajectory.Y);
                balls[i].Draw();
            }


            // DEBUGGING
            //textTest.Draw("[TIME] " + gameTime.ElapsedGameTime.Milliseconds, testXPos + 40, testYPos + 40);

            //log.Log("Elapsed Milliseconds : " + (float)gameTime.ElapsedGameTime.Milliseconds * 0.5f);

            log.Draw(gameTime);

            base.Draw(gameTime);
        }
    }
}
