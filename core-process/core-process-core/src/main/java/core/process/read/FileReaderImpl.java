package core.process.read;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileReaderImpl implements FileReader {
    
    private FileReaderConfiguration configuration;
    private File baseDirectory = null;
    private List<File> pendingFiles;
    
    public FileReaderImpl(String baseDirectory) {
        this.configuration = new SimpleFileReaderConfiguration(baseDirectory);
        pendingFiles = new ArrayList<File>();
    }
    
    public FileReaderImpl(FileReaderConfiguration configuration) {
        this.configuration = configuration;
        pendingFiles = new ArrayList<File>();
    }
    
    private void initialize() {
        if (configuration.getBaseDirectory() == null) {
            throw new RuntimeException("No base directory set");
        }
        
        baseDirectory = new File(configuration.getBaseDirectory());
    }
    
    @Override
    public File read() {
        if (baseDirectory == null) {
            initialize();
        }
        
        if (pendingFiles.isEmpty()) {            
            if (!readBatchOfFiles(baseDirectory)) {
                // No files were loaded, return null
                return null;
            }
        }
        
        File file = pendingFiles.remove(0);
        
        if (file.isDirectory()) {
            if (configuration.isRecursiveReader()) {
                // Recursive reader, load the files out of this directory
                readBatchOfFiles(file);
            }
            
            return read();
        }
        
        return file;
    }
    
    private boolean readBatchOfFiles(File baseFile) {
        File[] files = baseFile.listFiles(configuration.getFileFilter());
        
        if (files == null) {
            return false;
        } else {
            this.pendingFiles.addAll(Arrays.asList(files));
            return true;
        }
    }
}
