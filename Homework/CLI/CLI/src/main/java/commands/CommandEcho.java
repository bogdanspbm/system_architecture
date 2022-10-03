package commands;

public class CommandEcho extends Command {
    public CommandEcho(String name) {
        super(name);
    }

    @Override
    public void hiddenExecute() {
        System.out.println(buildOutput());
    }

    @Override
    public String buildOutput() {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < params.size(); i++) {
            String param = params.get(i);
            result.append(param);
            if (i < params.size() - 1) {
                result.append(" ");
            }
        }
        return result.toString();
    }

    @Override
    public int getParamsCount() {
        return -1;
    }
}
