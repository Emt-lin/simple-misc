package spring;

import com.misc.core.test.EchoService;

import java.util.List;

/**
 * todo
 *
 */
public class EchoServiceFallback implements EchoService {

    @Override
    public int[] hash(String str) {
        return new int[]{1, 2, 3};
    }

    @Override
    public int[] hashCodes(int _int, String _string, List<Integer> list) {
        return new int[0];
    }
}
