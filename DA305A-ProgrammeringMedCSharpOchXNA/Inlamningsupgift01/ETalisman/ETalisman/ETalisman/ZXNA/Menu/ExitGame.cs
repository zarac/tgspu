using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;

namespace ZXNA.Menu
{
    public class ExitGame : Option
    {
        Game game;

        public ExitGame(Menu menu, Game game)
            : base(menu, "Exit")
        {
            this.game = game;
        }

        public ExitGame(ZXNA.Menu.Menu menu, String label, Game game)
            : base(menu, label)
        {
            this.game = game;
        }

        public override void Action()
        {
            game.Exit();
        }
    }
}
