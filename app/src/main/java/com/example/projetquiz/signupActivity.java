package com.example.projetquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.projetquiz.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class signupActivity extends AppCompatActivity {

    private ActivitySignupBinding binding;
    private FirebaseAuth auth;
    private FirebaseFirestore database;
    private ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseFirestore.getInstance();
        dialog = new ProgressDialog(this);

        binding.signupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email, password, nom;
                email = binding.emailBox.getText().toString();
                password = binding.pswBox.getText().toString();
                nom = binding.nomBox.getText().toString();

                auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Créer un nouvel utilisateur avec les données saisies"mimounahabib@gmail.com"
                            final User user = new User(nom, email, password, "user");

                            // Ajouter l'utilisateur à Firestore
                            database.collection("users")
                                    .document(auth.getCurrentUser().getUid())
                                    .set(user)
                                    .addOnCompleteListener(new OnCompleteListener<Void>() {
                                        @Override
                                        public void onComplete(@NonNull Task<Void> task) {
                                            if (task.isSuccessful()) {
                                                // Rediriger en fonction du rôle
                                                if (user.getRole().equals("admin")) {
                                                    startActivity(new Intent(signupActivity.this, MainActivity.class));
                                                } else if (user.getRole().equals("user")) {
                                                    startActivity(new Intent(signupActivity.this, userActivity.class));
                                                }
                                                finish();
                                            } else {
                                                // Afficher un message d'erreur en cas d'échec
                                                Toast.makeText(signupActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                                            }
                                        }
                                    });
                            Toast.makeText(signupActivity.this, "Success", Toast.LENGTH_LONG).show();
                        } else {
                            // Afficher un message d'erreur en cas d'échec
                            Toast.makeText(signupActivity.this, task.getException().getLocalizedMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
            }
        });

        binding.loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(signupActivity.this, loginActivity.class));
            }
        });
    }
}
