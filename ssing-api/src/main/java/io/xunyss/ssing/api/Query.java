package io.xunyss.ssing.api;

import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com4j.EventCookie;
import io.xunyss.ssing.xa.dataset.ClassFactory;
import io.xunyss.ssing.xa.dataset.IXAQuery;
import io.xunyss.ssing.xa.dataset.events._IXAQueryEvents;

/**
 * 
 * @author XUNYSS
 */
public abstract class Query {
	
	/**
	 * 
	 */
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	// 동일한 trCode 객체를 new 하게 하지 않기 위함
	public static Query create(String trCode) {
		return new Query(trCode) {
		};
	}
	
	@Override
	protected void finalize() throws Throwable {
		super.finalize();
	//	xaQuery.dispose();
		
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
	
	public void setBlock(Block block) {
		String blockName = block.getName();
		Record record;
		Iterator<String> keys;
		String key = null, value = null;
		
		int len = block.size();
		for (int idx = 0; idx < len; idx++) {
			record = block.get(idx);
			keys = record.keySet().iterator();
			while (keys.hasNext()) {
				key = keys.next();
				value = record.get(key);
				xaQuery.setFieldData(blockName, key, idx, value);
			}
		}
	}
	
	public void wakeup() {
		synchronized (this) {
			notifyAll();
		}
	}
	
	public Object request() {
		EventCookie eventCookie = xaQuery.advise(_IXAQueryEvents.class, new _IXAQueryEvents() {
			@Override
			public void receiveData(String szTrCode) {
				log.debug("callback received: {}", szTrCode);
				wakeup();
			}
			
			@Override
			public void receiveMessage(boolean bIsSystemError, String nMessageCode, String szMessage) {
				wakeup();
			}
			
			@Override
			public void receiveChartRealData(String szTrCode) {
				wakeup();
			}
		});
		
		synchronized (this) {
			try {
				System.err.println("request before..");
				int result = xaQuery.request(false);
				wait();
			}
			catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		
		eventCookie.close();
		
		System.err.println("request returns..");
		return new Block();
	}
	
	public void dispose() {
		xaQuery.dispose();
	}
}
