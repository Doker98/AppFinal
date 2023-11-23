package cl.ipvg.appfinal;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
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

import cl.ipvg.appfinal.modelos.User;

public class RegistrarActivity2 extends AppCompatActivity {

    FirebaseAuth mAuth;
    DatabaseReference mDatabase;

    SharedPreferences mPref;

    //vistas

    Button mbuttonregistrar;
    TextInputEditText mtextimputnombre;
    TextInputEditText mtextimputemail;
    TextInputEditText mtextimputcontraseña;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar2);

        mPref= getApplicationContext().getSharedPreferences("typeUser",MODE_PRIVATE);




        mAuth=FirebaseAuth.getInstance();

        mDatabase= FirebaseDatabase.getInstance().getReference();
        mbuttonregistrar = findViewById(R.id.btnregistrar);
        mtextimputnombre = findViewById(R.id.textimputnombre);
        mtextimputemail = findViewById(R.id.textimputemail);
        mtextimputcontraseña = findViewById(R.id.textimputpassword);

        mbuttonregistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });


    }

    private void registerUser() {

        String nombre= mtextimputnombre.getText().toString();
        String email= mtextimputemail.getText().toString();
        String contraseña= mtextimputcontraseña.getText().toString();

        if (!nombre.isEmpty() && !email.isEmpty() && !contraseña.isEmpty()){
            if (contraseña.length() >=6){
                mAuth.createUserWithEmailAndPassword(email,contraseña).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()){
                            String id = mAuth.getCurrentUser().getUid();
                            saveUser(id,nombre, email);

                        }
                        else{
                            Toast.makeText(RegistrarActivity2.this,"No se pudo registrar el usuario", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
            else {
                Toast.makeText(this,"La contraseña debe tener al menos 6 caracteres", Toast.LENGTH_SHORT).show();
            }
        }
        else {
            Toast.makeText(this,"Ingrese todos los campos", Toast.LENGTH_SHORT).show();
        }

    }

    private void saveUser(String id, String nombre, String email) {
        String selectedUser = mPref.getString("user", "");
        User user= new User();
        user.setEmail(email);
        user.setName(nombre);

        if (selectedUser.equals("driver")){
            mDatabase.child("Users").child("Drivers").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegistrarActivity2.this, "Registro exitoso",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(RegistrarActivity2.this, "Falló el registro",Toast.LENGTH_SHORT).show();

                    }
                }
            });

        } else if (selectedUser.equals("client")){
            mDatabase.child("Users").child("Clients").child(id).setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                @Override
                public void onComplete(@NonNull Task<Void> task) {
                    if (task.isSuccessful()){
                        Toast.makeText(RegistrarActivity2.this, "Registro exitoso",Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(RegistrarActivity2.this, "Falló el registro",Toast.LENGTH_SHORT).show();

                    }

                }
            });

        }

    }
}