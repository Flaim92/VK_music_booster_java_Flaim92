import java.io.IOException;



public class ProcessKiller {

    public static void killProcess(String processName) throws IOException {
        Runtime runtime = Runtime.getRuntime();
        runtime.exec("taskkill /F /IM " + processName);



    }

}