package model;

import controller.LogController;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.logging.Logger;

@ToString
@Setter
@Getter
public class InputParametersDataUnix {

    Logger logger = Logger.getLogger(LogController.class.getName());

    private String storedProcedure;
    private String userName;
    private String userPassword;
    private String locationOutputLisFile;
    private String locationLogFile;
    private String database;
    private ArrayList<String> parameterList;
    private ArrayList<String> inputParameterList;

    public InputParametersDataUnix(String[] args) {
        this.storedProcedure = storedProcedure(args);
        this.locationLogFile = locationLogFile(args);
        this.userName = userName(args);
        this.userPassword = userPassword(args);
        this.locationOutputLisFile = locationOutputLisFile(args);
        this.database = databaseName(args);
        this.parameterList = parameterList(args);
    }

    public String storedProcedure(String[] args) {
        return args[0].replace("-", "_").toLowerCase();
    }

    public String locationLogFile(String[] args) {
        return args[3].toLowerCase().replace("-e", "");
    }

    public ArrayList<String> inputParameterList(String[] args) {
        ArrayList<String> inputParameterList = new ArrayList<>();
        File file = new File(args[4].replace("@", ""));
        try {
            Scanner readFile = new Scanner(file);
            while (readFile.hasNext()) {
                String parameter = readFile.nextLine();
                inputParameterList.add(parameter);
            }
        } catch (Exception e) {
            logger.warning("File is empty: " + e.getMessage() + " or system cannot open " + e.getMessage());
            System.exit(1);
        }
        return inputParameterList;
    }

    public String userName(String[] args) {
        String[] split = inputParameterList(args).get(0).split("/");
        return split[0];
    }

    public String userPassword(String[] args) {
        String[] split = inputParameterList(args).get(0).split("/");
        return split[1];
    }

    public String locationOutputLisFile(String[] args) {
        return args[2].toLowerCase().replace("-f", "");
    }

    public String databaseName(String[] args) {
        return inputParameterList(args).get(1);
    }

    public ArrayList<String> parameterList(String[] args) {
        parameterList = new ArrayList<>();
        int size = inputParameterList(args).size();
        if (size == 2) {
            parameterList.add("");
        } else {
            parameterList.addAll(inputParameterList(args).subList(2, size));
        }
        return parameterList;
    }

}
