ZOOMBA SMASHERS
===============
To do
    Map Editor
        Grid system
    Graphics
Interfaces
    Graphics
        drawScene(Scene)
    Controller
Class
    Scene:
        Map
        Player[4]
        Zoomba[]
        Rocket[]
        Explotion[]
        Bonus[]
        Information
    Map:
        Square[][]
        Square:
            enum FLOOR, WALL, PLAYER_SPAWN(1,2,3,4) ENEMY_SPAWN(1,2,3,4)
                * save as byte
    Player:
        float X,Y
        float velocityX, velocityY
        float direction
        enum LegState STAND, WALK, DYING
        enum BodyState STAND, WALK, SHOOT, DYING
        float animationLeg
        float animationBody
        int weaponType
        int ammoTime
    Zoomba:
        float X,Y
        float velocityX, velocityY
        enum State STAND, WALK, ATTACK, DYING
        float animation
        float direction
    Rocket:
        float X, Y
        float direction
        float velocityX, velocityY
        int type
    Explotion:
        float X, Y
        float animation
        int type
    Bonus:
        int type
        float animation
        float X, Y
    Information:
        int life
        int score
        


Blocks:
    0 Spawn player
    1 Spawn player
    2 Spawn player
    3 Spawn player
    4 Spawn Monster type 1 
    5 Spawn Monster type 2
    6 Spawn Monster type 3
    7 Spawn Monster type 4
    8 Wall type 1
    9 Wall type 2
    10 Wall type 3
    11 Wall type 4
    12 Floor type 1
    13 Floor type 2
    14 Floor type 3
    15 Floor type 4
    16 Object type 1
    17 Object type 2
    18 Object type 3
    19 Object type 4    
