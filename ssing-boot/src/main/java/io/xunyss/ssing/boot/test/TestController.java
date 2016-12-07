package io.xunyss.ssing.boot.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.xunyss.ssing.xa.dataset.ClassFactory;
import io.xunyss.ssing.xa.dataset.IXAQuery;
import io.xunyss.ssing.xa.dataset.IXAReal;
import io.xunyss.ssing.xa.dataset.events._IXAQueryEvents;
import io.xunyss.ssing.xa.dataset.events._IXARealEvents;

@RestController
public class TestController {
	
	private final Logger log = LoggerFactory.getLogger(getClass());
	
	@RequestMapping("/t/sess")
	public ModelMap connect() {
//		
//		SSConnection xconn = new SSConnection();
//		
//		xconn.connect();
//		xconn.login();
//		
//		xconn.debug();
//		
		ModelMap model = new ModelMap();
		model.addAttribute("result", true);
		return model;
	}
	
	@RequestMapping("/t/qr")
	public String qr() {
		
		final IXAQuery iq = ClassFactory.createXAQuery();
		iq.advise(_IXAQueryEvents.class, new _IXAQueryEvents() {
			@Override
			public void receiveData(String szTrCode) {
				System.out.println("receiveData : " + szTrCode);
				
				if ("t1101".equals(szTrCode)) {
					String b = "t1101OutBlock";
					String r1 = iq.getFieldData(b, "hname", 0);
					String r2 = iq.getFieldData(b, "price", 0);
					
					System.out.println("--------------------------------------");
					System.out.println(r1 + ";" + r2);
				}
			}
		});
		iq.resFileName("\\res\\t1101.res");
		iq.setFieldData("t1101InBlock", "shcode", 0, "000660");
		int res = iq.request(false);
		System.err.println(res);
		
	//	q.dispose();
		System.out.println(iq.getPointer());
		System.out.println(iq.toString());
		
		return "qr finished.";
	}
	
	@RequestMapping("/t/r")
	public String r() {
		
		final IXAReal ir = ClassFactory.createXAReal();
		ir.resFileName("\\res\\NWS.res");
		ir.setFieldData("InBlock", "nwcode", "NWS001");
		
		ir.adviseRealData();
		ir.advise(_IXARealEvents.class, new _IXARealEvents() {

			@Override
			public void receiveRealData(String szTrCode) {
				String t = "";
				try {
					t = ir.getFieldData("OutBlock", "title");
				}
				catch (Exception e) {
					e.printStackTrace();
				}
				System.out.println(">>> " + szTrCode + ": " + t);
			}

			@Override
			public void recieveLinkData(String szLinkName, String szData, String szFiller) {
				System.out.println(">>>>>>>>>>>>> recieveLinkData......");
			}
		});
		
		return "real finished";
	}
}
