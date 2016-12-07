package io.xunyss.ssing.boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.xunyss.ssing.boot.mapper.Jong;
import io.xunyss.ssing.boot.mapper.TradeMapper;
import io.xunyss.ssing.xa.dataset.IXAQuery;

@Service
public class TradeService {
	
	@Autowired
	TradeMapper mapper;
	
	@Transactional
	public void save(final IXAQuery iq) {
		String b = "t8430OutBlock";
		int cnt = iq.getBlockCount(b);
		
		System.out.println("--------------------------------------");
		System.out.println(cnt);
		System.out.println("--------------------------------------");
		
		for (int i = 0; i < cnt; i++) {
			String r1 = iq.getFieldData(b, "hname", i);
			String r2 = iq.getFieldData(b, "shcode", i);
			System.out.println(r1 + ";" + r2);
			
			Jong jong = new Jong();
			jong.setShcode(r2);
			jong.setHname(r1);
			mapper.insertJongMok(jong);
		}
	}
}
