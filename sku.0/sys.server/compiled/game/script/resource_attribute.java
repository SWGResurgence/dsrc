/*
 Title:        resource_attribute.java
 Description:  resource attribute name->value pair
 */

package script;


/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

public class resource_attribute
{
    private final String m_name;
    private final int m_value;

    public resource_attribute(String name, int value)
    {
        m_name = name;
        m_value = value;
    }

    public String getName()
    {
        return m_name;
    }

    public int getValue()
    {
        return m_value;
    }
}

