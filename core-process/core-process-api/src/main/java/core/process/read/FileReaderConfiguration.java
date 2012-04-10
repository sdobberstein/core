package core.process.read;

import java.io.FileFilter;

public interface FileReaderConfiguration {

	public String getBaseDirectory();
	
	public void setBaseDirectory(String baseDirectory);
	
	public boolean isRecursiveReader();
	
	public void setRecursiveReader(boolean recursive);
	
	public FileFilter getFileFilter();
	
	public void setFileFilter(FileFilter fileFilter);
	
}