package ru.shanin.mydatalist.presentation.fragments.list_of_people;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import ru.shanin.mydatalist.R;

public class ViewHolder extends RecyclerView.ViewHolder {
    TextView tvFirstName;
    TextView tvSecondName;
    ImageView imPhoto;

    public ViewHolder(View itemView) {
        super(itemView);
        tvFirstName = itemView.findViewById(R.id.tv_fname);
        tvSecondName = itemView.findViewById(R.id.tv_sname);
        imPhoto = itemView.findViewById(R.id.im_photo);
    }
}
