/*
 * Copyright [2011] [Marco Dinacci <marco.dinacci@gmail.com>]
 * Copyright [2014] Byron Maroney
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.Ian F. Darwin

 * The license text is available online and in the LICENSE file accompanying the distribution
 * of this program.
 */
package com.example.bmaroney_notes;

/*I learnt how to make a custom adapter by reading the following Android cookbook:
 * “Android Cookbook, edited by Ian F. Darwin (O’Reilly). Copyright 2012 O’Reilly Media, Inc., 978-1-449-38841-6.”
 * That book provided a link to source code 
 * written by  Marco Dinacci: http://www.intransitione.com/intransitione.com/code/android/adv_listview_demo.zip)
 * which may CheckboxAdapter is based on. 
 */

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import com.example.bmaroney_notes.R;

public class CheckBoxAdapter extends ArrayAdapter<ToDoListItem> {
	private LayoutInflater mInflater;
    private int mViewResourceId;
    
	public CheckBoxAdapter(Context context, int textViewResourceId) {
		super(context, textViewResourceId);
		mViewResourceId = textViewResourceId;
		mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}    
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = mInflater.inflate(mViewResourceId, null);
	    CheckBox cb = (CheckBox)convertView.findViewById(R.id.checkBox1);
	    cb.setChecked(getItem(position).toDoCompleted());
	    TextView tv = (TextView)convertView.findViewById(R.id.textView1);
	    tv.setText(getItem(position).toString());
	    return convertView;
	}
}
