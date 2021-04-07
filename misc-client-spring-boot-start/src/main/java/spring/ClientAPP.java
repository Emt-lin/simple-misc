package spring;


import com.misc.core.exception.RpcException;
import com.misc.core.register.ZKRegistryService;
import com.misc.core.test.EchoService;
import com.misc.rpc.client.ReferenceBean;
import com.misc.rpc.core.RpcProperties;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;

/**
 * todo
 *
 */
public class ClientAPP {


    public static void main(String[] args) throws Exception {

        Method method = EchoService.class.getMethod("hash", String.class);
        RpcProperties properties = new RpcProperties(method);
        properties.setAck(true);
        properties.setFallBackClass(EchoServiceFallback.class);

        // 初始化
        ReferenceBean<EchoService> referenceBean = new ReferenceBean<>(EchoService.class);
//        referenceBean.setMethodPropertie(properties);

        // 设置注册中心
        referenceBean.setRegistryService(new ZKRegistryService());

        try {
            //
            EchoService echoService = referenceBean.get();
            long start = System.currentTimeMillis();
            IntStream.range(0, 10000).forEach(value -> {
                int[] hash = echoService.hashCodes(1,"111" + value, new ArrayList<>());
                System.out.println(Arrays.toString(hash));
            });

            System.out.println(System.currentTimeMillis() - start);
        } catch (RpcException e) {
            System.out.println(e);
        }
    }
}
