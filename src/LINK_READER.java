import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LINK_READER {
    static String line;
    public static String albumLink;
    public static String LINK_READER_RUN() {
        String fileName = "Settings.txt";
        boolean readEnabled = false;
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            while ((line = br.readLine()) != null) {
                if (line.trim().equals("///")) {
                    readEnabled = true;
                } else if (readEnabled) {
                    int index = line.indexOf("/");
                    if (index > 0) {
                        String substring = line.substring(0, index);
                        System.out.println(line);
                        return LINK_READER.line; // Возвращаем найденную ссылку
                    } else {
                        System.out.println("Ошибка в " + line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Если ссылка не найдена, возвращаем null
    }
}


