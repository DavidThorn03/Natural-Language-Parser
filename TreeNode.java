import java.util.ArrayList;

// class to hold results from recursive checks in tree structure

public class TreeNode {
    private POS node;
    private ArrayList<TreeNode> children;
    private Word word;

    // constructor for non word/leaf nodes
    public TreeNode(POS node) {
        this.node = node;
        this.children = new ArrayList<>();
        this.word = null;
    }

    // constructor for word/leaf nodes
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

    // returns bracket structure representation of the tree
    public String getBracketStructure() {
        if (children.isEmpty()) { // if leaf node
            return "[" + node.getTag() + "[" + word.getWord() + "]" + "]"; // return [POS[word]]
        } else { // else non-leaf node
            StringBuilder sb = new StringBuilder();
            sb.append("[").append(node.getTag()); // start with [POS
            for (TreeNode child : children) { // get all children bracket structures
                sb.append(child.getBracketStructure());
            }
            sb.append("]"); // close the POS bracket
            return sb.toString();
        }
    }

    // prints the tree structure in a readable format
    public void printTree(int level) {
        // print indentation based on level
        for (int i = 0; i < level; i++) {
            System.out.print("   ");
        }
        // print current node
        if (word != null) {
            System.out.println("|--" + node.getTag() + ": " + word.getWord());
        } else {
            System.out.println("|--" + node.getTag());
        }
        // recursively print children
        for (TreeNode child : children) {
            child.printTree(level + 1);
        }

    }
}
