package org.solstice;

import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import com.baidu.aip.util.Util;
import org.apache.commons.io.IOUtils;
import org.json.JSONObject;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    //设置APPID/AK/SK
     static final String APP_ID = "15598344";
     static final String API_KEY = "W8h9AasjysLHkGmMG2nUbLoB";
     static final String SECRET_KEY = "H4I5TtwNIsbGvTXFSyoEaKpQaIOg3NBj";


    public static void main(String[] args) {
        // 初始化一个AipSpeech
        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);

        // 可选：设置网络连接参数
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);

        // 调用接口
        FileInputStream fis = null;

        String path = args[0];
        try {
            fis = new FileInputStream(path);
            String text = IOUtils.toString(fis);
            TtsResponse res = client.synthesis(text, "zh", 1, null);

            byte[] data = res.getData();
            JSONObject res1 = res.getResult();
            if (data != null) {
                try {
                    Util.writeBytesToFileSystem(data, "output.mp3");
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (res1 != null) {
                System.out.println(res1.toString(2));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.closeQuietly(fis);
        }

    }
}
