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

using Microsoft.Xna.Framework.Storage;
using TextLib;
using CharacterEditor.Character;

namespace CharacterEditor
{
    /// <summary>
    /// This is the main type for your game
    /// </summary>
    public class Game1 : Microsoft.Xna.Framework.Game
    {
        GraphicsDeviceManager graphics;
        SpriteBatch spriteBatch;
        SpriteFont font;
        Text text;
        CharDef charDef;

        // For loading textures
        Texture2D nullTex;
        Texture2D iconsTex;
        // WUTFUX? Why create an array with lenght 1?
        Texture2D[] legsTex = new Texture2D[1];
        Texture2D[] torsoTex = new Texture2D[1];
        Texture2D[] headTex = new Texture2D[1];
        Texture2D[] weaponTex = new Texture2D[1];

        // Flipping characters right and left
        const int FACE_LEFT = 0;
        const int FACE_RIGHT = 1;

        // To be able to select different things (?)
        int selPart = 0;

        // States jao
        MouseState mouseState;
        MouseState preState;
        bool mouseClick;

        int selFrame;

        public Game1()
        {
            graphics = new GraphicsDeviceManager(this);
            Content.RootDirectory = "Content";
        }

        /// <summary>
        /// Allows the game to perform any initialization it needs to before starting to run.
        /// This is where it can query for any required services and load any non-graphic
        /// related content.  Calling base.Initialize will enumerate through any components
        /// and initialize them as well.
        /// </summary>
        protected override void Initialize()
        {
            // TODO: Add your initialization logic here

            base.Initialize();
        }

        /// <summary>
        /// LoadContent will be called once per game and is the place to load
        /// all of your content.
        /// </summary>
        protected override void LoadContent()
        {
            spriteBatch = new SpriteBatch(GraphicsDevice);
            font = Content.Load<SpriteFont>("Arial");
            text = new Text(spriteBatch, font);

            legsTex = new Texture2D[1];
            torsoTex = new Texture2D[1];
            headTex = new Texture2D[1];
            weaponTex = new Texture2D[1];

            LoadTextures(legsTex, @"gfx/legs");
            LoadTextures(torsoTex, @"gfx/torso");
            LoadTextures(headTex, @"gfx/head");
            LoadTextures(weaponTex, @"gfx/weapon");

            // Testar lite jao
            text.Color = Color.Red;
        }

        private void LoadTextures(Texture2D[] textures, string path)
        {
            for (int i = 0; i < textures.Length; i++)
                textures[i] = Content.Load<Texture2D>(path + (i + 1).ToString());
        }

        /// <summary>
        /// UnloadContent will be called once per game and is the place to unload
        /// all content.
        /// </summary>
        protected override void UnloadContent()
        {
            // TODO: Unload any non ContentManager content here
        }

        /// <summary>
        /// Allows the game to run logic such as updating the world,
        /// checking for collisions, gathering input, and playing audio.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Update(GameTime gameTime)
        {
            // Allows the game to exit
            if (GamePad.GetState(PlayerIndex.One).Buttons.Back == ButtonState.Pressed)
                this.Exit();

            mouseState = Mouse.GetState();

            if (mouseState.LeftButton == ButtonState.Pressed)
            {
                // :
            }
            else
            {
                if (preState.LeftButton == ButtonState.Pressed)
                    mouseClick = true;
            }

            preState = mouseState;

            base.Update(gameTime);
        }

        /// <summary>
        /// This is called when the game should draw itself.
        /// </summary>
        /// <param name="gameTime">Provides a snapshot of timing values.</param>
        protected override void Draw(GameTime gameTime)
        {
            GraphicsDevice.Clear(Color.CornflowerBlue);

            spriteBatch.Begin(SpriteSortMode.Deferred, BlendState.AlphaBlend);
            spriteBatch.Draw(nullTex, new Rectangle(590, 0, 300, 600), new Color(new Vector4(0.0f, 0.0f, 0.0f, 0.5f)));
            spriteBatch.End();
            // TODO: Add your drawing code here


            DrawCharacter (new Vector2(400f, 450f), 2f, FACE_RIGHT, selFrame, false, 1.0f);
            DrawPalette();
            DrawPartsList();
            DrawCursor();
            mouseClick = false;

            //test lolomfg
            text.DrawText(40, 60, "hejsan");

            base.Draw(gameTime);
        }

        private void DrawCharacter(
            Vector2 p_location,
            float p_scale,
            int p_face,
            int p_frameIndex,
            bool p_preview,
            float p_alpha)
        {
            Rectangle sRect = new Rectangle();
            Frame frame = charDef.Frames[p_frameIndex];
            spriteBatch.Begin(SpriteSortMode.Deferred, BlendState.AlphaBlend);

            // Part A
            for (int i = 0; i < frame.Parts.Length; i++)
            {
                Part part = frame.Parts[i];
                if (part.Index > -1)
                {
                    sRect.X = ((part.Index % 64) % 5) * 64;
                    sRect.Y = ((part.Index % 64) / 5) * 64;
                    sRect.Width = 64;
                    sRect.Height = 64;
                    if (part.Index >= 192)
                    {
                        sRect.X = ((part.Index % 64) % 3) * 80;
                        sRect.Width = 80;
                    }

                    float rotation = part.Rotation;

                    Vector2 location = part.Location * p_scale + p_location;
                    Vector2 scaling = part.Scaling * p_scale;
                    if (part.Index >= 128) scaling *= 1.35f;

                    if (p_face == FACE_LEFT)
                    {
                        rotation = -rotation;
                        location.X -= part.Location.X * p_scale * 2.0f;
                    }

                    // Part B
                    Texture2D texture = null;

                    int t = part.Index / 64;
                    switch (t)
                    {
                        case 0:
                            texture = headTex[charDef.headIndex];
                            break;
                        case 1:
                            texture = torsoTex[charDef.torsoIndex];
                            break;
                        case 2:
                            texture = legsTex[charDef.legsIndex];
                            break;
                        case 3:
                            texture = weaponTex[charDef.weaponIndex];
                            break;
                    }

                    // Part C
                    Color color = new Color(255, 255, 255, (byte)(p_alpha * 255));
                    if (!p_preview && selPart == i)
                        color = new Color(255, 0, 0, (byte)(p_alpha * 255));

                    bool flip = false;

                    if (texture != null)
                    {
                        spriteBatch.Draw(
                            texture,
                            location,
                            sRect,
                            color,
                            rotation,
                            new Vector2((float)sRect.Width / 2f, 32.0f),
                            scaling,
                            (flip ? SpriteEffects.None : SpriteEffects.FlipHorizontally),
                            1.0f);
                    }
                }
                spriteBatch.End();
            }

            DrawPalette();
            DrawCursor();
            mouseClick = false;
        }

        protected void DrawCursor()
        {
            spriteBatch.Begin(SpriteSortMode.Deferred, BlendState.AlphaBlend);

            spriteBatch.Draw(
                iconsTex, new Vector2(mouseState.X, mouseState.Y),
                new Rectangle(0, 0, 32, 32),
                Color.White,
                0.0f,
                new Vector2(0, 0),
                1.0f,
                SpriteEffects.None,
                0.0f);

            spriteBatch.End();
        }

        private bool DrawButton(
            int x,
            int y,
            int index,
            int mosX,
            int mosY,
            bool mouseClick)
        {
            bool returnValue = false;

            Rectangle sRect = new Rectangle(32 * (index % 8), 32 * (index / 8), 32, 32);
            Rectangle dRect = new Rectangle(x, y, 32, 32);

            if (dRect.Contains(mosX, mosY))
            {
                dRect.X -= 1;
                dRect.Y -= 1;
                dRect.Width += 2;
                dRect.Height += 2;

                if (mouseClick)
                    returnValue = true;
            }

            spriteBatch.Begin(SpriteSortMode.Deferred, BlendState.AlphaBlend);
            spriteBatch.Draw(iconsTex, dRect, sRect, Color.White);
            spriteBatch.End();

            return returnValue;
        }

        private void DrawPalette()
        {
            // PART 1
            spriteBatch.Begin(SpriteSortMode.Deferred, BlendState.AlphaBlend);

            for (int l = 0; l < 4; l++)
            {
                Texture2D texture = null;
                switch (l)
                {
                    case 0:
                        texture = headTex[charDef.headIndex];
                        break;
                    case 1:
                        texture = torsoTex[charDef.torsoIndex];
                        break;
                    case 2:
                        texture = legsTex[charDef.legsIndex];
                        break;
                    case 3:
                        texture = weaponTex[charDef.weaponIndex];
                        break;
                }

                if (texture != null)
                {
                    for (int i = 0; i < 25; i++)
                    {
                        Rectangle sRect = new Rectangle((i % 5) * 64, (i / 5) * 64, 64, 64);
                        Rectangle dRect = new Rectangle(i * 23, 467 + l * 32, 23, 32);
                        spriteBatch.Draw(nullTex, dRect, new Color(0, 0, 0, 25));

                        // PART 2
                        if (i == 3)
                        {
                            sRect.X = (i % 4) * 80;
                            sRect.Y = (i / 4) * 64;
                            sRect.Width = 80;

                            if (i < 15)
                            {
                                dRect.X = i * 30;
                                dRect.Width = 30;
                            }
                        }

                        // PART 3
                        spriteBatch.Draw(texture, dRect, sRect, Color.White);
                        if (dRect.Contains(mouseState.X, mouseState.Y))
                        {
                            if (mouseClick)
                            {
                                charDef.Frames[selFrame].Parts[selPart].Index = i + 64 * l;
                            }
                        }
                    }
                }
            }
            spriteBatch.End();
        }

        private void DrawPartsList()
        {
            for (int i = 0; i < charDef.Frames[selFrame].Parts.Length; i++)
            {
                int y = 5 + i * 15;
                text.Size = 0.75f;
                string line = "";
                int index = charDef.Frames[selFrame].Parts[i].Index;
                if (index < 0)
                    line = "";
                else if (index < 64)
                    line = "head" + index.ToString();
                else if (index < 74)
                    line = "torso" + index.ToString();
                else if (index < 128)
                    line = "arms" + index.ToString();
                else if (index < 192)
                    line = "legs" + index.ToString();
                else
                    line = "weapon" + index.ToString();

                if (selPart == i)
                {
                    text.Color = Color.Lime;
                    text.DrawText(600, y, i.ToString() + ": " + line);

                    // Lägger till knapper och använder funktionen SwapParts()
                    if (DrawButton(700, y, i, mouseState.X, mouseState.Y, mouseClick))
                    {
                        SwapParts(selPart, selPart - 1);
                        if (selPart > 0)
                            selPart--;
                    }
                    if (DrawButton(720, y, 2, mouseState.X, mouseState.Y, mouseClick))
                    {
                        SwapParts(selPart, selPart + 1);
                        if (selPart < charDef.Frames[selFrame].Parts.Length - 1)
                            selPart++;
                    }
                    // Spegling av delar n=normal och m=mirror
                    Part part = charDef.Frames[selFrame].Parts[selPart];
                    if (text.DrawClickText(
                        740,
                        y,
                        (part.Flip == 0 ? "(n)" : "(m)"),
                        mouseState.X,
                        mouseState.Y,
                        mouseClick))
                    {
                        part.Flip = 1 - part.Flip;
                    }

                    // Knapp för att återställa (reset)
                    if (text.DrawClickText(762, y, "(r)", mouseState.X, mouseState.Y, mouseClick))
                        part.Scaling = new Vector2(1.0f, 1.0f);

                    //  Knapp för att ta bort (delete)
                    if (text.DrawClickText(780, y, "(x)", mouseState.X, mouseState.Y, mouseClick))
                        part.Index = -1;
                }
                else
                {
                    if (text.DrawClickText(600, y, i.ToString() + ": " + line, mouseState.X, mouseState.Y, mouseClick))
                        selPart = i;
                }
            }
        }
        private void SwapParts(int index1, int index2)
        {
            if (index1 < 0 || index2 < 0 || index1 >= charDef.Frames[selFrame].Parts.Length || index2 >= charDef.Frames[selFrame].Parts.Length) 
                return;
            Part i = charDef.Frames[selFrame].Parts[index1];
            Part j = charDef.Frames[selFrame].Parts[index2];
            charDef.Frames[selFrame].Parts[index1] = j;
            charDef.Frames[selFrame].Parts[index2] = i;
        }
    }
}
