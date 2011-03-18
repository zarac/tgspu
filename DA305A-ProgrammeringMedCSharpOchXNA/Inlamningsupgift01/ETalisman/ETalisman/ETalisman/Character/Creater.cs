using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;
using Microsoft.Xna.Framework.Input;

namespace ETalisman.Character
{
    enum Step
    {
        PICK_CLASS = 1,
        SET_STATS = 2,
        END = 6,
    }

    class Creater : DrawableGameComponent
    {
        private ETalisman eTalisman;
        private Adventure adventure;
        private SpriteBatch spriteBatch;
        private Character character;
        private ZXNA.Input.Input input;

        private Step step;

        private Class[] classes;

        int selected;

        //private Texture2D amazon, warrior, sorceress;

        public Creater(ETalisman eTalisman): base(eTalisman)
        {
            this.eTalisman = eTalisman;
            input = eTalisman.input;
            spriteBatch = eTalisman.spriteBatch;
            adventure = eTalisman.adventure;
            step = Step.PICK_CLASS;

            character = new Character(eTalisman);

            Class amazon = new Class(new Stats(eTalisman), "Yahaua Indian");
            amazon.texture = eTalisman.Content.Load<Texture2D>("gfx/amazon/amazon");
            amazon.bounds = new Rectangle(-30, 170, 800, 439);

            Class warrior = new Class(new Stats(eTalisman), "Dingo Warrior");
            warrior.texture = eTalisman.Content.Load<Texture2D>("gfx/warrior/warrior");
            warrior.bounds = new Rectangle(290, 0, 270, 355);

            Class sorceress = new Class(new Stats(eTalisman), "Mary Kate");
            sorceress.texture = eTalisman.Content.Load<Texture2D>("gfx/sorceress/sorceress");
            sorceress.bounds = new Rectangle(550, 0, 242, 600);

            Class eTMichael = new Class(new Stats(eTalisman), "E.T. and Michael Jackson");
            eTMichael.texture = eTalisman.Content.Load<Texture2D>("gfx/etandmichaeljackson/etandmichaeljackson");
            eTMichael.bounds = new Rectangle(290, 260, 362, 400);

            classes = new Class[] { warrior, sorceress, amazon, eTMichael };
        }

        public override void Draw(GameTime gameTime)
        {
            spriteBatch.Begin();
            
            // step: class
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

            spriteBatch.End();

            base.Draw(gameTime);
        }

        public override void Update(GameTime gameTime)
        {
            if (step >= Step.END)
            {
            }
            // navigation

            // next step
            if (input.keyboardState.IsKeyDown(Keys.Enter)
                && !input.lastKeyboardState.IsKeyDown(Keys.Enter))
            {
                // TODO ? validation
                 step++;
            }
            // prev step
            if (input.keyboardState.IsKeyDown(Keys.Back)
                && !input.lastKeyboardState.IsKeyDown(Keys.Back))
            {
                if ((int)step > 1)
                    step--;
            }

            // step: class
            if (step == Step.PICK_CLASS)
            {
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

            base.Update(gameTime);
        }

        //public void Start()
        //{
        //    adventure.character = character;
        //    adventure.creater = null;
        //}
    }
}
