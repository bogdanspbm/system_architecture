package commands;

import global.GlobalFunctions;
import utils.VariableStorage;

public class CommandAssign extends Command {

    String key = "";

    public CommandAssign(String name) {
        super(name);
    }

    @Override
    public void hiddenExecute() {
        String value = buildOutput();
        VariableStorage.getStorage().put(key, value);
    }

    @Override
    public String buildOutput() {

        boolean flag = false;

        StringBuilder keyBuilder = new StringBuilder();
        StringBuilder valueBuilder = new StringBuilder();

        for (char ch : name.toCharArray()) {
            if (ch == '=' && !flag) {
                flag = true;
            } else if (!flag) {
                keyBuilder.append(ch);
            } else {
                valueBuilder.append(ch);
            }
        }

        key = keyBuilder.toString();

        return valueBuilder.toString();
    }

    @Override
    public int getParamsCount() {
        return 0;
    }
}
