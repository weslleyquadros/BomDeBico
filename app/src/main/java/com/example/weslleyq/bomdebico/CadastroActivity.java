package com.example.weslleyq.bomdebico;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class CadastroActivity extends AppCompatActivity {

    private EditText editEmail, editSenha;
    private Button btnRegistrar, btnVoltar;
    private FirebaseAuth auth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
        inicializaComponentes();
        eventoClicks();
    }

    private void inicializaComponentes() {
        editEmail = (EditText) findViewById(R.id.CadastroEmail);
        editSenha = (EditText) findViewById(R.id.CadastroSenha);
        btnRegistrar = (Button)  findViewById(R.id.buttonCadastrar);
        btnVoltar = (Button) findViewById(R.id.botaoVoltar);

    }
    protected void onStart(){
        super.onStart();

        auth = Conexao.getFirebaseAuth();



    }

    private void eventoClicks() {
        btnVoltar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }

        });
        btnRegistrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = editEmail.getText().toString().trim();
                String senha = editSenha.getText().toString().trim();

                criarUser(email,senha);
            }
        });
    }

    private void criarUser(String email, String senha) {

        auth.createUserWithEmailAndPassword(email,senha).addOnCompleteListener(CadastroActivity.this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    alert("Usuario cadastrado com sucesso");
                    Intent i = new Intent(CadastroActivity.this, PerfilFragment.class);
                    startActivity(i);
                    finish();
                }
                else{
                    alert("Erro ao cadastrar");


                }
            }
        });
    }
    private void alert(String msg){

        Toast.makeText(CadastroActivity.this,msg, Toast.LENGTH_SHORT).show();
    }
}
