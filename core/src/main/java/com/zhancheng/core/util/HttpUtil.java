package com.zhancheng.core.util;

import cn.hutool.json.JSONObject;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ConnectionPoolTimeoutException;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.net.URL;
import java.util.Map;

public class HttpUtil {
    private static Logger log = LoggerFactory.getLogger(HttpUtil.class);
    public static String doGet(String httpurl) {
        HttpURLConnection connection = null;
        InputStream is = null;
        BufferedReader br = null;
        String result = null;// 返回结果字符串
        try {
            // 创建远程url连接对象
            URL url = new URL(httpurl);
            // 通过远程url连接对象打开一个连接，强转成httpURLConnection类
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接方式：get
            connection.setRequestMethod("GET");
            // 设置连接主机服务器的超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取远程返回的数据时间：60000毫秒
            connection.setReadTimeout(60000);
            // 发送请求
            connection.connect();
            // 通过connection连接，获取输入流
            if (connection.getResponseCode() == 200) {
                is = connection.getInputStream();
                // 封装输入流is，并指定字符集
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                // 存放数据
                StringBuffer sbf = new StringBuffer();
                String temp = null;
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            connection.disconnect();// 关闭远程连接
        }

        return result;
    }

    public static String doPost(String httpUrl, String param) {

        HttpURLConnection connection = null;
        InputStream is = null;
        OutputStream os = null;
        BufferedReader br = null;
        String result = null;
        try {
            URL url = new URL(httpUrl);
            // 通过远程url连接对象打开连接
            connection = (HttpURLConnection) url.openConnection();
            // 设置连接请求方式
            connection.setRequestMethod("POST");
            // 设置连接主机服务器超时时间：15000毫秒
            connection.setConnectTimeout(15000);
            // 设置读取主机服务器返回数据超时时间：60000毫秒
            connection.setReadTimeout(60000);

            // 默认值为：false，当向远程服务器传送数据/写数据时，需要设置为true
            connection.setDoOutput(true);
            // 默认值为：true，当前向远程服务读取数据时，设置为true，该参数可有可无
            connection.setDoInput(true);
            // 设置传入参数的格式:请求参数应该是 name1=value1&name2=value2 的形式。
            connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            // 设置鉴权信息：Authorization: Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0
            connection.setRequestProperty("Authorization", "Bearer da3efcbf-0845-4fe3-8aba-ee040be542c0");
            // 通过连接对象获取一个输出流
            os = connection.getOutputStream();
            // 通过输出流对象将参数写出去/传输出去,它是通过字节数组写出的
            if(param!=null){
                os.write(param.getBytes());
            }

            // 通过连接对象获取一个输入流，向远程读取
            if (connection.getResponseCode() == 200) {

                is = connection.getInputStream();
                // 对输入流对象进行包装:charset根据工作项目组的要求来设置
                br = new BufferedReader(new InputStreamReader(is, "UTF-8"));

                StringBuffer sbf = new StringBuffer();
                String temp = null;
                // 循环遍历一行一行读取数据
                while ((temp = br.readLine()) != null) {
                    sbf.append(temp);
                    sbf.append("\r\n");
                }
                result = sbf.toString();
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 关闭资源
            if (null != br) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != os) {
                try {
                    os.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != is) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            // 断开与远程地址url的连接
            connection.disconnect();
        }
        return result;
    }

    /**
     * 发送HttpPost请求
     *
     * @param strURL
     *            服务地址
     * @param params
     *            json字符串,例如: "{ \"id\":\"12345\" }" ;其中属性名必须带双引号<br/>
     * @return 成功:返回json字符串<br/>
     */
    public static String post(String strURL, String params) {
        BufferedReader reader = null;
        try {
            URL url = new URL(strURL);// 创建连接
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setUseCaches(false);
            connection.setInstanceFollowRedirects(true);
            connection.setRequestMethod("POST"); // 设置请求方式
            //connection.setRequestProperty("Accept", "application/json"); // 设置接收数据的格式<br>、　　　　　　//因为要登陆才可以执行请求，所以这里要带cookie的header
            connection.setRequestProperty("Cookie", "geli-session=41b6d86db1e97df5:-60813064:16d0571e3e8:-3a58377431669b192d08; c=58ydwry; u=58zqg8z; pcsuv=1482230765738.a.46958170; u4ad=4031lfgby; UM_distinctid=16be539fa796b9-099f04a8a5445d-4144032b-1fa400-16be539fa7a7d3; visitedfid=22035D16260D14152; gr_user_id=b2a6251c-dddb-4502-91f9-0b6f10ccb260; favCar=%E5%A5%A5%E8%BF%AAA3_9550%7C%E5%A5%A5%E8%BF%AAA8L_7%7C%E7%A6%8F%E5%85%8B%E6%96%AFActive_25101%7C%E8%BD%A9%E9%80%B8_3996%7C%E5%88%9B%E7%95%8C_24483; locationWap=%7B%22expires%22%3A1568884707863%2C%22city%22%3A%22%E5%93%88%E5%B0%94%E6%BB%A8%E5%B8%82%22%2C%22cityUser%22%3A%22%E5%93%88%E5%B0%94%E6%BB%A8%E5%B8%82%22%2C%22cityCode%22%3A%22230100%22%2C%22proCode%22%3A%22230000%22%2C%22cityCodeUser%22%3A%22230100%22%2C%22proCodeUser%22%3A%22230000%22%7D; AplocationWap=%7B%22regionId%22%3A187%2C%20%22regionName%22%3A%22%E5%93%88%E5%B0%94%E6%BB%A8%22%7D; pcLocate=%7B%22proCode%22%3A%22440000%22%2C%22pro%22%3A%22%E5%B9%BF%E4%B8%9C%E7%9C%81%22%2C%22cityCode%22%3A%22441900%22%2C%22city%22%3A%22%E4%B8%9C%E8%8E%9E%E5%B8%82%22%2C%22dataType%22%3A%22user%22%2C%22expires%22%3A1571888929008%7D; pcautoLocate=%7B%22proId%22%3A5%2C%22cityId%22%3A6%2C%22url%22%3A%22%2F%2Fwww.pcauto.com.cn%2Fqcbj%2Fdg%2F%22%2C%22dataTypeAuto%22%3A%22user%22%7D; PClocation=6; pcuvdata=lastAccessTime=1570869719999|visits=289; channel=9396");
            connection.setRequestProperty("Content-Type", "application/json"); // 设置发送数据的格式
            connection.connect();
            //一定要用BufferedReader 来接收响应， 使用字节来接收响应的方法是接收不到内容的
            OutputStreamWriter out = new OutputStreamWriter(connection.getOutputStream(), "UTF-8"); // utf-8编码
            out.append(params);
            out.flush();
            out.close();
            // 读取响应
            reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
            String line;
            String res = "";
            while ((line = reader.readLine()) != null) {
                res += line;
            }
            reader.close();
            return res;
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return "error"; // 自定义错误信息
    }

    /**

     url:传入带token的获取二维码url链接

     map：获取post参数

     */

    public static byte[]  doImgPost(String url, Map<String, Object> map) {

        byte[] result = null;

        //使用HTTPPost方法访问获取二维码链接url

        HttpPost httpPost = new HttpPost(url);

        //创建http连接客户端

        CloseableHttpClient client = HttpClients.createDefault();

        //设置头响应类型

         httpPost.addHeader("Content-Type", "application/json");
         try {
         // 设置请求的参数
         JSONObject postData = new JSONObject();
         for (Map.Entry<String, Object> entry : map.entrySet()) {
                postData.put(entry.getKey(), entry.getValue());
         }
         httpPost.setEntity(new StringEntity(postData.toString(), "UTF-8"));

         log.info("微信获取微信二维码post数据 " + postData.toString());

         //返回的post请求结果

         HttpResponse response = client.execute(httpPost);
         HttpEntity entity = response.getEntity();
         result = EntityUtils.toByteArray(entity);
         } catch (ConnectionPoolTimeoutException e) {
         log.error("http get throw ConnectionPoolTimeoutException(wait time out)", e);
        } catch (ConnectTimeoutException e) {
         log.error("http get throw ConnectTimeoutException", e);
         } catch (SocketTimeoutException e) {
         log.error("http get throw SocketTimeoutException", e);
        } catch (Exception e) {
         log.error("http get throw Exception", e);
        } finally {
        httpPost.releaseConnection();

         }

         //最后转成2进制图片流

         return result;

        }

}
