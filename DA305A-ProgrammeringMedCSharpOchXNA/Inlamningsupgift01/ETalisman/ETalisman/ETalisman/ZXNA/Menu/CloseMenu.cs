using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ZXNA.Menu
{
    public class OptionCloseMenu : Option
    {
        public OptionCloseMenu(Menu menu)
            : this(menu, "Close Menu")
        {
        }
        public OptionCloseMenu(Menu menu, String label)
            : base(menu, label)
        {
        }

        public override void Action()
        {
            menu.close();
        }
    }
}
