package com.example.bmaroney_todolist;

import java.util.ArrayList;
import java.util.Set;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListHub extends Activity {
	ArrayList<ToDoListItem> items;
	ArrayList<String> listItems;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_hub);
		loadToDoList(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.list_hub, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.create_todo) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	private void loadToDoList(Activity currentAct){
		SharedPreferences prefsMaster=loadSharedPreference(currentAct,R.string.activity_ListHub_MasterList);
		if(todosPresent(prefsMaster)){
			getToDoListItems(currentAct);
			drawList();
		}
	}
	
	private void drawList() {
		ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,listItems.toArray(new String[listItems.size()]));
		ListView list=(ListView) findViewById(R.id.listView1);
		list.setAdapter(listAdapter);	
	}

	private SharedPreferences loadSharedPreference(Activity act, int stringID){
		 return loadPreference(act,act.getString(stringID));
	}
	private SharedPreferences loadPreference(Activity act, String key){
		 return act.getSharedPreferences(key,Context.MODE_PRIVATE);
	}
	private boolean todosPresent(SharedPreferences prefs){
		try{
			return !(prefs.getAll().isEmpty()); //check if the corresponding map of preferences is empty
		}catch(NullPointerException ex){
			return false;
		}
	}
	private void getToDoListItems(Activity current){
		Set<String> titles=getToDoTitles(loadSharedPreference(current,R.string.activity_ListHub_MasterList),current.getString(R.string.activity_ListHub_ToDoTitles));
		for(String title:titles){
			items.add(new ToDoListItem(title,loadPreference(current,title).getString(title,null),loadSharedPreference(current,R.string.activity_listHubState).getBoolean(title, false)));
		}
		
	}
	private  Set<String> getToDoTitles(SharedPreferences masterPrefs, String key){
		Set<String> titles=masterPrefs.getStringSet(key, null);
		listItems.addAll(titles);
		return masterPrefs.getStringSet(key, null);
	}
}
