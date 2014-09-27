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

import java.util.List;

import com.example.bmaroney_notes.R;

import android.app.Activity;
import android.widget.ArrayAdapter;

public class ToDoListController {
	private Activity ToDoAct;
	private ToDoListSaver saver;
	private static ToDoListLoader loader=null;
	//singleton
	private List<ToDoListItem> toDoList;

	public ToDoListController(Activity main, int prefKey){
		this.ToDoAct=main;
		init(prefKey);
	}
	private void init(int prefKey){
		loader=getLoader(ToDoAct,prefKey);
		toDoList=loader.loadToDoList();
		saver=new ToDoListSaver(ToDoAct,ToDoAct.getString(prefKey),ToDoAct.getString(R.string.StringSetKey));
	}

	private static ToDoListLoader getLoader(Activity act, int prefKey) {
		if(loader==null){
			loader=new ToDoListLoader(act,act.getString(prefKey),act.getString(R.string.StringSetKey));
		}
		return loader;
	}

	public void loadToDoList(ArrayAdapter<ToDoListItem> items) {
			items.addAll(toDoList);
	}

	public void saveToDoListItem(ToDoListItem item) {
		saver.saveToDoListItem(item);
		toDoList.add(item);
	}

	public void remove(ToDoListItem item) {
		toDoList.remove(item);
		saver.delete(item);
		
	}

	public void sendToArchive(ToDoListItem item) {
		ToDoListSaver arch=new ToDoListSaver(ToDoAct,ToDoAct.getString(R.string.prefs_archive),ToDoAct.getString(R.string.StringSetKey));
		arch.saveToDoListItem(item);
	}
	public void softReset(){
		loader=null;
	}
	public void hardReset(int prefKey){
		softReset();
		init(prefKey);
	}
	public void unarchive(ToDoListItem item) {
		ToDoListSaver arch=new ToDoListSaver(ToDoAct,ToDoAct.getString(R.string.prefs_ToDos),ToDoAct.getString(R.string.StringSetKey));
		arch.saveToDoListItem(item);
		remove(item);
	}
	public void toggleState(ToDoListItem item) {
		item.toggleState(); 
		saver.updateState(item);
		
	}
	public String getMassEmailText() throws Exception {
		return ToDoListItem.printToDoList(loadAll());
	}
	private List<ToDoListItem> loadAll() throws Exception {
		ToDoListLoader archive=new ToDoListLoader(this.ToDoAct,ToDoAct.getString(R.string.prefs_archive),ToDoAct.getString(R.string.StringSetKey));
		ToDoListLoader normal=new ToDoListLoader(this.ToDoAct,ToDoAct.getString(R.string.prefs_ToDos),ToDoAct.getString(R.string.StringSetKey));
		List<ToDoListItem> all=archive.loadToDoList();
		all.addAll(normal.loadToDoList());
		if(all.isEmpty()){
			throw new Exception();
		}
		return all;
	}
}
