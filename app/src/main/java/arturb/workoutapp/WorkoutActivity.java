package arturb.workoutapp;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static android.R.attr.textColorTertiary;
import static android.R.attr.textColorTertiaryInverse;
import static arturb.workoutapp.R.id.pullUpReps;
import static arturb.workoutapp.R.id.pullUpText;

public class WorkoutActivity extends AppCompatActivity
{
    int curSetNo = 1;
    int curEx = 1;
    int sets;

    int maxPullUps, maxDips, maxPushUps;

    int pullUpCount = 0, dipCount = 0, pushUpCount = 0;

    Boolean pullUpActive = true;
    Boolean dipActive = true;
    Boolean pushUpActive = true;

    TextView pullUpText, dipText, pushUpText;
    TextView pullUpReps, dipReps, pushUpReps;
    TextView setsText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        //setupPart
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout);

        pullUpText = (TextView) findViewById(R.id.pullUpText);
        dipText = (TextView) findViewById(R.id.dipText);
        pushUpText = (TextView) findViewById(R.id.pushUpText);

        pullUpReps = (TextView) findViewById(R.id.pullUpReps);
        dipReps = (TextView) findViewById(R.id.dipReps);
        pushUpReps = (TextView) findViewById(R.id.pushUpReps);

        setsText = (TextView) findViewById(R.id.setNoText);

        Bundle extras = getIntent().getExtras();

        maxPullUps = extras.getInt("maxPullUps");
        maxDips = extras.getInt("maxDips");
        maxPushUps = extras.getInt("maxPushUps");

        maxPullUps /= 2;
        maxDips /= 2;
        maxPushUps /= 2;

        sets = max(maxPullUps, maxDips, maxPushUps) + 4;

        //workoutPart

        setsText.setText(curSetNo + "/" + sets);


    }

    public void exPlus(View view)
    {
        switch (curEx)
        {
            case 1:
                pullUpCount+=curSetNo;
                break;
            case 2:
                dipCount+=curSetNo;
                break;
            case 3:
                pushUpCount+=curSetNo;
                break;
        }
        curEx++;
        if (curEx == 4)
        {
            curEx = 1;
            curSetNo += 1;
        }
        if (curSetNo <= sets)
        {
            pullUpReps.setText("" + curSetNo);
            dipReps.setText("" + curSetNo);
            pushUpReps.setText("" + curSetNo);
            setsText.setText(curSetNo + "/" + sets);

            if (maxPullUps == curSetNo)
                pullUpActive = false;
            if (maxDips == curSetNo)
                dipActive = false;
            if (maxPushUps == curSetNo)
                pushUpActive = false;

            if (curEx == 1 && pullUpActive)
            {
                pullUpText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
                pullUpReps.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
                pullUpText.setTypeface(null, Typeface.BOLD);
                pullUpReps.setTypeface(null, Typeface.BOLD);
                pullUpText.setTextColor(Color.rgb(115, 115, 115));
                pullUpReps.setTextColor(Color.rgb(115, 115, 115));
                dipText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
                dipReps.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
                dipText.setTypeface(null, Typeface.NORMAL);
                dipReps.setTypeface(null, Typeface.NORMAL);
                dipText.setTextColor(Color.rgb(115, 115, 115));
                dipReps.setTextColor(Color.rgb(115, 115, 115));
                pushUpText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
                pushUpReps.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
                pushUpText.setTypeface(null, Typeface.NORMAL);
                pushUpReps.setTypeface(null, Typeface.NORMAL);
            }
            else if (curEx == 2 && dipActive)
            {
                pullUpText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
                pullUpReps.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
                pullUpText.setTypeface(null, Typeface.NORMAL);
                pullUpReps.setTypeface(null, Typeface.NORMAL);
                pullUpText.setTextColor(Color.rgb(209, 209, 209));
                pullUpReps.setTextColor(Color.rgb(209, 209, 209));
                dipText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
                dipReps.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
                dipText.setTypeface(null, Typeface.BOLD);
                dipReps.setTypeface(null, Typeface.BOLD);
            }
            else if (pushUpActive)
            {
                dipText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
                dipReps.setTextSize(TypedValue.COMPLEX_UNIT_SP, 30);
                dipText.setTypeface(null, Typeface.NORMAL);
                dipReps.setTypeface(null, Typeface.NORMAL);
                dipText.setTextColor(Color.rgb(209, 209, 209));
                dipReps.setTextColor(Color.rgb(209, 209, 209));
                pushUpText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
                pushUpReps.setTextSize(TypedValue.COMPLEX_UNIT_SP, 50);
                pushUpText.setTypeface(null, Typeface.BOLD);
                pushUpReps.setTypeface(null, Typeface.BOLD);
            }
        }
        if (curSetNo > sets)
        {
            sumAct();
        }
    }

    private void sumAct()
    {
        Intent intent = new Intent(this, SummaryActivity.class);

        intent.putExtra("pullUpCount", pullUpCount);
        intent.putExtra("dipCount", dipCount);
        intent.putExtra("pushUpCount", pushUpCount);

        startActivity(intent);
        finish();
    }

    public int max(int a, int b, int c)
    {
        if (a > b && a > c)
            return a;
        else if (b > c)
            return b;
        else
            return c;
    }
}
