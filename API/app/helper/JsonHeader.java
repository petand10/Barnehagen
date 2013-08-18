package helper;

public class JsonHeader {

    public Integer errorCode;
    public String error;

    public JsonHeader(){
        errorCode = 0;
        error = getError(errorCode);
    }

    public static String getError(Integer code){
        return errors[code];
    }

    private static String[] errors = {
        "No error",
        "Unable to find children",
        "Functionality unavailable",
        "Invalid JSON input",
        "Authentication failure"
    };
}
