package com.example.barnehagesfo;
import java.util.ArrayList;

import DataClasses.ChildData;
import android.content.ClipData.Item;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ItemAdapter extends ArrayAdapter<ChildData> {

	// declaring our ArrayList of items
	private ArrayList<ChildData> objects;

	public ItemAdapter(Context context, int textViewResourceId, ArrayList<ChildData> objects) {
		super(context, textViewResourceId, objects);
		this.objects = objects;
	}

	
	public View getView(int position, View convertView, ViewGroup parent){

		View v = convertView;

		if (v == null) {
			LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = inflater.inflate(R.layout.contact_listview, null);
		}

		/*
		 * Recall that the variable position is sent in as an argument to this method.
		 * The variable simply refers to the position of the current object in the list. (The ArrayAdapter
		 * iterates through the list we sent it)
		 * 
		 * Therefore, i refers to the current Item object.
		 */
		
		ChildData i = objects.get(position);

		if (i != null) {
			
			// This is how you obtain a reference to the TextViews.
			// These TextViews are created in the XML files we defined.

			TextView txtChildName = (TextView) v.findViewById(R.id.txtChildName);
			TextView txtGuardianOne = (TextView) v.findViewById(R.id.txtGuardianOne);
			TextView txtGuardianTwo = (TextView) v.findViewById(R.id.txtGuardianTwo);
			TextView txtAdress = (TextView) v.findViewById(R.id.txtAdress);

			// check to see if each individual textview is null.
			// if not, assign some text!
			if (txtChildName != null){
				txtChildName.setText(i.getName());
			}
			if (txtGuardianOne != null){
				txtGuardianOne.setText(i.getGuardian(0).getName());//i.getName());
			}
			if (txtGuardianTwo != null){
				txtGuardianTwo.setText(i.getGuardian(0).getNumber());
			}
			if (txtAdress != null){
				txtAdress.setText(i.getGuardian(0).getAdress());
			}
		}

		// the view must be returned to our activity
		return v;

	}

}