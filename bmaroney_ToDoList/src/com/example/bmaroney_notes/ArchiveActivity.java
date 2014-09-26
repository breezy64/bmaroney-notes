package com.example.bmaroney_notes;

import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;

public class ArchiveActivity extends MainActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		controller=new ToDoListController(this,R.string.prefs_ToDos);
		setItems(new CheckBoxAdapter(this,R.layout.test));
		ListView list=(ListView)findViewById(R.id.listView1);
		list.setAdapter(getItems());
		list.setMultiChoiceModeListener(new MainActivityMultiChoiceListener(this,R.menu.archive_contextmenu,list));
		setController(new ToDoListController(this,R.string.prefs_archive));	
		controller.loadToDoList(getItems());
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.archive_menu, menu);
		return true;
	}
}
