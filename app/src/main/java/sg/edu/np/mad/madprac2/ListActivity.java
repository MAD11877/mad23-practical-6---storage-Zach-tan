package sg.edu.np.mad.madprac2;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Random;

public class ListActivity extends AppCompatActivity {

    final String TITLE = "List Activity";

    MyDBHandler myDBHandler = new MyDBHandler(this, null, null, 1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        /*
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Profile");
        builder.setMessage("MADness");
        builder.setCancelable(true);
        builder.setPositiveButton("View", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                Random rand = new Random();
                int randint = rand.nextInt(999999);
                Intent activityName = new Intent(ListActivity.this, MainActivity.class);
                activityName.putExtra("randint", randint);
                startActivity(activityName);
            }
        });

        builder.setNegativeButton("Close", new DialogInterface.OnClickListener(){
            public void onClick(DialogInterface dialog, int id){
                dialog.dismiss();
            }
        });*/

        ArrayList<User> userList = new ArrayList<>();
        ArrayList<String> nameList = new ArrayList<>();
        String Alphabets = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String Description = "0123456789";
        Random rand = new Random();
        for(int i = 0; i < 20; i++){
            StringBuilder sbName = new StringBuilder(4);
            int randint = rand.nextInt(999999);
            for (int j = 0; j < 4; j++) {
                int index
                        = (int) (Alphabets.length()
                        * Math.random());

                // add Character one by one in end of sb
                sbName.append(Alphabets.charAt(index));
            }
            String name = sbName.append(" " + randint).toString();

            StringBuilder sbDesc = new StringBuilder(Description.length());
            for (int k = 0; k < Description.length(); k++) {
                int index
                        = (int) (Description.length()
                        * Math.random());

                // add Character one by one in end of sb
                sbDesc.append(Description.charAt(index));
            }

            User newUser = new User(name,"Description " + sbDesc.toString(),i,rand.nextBoolean());
            myDBHandler.addUser(newUser);
            nameList.add(name);
        }
        /*
        ImageView image = findViewById(R.id.imageView2);
        image.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog alert = builder.create();
                alert.show();
                Log.v(TITLE, "Image pressed");
            }
        });*/
        RecyclerView recyclerView = findViewById(R.id.recyclerView);
        Adapter mAdapter =
                new Adapter(this,myDBHandler.getUsers());
        LinearLayoutManager mLayoutManager =
                new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
    }}
