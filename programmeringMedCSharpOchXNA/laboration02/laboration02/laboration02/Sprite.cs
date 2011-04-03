using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace laboration02
{
    class Sprite
    {
        public Vector2 _position;
        Vector2 _size;
        SpriteBatch _spriteBatch;
        Rectangle _rectangle;
        Texture2D _texture;

        public Vector2 position
        {
            get
            {
                return _position;
            }
            set
            {
                _position = value;
            }
        }

        public float positionY
        {
            get
            {
                return _position.Y;
            }
            set
            {
                _position.Y = value;
            }
        }

        public float positionX
        {
            get
            {
                return _position.X;
            }
            set
            {
                _position.X = value;
            }
        }

        public Vector2 size
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

        public Texture2D texture
        {
            get
            {
                return _texture;
            }
            set
            {
                _texture = value;
            }
        }

        public void Draw()
        {
            _spriteBatch.Begin();
            _spriteBatch.Draw(_texture, _position, _rectangle, Color.White);
            _spriteBatch.End();
        }

        public Sprite(SpriteBatch spriteBatch, Texture2D texture, Rectangle rectangle, Vector2 position)
        {
            _size = new Vector2(rectangle.Width, rectangle.Height);
            _spriteBatch = spriteBatch;
            _texture = texture;
            _rectangle = rectangle;
            _position = position;
        }
    }
}
