package com.example.bmaroney_todolist;

import java.util.List;

import android.os.Bundle;

public class ToDoListItem {
	private String Title;
	private String todo;
	private boolean isChecked=false;
	private boolean isDone=false;
	
	public ToDoListItem(String title, String todo){
		Title=title;
		this.todo=todo;
	}
	public ToDoListItem(String title, String todo, boolean state){
		this(title,todo);
		isDone=state;
	}
	public String printToDoListItem(){
		return "Title: "+getTitle()+"\n"+"todo: "+gettoDo()+"\n";
	}
	public String printToDoList(List<ToDoListItem> itms){
		String result="";
		for(ToDoListItem item:itms){
			result+=item.printToDoListItem();
		}
		return result;
	}
	@Override
	public String toString(){
		return printToDoListItem();
	}
	public String getTitle(){
		return Title;
	}
	public String gettoDo(){
		return todo;
	}
	public void setTitle(String newTitle){
		Title=newTitle;
	}
	public void setToDo(String newTodo){
		todo=newTodo;
	}
	public boolean isSelected(){
		return isChecked;
	}
	public void setSelected(){
		isChecked=true;
	}
	public boolean toDoCompleted(){
		return isDone;
	}
	public void markAsCompleted(){
		isDone=true;
	}
}
