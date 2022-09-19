package utils;

import java.util.Scanner;

public class Reader {

    Scanner scan = new Scanner(System.in);
    String lastLine = "";

    public Reader() {

    }

    public void start() {
        while (scan.hasNextLine()) {
            lastLine = scan.nextLine();
            System.out.println(lastLine);
        }
    }


}
