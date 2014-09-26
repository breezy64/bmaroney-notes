package com.example.bmaroney_notes;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class ToDoListPreferenceHelper {
	private SharedPreferences toDoPrefs;
	private String setKey;

	public ToDoListPreferenceHelper(Activity act,String ToDos,String toDoSetKey){
		toDoPrefs=loadPreference(act,ToDos);
		setKey=toDoSetKey;
	}
	public String getSetKey(){
		return setKey;
	}
	private SharedPreferences loadPreference(Activity act,String key){
		 return act.getSharedPreferences(key,Context.MODE_PRIVATE);
	}
	public SharedPreferences getToDoPrefs(){
		return toDoPrefs;
	}
	public boolean isEmpty(){
		return getToDoPrefs().contains(getSetKey());
	}
}
