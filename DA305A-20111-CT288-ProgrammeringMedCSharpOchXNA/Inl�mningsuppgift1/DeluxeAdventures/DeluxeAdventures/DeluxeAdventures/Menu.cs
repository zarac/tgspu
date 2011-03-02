using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace DeluxeAdventures
{
    class Menu : XNAGamePart
    {
        Game game;
        GraphicsDeviceManager graphics;
        static SpriteBatch spriteBatch;

        public static Texture2D white1x1;

        List<MenuPart> parts;

        public Menu(Game game, GraphicsDeviceManager graphics)
        {
            this.game = game;
            this.graphics = graphics;
            parts = new List<MenuPart>();

            spriteBatch = new SpriteBatch(game.GraphicsDevice);
        }

        public void AddPart(MenuPart part)
        {
            parts.Add(part);
        }

        public void Draw(GameTime gameTime)
        {
            // draw each menu part
            DeluxeAdventure.spriteBatch.Begin();

            foreach (MenuPart part in parts)
                part.DrawPart();

            DeluxeAdventure.spriteBatch.End();
        }

        public void LoadContent()
        {
            white1x1 = game.Content.Load<Texture2D>(@"gfx/white1x1");
        }

        public void Update(GameTime gameTime)
        {
        }
    }

    interface MenuPart
    {
        void DrawPart();
    }

    public class Button : MenuPart
    {
        Vector2 position;
        //Rectangle dimensions;
        String text;
        SpriteFont font;
        Color color;

        int x;
        int y;
        int width;
        int height;
        int textWidth;
        int textHeight;

        int border;

        public Button(String text, SpriteFont font, int x, int y)
        {
            border = 2;
            color = Color.Red;

            this.text = text;
            this.font = font;
            this.x = x;
            this.y = y;

            position = new Vector2(x, y);

            Vector2 fontDimesions = font.MeasureString(text);
            textWidth = (int)fontDimesions.X;
            textHeight = (int)fontDimesions.Y;

            width = textWidth + border * 2;
            height = textHeight + border * 2;

            //dimensions = new Rectangle(x, y, width, height);
        }

        public void DrawPart()
        {
            DeluxeAdventure.spriteBatch.Draw(Menu.white1x1, new Rectangle(x, y, width, height), Color.White);
            DeluxeAdventure.spriteBatch.Draw(Menu.white1x1, new Rectangle(x + border, y + border, width - border * 2, height - border * 2), Color.CornflowerBlue);
            DeluxeAdventure.spriteBatch.DrawString(font, text, new Vector2(position.X + border, position.Y + border), color);
        }
    }
}
