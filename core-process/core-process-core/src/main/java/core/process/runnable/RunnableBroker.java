package core.process.runnable;

import org.springframework.core.task.TaskExecutor;

import core.packet.Packet;
import core.process.Broker;

public class RunnableBroker implements Broker {

    private TaskExecutor taskExecutor;
    private RunnablePacketFactory runnablePacketFactory;
    
    public RunnableBroker() {}
    
    public RunnableBroker(TaskExecutor taskExecutor, RunnablePacketFactory runnablePacketFactory) {
        this.taskExecutor = taskExecutor;
        this.runnablePacketFactory = runnablePacketFactory;
    }
    
    public void onMessage(Packet packet) {
        taskExecutor.execute(runnablePacketFactory.newInstance(packet));
    }

    public TaskExecutor getTaskExecutor() {
        return taskExecutor;
    }

    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public RunnablePacketFactory getRunnablePacketFactory() {
        return runnablePacketFactory;
    }

    public void setRunnablePacketFactory(RunnablePacketFactory runnablePacketFactory) {
        this.runnablePacketFactory = runnablePacketFactory;
    }
    
}
