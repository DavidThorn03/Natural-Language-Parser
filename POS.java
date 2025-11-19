import java.util.ArrayList;

public class POS {
    private String tag;
    private static ArrayList<POS> POSs = new ArrayList<>();
    private boolean isWord;

    private POS(String tag, boolean isWord) {
        this.tag = tag;
        this.isWord = isWord;
    }

    public String getTag() {
        return tag;
    }

    public boolean isWord() {
        return isWord;
    }

    public static void getPOSs(String line, boolean word) {
        String[] parts = line.split(" ");
        for (String part : parts) {
            POSs.add(new POS(part, word));
        }
    }

    public static POS getInstance(String tag) throws Exception {
        for (POS pos : POSs) {
            if (pos.tag.equals(tag)) {
                return pos;
            }
        }
        throw new Exception("POS tag not found: " + tag);
    }
}