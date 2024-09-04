package Hogwarts.Utils;

public class ConsoleUtils {

    public static final String RESET = "\u001B[0m";
    public static final String CYAN = "\u001B[36m";
    public static final String GREEN = "\u001B[32m";
    public static final String RED = "\u001B[31m";
    public static final String YELLOW = "\u001B[33m";
    public static final String BLUE = "\u001B[34m";
    public static final String PURPLE = "\u001B[35m";
    public static final String WHITE = "\u001B[37m";
    public static final String BOLD = "\u001B[1m";


    public static void printTitle() {

        String title = "  _          _     _      _       _              _        _   _                                _       \n" +
                " | |    __ _| |__ (_)_ __(_)_ __ | |_ ___     __| | ___  | | | | ___   __ ___      ____ _ _ __| |_ ___ \n" +
                " | |   / _` | '_ \\| | '__| | '_ \\| __/ _ \\   / _` |/ _ \\ | |_| |/ _ \\ / _` \\ \\ /\\ / / _` | '__| __/ __|\n" +
                " | |__| (_| | |_) | | |  | | | | | || (_) | | (_| |  __/ |  _  | (_) | (_| |\\ V  V / (_| | |  | |_\\__ \\\n" +
                " |_____\\__,_|_.__/|_|_|  |_|_| |_|\\__\\___/   \\__,_|\\___| |_| |_|\\___/ \\__, | \\_/\\_/ \\__,_|_|   \\__|___/\n" +
                "                                                                      |___/                            ";

        System.out.println(title);
        System.out.println("==========================================================================================================");
    }

    public static void typeWriter(String text, int delay) {
        for (char c : text.toCharArray()) {
            System.out.print(c);
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println();
    }

}
