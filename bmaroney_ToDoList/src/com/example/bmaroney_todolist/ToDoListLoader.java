package com.example.bmaroney_todolist;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.TextView;

public class ToDoListLoader{
	private ToDoListPreferenceHelper helper;
	ArrayList<ToDoListItem> ToDos;
	public ToDoListLoader(Activity act,String toDos,String setKey){
		helper=new ToDoListPreferenceHelper(act,toDos,setKey);
		ToDos=new ArrayList<ToDoListItem>();
	}
	public ArrayList<ToDoListItem> loadToDoList(){
		if(helper.isEmpty() && ToDos.isEmpty() ){
			getToDoListItems();
		}
		return ToDos;
	}
	private void getToDoListItems(){
		Set<String> titles=helper.getToDoPrefs().getStringSet(helper.getSetKey(),null);
		for(String ToDo:titles){
			ToDoListItem item=new ToDoListItem(ToDo,helper.getToDoPrefs().getBoolean(ToDo, false));
			ToDos.add(item);
		}
		
	}
}
