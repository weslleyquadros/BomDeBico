package com.example.weslleyq.bomdebico;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Perfil extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener {
   private ImageView ivFoto;
    private TextView textEMail, textID;
    private Button btnSignOut;
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthStateListener;
    private  FirebaseUser mFirebaseUser;
    private GoogleApiClient mGoogleApiClient;


    private FirebaseAuth auth;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        inicializaComponentes();
        inicializaFirebase();
       clicksButton();
        conectarGoogleApi();
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
    private void inicializaFirebase() {
   mFirebaseAuth = FirebaseAuth.getInstance();

   mAuthStateListener = new FirebaseAuth.AuthStateListener() {
       @Override
       public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
           mFirebaseUser = firebaseAuth.getCurrentUser();
           if(mFirebaseUser != null){

               exibirDados(mFirebaseUser);


           }else
               finish();

       }
   };

    }

    private void exibirDados(FirebaseUser mFirebaseUser) {
        textEMail.setText(mFirebaseUser.getEmail());
        //textID.setText(mFirebaseUser.getUid());
        textID.setText(mFirebaseUser.getDisplayName());

        Glide.with(Perfil.this).load(mFirebaseUser.getPhotoUrl()).into(ivFoto);


    }



    private void clicksButton() {
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               signOut();
                Conexao.logOut();
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.conteudo, new AnuncioFragment());
                transaction.commit();
            }
        });
    }

    private void signOut() {

        mFirebaseAuth.signOut();
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(new ResultCallback<Status>() {
            @Override
            public void onResult(@NonNull Status status) {
                alert("Conta desconectada");

            }
        });
    }


    private void inicializaComponentes() {
        textEMail = (TextView) findViewById(R.id.textEmail);
        textID = (TextView) findViewById(R.id.textID);
        btnSignOut = (Button) findViewById(R.id.botaoSignOut );
        ivFoto = (ImageView) findViewById(R.id.ivFoto);
    }

    @Override
    protected void onStart(){

       super.onStart();
        mFirebaseAuth.addAuthStateListener(mAuthStateListener);
       // auth = Conexao.getFirebaseAuth();
        //user = Conexao.getFirebaseUser();

        //erificaUser();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mFirebaseAuth.removeAuthStateListener(mAuthStateListener);
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        alert("Falha na conexão");
    }
    private void alert(String s){
        Toast.makeText(Perfil.this, s, Toast.LENGTH_SHORT).show();

    }
/*  private void verificaUser() {

        if(user == null){

            finish();
        }else{
            textEMail.setText("Email:" + user.getEmail());
            textID.setText("ID:" + user.getUid());
        }
    }
*/
}
