import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;

// class to represent a grammar rule

public class Rule {
    private POS rule;
    private ArrayList<POS> poss;

    public Rule(POS rule, ArrayList<POS> poss) {
        this.rule = rule;
        this.poss = poss;
    }

    public POS getRule() {
        return rule;
    }

    public ArrayList<POS> getPoss() {
        return poss;
    }

    public POS getPosAt(int index) {
        return poss.get(index);
    }

    // reads rules from rules file
    public static ArrayList<Rule> getRules(String filename) throws Exception {
        FileReader fr = new FileReader(filename);
        BufferedReader br = new BufferedReader(fr);
        ArrayList<Rule> rules = new ArrayList<>();
        POS.getPOSs(br.readLine(), false); // read non-word POS tags
        POS.getPOSs(br.readLine(), true); // read word POS tags
        String line;
        // read each rule line
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(" -> ");
            POS rule = POS.getInstance(parts[0].trim());
            ArrayList<POS> poss = new ArrayList<>();
            for (String pos : parts[1].split(" ")) {
                poss.add(POS.getInstance(pos));
            }
            rules.add(new Rule(rule, poss));
        }
        br.close();
        return rules;
    }
}
