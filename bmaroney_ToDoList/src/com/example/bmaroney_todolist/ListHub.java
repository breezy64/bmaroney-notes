package com.example.bmaroney_todolist;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ListHub extends Activity {
	private static final int createToDo_result=1;
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
			createTodo();
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data ){
		if(requestCode==this.createToDo_result && resultCode==this.RESULT_OK){
			items.add(unBundleToDo(data.getExtras()));
			backUpToDo(getString(R.string.prefs_titles),getString(R.string.titles_key),getString(R.string.prefs_state),unBundleToDo(data.getExtras()));
		}
	}
	private void loadToDoList(){
		items=new ArrayAdapter<ToDoListItem>(this,android.R.layout.simple_list_item_checked);
		ToDoListLoader loader=new ToDoListLoader(this,getString(R.string.prefs_titles),getString(R.string.titles_key),getString(R.string.prefs_state));
		ListView list=loader.loadToDoList(this,items,R.id.listView1);
		list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				return false;
			}
		} );
	}
	private void backUpToDo(String titlePref, String titleKey,String statePref,ToDoListItem item){
		saveToDoList saver=new saveToDoList(this,titlePref,titleKey,statePref);
		saver.saveToDoListItem(item);
	}
	private ToDoListItem unBundleToDo(Bundle b){
		return new ToDoListItem(b.getString(getString(R.string.titles_text)),b.getString(getString(R.string.notes_text)),false);
	}
	private void createTodo(){
		Intent createTodo = new Intent(this, newTodo.class);
		this.startActivityForResult(createTodo,createToDo_result);
	}
}
