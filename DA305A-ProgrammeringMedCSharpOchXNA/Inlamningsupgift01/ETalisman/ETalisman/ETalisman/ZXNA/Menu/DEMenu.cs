using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace ETalisman.ZXNA.Menu
{
    class DEMenu
    {
        Game game;
        GraphicsDeviceManager graphics;
        SpriteBatch spriteBatch;

        public static Texture2D white1x1;

        //List<Node> nodes;

        public Node root;

        public DEMenu(Game game, GraphicsDeviceManager graphics)
        {
            this.game = game;
            this.graphics = graphics;
            //nodes = new List<Node>();

            spriteBatch = new SpriteBatch(game.GraphicsDevice);
        }

        public void SetRoot(Node root)
        {
            this.root = root;
        }

        public void Draw(GameTime gameTime)
        {
            // draw each menu part
            spriteBatch.Begin();
            
            if (root is Button)
                ((Button)root).DrawPart();
            else if (root is Node)
                root.DrawPart();
            else
                throw new UnsupportedMenuTypeException("vafan f0r typ jao?", null);

            spriteBatch.End();
        }

        public void LoadContent()
        {
            white1x1 = game.Content.Load<Texture2D>(@"gfx/white1x1");
        }

        public void Update(GameTime gameTime)
        {
        }
    }

    abstract public class Node
    {
        public Node parent;
        public List<Node> children;

        public Vector2 position;
        public Vector2 size;

        public Node(Node parent) : this(parent, Vector2.Zero, Vector2.Zero)
        //public Node(Node parent) : this(parent, null, null)
        {
        }

        public Node(Node parent, Vector2 position, Vector2 size)
        {
            this.parent = parent;
            children = new List<Node>();
        }

        public void AddChild(Node child)
        {
            children.Add(child);
        }

        public void DrawPart()
        {
            foreach (Node child in children)
            {
                if (child is Button)
                    ((Button)child).DrawPart();
                else if (child is Node)
                    child.DrawPart();
                else
                    throw new UnsupportedMenuTypeException("vafan f0r typ jao?", null);
            }
        }
    }

    //public class ParentNode : Node
    //{
    //}

    public class Button : Node
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

        public Button(Node parent, String text, SpriteFont font, int x, int y) : base(parent)
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

        new public void DrawPart()
        {
            DeluxeAdventure.spriteBatch.Draw(Menu.white1x1, new Rectangle(x, y, width, height), Color.White);
            DeluxeAdventure.spriteBatch.Draw(Menu.white1x1, new Rectangle(x + border, y + border, width - border * 2, height - border * 2), Color.CornflowerBlue);
            DeluxeAdventure.spriteBatch.DrawString(font, text, new Vector2(position.X + border, position.Y + border), color);

            base.DrawPart();
        }
    }

    public class UnsupportedMenuTypeException : Exception
    {
        public UnsupportedMenuTypeException(String reason, Exception inner)
            : base(reason, inner)
        {
        }
    }
}
