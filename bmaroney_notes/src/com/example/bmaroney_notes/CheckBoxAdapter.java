package com.example.bmaroney_notes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.bmaroney_notes.R;

public class CheckBoxAdapter extends ArrayAdapter<ToDoListItem> {
	private LayoutInflater mInflater;
    private int mViewResourceId;
    
	public CheckBoxAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		mViewResourceId = textViewResourceId;
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}    
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = mInflater.inflate(mViewResourceId, null);
	    CheckBox cb = (CheckBox)convertView.findViewById(R.id.checkBox1);
	    cb.setChecked(getItem(position).toDoCompleted());
	    TextView tv = (TextView)convertView.findViewById(R.id.textView1);
	    tv.setText(getItem(position).toString());
	    return convertView;
	}
}
