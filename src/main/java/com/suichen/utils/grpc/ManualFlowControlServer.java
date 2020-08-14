package com.suichen.utils.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.Status;
import io.grpc.stub.ServerCallStreamObserver;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.logging.Logger;

public class ManualFlowControlServer {
//    private static final Logger logger = Logger.getLogger(ManualFlowControlServer.class.getName());
//
//    public static void main(String[] args) throws IOException,InterruptedException {
//        StreamingGreeterGrpc.StreamingGreeterImplBase svc =
//                new StreamingGreeterGrpc.StreamingGreeterImplBase() {
//                    @Override
//                    public StreamObserver<HelloRequest> sayHelloStreaming(StreamObserver<HelloResponse> responseObserver) {
//                        final ServerCallStreamObserver<HelloResponse> serverCallStreamObserver =
//                                (ServerCallStreamObserver<HelloResponse>)responseObserver;
//
//                        serverCallStreamObserver.disableAutoInboundFlowControl();
//
//                        final AtomicBoolean wasReady = new AtomicBoolean(false);
//
//                        serverCallStreamObserver.setOnReadyHandler(new Runnable() {
//                            @Override
//                            public void run() {
//                                if (serverCallStreamObserver.isReady() && wasReady.compareAndSet(false,true)) {
//                                    logger.info("READY");
//
//                                    serverCallStreamObserver.request(1);
//                                }
//                            }
//                        });
//
//                        return new StreamObserver<HelloRequest>() {
//                            @Override
//                            public void onNext(HelloRequest helloRequest) {
//                                try {
//                                    String name = helloRequest.getName();
//                                    logger.info("----> "+name);
//
//                                    Thread.sleep(100);
//
//                                    String message = "Hello "+name;
//                                    logger.info("<--- "+message);
//
//                                    HelloResponse response = HelloResponse.newBuilder().setMessage(message).build();
//                                    responseObserver.onNext(response);
//
//                                    if (serverCallStreamObserver.isReady()) {
//                                        serverCallStreamObserver.request(1);
//                                    } else {
//                                        wasReady.set(false);
//                                    }
//                                }catch (Throwable throwable) {
//                                    throwable.printStackTrace();
//                                    responseObserver.onError(Status.UNKNOWN.withDescription("Error handling request")
//                                                    .withCause(throwable).asException());
//                                }
//                            }
//
//                            @Override
//                            public void onError(Throwable throwable) {
//                                throwable.printStackTrace();
//                                responseObserver.onCompleted();
//                            }
//
//                            @Override
//                            public void onCompleted() {
//                                logger.info("COMPLETED");
//                                responseObserver.onCompleted();
//                            }
//                        };
//                    }
//                };
//
//        final Server server = ServerBuilder.forPort(50051)
//                                    .addService(svc).build().start();
//
//        logger.info("Listening on "+server.getPort());
//
//        Runtime.getRuntime().addShutdownHook(new Thread() {
//            @Override
//            public void run() {
//                logger.info("Shutting down");
//                server.shutdown();
//            }
//        });
//
//        server.awaitTermination();
//    }
}
