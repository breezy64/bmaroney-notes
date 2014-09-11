package com.example.bmaroney_todolist;

import java.util.Set;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ToDoListLoader extends ToDoListPreferenceHelper {
	public ToDoListLoader(Activity act,String titleprefs_key,String titlekeys,String stateprefs_key){
		super(act,titleprefs_key,titlekeys,stateprefs_key);
	}
	public void loadToDoList(Activity act,ArrayAdapter<ToDoListItem> items){
		bindAdaptertoListView(act,items);
		if(todosPresent()){
			getToDoListItems(items);
		}
	}
	private boolean todosPresent(){
		return getTitlePrefs().contains(getKey());
	}
	private void getToDoListItems(ArrayAdapter<ToDoListItem> items){
		Set<String> titles=getTitlePrefs().getStringSet(getKey(),null);
		for(String title:titles){
			items.add(new ToDoListItem(title,getTitlePrefs().getString(title,""),getStatePrefs().getBoolean(title, false)));
		}
	}
	private void bindAdaptertoListView(Activity act,ArrayAdapter<ToDoListItem> items){
		getListView(act).setAdapter(items);
	}
	private ListView getListView(Activity act){
		return (ListView) act.findViewById(R.id.listView1);
	}
}
