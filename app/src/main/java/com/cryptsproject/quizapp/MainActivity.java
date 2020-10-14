package com.cryptsproject.quizapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.print.PrinterId;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private final String SCORE_KEY = "SCORE";
    private final String INDEX_KEY = "INDEX";

    private TextView mTextQuestion;
    private ProgressBar mProgressBar;
    private TextView mTextQuizStats;
    private Button btnTrue;
    private Button btnWrong;
    private int mQuestionIndex;
    protected int mQuizQuestion;

    private int mUserScore;

    private QuizModel[] myCollection = new QuizModel[]{
            new QuizModel(R.string.q1, true),
            new QuizModel(R.string.q2, false),
            new QuizModel(R.string.q3, true),
            new QuizModel(R.string.q4, false),
            new QuizModel(R.string.q5, true),
            new QuizModel(R.string.q6, false),
            new QuizModel(R.string.q7, true),
            new QuizModel(R.string.q8, false),
            new QuizModel(R.string.q9, true),
            new QuizModel(R.string.q10, false),

    };

    final int USER_PROGRESS = (int) Math.ceil(100 / myCollection.length);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null){
            mUserScore = savedInstanceState.getInt(SCORE_KEY);
            mQuestionIndex = savedInstanceState.getInt(INDEX_KEY);
        }else {
            mUserScore = 0;
            mQuestionIndex = 0;
        }


        Toast.makeText(this, "onCreate method is called", Toast.LENGTH_SHORT).show();
        Log.d("lifecycle", "onCreate method is called");

        mTextQuestion = findViewById(R.id.txtQuestion);
        btnTrue = findViewById(R.id.btnTrue);
        btnWrong = findViewById(R.id.btnWrong);

        QuizModel q1 = myCollection[mQuestionIndex];
        mQuizQuestion = q1.getQuestion();
        mTextQuestion.setText(mQuizQuestion);

        mProgressBar = findViewById(R.id.quizPb);
        mTextQuizStats = findViewById(R.id.txtQuizStats);
        mTextQuizStats.setText(mUserScore + "");

        View.OnClickListener myClickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (view.getId() == R.id.btnTrue){
                    evaluateUserAnswer(true);
                    changeQuestionButtonClick();

                }else  {
                    evaluateUserAnswer(false);
                    changeQuestionButtonClick();

                }
            }
        };
        btnTrue.setOnClickListener(myClickListener);
        btnWrong.setOnClickListener(myClickListener);
    }

    private void changeQuestionButtonClick(){

        mQuestionIndex = (mQuestionIndex + 1) % 10;

        if (mQuestionIndex == 0){
            AlertDialog.Builder quizAlert = new AlertDialog.Builder(this);
            quizAlert.setTitle("The Quiz is Finished");
            quizAlert.setMessage("Your Score is " + mUserScore);
            quizAlert.setCancelable(false);
            quizAlert.setPositiveButton("Finish the Quiz", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();
                }
            });

            quizAlert.show();

        }


        mQuizQuestion = myCollection[mQuestionIndex].getQuestion();
        mTextQuestion.setText(mQuizQuestion);
        mProgressBar.incrementProgressBy(USER_PROGRESS);
        mTextQuizStats.setText(mUserScore + "");
    }

    private void evaluateUserAnswer(boolean userGuess){

        boolean currentQuestionAnswer = myCollection[mQuestionIndex].isAnswer();
        if (currentQuestionAnswer == userGuess){
            Toast.makeText(getApplicationContext(), R.string.correct_toast_message, Toast.LENGTH_SHORT).show();
            mUserScore = mUserScore + 1;
        }else {
            Toast.makeText(getApplicationContext(),  R.string.incorrect_text_message, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(getApplicationContext(), "onStart method is called", Toast.LENGTH_SHORT).show();
        Log.d("lifecycle", "onStart method is called");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(getApplicationContext(), "onResume method is called", Toast.LENGTH_SHORT).show();
        Log.d("lifecycle", "onResume method is called");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(getApplicationContext(), "onPause method is called", Toast.LENGTH_SHORT).show();
        Log.d("lifecycle", "onPause method is called");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(getApplicationContext(), "onStop method is called", Toast.LENGTH_SHORT).show();
        Log.d("lifecycle", "onStop method is called");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(), "onDestroy method is called", Toast.LENGTH_SHORT).show();
        Log.d("lifecycle", "onDestroy method is called");
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putInt(SCORE_KEY, mUserScore);
        outState.putInt(INDEX_KEY, mQuestionIndex);
    }
}