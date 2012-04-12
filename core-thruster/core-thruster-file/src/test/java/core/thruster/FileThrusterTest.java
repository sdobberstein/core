package core.thruster;

import java.io.File;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import core.packet.Packet;
import core.packet.factory.DataPacketFactory;
import core.process.read.FileReader;
import core.process.read.FileReaderImpl;

public class FileThrusterTest {
    
    private String baseDirectory;
    
    @Before
    public void setUp() {
        baseDirectory = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    }
    
    @Test
    public void testThrust() {

        FileReader fileReader = new FileReaderImpl(baseDirectory);
        
        FileThruster thruster = new FileThruster();
        thruster.setFileReader(fileReader);
        thruster.setPacketFactory(new DataPacketFactory());
        
        Packet packet = thruster.thrust();
        
        Assert.assertNotNull(packet.getId());
        Assert.assertEquals("TEST FILE!", packet.getData());
    }

}
