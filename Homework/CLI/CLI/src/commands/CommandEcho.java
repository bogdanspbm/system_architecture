package commands;

public class CommandEcho extends Command {
    @Override
    public void execute() {
        StringBuffer result = new StringBuffer();
        for (int i = 0; i < params.size(); i++) {
            String param = params.get(i);
            result.append(param);
            if (i < params.size() - 1) {
                result.append(" ");
            }
        }
        System.out.println(result.toString());
    }

    @Override
    public int getParamsCount() {
        return -1;
    }
}
