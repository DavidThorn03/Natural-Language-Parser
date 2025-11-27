import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

// main parser class, uses rules and lexicon to parse input text

public class Parser {
    private ArrayList<Rule> rules;

    // constructor takes in input text to be parsed
    public Parser() {
        // get rules and words from files
        try {
            this.rules = Rule.getRules("rules.txt");
            Word.getWords("lexicon.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void parse(String text) {
        // convert input text to list of Word instances
        ArrayList<Word> sentence = new ArrayList<>();
        for (String word : text.split(" ")) {
            try {
                sentence.add(Word.getInstance(word));
            } catch (Exception e) { // or throw error if word not found
                System.out.println("Word not found: \"" + word + "\"");
                return;
            }
        }

        // parse string
        try {
            // start parsing sentence starting with the sentence pos
            CheckResult result = check(POS.getInstance("S"), sentence, 0);
            TreeNode root = result.getNode(); // get root of parse tree
            if (result.getIndex() == sentence.size()) { // if entire sentence parsed and sentence length is correct
                NumChecker numChecker = new NumChecker();
                // check tree for singular/plural matching
                if (!numChecker.checkNum(root)) {
                    System.out.println("Number agreement error.");
                    return;
                }
                // if match, print success and bracket structure
                System.out.println("Parse successful");
                // print bracket structure and tree
                System.out.println("Bracket Structure:");
                System.out.println(root.getBracketStructure());
                System.out.println("Tree Structure:");
                root.printTree(0);
            } else {
                System.out.println("Parse failed");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // main method to check for correct structure
    // recursivly calls itself to check sub-structures
    // depth first search of rules to find matching structure
    // breaks if rule doesnt match
    public CheckResult check(POS pos, ArrayList<Word> sentence, int index) {
        // if POS is a word, check if current word matches
        if (pos.isWord()) {
            if (sentence.get(index).getPosTag().equals(pos.getTag())) {
                return new CheckResult(new TreeNode(sentence.get(index), pos), index + 1); // if matches return word
                                                                                           // node and increment index
            } else {
                return new CheckResult(new TreeNode(pos), -1); // else return failure
            }
        }

        // if not word, check each possible rule for this subtree
        for (Rule rule : rules) {
            if (rule.getRule().getTag().equals(pos.getTag())) { // if rule matches current POS
                TreeNode parent = new TreeNode(pos);
                int currI = index;
                boolean match = true;
                // check all child POS rules
                for (int i = 0; i < rule.getPoss().size(); i++) {
                    CheckResult child = check(rule.getPosAt(i), sentence, currI);
                    // if any child fails, break and try next rule
                    if (child.getIndex() == -1) {
                        match = false;
                        break;
                    }

                    // else add child node and update current index
                    parent.addChild(child.getNode());
                    currI = child.getIndex();

                }
                // if all children matched, return parent node and updated index
                if (match) {
                    return new CheckResult(parent, currI);
                }
            }
        }
        // if no rules matched, return failure
        return new CheckResult(new TreeNode(pos), -1);
    }
}
