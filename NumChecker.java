import java.util.ArrayList;

// Class to check if singular/plural usage stays consistent

public class NumChecker {
    // check if singular/plural usage is consistent between subject and verb
    public boolean checkNum(TreeNode node) {
        // check if the NP is consistent
        int npNum = checkNPNum(findNPNode(node));
        if (npNum == -1) {
            return false;
        }

        // if it is, check if the verb matches the NP
        return checkVerbNum(findVerbNode(node), npNum);
    }

    // check if the Noun Phrase is consistent
    public int checkNPNum(TreeNode npNode) {
        // get the children of the NP node
        ArrayList<TreeNode> children = npNode.getChildren();
        int num = -1;

        // determine the number of the NP root
        for (TreeNode child : children) {
            if (child.getWord() != null && child.getWord().isRoot()) {
                num = child.getWord().getNum();
                break;
            }
        }
        // if no root found return -1
        if (num == -1) {
            return -1;
        }
        // check if other words match root number
        for (TreeNode child : children) {
            if (child.getWord() != null && !child.getWord().isRoot()) { // if node is a word and isnt root
                if (child.getWord().getNum() != num && child.getWord().getNum() != 0) {
                    // if any dont match return -1
                    return -1;
                }
            }
        }
        // if all match return num
        return num;
    }

    // check if verb matches the noun phrase
    public boolean checkVerbNum(TreeNode verbNode, int npNum) {
        // get the verb word
        Word verbWord = verbNode.getWord();
        if (verbWord == null) {
            return false;
        }
        // get the number of the verb
        int verbNum = verbWord.getNum();

        // compare verb number with NP number
        return verbNum == npNum;
    }

    // find the subject noun phrase associated with the verb
    public TreeNode findNPNode(TreeNode node) {
        // if current node matches, return it
        if (node.getNode().getTag().equals("NP-SUB")) {
            return node;
        }
        // else search children
        for (TreeNode child : node.getChildren()) {
            TreeNode result = findNPNode(child);
            if (result != null) { // if child node matches, return it
                return result;
            }
        }
        return null; // otherwise return null
    }

    // find the main verb
    public TreeNode findVerbNode(TreeNode node) {
        // if current node matches, return it
        if (node.getNode().getTag().equals("VB")) {
            return node;
        }
        // else search children
        for (TreeNode child : node.getChildren()) {
            TreeNode result = findVerbNode(child);
            if (result != null) { // if child node matches, return it
                return result;
            }
        }
        return null; // otherwise return null
    }
}
