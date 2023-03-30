package devandroid.elismar.appdegas.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import devandroid.elismar.appdegas.R;
import devandroid.elismar.appdegas.database.DeGasDB;

public class SplashActivity extends AppCompatActivity {

    public static final int TIME_OUT_SPLASH = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        comutarTelaSplash();
    }

    private void comutarTelaSplash() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                DeGasDB db = new DeGasDB(SplashActivity.this);
                Intent telaPrincipal = new Intent(SplashActivity.this, DeGasActivity.class);
                startActivity(telaPrincipal);
                finish();
            }
        }, TIME_OUT_SPLASH);
    }
}