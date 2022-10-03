package commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandOut extends Command {
    public CommandOut(String name) {
        super(name);
    }

    @Override
    public void hiddenExecute() {
        print(buildOutput());
    }

    @Override
    public String buildOutput() {
        StringBuffer command = new StringBuffer();
        command.append(name);
        command.append(" ");
        for (int i = 0; i < params.size(); i++) {
            String param = params.get(i);
            command.append(param);
            if (i < params.size() - 1) {
                command.append(" ");
            }
        }


        try {
            StringBuilder result = new StringBuilder();
            Process p = Runtime.getRuntime().exec(command.toString());
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            for (String line : in.lines().toList()) {
                result.append(line);
                result.append("\n");
            }
            return result.toString();
        } catch (Exception e) {
            return "Неизвестная команда: " + name;
            //return e.toString();
        }
    }


    @Override
    public int getParamsCount() {
        return -1;
    }
}
