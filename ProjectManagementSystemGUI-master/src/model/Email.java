package model;

/**
 * model.Email of the employees object
 *
 * @author Rokas Paulauskas
 * @version 1.001 2020-12-03
 */
public class Email
{
    private String user;
    private String host;
    private String domain;
    /**
     * A three argument constructor
     *
     */

    public Email(String user, String host, String domain)
    {
        if (trueOrFalse(user))
        {
            this.user = user.toLowerCase();
        }
        else
            this.user = "wrong email";

        if (trueOrFalse(host))
        {
            this.host = host.toLowerCase();
        }
        else
            this.host = "wrong email";
        if (trueOrFalse(domain))
        {
            this.domain = domain.toLowerCase();
        }
        else
            this.domain = "wrong email";

    }
    /**
     * Checks
     * @return true or false
     * @param test -the tested variable
     */

    public boolean trueOrFalse(String test)
    {
        if (!(test.length() >= 1 && test.length() < 64))
        {
            return false;
        }

        for (int i = 0; i < test.length(); i++)
        {
            char ch = test.charAt(i);

            if (!(ch >= 'A' && ch <= 'Z' || ch >= 'a' && ch <= 'z'
                    || ch >= '0' && ch <= '9'))
            {
                return false;
            }
        }
        return true;
    }

    /**
     * gets the name of the user
     * @return user
     */
    public String getUser()
    {
        return user;
    }

    /**
     * gets the name of the host for example gmail/yahoo etc
     * @return host
     */
    public String getHost()
    {
        return host;
    }

    /**
     * gets domain of the email service for example com/co.uk/lt etc
     * @return domain
     */
    public String getDomain()
    {
        return domain;
    }

    /**
     * formats the user/host/domain to the valid email if there was any wrong input changes the string to wrong format.
     * @return
     */
    public String toString()
    {
        String s = user + "@" + host + "." + domain;
        if (s.contains("wrong email"))
            return "Wrong format";
        else
            return s;

    }

    /**
     * compares objects first checks if it is email if not returns false else compares email objects if they are the same
     *
     * @param obj - any object
     * @return false or true if the emails are the same or not.
     */
    @Override public boolean equals(Object obj)
    {
        if (!(obj instanceof Email))
        {
            return false;
        }
        Email other = (Email) obj;
        return user.equals(other.getUser()) && host.equals(other.getHost())
                && domain.equals(other.getDomain());
    }

}
















