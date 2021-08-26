package com.groupjn.userservice.kieker.pipes;

import java.util.HashMap;
import java.util.Map;

public class MyNamedPipeManager {
    private static final MyNamedPipeManager PIPE_MGR_INSTANCE = new MyNamedPipeManager();

    // Not synchronized!
    private final Map<String, MyPipe> pipeMap = new HashMap<>();

    public static MyNamedPipeManager getInstance() {
        return MyNamedPipeManager.PIPE_MGR_INSTANCE;
    }

    public MyPipe acquirePipe(final String pipeName) throws IllegalArgumentException {
        if ((pipeName == null) || (pipeName.length() == 0)) {
            throw new IllegalArgumentException("Invalid connection name: '" + pipeName + "'");
        }
        MyPipe conn;
        synchronized (this) {
            conn = this.pipeMap.get(pipeName);
            if (conn == null) {
                conn = new MyPipe(pipeName);
                this.pipeMap.put(pipeName, conn);
            }
        }
        return conn;
    }
}
