package com.example.bmaroney_todolist;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class ToDoListPreferenceHelper {
	private SharedPreferences titleprefs;
	private String titlekeys;
	private SharedPreferences stateprefs;

	public ToDoListPreferenceHelper(Activity act,String titleprefs_key,String titlekeys,String stateprefs_key){
		this.titlekeys=titlekeys;
		titleprefs=loadPreference(act,titleprefs_key);
		stateprefs=loadPreference(act,stateprefs_key);
	}
	private SharedPreferences loadPreference(Activity act,String key){
		 return act.getSharedPreferences(key,Context.MODE_PRIVATE);
	}
	protected SharedPreferences getTitlePrefs(){
		return titleprefs;
	}
	protected String getKey(){
		return titlekeys;
	}
	protected SharedPreferences getStatePrefs(){
		return stateprefs;
	}
}
