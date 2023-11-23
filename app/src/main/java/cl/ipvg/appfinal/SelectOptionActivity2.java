package cl.ipvg.appfinal;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class SelectOptionActivity2 extends AppCompatActivity {

    Button mButtongotologin;
    Button mButtongotoregistro;
    Button btnGoToMap;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_option2);

        btnGoToMap = findViewById(R.id.btnGoToMap);
        mButtongotologin = findViewById(R.id.btngotologin);
        mButtongotoregistro= findViewById(R.id.btngotoregister);
        btnGoToMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToMapActivity();

            }
        });


                mButtongotoregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                goToregister();
            }
        });
        mButtongotologin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                
                goToLogin();
            }
        });
    }

    private void goToMapActivity() {
        Intent intent = new Intent(SelectOptionActivity2.this, MapaActivity.class);
        startActivity(intent);
    }


    private void goToLogin() {
        Intent intent = new Intent(SelectOptionActivity2.this,LoginActivity2.class);
        startActivity(intent);
    }
    private void goToregister() {
        Intent intent = new Intent(SelectOptionActivity2.this,RegistrarActivity2.class);
        startActivity(intent);
    }
}