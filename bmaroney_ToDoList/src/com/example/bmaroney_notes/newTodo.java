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