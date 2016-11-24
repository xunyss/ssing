package io.xunyss.ssing;

import com4j.EventCookie;
import io.xunyss.ssing.xa.dataset.ClassFactory;
import io.xunyss.ssing.xa.dataset.IXAQuery;
import io.xunyss.ssing.xa.dataset.events._IXAQueryEvents;

/**
 * 
 * @author XUNYSS
 */
public class Query {
	
	/**
	 * 
	 */
	private static class Holder {
		private static final Query instance = new Query();
	}
	
	/**
	 * 
	 * @return
	 */
	public static Query getInstance() {
		return Holder.instance;
	}
	
	private Query() {
		init();
	}
	
	private IXAQuery iXAQuery = null;
	private EventCookie callback = null;
	
	private Response response = null;
	
	/**
	 * ÃÊ±âÈ­
	 */
	private void init() {
		iXAQuery = ClassFactory.createXAQuery();
		callback = iXAQuery.advise(_IXAQueryEvents.class, new QueryCallback(this));
	}
	
	public Response t1101(String shcode) {
		iXAQuery.resFileName("\\res\\t1101.res");
		iXAQuery.setFieldData("t1101InBlock", "shcode", 0, shcode);
		
		iXAQuery.request(false);
		
		synchronized (this) {
			try {
				wait(1500);		// timeout : 1500
			}
			catch (InterruptedException ie) {
				ie.printStackTrace();
			}
		}
		
		return response;
	}
	
	
	
	private class QueryCallback extends _IXAQueryEvents {
		
		private Query query = null;
		
		private QueryCallback(Query query) {
			this.query = query;
		}
		
		@Override
		public void receiveMessage(boolean bIsSystemError, String nMessageCode, String szMessage) {
			
		}
		
		@Override
		public void receiveData(String szTrCode) {
			System.out.println("¸®¾¾ºê ¼öÇàµÊ");
			Response res = new Response();
			
			if ("t1101".equals(szTrCode)) {
				res.f1 = iXAQuery.getFieldData("t1101OutBlock", "hname", 0);
				res.f2 = iXAQuery.getFieldData("t1101OutBlock", "price", 0);
			}
			
			synchronized (query) {
				query.response = res;
				query.notify();
			}
		}

		@Override
		public void receiveChartRealData(String szTrCode) {
			
		}
	}
}
