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

namespace laboration02
{
    /// <summary>
    /// This is the main type for your game
    /// </summary>
    public class Game1 : Microsoft.Xna.Framework.Game
    {
        GraphicsDeviceManager graphics;
        SpriteBatch spriteBatch;
        int moveSpeed = 5;
        Text arialTest;

        Sprite apple, dude, melon;

#if WINDOWS
        private bool leftMouseDown;
        private bool mouseClick;
#endif

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
            Texture2D dudes = Content.Load<Texture2D>(@"Sprites/sprite-sample");
            Texture2D fruits = Content.Load<Texture2D>(@"Sprites/uppgift2");
            Texture2D space = Content.Load<Texture2D>(@"Sprites/RockRain");

            apple = new Sprite(spriteBatch, fruits, new Rectangle(67, 35, 24, 30), new Vector2(20, 20));
            dude = new Sprite(spriteBatch, dudes, new Rectangle(262, 82, 64, 64), new Vector2(90, 20));
            melon = new Sprite(spriteBatch, fruits, new Rectangle(161, 34, 30, 27), new Vector2(170, 20));

            // TODO: use this.Content to load your game content here
            arialTest = new Text(spriteBatch, Content.Load<SpriteFont>(@"Fonts/Arial"));
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
            // Current keyboard state.
            KeyboardState keyboardState = Keyboard.GetState();

            // Allows the game to exit
            if (GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed)
                this.Exit();

            // Move ship
            if (keyboardState.IsKeyDown(Keys.Down))
                if (dude.positionY <= this.Window.ClientBounds.Height - dude.size.Y)
                    dude.positionY += moveSpeed;
            if (keyboardState.IsKeyDown(Keys.Up))
                if (dude.positionY >= 0)
                    dude.positionY -= moveSpeed;
            if (keyboardState.IsKeyDown(Keys.Left))
                if (dude.positionX >= 0)
                    dude.positionX -= moveSpeed;
            if (keyboardState.IsKeyDown(Keys.Right))
                if (dude.positionX <= this.Window.ClientBounds.Width - dude.size.X)
                    dude.positionX += moveSpeed;

            // Check mouse button states
#if WINDOWS
            MouseState mouseState = Mouse.GetState();
            if (mouseState.LeftButton == ButtonState.Pressed)
            {
                leftMouseDown = true;
                if (mouseState.LeftButton == ButtonState.Released)
                {
                    mouseClick = true;
                    leftMouseDown = false;
                }
            }
            else
            {
                leftMouseDown = false;
            }
#endif

            base.Update(gameTime);
        }

        /// <summary>
        /// This is called when the game should draw itself.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.CornflowerBlue);

            arialTest.size = 1.0f;
            arialTest.Color = new Color(200, 250, 200);
            arialTest.DrawText(50, 50, "Hej svejs");
            //arialTest.DrawText(50, 50, "Hej svejsåäö");
            apple.Draw();
            dude.Draw();
            melon.Draw();

            base.Draw(gameTime);
        }
    }
}
