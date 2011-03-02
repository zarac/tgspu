using System;

namespace zXNAPong
{
#if WINDOWS || XBOX
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        static void Main(string[] args)
        {
            using (XNAPong game = new XNAPong())
            {
                game.Run();
            }
        }
    }
#endif
}

