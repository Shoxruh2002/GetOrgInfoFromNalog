package uz.atm.utils;

public class AppUtils {
    public static void sleepThread(int timeMilliSecunt) {
        try {
            Thread.sleep(timeMilliSecunt);
        } catch (InterruptedException e) {
            System.err.println(e.getMessage());
        }
    }
}
