using System;
using System.Collections.Generic;
using System.Linq;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Audio;
using Microsoft.Xna.Framework.Content;
using Microsoft.Xna.Framework.GamerServices;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using Microsoft.Xna.Framework.Media;
//using System;
//using System.Collections.Generic;
//using System.Linq;
//using System.Text;
//using Microsoft.Xna.Framework;
//using Microsoft.Xna.Framework.Graphics;

namespace TextLib
{
    public class Text
    {
        Color _color;
        SpriteFont _font;
        float _size;
        SpriteBatch _sprite;

        public Color Color
        {
            get
            {
                return _color;
            }
            set
            {
                _color = value;
            }
        }

        public float Size
        {
            get
            {
                return _size;
            }
            set
            {
                _size = value;
            }
        }

        public Text(SpriteBatch sprite, SpriteFont font)
        {
            _color = new Color();
            _font = font;
            _size = 1.0f;
            _sprite = sprite;
        }

        public void DrawText(int x, int y, String s)
        {
            _sprite.Begin();

            _sprite.DrawString(_font,
                s,
                new Vector2((float)x, (float)y),
                _color,
                0f,
                new Vector2(),
                _size,
                SpriteEffects.None,
                1f);

            _sprite.End();
        }
    }
}