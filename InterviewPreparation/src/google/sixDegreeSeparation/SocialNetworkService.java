package google.sixDegreeSeparation;

import java.util.Collection;

public interface SocialNetworkService {

    /**
     * Returns a set of Ids of the immediate friends of the Person with the given Id.
     */
    Collection<String> findFriends(String personId);

    /**
     * Creates a new Person in the social network with the given name and returns the unique Id of the Person.
     */
    void addPerson(String personId);

    /**
     * Adds a friend relationship between the given two Person Ids
     */
    void addFriend(String personId, String friendId);
}