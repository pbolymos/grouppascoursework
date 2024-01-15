public class FileNode extends FSNode {
    public FileNode(String name, int size, String parentPath) {
        super(name, size, parentPath + "/" + name);
    }

    @Override
    public String toString() {
        return "FileNode [name=" + getName() + ", size=" + size + ", path=" + path + "]";
    }
}