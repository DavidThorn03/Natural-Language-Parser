import java.util.ArrayList;

public class TreeNode {
    private POS node;
    private ArrayList<TreeNode> children;
    private Word word;

    public TreeNode(POS node) {
        this.node = node;
        this.children = new ArrayList<>();
        this.word = null;
    }

    public TreeNode(Word word, POS node) {
        this.node = node;
        this.children = new ArrayList<>();
        this.word = word;
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

    public Word getWord() {
        return word;
    }

    public String getBracketStructure() {
        if (children.isEmpty()) {
            return "[" + node.getTag() + "[" + word.getWord() + "]" + "]";
        } else {
            StringBuilder sb = new StringBuilder();
            sb.append("[").append(node.getTag());
            for (TreeNode child : children) {
                sb.append(child.getBracketStructure());
            }
            sb.append("]");
            return sb.toString();
        }
    }
}
