import java.io.BufferedReader;
import java.io.FileReader;

public class Test {
    public static void main(String[] args) {
        System.out.println("Starting Parser Test");
        System.out.println("--------------------");
        System.out.println("Test 1: Parsing sentence 'A king likes the new cat' should succeed.");
        Parser parser = new Parser("A king likes the new cat");
        System.out.println("Test 2: Parsing sentence 'The quick brown fox jumps' should fail.");
        parser = new Parser("The quick brown fox jumps");
        System.out.println("Test 3: Parsing sentence 'A new cat likes the king' should fail.");
        parser = new Parser("A new cat likes the king");
        System.out.println("Test 4: Parsing sentence 'The king likes the cat' should fail.");
        parser = new Parser("The king likes the cat");
        System.out.println("Test 5: Parsing sentence 'A cat likes the new king' should fail.");
        parser = new Parser("A cat likes the new king");

        System.out.println();
        System.out.println("--------------------");
        System.out.println();
        System.out.println("Now test all possible combinations of singular/plural.");
        try {
            FileReader fr = new FileReader("regular_expressions.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println("Testing sentence: " + line);
                parser = new Parser(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
