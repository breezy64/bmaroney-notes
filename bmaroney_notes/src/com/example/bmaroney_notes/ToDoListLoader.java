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
		//Make sure the preference file isn't empty and that the Arraylist is empty
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
