/** Represents a social network. The network has users, who follow other uesrs.
 *  Each user is an instance of the User class. */
public class Network {

    // Fields
    private User[] users;  // the users in this network (an array of User objects)
    private int userCount; // actual number of users in this network

    /** Creates a network with a given maximum number of users. */
    public Network(int maxUserCount) {
        this.users = new User[maxUserCount];
        this.userCount = 0;
    }

    /** Creates a network  with some users. The only purpose of this constructor is 
     *  to allow testing the toString and getUser methods, before implementing other methods. */
    public Network(int maxUserCount, boolean gettingStarted) {
        this(maxUserCount);
        users[0] = new User("Foo");
        users[1] = new User("Bar");
        users[2] = new User("Baz");
        userCount = 3;
    }

    public int getUserCount() {
        return this.userCount;
    }
    /** Finds in this network, and returns, the user that has the given name.
     *  If there is no such user, returns null.
     *  Notice that the method receives a String, and returns a User object. */
    public User getUser(String name) {
        //// Replace the following statement with your code
        String lowName = name.toLowerCase();

        for (int i = 0; i < userCount; i ++) {
            String lowUserName = users[i].getName().toLowerCase();
            if (lowUserName.equals(lowName)) {
                return users[i];
            }
        }

        return null;
    }

    /** Adds a new user with the given name to this network.
    *  If ths network is full, does nothing and returns false;
    *  If the given name is already a user in this network, does nothing and returns false;
    *  Otherwise, creates a new user with the given name, adds the user to this network, and returns true. */
    public boolean addUser(String name) {
        //// Replace the following statement with your code
        
        for (int i = 0; i < userCount; i ++) {
            if (this.users[i].getName().equals(name)) return false;
        }

        if (userCount != users.length) {
            this.users[userCount] = new User(name);
            userCount ++;
            return true;
        }
        return false;
    }

    /** Makes the user with name1 follow the user with name2. If successful, returns true.
     *  If any of the two names is not a user in this network,
     *  or if the "follows" addition failed for some reason, returns false. */
    public boolean addFollowee(String name1, String name2) {
        //// Replace the following statement with your code

        for (int i = 0; i < userCount; i ++) {
            if (users[i].getName().equals(name1)) {
                if (users[i].follows(name2) || users[i].getName().equals(name2)) return false;
                else {
                    for (int j = 0; j < userCount; j ++) {
                        if (users[j].getName().equals(name2)) {
                            this.users[i].addFollowee(name2);
                            return true;
                        }
                    }
                }
            }                  
        }

        return false;
    }
    
    /** For the user with the given name, recommends another user to follow. The recommended user is
     *  the user that has the maximal mutual number of followees as the user with the given name. */
    public String recommendWhoToFollow(String name) {
        //// Replace the following statement with your code
        User thisUser = null;
        User mostRecommendedUserToFollow = null;
        int maxCommonF = 0;

        for (int i = 0; i < userCount; i ++) {
            if (users[i].getName() == name) {
                thisUser = users[i];
                break;
            }
        }
        
        for (int i = 0; i < userCount; i ++) {            
            if (thisUser == users[i]) continue;
            int commonF = 0;
            String[] userArr = users[i].getfFollows();

            for (int j = 0; j < users[i].getfCount(); j ++) {
                if (thisUser.follows(userArr[j])) commonF ++;
            }

            if (commonF > maxCommonF) {
                mostRecommendedUserToFollow = users[i];
                maxCommonF = commonF;
            }
        }

        return mostRecommendedUserToFollow.getName();
    }

    /** Computes and returns the name of the most popular user in this network: 
     *  The user who appears the most in the follow lists of all the users. */
    public String mostPopularUser() {
        //// Replace the following statement with your code
        User mostPop = null;
        int maxF = 0;

        for (int i = 0; i < userCount; i ++) {
            if (users[i] != null && (followeeCount(users[i].getName()) > maxF)) {
                mostPop = users[i];
                maxF = followeeCount(users[i].getName());
            }
        }

        if (mostPop == null) return null;
        return mostPop.getName();
    }

    /** Returns the number of times that the given name appears in the follows lists of all
     *  the users in this network. Note: A name can appear 0 or 1 times in each list. */
    private int followeeCount(String name) {
        //// Replace the following statement with your code
        int numOfFollowers = 0;

        for (int i = 0; i < userCount; i ++) {
            if (users[i].follows(name)) numOfFollowers ++;
        }
        return numOfFollowers;
    }

    // Returns a textual description of all the users in this network, and who they follow.
    public String toString() {
       //// Replace the following statement with your code
       String ans = "Network:";
       for (int i = 0; i < userCount; i++) {
           ans = ans + "\n" + users[i];
        }
       return ans;       
    }
}
