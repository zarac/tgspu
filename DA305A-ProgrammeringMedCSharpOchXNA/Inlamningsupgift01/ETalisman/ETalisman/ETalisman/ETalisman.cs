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

using ETalisman.Pong;
using ETalisman.Menu;

namespace ETalisman
{
    /// <summary>
    /// This is the main type for your game
    /// </summary>
    public class ETalisman : Game
    {
        public GraphicsDeviceManager graphics;
        public SpriteBatch spriteBatch;

        public MainMenu mainMenu;
        public Adventure adventure;
        public Texture2D testTexture;

        // this is so we can change the previous state to current so we don't act twice on one input
        // TODO: there should be a better way to do this.
        public ZXNA.Input.Input input;

        // bonus :)
        Pong.Pong pong;

        public ETalisman()
        {
            graphics = new GraphicsDeviceManager(this);
            Content.RootDirectory = "Content";

            graphics.PreferredBackBufferWidth = 800;
            graphics.PreferredBackBufferHeight = 600;
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

        protected override void LoadContent()
        {
            spriteBatch = new SpriteBatch(GraphicsDevice);

            testTexture = Content.Load<Texture2D>(@"gfx/1x1");

            // input
            input = new ZXNA.Input.Input(this);

            // menu
            mainMenu = new MainMenu(this);
            mainMenu.open();
            mainMenu.DrawOrder = 1;
            Components.Add(mainMenu);

            // adventure
            adventure = new Adventure(this);
            Components.Add(adventure);

            // pong
            pong = new Pong.Pong(this);
            Components.Add(pong);

            //Services.AddService(typeof(SpriteBatch), spriteBatch);

            base.LoadContent();
        }

        protected override void UnloadContent()
        {
        }

        protected override void Update(GameTime gameTime)
        {
            input.Update();

            // TODO: ? prettier way to do this
            // pass input states to menu (this is so we can set last to current after acting on input (actually nm.. ))
            //mainMenu.setInputStates(keyboardState, lastKeyboardState);


            // allow the game to exit
            // || input.gamePadOneState.Buttons.Back == ButtonState.Pressed)
            if (input.keyboardState.IsKeyDown(Keys.Q))
                this.Exit();

            // open menu
            if (!mainMenu.Opened
                && input.keyboardState.IsKeyDown(Keys.Escape)
                && !input.lastKeyboardState.IsKeyDown(Keys.Escape))
            {
                mainMenu.open();
                // Clears previous state so we don't act on same key again (will close menu otherwise).
                input.lastKeyboardState = input.keyboardState;
            }

            base.Update(gameTime);
        }

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
