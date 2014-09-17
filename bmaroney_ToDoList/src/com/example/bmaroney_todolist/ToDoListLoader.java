package com.example.bmaroney_todolist;

import java.util.Set;

import android.app.Activity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ToDoListLoader{
	private ToDoListPreferenceHelper helper;
	private Activity act;
	private ListView list;
	public ToDoListLoader(Activity act,String titleprefs_key,String titlekeys,String stateprefs_key,int listViewID){
		helper=new ToDoListPreferenceHelper(act,titleprefs_key,titlekeys,stateprefs_key);
		this.act=act;
		list=getListView(listViewID);
	}
	public void loadToDoList(ArrayAdapter<ToDoListItem> items){
		bindAdaptertoListView(items);
		if(helper.isEmpty()){
			getToDoListItems(items);
		}
	}
	private void getToDoListItems(ArrayAdapter<ToDoListItem> items){
		Set<String> titles=helper.getTitlePrefs().getStringSet(helper.getKey(),null);
		for(String title:titles){
			items.add(new ToDoListItem(title,helper.getTitlePrefs().getString(title,""),helper.getStatePrefs().getBoolean(title, false)));
			ToDoListItem item=new ToDoListItem(title,helper.getTitlePrefs().getString(title,""),helper.getStatePrefs().getBoolean(title, false));
			items.add(item);
			checkToDo(item,list,new Long(items.getItemId(items.getPosition(item))));
		}
	}
	private void checkToDo(ToDoListItem item, ListView lv, Long position){
		lv.setChoiceMode(lv.CHOICE_MODE_MULTIPLE);
		lv.setItemChecked(position.intValue(), item.toDoCompleted());
	}
	private void bindAdaptertoListView(ArrayAdapter<ToDoListItem> items){
		list.setAdapter(items);
	}
	public ListView getListView(int listViewID){
		return (ListView) act.findViewById(listViewID);
	}
}
