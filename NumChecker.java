public class NumChecker {
    public boolean checkNum(TreeNode node) {
        // check if the NP is consistent

        // if it is, check if the verb matches the NP

        // if both match return true, else false
    }

    public boolean checkNPNum(TreeNode npNode) {
        // get the children of the NP node

        // determine the number of the NP root

        // check if other words match root number

        // if match return true, else false - RETURN THE NUMBER OR -1 SO IT CAN BE
        // PASSED FOR THE VERB CHECK
    }

    public boolean checkVerbNum(TreeNode verbNode, int npNum) {
        // get the verb word

        // determine the number of the verb

        // compare verb number with NP number

        // if match return true, else false
    }

    // also need methods to find the NP and verb nodes in the tree
}
