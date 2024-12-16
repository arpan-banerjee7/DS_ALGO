package google.trie;

import java.util.*;
// 1233. Remove Sub-Folders from the Filesystem
/*
We start with an empty Trie and insert folder paths by splitting each path into its components
(e.g., "/a/b/c" becomes ["a", "b", "c"]). As we insert each part, we check if we’ve reached an endpoint
in the Trie. If so, we can skip the current folder as it’s a sub-folder. Otherwise, we continue inserting the remaining parts. At the end of each path, we mark it as an endpoint.

This way, any future folder that follows an existing path will encounter the endpoint, confirming
it as a sub-folder. This is extremely effective for handling deeply nested folder structures.

We will do this in two pass, first we wll insert all folder paths in trie
Then check for each part, we need to be careful to check isFolder variable only if the current part of the path is not the last folder

as the folders might not come sorted, so we would have inserted /a/b first in trie and marked the end of bs trie as true
and then we will insert /a mask the end of a's trie as true. When we start checking for sub folders in the next pass
when we encounter a, its trie wll be marked as true, but then its not a subfolder, as a is the last element in the path.
*/
public class RemoveSubFolders {

    static class TrieNode {

        boolean isEndOfFolder;
        HashMap<String, TrieNode> children;

        TrieNode() {
            this.isEndOfFolder = false;
            this.children = new HashMap<>();
        }
    }

    TrieNode root;

    RemoveSubFolders() {
        this.root = new TrieNode();
    }

    public List<String> removeSubfolders(String[] folder) {
        // Build Trie from folder paths
        for (String path : folder) {
            TrieNode currentNode = root;
            String[] folderNames = path.split("/");

            for (String folderName : folderNames) {
                // Skip empty folder names
                if (folderName.equals("")) continue;
                // Create new node if it doesn't exist
                if (!currentNode.children.containsKey(folderName)) {
                    currentNode.children.put(folderName, new TrieNode());
                }
                currentNode = currentNode.children.get(folderName);
            }
            // Mark the end of the folder path
            currentNode.isEndOfFolder = true;
        }

        // Check each path for subfolders
        List<String> result = new ArrayList<>();
        for (String path : folder) {
            TrieNode currentNode = root;
            String[] folderNames = path.split("/");
            boolean isSubfolder = false;

            for (int i = 0; i < folderNames.length; i++) {
                // Skip empty folder names
                if (folderNames[i].equals("")) continue;

                TrieNode nextNode = currentNode.children.get(folderNames[i]);
                // Check if the current folder path is a subfolder of an
                // existing folder
                if (nextNode.isEndOfFolder  && i != folderNames.length - 1) {
                    isSubfolder = true;
                    break; // Found a sub-folder
                }

                currentNode = nextNode;
            }
            // If not a sub-folder, add to the result
            if (!isSubfolder) result.add(path);
        }

        return result;
    }

    public static void main(String[] args) {
        String[] folders = {"/a", "/a/b", "/c/d", "/c/d/e", "/c/f"};
        RemoveSubFolders rs = new RemoveSubFolders();
        List<String> res = rs.removeSubfolders(folders);
        for (String r : res) {
            System.out.println(r);
        }
    }

}

