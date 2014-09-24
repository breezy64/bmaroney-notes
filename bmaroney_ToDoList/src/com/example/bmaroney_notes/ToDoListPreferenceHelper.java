package com.example.bmaroney_notes;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class ToDoListPreferenceHelper {
	private SharedPreferences toDoPrefs;
	private String preferenceKey;

	public ToDoListPreferenceHelper(Activity act,String ToDos){
		toDoPrefs=loadPreference(act,ToDos);
		preferenceKey=ToDos;
	}
	private SharedPreferences loadPreference(Activity act,String key){
		 return act.getSharedPreferences(key,Context.MODE_PRIVATE);
	}
	public SharedPreferences getToDoPrefs(){
		return toDoPrefs;
	}
	public boolean isEmpty(){
		try{
			return getToDoPrefs().getAll().isEmpty();
		}catch (NullPointerException ex){
			return false;
		}
	}
}
