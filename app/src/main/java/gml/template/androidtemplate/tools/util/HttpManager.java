package gml.template.androidtemplate.tools.util;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.CookieStore;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.ClientContext;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HTTP;
import org.apache.http.protocol.HttpContext;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.ref.WeakReference;
import java.net.URL;
import java.security.GeneralSecurityException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.X509TrustManager;


public class HttpManager {
    //创建cookies
    static CookieStore localCookies = new BasicCookieStore();
    static private int timeoutConnection = 5000;
    static private int timeoutSocket = 10000;
    private static X509TrustManager xtm = new X509TrustManager() {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType)
                throws CertificateException {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }
    };
    private static HostnameVerifier hnv = new HostnameVerifier() {
        @Override
        public boolean verify(String hostname, SSLSession session) {
            return true;
        }
    };

    static public String syncHttpsGet(String urlString) {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            X509TrustManager[] xtmArray = new X509TrustManager[]{xtm};
            sslContext.init(null, xtmArray, new java.security.SecureRandom());
        } catch (GeneralSecurityException gse) {
        }

        // 为javax.net.ssl.HttpsURLConnection设置默认的SocketFactory和HostnameVerifier
        if (sslContext != null) {
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        }

        HttpsURLConnection.setDefaultHostnameVerifier(hnv);

        HttpsURLConnection urlCon = null;
        try {
            urlCon = (HttpsURLConnection) (new URL(urlString)).openConnection();
            urlCon.setRequestMethod("GET");
//				urlCon.setDoOutput(true);
            urlCon.setDoInput(true);
            urlCon.connect();

            // 服务器返回输入流并读写
            BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            String line;
            StringBuilder ret = new StringBuilder();
            while ((line = in.readLine()) != null) {
                ret.append(line);
            }
            in.close();

            return ret.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    static public String syncHttpsPost(String urlString, final List<NameValuePair> postData) {
        SSLContext sslContext = null;
        try {
            sslContext = SSLContext.getInstance("TLS");
            X509TrustManager[] xtmArray = new X509TrustManager[]{xtm};
            sslContext.init(null, xtmArray, new java.security.SecureRandom());
        } catch (GeneralSecurityException gse) {
        }

        // 为javax.net.ssl.HttpsURLConnection设置默认的SocketFactory和HostnameVerifier
        if (sslContext != null) {
            HttpsURLConnection.setDefaultSSLSocketFactory(sslContext.getSocketFactory());
        }

        HttpsURLConnection.setDefaultHostnameVerifier(hnv);

        StringBuilder data = new StringBuilder();
        if (postData != null && postData.size() > 0) {
            for (int i = 0; i < postData.size(); i++) {
                NameValuePair pair = postData.get(i);
                if (i > 0) {
                    data.append('&');
                }
                data.append(pair.getName());
                data.append('=');
                data.append(pair.getValue());
            }
        }
        String query = data.toString();

        byte[] entitydata = query.getBytes();// 得到实体数据
        HttpsURLConnection urlCon = null;
        try {
            urlCon = (HttpsURLConnection) (new URL(urlString)).openConnection();
            urlCon.setRequestProperty("Content-Type", "application/x-www-form-urlencoded;charset=UTF-8");
            urlCon.setRequestProperty("Content-Length", String.valueOf(entitydata.length));
            urlCon.setRequestMethod("POST");
            urlCon.setDoOutput(true);
            urlCon.setDoInput(true);
            urlCon.connect();

            // 把封装好的实体数据发送到输出流
            OutputStream outStream = urlCon.getOutputStream();
            outStream.write(entitydata);
            outStream.flush();
            outStream.close();

            // 服务器返回输入流并读写
            BufferedReader in = new BufferedReader(new InputStreamReader(urlCon.getInputStream()));
            String line;
            StringBuilder ret = new StringBuilder();
            while ((line = in.readLine()) != null) {
                ret.append(line);
            }
            in.close();

            return ret.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return "";
    }

    static private HttpEntity syncGetEntity(final String urlString) {
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, timeoutConnection);
        HttpConnectionParams.setSoTimeout(httpParams, timeoutSocket);
        /* 建立HTTP Post连线 */
        try {
            HttpGet get = new HttpGet(urlString);
            // 取得HTTP response
            HttpResponse httpResponse = new DefaultHttpClient(httpParams).execute(get);

            // 若状态码为200 ok
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                // 取出回应字串
                return httpResponse.getEntity();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static public String syncGetString(final String url) {
        return syncGetString(url, HTTP.UTF_8);
    }

    static public String syncGetString(final String url, String charset) {
        HttpEntity entity = syncGetEntity(url);
        if (entity != null) {
            try {
                String ret = EntityUtils.toString(entity, charset);
                return ret;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    static public byte[] syncGetBytes(final String url) {
        HttpEntity entity = syncGetEntity(url);
        if (entity != null) {
            try {
                return EntityUtils.toByteArray(entity);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    static public void asyncGetString(final String urlString,
                                      final WeakReference<HttpQueryCallback> weakCallback) {
        asyncGetString(urlString, HTTP.UTF_8, null, weakCallback);
    }

    static public void asyncGetString(final String urlString,
                                      final String charset, final Object queryId,
                                      final WeakReference<HttpQueryCallback> weakCallback) {
        (new Thread(new Runnable() {
            @Override
            public void run() {
                String result = syncGetString(urlString, charset);
                HttpQueryCallback callback = weakCallback.get();
                if (callback != null) {
                    if (result == null) {
                        callback.onQueryComplete(HttpQueryCallback.STATE_ERROR,
                                queryId, null);
                    } else {
                        callback.onQueryComplete(HttpQueryCallback.STATE_OK,
                                queryId, result);
                    }
                }
            }
        })).start();
    }

    static public void asyncGetBytes(final String urlString,
                                     final WeakReference<HttpQueryCallback> weakCallback) {
        asyncGetBytes(urlString, null, weakCallback);
    }

    static public void asyncGetBytes(final String urlString, final Object queryId,
                                     final WeakReference<HttpQueryCallback> weakCallback) {
        (new Thread(new Runnable() {
            @Override
            public void run() {
                byte[] result = syncGetBytes(urlString);
                HttpQueryCallback callback = weakCallback.get();
                if (callback != null) {
                    if (result == null) {
                        callback.onQueryComplete(HttpQueryCallback.STATE_ERROR,
                                queryId, null);
                    } else {
                        callback.onQueryComplete(HttpQueryCallback.STATE_OK,
                                queryId, result);
                    }
                }
            }
        })).start();
    }

    static public String syncPost(final String urlString, final String postStr, boolean encrypt, String charset) {
        try {
            return syncPost(urlString, new StringEntity(postStr, charset), encrypt, charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    static private String syncPost(final String urlString, final HttpEntity postEntity, boolean encrypt, String charset) {
        HttpParams httpParams = new BasicHttpParams();
        HttpConnectionParams.setConnectionTimeout(httpParams, timeoutConnection);
        HttpConnectionParams.setSoTimeout(httpParams, timeoutSocket);
        /* 建立HTTP Post连线 */
        HttpPost post = new HttpPost(urlString);
        try {
            // 发出HTTP request
            if (postEntity != null) {
                post.setEntity(postEntity);
            }
            if (encrypt) {
                post.setHeader("ecrypt", "true");
            }
            //创建HttpContext
            HttpContext localContext = new BasicHttpContext();
            localContext.setAttribute(ClientContext.COOKIE_STORE, localCookies);
            // 取得HTTP response
            DefaultHttpClient defaultHttpClient = new DefaultHttpClient(httpParams);
            HttpResponse httpResponse = defaultHttpClient.execute(post, localContext);
            // 若状态码为200 ok
            if (httpResponse.getStatusLine().getStatusCode() == 200) {
                // 取出回应字串
                HttpEntity entity = httpResponse.getEntity();
                if (entity != null) {
                    return EntityUtils.toString(entity, charset);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    static public String syncPost(final String url, final String postStr) {
        return syncPost(url, postStr, false, HTTP.UTF_8);
    }

    static public String syncPost(final String url, final List<NameValuePair> postData) {
        return syncPost(url, postData, false, HTTP.UTF_8);
    }

    static public String syncPost(final String url, final List<NameValuePair> postData, boolean encrypt, String charset) {
        try {
            return syncPost(url, new UrlEncodedFormEntity(postData, charset), encrypt, charset);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return "";
    }

    static public void asyncPost(final String urlString,
                                 final String postStr, final boolean encrypt,
                                 final String charset, final Object queryId,
                                 final WeakReference<HttpQueryCallback> weakCallback) {
        (new Thread(new Runnable() {
            @Override
            public void run() {
                String result = syncPost(urlString, postStr, encrypt, charset);
                HttpQueryCallback callback = weakCallback.get();
                if (callback != null) {
                    if (result == null || result.equals("")) {
                        callback.onQueryComplete(HttpQueryCallback.STATE_ERROR, queryId, null);
                    } else {
                        callback.onQueryComplete(HttpQueryCallback.STATE_OK, queryId, result);
                    }
                }
            }
        })).start();
    }

    static public void asyncPost(final String urlString,
                                 final String postStr, final Object queryId,
                                 final WeakReference<HttpQueryCallback> weakCallback) {
        asyncPost(urlString, postStr, false, HTTP.UTF_8, queryId, weakCallback);
    }

    static public void asyncPost(final String urlString,
                                 final List<NameValuePair> postData, final boolean encrypt,
                                 final String charset, final Object queryId,
                                 final WeakReference<HttpQueryCallback> weakCallback) {
        (new Thread(new Runnable() {
            @Override
            public void run() {
                String result = syncPost(urlString, postData, encrypt, charset);
                HttpQueryCallback callback = weakCallback.get();
                if (callback != null) {
                    if (result == null || result.equals("")) {
                        callback.onQueryComplete(HttpQueryCallback.STATE_ERROR, queryId, null);
                    } else {
                        callback.onQueryComplete(HttpQueryCallback.STATE_OK, queryId, result);
                    }
                }
            }
        })).start();
    }

    static public void asyncPost(final String urlString,
                                 final List<NameValuePair> postData, final Object queryId,
                                 final WeakReference<HttpQueryCallback> weakCallback) {
        asyncPost(urlString, postData, false, HTTP.UTF_8, queryId, weakCallback);
    }

    enum Method {
        GET, POST
    }

    //	private static String convertNameValuePair(List<NameValuePair> list) {
//		StringBuilder sb = lastnew StringBuilder();
//		for(int i = 0; i < list.size(); i++) {
//			if(i != 0) {
//				sb.append('&');
//			}
//			sb.append(list.get(i).getName());
//			sb.append('=');
//			sb.append(list.get(i).getValue());
//		}
//		
//		return URLEncoder.encode(sb.toString());
//	}
    interface HttpQueryCallback {
        int STATE_OK = 0;
        int STATE_ERROR = 1;

        void onQueryComplete(final int state, final Object requestId, final Object result);
    }
}
