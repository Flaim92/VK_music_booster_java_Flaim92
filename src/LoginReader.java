import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginReader {
    public static void LoginReader_Run() {
        String fileName = "Settings.txt";
        int lineCount = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.equals("///")) {
                    break;
                }
                lineCount++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Find accounts: " + lineCount);
    }
}

