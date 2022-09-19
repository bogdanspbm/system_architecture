package main;

import global.SharedVariables;
import utils.Reader;

public class Main {
    public static void main(String[] args) {
        SharedVariables.curPath = System.getProperty("user.dir");
        Reader reader = new Reader();
        reader.start();
    }
}
