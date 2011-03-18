using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;

namespace ZXNA.Menu
{
    public class Menu : DrawableGameComponent
    {
        public bool Opened { get; set; }

        protected Game game;
        protected SpriteBatch spriteBatch;
        protected SpriteFont spriteFont;
        protected Texture2D choiceBackground;
        protected Texture2D optionBackground;
        protected Rectangle bounds;
        //protected KeyboardState keyboardState, lastKeyboardState;

        protected SubMenu currentMenu;
        protected SubMenu root;
        protected Input.Input input;

        protected int margin = 10;
        protected int padding = 2;
        protected int buttonWidth = 300;
        protected int buttonHeight = 100;

        //List<Option> options;
        protected int selected;

        public Menu(Game game, ZXNA.Input.Input input, SpriteBatch spriteBatch, Rectangle bounds)
            : base(game)
        {
            this.game = game;
            this.input = input;
            this.spriteBatch = spriteBatch;
            this.bounds = bounds;
            Opened = true;

            //spriteBatch = (SpriteBatch)eTalisman.Services.GetService(typeof(SpriteBatch));
            choiceBackground = game.Content.Load<Texture2D>(@"gfx\menu_item_background-300x100");
            optionBackground = game.Content.Load<Texture2D>(@"gfx\menu_item_background2_selected-300x100");
            spriteFont = game.Content.Load<SpriteFont>(@"font\arial");
        }

        //public void setInputStates(KeyboardState keyboardState, KeyboardState lastKeyboardState)
        //{
        //    this.keyboardState = keyboardState;
        //    this.lastKeyboardState = lastKeyboardState;
        //}

        public override void Update(GameTime gameTime)
        {
            //Console.WriteLine("MainMenu.Update(...):");

            // Allow closing menu.
            // TODO: Use gameTime to not disable if we just enabled, instead of setting last state this current (when using same key)?
            if (input.keyboardState.IsKeyDown(Keys.Escape)
                && !input.lastKeyboardState.IsKeyDown(Keys.Escape))
            {
                close();
            }

            if (input.keyboardState.IsKeyDown(Keys.Back)
                && !input.lastKeyboardState.IsKeyDown(Keys.Back)
                && currentMenu.GetParent() != null)
                currentMenu = currentMenu.GetParent();

            // navigation
            if (currentMenu.OptionCount > 0)
            {
                // up / prev
                if (input.keyboardState.IsKeyDown(Keys.Up)
                    && !input.lastKeyboardState.IsKeyDown(Keys.Up))
                {
                    if (selected == 0)
                        selected = currentMenu.OptionCount - 1;
                    else
                        selected--;
                    input.Clear();
                }

                // down / next
                if (input.keyboardState.IsKeyDown(Keys.Down)
                    && !input.lastKeyboardState.IsKeyDown(Keys.Down))
                {
                    if (selected == currentMenu.OptionCount - 1)
                        selected = 0;
                    else
                        selected++;
                    input.Clear();
                }

                // select
                if (input.keyboardState.IsKeyDown(Keys.Enter)
                    && !input.lastKeyboardState.IsKeyDown(Keys.Enter))
                {
                    currentMenu.GetChildren()[selected].Action();
                    input.Clear();
                }
            }

            base.Update(gameTime);
        }

        public override void Draw(GameTime gameTime)
        {
            spriteBatch.Begin();

            for (int i = 0; i < currentMenu.OptionCount; i++)
            {
                Color buttonColor;
                if (selected == i)
                    buttonColor = new Color(200, 160, 160);
                else
                    buttonColor = Color.White;

                // center aligned
                spriteBatch.Draw(choiceBackground,
                    new Rectangle(bounds.Width / 2 - buttonWidth / 2,
                        margin + padding * i + buttonHeight * i,
                        buttonWidth,
                        buttonHeight),
                    buttonColor);

                Vector2 stringSize = spriteFont.MeasureString(currentMenu.GetChildren()[i].GetLabel());
                spriteBatch.DrawString(spriteFont,
                    currentMenu.GetChildren()[i].GetLabel(),
                    new Vector2(bounds.Width / 2 - stringSize.X/2,
                        margin + padding*i + buttonHeight * i + buttonHeight/2 - stringSize.Y/2),
                    new Color(3,33,163));
            }

            spriteBatch.End();

            base.Draw(gameTime);
        }

        internal void SetCurrentMenu(SubMenu subMenu)
        {
            currentMenu = subMenu;
            selected = 0;
        }

        public void close()
        {
            Enabled = false;
            Visible = false;
            Opened = false;
        }

        public void open()
        {
            Enabled = true;
            Visible = true;
            Opened = true;
        }
    }
}
