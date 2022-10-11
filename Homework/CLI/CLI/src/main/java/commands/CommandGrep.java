package commands;

import global.GlobalFunctions;
import utils.STDIn;
import utils.Stack;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static utils.Stack.getStack;

public class CommandGrep extends Command {

    public CommandGrep(String name) {
        super(name);
    }

    @Override
    void hiddenExecute() {
        print(buildOutput());
    }

    @Override
    public String buildOutput() {
        String result = "";

        if(checkHasBadOption()){
            return "";
        }

        List<Integer> linesInicies = new ArrayList<>();

        setSpecificWord();

        if (params.size() == 2) {
            File file = new File(params.get(1));
            if (file.exists()) {
                List<String> lines = GlobalFunctions.getFileContent(file);
                for (int i = 0; i < lines.size(); i++) {
                    String line = lines.get(i);
                    String tmpLine = setIgnoreRegister(line);

                    Pattern p = Pattern.compile(params.get(0));
                    Matcher m = p.matcher(tmpLine);
                    if (m.find()) {

                        int afterContextCount = getAfterContextLines();

                        if (afterContextCount == -1) {
                            return "Invalid argument";
                        }

                        for (int k = 0; k <= afterContextCount; k++) {
                            if (!linesInicies.contains(i + k)) {
                                linesInicies.add(i + k);
                            }
                        }
                    }
                }

                for (int i : linesInicies) {
                    if (i < lines.size()) {
                        result += lines.get(i) + "\n";
                    }
                }

            } else {
                return "File doesn't exist";
            }
        }
        return result;
    }

    private boolean checkHasBadOption() {
        for (String option : options) {
            if (option.startsWith("-")) {
                if (!optionMap.containsKey(option)) {
                        return true;
                }
            }
        }

        return false;
    }


    private int getAfterContextLines() {
        if (!options.contains("-A")) {
            return 0;
        } else {
            int countIndex = options.indexOf("-A") + 1;
            if (countIndex < options.size()) {
                String countStr = options.get(countIndex);
                try {
                    int count = Integer.parseInt(countStr);
                    return count;
                } catch (Exception e) {
                    return -1;
                }
            }
        }

        return -1;
    }

    private String setIgnoreRegister(String line) {
        if (options.contains("-i")) {
            params.set(0, params.get(0).toLowerCase());
            return line.toLowerCase();
        }

        return line;
    }

    private void setSpecificWord() {
        if (options.contains("-w")) {
            params.set(0, "\\b" + params.get(0) + "\\b");
        }
    }

    @Override
    protected void generateOptionsMap() {
        optionMap = new HashMap<>();

        optionMap.put("-w", 0);
        optionMap.put("-i", 0);
        optionMap.put("-A", 1);
    }

    @Override
    public int getParamsCount() {
        return 2;
    }

}
