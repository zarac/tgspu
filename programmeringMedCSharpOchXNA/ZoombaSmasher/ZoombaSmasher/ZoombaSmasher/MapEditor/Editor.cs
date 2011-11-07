using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;
using System.IO;

namespace ZoombaSmasher.MapEditor
{
    // TODO:
    //  list available maps
    //      load selected map
    //  save file
    class Editor
        : DrawableGameComponent
    {
        // settings
        String
            mapDir,
            basePath = "maps",
            extension = "zoomba";
        // TODO : use sensible units (as in seconds or so)
        float backspaceDelayInitial = 200.0f;
        float backspaceFrequency = 50.0f;

        // dependencies
        ZoombaSmasher zoomba;

        // dimensions 
        short rows, columns;
        MapSquare[,] squares;
        Texture2D white1x1;
        int windowWidth, windowHeight,
            squareWidth, squareHeight;

        // input
        Cursor cursor;
        Actions actions;
        MouseState mouse, mouseLast;
        KeyboardState keyboard, keyboardLast;
        float repeatDelay;
        GamePadState gamePad, gamePadLast;
        Keys[] validInput;

        // tools
        int numTools = 2;
        int currentTool;
        Tool[] tools;

        Color gridColor = new Color(100, 100, 100);

        Saver saver;
        Loader loader;
        MapFile[] availableMaps;

        public Editor(ZoombaSmasher zoomba)
            : base(zoomba)
        {
            this.zoomba = zoomba;
            mapDir = Path.Combine(zoomba.Content.RootDirectory, basePath);
            rows = 30;
            columns = 40;
            squares = new MapSquare[rows,columns];
            windowWidth = zoomba.graphics.PreferredBackBufferWidth;
            windowHeight = zoomba.graphics.PreferredBackBufferHeight;
            squareWidth = windowWidth / columns;
            squareHeight = windowHeight / rows;
            saver = new Saver(this);
            saver.Bounds = new Rectangle(windowWidth / 4, windowHeight / 4,
                windowWidth / 2, windowHeight / 2);
            saver.Text = "";
            cursor = new Cursor(zoomba, windowWidth / 2, windowHeight / 2);
            actions = new Actions();
            loader = new Loader(this);

            SetValidNameCharacters();
        }

        protected void InitTools()
        {
            numTools = Enum.GetNames(typeof(MapSquare)).Length;
            tools = new Tool[numTools];
            currentTool = 0;
            tools[(int)MapSquare.SPAWNPLAYER1] = new Tool(zoomba.Content.Load<Texture2D>("gfx/squares/floor1"));
            tools[(int)MapSquare.SPAWNPLAYER2] = new Tool(zoomba.Content.Load<Texture2D>("gfx/squares/floor2"));
            tools[(int)MapSquare.SPAWNPLAYER3] = new Tool(zoomba.Content.Load<Texture2D>("gfx/squares/floor3"));
            tools[(int)MapSquare.SPAWNPLAYER4] = new Tool(zoomba.Content.Load<Texture2D>("gfx/squares/floor4"));
            tools[(int)MapSquare.SPAWNMONSTER1] = new Tool(zoomba.Content.Load<Texture2D>("gfx/squares/floor1"));
            tools[(int)MapSquare.SPAWNMONSTER2] = new Tool(zoomba.Content.Load<Texture2D>("gfx/squares/floor2"));
            tools[(int)MapSquare.SPAWNMONSTER3] = new Tool(zoomba.Content.Load<Texture2D>("gfx/squares/floor3"));
            tools[(int)MapSquare.SPAWNMONSTER4] = new Tool(zoomba.Content.Load<Texture2D>("gfx/squares/floor4"));
            tools[(int)MapSquare.WALLTYPE1] = new Tool(zoomba.Content.Load<Texture2D>("gfx/squares/floor1"));
            tools[(int)MapSquare.WALLTYPE2] = new Tool(zoomba.Content.Load<Texture2D>("gfx/squares/floor2"));
            tools[(int)MapSquare.WALLTYPE3] = new Tool(zoomba.Content.Load<Texture2D>("gfx/squares/floor3"));
            tools[(int)MapSquare.WALLTYPE4] = new Tool(zoomba.Content.Load<Texture2D>("gfx/squares/floor4"));
            tools[(int)MapSquare.FLOORTYPE1] = new Tool(zoomba.Content.Load<Texture2D>("gfx/squares/floor1"));
            tools[(int)MapSquare.FLOORTYPE2] = new Tool(zoomba.Content.Load<Texture2D>("gfx/squares/floor2"));
            tools[(int)MapSquare.FLOORTYPE3] = new Tool(zoomba.Content.Load<Texture2D>("gfx/squares/floor3"));
            tools[(int)MapSquare.FLOORTYPE4] = new Tool(zoomba.Content.Load<Texture2D>("gfx/squares/floor4"));
            tools[(int)MapSquare.OBJECTTYPE1] = new Tool(zoomba.Content.Load<Texture2D>("gfx/squares/floor1"));
            tools[(int)MapSquare.OBJECTTYPE2] = new Tool(zoomba.Content.Load<Texture2D>("gfx/squares/floor2"));
            tools[(int)MapSquare.OBJECTTYPE3] = new Tool(zoomba.Content.Load<Texture2D>("gfx/squares/floor3"));
            tools[(int)MapSquare.OBJECTTYPE4] = new Tool(zoomba.Content.Load<Texture2D>("gfx/squares/floor4"));
        }

        new public void LoadContent()
        {
            saver.Font = zoomba.Content.Load<SpriteFont>("fonts/arial");
            loader.Font = saver.Font;
            white1x1 = zoomba.Content.Load<Texture2D>("gfx/mapeditor/white1x1");
            cursor.texture = white1x1;
            InitTools();

            if (Directory.Exists(mapDir))
                Directory.CreateDirectory(mapDir);

            ReadAvailableFiles();
        }

        protected void SetValidNameCharacters()
        {
            validInput = new Keys[]
            {
                Keys.A, Keys.B, Keys.C, Keys.D, Keys.E, Keys.F, Keys.G, Keys.H,
                Keys.I, Keys.J, Keys.K, Keys.L, Keys.M, Keys.N, Keys.O, Keys.P,
                Keys.Q, Keys.R, Keys.S, Keys.T, Keys.U, Keys.V, Keys.W, Keys.X,
                Keys.Y, Keys.Z, Keys.Space, Keys.Back
            };
        }

        protected String NameToFilename(String name)
        {
            return name.ToLower().Replace(' ', '_');
        }

        protected String FilenameToName(String filename)
        {
            return filename.ToUpper().Replace('_', ' ');
        }

        protected void SaveToFile(String name)
        {
            Console.WriteLine("SaveToFile(" + name + "):");
            // TODO : use path.combine (or whatever it's called)
            //StreamWriter writer = new StreamWriter(basePath + "/" + filename + "." + extension);
            StreamWriter writer = new StreamWriter(Path.Combine(mapDir, NameToFilename(name) + "." + extension));

            for (int row = 0; row < rows; row++)
            {
                for (int column = 0; column < columns; column++)
                {
                    // e.g. squareTool[0] > A, 1 > B and so on  
                    writer.Write((char)((int)'A' + (int)squares[row, column]));
                }
                writer.WriteLine();
            }
            writer.Close();

            // TODO : ? perhaps this should not be here
            ReadAvailableFiles();
        }

        protected String GetFullPath(String name)
        {
            return Path.Combine(mapDir, NameToFilename(name) + "." + extension);
        }

        protected void LoadFromFile(String name)
        {
            Console.WriteLine("LoadFromFile(" + name + "):");
            // TODO : handle FileNotFoundException?
            StreamReader reader = new StreamReader(GetFullPath(name));
            for (int row = 0; row < rows; row++)
            {
                Char[] line = reader.ReadLine().ToCharArray();
                for (int column = 0; column < columns; column++)
                    squares[row, column] = (MapSquare)(line[column] - (int)'A');
            }
            reader.Close();
        }

        protected void DeleteMap(String name)
        {
            try
            {
                File.Delete(GetFullPath(name));
                // TODO : action "previous"
                if (--loader.Selected < 0)
                    loader.Selected = 0;
                // TODO : ? perhaps this should not be here
                ReadAvailableFiles();
            }
            catch (FileNotFoundException e)
            {
                Console.WriteLine("RemoveMap(" + name + "): map not found. [" + e.Message + "]");
            }
        }

        // TODO : ? move to loader
        protected void ReadAvailableFiles()
        {
            FileInfo[] files = (new DirectoryInfo(mapDir).GetFiles("*." + extension));
            availableMaps = new MapFile[files.Length];
            for (int i = 0; i < files.Length; i++)
            {
                availableMaps[i] = new MapFile();
                availableMaps[i].Name = FilenameToName(files[i].Name.Split('.')[0]).ToUpper();
                availableMaps[i].FilePath = files[i].Name;
                Console.WriteLine(files[i].ToString());
            }
        }

        public override void Update(GameTime gameTime)
        {
            HandleInput(gameTime);

            if (actions.ToggleSaver)
            {
                saver.Enabled = !saver.Enabled;
                if (saver.Enabled)
                    loader.Enabled = false;
            }
            if (actions.ToggleLoader)
            {
                loader.Enabled = !loader.Enabled;
                if (loader.Enabled)
                    saver.Enabled = false;
            }

            // update cursor, takes care of both GamePad and Mouse
            UpdateCursor(gameTime);

            if (saver.Enabled)
                HandleNameInput(gameTime);
            else if (loader.Enabled)
                HandleLoader(gameTime);
            else
                HandleMapEdit(gameTime);
        }

        protected void HandleInput(GameTime gameTime)
        {
            HandleGamePad(gameTime);
            HandleKeyboard(gameTime);
            HandleMouse(gameTime);
        }

        protected void HandleGamePad(GameTime gameTime)
        {
            gamePadLast = gamePad;
            gamePad = GamePad.GetState(PlayerIndex.One);

            // cursor
            // TODO : implement
        }

        protected void HandleKeyboard(GameTime gameTime)
        {
            keyboardLast = keyboard;
            keyboard = Keyboard.GetState();

            if (keyboard.IsKeyDown(Keys.F6) && keyboardLast.IsKeyUp(Keys.F6))
                actions.ToggleSaver = true;
            else
                actions.ToggleSaver = false;

            if (keyboard.IsKeyDown(Keys.F5) && keyboardLast.IsKeyUp(Keys.F5))
                actions.ToggleLoader = true;
            else
                actions.ToggleLoader = false;

            if (loader.Enabled)
            {
                if (keyboard.IsKeyDown(Keys.Enter) && keyboardLast.IsKeyUp(Keys.Enter))
                    actions.Load = true;
                else
                    actions.Load = false;

                if (keyboard.IsKeyDown(Keys.Delete) && keyboardLast.IsKeyUp(Keys.Delete))
                    actions.Delete = true;
                else
                    actions.Delete = false;

                // TODO : use actions "next" and "previous"
                if (keyboard.IsKeyDown(Keys.Up) && keyboardLast.IsKeyUp(Keys.Up))
                    if (--loader.Selected < 0)
                        loader.Selected = availableMaps.Length - 1;
                if (keyboard.IsKeyDown(Keys.Down) && keyboardLast.IsKeyUp(Keys.Down))
                    if (++loader.Selected > availableMaps.Length - 1)
                        loader.Selected = 0;
            }
            else
            {
                // reset all loader actions
                actions.Load = false;
                actions.Delete = false;
            }

            // Save
            if (saver.Enabled
                && keyboard.IsKeyDown(Keys.Enter)
                && keyboardLast.IsKeyUp(Keys.Enter))
                actions.Save = true;
            else
                actions.Save = false;
        }

        protected void HandleMouse(GameTime gameTime)
        {
            mouseLast = mouse;
            mouse = Mouse.GetState();

            // cursor
            //Console.WriteLine("cursor.X=" + cursor.X + ", cursor.Y=" + cursor.Y);
            //Console.WriteLine("mouse.X=" + mouse.X + ", mouse.Y=" + mouse.Y);
            cursor.X = mouse.X;
            cursor.Y = mouse.Y;
        }

        protected void UpdateCursor(GameTime gameTime)
        {
        }

        protected void HandleLoader(GameTime gameTime)
        {
            if (actions.Load && availableMaps.Length > 0)
                LoadFromFile(availableMaps[loader.Selected].Name);

            if (actions.Delete && availableMaps.Length > 0)
                DeleteMap(availableMaps[loader.Selected].Name);
        }

        protected void HandleMapEdit(GameTime gameTime)
        {
            if (mouse.LeftButton == ButtonState.Pressed
                && mouse.X >= 0
                && mouse.X < windowWidth
                && mouse.Y >= 0
                && mouse.Y < windowHeight)
            {
                squares[mouse.Y / squareHeight, mouse.X / squareWidth] = (MapSquare)currentTool;
            }

            // change tool
            if (mouse.ScrollWheelValue - mouseLast.ScrollWheelValue > 0)
            {
                if (++currentTool > numTools - 1)
                    currentTool = 0;
            }
            else if (mouse.ScrollWheelValue - mouseLast.ScrollWheelValue < 0)
            {
                if (--currentTool < 0)
                    currentTool = numTools - 1;
            }
        }

        protected void HandleMapEditBU(GameTime gameTime)
        {
            if (gamePad.IsConnected)
            {
            }
            else
            {
                if (mouse.MiddleButton == ButtonState.Pressed
                    && mouseLast.MiddleButton == ButtonState.Released)
                    SaveToFile(saver.Text);

                if (mouse.LeftButton == ButtonState.Pressed
                    && mouse.X >= 0
                    && mouse.X < windowWidth
                    && mouse.Y >= 0
                    && mouse.Y < windowHeight)
                {
                    squares[mouse.Y / squareHeight, mouse.X / squareWidth] = (MapSquare)currentTool;
                }

                // change tool
                if (mouse.ScrollWheelValue - mouseLast.ScrollWheelValue > 0)
                {
                    if (++currentTool > numTools - 1)
                        currentTool = 0;
                }
                else if (mouse.ScrollWheelValue - mouseLast.ScrollWheelValue < 0)
                {
                    if (--currentTool < 0)
                        currentTool = numTools - 1;
                }
            }
        }

        protected void HandleNameInput(GameTime gameTime)
        {
            if (actions.Save && saver.Text.Length > 0)
                SaveToFile(saver.Text);

            foreach (Keys key in validInput)
                if (keyboard.IsKeyDown(key) && keyboardLast.IsKeyUp(key))
                    saver.Text += (char)key;

            if (saver.Text.Length > 0)
            {
                if (keyboard.IsKeyDown(Keys.Back))
                {
                    Console.WriteLine(repeatDelay);
                    if (keyboardLast.IsKeyUp(Keys.Back))
                    {
                        saver.Text = saver.Text.Substring(0, saver.Text.Length - 1);
                        repeatDelay = backspaceDelayInitial;
                    }
                    else if (repeatDelay <= 0f)
                    {
                        saver.Text = saver.Text.Substring(0, saver.Text.Length - 1);
                        repeatDelay = backspaceFrequency;
                    }
                    else
                        repeatDelay -= backspaceFrequency;
                }
            }
        }

        public override void Draw(GameTime gameTime)
        {
            zoomba.spriteBatch.Begin();

            DrawSquares();
            DrawGrid();
            if (saver.Enabled)
            {
                saver.Draw(gameTime);
                cursor.Draw(gameTime);
            }
            else if (loader.Enabled)
            {
                loader.Draw(gameTime);
                cursor.Draw(gameTime);
            }
            else
            {
                DrawTool();
            }

            zoomba.spriteBatch.End();
        }

        protected void DrawCursor(GameTime gameTime)
        {
            if (saver.Enabled)
                cursor.Draw(gameTime);
            else
                DrawTool();
        }

        protected void DrawSquares()
        {
            // TODO : reuse Rectangle
            for (int row = 0; row < rows; row++)
                for (int column = 0; column < columns; column++)
                    zoomba.spriteBatch.Draw(tools[(int)squares[row,column]].Texture, new Rectangle(column * squareWidth, row * squareHeight, squareWidth, squareHeight), Color.White);
        }

        protected void DrawGrid()
        {
            for (int x = -1; x < windowWidth; x += squareWidth)
            {
                zoomba.spriteBatch.Draw(white1x1, new Rectangle(x, 0, 2, windowHeight), gridColor);
            }
            for (int y = -1; y < windowHeight; y += squareHeight)
            {
                zoomba.spriteBatch.Draw(white1x1, new Rectangle(0, y, windowWidth, 2), gridColor);
            }
        }

        protected void DrawTool()
        {
            zoomba.spriteBatch.Draw(tools[currentTool].Texture, new Rectangle(cursor.X - squareWidth / 2, cursor.Y - squareHeight / 2, squareWidth, squareHeight), Color.White);
            //zoomba.spriteBatch.Draw(tools[currentTool].Texture, new Rectangle(mouse.X - squareWidth / 2, mouse.Y - squareHeight / 2, squareWidth, squareHeight), Color.White);
        }

        protected class Tool
        {
            Texture2D _texture;
            public Texture2D Texture { get { return _texture; } }

            public Tool(Texture2D texture)
            {
                this._texture = texture;
            }
        }

        protected class Cursor
        {
            public int X;
            public int Y;
            public int Width;
            public int Height;
            public Texture2D texture;
            public ZoombaSmasher zoomba;
            public Color color;

            public Cursor(ZoombaSmasher p_zoomba, int p_X, int p_Y)
            {
                zoomba = p_zoomba;
                X = p_X;
                Y = p_Y;
                Width = 10;
                Height = 10;
                color = new Color(112, 219, 255);
            }

            public void Draw(GameTime gameTime)
            {
                zoomba.spriteBatch.Draw(texture, new Rectangle(X - Width / 2, Y - Height / 2, Width, Height), color);
            }
        }

        protected class Actions
        {
            public bool
                Save,
                Load,
                Delete,
                ToggleSaver,
                ToggleLoader,
                SetTile;
        }

        protected class MapFile
        {
            public String 
                Name,
                FilePath;
        }

        protected class Loader : Updatable, Drawable
        {
            public Boolean Enabled { get; set; }
            public Rectangle Bounds { get; set; }
            public SpriteFont Font { get; set; }
            protected Editor editor;
            public int Selected;
            protected Color colorSelected = Color.Red;
            protected Color colorUnselected = Color.White;

            public Loader(Editor editor)
            {
                this.editor = editor;
                Selected = 0;
            }

            public void Draw(GameTime gameTime)
            {
                if (editor.availableMaps.Length > 0)
                {
                    Color color;
                    for (int i = 0; i < editor.availableMaps.Length; i++)
                    {
                        if (Selected == i)
                            color = colorSelected;
                        else
                            color = colorUnselected;

                        editor.zoomba.spriteBatch.DrawString(Font, editor.availableMaps[i].Name.ToUpper(), new Vector2(50, i * 50), color);
                    }
                }
                else
                {
                    editor.zoomba.spriteBatch.DrawString(Font, "NO MAPS AVAILABLE, PLEASE CREATE ONE! : )", new Vector2(50, 50), colorUnselected);
                }
            }

            public void Update(GameTime gameTime)
            {
                throw new NotImplementedException();
            }
        }

        protected class Saver : Drawable
        {
            public Boolean Enabled { get; set; }
            public Rectangle Bounds { get; set; }
            public SpriteFont Font { get; set; }
            public String Text { get; set; }
            protected Editor editor;

            public Saver(Editor editor)
            {
                this.editor = editor;
            }

            public void Draw(GameTime gameTime)
            {
                editor.zoomba.spriteBatch.Draw(editor.white1x1, Bounds, Color.Wheat);

                Vector2 stringSize = Font.MeasureString(Text);
                editor.zoomba.spriteBatch.DrawString(Font, Text, new Vector2(editor.windowWidth / 2 - stringSize.X / 2,
                    editor.windowHeight / 2 - stringSize.Y / 2), new Color(3, 33, 163));
            }
        }

        public interface Drawable
        {
            void Draw(GameTime gameTime);
        }

        public interface Updatable
        {
            void Update(GameTime gameTime);
        }

        // TODO : implement and use, for individual cooldown
        public class ZumbaKeyboard
        {
            public Keys key;

            public class ZumbaKey
            {
                public Keys key;
                public double cooldown;
            }
        }
    }
}
