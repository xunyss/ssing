package io.xunyss.ssing.api.tr;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

/**
 * 
 * @author XUNYSS
 */
public class BlockInfo {
	
	/**
	 * 
	 */
	public enum IO {
		INPUT, OUTPUT
	}
	
	//--------------------------------------------------------------------------
	
	private String name;
	private IO io;
	private List<FieldInfo> fieldList;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public IO getIo() {
		return io;
	}
	public void setIo(IO io) {
		this.io = io;
	}
	public List<FieldInfo> getFieldList() {
		return fieldList;
	}
	public void setFieldList(List<FieldInfo> fieldList) {
		this.fieldList = fieldList;
	}
	
	//--------------------------------------------------------------------------
	
	public BlockInfo(String name) {
		fieldList = new ArrayList<>();
		this.name = name;
		this.io = name.contains("InBlock") ? IO.INPUT : IO.OUTPUT;
	}
	
	//--------------------------------------------------------------------------
	
	private Set<String> fieldNames = null;
	
	public Set<String> getFieldNames() {
		if (fieldNames != null) {
			return fieldNames;
		}
		
		fieldNames = new HashSet<>();
		
		Iterator<FieldInfo> fields = fieldList.iterator();
		FieldInfo fieldInfo;
		while (fields.hasNext()) {
			fieldInfo = fields.next();
			fieldNames.add(fieldInfo.getName());
		}
		
		return fieldNames;
	}
	
	public void addFieldInfo(FieldInfo fieldInfo) {
		fieldList.add(fieldInfo);
	}
}
