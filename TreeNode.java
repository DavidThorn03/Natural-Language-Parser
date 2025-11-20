import java.util.ArrayList;

public class TreeNode {
    private POS node;
    private ArrayList<TreeNode> children;

    public TreeNode(POS node) {
        this.node = node;
        this.children = new ArrayList<>();
    }

    public POS getNode() {
        return node;
    }

    public ArrayList<TreeNode> getChildren() {
        return children;
    }

    public void addChild(TreeNode child) {
        children.add(child);
    }
}
