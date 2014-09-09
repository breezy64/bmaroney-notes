package com.example.bmaroney_todolist;

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
	ArrayAdapter<ToDoListItem> items;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_hub);
		loadToDoList();
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
	private void loadToDoList(){
		SharedPreferences titleprefs=loadSharedPreference(R.string.prefs_titles);
		if(todosPresent(titleprefs)){
			items=new ArrayAdapter<ToDoListItem>(this,android.R.layout.simple_list_item_1);
			getToDoListItems(titleprefs,loadSharedPreference(R.string.prefs_state));
			showList();
		}
	}
	

	private SharedPreferences loadSharedPreference(int stringID){
		 return loadPreference(getString(stringID));
	}
	private SharedPreferences loadPreference(String key){
		 return getSharedPreferences(key,Context.MODE_PRIVATE);
	}
	private boolean todosPresent(SharedPreferences prefs){
		try{
			return !(prefs.getAll().isEmpty()); //check if the corresponding map of preferences is empty
		}catch(NullPointerException ex){
			return false;
		}
	}
	private void getToDoListItems(SharedPreferences titleprefs, SharedPreferences stateprefs){
		Set<String> titles=titleprefs.getStringSet(getString(R.string.titles_key),null);
		for(String title:titles){
			items.add(new ToDoListItem(title,titleprefs.getString(title,""),stateprefs.getBoolean(title, false)));
		}
		
	}
	private void showList(){
		ListView List=(ListView) this.findViewById(R.id.listView1);
		List.setAdapter(items);
	}
}
