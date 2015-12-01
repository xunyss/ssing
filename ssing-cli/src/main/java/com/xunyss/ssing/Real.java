package com.xunyss.ssing;

import com.xunyss.ssing.xa.dataset.ClassFactory;
import com.xunyss.ssing.xa.dataset.IXAReal;
import com.xunyss.ssing.xa.dataset.events._IXARealEvents;

/**
 * 
 * @author XUNYSS
 */
public class Real {

	private static class Holder {
		private static final Real instance = new Real();
	}
	
	public static Real getInstance() {
		return Holder.instance;
	}
	
	private Real() {
		init();
	}
	
	private IXAReal iXAReal = null;
	
	private void init() {
		iXAReal = ClassFactory.createXAReal();
		iXAReal.advise(_IXARealEvents.class, new _IXARealEvents() {
			@Override
			public void receiveRealData(String szTrCode) {
				String ss = iXAReal.getFieldData("OutBlock", "price");
				System.out.println("REAL DATA >> " + ss);
			}

			@Override
			public void recieveLinkData(String szLinkName, String szData, String szFiller) {
			
			}
		});
	}
	
	public void now() {
		iXAReal.resFileName("\\res\\S3_.res");
		iXAReal.setFieldData("InBlock", "shcode", "000660");
		iXAReal.adviseRealData();
	}
	
	public void stop() {
		iXAReal.unadviseRealData();
	}
}
