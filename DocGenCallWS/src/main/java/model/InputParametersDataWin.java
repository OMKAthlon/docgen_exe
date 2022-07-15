package model;

import controller.LogController;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Logger;

@Getter
@Setter
@ToString
public class InputParametersDataWin {

    Logger logger = Logger.getLogger(LogController.class.getName());

    private String storedProcedure;
    private String userName;
    private String userPassword;
    private String locationOutputLisFile;
    private String locationLogFile;
    private ArrayList<String> parameterList;
    private boolean append;
    private boolean nolis;

    public InputParametersDataWin(String [] args) {
        this.storedProcedure = storedProcedure(args);
        this.locationLogFile = locationLogFile(args);
        this.userName = userName(args);
        this.userPassword = userPassword(args);
        this.locationOutputLisFile = locationOutputLisFile(args);
        this.parameterList = parameterList(args);
        this.append = appendNoappend(args);
        this.nolis = lisOrNolis(args);
    }

    public String storedProcedure(String[] args) {
        String storedProcedure = "";
        for (String arg : args) {
            if (arg.contains("sqr\\")) {
                storedProcedure = arg.replace("sqr\\", "").replace("-", "_");
            }
        }
//        String storedProcedure = args[0].replace("\\", " ").replace("-", "_");
//        String [] split = storedProcedure.split(" ");
//        return split[split.length - 1].toLowerCase();

        return storedProcedure.toLowerCase();
    }

    public String locationLogFile (String [] args) {
//        String storedProcedure = args[0].replace("\\", " ").replace("-", "_");
//        String [] split = storedProcedure.split(" ");
        return System.getenv("ATLASLOG") + "\\" + storedProcedure(args) + ".log";
    }

    public String userName (String[] args) {

        String[] split = args[1].split("/");
        return split[0];
    }

    public String userPassword (String[] args) {
        String[] split = args[1].split("/");
        return split[1];
    }

    public String locationOutputLisFile (String[] args) {
        String locationOutputFile = "";
        for (String arg : args) {
            if (arg.startsWith("-F")) {
                locationOutputFile = arg.toLowerCase().replace("-f","");
            }
        }
        return locationOutputFile;
    }

    public String databaseName (String[] args) {
        return args[3];
    }

    public ArrayList<String> parameterList (String [] args) {
        parameterList = new ArrayList<>();
//        for (String arg : args) {
//            if (arg.startsWith("@")) {
//                File file = new File(args[4].replace("@", ""));
//                try {
//                    Scanner readFile = new Scanner(file);
//                    while (readFile.hasNext()) {
//                        String parameter = readFile.nextLine();
//                        parameterList.add(parameter);
//                    }
//                } catch (Exception e) {
//                    logger.warning("File is empty: " + e.getMessage() + " or system cannot open " + e.getMessage());
//                    System.exit(1);
//                }
//            } else {
//                if (args.length == 4) {
//                    parameterList.add(null);
//                } else {
//                    parameterList.addAll(Arrays.asList(args).subList(4, args.length));
//                }
//            }
//        }
        if (args.length == 4) {
            parameterList.add("");
        } else {
//            if (!append || !nolis) {
                if (args[4].startsWith("@")) {
                    File file = new File(args[4].replace("@", ""));
                    try {
                        Scanner readFile = new Scanner(file);
                        while (readFile.hasNext()) {
                            String parameter = readFile.nextLine();
                            parameterList.add(parameter);
                        }
                    } catch (Exception e) {
                        logger.warning("File is empty: " + e.getMessage() + " or system cannot open " + e.getMessage());
                        System.exit(1);
                    }
                } else {
                    parameterList.addAll(Arrays.asList(args).subList(4, args.length));
                }
            }
//        }
        return parameterList;
    }

    public boolean appendNoappend (String [] args) {
        for (String arg : args) {
            if (arg.startsWith("-A")) {
               return true;
            }
        }
        return false;
    }

    public boolean lisOrNolis (String [] args) {
        for (String arg : args) {
            if (arg.equals("-NOLIS")) {
                return true;
            }
        }
        return false;
    }


}
