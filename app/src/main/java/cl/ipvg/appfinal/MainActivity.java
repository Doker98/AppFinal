package cl.ipvg.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button mbuttonsoycliente;
    Button mButtonsoyconductor;

    SharedPreferences mPref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPref = getApplicationContext().getSharedPreferences("typeUser", MODE_PRIVATE);
        SharedPreferences.Editor editor = mPref.edit();

        mbuttonsoycliente = findViewById(R.id.btncliente);
        mButtonsoyconductor= findViewById(R.id.btnconductor);

        mbuttonsoycliente.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("user","client");
                editor.apply();
                goToSelectAuth();
            }
        });
        mButtonsoyconductor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editor.putString("user", "driver");
                editor.apply();
                goToSelectAuth();

            }
        });
    }

    private void goToSelectAuth() {

        Intent intent = new Intent(MainActivity.this, SelectOptionActivity2.class);
        startActivity(intent);
    }
}