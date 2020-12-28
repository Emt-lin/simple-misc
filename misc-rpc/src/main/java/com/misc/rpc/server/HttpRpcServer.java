package com.misc.rpc.server;

import com.misc.core.netty.NettyServer;
import com.misc.core.proto.http.HttpCodecProvider;
import com.misc.rpc.core.RpcRequest;
import com.misc.rpc.core.RpcResponse;
import com.misc.rpc.proto.HttpServerConvertHandler;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;

/**
 * http
 */
public class HttpRpcServer extends NettyServer.Builder<FullHttpRequest, FullHttpResponse, RpcRequest, RpcResponse> {

    private RpcServerConfig rpcServerConfig;

    private HttpRpcServer(RpcServerConfig rpcServerConfig) {
        this.rpcServerConfig = rpcServerConfig;
    }

    @Override
    protected void init() {
        super.setNettyCodecProvider(new HttpCodecProvider());
        super.setNettyConvertHandler(new HttpServerConvertHandler(rpcServerConfig));
        super.setNettyEventListener(new RpcServerHandler());
    }

    /**
     * 提供静态方法，启动
     * @param rpcServerConfig
     * @throws Throwable
     */
    public static void run(RpcServerConfig rpcServerConfig) throws Throwable {
        new HttpRpcServer(rpcServerConfig).build().start().sync();
    }
}
