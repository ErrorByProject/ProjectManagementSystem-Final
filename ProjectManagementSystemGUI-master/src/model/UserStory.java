package model;

/**
 *  A class representing a User Story
 * @author Nichita Railean
 * @version 1.0 dec 3
 */

public class UserStory
{
    private String what;
    private String how;
    private String who;

    /**
     *  A 3 argument constructor creating an model.UserStory
     * @param what what
     * @param how how
     * @param who who
     *
     */

    public UserStory (String what, String how, String who ) {
        this.what = what;
        this.how = how;
        this.who = who;
    }

    /**
     * A one argument creting a User story
     * Setting why, who to an empty String
     * @param what what
     */

    public UserStory (String what){
        this.what = what;
        this.how = "";
        this.who = "";
    }

    /**
     * A method copying the model.UserStory
     * @return the copy named other
     */

    public UserStory copy(){
        UserStory other = new UserStory(what, how, who);
        return other;
    }

    /**
     * A method returning what
     * @return what
     */
    public String getWhat(){return what;}

    /**
     * A method returning how
     * @return how
     */
    public String getHow(){return how;}

    /**
     * A method returning who
     * @return who
     */
    public String getWho(){return who;}

    @Override public String toString(){
        return what + " " + how + " " + who;
    }

    @Override public boolean equals(Object obj){
        if(!(obj instanceof UserStory)){return false;}
        UserStory other = (UserStory) obj;
        return (what.equals(other.what) && how.equals(other.how) && who.equals(other.who));
    }
}
