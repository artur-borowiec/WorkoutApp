package arturb.workoutapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.widget.TextView;

public class SummaryActivity extends AppCompatActivity
{
    int pullUpCount, dipCount, pushUpCount;

    TextView summaryText;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        Bundle extras = getIntent().getExtras();

        pullUpCount = extras.getInt("pullUpCount");
        dipCount = extras.getInt("dipCount");
        pushUpCount = extras.getInt("pushUpCount");

        summaryText = (TextView) findViewById(R.id.summaryText);

        summaryText.setText("You've done:\n" + pullUpCount + " pull ups\n" + dipCount + " dips\n" + pushUpCount + " push ups\n\n" + "Congratulations!");
        summaryText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25);
    }
}
