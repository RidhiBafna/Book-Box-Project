
package com.example.ridzi.helpteens1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.ExpandableListView;
import android.widget.TextView;


public class Category_Fragment extends Fragment {

    android.widget.ExpandableListAdapter listAdapter;
    ExpandableListView expListView;
    List<String> listDataHeader;
    HashMap<String, List<String>> listDataChild;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // get the listview
        View v=inflater.inflate(R.layout.fragment_category_, container, false);;
        expListView = (ExpandableListView)v.findViewById(R.id.list);

        // preparing list data
        prepareListData();

        listAdapter = new ExpandableListAdapter(getContext(), listDataHeader, listDataChild);

        // setting list adapter
        expListView.setAdapter(listAdapter);
        return  v;
    }

    /*
     * Preparing the list data
     */
    private void prepareListData() {
        listDataHeader = new ArrayList<String>();
        listDataChild = new HashMap<String, List<String>>();

        // Adding child data
        listDataHeader.add("TextBook");
        listDataHeader.add("Novel");
        listDataHeader.add("Reference Books");

        // Adding child data
        List<String> TextBook = new ArrayList<String>();
        TextBook.add("I Year");
        TextBook.add("II Year");
        TextBook.add("III Year");
        TextBook.add("IV Year");


        List<String> Novel = new ArrayList<String>();
        Novel.add("Mystery");
        Novel.add("Horror");
        Novel.add("Romantic");
        Novel.add("Detective");
        Novel.add("Inspirational");
        Novel.add("Thriller");

        List<String> ReferenceBooks = new ArrayList<String>();
        ReferenceBooks.add("2 Guns");
        ReferenceBooks.add("The Smurfs 2");
        ReferenceBooks.add("The Spectacular Now");
        ReferenceBooks.add("The Canyons");
        ReferenceBooks.add("Europa Report");

        listDataChild.put(listDataHeader.get(0), TextBook); // Header, Child data
        listDataChild.put(listDataHeader.get(1), Novel);
        listDataChild.put(listDataHeader.get(2), ReferenceBooks);
    }


}

