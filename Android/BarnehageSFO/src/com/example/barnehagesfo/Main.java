package com.example.barnehagesfo;

import java.util.ArrayList;
import java.util.List;

import DataClasses.ChildData;
import DataClasses.GuardianData;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.content.ClipData.Item;
import android.content.ClipData;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.NavUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Main extends FragmentActivity implements ActionBar.TabListener {

    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
    public static List<ChildData> children;
    public static Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this;
        
        //Get child data
        children = new ArrayList<ChildData>();
        GuardianData guardianOne = new GuardianData("Forelder1", null, "veien1");
        GuardianData guardianTwo = new GuardianData("Forelder2", null, "veien2");
        GuardianData guardianThree = new GuardianData("Forelder3", null, "veien3");
        List<GuardianData> guardians = new ArrayList<GuardianData>();
        guardians.add(guardianOne);
        guardians.add(guardianTwo);
        guardians.add(guardianThree);
        ChildData childOne = new ChildData("Kurt", guardians);
        ChildData childTwo = new ChildData("Jon", guardians);
        ChildData childThree = new ChildData("Frank", guardians);
        children.add(childOne);
        children.add(childTwo);
        children.add(childThree);
        
        // Set up the action bar.
        final ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // For each of the sections in the app, add a tab to the action bar.
        actionBar.addTab(actionBar.newTab().setText(R.string.title_section1).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.title_section2).setTabListener(this));
        actionBar.addTab(actionBar.newTab().setText(R.string.title_section3).setTabListener(this));
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
            getActionBar().setSelectedNavigationItem(
                    savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_SELECTED_NAVIGATION_ITEM,
                getActionBar().getSelectedNavigationIndex());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }

    

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
        // When the given tab is selected, show the tab contents in the container
    	if(tab.getPosition() == 2)
    	{
    		Fragment fragment = new ContactInformation();
    		getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    	}
    	else {
    	Fragment fragment = new DummySectionFragment();
        Bundle args = new Bundle();
        args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, tab.getPosition() + 1);
        fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.container, fragment)
                .commit();
    	}
	}

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    /**
     * A dummy fragment representing a section of the app, but that simply displays dummy text.
     */
    public static class DummySectionFragment extends Fragment {
        public DummySectionFragment() {
        }

        public static final String ARG_SECTION_NUMBER = "section_number";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            TextView textView = new TextView(getActivity());
            textView.setGravity(Gravity.CENTER);
            Bundle args = getArguments();
            textView.setText(Integer.toString(args.getInt(ARG_SECTION_NUMBER)));
            return textView;
        }
    }
    public static class ContactInformation extends Fragment {
    	public ContactInformation(){
    	
    	}
    	
    	
    	@Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
    		View view = inflater.inflate(R.layout.contact_information, container, false);
    		ListView listView = (ListView) view.findViewById(R.id.listviewContactInformation);
    		
    		ArrayList<ChildData> childArray = new ArrayList<ChildData>();
    		
    		for(int i = 0; i < children.size(); i++)
    		{
    			childArray.add(children.get(i));
    			/*
    			strings.add(new ClipData.Item(children.get(i).getName()));
    			strings.add(new ClipData.Item(children.get(i).getGuardian(0).getName()));
    			strings.add(new ClipData.Item(children.get(i).getGuardian(0).getNumber()));
    			strings.add(new ClipData.Item(children.get(i).getGuardian(0).getAdress()));*/
    		}
    		ArrayAdapter<ChildData> arrayAdapter = new ItemAdapter(context, R.layout.contact_listview, childArray);     
    		listView.setAdapter(arrayAdapter);
    		
    		listView.setOnItemClickListener(new OnItemClickListener() {
    			public void onItemClick(AdapterView<?> parent, View view,
    					int position, long id) {
    			    // When clicked, show a toast with the TextView text
    			    Toast.makeText(context,
    				"Information about kid please", Toast.LENGTH_SHORT).show();
    			}
    		});
    		return view;
        }
    	
    }
}
