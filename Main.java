public class Main {
    public static void main(String[] args) {
        FileSystemDirectory fileSystem = new FileSystemDirectory("Root");
        FSNode folder1 = new FolderNode("Documents", "");
        FSNode pics1 = new FolderNode("Pictures", "");
        FSNode pics2 = new FolderNode("Pictures2", "");
        FSNode pics3 = new FolderNode("Pictures3", "");
        FSNode file1 = new FileNode("file1", 20,"");
        fileSystem.addNode(folder1,"Root");
        fileSystem.addNode(pics1, "Root");
        fileSystem.addNode(pics2, "Pictures");
        fileSystem.addNode(pics3, "Pictures2");
        fileSystem.addNode(file1, "Pictures3");
        System.out.println("Original Directory Structure:");
        fileSystem.printDirectory();
        // System.out.println(pics3);
        // Delete the folder "Pictures"
        fileSystem.searchNodeByPath(pics3.getPath());
        //fileSystem.deleteNode(file1.getPath());
        System.out.println("\nDirectory Structure after deleting Pictures:");
        fileSystem.printDirectory();
    }
}

