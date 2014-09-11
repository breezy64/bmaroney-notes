package com.example.bmaroney_todolist;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.SharedPreferences;

public class saveToDoList extends ToDoListPreferenceHelper {
	public saveToDoList(Activity act,String titleprefs_key,String titlekeys,String stateprefs_key){
		super(act,titleprefs_key,titlekeys,stateprefs_key);
	}
	public void saveToDoListItem(ToDoListItem item){
		backUptheTitle(getTitlePrefs(), item.getTitle());
		backUpState(getStatePrefs().edit(),item.getTitle(),item.isSelected());
		backUpNotes(getTitlePrefs().edit(),item.getTitle(),item.gettoDo());
	}
	private void backUpState(SharedPreferences.Editor pref, String key, Boolean value){
		pref.putBoolean(key, value);
		pref.commit();
	}
	private void backUptheTitle(SharedPreferences titlePrefs,String title){
		SharedPreferences.Editor edit=titlePrefs.edit();
		Set<String> titles=titlePrefs.getStringSet(getKey(), new HashSet<String>());
		titles.add(title);
		edit.putStringSet(getKey(),titles);
		edit.commit();
	}
	private void backUpNotes(SharedPreferences.Editor edit, String key, String note){
		edit.putString(key, note);
		edit.commit();
	}
}
