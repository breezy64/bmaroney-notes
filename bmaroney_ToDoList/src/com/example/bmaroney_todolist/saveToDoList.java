package com.example.bmaroney_todolist;

import java.util.HashSet;
import java.util.Set;

import android.app.Activity;
import android.content.SharedPreferences;

public class saveToDoList{
	private ToDoListPreferenceHelper helper;
	public saveToDoList(Activity act,String titleprefs_key,String titlekeys,String stateprefs_key){
		helper=new ToDoListPreferenceHelper(act,titleprefs_key,titlekeys,stateprefs_key);
	}
	public void saveToDoListItem(ToDoListItem item){
		backUptheTitle(helper.getTitlePrefs(), item.getTitle());
		backUpState(helper.getStatePrefs().edit(),item.getTitle(),item.toDoCompleted());
		backUpNotes(helper.getTitlePrefs().edit(),item.getTitle(),item.gettoDo());
	}
	private void backUpState(SharedPreferences.Editor pref, String key, Boolean value){
		pref.putBoolean(key, value);
		pref.commit();
	}
	private void backUptheTitle(SharedPreferences titlePrefs,String title){
		SharedPreferences.Editor edit=titlePrefs.edit();
		Set<String> titles=titlePrefs.getStringSet(helper.getKey(), new HashSet<String>());
		titles.add(title);
		edit.putStringSet(helper.getKey(),titles);
		edit.commit();
	}
	private void backUpNotes(SharedPreferences.Editor edit, String key, String note){
		edit.putString(key, note);
		edit.commit();
	}
	public void updateState(ToDoListItem item){
		backUpState(helper.getStatePrefs().edit(),item.getTitle(),item.toDoCompleted());
	}
}
