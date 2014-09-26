package com.example.bmaroney_todolist;

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
		loader=getLoader(main,prefKey);
		toDoList=loader.loadToDoList();
		saver=new ToDoListSaver(main,main.getString(prefKey),main.getString(R.string.StringSetKey));
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
}
