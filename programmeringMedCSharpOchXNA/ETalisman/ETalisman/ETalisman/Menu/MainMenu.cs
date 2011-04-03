using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using ZXNA.Menu;

namespace ETalisman.Menu
{
    public class MainMenu : ZXNA.Menu.Menu
    {
        ETalisman eTalisman;

        SubMenu options;

        public MainMenu(ETalisman eTalisman)
            : base(eTalisman, eTalisman.input, eTalisman.spriteBatch, new Rectangle(0, 0, eTalisman.graphics.PreferredBackBufferWidth, eTalisman.graphics.PreferredBackBufferHeight))
        {
            this.eTalisman = eTalisman;

            root = new SubMenu(this, null, null);
            currentMenu = root;

            root.AddChild(new OptionCloseMenu(this, "Resume"));

            root.AddChild(new NewAdventure(eTalisman, this));

            options = new SubMenu(this, root, "Options");
            options.AddChild(new ExitGame(this, "exit again..", eTalisman));
            root.AddChild(options);

            root.AddChild(new ExitGame(this, eTalisman));
        }
    }
}
