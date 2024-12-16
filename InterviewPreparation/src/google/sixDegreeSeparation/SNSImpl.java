package google.sixDegreeSeparation;


import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;

public class SNSImpl implements SocialNetworkService {

    HashMap<String, Collection<String>> relationships = new HashMap<>();

    /**
     * Returns a list of Ids of the immediate friends of the Person with the given Id.
     */
    @Override
    public Collection<String> findFriends(String personId) {

        return relationships.get(personId);
    }

    /**
     * Creates a new Person in the social network with the given name and returns the unique Id of the Person.
     */
    @Override
    public void addPerson(String personId) {

        relationships.put(personId, new ArrayList<>());
    }

    /**
     * Adds a friend relationship between the given two Person Ids
     */
    @Override
    public void addFriend(String personId, String friendId) {

        // Ensure that both persons exist in the map for convenience.

        if (!relationships.containsKey(personId)) {
            addPerson(personId);
        }

        if (!relationships.containsKey(friendId)) {
            addPerson(friendId);
        }

        relationships.get(personId).add(friendId);
        relationships.get(friendId).add(personId);
    }
}

