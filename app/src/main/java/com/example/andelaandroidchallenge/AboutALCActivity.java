package com.example.andelaandroidchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.net.http.SslError;
import android.os.Bundle;
import android.webkit.SslErrorHandler;

import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class AboutALCActivity extends AppCompatActivity {


    private WebView webView;

    private class WvClient extends WebViewClient
    {
        @Override
        public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError er) {
            handler.proceed();
            // Ignore SSL certificate errors
            try {
                TrustManager[] victimizedManager = new TrustManager[]{

                        new X509TrustManager() {

                            public X509Certificate[] getAcceptedIssuers() {

                                X509Certificate[] myTrustedAnchors = new X509Certificate[0];

                                return myTrustedAnchors;
                            }

                            @Override
                            public void checkClientTrusted(X509Certificate[] certs, String authType) {
                            }

                            @Override
                            public void checkServerTrusted(X509Certificate[] certs, String authType) {
                            }
                        }
                };

                SSLContext sc = SSLContext.getInstance("SSL");
                sc.init(null, victimizedManager, new SecureRandom());
                HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
                HttpsURLConnection.setDefaultHostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_alc);

        // In the on create method initialize the web view

        webView = findViewById(R.id.alc_webview);
        webView.setWebViewClient(new WvClient()); // Let's you not leave the app
        webView.loadUrl("https://andela.com/alc");



    }

    @Override
    public void onBackPressed() { // Override this method to prevent the app from closing
        if (webView.canGoBack()){
            webView.goBack();
        }
        else {
            super.onBackPressed();

        }
    }
}
