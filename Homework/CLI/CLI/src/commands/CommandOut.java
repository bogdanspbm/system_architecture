package commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandOut extends Command {
    public CommandOut(String name) {
        super(name);
    }

    @Override
    public void execute() {
        StringBuffer command = new StringBuffer();
        command.append(name + " ");
        for (int i = 0; i < params.size(); i++) {
            String param = params.get(i);
            command.append(param);
            if (i < params.size() - 1) {
                command.append(" ");
            }
        }
        try {
            Process p = Runtime.getRuntime().exec(command.toString());
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String ret = in.readLine();
            System.out.println(ret);
        } catch (Exception e) {
        }
    }

    @Override
    public int getParamsCount() {
        return -1;
    }
}
