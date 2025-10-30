import java.util.ArrayList;

public class POS {
    private String tag;
    private static ArrayList<POS> POSs = new ArrayList<>();

    private POS(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public static void getPOSs(String line) {
        String[] parts = line.split(" ");
        for (String part : parts) {
            POSs.add(new POS(part));
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