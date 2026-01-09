package utils;

public class StringUtils {
    private StringUtils (){
        throw new UnsupportedOperationException("Utility Class - Can not be created");
    }

    // 1) checkString
    /**
     * <h1>checkString</h1>
     * Check if a string is null or empty
     * @param s string to test
     * @return boolean
     */
    public static boolean checkString (String s){
        return (s!=null && !s.isEmpty());
    }

}
