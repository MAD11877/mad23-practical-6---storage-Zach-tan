package sg.edu.np.mad.madprac2;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

public class ViewHolder extends RecyclerView.ViewHolder {
    public ImageView userImage;

    public TextView userName, userDesc;

    public ViewHolder(View itemView) {
        super(itemView);
        userName = itemView.findViewById(R.id.userName);
        userDesc = itemView.findViewById(R.id.userDesc);
        userImage = itemView.findViewById(R.id.userImage);
    }
}

