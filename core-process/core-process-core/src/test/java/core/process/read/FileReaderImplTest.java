package core.process.read;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class FileReaderImplTest {

    private static final String baseDirectoryStr = "src" + File.separator + "test" + File.separator 
            + "resources" + File.separator + "fileReaderTest" + File.separator;
    
    private static final File baseDirectory = new File(baseDirectoryStr); 
    
    private FileReader fileReader;
    
    @Before
    public void setUp() {
        
        baseDirectory.mkdir();
        
        fileReader = new FileReaderImpl(baseDirectoryStr);
    }
    
    @After
    public void tearDown() {
        
        deleteDir(baseDirectory);
    }
    
    @Test
    public void testReadNoFile() {
        File file = fileReader.read();
        
        Assert.assertNull(file);
    }

    @Test
    public void testReadOneFile() throws IOException {
        createFile(1);
        
        File file = fileReader.read();
        
        Assert.assertNotNull(file);
    }
    
    @Test
    public void testReadThreeFiles() throws IOException {
        createFile(3);
        
        File[] files = new File[6];
        files[0] = fileReader.read();
        files[1] = fileReader.read();
        files[2] = fileReader.read();
        files[3] = fileReader.read();
        
        // Make sure we read the correct number of files
        Assert.assertNotNull(files[0]);
        Assert.assertNotNull(files[1]);
        Assert.assertNotNull(files[2]);
        Assert.assertNull(files[3]);
        
        // Make sure we didn't read the same file repeatedly
        Assert.assertTrue(!files[0].equals(files[1]));
        Assert.assertTrue(!files[1].equals(files[2]));
        Assert.assertTrue(!files[2].equals(files[0]));
    }
    
    private static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i=0; i<children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }

        return dir.delete();
    }
    
    private static void createFile(int count) throws IOException {
        for (int i = 0; i < count; i++) {
            System.out.println("Creating file: " + (baseDirectoryStr + i));
            File file = new File(baseDirectoryStr + i);
            file.createNewFile();
        }
    }
}
