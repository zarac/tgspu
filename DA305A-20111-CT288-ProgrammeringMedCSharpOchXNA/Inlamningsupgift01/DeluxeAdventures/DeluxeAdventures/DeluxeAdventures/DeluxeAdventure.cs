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

using ZLogger;
using GUI;

namespace DeluxeAdventures
{
    /// <summary>
    /// This is the main type for your game
    /// </summary>
    public class DeluxeAdventure : Microsoft.Xna.Framework.Game
    {
        public static GraphicsDeviceManager graphics;
        public static SpriteBatch spriteBatch;
        public static Logger logger;
        //SpriteBatch spriteBatch2;
        //Texture2D white1x1;
        //Menu men;u

        List<XNAGamePart> gameParts;

        public DeluxeAdventure()
        {
            graphics = new GraphicsDeviceManager(this);

            Content.RootDirectory = "Content";

            gameParts = new List<XNAGamePart>();

        }

        /// <summary>
        /// Allows the game to perform any initialization it needs to before starting to run.
        /// This is where it can query for any required services and load any non-graphic
        /// related content.  Calling base.Initialize will enumerate through any components
        /// and initialize them as well.
        /// </summary>
        protected override void Initialize()
        {
            spriteBatch = new SpriteBatch(GraphicsDevice);
            SpriteFont fontArial = Content.Load<SpriteFont>(@"fonts/Arial");

            logger = new Logger(new Text(spriteBatch, fontArial), 10, 10);
            logger.Log("testicle");

            Menu menu = new Menu(this, graphics);
            Button button = new Button(null, "hejsan", fontArial, 10, 10);
            menu.SetRoot(button);
            button.AddChild(new Button(null, "hoppsan", fontArial, 20, 20));

            gameParts.Add(menu);

            base.Initialize();
        }

        /// <summary>
        /// LoadContent will be called once per game and is the place to load
        /// all of your content.
        /// </summary>
        protected override void LoadContent()
        {
            foreach (XNAGamePart part in gameParts)
                part.LoadContent();
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
            KeyboardState keyboardState = Keyboard.GetState();
            GamePadState gamePadOneState = GamePad.GetState(PlayerIndex.One);
            GamePadState gamePadTwoState = GamePad.GetState(PlayerIndex.Two);

            // allow the game to exit
            if (gamePadOneState.Buttons.Back == ButtonState.Pressed
                || gamePadTwoState.Buttons.Back == ButtonState.Pressed
                || keyboardState.IsKeyDown(Keys.Escape))
                this.Exit();

            foreach (XNAGamePart part in gameParts)
                part.Update(gameTime);

            base.Update(gameTime);
        }

        /// <summary>
        /// This is called when the game should draw itself.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.CornflowerBlue);

            logger.Draw(gameTime);

            foreach (XNAGamePart part in gameParts)
                part.Draw(gameTime);

            base.Draw(gameTime);
        }
    }
}
