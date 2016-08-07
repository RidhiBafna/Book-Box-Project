package com.example.ridzi.helpteens1;


import android.app.ActionBar;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class Mypost_Fragment extends Fragment {
    TextView viewtext;
    Button view;
    DBPost dbPost;


    public Mypost_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v=inflater.inflate(R.layout.fragment_mypost_, container, false);
        view=(Button)v.findViewById(R.id.button3);




        viewtext=(TextView)v.findViewById(R.id.textView4);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dbPost=new DBPost(getActivity());

                Toast.makeText(getActivity(),"Your Post",Toast.LENGTH_LONG).show();
                List<BookBoxModel2> list = dbPost.getAllStudentsList();
               String values = "";



            for (BookBoxModel2 sm : list) {
                   dbPost.getAllStudentsList();

                   values = values + "Book name: " + sm.bookname + " \n Publication: " + sm.publication + " \n Description: " + sm.description + " \n Price: " + sm.price
                          + "\n\n";
                   viewtext.setText(values);
               }


            }
        });

        // Inflate the layout for this fragment
        return v;

    }

}
