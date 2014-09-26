package com.example.bmaroney_notes;

import java.util.List;

import android.os.Bundle;

public class ToDoListItem {
	private String Todo;
	private Boolean isDone=false;
	
	public ToDoListItem(String ToDo){
		Todo=ToDo;
	}
	public ToDoListItem(String ToDo, boolean state){
		this(ToDo);
		isDone=state;
	}
	public String printToDoListItem(){
		return ToDoListItem.getToDo(this)+"\n";
	}
	public static String printToDoList(List<ToDoListItem> itms){
		String result="";
		for(ToDoListItem item:itms){
			result+=item.printToDoListItem();
		}
		return result;
	}
	public static String getToDo(ToDoListItem toDoListItem) {
		return toDoListItem.getToDo();
	}
	@Override
	public String toString(){
		return printToDoListItem();
	}
	public String getToDo(){
		return Todo;
	}
	public void setToDo(String newTitle){
		Todo=newTitle;
	}
	public Boolean toDoCompleted(){
		return isDone;
	}
	public void markAsCompleted(){
		isDone=true;
	}
	public void toggleState(){
		isDone=!isDone;
	}
	@Override
	public boolean equals(Object obj){
		if(this==obj){
			return true;
		}
		else if(this.getClass()!=obj.getClass()){
			return false;
		}
		ToDoListItem item=(ToDoListItem)obj;
		return (this.Todo.equals(item.getToDo()) && item.toDoCompleted().equals(item.getToDo()));
	}
}
