package com.example.bmaroney_todolist;


import android.graphics.Paint;
import android.widget.CheckedTextView;
import android.widget.TextView;

public class StrikeThroughPainter {
	 public void paint(TextView text, int position){
			 text.setPaintFlags(text.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
	 }
}
