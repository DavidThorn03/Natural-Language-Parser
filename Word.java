import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

public class Word {
    private String word;
    private POS pos;
    private boolean root;
    private int num;
    private static ArrayList<Word> words = new ArrayList<>();

    public Word(String word, POS pos, boolean root, int num) {
        this.word = word;
        this.pos = pos;
        this.root = root;
        this.num = num;
    }

    public String getWord() {
        return word;
    }

    public POS getPos() {
        return pos;
    }

    public String getPosTag() {
        return pos.getTag();
    }

    public boolean isRoot() {
        return root;
    }

    public int getNum() {
        return num;
    }

    public static ArrayList<Word> getWords(String filename) throws Exception {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        String line;
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" ");
            String wordStr = parts[0].trim();
            POS pos = POS.getInstance(parts[1].trim());
            boolean root = parts.length > 2 && parts[2].trim().equals("y");
            int num = parts.length > 3 ? Integer.parseInt(parts[3].trim()) : 0;
            words.add(new Word(wordStr, pos, root, num));
        }
        br.close();
        return words;
    }

    public static Word getInstance(String wordStr) throws Exception {
        for (Word word : words) {
            if (word.word.equals(wordStr)) {
                return word;
            }
        }
        throw new Exception("Word not found: " + wordStr);
    }
}
