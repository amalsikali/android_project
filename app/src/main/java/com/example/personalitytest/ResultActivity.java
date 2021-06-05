package com.example.personalitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

import static java.lang.Integer.parseInt;

public class ResultActivity extends AppCompatActivity {
  TextView tvresultat;
  Button btnagain,btnlogout;
  public String intentscoreA,intentscoreB;
  private FirebaseAuth fAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        tvresultat=findViewById(R.id.tvresultat);
        btnagain=findViewById(R.id.btnagain);
        btnlogout=findViewById(R.id.btnlogout);
        intentscoreA=getIntent().getStringExtra("ra");

        int A= Integer.parseInt(intentscoreA);
        if ( A<4){
            tvresultat.setText("Tu es une personne trés egoiste,trés peu de gens aimeraient travailler avec toi dans une equipe." +
                    "Mais ce n'est pas si mal que ca,cela signifie simplement que tu préfère te battre pour obtenir exactement ce que tu veux   ");
        }else if (A>6){
                tvresultat.setText("Tu es une personne qui est toujours prete à sacrifier tes propre intérets pour quelqu'un d'autre." +
                        "Mais obtiendras-tu un jour ce que tu veux dans la vie si tu abondonnes facilement pour laisser place ax autres?");
        }
        else{
            tvresultat.setText("Cela signifie que tu es modérement égoiste.Tu es un peut comme la boucle d'or de cette liste:ni trop peu,ni trop:juste parfait" +
                    "Tu peux etre égoiste quand tu as besoin de l'etre");
            }

        btnlogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fAuth = FirebaseAuth.getInstance();
                fAuth.signOut();
                new TestActivity();
                Intent intent =new Intent(ResultActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

        btnagain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                TestActivity.rA=null;

                Intent intent =new Intent(ResultActivity.this,new TestActivity().getClass());
                startActivity(intent);

            }
        });


    }
}