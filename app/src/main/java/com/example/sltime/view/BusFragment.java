package com.example.sltime.view;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.sltime.R;
import com.example.sltime.api.ApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * A simple {@link Fragment} subclass.
 */
public class BusFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener {
    SwipeRefreshLayout swipeRefreshLayout;
    String id=" ";

    public BusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_bus, container, false);

        ApiClient apiClient=new ApiClient();
        ArrayList<String> rtl=new ArrayList<String>();
        try {

//            System.out.println(this.getArguments().getChar("c"));
            rtl=apiClient.getRouteInformation(id,1000,"Buses");
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ListView listView =view.findViewById(R.id.bus_list);
        ArrayAdapter<String> listofPe = new ArrayAdapter<String>(getActivity(),android.R.layout.simple_list_item_1
                ,GetStringArray(rtl));
        listView.setAdapter(listofPe);

        return view;


    }



    public static String[] GetStringArray(ArrayList<String> arr)
    {

        // declaration and initialise String Array
        String str[] = new String[arr.size()];

        // ArrayList to Array Conversion
        for (int j = 0; j < arr.size(); j++) {

            // Assign each value to String array
            str[j] = arr.get(j);
        }

        return str;

    }


    @Override
    public void onRefresh() {
        swipeRefreshLayout.setOnRefreshListener(this);


    }
    public void setId(String id) {
        this.id=id;
    }
}
