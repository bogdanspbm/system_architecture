package main.commands;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class CommandOut extends Command {
    public CommandOut(String name) {
        super(name);
    }

    @Override
    public void execute() {
        System.out.println(buildOutput());
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
            Process p = Runtime.getRuntime().exec(command.toString());
            BufferedReader in = new BufferedReader(new InputStreamReader(p.getInputStream()));
            String ret = in.readLine();
            return ret;
        } catch (Exception e) {
        }
        return "";
    }


    @Override
    public int getParamsCount() {
        return -1;
    }
}
