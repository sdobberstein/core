package core.process.read;

import java.io.FileFilter;

public class SimpleFileReaderConfiguration implements FileReaderConfiguration {

    private String baseDirectory;
    private boolean recursive = false;
    private boolean deleteAfterRead = true;
    private FileFilter fileFilter = null;
    
    public SimpleFileReaderConfiguration() {}
    
    public SimpleFileReaderConfiguration(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }
    
    @Override
    public String getBaseDirectory() {
        return baseDirectory;
    }
    
    @Override
    public void setBaseDirectory(String baseDirectory) {
        this.baseDirectory = baseDirectory;
    }

    @Override
    public boolean isRecursiveReader() {
        return recursive;
    }
    
    @Override
    public void setRecursiveReader(boolean recursive) {
        this.recursive = recursive;
    }
    
    @Override
    public boolean isDeleteAfterRead() {
        return deleteAfterRead;
    }
    
    @Override
    public void setDeleteAfterRead(boolean deleteAfterRead) {
        this.deleteAfterRead = deleteAfterRead;
    }

    @Override
    public FileFilter getFileFilter() {
        return fileFilter;
    }

    @Override
    public void setFileFilter(FileFilter fileFilter) {
        this.fileFilter = fileFilter;
    }

}
