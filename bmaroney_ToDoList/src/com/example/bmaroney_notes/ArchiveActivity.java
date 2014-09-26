package com.example.bmaroney_notes;

import java.util.ArrayList;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArchiveActivity extends MainActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setItems(new ArrayAdapter<ToDoListItem>(this,android.R.layout.simple_list_item_activated_1));
		setController(new ToDoListController(this,R.string.prefs_archive));
		getController().hardReset(R.string.prefs_archive);
		getController().loadToDoList(getItems());
		ListView list=(ListView)findViewById(R.id.listView1);
		list.setAdapter(getItems());
		list.setMultiChoiceModeListener(new MainActivityMultiChoiceListener(this,R.menu.archive_contextmenu,list));
		
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}
	@Override
	protected void onPause(){
		super.onPause();
		getController().softReset();
	}
	public void unarchive(ArrayList<Integer> positions) {
		for(int i:positions){
			getController().unarchive(getItems().getItem(i));
			getItems().remove(getItems().getItem(i));
		}
		
	}
}
