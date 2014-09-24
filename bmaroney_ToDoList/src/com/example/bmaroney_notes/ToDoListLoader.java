package com.example.bmaroney_notes;

import java.util.Set;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

public class ToDoListLoader{
	private ToDoListPreferenceHelper helper;
	private Activity act;
	private ListView list;
	public ToDoListLoader(Activity act,String toDos,int listViewID){
		helper=new ToDoListPreferenceHelper(act,toDos);
		this.act=act;
		list=getListView(listViewID);
	}
	public void loadToDoList(ArrayAdapter<ToDoListItem> items){
		bindAdaptertoListView(items);
		if(helper.isEmpty()){
			getToDoListItems(items);
		}
	}
	private void getToDoListItems(ArrayAdapter<ToDoListItem> items){
		try{
			for(String ToDo:helper.getToDoPrefs().getAll().keySet()){
				ToDoListItem item=new ToDoListItem(ToDo,helper.getToDoPrefs().getBoolean(ToDo, false));
				items.add(item);
			}
		}catch(NullPointerException ex){
		}
	}
	private void bindAdaptertoListView(ArrayAdapter<ToDoListItem> items){
		list.setAdapter(items);
	}
	public ListView getListView(int listViewID){
		return (ListView) act.findViewById(listViewID);
	}
}
