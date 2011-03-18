using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;

namespace ETalisman.Character
{
    class Creater : DrawableGameComponent
    {

        ETalisman eTalisman;
        //Adventure adventure;
        SpriteBatch spriteBatch;
        Character character;
        ZXNA.Input.Input input;

        Step step;
        enum Step
        {
            PICK_CLASS = 1,
            SET_STATS = 2,
            END = 3,
        }

        private Class[] classes;

        int selected;

        StatsGUI statsGUI;
 
        int statSelected;

        public Creater(ETalisman eTalisman): base(eTalisman)
        {
            this.eTalisman = eTalisman;
            input = eTalisman.input;
            spriteBatch = eTalisman.spriteBatch;
            step = Step.PICK_CLASS;

            character = new Character(eTalisman);

            // class
            Class amazon = new Class(new Stats(eTalisman), "Yahaua Indian");
            amazon.texture = eTalisman.Content.Load<Texture2D>("gfx/amazon/amazon2");
            amazon.bounds = new Rectangle(-30, 170, 800, 439);

            Class warrior = new Class(new Stats(eTalisman), "Dingo Warrior");
            warrior.texture = eTalisman.Content.Load<Texture2D>("gfx/warrior/warrior2");
            warrior.bounds = new Rectangle(290, 0, 270, 355);

            Class sorceress = new Class(new Stats(eTalisman), "Mary Kate");
            sorceress.texture = eTalisman.Content.Load<Texture2D>("gfx/sorceress/sorceress2");
            sorceress.bounds = new Rectangle(550, 0, 242, 600);

            Class eTMichael = new Class(new Stats(eTalisman), "E.T. and Michael Jackson");
            eTMichael.texture = eTalisman.Content.Load<Texture2D>("gfx/etandmichaeljackson/etandmichaeljackson2");
            eTMichael.bounds = new Rectangle(290, 260, 362, 400);

            classes = new Class[] { warrior, sorceress, amazon, eTMichael };

            // stats
            statsGUI = new StatsGUI(eTalisman, character.stats);
        }

        public override void Draw(GameTime gameTime)
        {
            spriteBatch.Begin();
            
            // step: CLASS
            if (step == Step.PICK_CLASS)
            {
                Color colorUnSelected = new Color(180, 150, 150);
                Color colorSelected = new Color(215, 255, 215);

                for (int i = 0; i < classes.Length; i++)
                {
                    if (i == selected)
                        spriteBatch.Draw(classes[i].texture, classes[i].bounds, colorSelected);
                    else
                        spriteBatch.Draw(classes[i].texture, classes[i].bounds, colorUnSelected);
                }
            }
            // step: STATS
            else if (step == Step.SET_STATS)
            {
                // orange test box
                // TODO :
                spriteBatch.Draw(eTalisman.testTexture, new Rectangle(10, 10, 10, 10), Color.Orange);
            }

            spriteBatch.End();

            base.Draw(gameTime);
        }

        public override void Update(GameTime gameTime)
        {
            // TODO: ? runs one iteration behind
            if (step >= Step.END)
            {
                eTalisman.adventure.step = Adventure.Step.MAP;
            }
            // navigation

            // prev step
            if (input.keyboardState.IsKeyDown(Keys.Back)
                && !input.lastKeyboardState.IsKeyDown(Keys.Back))
            {
                if ((int)step > 1)
                    step--;
            }

            // step: CLASS
            if (step == Step.PICK_CLASS)
            {
                // continue
                // TODO : ? validation
                if (input.keyboardState.IsKeyDown(Keys.Enter)
                    && !input.lastKeyboardState.IsKeyDown(Keys.Enter))
                {
                    step++;
                }

                if (input.keyboardState.IsKeyDown(Keys.Left)
                    && !input.lastKeyboardState.IsKeyDown(Keys.Left))
                {
                    selected--;
                    if (selected < 0)
                        selected = classes.Length - 1;
                }
                if (input.keyboardState.IsKeyDown(Keys.Right)
                    && !input.lastKeyboardState.IsKeyDown(Keys.Right))
                {
                    selected++;
                    if (selected >= classes.Length)
                        selected = 0;
                }
            }
            // step: STATS
            else if (step == Step.SET_STATS)
            {
                if (eTalisman.input.keyboardState.IsKeyDown(Keys.Left))
                {
                }
                else if (eTalisman.input.keyboardState.IsKeyDown(Keys.Right))
                {
                }
                else if (eTalisman.input.keyboardState.IsKeyDown(Keys.Up))
                {
                    // TODO : boundary check
                    statSelected--;
                }
                else if (eTalisman.input.keyboardState.IsKeyDown(Keys.Down))
                {
                    // TODO : boundary check
                    statSelected++;
                }
            }

            base.Update(gameTime);
        }

        class StatsGUI
        {
            private ETalisman eTalisman;
            Stats stats;

            public StatsGUI(ETalisman eTalisman, Stats stats)
            {
                this.eTalisman = eTalisman;
                this.stats = stats;
            }

            public void Draw(GameTime gameTime)
            {
            }

            public void Update(GameTime gameTime)
            {
            }
        }
    }
}
