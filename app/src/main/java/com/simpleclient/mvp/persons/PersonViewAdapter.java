package com.simpleclient.mvp.persons;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.simpleclient.R;
import com.simpleclient.api.Person;
import com.simpleclient.api.PersonMessage;
import com.simpleclient.utils.CircleTransform;
import com.simpleclient.utils.PersonObserver;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class PersonViewAdapter extends RecyclerView.Adapter<PersonViewAdapter.PersonViewHolder> {

    public static final int PERSON_DELETE_KEY = 1;
    public static  final int SEX_KEY = 1;
    private Context context;
    private List<Person> items = new ArrayList<>();

    public PersonViewAdapter(Context context) {
        this.context = context;
    }

    public static class PersonViewHolder extends RecyclerView.ViewHolder {

        private ImageView photo;
        private TextView nameAndAge;
        private TextView date;
        private TextView massage;
        private TextView gender;
        private LinearLayout container;


        public PersonViewHolder(View itemView) {
            super(itemView);
            photo = itemView.findViewById(R.id.person_photo);
            nameAndAge = itemView.findViewById(R.id.person_name_and_age);
            date = itemView.findViewById(R.id.data);
            massage = itemView.findViewById(R.id.massage);
            gender = itemView.findViewById(R.id.gender);
            container = itemView.findViewById(R.id.person_item_container);
        }
    }

    @NonNull
    @Override
    public PersonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.person_item_view, parent, false);
        PersonViewHolder viewHolder = new PersonViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull PersonViewHolder holder, int position) {
        final Person person = items.get(position);
        PersonMessage message = person.getData();

        holder.nameAndAge.setText(person.name + ", " + String.valueOf(items.get(position).getAge()));
        holder.date.setText(timeFormat(message.getTime()));
        holder.massage.setText(message.getMsg());

        String tempPhotoUrl = items.get(position).foto;

        if (tempPhotoUrl != null) {
            String photoUrl = tempPhotoUrl.replace("@", "m_");
            Glide.with(context).load(photoUrl)
                    .transform(new CircleTransform(context))
                    .into(holder.photo);
        } else  {
            Glide.with(context).load(R.drawable.user_delete)
                    .transform(new CircleTransform (context))
                    .into(holder.photo);

        }

        if(person.getSex() == SEX_KEY){
            holder.gender.setText(R.string.sex_male);
        }else{
            holder.gender.setText(R.string.sex_female);
        }

        holder.container.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(person.deleted != PERSON_DELETE_KEY)
                PersonObserver.getInstance().setPerson(person);
            }
        });

    }

    public String timeFormat(long time) {
        Date date = new Date(time);
        SimpleDateFormat dateformat = new SimpleDateFormat("dd.MM, HH:mm");
        String timeF = dateformat.format(date);
        return timeF;
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return (items == null ? 0 : items.size());

    }

    public List<Person> getItems() {
        return items;
    }

    public void setItems(List<Person> items) {
        this.items.addAll(items);
    }
}
