package main.global;

public class SharedVariables {
    private static String curPath = "";

    public static String getCurPath(){
        if(curPath.equals("")){
            curPath = System.getProperty("user.dir");
        }
        return curPath;
    }

}
