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
            System.out.println(check(POS.getInstance("S"), sentence, 0));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int check(POS pos, ArrayList<Word> sentence, int index) {
        if (pos.isWord()) {
            if (sentence.get(index).getPosTag().equals(pos.getTag())) {
                return index + 1;
            } else {
                return -1;
            }
        }

        for (Rule rule : rules) {
            if (rule.getRule().getTag().equals(pos.getTag())) {
                int currI = index;
                boolean match = true;
                for (int i = 0; i < rule.getPoss().size(); i++) {
                    int newI = check(rule.getPosAt(i), sentence, currI);
                    if (newI == -1) {
                        match = false;
                        break;
                    }
                    currI = newI;

                }
                if (match) {
                    return currI;
                }
            }
        }
        return -1;
    }
}
