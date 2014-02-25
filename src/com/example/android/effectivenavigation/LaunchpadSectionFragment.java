package com.example.android.effectivenavigation;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by hackeru on 24/02/14.
 */
public class LaunchpadSectionFragment extends Fragment {

    SQLiteDatabase db;
    private ArrayList<HashMap<String, Object>> imageList;
    private static HashMap<String, Object> hm;
    private static final String TITLE = "product name"; // Text upper
    private static final String DESCRIPTION = "description"; //text small
    private static final String ICON = "icon";  // futures icon
    MainActivity.AppSectionsPagerAdapter mAppSectionsPagerAdapter;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_section_launchpad, container, false);

        this.db = DBManager.myData(getActivity()).getWritableDatabase();

        Toast.makeText(getActivity(), "" + getActivity().getClass(), Toast.LENGTH_SHORT).show();
        Log.i("*****", "" + getActivity().getClass());

        ArrayList<HashMap<String, Object>> imageList;
        ListView lv1;

        imageList = new ArrayList<HashMap<String, Object>>();

        Cursor cursor = db.rawQuery("SELECT * FROM products;", null);
        for (cursor.moveToFirst(); !cursor.isAfterLast() && cursor.getCount() > 0; cursor.moveToNext()) {
            Log.i("****", cursor.getInt(0) + "," + cursor.getString(1) + "," + cursor.getInt(2) + "," + cursor.getString(3) + "," + cursor.getString(4));

            hm = new HashMap<String, Object>();
            hm.put(TITLE, cursor.getString(1));
            hm.put(DESCRIPTION, cursor.getString(3));
            hm.put(ICON, R.drawable.ic_launcher);
            imageList.add(hm);

        }

        lv1 = (ListView) rootView.findViewById(R.id.listView);


        SimpleAdapter adapter = new SimpleAdapter(getActivity(), imageList, R.layout.rowlayout,
                new String[]{TITLE, DESCRIPTION, ICON},
                new int[]{R.id.text1, R.id.text2, R.id.icon});

        lv1.setAdapter(adapter);




        // Demonstration of a collection-browsing activity.
        rootView.findViewById(R.id.demo_collection_button)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent intent = new Intent(getActivity(), CollectionDemoActivity.class);
                        startActivity(intent);
                    }
                });

        // Demonstration of navigating to external activities.
        rootView.findViewById(R.id.demo_external_activity)
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        // Create an intent that asks the user to pick a photo, but using
                        // FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET, ensures that relaunching
                        // the application from the device home screen does not return
                        // to the external activity.
                        Intent externalActivityIntent = new Intent(Intent.ACTION_PICK);
                        externalActivityIntent.setType("image/*");
                        externalActivityIntent.addFlags(
                                Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
                        startActivity(externalActivityIntent);
                    }
                });

        return rootView;
    }





}
