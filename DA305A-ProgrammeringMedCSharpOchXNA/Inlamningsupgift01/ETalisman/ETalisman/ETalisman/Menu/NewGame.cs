using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using ZXNA.Menu;

namespace ETalisman.Menu
{
    class NewAdventure : Option
    {
        ETalisman eTalisman;

        public NewAdventure(ETalisman eTalisman, MainMenu menu)
            : base(menu, "New Adventure")
        {
            this.eTalisman = eTalisman;
        }

        public override void Action()
        {
            // TODO: Ask for confirmation etc..
            eTalisman.adventure.step = Adventure.Step.NEW_CHARACTER;
            menu.close();
        }
    }
}
