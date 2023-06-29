package sg.edu.np.mad.madprac2;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.io.Serializable;
import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<ViewHolder>{
    ArrayList<String> data;
    Context context;
    ImageView userImage;
    ArrayList<User> userList;
    public Adapter(Context context, ArrayList<User> userList) {
        this.context = context;
        this.userList = userList;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(
            ViewGroup parent,
            int viewType) {

        if (viewType == 0){
            View item = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.user_view,
                    parent,
                    false);
            return new ViewHolder(item);
        }
        else {
            View item = LayoutInflater.from(parent.getContext()).inflate(
                    R.layout.alt_user_view,
                    parent,
                    false);
            return new ViewHolder(item);
        }
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        User s = userList.get(position);
        holder.userName.setText(s.name);
        holder.userDesc.setText(s.description);

        userImage = holder.userImage.findViewById(R.id.userImage);
        userImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d("Image Click", "Image has been clicked!");

                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Profile");
                builder.setMessage(s.name);
                builder.setPositiveButton("VIEW", new DialogInterface.OnClickListener(){
                    @Override
                    public void onClick(DialogInterface dialog, int id){
                        Intent activityName = new Intent(context, MainActivity.class);
                        activityName.putExtra("id", s.getId());
                        activityName.putExtra("name", s.name);
                        activityName.putExtra("description", s.description);
                        activityName.putExtra("followed", s.followed);

                        context.startActivity(activityName);
                        Log.d("Image Click", "Positive has been clicked!");
                    }
                });
                builder.setNegativeButton("CLOSE", new DialogInterface.OnClickListener(){
                    public void onClick(DialogInterface dialog, int id){
                        Log.d("Image Click", "Negative has been clicked!");
                        dialog.dismiss();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
        }

    @Override
    public int getItemCount() {
        return userList.size();
    }

    @NonNull
    @Override
    public int getItemViewType(int position){
        User user = userList.get(position);
        if (user.name.endsWith("7")) { return 1; }
        else { return 0;}
    }
}
