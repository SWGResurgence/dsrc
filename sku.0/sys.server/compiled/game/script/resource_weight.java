/*
 Title:        resource_weight.java
 Description:  resource data used during crafting/manufacturing
 */

package script;


/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

// This class is used to map a resource id to how much it influences crafting data
public class resource_weight
{
    public String attribName;
    public int slot = -1;
    public weight[] weights;
    public resource_weight(String a, weight[] w)
    {
        attribName = a;
        weights = w;
    }

    public resource_weight(String a, int s, weight[] w)
    {
        attribName = a;
        slot = s;
        weights = w;
    }

    public static class weight
    {
        public int resource;
        public int weight;

        public weight(int r, int w)
        {
            resource = r;
            weight = w;
        }
    }
}

