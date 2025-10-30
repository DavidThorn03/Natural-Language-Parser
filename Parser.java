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

        ArrayList<Word> inputWords = new ArrayList<>();
        for (String word : text.split(" ")) {
            try {
                inputWords.add(Word.getInstance(word));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        ArrayList<Rule> currentRules = new ArrayList<>(rules);
        int currentRuleIndex = 0;
        for (Word word : inputWords) { // for each word in the input
            // if there are no current rules, find new ones that start with the current
            // word's POS, or fail if none found
            if (currentRules.isEmpty()) {
                for (Rule rule : rules) {
                    if (rule.getPosAt(0) == word.getPos()) {
                        currentRules.add(rule);
                    }
                }
                if (currentRules.isEmpty()) {
                    System.out.println("No applicable rules found for word: " + word.getWord());
                    return;
                }
                currentRuleIndex++;
            } else {
                // filter current rules to only those that match the current word's POS at the
                boolean lastInRule = true;
                Iterator<Rule> iterator = currentRules.iterator(); // use iterator to allow removal during iteration

                while (iterator.hasNext()) { // iterate through current rules
                    Rule rule = iterator.next();

                    if (rule.getPosAt(currentRuleIndex) != word.getPos()) { // if POS doesn't match, remove rule
                        iterator.remove();
                    } else { // if it matches, check if we're at the last POS in the rule
                        if (currentRuleIndex < rule.getPoss().size() - 1) {
                            lastInRule = false;
                        }
                    }
                }

                if (currentRules.size() == 0) { // if no rules remain, fail
                    System.out.println("No applicable rules found for word: " + word.getWord());
                    return;
                }
                if (lastInRule) { // if we matched the last POS in all rules, reset for the next word
                    currentRuleIndex = 0;
                    currentRules.clear();
                } else { // otherwise, move to the next POS in the rules
                    currentRuleIndex++;
                }
            }
        }
    }
}
