package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;

@NoArgsConstructor
@Getter
@Setter
public class InputParameters {

    private String storedProcedure;
    private boolean append;
    private String locationOutputTmpFile;
    private String locationOutputLisFile;
    private String locationLogFile;
    private ArrayList<String> inputParameterList;
    private ArrayList<String> parameterList;

    private String databaseName;
    private String userNameAnduserPasword;

    private String userName;
    private String userPassword;


    public InputParameters(InputParametersDataWin inputParametersDataWin) {
        this.storedProcedure = inputParametersDataWin.getStoredProcedure();
        this.userName = inputParametersDataWin.getUserName();
        this.userPassword = inputParametersDataWin.getUserPassword();
        this.locationOutputLisFile = inputParametersDataWin.getLocationOutputLisFile();
        this.locationLogFile = inputParametersDataWin.getLocationLogFile();
        this.databaseName = inputParametersDataWin.getDatabase();
        this.parameterList = inputParametersDataWin.getParameterList();
    }

    public InputParameters(InputParametersDataUnix inputParametersDataUnix) {
        this.storedProcedure = inputParametersDataUnix.getStoredProcedure();
        this.userName = inputParametersDataUnix.getUserName();
        this.userPassword = inputParametersDataUnix.getUserPassword();
        this.locationOutputLisFile = inputParametersDataUnix.getLocationOutputLisFile();
        this.locationLogFile = inputParametersDataUnix.getLocationLogFile();
        this.databaseName = inputParametersDataUnix.getDatabase();
        this.parameterList = inputParametersDataUnix.getParameterList();
    }


}
