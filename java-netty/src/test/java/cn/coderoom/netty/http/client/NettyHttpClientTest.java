package cn.coderoom.netty.http.client;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class NettyHttpClientTest {

    @Test
    public void testGet() {
        long startTIme = System.currentTimeMillis();
        NettyHttpClient client = new NettyHttpClient();
        for (int i = 0; i < 1000; i++) {
            String result = client.send("GET");
            //System.out.println("request result===="+result);
        }
        System.out.println("request time===="+(System.currentTimeMillis() - startTIme));
        assertTrue( true );
    }

    @Test
    public void testHutoolGet() {
        long startTIme = System.currentTimeMillis();
        HttpRequest request = new HttpRequest("http://112.124.52.157:8091/dy/douyindeviceinfo/getGoodsInfos");
        for (int i = 0; i < 1000; i++) {
            HttpResponse response = request.execute(false);
        }
        System.out.println("request time===="+(System.currentTimeMillis() - startTIme));
        assertTrue( true );
    }

}