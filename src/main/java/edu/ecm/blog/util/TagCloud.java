package edu.ecm.blog.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;



public class TagCloud {
	
	private List<String> tags = new ArrayList<String>();

	public void add() {
		//do-nothing.
	}
	
	public void add(String tagToAdd) {
		if (tagToAdd != null){
			if(!this.contains(tagToAdd) && !tagToAdd.equals("")){
				this.tags.add(tagToAdd);
			}
		}
	}
   
	public void add(String tag1, String tag2, String tag3) {
		this.add(tag1);
		this.add(tag2);
		this.add(tag3);
	}
	
	public void add(String tag1, String tag2, String tag3, String tag4, String tag5) {
		this.add(tag1);
		this.add(tag2);
		this.add(tag3);
		this.add(tag4);
		this.add(tag5);
	}
	
	public void add(String[] tabTag) {
		if (tabTag != null){
			for (String tag : tabTag) {
				tags.add(tag);
			}
		}
	}
	
    public int size() {
        return tags.size();
    }

	public boolean contains(String monTag) {
		for (String tag : tags) {
			if(tag.equals(monTag)){
				return true;
			}
		}
		return false;
	}

	public void top(int i) {
		int size = this.tags.size();
		if(i > 0){
			for (int j = 0; j < size - i; j++) {
				this.tags.remove(j);
			}
		} else {
			for (int j = size -1; j > size + i - 1; j--) {
				if(j >= 0){
					this.tags.remove(j);
				}
			}
		}
	}

	public void shuffle() {
		java.util.Collections.shuffle(tags);
	}
	
}
