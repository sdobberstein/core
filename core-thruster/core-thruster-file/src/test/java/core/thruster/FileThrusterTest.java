package core.thruster;

import java.io.File;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.junit.Before;
import org.junit.Test;

import core.packet.Packet;
import core.packet.factory.DataPacketFactory;
import core.packet.factory.PacketFactory;
import core.process.read.FileReader;

public class FileThrusterTest {
    
    private static final String TEST_FILE_NAME = "test";
    
    private String baseDirectory = "src" + File.separator + "test" + File.separator + "resources" + File.separator;
    private FileReader fileReader = null;
    private PacketFactory packetFactory = null;
    
    @Before
    public void setUp() {
        fileReader = EasyMock.createStrictMock(FileReader.class);
        packetFactory = new DataPacketFactory();
    }
    
    @Test
    public void testThrust() {

        EasyMock.expect(fileReader.read()).andReturn(new File(baseDirectory + TEST_FILE_NAME));
        EasyMock.replay(fileReader);
        
        FileThruster thruster = new FileThruster();
        thruster.setFileReader(fileReader);
        thruster.setPacketFactory(packetFactory);
        
        Packet packet = thruster.thrust();
        
        Assert.assertNotNull(packet.getId());
        Assert.assertEquals("TEST FILE!", packet.getData());
    }
    
    @Test
    public void testThrustNoFile() {

        EasyMock.expect(fileReader.read()).andReturn(null);
        EasyMock.replay(fileReader);
        
        FileThruster thruster = new FileThruster();
        thruster.setFileReader(fileReader);
        thruster.setPacketFactory(packetFactory);
        
        Packet packet = thruster.thrust();
        
        Assert.assertNull(packet);
    }

}
