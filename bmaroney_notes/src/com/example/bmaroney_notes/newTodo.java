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

import com.example.bmaroney_notes.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class newTodo extends Activity {
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_newtodo);
	}
	public void sendToDo(View view){
		ToDoListController controller=new ToDoListController(this,R.string.prefs_ToDos);
		if(!getToDo().equals(""))
		controller.saveToDoListItem(new ToDoListItem(getToDo(),false));
		finish();
		
	}
	private String getToDo(){
		return getTextFromTextBox(R.id.todoText);
	}
	private String getTextFromTextBox(int id){
		EditText title=(EditText) this.findViewById(id);
		return title.getText().toString();
	}
	
}
