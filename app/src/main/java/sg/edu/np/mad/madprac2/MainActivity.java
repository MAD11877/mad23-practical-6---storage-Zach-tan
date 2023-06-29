package sg.edu.np.mad.madprac2;

import static android.app.ProgressDialog.show;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.time.Duration;

public class MainActivity extends AppCompatActivity {

    final String TITLE = "Main Activity";
    String name;
    String description;
    int id;
    boolean followed;

    User user;
    MyDBHandler myDBHandler = new MyDBHandler(this, null, null, 1);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.v(TITLE, "On Create");
        /*myUser = new User("Zach", "Lorem ipsum dolor sit amet, consectetur adipiscing elit,"+
                "sed do eiusmod tempor incididunt ut labore et dolore magna aliqua", 1, false);*/

        Intent receivingEnd = getIntent();
        id = receivingEnd.getIntExtra("id",0);
        name = receivingEnd.getStringExtra("name");
        description = receivingEnd.getStringExtra("description");
        followed = receivingEnd.getBooleanExtra("followed",false);

        user = myDBHandler.findUser(id);

        TextView nameAndID = findViewById(R.id.textView);
        nameAndID.setText(name);
        TextView desc = findViewById(R.id.textView3);
        desc.setText(description);
        
        Log.v(TITLE, "Followed"+ followed);
        Button followButton = findViewById(R.id.followButton);
        if (followed == false){
            followButton.setText("Follow");
        }
        else {
            followButton.setText("Unfollow");
        }
    }

    @Override
    protected void onResume(){
        super.onResume();

        Button followButton = findViewById(R.id.followButton);
        followButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (followed == false){
                    followed = true;
                    followButton.setText("Unfollow");
                    String Text = "Followed";
                    myDBHandler.updateUser(user);
                    Toast.makeText(getApplicationContext(), Text, Toast.LENGTH_SHORT).show();

                }
                else{
                    followed = false;
                    followButton.setText("Follow");
                    String Text = "Unfollowed";
                    myDBHandler.updateUser(user);
                    Toast.makeText(getApplicationContext(), Text, Toast.LENGTH_SHORT).show();
                }
                Log.v(TITLE, "Button A is pressed");
            }
        });

        Button messageButton = findViewById(R.id.messageButton);
        messageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myIntent = new Intent(MainActivity.this, MessageGroup.class);
                startActivity(myIntent);
            }
    });
}}
