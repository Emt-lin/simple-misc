package com.misc.rpc.proto;

import com.misc.core.exception.ConvertException;
import com.misc.core.model.MiscPack;
import com.misc.core.netty.NettyConvertHandler;
import com.misc.rpc.core.RpcRequest;
import com.misc.rpc.core.RpcResponse;
import com.misc.rpc.server.RpcServerConfig;
import io.netty.buffer.ByteBufAllocator;

/**
 * misc
 */
public class MiscServerConvertHandler extends NettyConvertHandler<MiscPack, MiscPack, RpcRequest, RpcResponse> {

    private RpcServerConfig rpcServerConfig;

    public MiscServerConvertHandler(RpcServerConfig rpcServerConfig) {
        this.rpcServerConfig = rpcServerConfig;
    }

    /**
     * 收到请求，解码
     */
    @Override
    protected RpcRequest decode(MiscPack msg) throws ConvertException {
        RpcRequest rpcRequest = rpcServerConfig.convertMiscPackToRpcRequest(msg);
        msg.release();
        return rpcRequest;
    }

    /**
     * 发出响应，编码
     */
    @Override
    protected MiscPack encode(ByteBufAllocator allocator, RpcResponse msg) throws ConvertException {
        return rpcServerConfig.convertRpcResponseToMiscPack(msg);
    }
}
