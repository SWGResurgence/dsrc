package script.creature_spawner;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

public class corellia_easy extends base_newbie_creature_spawner
{
    public static final boolean SPAWNER_DISABLED = true;
    public int maxPop = 6;
    public boolean newbie = true;
    public corellia_easy()
    {
    }

    public String pickCreature() throws InterruptedException
    {
        switch (rand(1, 4))
        {
            case 1:
                return "corellian_butterfly_drone";
            case 2:
                return "corellian_butterfly";
            case 3:
                return "durni";
            case 4:
                return "gubbur";
        }
        return "gubbur";
    }
}
