package com.example.bmaroney_todolist;

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
		Intent item=new Intent();
		int result=this.RESULT_OK;
		if(getToDoTitle().equals("")){
			result=this.RESULT_CANCELED;
		}
		item.putExtras(bundleUpTheContent());
		this.setResult(result,item);
		finish();
		
	}
	private String getToDoTitle(){
		return getTextFromTextBox(R.id.todo_Title);
	}
	private String getToDoNotes(){
		return getTextFromTextBox(R.id.todo_Notes);
	}
	private String getTextFromTextBox(int id){
		EditText title=(EditText) this.findViewById(id);
		return title.getText().toString();
	}
	public Bundle bundleUpTheContent(){
		Bundle content=new Bundle();
		content.putString(getString(R.string.titles_text),getToDoTitle());
		content.putString(getString(R.string.notes_text),getToDoNotes());
		return content;
	}
	
}
