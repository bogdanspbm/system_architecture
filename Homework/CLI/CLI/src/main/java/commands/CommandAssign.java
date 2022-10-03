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
        String[] words = this.name.split("=");
        String value = "";

        key = words[0];

        for(int i = 1; i < words.length;i++){
            value += words[i];
        }

        return value;
    }

    @Override
    public int getParamsCount() {
        return 0;
    }
}
