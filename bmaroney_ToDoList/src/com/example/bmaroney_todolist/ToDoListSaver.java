package com.example.bmaroney_todolist;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.SharedPreferences;

public class ToDoListSaver{
	private ToDoListPreferenceHelper helper;
	public ToDoListSaver(Activity act,String ToDos,String setKey){
		helper=new ToDoListPreferenceHelper(act,ToDos,setKey);
	}
	public void saveToDoListItem(ToDoListItem item){
		backUpToDo(helper.getToDoPrefs(), item.getToDo());
		backUpTheState(helper.getToDoPrefs().edit(), item.getToDo(),item.toDoCompleted());
	
	}
	private void backUpToDo(SharedPreferences titlePrefs,String title){
		SharedPreferences.Editor edit=titlePrefs.edit();
		Set<String> titles=titlePrefs.getStringSet(helper.getSetKey(), new HashSet<String>());
		titles.add(title);
		edit.putStringSet(helper.getSetKey(),titles);
		edit.commit();
	}

	private void backUpTheState(SharedPreferences.Editor ToDoPrefs,String ToDo,boolean value){
		ToDoPrefs.putBoolean(ToDo, value);
		ToDoPrefs.commit();
	}
	public void updateState(ToDoListItem item){
		backUpTheState(helper.getToDoPrefs().edit(),item.getToDo(),item.toDoCompleted());
	}
	public void delete(ToDoListItem item){
		removeFromPreferences(helper.getToDoPrefs().edit(),item.getToDo());
		removeFromKeySet(helper.getToDoPrefs().edit(),item.getToDo());
	}
	private void removeFromPreferences(SharedPreferences.Editor pref, String key){
		pref.remove(key);
		pref.commit();
	}
	private void removeFromKeySet(SharedPreferences.Editor pref, String key){
		Set<String> titles=helper.getToDoPrefs().getStringSet(helper.getSetKey(), null);
		titles.remove(key);
		pref.putStringSet(helper.getSetKey(),titles);
	}
}
