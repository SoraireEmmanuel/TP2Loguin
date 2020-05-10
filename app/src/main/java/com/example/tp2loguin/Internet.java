package com.example.tp2loguin;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class Internet extends AppCompatActivity {

    //url que se carga en el visor
    String rol ="Administrador";
    String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);

        switch (rol){
            case "CEO": url = "https://es.wikipedia.org/wiki/Director_ejecutivo";
                break;
            case "Director": url = "https://es.wikipedia.org/wiki/Gerente";
                break;
            case "Administrador": url = "https://es.wikipedia.org/wiki/Administrador_de_sistemas";
                break;
            case "Jefe o Supervisor": url = "https://es.wikipedia.org/wiki/Jefe";
                break;
            case "Empleado": url = "https://es.wikipedia.org/wiki/Trabajador";
                break;
        }

        WebView web = (WebView) findViewById(R.id.miVisor);
        web.setWebViewClient(new MyWebViewClient());
        WebSettings settings = web.getSettings();
        settings.setJavaScriptEnabled(true);
        web.loadUrl(url);


    }

    private class MyWebViewClient extends WebViewClient {
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            view.loadUrl(url);
            return true;
        }
    }
}
