/*
 * Copyright (C) 2014 The Android Open Source Project
 * Copyright (C) 2014 Byron Maroney
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 */
package com.example.bmaroney_notes;
/*
 * My implementation of a MulitChoiceListener is based on the ones presented in the
 * Android Developer Guides (http://developer.android.com/guide/topics/ui/menus.html)
 */
import java.util.ArrayList;

import com.example.bmaroney_notes.R;

import android.widget.AbsListView;
import android.widget.ListView;
import android.app.Activity;
import android.content.Intent;
import android.view.ActionMode;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
public class MainActivityMultiChoiceListener implements AbsListView.MultiChoiceModeListener {
	    private MainActivity callingAct;
	    private int menuID;
	    private ArrayList<Integer> positions;
		public MainActivityMultiChoiceListener(MainActivity act, int menu,ListView list){
			callingAct=act;
	    	menuID=menu;
	    	list.setChoiceMode(list.CHOICE_MODE_MULTIPLE_MODAL);
	    	positions=new ArrayList<Integer>();
	    }
	    @Override
	    public boolean onCreateActionMode(ActionMode mode, Menu menu) {
	        // Inflate a menu resource providing context menu items
	        MenuInflater inflater = mode.getMenuInflater();
	        inflater.inflate(menuID, menu);
	        return true;
	    }

	    @Override
	    public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
	        return false; // Return false if nothing is done
	    }

	    // Called when the user selects a contextual menu item
	    @Override
	    public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
	        switch (item.getItemId()) {
	            case R.id.delete:
	                callingAct.deleteToDo(positions);
	            	mode.finish(); 
	                return true;
	            case R.id.email:
	            	callingAct.sendEmail(positions);
	            	mode.finish();
	                return true;
	            case R.id.action_mark:
	            	callingAct.toggleState(positions);
	            	mode.finish(); 
	            case R.id.action_sendToArchive:
	            	callingAct.sendtoArchive(positions);
	            	mode.finish(); 
	                return true;
	            case R.id.unarch:
	            	((ArchiveActivity)callingAct).unarchive(positions);
	            	mode.finish();	            	
	            default:
	                return false;
	        }
	    }
	    // Called when the user exits the action mode
	    @Override
	    public void onDestroyActionMode(ActionMode mode) {
	    	positions.clear();
	    }

		@Override
		public void onItemCheckedStateChanged(ActionMode mode, int position,long id, boolean checked) {
			if(checked){
				positions.add(position);
			}
			else if(!checked){
				positions.remove(new Integer(position));
			}
			
			
		}
}
