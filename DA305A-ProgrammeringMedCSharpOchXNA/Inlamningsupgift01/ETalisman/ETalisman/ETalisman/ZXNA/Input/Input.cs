using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Input;

namespace ZXNA.Input
{
    public class Input
    {
        Game game;

        public KeyboardState keyboardState, lastKeyboardState;
        //public GamePadState gamePadOneState;

        public Input(Game game)
        {
            this.game = game;
        }

        public void Update()
        {
            //gamePadOneState = GamePad.GetState(PlayerIndex.One);
            lastKeyboardState = keyboardState;
            keyboardState = Keyboard.GetState();
        }

        internal void Clear()
        {
            lastKeyboardState = keyboardState;
        }
    }
}