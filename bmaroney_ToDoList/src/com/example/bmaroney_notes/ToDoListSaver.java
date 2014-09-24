package com.example.bmaroney_notes;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.SharedPreferences;

public class ToDoListSaver{
	private ToDoListPreferenceHelper helper;
	public ToDoListSaver(Activity act,String ToDos){
		helper=new ToDoListPreferenceHelper(act,ToDos);
	}
	public void saveToDoListItem(ToDoListItem item){
		backUpToDos(helper.getToDoPrefs().edit(), item.getToDo(),item.toDoCompleted());
	}
	private void backUpToDos(SharedPreferences.Editor ToDoPrefs,String ToDo,boolean value){
		ToDoPrefs.putBoolean(ToDo, value);
		ToDoPrefs.commit();
	}
	public void updateState(ToDoListItem item){
		backUpToDos(helper.getToDoPrefs().edit(),item.getToDo(),item.toDoCompleted());
	}
	public void delete(ToDoListItem item){
		removeFromPreferences(helper.getToDoPrefs().edit(),item.getToDo());
		
	}
	private void removeFromPreferences(SharedPreferences.Editor pref, String key){
		pref.remove(key);
		pref.commit();
	}
}
