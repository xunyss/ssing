package io.xunyss.ssing.api;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author XUNYSS
 */
public class Block implements Iterable<Record> {
	
	private int type = 0;	// what..
	private String name = null;
	
	private List<Record> data = new ArrayList<>();
	
	public Block(String name) {
		setName(name);
	}
	
	public Block() {
		this("");
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public int size() {
		return data.size();
	}
	
	public void set(int index, String key, String value) {
		fill(index);
		data.get(index).put(key, value);
	}
	
	public void set(String key, String value) {
		set(0, key, value);
	}
	
	public Record get(int index) {
		return data.get(index);
	}
	
	@Override
	public Iterator<Record> iterator() {
		return data.iterator();
	}
	
	private void fill(int index, boolean createRecord) {
		while (data.size() - 1 < index) {
			data.add(createRecord ? new Record() : null);
		}
	}
	private void fill(int index) {
		fill(index, true);
	}
}
