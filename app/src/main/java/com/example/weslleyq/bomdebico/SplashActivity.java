package com.example.weslleyq.bomdebico;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class SplashActivity extends Activity {

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_splash);
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    mostrarHomePage();
                }
            }, 2000);
        }

        private void mostrarHomePage() {
            Intent intent = new Intent(
                    SplashActivity.this,HomePage.class
            );
            startActivity(intent);
            finish();
        }
}

