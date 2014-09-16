package com.example.bmaroney_todolist;

import java.util.Set;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;

public class ToDoListLoader extends ToDoListPreferenceHelper {
	public ToDoListLoader(Activity act,String titleprefs_key,String titlekeys,String stateprefs_key){
		super(act,titleprefs_key,titlekeys,stateprefs_key);
	}
	public void loadToDoList(Activity act,ArrayAdapter<ToDoListItem> items, int listViewID){
		bindAdaptertoListView(act,items,listViewID);
		if(todosPresent()){
			getToDoListItems(items,act,listViewID);
		}
	}
	private boolean todosPresent(){
		return getTitlePrefs().contains(getKey());
	}
	private void getToDoListItems(ArrayAdapter<ToDoListItem> items, Activity act,int listViewID){
		Set<String> titles=getTitlePrefs().getStringSet(getKey(),null);
		for(String title:titles){
			ToDoListItem item=new ToDoListItem(title,getTitlePrefs().getString(title,""),getStatePrefs().getBoolean(title, false));
			items.add(item);
			checkToDo(item,getListView(act,listViewID),new Long(items.getItemId(items.getPosition(item))));
			
		}
	}
	private void checkToDo(ToDoListItem item, ListView lv, Long position){
		lv.setChoiceMode(lv.CHOICE_MODE_MULTIPLE);
		lv.setItemChecked(position.intValue(), item.toDoCompleted());
	}
	private void bindAdaptertoListView(Activity act,ArrayAdapter<ToDoListItem> items,int listViewID){
		getListView(act,listViewID).setAdapter(items);
	}
	private ListView getListView(Activity act, int listViewID){
		return (ListView) act.findViewById(listViewID);
	}
}