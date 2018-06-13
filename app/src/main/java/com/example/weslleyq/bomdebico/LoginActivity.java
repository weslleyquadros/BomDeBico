package com.example.weslleyq.bomdebico;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;

import android.widget.Toast;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.GoogleAuthProvider;


public class LoginActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {

        private SignInButton btnSignIn;
        private FirebaseAuth mFireBaseAuth;
        private GoogleApiClient mGoogleApiClient;


        protected void onCreate(Bundle savedInstanceState) {

            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_login);

            inicializarComponentes();
            inicializarFirebase();
            conectarGoogleApi();
            clickButton();
            
        }

    private void clickButton() {
    btnSignIn.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            signIn();
        }
    }


    );
        }

    private void signIn() {

            Intent i = Auth.GoogleSignInApi.getSignInIntent(mGoogleApiClient);
            startActivityForResult(i,1);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if(result.isSuccess()){

                GoogleSignInAccount account = result.getSignInAccount(); // Criar  conta Google
                firebaseLogin(account);
            }

        }
    }

    private void firebaseLogin(GoogleSignInAccount account) { //sintonia do Firebase com a conta do Google
        AuthCredential credential = GoogleAuthProvider.getCredential(account.getIdToken(), null);

        mFireBaseAuth.signInWithCredential(credential)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){ // verifica se o AuthResult ta tudo OK
                            Intent i = new Intent(LoginActivity.this, Perfil.class);
                            startActivity(i);

                        }else
                            alert("Falha na autenticação");
                    }
                });
    }

    private void conectarGoogleApi() {
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder(this)
                .enableAutoManage(this,this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

    }

    private void inicializarFirebase() {
            mFireBaseAuth = FirebaseAuth.getInstance();

    }

    private void inicializarComponentes() {
            btnSignIn = (SignInButton) findViewById(R.id.btnSignIn);

    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        alert("Falha na conexão");
    }
    private void alert(String s){
        Toast.makeText(LoginActivity.this, s, Toast.LENGTH_SHORT).show();

    }



}

