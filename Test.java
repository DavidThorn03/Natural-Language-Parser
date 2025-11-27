import java.io.BufferedReader;
import java.io.FileReader;

// test class
// checks some strings to check parser functionality, and all combinations of singular/plural sentences from a file to test number agreement

public class Test {
    public static void main(String[] args) {
        Parser parser = new Parser();
        System.out.println("Starting Parser Test");
        System.out.println("--------------------");
        System.out.println("Test 1: Parsing sentence 'A king likes the new cat' should succeed.");
        parser.parse("A king likes the new cat");
        System.out.println();
        System.out.println("Test 2: Parsing sentence 'The quick brown fox jumps' should fail.");
        parser.parse("The quick brown fox jumps");
        System.out.println();
        System.out.println("Test 3: Parsing sentence 'A new cat likes the king' should fail.");
        parser.parse("A new cat likes the king");
        System.out.println();
        System.out.println("Test 4: Parsing sentence 'The king likes the cat' should fail.");
        parser.parse("The king likes the cat");
        System.out.println();
        System.out.println("Test 5: Parsing sentence 'A cat likes the new king' should fail.");
        parser.parse("A cat likes the new king");
        System.out.println();

        System.out.println();
        System.out.println("--------------------");
        System.out.println();
        System.out.println("Now test all possible combinations of singular/plural.");
        // reads sentences from regular_expressions.txt and tests each one
        try {
            FileReader fr = new FileReader("regular_expressions.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Testing sentence: " + line);
                parser.parse(line);
                System.out.println();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
