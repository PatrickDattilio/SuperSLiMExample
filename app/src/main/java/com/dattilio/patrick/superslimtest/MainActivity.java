package com.dattilio.patrick.superslimtest;

import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;

import com.tonicartos.superslim.GridSectionLayoutManager;
import com.tonicartos.superslim.LayoutManager;
import com.tonicartos.superslim.LinearSectionLayoutManager;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new PlaceholderFragment())
                    .commit();
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        private final LayoutManager layoutManager;
        private final LinearSectionLayoutManager headerManager;
        private final LinearSectionLayoutManager navBarManager;
        private final GridSectionLayoutManager gridManager;

        public PlaceholderFragment() {
            layoutManager = new LayoutManager();
            headerManager = new LinearSectionLayoutManager(layoutManager);
            layoutManager.registerSectionLayoutManager(0, headerManager);
            navBarManager = new LinearSectionLayoutManager(layoutManager);
            gridManager = new GridSectionLayoutManager(layoutManager, getActivity());
            gridManager.setNumColumns(3);
            layoutManager.registerSectionLayoutManager(1, gridManager);
        }


        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            RecyclerView recyclerView = (RecyclerView) rootView.findViewById(R.id.recyclerview);
            recyclerView.setLayoutManager(layoutManager);
            RecyclerAdapter adapter = new RecyclerAdapter(getActivity(),recyclerView);
            recyclerView.setAdapter(adapter);
            return rootView;
        }
    }
}
