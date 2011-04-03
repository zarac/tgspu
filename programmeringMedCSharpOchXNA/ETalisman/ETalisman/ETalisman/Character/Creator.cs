using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;

namespace ETalisman.Character
{
    class Creator : DrawableGameComponent
    {

        ETalisman eTalisman;
        //Adventure adventure;
        SpriteBatch spriteBatch;
        Character character;
        static SpriteFont font;
        static Stats newStats;
        Texture2D background;

        static Step step;
        enum Step
        {
            PICK_CLASS = 1,
            SET_STATS = 2,
            END = 3,
        }

        ClassGUI classGUI;
        StatsGUI statsGUI;

        public Creator(ETalisman eTalisman): base(eTalisman)
        {
            this.eTalisman = eTalisman;
            spriteBatch = eTalisman.spriteBatch;
            step = Step.PICK_CLASS;

            character = new Character(eTalisman);
            newStats = new Stats(eTalisman);

            // info text
            font = eTalisman.Content.Load<SpriteFont>("font/Arial");

            background = eTalisman.Content.Load<Texture2D>("gfx/background/106702_Starlightss1");

            // class
            Class amazon = new Class(new Stats(eTalisman), "Yahaua Indian");
            amazon.stats.health = 150;
            amazon.stats.mana = 100;
            amazon.stats.attributes[(int)Stats.Attributes.STRENGTH].value = 10;
            amazon.stats.attributes[(int)Stats.Attributes.DEXTERITY].value = 20;
            amazon.stats.attributes[(int)Stats.Attributes.VITALITY].value = 15;
            amazon.stats.attributes[(int)Stats.Attributes.ENERGY].value = 10;
            amazon.stats.availableAttributePoints = 11;
            amazon.texture = eTalisman.Content.Load<Texture2D>("gfx/amazon/amazon3");
            amazon.bounds = new Rectangle(-30, 170, 800, 439);

            Class warrior = new Class(new Stats(eTalisman), "Dingo Warrior");
            warrior.stats.health = 200;
            warrior.stats.mana = 50;
            warrior.stats.attributes[(int)Stats.Attributes.STRENGTH].value = 20;
            warrior.stats.attributes[(int)Stats.Attributes.DEXTERITY].value = 12;
            warrior.stats.attributes[(int)Stats.Attributes.VITALITY].value = 17;
            warrior.stats.attributes[(int)Stats.Attributes.ENERGY].value = 5;
            warrior.stats.availableAttributePoints = 10;
            warrior.texture = eTalisman.Content.Load<Texture2D>("gfx/warrior/warrior3");
            warrior.bounds = new Rectangle(290, 0, 270, 355);

            Class sorceress = new Class(new Stats(eTalisman), "Mary Kate");
            sorceress.stats.health = 100;
            sorceress.stats.mana = 200;
            sorceress.stats.attributes[(int)Stats.Attributes.STRENGTH].value = 10;
            sorceress.stats.attributes[(int)Stats.Attributes.DEXTERITY].value = 10;
            sorceress.stats.attributes[(int)Stats.Attributes.VITALITY].value = 12;
            sorceress.stats.attributes[(int)Stats.Attributes.ENERGY].value = 20;
            sorceress.stats.availableAttributePoints = 12;
            sorceress.texture = eTalisman.Content.Load<Texture2D>("gfx/sorceress/sorceress3");
            sorceress.bounds = new Rectangle(550, 0, 242, 600);

            Class eTMichael = new Class(new Stats(eTalisman), "E.T. and Michael Jackson");
            eTMichael.stats.health = 150;
            eTMichael.stats.mana = 150;
            eTMichael.stats.attributes[(int)Stats.Attributes.STRENGTH].value = 12;
            eTMichael.stats.attributes[(int)Stats.Attributes.DEXTERITY].value = 13;
            eTMichael.stats.attributes[(int)Stats.Attributes.VITALITY].value = 13;
            eTMichael.stats.attributes[(int)Stats.Attributes.ENERGY].value = 19;
            eTMichael.stats.availableAttributePoints = 13;
            eTMichael.texture = eTalisman.Content.Load<Texture2D>("gfx/etandmichaeljackson/etandmichaeljackson3");
            eTMichael.bounds = new Rectangle(290, 260, 362, 400);

            Class[] classes = new Class[] { warrior, sorceress, amazon, eTMichael };

            // GUIs used in different steps
            classGUI = new ClassGUI(eTalisman, this, classes);
            statsGUI = new StatsGUI(eTalisman, this);
        }

        public override void Draw(GameTime gameTime)
        {
            spriteBatch.Begin();

            //eTalisman.GraphicsDevice.Clear(Color.Black);
            //spriteBatch.Draw(background, new Rectangle(0, 0, 800, 600), new Color(100, 100, 100));

            // STEP : CLASS
            if (step == Step.PICK_CLASS)
                classGUI.Draw(gameTime);
            // STEP : STATS
            else if (step == Step.SET_STATS)
                statsGUI.Draw(gameTime);

            spriteBatch.End();

            base.Draw(gameTime);
        }

        public override void Update(GameTime gameTime)
        {
            // TODO: ? runs one iteration behind
            if (step >= Step.END)
                eTalisman.adventure.step = Adventure.Step.MAP;
            
            // go back to previous step
            if (eTalisman.input.keyboardState.IsKeyDown(Keys.Back)
                && !eTalisman.input.lastKeyboardState.IsKeyDown(Keys.Back))
                if ((int)step > 1)
                    step--;

            // STEP : CLASS
            if (step == Step.PICK_CLASS)
                classGUI.Update(gameTime);
            // STEP : STATS
            else if (step == Step.SET_STATS)
                statsGUI.Update(gameTime);

            base.Update(gameTime);
        }


        class ClassGUI
        {
            private Class[] classes;

            private ETalisman eTalisman;
            private Creator creator;

            int selected;

            public ClassGUI(ETalisman eTalisman, Creator creator, Class[] classes)
            {
                this.eTalisman = eTalisman;
                this.creator = creator;
                this.classes = classes;

            }

            public void Draw(GameTime gameTime)
            {
                Color colorUnSelected = new Color(180, 150, 150);
                Color colorSelected = new Color(230, 255, 230);

                for (int i = 0; i < classes.Length; i++)
                {
                    if (i == selected)
                        eTalisman.spriteBatch.Draw(classes[i].texture, classes[i].bounds, colorSelected);
                    else
                        eTalisman.spriteBatch.Draw(classes[i].texture, classes[i].bounds, colorUnSelected);
                }

                Stats stats = classes[selected].stats;
                String statString = String.Format("{7}{0}{0}Health: {1}{0}Mana: {2}{0}Strength: {3}{0}Dexterity: {4}{0}Vitality: {5}{0}Energy: {6}",
                    Environment.NewLine,
                    stats.health,
                    stats.mana,
                    stats.attributes[(int)Stats.Attributes.STRENGTH].value,
                    stats.attributes[(int)Stats.Attributes.DEXTERITY].value,
                    stats.attributes[(int)Stats.Attributes.VITALITY].value,
                    stats.attributes[(int)Stats.Attributes.ENERGY].value,
                    classes[selected].name);
                eTalisman.spriteBatch.DrawString(font, statString, new Vector2(10f, 10f), Color.Beige);

                eTalisman.spriteBatch.DrawString(font, "Extra Points: " + stats.availableAttributePoints, new Vector2(10f, font.MeasureString(statString).Y + 20f), Color.Chartreuse);
            }

            public void Update(GameTime gameTime)
            {
                // continue
                // TODO : ? validation
                if (eTalisman.input.keyboardState.IsKeyDown(Keys.Enter)
                    && !eTalisman.input.lastKeyboardState.IsKeyDown(Keys.Enter))
                {
                    creator.character.class_ = classes[selected];
                    creator.character.stats.Copy(classes[selected].stats);
                    newStats.Copy(creator.character.stats);
                    step++;
                }

                // select
                if (eTalisman.input.keyboardState.IsKeyDown(Keys.Left)
                    && !eTalisman.input.lastKeyboardState.IsKeyDown(Keys.Left))
                {
                    selected--;
                    if (selected < 0)
                        selected = classes.Length - 1;
                }
                if (eTalisman.input.keyboardState.IsKeyDown(Keys.Right)
                    && !eTalisman.input.lastKeyboardState.IsKeyDown(Keys.Right))
                {
                    selected++;
                    if (selected >= classes.Length)
                        selected = 0;
                }
            }
        }


        class StatsGUI
        {
            private ETalisman eTalisman;
            private ZXNA.Input.Input input;
            private Creator creator;

            Stats.Attributes selected;

            public StatsGUI(ETalisman eTalisman, Creator creator)
            {
                this.eTalisman = eTalisman;
                this.creator = creator;
                input = eTalisman.input;
                selected = Stats.Attributes._BEGIN + 1;
            }

            public void Draw(GameTime gameTime)
            {
                // orange test box
                // TODO :

                eTalisman.spriteBatch.Draw(eTalisman.testTexture, new Rectangle(10, 10, 10, 10), Color.Orange);

                Color colorSelected = new Color(230, 255, 230);

                eTalisman.spriteBatch.Draw(creator.character.class_.texture, creator.character.class_.bounds, colorSelected);

                eTalisman.spriteBatch.DrawString(font, "Health: " + newStats.health, new Vector2(10f, 10f), Color.Red);
                eTalisman.spriteBatch.DrawString(font, "Mana: " + newStats.mana, new Vector2(10f, 40f), Color.Blue);

                Color attributeColorSelected = Color.OldLace;
                Color attributeColorUnSelected = Color.Firebrick;
                Color color;

                for (int i = 1; i < (int)Stats.Attributes._END; i++)
                {
                    if (selected == newStats.attributes[i].attribute)
                        color = attributeColorSelected;
                    else
                        color = attributeColorUnSelected;

                    eTalisman.spriteBatch.DrawString(font, newStats.attributes[i].name + " : " + newStats.attributes[i].value, new Vector2(10f, 70f + 30f * i), color);
                }

                eTalisman.spriteBatch.DrawString(font, "Available Points: " + newStats.availableAttributePoints, new Vector2(10f, 200f), Color.Chartreuse);
            }

            public void Update(GameTime gameTime)
            {
                Console.WriteLine("IS " + newStats.attributes[(int)selected].value + " > " + creator.character.stats.attributes[(int)selected].value);

                // adjust
                if (newStats.attributes[(int)selected].value > creator.character.stats.attributes[(int)selected].value
                    && input.keyboardState.IsKeyDown(Keys.Left)
                    && !input.lastKeyboardState.IsKeyDown(Keys.Left))
                {
                    newStats.attributes[(int)selected].value--;
                    newStats.availableAttributePoints++;
                }
                else if (newStats.availableAttributePoints > 0
                    && input.keyboardState.IsKeyDown(Keys.Right)
                    && !input.lastKeyboardState.IsKeyDown(Keys.Right))
                {
                    newStats.attributes[(int)selected].value++;
                    newStats.availableAttributePoints--;
                }
                // navigate
                else if (input.keyboardState.IsKeyDown(Keys.Up)
                    && !input.lastKeyboardState.IsKeyDown(Keys.Up))
                {
                    if (selected <= Stats.Attributes._BEGIN + 1)
                        selected = Stats.Attributes._END - 1;
                    else
                        selected--;

                    input.Clear();
                    //input.lastKeyboardState = input.keyboardState;
                }
                else if (input.keyboardState.IsKeyDown(Keys.Down)
                    && !input.lastKeyboardState.IsKeyDown(Keys.Down))
                {
                    if (selected >= Stats.Attributes._END - 1)
                        selected = Stats.Attributes._BEGIN + 1;
                    else
                        selected++;

                    //input.lastKeyboardState = input.keyboardState;

                    input.Clear();
                }
            }
        }
    }
}
