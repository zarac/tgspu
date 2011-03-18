using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ZXNA.Menu
{
    public abstract class Option
    {
        internal Menu menu;
        String label;

        public Option(Menu menu, String label)
        {
            this.menu = menu;
            this.label = label;
        }

        // TODO: ? rename to EnterNode() or something?
        public abstract void Action();

        public String GetLabel()
        {
            return label;
        }
    }
}
