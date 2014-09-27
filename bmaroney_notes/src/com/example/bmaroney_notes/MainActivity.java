/** Copyright (C) 2006 The Android Open Source Project
 *  Copyright 2014 Byron Maroney

   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitations under the License.
*/
package com.example.bmaroney_notes;

/*
 I included The Android Open Source Project in my license header since my implementation of onOptionsItemSelected
 is based on the implementation available on the Android Develepor Guides 
 (http://developer.android.com/training/basics/firstapp/starting-activity.html)
 */

import java.util.ArrayList;

import com.example.bmaroney_notes.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

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
		int id = item.getItemId();
		if (id == R.id.create_todo) {
			createTodo();
		}
		else if(id==R.id.archiveMode){
			startArchiveMode();
		}
		else if(id==R.id.stats){
			startSummaryActivity();
		}
		else if(id==R.id.emailAll){
			sendMassEmail();
		}
		return super.onOptionsItemSelected(item);
	}
	private void sendMassEmail() {
		try{
			startActivity(generateEmailIntent(controller.getMassEmailText()));
		}catch (Exception ex){
			//I learnt about Toast via the Android Developer guides (http://developer.android.com/guide/topics/ui/notifiers/toasts.html)
			Toast.makeText(this, "No items to email", Toast.LENGTH_SHORT).show(); 
		}
		
	}
	private void startSummaryActivity() {
		Intent intent=new Intent(this,SummaryActivity.class);
		startActivity(intent);
		
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
	    	startActivity(generateEmailIntent(ToDoListItem.printToDoList(arrayAdapterSubList(positions))));
	 }
	 /*I learnt how to use to make intents send email from fiXedd's post on stackoverflow: 
	 http://stackoverflow.com/questions/2197741/how-can-i-send-emails-from-my-android-application
	 */
	 private Intent generateEmailIntent(String emailText){
			Intent email=new Intent(Intent.ACTION_SEND);
	    	email.setType("message/rfc822");
	    	email.putExtra(Intent.EXTRA_TEXT,emailText);
	    	return email;
	 }
	 public void toggleState(ArrayList<Integer>positions){
		 for(int position:positions){
			 ToDoListItem item=items.getItem(position);
			 controller.toggleState(item);
		 }
	 }
	 public void deleteToDo(ArrayList<Integer> positions){
		 for(ToDoListItem item:arrayAdapterSubList(positions)){
			 delete(item);
			 }
	 }
	 private void delete(ToDoListItem item) {
		 items.remove(item);
		 controller.remove(item);
	 }
	protected ArrayList<ToDoListItem> arrayAdapterSubList(ArrayList<Integer> positions){
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
