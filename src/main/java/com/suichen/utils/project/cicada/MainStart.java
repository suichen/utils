package com.suichen.utils.project.cicada;

import top.crossoverjie.cicada.server.CicadaServer;

public class MainStart {
    public static void main(String[] args) throws Exception{
        CicadaServer.start(MainStart.class, "/cicada-example");
    }
}
