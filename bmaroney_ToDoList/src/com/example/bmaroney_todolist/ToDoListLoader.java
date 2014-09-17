package com.example.bmaroney_todolist;

import java.util.Set;

import android.app.Activity;
import android.content.SharedPreferences;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ToDoListLoader{
	private ToDoListPreferenceHelper helper;
	private Activity act;
	public ToDoListLoader(Activity act,String titleprefs_key,String titlekeys,String stateprefs_key){
		helper=new ToDoListPreferenceHelper(act,titleprefs_key,titlekeys,stateprefs_key);
		this.act=act;
	}
	public void loadToDoList(Activity act,ArrayAdapter<ToDoListItem> items){
		bindAdaptertoListView(act,items);
		if(helper.isEmpty()){
			getToDoListItems(items);
		}
	}
	private void getToDoListItems(ArrayAdapter<ToDoListItem> items){
		Set<String> titles=helper.getTitlePrefs().getStringSet(helper.getKey(),null);
		for(String title:titles){
			items.add(new ToDoListItem(title,helper.getTitlePrefs().getString(title,""),helper.getStatePrefs().getBoolean(title, false)));
		}
	}
	private void bindAdaptertoListView(Activity act,ArrayAdapter<ToDoListItem> items){
		getListView(act).setAdapter(items);
	}
	private ListView getListView(Activity act){
		return (ListView) act.findViewById(R.id.listView1);
	}
}
