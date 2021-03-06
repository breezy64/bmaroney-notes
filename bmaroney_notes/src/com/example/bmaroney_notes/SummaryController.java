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

import android.app.Activity;

public class SummaryController {
	private int normal_numToDosChecked=0;
	private int normal_numToDosUnchecked=0;
	private int normal_numToDosArchived=0;
	private int archive_numToDosChecked=0;
	private int archive_numToDosUnchecked=0;
	
	
	public SummaryController(Activity summaryActivity, String prefsNormalMode,String prefsArchiveMode) {
		ToDoListLoader normal=new ToDoListLoader(summaryActivity,prefsNormalMode,summaryActivity.getString(R.string.StringSetKey));
		getNormalModeStats(normal.loadToDoList());
		ToDoListLoader archive=new ToDoListLoader(summaryActivity,prefsArchiveMode,summaryActivity.getString(R.string.StringSetKey));
		getArchiveModeStats(archive.loadToDoList());
	}

	private void getNormalModeStats(ArrayList<ToDoListItem> loadToDoList) {
		for(ToDoListItem item:loadToDoList){
			if(item.toDoCompleted()){
				this.normal_numToDosChecked++;
			}
			else{
				this.normal_numToDosUnchecked++;
			}
		}
		
	}
	private void getArchiveModeStats(ArrayList<ToDoListItem> loadToDoList) {
		if(!loadToDoList.isEmpty()){
			for(ToDoListItem item: loadToDoList){
				this.normal_numToDosArchived++;
				if(item.toDoCompleted()){
					this.archive_numToDosChecked++;
				}
				else{
					this.archive_numToDosUnchecked++;
				}
			}
		}
		
		
	}
	public String summary(){
		return "Normal Mode\n\nNumber of ToDoItems Checked: "+this.getNormal_numToDosChecked()+
			   "\nNumber of ToDoItems Unchecked: "+this.getNormal_numToDosUnchecked()+
			   "\nNumber of Archived ToDoItems: "+this.getNormal_numToDosArchived()+
			   "\n\nArchive Mode\n\n"+
			   "Number of Archived ToDoItems Checked: "+this.getArchive_numToDosChecked()+
			   "\nNumber of Archived ToDoItems Unchecked: "+this.getArchive_numToDosUnchecked();
	}


	protected int getNormal_numToDosChecked() {
		return normal_numToDosChecked;
	}


	protected int getNormal_numToDosUnchecked() {
		return normal_numToDosUnchecked;
	}


	protected int getNormal_numToDosArchived() {
		return normal_numToDosArchived;
	}


	protected int getArchive_numToDosChecked() {
		return archive_numToDosChecked;
	}


	protected int getArchive_numToDosUnchecked() {
		return archive_numToDosUnchecked;
	}


	

}
