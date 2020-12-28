package com.misc.rpc.client;

import com.misc.core.model.MiscPack;
import com.misc.core.netty.NettyClient;
import com.misc.core.proto.misc.MiscCodecProvider;
import com.misc.core.proto.misc.common.MiscProperties;
import com.misc.rpc.core.RpcRequest;
import com.misc.rpc.core.RpcResponse;
import com.misc.rpc.proto.MiscClientConvertHandler;

/**
 * todo
 */
public class MiscRpcClient extends NettyClient.Builder<MiscPack, MiscPack, RpcResponse, RpcRequest> {

    private MiscProperties properties;

    @Override
    protected void init() {
        properties.initClient(this);
        super.setNettyCodecProvider(new MiscCodecProvider(properties));
        super.setNettyConvertHandler(new MiscClientConvertHandler());
        super.setNettyEventListener(new RpcClientHandler());
    }

    private MiscRpcClient(MiscProperties properties) {
        this.properties = properties == null ? new MiscProperties() : properties;
    }
//    @SuppressWarnings("all")
    public static NettyClient<MiscPack, MiscPack, RpcResponse, RpcRequest> run(MiscProperties properties) throws Throwable {
        MiscRpcClient miscRpcClient = new MiscRpcClient(properties);
        NettyClient<MiscPack, MiscPack, RpcResponse, RpcRequest> build = miscRpcClient.build();
        return (NettyClient<MiscPack, MiscPack, RpcResponse, RpcRequest>) build.start();
    }
}
