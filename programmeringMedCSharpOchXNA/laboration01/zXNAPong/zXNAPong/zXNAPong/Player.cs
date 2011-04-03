using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace zXNAPong
{
    class Player
    {
        public Paddle paddle;
        int score;

        public Player(Paddle paddle)
        {
            this.paddle = paddle;
            score = 0;
        }

        public void ResetScore()
        {
            score = 0;
        }

        public int Score
        {
            get { return score; }
            set { score = value; }
        }
    }
}
