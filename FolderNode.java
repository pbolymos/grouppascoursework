import java.util.ArrayList;
import java.util.List;

public class FolderNode extends FSNode {
    private List<FSNode> children;

    public FolderNode(String name, String path) {
        super(name, 0, path); // Folder size initially set to 0
        this.children = new ArrayList<>();
    }

    public void addChild(FSNode child) {
        children.add(child);
        updatePathForChild(child);
        updateSize();
    }
    
    // Add this method to update the path for child folders
    public void updatePath(String newPath) {
        this.path = newPath;
        for (FSNode child : children) {
            if (child instanceof FolderNode) {
                ((FolderNode) child).updatePath(this.path + "/" + child.getName());
            } else {
                child.path = this.path + "/" + child.getName();
            }
        }
    }
    // Add this method to update the path for child nodes
    private void updatePathForChild(FSNode child) {
        if (child instanceof FolderNode) {
            ((FolderNode) child).updatePath(this.path + "/" + child.getName());
            for (FSNode grandchild : ((FolderNode) child).getChildren()) {
                updatePathForChild(grandchild);
            }
        } else {
            child.path = this.path + "/" + child.getName();
        }
    }

    public void removeChild(FSNode child) {
        children.remove(child);
        updateSize();
    }

    private void updateSize() {
        size = 0;
        for (FSNode child : children) {
            size += child.getSize();
        }
    }

    public List<FSNode> getChildren() {
        return children;
    }

    @Override
    public String toString() {
        return "FolderNode [name=" + getName() + ", size=" + size + ", path=" + path + "]";
    }
}
