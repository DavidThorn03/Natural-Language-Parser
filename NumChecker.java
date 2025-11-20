import java.util.ArrayList;

public class NumChecker {
    public boolean checkNum(TreeNode node) {
        // check if the NP is consistent
        int npNum = checkNPNum(findNPNode(node));
        if (npNum == -1) {
            return false;
        }

        // if it is, check if the verb matches the NP
        return checkVerbNum(findVerbNode(node), npNum);
    }

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

        if (num == -1) {
            return -1;
        }
        // check if other words match root number
        for (TreeNode child : children) {
            if (child.getWord() != null && !child.getWord().isRoot()) {
                if (child.getWord().getNum() != num && child.getWord().getNum() != 0) {
                    return -1;
                }
            }
        }
        // if match return num, else -1
        return num;
    }

    public boolean checkVerbNum(TreeNode verbNode, int npNum) {
        // get the verb word
        Word verbWord = verbNode.getWord();
        if (verbWord == null) {
            return false;
        }
        // determine the number of the verb
        int verbNum = verbWord.getNum();

        // compare verb number with NP number
        return verbNum == npNum;
    }

    public TreeNode findNPNode(TreeNode node) {
        if (node.getNode().getTag().equals("NP-SUB")) {
            return node;
        }
        for (TreeNode child : node.getChildren()) {
            TreeNode result = findNPNode(child);
            if (result != null) {
                return result;
            }
        }
        return null;
    }

    public TreeNode findVerbNode(TreeNode node) {
        if (node.getNode().getTag().equals("VB")) {
            return node;
        }
        for (TreeNode child : node.getChildren()) {
            TreeNode result = findVerbNode(child);
            if (result != null) {
                return result;
            }
        }
        return null;
    }
}
