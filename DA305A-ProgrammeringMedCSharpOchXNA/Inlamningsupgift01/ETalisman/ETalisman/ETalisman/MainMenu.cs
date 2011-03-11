using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;

namespace ETalisman
{
    class MainMenu : DrawableGameComponent
    {
        private ETalisman eTalisman;
        private SpriteBatch spriteBatch;

        public MainMenu(ETalisman eTalisman)
            : base(eTalisman)
        {
            this.eTalisman = eTalisman;
            spriteBatch = (SpriteBatch)eTalisman.Services.GetService(typeof(SpriteBatch));
        }

        public override void Update(GameTime gameTime)
        {
            KeyboardState keyBoardState = Keyboard.GetState();
            Console.WriteLine("MainMenu.Update(...):");
            base.Update(gameTime);
        }

        public override void Draw(GameTime gameTime)
        {
            Console.WriteLine("MainMenu.Draw(...):");
            base.Draw(gameTime);
        }
    }

    class MenuItem
    {
    }
}
