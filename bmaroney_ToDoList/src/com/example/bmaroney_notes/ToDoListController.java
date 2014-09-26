package com.example.bmaroney_notes;

import java.util.List;

import com.example.bmaroney_notes.R;

import android.app.Activity;
import android.widget.ArrayAdapter;

public class ToDoListController {
	private Activity ToDoAct;
	private ToDoListSaver saver;
	private static ToDoListLoader loader=null;
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
}