package com.rishistech.onlinecart;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {

    ImageButton backRegister_btn;
    EditText emailRegister_txt,passwordRegister_txt;
    Button register_btn;
    TextView needHelp_btn;
    TextView tpRegister_btn;
    SignInButton registerWithGoogle_btn;
    //for signin with google
    private GoogleSignInClient mGoogleSignInClient;
    private final static int RC_SIGN_IN = 123; //
    private FirebaseAuth mAuth;
    //after register add the data to the realtime firebase
    private FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user != null){
            Intent i = new Intent(RegisterActivity.this, HomePage.class);
            startActivity(i);
            finish();
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        backRegister_btn = findViewById(R.id.backRegister_btn);
        emailRegister_txt = findViewById(R.id.emailRegister_txt);
        passwordRegister_txt = findViewById(R.id.passwordRegister_txt);
        register_btn = findViewById(R.id.register_btn);
        tpRegister_btn = findViewById(R.id.tpLogin_btn);
        needHelp_btn = findViewById(R.id.needHelp_btn);
        mAuth = FirebaseAuth.getInstance();//
        //for realtime firebase
        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        registerWithGoogle_btn = findViewById(R.id.registerWithGoogle_btn);//

        // Configure Google Sign In
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();
        // Build a GoogleSignInClient with the options specified by gso.
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);

        backRegister_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        register_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = emailRegister_txt.getText().toString();
                String password = passwordRegister_txt.getText().toString();
                if (email.length()==0){
                    emailRegister_txt.requestFocus();
                    emailRegister_txt.setError("Field can not be empty");
                }else if (password.length()==0){
                    passwordRegister_txt.requestFocus();
                    passwordRegister_txt.setError("Password can not be empty");
                }else{
                    mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                //get all the values, save in firebase real data db
                                //we have email and password, rest needed to be full as empty, as constructor requires all values
                                {
                                    FirebaseUser user = mAuth.getCurrentUser();
                                    String id = user.getUid();
                                    String name = "undefined..";
                                    String email = user.getEmail();
                                    String phoneno = "undefined..";
                                    String photourl = "No Image";
                                    String password = passwordRegister_txt.getText().toString();
                                    String money = "2000";
                                    UserHelperClass helperClass = new UserHelperClass(id,name,email,phoneno,photourl,password,money);
                                    reference.child(id).setValue(helperClass).addOnSuccessListener(new OnSuccessListener<Void>() {
                                        @Override
                                        public void onSuccess(Void aVoid) {
                                            Toast.makeText(RegisterActivity.this, "Data Added", Toast.LENGTH_SHORT).show();
                                        }
                                    }).addOnFailureListener(new OnFailureListener() {
                                        @Override
                                        public void onFailure(@NonNull Exception e) {
                                            Toast.makeText(RegisterActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();
                                        }
                                    });


                                }

                            }else{
                                Toast.makeText(RegisterActivity.this, "Invalid Info: "+task.getException(), Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });
        needHelp_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this, helpActivity.class);
                startActivity(i);
            }
        });
        tpRegister_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);
                startActivity(i);
            }
        });
        registerWithGoogle_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signIn();
            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                assert account != null;
                firebaseAuthWithGoogle(account.getIdToken());
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                // ...
                Toast.makeText(this, e.getMessage() , Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            FirebaseUser user = mAuth.getCurrentUser();
                            {
                                String id = user.getUid();
                                String name = user.getDisplayName();
                                String email = user.getEmail();
                                String phoneno = "google dosenot give acces to phone no also?";//user.getPhoneNumber();
                                String photourl = String.valueOf(user.getPhotoUrl());
                                String password = "google psw are hidden although you can change here";
                                String money = "2000";
                                UserHelperClass helperClass = new UserHelperClass(id,name,email,phoneno,photourl,password,money);
                                reference.child(id).setValue(helperClass);

                            }
                            Toast.makeText(RegisterActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(RegisterActivity.this, HomePage.class);
                            startActivity(i);
                            finish();
                        } else {
                            // If sign in fails, display a message to the user.
                            Toast.makeText(RegisterActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                        }
                        // ...
                    }
                });
    }




}
