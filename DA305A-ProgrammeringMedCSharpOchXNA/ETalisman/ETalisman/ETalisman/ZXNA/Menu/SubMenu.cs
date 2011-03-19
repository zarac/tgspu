using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace ZXNA.Menu
{
    public class SubMenu : Option
    {
        SubMenu parent;
        List<Option> children;

        public SubMenu(Menu menu, SubMenu parent, String label)
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
