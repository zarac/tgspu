using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;

namespace DeluxeAdventures
{
    interface XNAGamePart
    {
        void Draw(GameTime gameTime);
        void LoadContent();
        void Update(GameTime gameTime);
    }
}
