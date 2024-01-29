package atlassian;

import java.util.*;


public class FileProcessor {
    private Map<String, List<FileEntry>> fileData;
    private Map<String, Integer> collectionSizes;

    public FileProcessor() {
        fileData = new HashMap<>();
        collectionSizes = new HashMap<>();
    }

    public void addFile(String fileName, int fileSize, List<String> collections) {
        FileEntry fileEntry = new FileEntry(fileName, fileSize, collections);
        for (String collection : collections) {
            fileData.computeIfAbsent(collection, k -> new ArrayList<>()).add(fileEntry);

            // Update the collection size
            collectionSizes.put(collection, collectionSizes.getOrDefault(collection, 0) + fileSize);
        }
    }

    public void updateFile(String fileName, int newFileSize) {
        for (List<FileEntry> fileList : fileData.values()) {
            Optional<FileEntry> fileToUpdate = fileList.stream()
                    .filter(fileEntry -> fileEntry.fileName.equals(fileName))
                    .findFirst();

            if (fileToUpdate.isPresent()) {
                FileEntry fileEntry = fileToUpdate.get();
                int oldFileSize = fileEntry.fileSize;
                List<String> collections = fileEntry.collections;

                // Update the file size and collection sizes
                for (String collection : collections) {
                    collectionSizes.put(collection, collectionSizes.get(collection) - oldFileSize + newFileSize);
                }

                // Update the file's size
                fileEntry.fileSize = newFileSize;
                break; // Assuming a file has a unique name across collections
            }
        }
    }

    public int calculateTotalSize() {
        return collectionSizes.values().stream().mapToInt(Integer::intValue).sum();
    }

    public List<Map.Entry<String, Integer>> calculateTopCollections(int topK) {
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((e1, e2) -> e2.getValue() - e1.getValue());

        for (Map.Entry<String, Integer> entry : collectionSizes.entrySet()) {
            pq.offer(entry);
        }

        List<Map.Entry<String, Integer>> topCollections = new ArrayList<>();
        for (int i = 0; i < topK && !pq.isEmpty(); i++) {
            topCollections.add(pq.poll());
        }

        return topCollections;
    }

    public static void main(String[] args) {
        FileProcessor processor = new FileProcessor();

        // Add files using the addFile method
        processor.addFile("file1.txt", 100, Collections.singletonList(""));
        processor.addFile("file5.txt", 100, Collections.singletonList(""));
        processor.addFile("file2.txt", 200, Collections.singletonList("collection1"));
        processor.addFile("file3.txt", 200, Collections.singletonList("collection1"));
        processor.addFile("file4.txt", 300, Collections.singletonList("collection2"));
        processor.addFile("file6.txt", 150, Arrays.asList("collection1", "collection2")); // File in multiple collections

        // Calculate total size of files processed
        int totalSize = processor.calculateTotalSize();
        System.out.println("Total size of files processed: " + totalSize);

        // Calculate and display top K collections based on size
        int topK = 2;
        List<Map.Entry<String, Integer>> topCollections = processor.calculateTopCollections(topK);
        System.out.println("Top " + topK + " collections:");
        for (Map.Entry<String, Integer> entry : topCollections) {
            String collectionName = entry.getKey().isEmpty() ? "Default" : entry.getKey();
            System.out.println(collectionName + " : " + entry.getValue());
        }

        // Update the size of "file2.txt" to 250
        processor.updateFile("file2.txt", 250);

        // Recalculate total size of files processed after the update
        totalSize = processor.calculateTotalSize();
        System.out.println("Updated total size of files processed: " + totalSize);

        // Display updated top K collections based on size
        topCollections = processor.calculateTopCollections(topK);
        System.out.println("Updated top " + topK + " collections:");
        for (Map.Entry<String, Integer> entry : topCollections) {
            String collectionName = entry.getKey().isEmpty() ? "Default" : entry.getKey();
            System.out.println(collectionName + " : " + entry.getValue());
        }
    }

    static class FileEntry {
        String fileName;
        int fileSize;
        List<String> collections;

        public FileEntry(String fileName, int fileSize, List<String> collections) {
            this.fileName = fileName;
            this.fileSize = fileSize;
            this.collections = collections;
        }
    }
}
