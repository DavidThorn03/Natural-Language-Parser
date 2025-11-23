// class to hold results from recursive checks
// holds the current word index and current sentence subtree root associated with current node

public class CheckResult {
    private TreeNode node;
    private int index;

    public CheckResult(TreeNode node, int index) {
        this.node = node;
        this.index = index;
    }

    public TreeNode getNode() {
        return node;
    }

    public int getIndex() {
        return index;
    }

}
