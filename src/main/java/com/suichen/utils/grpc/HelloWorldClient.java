package com.suichen.utils.grpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.StatusRuntimeException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;
import java.util.logging.Level;

public class HelloWorldClient {
//    private static final Logger logger = LoggerFactory.getLogger(HelloWorldClient.class);
//
//    private final ManagedChannel channel;
//    private final GreeterGrpc.GreeterBlockingStub blockingStub;
//
//    HelloWorldClient(ManagedChannel channel) {
//        this.channel = channel;
//        blockingStub = GreeterGrpc.newBlockingStub(channel);
//    }
//
//    public HelloWorldClient(String host, int port) {
//        this(ManagedChannelBuilder.forAddress(host,port).usePlaintext().build());
//    }
//
//    public void shutdown() throws InterruptedException {
//        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
//    }
//
//    public void greet(String name) {
//        logger.info("Will try to greet " + name + " ...");
//
//        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
//        HelloResponse response;
//
//        try {
//            response = blockingStub.sayHello(request);
//        }catch (StatusRuntimeException e) {
//            logger.info(Level.WARNING+" RPC failed: "+ e.getStatus());
//            return;
//        }
//
//        logger.info("Greeting: " + response.getMessage());
//    }
//
//    public static void main(String[] args) throws Exception{
//        HelloWorldClient client = new HelloWorldClient("localhost", 50051);
//
//        try {
//            String user = "world";
//            if (args.length > 0) {
//                user = args[0]; /* Use the arg as the name to greet if provided */
//            }
//
//            client.greet(user);
//        }finally {
//            client.shutdown();
//        }
//    }
}
