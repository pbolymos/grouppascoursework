import java.util.Arrays;

public class FileSystemDirectory {
    private FolderNode root;

    public FileSystemDirectory(String rootName) {
        this.root = new FolderNode(rootName, rootName);
    }

    public void addNode(FSNode node, String parentPath) {
        FolderNode parentNode = findFolder(root, parentPath);
        if (parentNode != null && parentNode instanceof FolderNode) {
            if (node instanceof FileNode) {
                node.path = parentPath + "/" + node.getName();
            }
            ((FolderNode) parentNode).addChild(node);
            System.out.println("Node added successfully.");
        } else {
            System.out.println("Parent folder not found!");
        }
    }


    public void deleteNode(String path) {
        FSNode nodeToDelete = findNode(path);
        System.out.println(nodeToDelete);
        if (nodeToDelete != null) {
            FolderNode parentNode = findParentFolder(path);
            if (parentNode != null) {
                parentNode.removeChild(nodeToDelete);
                System.out.println("Node deleted successfully.");
            } else {
                System.out.println("Parent folder not found!");
            }
        } else {
            System.out.println("Node not found!");
        }
    }
    

    public void printDirectory() {
        printNode(root, 0);
    }

    private void printNode(FSNode node, int depth) {
        for (int i = 0; i < depth; i++) {
            System.out.print("  ");
        }
        System.out.println("|--" + node.getName());
        if (node instanceof FolderNode) {
            FolderNode folderNode = (FolderNode) node;
            for (FSNode child : folderNode.getChildren()) {
                printNode(child, depth + 1);
            }
        }
    }

    private FolderNode findFolder(FolderNode current, String folderName) {
        if (current.getName().equals(folderName)) {
            return current;
        }

        for (FSNode child : current.getChildren()) {
            if (child instanceof FolderNode) {
                FolderNode result = findFolder((FolderNode) child, folderName);
                if (result != null) {
                    return result;
                }
            }
        }

        return null;
    }

    protected FSNode findNode(String path) {
        return findNodeHelper(root, path);
    }
    
    private FSNode findNodeHelper(FSNode current, String targetPath) {
        if (current == null) {
            return null;
        }
    
        if (current.getPath().equals(targetPath)) {
            return current;
        }
    
        if (current instanceof FolderNode) {
            FolderNode folderNode = (FolderNode) current;
            for (FSNode child : folderNode.getChildren()) {
                FSNode result = findNodeHelper(child, targetPath);
                if (result != null) {
                    return result;
                }
            }
        }
    
        return null;
    }
    
    

    private FolderNode findParentFolder(String path) {
        String[] parts = path.split("/");
        if (parts.length <= 1) {
            return null; // Root has no parent
        }
    
        String parentPath = String.join("/", Arrays.copyOf(parts, parts.length - 1));
        return (FolderNode) findNodeHelper(root, parentPath);
    }
    
    public FSNode searchNodeByPath(String path) {
        FSNode foundNode = searchNodeByPathHelper(root, path);
    
        if (foundNode != null) {
            System.out.println("Node found: " + foundNode.toString());
        } else {
            System.out.println("Node not found!");
        }
    
        return foundNode;
    }
    
    private FSNode searchNodeByPathHelper(FSNode current, String targetPath) {
        if (current == null) {
            return null;
        }
    
        if (current.getPath().equals(targetPath)) {
            return current;
        }
    
        if (current instanceof FolderNode) {
            FolderNode folderNode = (FolderNode) current;
            for (FSNode child : folderNode.getChildren()) {
                FSNode result = searchNodeByPathHelper(child, targetPath);
                if (result != null) {
                    return result;
                }
            }
        }
    
        return null;
    }
    

    public String getPath(FSNode node) {
        return getPathHelper(root, node);
    }

    private String getPathHelper(FolderNode current, FSNode targetNode) {
        if (current == null) {
            return "";
        }

        if (current.equals(targetNode)) {
            return "/" + current.getName();
        }

        for (FSNode child : current.getChildren()) {
            if (child instanceof FolderNode) {
                String path = getPathHelper((FolderNode) child, targetNode);
                if (!path.isEmpty()) {
                    return "/" + current.getName() + path;
                }
            }
        }

        return "";
    }
}