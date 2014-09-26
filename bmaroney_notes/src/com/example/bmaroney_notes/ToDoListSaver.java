/*
 *    Copyright 2014 Byron Maroney

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
	/*
	 * I learnt how to edit SharedPreferences from the Android Developer Guides
	 * (http://developer.android.com/reference/android/content/SharedPreferences.html)
	 */
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
		pref.remove(helper.getSetKey());
		pref.commit();
		pref.putStringSet(helper.getSetKey(),titles);
		pref.commit();
	}
}
