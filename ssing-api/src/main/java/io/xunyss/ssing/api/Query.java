package io.xunyss.ssing.api;

import io.xunyss.ssing.xa.dataset.ClassFactory;
import io.xunyss.ssing.xa.dataset.IXAQuery;

/**
 * 
 * @author XUNYSS
 */
public abstract class Query {
	
	// ������ trCode ��ü�� new �ϰ� ���� �ʱ� ����
	public static Query create(String trCode) {
		return new Query(trCode) {
		};
	}
	
	//--------------------------------------------------------------------------
	
	private String trCode;
	private IXAQuery xaQuery;
	
	private Query(String trCode) {
		this.trCode = trCode;
		this.xaQuery = ClassFactory.createXAQuery();
		
		xaQuery.setName(Query.class.getName() + "." + trCode);
		xaQuery.resFileName("\\res\\" + trCode + ".res");
	}
	
	public String getTrCode() {
		return trCode;
	}
	
	public void setInput(String shcode) {
		xaQuery.setFieldData(trCode + "InBlock", "shcode", 0, shcode);
	}
	
	public Object request() {
		int result = xaQuery.request(false);
		return null;
	}
}
