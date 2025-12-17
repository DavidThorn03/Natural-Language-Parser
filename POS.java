import java.util.ArrayList;

// class to represent a Part of Speech (POS) tag

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

    // creates POS instances from a line of tags read in from rules file
    public static void getPOSs(String line, boolean word) {
        String[] parts = line.split(" ");
        for (String part : parts) {
            POSs.add(new POS(part, word));
        }
    }

    // returns POS instance for a given tag
    // (or exception if not found)
    public static POS getInstance(String tag) throws Exception {
        for (POS pos : POSs) {
            if (pos.tag.equals(tag)) {
                return pos;
            }
        }
        throw new Exception("POS tag not found: " + tag);
    }
}