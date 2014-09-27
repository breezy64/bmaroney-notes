/*
 *      Copyright 2014 Byron Maroney

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

import android.os.Bundle;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ArchiveActivity extends MainActivity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		setItems(new CheckBoxAdapter(this,R.layout.test));
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
		for(ToDoListItem item:super.arrayAdapterSubList(positions)){
			getController().unarchive(item);
			getItems().remove(item);
		}
		
	}
}
