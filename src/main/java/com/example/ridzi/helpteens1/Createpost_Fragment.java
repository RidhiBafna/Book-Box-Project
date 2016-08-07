package com.example.ridzi.helpteens1;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.app.Fragment;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;

import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


/**
 * A simple {@link Fragment} subclass.
 */
public class Createpost_Fragment extends Fragment {

    EditText bookname,publication,price,description;
    private static final int RESULT_OK = 0;
    DBPost dbPost;
    ImageButton camera;
    Button  browse,submit;
    private static final int SELECT_PHOTO = 100;
    private static final int CAMERA_REQUEST = 0;
    ImageView imageView;

    public Createpost_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_createpost_, container, false);
        camera = (ImageButton) v.findViewById(R.id.button);
        browse = (Button) v.findViewById(R.id.button2);
        bookname=(EditText)v.findViewById(R.id.bname1);
        description=(EditText)v.findViewById(R.id.bdescription1);
        price=(EditText)v.findViewById(R.id.cost1);
        publication=(EditText)v.findViewById(R.id.bpublication1);
        imageView = (ImageView) v.findViewById(R.id.imageview2);
        dbPost = new DBPost(getActivity().getApplicationContext());

        submit=(Button)v.findViewById(R.id.submit2);
        camera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent, CAMERA_REQUEST);


            }
        });
        browse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);


                startActivityForResult(Intent.createChooser(intent,"Select"), SELECT_PHOTO);

            }
        });
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BookBoxModel2 book = new BookBoxModel2();
                book.bookname =bookname.getText().toString();
                book.publication = publication.getText().toString();
                book.description = description.getText().toString();
                book.price = price.getText().toString();

                dbPost.submitdetail(book);
                Toast.makeText(getActivity(),"Post Created",Toast.LENGTH_LONG).show();

            }
        });

        // Inflate the layout for this fragment
        return v;

    }

    public void onActivityResult(int requestCode, int resultCode, Intent imageReturnedIntent) {


        super.onActivityResult(requestCode, resultCode, imageReturnedIntent);
        switch (requestCode) {
            case SELECT_PHOTO:

                if (resultCode == RESULT_OK) {

                    if(imageReturnedIntent!=null)
                    {
                        Bitmap bitmap = null;
                        try {
                            bitmap = MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), imageReturnedIntent.getData());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        imageView.setImageBitmap(bitmap);
                    }


                }
            case CAMERA_REQUEST:
                if (requestCode == CAMERA_REQUEST) {
                    Bitmap photo = (Bitmap) imageReturnedIntent.getExtras().get("data");
                    imageView.setImageBitmap(photo);
                }


        }
    }
}

