package core.process.write;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import core.packet.Packet;

public class SimpleXmlFileWriter implements FileWriter {

	private static final Log LOG = LogFactory.getLog(SimpleXmlFileWriter.class);
	
	private final FileWriterConfiguration DEFAULT_CONFIGURATION;

	private final String baseDirectory;
	
	/**
	 * Creates a new SimpleXmlFileWriter instance with a base output directory.  This output
	 * directory will be used when no FileWriterConfiguration is passed in.  If a FileWriterConfiguration
	 * is passed in, then the FileWriter will default to use it's configured base directory
	 * instead of this baseDirectory.
	 * 
	 * @param baseDirectory
	 *                    The base output directory to use in cases where no FileWriterConfiguration
	 *                    is passed in.
	 */
	public SimpleXmlFileWriter(String baseDirectory) {
		if (baseDirectory == null) {
			throw new IllegalArgumentException("BaseDirectory cannot be null!");
		}
		
		this.baseDirectory = baseDirectory;
		this.DEFAULT_CONFIGURATION = new DefaultFileWriterConfiguration(this.baseDirectory);
	}
	
	@Override
	public void writePacket(Packet packet) {
		writePacket(packet, DEFAULT_CONFIGURATION);
	}

	@Override
	public void writePacket(Packet packet, FileWriterConfiguration configuration) {
		
		if (configuration.isDataWritable()) {
			writeData(packet, configuration);
		}
		
		if (configuration.isPropertiesWritable()) {
			writeProperties(packet, configuration);
		}
	}

	private void writeData(Packet packet, FileWriterConfiguration configuration) {
		String target = getTargetFilePath(packet, configuration, "xml");
		File targetFile = new File(target);
		
		BufferedWriter writer = null;
		
		try {
			writer = new BufferedWriter(new java.io.FileWriter(targetFile));
			
			String prettyPrint = prettyFormat(packet.getData(), 4);
			writer.write(prettyPrint);
		} catch (IOException ioe) {
			throw new RuntimeException(ioe.getMessage(), ioe);
		} finally {
			if (writer != null) {
				try {
					writer.close();
				} catch (Throwable t) {
					if (LOG.isWarnEnabled()) {
						LOG.warn(t.getMessage(), t);
					}
				}
			}
		}
	}

	private void writeProperties(Packet packet, FileWriterConfiguration configuration) {
		String target = getTargetFilePath(packet, configuration, "properties");
		File targetFile = new File(target);
		
		FileOutputStream fos = null;
		
		try {
			fos = new FileOutputStream(targetFile);
			packet.getProperties().store(fos, null);			
		} catch (FileNotFoundException fnfe) {
			throw new RuntimeException(fnfe.getMessage(), fnfe);
		} catch (IOException ioe) {
			throw new RuntimeException(ioe.getMessage(), ioe);
		} finally {
			if (fos != null) {
				try {
					fos.close();
				} catch (Throwable t) {
					if (LOG.isWarnEnabled()) {
						LOG.warn(t.getMessage(), t);
					}
				}
			}
		}
	}

	private String getTargetFilePath(Packet packet,	FileWriterConfiguration configuration, String extension) {
		String baseDir = configuration.getBaseDirectory();
		String folderName = configuration.generateFolderName(packet);
		String fileName = configuration.generateFileName(packet);
		
		File baseFile = new File(baseDir);
		if (!baseFile.exists()) {
			if (!baseFile.mkdirs()) {
				throw new RuntimeException("Failed creating directory: " + baseDir);
			}
		}
		
		if (folderName != null) {
			File folderFile = new File(folderName);
			if (!folderFile.exists()) {
				if (!folderFile.mkdirs()) {
					throw new RuntimeException("Failed creating directory: " + folderName);
				}
			}
		}
		
		return assemblePath(baseDir, folderName, fileName, extension);
	}

	private String assemblePath(String baseDir, String folderName, String fileName, String extension) {
		StringBuilder path = new StringBuilder();
		
		if (!baseDir.endsWith(File.separator)) {
			baseDir = baseDir + File.separator;
		}
		
		path.append(baseDir);
		
		if (folderName != null) {
			path.append(folderName);
			path.append(File.separator);			
		}
		
		path.append(fileName);
		
		if (!extension.startsWith(".")) {
			extension = "." + extension;
		}
		
		path.append(extension);
		
		return path.toString();
	}
	
	private static String prettyFormat(String input, int indent) {
	    try {
	        Source xmlInput = new StreamSource(new StringReader(input));
	        StringWriter stringWriter = new StringWriter();
	        StreamResult xmlOutput = new StreamResult(stringWriter);
	        TransformerFactory transformerFactory = TransformerFactory.newInstance();
	        transformerFactory.setAttribute("indent-number", indent);
	        Transformer transformer = transformerFactory.newTransformer(); 
	        transformer.setOutputProperty(OutputKeys.INDENT, "yes");
	        transformer.transform(xmlInput, xmlOutput);
	        return xmlOutput.getWriter().toString();
	    } catch (Exception e) {
	        throw new RuntimeException(e); // simple exception handling, please review it
	    }
	}

	private class DefaultFileWriterConfiguration implements FileWriterConfiguration {
		
		private String baseDirectory;
		private boolean dataWritable = true;
		private boolean propertiesWritable = true;
		
		public DefaultFileWriterConfiguration(String baseDirectory) {
			this.baseDirectory = baseDirectory;
		}
		
		@Override
		public boolean isDataWritable() {
			return dataWritable;
		}
		
		@Override
		public void setDataWritable(boolean dataWritable) {
			this.dataWritable = dataWritable;
		}
		
		@Override
		public boolean isPropertiesWritable() {
			return propertiesWritable;
		}
		
		@Override
		public void setPropertiesWritable(boolean propertiesWritable) {
			this.propertiesWritable = propertiesWritable;
		}

		@Override
		public String generateFolderName(Packet packet) {
			return null;
		}

		@Override
		public String generateFileName(Packet packet) {
			return packet.getId();
		}

		@Override
		public String getBaseDirectory() {
			return baseDirectory;
		}
	}
}