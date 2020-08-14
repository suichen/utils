package com.suichen.utils.dubbo;

//Exporter<?> exporter = protocol.export(wrapperInvoker);

//protocol is Protocol$Adaptive

//Protocol$Adaptive   export()

// com.alibaba.dubbo.rpc.Protocol extension = (com.alibaba.dubbo.rpc.Protocol)ExtensionLoader.
//  getExtensionLoader(com.alibaba.dubbo.rpc.Protocol.class).getExtension(extName)

//extName is registry; extension is ProtocolFilterWrapper

/**
 *
 * ProtocolFilterWrapper:
 *
 * public <T> Exporter<T> export(Invoker<T> invoker) throws RpcException {
 *         if (Constants.REGISTRY_PROTOCOL.equals(invoker.getUrl().getProtocol())) {
 *             return protocol.export(invoker);
 *         }
 *         return protocol.export(buildInvokerChain(invoker, Constants.SERVICE_FILTER_KEY, Constants.PROVIDER));
 *     }
 *
 * protocol is ProtocolListenerWrapper;  执行的是 return protocol.export(invoker);
 */

/***
 *
 * ProtocolListenerWrapper:
 *
 * public <T> Exporter<T> export(Invoker<T> invoker) throws RpcException {
 *         if (Constants.REGISTRY_PROTOCOL.equals(invoker.getUrl().getProtocol())) {
 *             return protocol.export(invoker);
 *         }
 *         return new ListenerExporterWrapper<T>(protocol.export(invoker),
 *                 Collections.unmodifiableList(ExtensionLoader.getExtensionLoader(ExporterListener.class)
 *                         .getActivateExtension(invoker.getUrl(), Constants.EXPORTER_LISTENER_KEY)));
 *     }
 *
 *     protocol is QosProtocolWrapper
 */


/**
 * QosProtocolWrapper:
 *
 * public <T> Exporter<T> export(Invoker<T> invoker) throws RpcException {
 *         if (Constants.REGISTRY_PROTOCOL.equals(invoker.getUrl().getProtocol())) {
 *             startQosServer(invoker.getUrl());
 *             return protocol.export(invoker);
 *         }
 *         return protocol.export(invoker);
 *     }
 *
 *  protocol is RegistryProtocol
 */


/**
 * RegistryProtocol:
 * @Override
 *     public <T> Exporter<T> export(final Invoker<T> originInvoker) throws RpcException {
 *         //export invoker
 *         final ExporterChangeableWrapper<T> exporter = doLocalExport(originInvoker);
 *
 *         URL registryUrl = getRegistryUrl(originInvoker);
 *
 *         //registry provider
 *         final Registry registry = getRegistry(originInvoker);
 *         final URL registedProviderUrl = getRegistedProviderUrl(originInvoker);
 *
 *         //to judge to delay publish whether or not
 *         boolean register = registedProviderUrl.getParameter("register", true);
 *
 *         ProviderConsumerRegTable.registerProvider(originInvoker, registryUrl, registedProviderUrl);
 *
 *         if (register) {
 *             register(registryUrl, registedProviderUrl);
 *             ProviderConsumerRegTable.getProviderWrapper(originInvoker).setReg(true);
 *         }
 *
 *         // Subscribe the override data
 *         // FIXME When the provider subscribes, it will affect the scene : a certain JVM exposes the service and call the same service. Because the subscribed is cached key with the name of the service, it causes the subscription information to cover.
 *         final URL overrideSubscribeUrl = getSubscribedOverrideUrl(registedProviderUrl);
 *         final OverrideListener overrideSubscribeListener = new OverrideListener(overrideSubscribeUrl, originInvoker);
 *         overrideListeners.put(overrideSubscribeUrl, overrideSubscribeListener);
 *         registry.subscribe(overrideSubscribeUrl, overrideSubscribeListener);
 *         //Ensure that a new exporter instance is returned every time export
 *         return new DestroyableExporter<T>(exporter, originInvoker, overrideSubscribeUrl, registedProviderUrl);
 *     }
 *
 *
 *     private <T> ExporterChangeableWrapper<T> doLocalExport(final Invoker<T> originInvoker) {
 *         String key = getCacheKey(originInvoker);
 *         ExporterChangeableWrapper<T> exporter = (ExporterChangeableWrapper<T>) bounds.get(key);
 *         if (exporter == null) {
 *             synchronized (bounds) {
 *                 exporter = (ExporterChangeableWrapper<T>) bounds.get(key);
 *                 if (exporter == null) {
 *                     final Invoker<?> invokerDelegete = new InvokerDelegete<T>(originInvoker, getProviderUrl(originInvoker));
 *                     exporter = new ExporterChangeableWrapper<T>((Exporter<T>) protocol.export(invokerDelegete), originInvoker);
 *                     bounds.put(key, exporter);
 *                 }
 *             }
 *         }
 *         return exporter;
 *     }
 *
 *     protocol is Protocol$Adaptive
 *
 */


//第二次执行执行Protocol$Adaptive export()

/**
 * Protocol$Adaptive:
 *
 * public com.alibaba.dubbo.rpc.Exporter export(com.alibaba.dubbo.rpc.Invoker arg0) throws com.alibaba.dubbo.rpc.RpcException {
 *         if (arg0 == null) throw new IllegalArgumentException("com.alibaba.dubbo.rpc.Invoker argument == null");
 *         if (arg0.getUrl() == null) throw new IllegalArgumentException("com.alibaba.dubbo.rpc.Invoker argument getUrl() == null");
 *         com.alibaba.dubbo.common.URL url = arg0.getUrl();
 *         String extName = ( url.getProtocol() == null ? "dubbo" : url.getProtocol() );
 *         if(extName == null) throw new IllegalStateException("Fail to get extension(com.alibaba.dubbo.rpc.Protocol) name from url(" + url.toString() + ") use keys([protocol])");
 *         com.alibaba.dubbo.rpc.Protocol extension = (com.alibaba.dubbo.rpc.Protocol)ExtensionLoader.getExtensionLoader(com.alibaba.dubbo.rpc.Protocol.class).getExtension(extName);
 *         return extension.export(arg0);
 *     }
 *
 *     extName is dubbo; extension is ProtocolFilterWrapper
 */


//ProtocolFilterWrapper 第二次执行的是 protocol.export(buildInvokeChain(invoker, Constants.SERVICE_FILTER_KEY, Constants.PROVIDER)
//ProtocolListenerWrapper 第二次执行的是
/**
 * return new ListenerExporterWrapper<T>(protocol.export(invoker),
 *                 Collections.unmodifiableList(ExtensionLoader.getExtensionLoader(ExporterListener.class)
 *                         .getActivateExtension(invoker.getUrl(), Constants.EXPORTER_LISTENER_KEY)));
 */

//QosProtocolWrapper 第二次执行的是 return protocol.export(invoker)  protocol is DubboProtocol


//Exchanges类，这个类是通讯的入口，在DubboProtocol.createServer和initClient方法里分别用到Exchanges的bind和connect方法

//客户端Exchanges和Transports

//客户端的Invoker。在ReferenceConfig.createProxy作为创建代理对象的入口

//dubbo集群模块主要实现了集群容错和负载均衡功能
//这一调用的入口在RegistryProtocol.doRefer方法中，会去执行cluster.join(directory)




































