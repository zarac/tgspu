using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using Microsoft.Xna.Framework;
using Microsoft.Xna.Framework.Graphics;

namespace ETalisman.Character
{
    public class Stats : ETalismanComponent
    {
        protected ETalisman eTalisman;

        // diablo ii style ; )
        public int health, mana;

        // attributes
        public Attribute[] attributes;
        public int availableAttributePoints;

        public enum Attributes
        {
            _BEGIN,
            STRENGTH,
            DEXTERITY,
            VITALITY,
            ENERGY,
            _END,
        }

        public Stats(ETalisman eTalisman) : base(eTalisman)
        {
            health = 100;
            mana = 100;

            attributes = new Attribute[(int)Attributes._END];

            attributes[(int)Stats.Attributes.STRENGTH] = new Attribute(Attributes.STRENGTH, "Strength", 10);
            attributes[(int)Stats.Attributes.DEXTERITY] = new Attribute(Attributes.DEXTERITY, "Dexterity", 10);
            attributes[(int)Stats.Attributes.VITALITY] = new Attribute(Attributes.VITALITY, "Vitality", 10);
            attributes[(int)Stats.Attributes.ENERGY] = new Attribute(Attributes.ENERGY, "Energy", 10);
            availableAttributePoints = 10;
        }

        //public static Stats operator+(Stats stats1)
        //{
        //    return new Stats(eTalisman);
        //}

        public override void Draw(GameTime gameTime)
        {
            //spriteBatch.Begin();
            //spriteBatch.End();
        }

        public override void Update(GameTime gameTime)
        {
            //throw new NotImplementedException();
        }


        public void Copy(Stats from)
        {
            // broken here!?
            //attributes = (Stats.Attribute[])from.attributes.Clone();
            for (int i = (int)Attributes._BEGIN + 1; i < (int)Attributes._END - 1; i++)
                attributes[i].value = from.attributes[i].value;
            health = from.health;
            mana = from.mana;
            availableAttributePoints = from.availableAttributePoints;
        }

        public class Attribute
        {
            public Attributes attribute;
            public String name;
            public int value;

            public Attribute(Attributes attribute, string name, int value)
            {
                this.attribute = attribute;
                this.name = name;
                this.value = value;
            }
        }
    }
}
