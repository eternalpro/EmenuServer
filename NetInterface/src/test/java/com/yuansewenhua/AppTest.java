package com.yuansewenhua;

import com.yuansewenhua.net.main.ServerInstance;
import org.junit.Test;

import java.io.IOException;

public class AppTest {
    @Test
    public void startServer() throws IOException {
        ServerInstance.getInstance(80).start();
    }
}
