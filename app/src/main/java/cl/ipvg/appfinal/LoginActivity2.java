package cl.ipvg.appfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity2 extends AppCompatActivity {

    TextInputEditText mtextimputemail;
    TextInputEditText mtextimputpassword;
    Button mbtnlogin;

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        mtextimputemail = findViewById(R.id.textimputemail);
        mtextimputpassword= findViewById(R.id.textimputpassword);
        mbtnlogin = findViewById(R.id.btnlogin);

        mAuth = FirebaseAuth.getInstance();
        mDatabase= FirebaseDatabase.getInstance().getReference();

        mbtnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });
    }

    private void login() {

        String email= mtextimputemail.getText().toString();
        String password = mtextimputpassword.getText().toString();

        if (!email.isEmpty()&& !password.isEmpty()){
            if (password.length() >=6){
                mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            Toast.makeText(LoginActivity2.this,"El registro se realizo exitosamente ", Toast.LENGTH_SHORT).show();

                        }
                        else {
                            Toast.makeText(LoginActivity2.this,"La contraseña es incorrecta", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        }

    }
}