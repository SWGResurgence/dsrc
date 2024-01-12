/*
 Title:        internal_script_error
 Description:  error class to be caught inside our C code
 */

package script;

/*
 * Copyright © SWG:Resurgence 2023.
 *
 * Unauthorized usage, viewing or sharing of this file is prohibited.
 */

class internal_script_error extends Error
{
    private Error wrappedError = null;

    internal_script_error()
    {
    }

    internal_script_error(String message)
    {
        super(message);
    }

    internal_script_error(Error err)
    {
        wrappedError = err;
    }
}    // class internal_script_error
