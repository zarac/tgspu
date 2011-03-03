using System;

namespace DeluxeAdventures
{
#if WINDOWS || XBOX
    static class Program
    {
        /// <summary>
        /// The main entry point for the application.
        /// </summary>
        static void Main(string[] args)
        {
            using (DeluxeAdventure game = new DeluxeAdventure())
            {
                game.Run();
            }
        }
    }
#endif
}

