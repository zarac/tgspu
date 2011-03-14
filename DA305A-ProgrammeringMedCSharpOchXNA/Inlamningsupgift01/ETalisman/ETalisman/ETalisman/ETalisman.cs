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

using ETalisman.Menu;

namespace ETalisman
{
    /// <summary>
    /// This is the main type for your game
    /// </summary>
    public class ETalisman : Microsoft.Xna.Framework.Game
    {
        public GraphicsDeviceManager graphics;
        public SpriteBatch spriteBatch;
        public KeyboardState keyboardState;
        public KeyboardState lastKeyboardState;

        MainMenu mainMenu;

        public ETalisman()
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

            mainMenu = new MainMenu(this);
            //mainMenu.Enabled = false;
            //mainMenu.Visible = false;
            Components.Add(mainMenu);
            Services.AddService(typeof(SpriteBatch), spriteBatch);

            // ??? Does this work? It didn't for me...
            //base.LoadContent();
        }

        /// <summary>
        /// UnloadContent will be called once per game and is the place to unload
        /// all content.
        /// </summary>
        protected override void UnloadContent()
        {
        }

        /// <summary>
        /// Allows the game to run logic such as updating the world,
        /// checking for collisions, gathering input, and playing audio.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Update(GameTime gameTime)
        {
            // get input states
            GamePadState gamePadOneState = GamePad.GetState(PlayerIndex.One);
            lastKeyboardState = keyboardState;
            keyboardState = Keyboard.GetState();

            // allow the game to exit
            if (keyboardState.IsKeyDown(Keys.Q)
                || gamePadOneState.Buttons.Back == ButtonState.Pressed)
                this.Exit();

            // enable menu
            if (!mainMenu.Enabled
                && keyboardState.IsKeyDown(Keys.Escape)
                && !lastKeyboardState.IsKeyDown(Keys.Escape))
            {
                Console.WriteLine("eTalisman.Update(...):");
                mainMenu.Enabled = true;
                mainMenu.Visible = true;
            }

            base.Update(gameTime);
        }

        /// <summary>
        /// This is called when the game should draw itself.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.Black);

            base.Draw(gameTime);
        }

        public void ExitGame()
        {
            Exit();
        }
    }
}
