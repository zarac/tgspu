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

namespace GUI
{
    public class Font
    {
        public float Size
        {
            get { return size; }
            set { size = value; }
        }

        float size;
        SpriteBatch batch;
        SpriteFont font;
        Color color;

        SpriteBatch spriteBatch;

        public Font(SpriteBatch p_batch, SpriteFont p_font)
        {
            batch = p_batch;
            font = p_font;

            size = 1.0f;
            color = new Color(0, 0, 0);
        }

        public void Draw(String text, int x, int y)
        {
            batch.Begin();

            batch.DrawString(
                font,
                text,
                new Vector2((float)x, (float)y),
                color,
                0f,
                new Vector2(),
                size,
                SpriteEffects.None,
                1f);

            batch.End();
        }
    }
}
