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
    }
}
