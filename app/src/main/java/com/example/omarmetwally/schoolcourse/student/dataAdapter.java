package com.example.omarmetwally.schoolcourse.student;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.omarmetwally.schoolcourse.R;

import java.util.ArrayList;

/**
 * Created by YAT on 26/10/2017.
 */

public class dataAdapter extends ArrayAdapter<GetTeacher> {
    Context context;
    ArrayList<GetTeacher>  mcontact;

    public dataAdapter(Context context, ArrayList<GetTeacher> contact){
        super(context, R.layout.teachercontent, contact);
        this.context=context;
        this.mcontact=contact;
    }

    public  class  Holder{

        TextView name,cente,school,subj;


       // ImageView pic;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        GetTeacher data = getItem(position);


        // Check if an existing view is being reused, otherwise inflate the view

        Holder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {

            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());

            convertView = inflater.inflate(R.layout.teachercontent, parent, false);

            viewHolder.name = (TextView) convertView.findViewById(R.id.teacherName);
            viewHolder.cente = (TextView) convertView.findViewById(R.id.centerName);
            viewHolder.school = (TextView) convertView.findViewById(R.id.schoolName);
            viewHolder.subj = (TextView) convertView.findViewById(R.id.subjectName);

          //  viewHolder.pic = (ImageView) convertView.findViewById(R.id.teacherpic);


            convertView.setTag(viewHolder);

        } else {
            viewHolder = (Holder) convertView.getTag();
        }



       // PicassoClient.downloadImage(context,mcontact.get(position).getImageurl(),viewHolder.pic);

        viewHolder.name.setText(data.getFirstName());
        viewHolder.subj.setText(data.getSubject().toString());
        viewHolder.cente.setText(data.getCity().toString());
        viewHolder.school.setText(data.getStageName().toString());


        return convertView;
    }

}
