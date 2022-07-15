import controller.HttpController;
import controller.LogController;
import model.InputParameters;
import model.InputParametersDataUnix;
import model.InputParametersDataWin;
import model.OS;

import java.util.logging.Logger;

public class DocGenExe {

    private static final Logger LOGGER = Logger.getLogger(LogController.class.getName());
    private static InputParameters inputParameters;

    public static void main(String[] args) {
        LogController.setLogFile(args);
        InputParameters inputParameters = setInputParameters(args);


        HttpController.SendInputData(inputParameters);


    }

    public static InputParameters setInputParameters(String[] args) {
        if (OS.IS_WINDOWS) {
            InputParametersDataWin inputParametersDataWin = new InputParametersDataWin(args);
            inputParameters = new InputParameters(inputParametersDataWin);
            System.out.println(inputParameters);
        } else if (OS.IS_UNIX) {
            InputParametersDataUnix inputParametersDataUnix = new InputParametersDataUnix(args);
            inputParameters = new InputParameters(inputParametersDataUnix);
            System.out.println(inputParameters);
        } else {
            LOGGER.warning("Unknown platfrom");
            System.exit(1);
        }
        return inputParameters;
    }



}
