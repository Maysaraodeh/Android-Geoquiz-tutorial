package com.masotech.geoquiz;

import android.os.Bundle;
import android.os.Debug;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends AppCompatActivity {


    // ToDo (1): add all Strings we need to the strings.xml file in the res/values/strings.xml.
    // Todo (3) : declare the Buttons and TextViews that we need.
    private ImageButton mTrueButton;
    private ImageButton mFalseButton;
    private ImageButton mNextButton;
    private TextView mQuestionTextView;

    // ToDo (4): create an array of questions called mQuestionBank .

    private Question[] mQuestionBank = new Question[] {
            new Question(R.string.question_australia, true),
            new Question(R.string.question_oceans, true),
            new Question(R.string.question_mideast, false),
            new Question(R.string.question_africa, false),
            new Question(R.string.question_americas, true),
            new Question(R.string.question_asia, true),
    };

    //ToDo (5) : create and initialize mCurrentIndex Variable (we need this variable to get a specific question from the array ).
    private int mCurrentIndex = 0;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_new_design);
        //ToDo (6): get a reference to the question_tv , true_btn , false_btn and next_btn.
        mQuestionTextView = (TextView) findViewById(R.id.question_text_view);
        mTrueButton = (ImageButton) findViewById(R.id.true_button);
        mFalseButton = (ImageButton) findViewById(R.id.false_button);
        mNextButton = (ImageButton) findViewById(R.id.next_button);

        // ToDo (7) : set onClickListener for the True_btn.
        mTrueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(true);
            }
        });

        // ToDo (8) : set onClickListener for the False_btn.
        mFalseButton.setOnClickListener( new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                checkAnswer(false);
            }
        });

        // ToDo (9) : set onClickListener for the Next_btn.
        mNextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCurrentIndex = (mCurrentIndex + 1) % mQuestionBank.length;
                updateQuestion();
            }
        });

        // ToDo (11) : update the question for the first time.
        updateQuestion();
    }

    // ToDo (10): create a method called updateQuestion which contains an integer represents the questionText resource id
    // and then set the question_tv to the value of this resource id.
    private void updateQuestion() {
        int question = mQuestionBank[mCurrentIndex].getTextResId();
        mQuestionTextView.setText(question);
    }

    //ToDo (12) : create check answer method to compare between the passed answer and the exact answer for a specific question.
    private void checkAnswer(boolean userPressedTrue) {
        boolean answerIsTrue = mQuestionBank[mCurrentIndex].isAnswerTrue();

        int messageResId = 0;
        // compare the passed answer from the button we clicked with the exact answer for this question
        if (userPressedTrue == answerIsTrue) {
            messageResId = R.string.correct_toast;
        } else {
            messageResId = R.string.incorrect_toast;
        }

        Toast.makeText(this, messageResId, Toast.LENGTH_SHORT)
                .show();
    }
}
