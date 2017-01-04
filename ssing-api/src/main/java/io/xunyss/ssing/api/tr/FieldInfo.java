package io.xunyss.ssing.api.tr;

/**
 * 
 * @author XUNYSS
 */
public class FieldInfo {
	
	private int index;			// block 내에서의 순서
	private String name;		// 필드명
	private String desc;		// 한글필드명
	
	private FieldType type;
	private int size;
	
	
	public int getIndex() {
		return index;
	}
	public void setIndex(int index) {
		this.index = index;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	
	public FieldType getType() {
		return type;
	}
	public void setType(FieldType type) {
		this.type = type;
	}
	public int getSize() {
		return size;
	}
	public void setSize(int size) {
		this.size = size;
	}
}
