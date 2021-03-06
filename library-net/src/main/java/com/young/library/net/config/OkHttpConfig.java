package com.young.library.net.config;

import android.content.Context;
import android.text.TextUtils;

import com.young.library.net.cookie.CookieJarImpl;
import com.young.library.net.cookie.store.CookieStore;
import com.young.library.net.http.HttpClient;
import com.young.library.net.http.SSLUtils;
import com.young.library.net.interceptor.HeaderInterceptor;
import com.young.library.net.interceptor.NetCacheInterceptor;
import com.young.library.net.interceptor.NoNetCacheInterceptor;
import com.young.library.net.interceptor.RxHttpLogger;

import java.io.File;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * @author dupeng
 * @version 2.6.0, 2019/3/26  10:32 PM
 * @since android 17MiddleTeacher
 */
public class OkHttpConfig {

    private static String defaultCachePath;
    private static final long defaultCacheSize = 1024 * 1024 * 100;
    private static final long defaultTimeout = 10;


    private static OkHttpConfig instance;

    private static OkHttpClient.Builder okHttpClientBuilder;

    private static OkHttpClient okHttpClient;

    public OkHttpConfig() {
        okHttpClientBuilder = new OkHttpClient.Builder();
    }

    public static OkHttpConfig getInstance() {

        if (instance == null) {
            synchronized (HttpClient.class) {
                if (instance == null) {
                    instance = new OkHttpConfig();
                }
            }

        }
        return instance;
    }

    public static OkHttpClient getOkHttpClient() {
        return okHttpClient;
    }

    public static class Builder {
        public Context context;
        private Map<String, Object> headerMaps;
        private boolean isDebug;
        private boolean isCache;
        private String cachePath;
        private long cacheMaxSize;
        private CookieStore cookieStore;
        private long readTimeout;
        private long writeTimeout;
        private long connectTimeout;
        private InputStream bksFile;
        private String password;
        private InputStream[] certificates;
        private Interceptor[] interceptors;

        public Builder(Context context) {
            this.context = context;
        }

        public Builder setHeaders(Map<String, Object> headerMaps) {
            this.headerMaps = headerMaps;
            return this;
        }

        public Builder setDebug(boolean isDebug) {
            this.isDebug = isDebug;
            return this;
        }

        public Builder setCache(boolean isCache) {
            this.isCache = isCache;
            return this;
        }

        public Builder setCachePath(String cachePath) {
            this.cachePath = cachePath;
            return this;
        }

        public Builder setCacheMaxSize(long cacheMaxSize) {
            this.cacheMaxSize = cacheMaxSize;
            return this;
        }

        public Builder setCookieType(CookieStore cookieStore) {
            this.cookieStore = cookieStore;
            return this;
        }

        public Builder setReadTimeout(long readTimeout) {
            this.readTimeout = readTimeout;
            return this;
        }

        public Builder setWriteTimeout(long writeTimeout) {
            this.writeTimeout = writeTimeout;
            return this;
        }

        public Builder setConnectTimeout(long connectTimeout) {
            this.connectTimeout = connectTimeout;
            return this;
        }

        public Builder setAddInterceptor(Interceptor... interceptors) {
            this.interceptors = interceptors;
            return this;
        }

        public Builder setSslSocketFactory(InputStream... certificates) {
            this.certificates = certificates;
            return this;
        }

        public Builder setSslSocketFactory(InputStream bksFile, String password, InputStream... certificates) {
            this.bksFile = bksFile;
            this.password = password;
            this.certificates = certificates;
            return this;
        }


        public OkHttpClient build() {

            OkHttpConfig.getInstance();

            setCookieConfig();
            setCacheConfig();
            setHeadersConfig();
            setSslConfig();
            addInterceptors();
            setTimeout();
            setDebugConfig();
            okHttpClient = okHttpClientBuilder.build();
            return okHttpClient;
        }

        private void  addInterceptors() {
            if (null != interceptors) {
                for (Interceptor interceptor : interceptors) {
                    okHttpClientBuilder.addInterceptor(interceptor);
                }
            }
        }

        /**
         * ??????????????????
         */
        private void setDebugConfig() {
            if (isDebug) {
                HttpLoggingInterceptor logInterceptor = new HttpLoggingInterceptor(new RxHttpLogger());
                logInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                okHttpClientBuilder.addInterceptor(logInterceptor);
            }
        }


        /**
         * ??????headers
         */
        private void setHeadersConfig() {
            okHttpClientBuilder.addInterceptor(new HeaderInterceptor(headerMaps));
        }

        /**
         * ??????cookie?????????sp?????????
         */
        private void setCookieConfig() {
            if (null != cookieStore) {
                okHttpClientBuilder.cookieJar(new CookieJarImpl(cookieStore));
            }
        }

        /**
         * ????????????
         */
        private void setCacheConfig() {
            File externalCacheDir = context.getExternalCacheDir();
            if (null == externalCacheDir) {
                return;
            }
            defaultCachePath = externalCacheDir.getPath() + "/RxHttpCacheData";
            if (isCache) {
                Cache cache;
                if (!TextUtils.isEmpty(cachePath) && cacheMaxSize > 0) {
                    cache = new Cache(new File(cachePath), cacheMaxSize);
                } else {
                    cache = new Cache(new File(defaultCachePath), defaultCacheSize);
                }
                okHttpClientBuilder
                        .cache(cache)
                        .addInterceptor(new NoNetCacheInterceptor())
                        .addNetworkInterceptor(new NetCacheInterceptor());
            }
        }

        /**
         * ??????????????????
         */
        private void setTimeout() {
            okHttpClientBuilder.readTimeout(readTimeout == 0 ? defaultTimeout : readTimeout, TimeUnit.SECONDS);
            okHttpClientBuilder.writeTimeout(writeTimeout == 0 ? defaultTimeout : writeTimeout, TimeUnit.SECONDS);
            okHttpClientBuilder.connectTimeout(connectTimeout == 0 ? defaultTimeout : connectTimeout, TimeUnit.SECONDS);
            okHttpClientBuilder.retryOnConnectionFailure(true);
        }

        /**
         * ????????????
         */
        private void setSslConfig() {
            SSLUtils.SSLParams sslParams = null;

            if (null == certificates) {
                //??????????????????,??????????????????
                sslParams = SSLUtils.getSslSocketFactory();
            } else {
                if (null != bksFile && !TextUtils.isEmpty(password)) {
                    //??????bks????????????????????????????????????????????????????????????????????????????????????????????????????????????????????????
                    sslParams = SSLUtils.getSslSocketFactory(bksFile, password, certificates);
                } else {
                    //???????????????????????????????????????????????????????????????
                    sslParams = SSLUtils.getSslSocketFactory(certificates);
                }
            }

            okHttpClientBuilder.sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager);

        }
    }
}

