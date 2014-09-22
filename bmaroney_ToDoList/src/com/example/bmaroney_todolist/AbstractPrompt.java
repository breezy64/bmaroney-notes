package com.example.bmaroney_todolist;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public abstract class AbstractPrompt extends DialogFragment {
	private String message;
	public AbstractPrompt(String message){
		 this.message=message;
	 }
	 @Override
	 public Dialog onCreateDialog(Bundle savedInstanceState) {
	 // Use the Builder class for convenient dialog construction
		 AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
	     builder.setMessage(message);
	     setButtons(builder);
	        // Create the AlertDialog object and return it
	        return builder.create();
	    }
	 public void setMessage(String message){
		 this.message=message;
	 }
	 protected abstract void setButtons(AlertDialog.Builder builder,String...buttons);
}
