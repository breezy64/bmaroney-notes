package com.example.bmaroney_notes;

import java.util.ArrayList;

import com.example.bmaroney_notes.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {
	private ToDoListController controller;
	private ArrayAdapter<ToDoListItem> items;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		controller=new ToDoListController(this,R.string.prefs_ToDos);
		items=new CheckBoxAdapter(this,R.layout.test);
		ListView list=(ListView)findViewById(R.id.listView1);
		list.setAdapter(items);
		list.setMultiChoiceModeListener(new MainActivityMultiChoiceListener(this,R.menu.context_menu,list));
		controller.loadToDoList(items);	
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}
	@Override
	protected void onResume(){
		super.onResume();
		refresh();
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
		else if(id==R.id.archiveMode){
			startArchiveMode();
		}
		return super.onOptionsItemSelected(item);
	}
	private void startArchiveMode() {
		Intent arch=new Intent(this,ArchiveActivity.class);
		startActivity(arch);
		
	}
	protected ToDoListController getController() {
		return controller;
	}
	protected ArrayAdapter<ToDoListItem> getItems() {
		return items;
	}
	protected void setController(ToDoListController controller) {
		this.controller = controller;
	}
	protected void setItems(ArrayAdapter<ToDoListItem> items) {
		this.items = items;
	}
	private void refresh(){
		items.clear();
		controller.loadToDoList(items);
	}
	private void createTodo(){
		Intent createTodo = new Intent(this, newTodo.class);
		this.startActivity(createTodo);
	}
	 public void sendEmail(ArrayList<Integer> positions){
	    	Intent email=new Intent(Intent.ACTION_SEND);
	    	email.setType("message/rfc822");
	    	email.putExtra(Intent.EXTRA_TEXT,ToDoListItem.printToDoList(arrayAdapterSubList(positions)));
	    	startActivity(email);
	 }
	 public void checkOffToDo(ArrayList<Integer>positions){
		 ToDoListSaver saver=new ToDoListSaver(this,getString(R.string.prefs_ToDos),getString(R.string.StringSetKey));
		 for(int position:positions){
			 ToDoListItem item=items.getItem(position);
			 item.markAsCompleted();
			 saver.updateState(item);
			 
		 }
	 }
	 public void deleteToDo(ArrayList<Integer> positions){
		 ToDoListSaver saver=new ToDoListSaver(this,getString(R.string.prefs_ToDos),getString(R.string.StringSetKey));
		 for(ToDoListItem item:arrayAdapterSubList(positions)){
			 delete(item);
			 }
	 }
	 private void delete(ToDoListItem item) {
		 items.remove(item);
		 controller.remove(item);
	 }
	private ArrayList<ToDoListItem> arrayAdapterSubList(ArrayList<Integer> positions){
		 ArrayList<ToDoListItem> subList=new ArrayList<ToDoListItem>();
		 for(int position:positions){
			 subList.add(items.getItem(position));
		 }
		 return subList;
	 }
	public void sendtoArchive(ArrayList<Integer> positions) {
		for(ToDoListItem item:arrayAdapterSubList(positions)){
			controller.sendToArchive(item);
			delete(item);
		}
		
	}
		 
}