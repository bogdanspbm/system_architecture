package global;

public class SharedVariables {
    private static String curPath = "";

    // Возвращает текущую директорию
    // Если путь пустой, устанавливает его автоматически
    // Заложенно для управления директорией в будущем
    public static String getCurPath(){
        if(curPath.equals("")){
            curPath = System.getProperty("user.dir");
        }
        return curPath;
    }

}
