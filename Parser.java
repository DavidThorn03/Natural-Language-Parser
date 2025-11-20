import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;

public class Parser {
    private ArrayList<Rule> rules;
    private ArrayList<Word> words;

    public Parser(String text) {
        try {
            this.rules = Rule.getRules("rules.txt");
            this.words = Word.getWords("lexicon.txt");
        } catch (Exception e) {
            e.printStackTrace();
        }

        ArrayList<Word> sentence = new ArrayList<>();
        for (String word : text.split(" ")) {
            try {
                sentence.add(Word.getInstance(word));
            } catch (Exception e) {
                System.out.println("Word not found: " + word);
                return;
            }
        }
        try {
            CheckResult result = check(POS.getInstance("S"), sentence, 0);
            if (result.getIndex() == sentence.size()) {
                TreeNode root = result.getNode();
                System.out.println("Parse successful!");
                System.out.println(root.getBracketStructure());
            } else {
                System.out.println("Parse failed.");
            }
            System.out.println(result.getIndex());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public CheckResult check(POS pos, ArrayList<Word> sentence, int index) {
        if (pos.isWord()) {
            if (sentence.get(index).getPosTag().equals(pos.getTag())) {
                return new CheckResult(new TreeNode(sentence.get(index).getWord(), pos), index + 1);
            } else {
                return new CheckResult(null, -1);
            }
        }

        for (Rule rule : rules) {
            if (rule.getRule().getTag().equals(pos.getTag())) {
                TreeNode parent = new TreeNode(pos);
                int currI = index;
                boolean match = true;
                for (int i = 0; i < rule.getPoss().size(); i++) {
                    CheckResult child = check(rule.getPosAt(i), sentence, currI);
                    if (child.getIndex() == -1) {
                        match = false;
                        break;
                    }

                    parent.addChild(child.getNode());
                    currI = child.getIndex();

                }
                if (match) {
                    return new CheckResult(parent, currI);
                }
            }
        }
        return new CheckResult(null, -1);
    }
}
