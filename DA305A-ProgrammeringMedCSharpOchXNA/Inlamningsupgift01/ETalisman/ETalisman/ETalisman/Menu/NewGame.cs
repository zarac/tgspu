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
        Adventure adventure;

        public NewAdventure(ETalisman eTalisman, MainMenu menu)
            : base(menu, "New Adventure")
        {
            this.eTalisman = eTalisman;
            adventure = eTalisman.adventure;
        }

        public override void Action()
        {
            // TODO: Ask for confirmation etc..

            menu.close();
        }
    }
}
