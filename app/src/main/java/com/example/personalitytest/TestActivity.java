package com.example.personalitytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Locale;

public class TestActivity extends AppCompatActivity {
    private static final long countdowninmillis=10000;
    private ColorStateList countdefaultcolor;
    private CountDownTimer countDownTimer;
    private long timeleft;
    TextView tvquestion,tvtimer ;
    RadioButton rbA,rbB;
    Button confirm;
    RadioGroup rbgroup;
    TextView tvquestioncount;
    ArrayList<Question> questions;
    public static int index,scoreA,scoreB;
    public static String rA;
    private Question currentQuestion;
    private boolean answered;
    private Long backpressedtime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        tvtimer=findViewById(R.id.tvtimer);
        tvquestion= findViewById(R.id.tvquestion);
        rbgroup= findViewById(R.id.rbgroup);
        rbA= findViewById(R.id.rbA);
        rbB= findViewById(R.id.rbB);
        tvquestioncount=findViewById(R.id.tvquestioncount);
        confirm=findViewById(R.id.confirm);

        questions =new ArrayList<Question>();
        questions.add(new Question("Choix N°1","Etre la personne la plus intelligente","Etre la personne le plus charmante"));
        questions.add(new Question("Choix N°2","Devoir toujours dire la vérité ","Devoir toujours mentir"));
        questions.add(new Question("Choix N°3","Ne plus jamais avoir accès à internet","Ne jamais prendre un avions de ta vie"));
        questions.add(new Question("Choix N°4","Trouvez le grand amour","Gagner un millon d'euros"));
        questions.add(new Question("Choix N°5","Ne jamais pouvoir parler de noveau","Toujours dire tout ce qui te passe par l'esprit"));
        questions.add(new Question("Choix N°6","Avoir une mémoire photographique","Etre capable d'oublier tout ce que tu veux"));
        questions.add(new Question("Choix N°7","Neplus jamais faire l'amour","Ne plus jamais manger ta nourriture préférée"));
        questions.add(new Question("Choix N°8","Etre la seule personne au monde à avoir une vie heureuse","Etre la seule personne au monde à avoir une vie malheureuse"));
        questions.add(new Question("Choix N°9","Etre la première personne à découvrir une planète habitable","Etre l'inventeur d'un médicament qui guérit une maladie mortelle'"));
        questions.add(new Question("Choix N°10","Etre pauvre mais capable d'aider les gens","Devenir incroyablement riche en blessant les autres"));

        showNextQuestion();
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!answered) {
                    if (rbA.isChecked() || rbB.isChecked()) {
                        checkAnswer();
                    } else {
                        Toast.makeText(TestActivity.this, "Please select an answer", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    showNextQuestion();
                }
            }
        });

        countdefaultcolor=tvtimer.getTextColors();

    }



    private void showNextQuestion(){
        rbgroup.clearCheck();
        if(index < questions.size()){
            currentQuestion = questions.get(index);
            tvquestion.setText(currentQuestion.getQuestion());
            rbA.setText(currentQuestion.getAnswerA());
            rbB.setText(currentQuestion.getAnswerB());
            index++;
            tvquestioncount.setText("Question: " + index+ "/" + questions.size());
            answered=false;
            confirm.setText("Confirm");
            timeleft=countdowninmillis;
            startCountdown();
        }else{
            index=0;
            finishTest();
        }
    }


    private void startCountdown(){
        countDownTimer=new CountDownTimer(timeleft,1000) {//2eme parametre = chaque1s fait appel a onTick
            @Override
            public void onTick(long l) {
                timeleft=l;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
             timeleft=0;
             updateCountDownText();
             checkAnswer();
            }
        }.start();

    }


    private void updateCountDownText(){
        int minutes = (int) (timeleft / 1000) / 60;
        int seconds = (int) (timeleft/ 1000) % 60;
        String timeFormatted = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        tvtimer.setText(timeFormatted);
        if (timeleft < 5000) {
            tvtimer.setTextColor(Color.RED);
        } else {
            tvtimer.setTextColor(countdefaultcolor);
        }
    }

    private void finishTest()
    {
        Intent intent =new Intent(TestActivity.this,ResultActivity.class);
        rA=scoreA+"";
       intent.putExtra("ra",rA);
       startActivity(intent);
        rbgroup.clearCheck();
        scoreA=0;
        finish();
    }


    private void checkAnswer() {
        answered=true;
        countDownTimer.cancel(); // to stop the timer when you pick the answer
        RadioButton rbselected=findViewById(rbgroup.getCheckedRadioButtonId());
        if (rbselected==findViewById(R.id.rbA)){
            scoreA++;
        }else{
            scoreB++;
        }
        showSolution();
    }

    private void showSolution() {
        if(index < questions.size()){
            confirm.setText("suivant");
            //showNextQuestion();
        }else {
            confirm.setText("resulat");
            //finishTest();
        }

    }

    @Override
    public void onBackPressed() {
        backpressedtime= System.currentTimeMillis();
        if (backpressedtime+1000> System.currentTimeMillis()){
            finish();
        }
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }
}