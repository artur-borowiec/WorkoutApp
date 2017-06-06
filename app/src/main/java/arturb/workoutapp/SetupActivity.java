package arturb.workoutapp;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import static java.lang.Integer.parseInt;

public class SetupActivity extends AppCompatActivity
{
    int maxPullUps;
    int maxDips;
    int maxPushUps;

    EditText pullUps;
    EditText dips;
    EditText pushUps;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setup);

        pullUps = (EditText) findViewById(R.id.pullUpsNo);
        dips = (EditText) findViewById(R.id.dipsNo);
        pushUps = (EditText) findViewById(R.id.pushUpsNo);

        Button workout = (Button) findViewById(R.id.workoutButton);
    }

    @Override
    protected void onDestroy()
    {
        super.onDestroy();

    }

    /** Called when the user clicks the Send button */
    public void startWorkout(View view)
    {
        if (!pullUps.getText().toString().equals("") && !dips.getText().toString().equals("") && !pushUps.getText().toString().equals(""))
        {
            Intent intent = new Intent(this, WorkoutActivity.class);

            maxPullUps = parseInt(pullUps.getText().toString());
            maxDips = parseInt(dips.getText().toString());
            maxPushUps = parseInt(pushUps.getText().toString());

            maxPullUps/=2;
            maxDips/=2;
            maxPushUps/=2;

            intent.putExtra("maxPullUps", maxPullUps);
            intent.putExtra("maxDips", maxDips);
            intent.putExtra("maxPushUps", maxPushUps);

            startActivity(intent);
            finish();
        }
    }
}
