

package controller;

/**
 * @author olaf.vander.kaaij@cgi.com, commissioned by Athlon
 */

import model.OS;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

public class LogController {

    private final Logger logger = Logger.getLogger(LogController.class.getName());

    /**
     * Method to initialize the logfile with version information.
     *
     * @param args Inputparamaters from iProcess or command line arguments.
     */

    public static void setLogFile(String[] args) {
        Logger logger = Logger.getLogger(LogController.class.getName());
        logger.setUseParentHandlers(false);
        FileHandler fileHandler;
        try {
            fileHandler = new FileHandler(setLogFileLocation(args));
            logger.addHandler(fileHandler);
            SimpleFormatter simpleFormatter = new SimpleFormatter();
            fileHandler.setFormatter(simpleFormatter);
            logger.info("\n\n****************** VERSION DOCGEN: 2022.0.0.1 ******************\n");
        } catch (SecurityException | IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to set the location of the log file.
     *
     * @param args Inputparamaters from iProcess or command line arguments.
     * @return location of logfile based on platform.
     */

    public static String setLogFileLocation(String[] args) {
        String logFile;
        if (OS.IS_WINDOWS) {
            String storedProcedure = args[0].replace("\\", " ").replace("-", "_");
            String[] split = storedProcedure.split(" ");
            logFile = System.getenv("ATLASLOG") + "\\" + split[split.length - 1].toLowerCase() + ".log";
        } else {
            logFile = args[3].toLowerCase().replace("-e", "");
        }
        return logFile;
    }
}
