using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;

namespace ETalisman.Menu
{
    class MainMenu : DrawableGameComponent
    {
        private ETalisman eTalisman;
        private SpriteBatch spriteBatch;
        private SpriteFont spriteFont;
        private Texture2D choiceBackground;
        private Texture2D optionBackground;

        int x = 0;
        int y = 0;
        int width;
        int height;

        int margin = 10;
        int padding = 2;
        int buttonWidth = 300;
        int buttonHeight = 100;

        //List<Option> options;
        int selected;

        SubMenu currentMenu;

        public MainMenu(ETalisman eTalisman)
            : base(eTalisman)
        {
            this.eTalisman = eTalisman;
            spriteBatch = eTalisman.spriteBatch;
            //spriteBatch = (SpriteBatch)eTalisman.Services.GetService(typeof(SpriteBatch));
            choiceBackground = eTalisman.Content.Load<Texture2D>(@"gfx\menu_item_background-300x100");
            optionBackground = eTalisman.Content.Load<Texture2D>(@"gfx\menu_item_background2_selected-300x100");
            spriteFont = eTalisman.Content.Load<SpriteFont>(@"font\arial");

            width = eTalisman.graphics.PreferredBackBufferWidth;
            height = eTalisman.graphics.PreferredBackBufferHeight;

            SubMenu root = new SubMenu(this, null, null);
            currentMenu = root;
            
            root.AddChild(new CloseMenu(this, "Resume"));

            SubMenu options = new SubMenu(this, root, "Options");
            options.AddChild(new ExitETalisman(this, "exit again..", eTalisman));
            root.AddChild(options);

            root.AddChild(new ExitETalisman(this, "Exit", eTalisman));
        }

        // ??? Does this work? It didn't for me...
        //protected override void LoadContent()
        //{
        //    base.LoadContent();
        //}

        public override void Update(GameTime gameTime)
        {
            //Console.WriteLine("MainMenu.Update(...):");

            // Allow closing menu.
            // TODO: Use gameTime to not disable if we just enabled (when using same key)?
            if (eTalisman.keyboardState.IsKeyDown(Keys.Escape)
                && !eTalisman.lastKeyboardState.IsKeyDown(Keys.Escape))
            {
                Visible = false;
                Enabled = false;
            }

            if (eTalisman.keyboardState.IsKeyDown(Keys.Back)
                && !eTalisman.lastKeyboardState.IsKeyDown(Keys.Back)
                && currentMenu.GetParent() != null)
                currentMenu = currentMenu.GetParent();

            if (currentMenu.OptionCount > 0)
            {
                if (eTalisman.keyboardState.IsKeyDown(Keys.Up)
                    && !eTalisman.lastKeyboardState.IsKeyDown(Keys.Up))
                {
                    if (selected == 0)
                        selected = currentMenu.OptionCount - 1;
                    else
                        selected--;
                }

                if (eTalisman.keyboardState.IsKeyDown(Keys.Down)
                    && !eTalisman.lastKeyboardState.IsKeyDown(Keys.Down))
                {
                    if (selected == currentMenu.OptionCount - 1)
                        selected = 0;
                    else
                        selected++;
                }

                if (eTalisman.keyboardState.IsKeyDown(Keys.Enter)
                    && !eTalisman.lastKeyboardState.IsKeyDown(Keys.Enter))
                {
                    currentMenu.GetChildren()[selected].Action();
                    //switch ((Options)selected)
                    //{
                    //    case (Options.NEW_GAME):
                    //        Console.WriteLine("New game!");
                    //        break;
                    //    case (Options.OPTIONS):
                    //        Console.WriteLine("Options!");
                    //        break;
                    //    case (Options.EXIT):
                    //        Console.WriteLine("Exit!");
                    //        eTalisman.ExitGame();
                    //        break;
                    //    default:
                    //        break;
                    //}
                }
            }

            base.Update(gameTime);
        }

        public override void Draw(GameTime gameTime)
        {
            //Console.WriteLine("MainMenu.Draw(...):");

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
                    new Rectangle(width / 2 - buttonWidth / 2,
                        margin + padding * i + buttonHeight * i,
                        buttonWidth,
                        buttonHeight),
                    buttonColor);

                Vector2 stringSize = spriteFont.MeasureString(currentMenu.GetChildren()[i].GetLabel());
                spriteBatch.DrawString(spriteFont,
                    currentMenu.GetChildren()[i].GetLabel(),
                    new Vector2(width / 2 - stringSize.X/2,
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
    }

    abstract class Option
    {
        internal MainMenu menu;
        String label;
        
        public Option(MainMenu menu, String label)
        {
            this.menu = menu;
            this.label = label;
        }

        abstract public void Action();
        
        public String GetLabel()
        {
            return label;
        }
    }

    class ExitETalisman : Option
    {
        ETalisman eTalisman;

        public ExitETalisman(MainMenu menu, String label, ETalisman eTalisman)
            : base(menu, label)
        {
            this.eTalisman = eTalisman;
        }

        public override void Action()
        {
            eTalisman.ExitGame();
        }
    }

    class CloseMenu : Option
    {
        public CloseMenu(MainMenu menu, String label)
            : base(menu, label)
        {
        }

        public override void Action()
        {
            menu.Enabled = false;
            menu.Visible = false;
        }
    }

    class SubMenu : Option
    {
        MainMenu menu;
        SubMenu parent;
        List<Option> children;

        public SubMenu(MainMenu menu, SubMenu parent, String label)
            : base(menu, label)
        {
            this.menu = menu;
            this.parent = parent;

            children = new List<Option>();
        }

        public void AddChild(Option option)
        {
            children.Add(option);
        }

        public List<Option> GetChildren()
        {
            return children;
        }

        public override void Action()
        {
            menu.SetCurrentMenu(this);
        }

        public int OptionCount
        {
            get { return children.Count; }
        }

        public Option GetChild(int element)
        {
            return children[element];
        }

        public SubMenu GetParent()
        {
            return parent;
        }
    }
}
