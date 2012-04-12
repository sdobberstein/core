package core.thruster.utils;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class FileToString {

    /**
     * Description of the Method
     * 
     * @param file
     *            The file to be turned into a String
     * @return The file as String encoded in the platform default encoding
     */
    public static String fileToString(File file) {
        
        String result = null;
        DataInputStream in = null;

        try {
            byte[] buffer = new byte[(int) file.length()];
            in = new DataInputStream(new FileInputStream(file));
            in.readFully(buffer);
            result = new String(buffer);
        } catch (IOException e) {
            throw new RuntimeException("IO problem in fileToString", e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {}
        }
        return result;
    }
}
