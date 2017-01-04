package io.xunyss.ssing.api;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

import org.junit.Test;

import io.xunyss.ssing.api.tr.BlockInfo;
import io.xunyss.ssing.api.tr.FieldInfo;
import io.xunyss.ssing.api.tr.FieldType;
import io.xunyss.ssing.api.tr.TrInfo;

public class ParserTest {
	
	String resdata = "************************** 레코드명:t1101InBlock **************************"
			+"\nNo,한글명,필드명,영문명,레코드타입,데이터사이즈,옵셋,소수점"
			+"\n0,단축코드,shcode,shcode,1,6,0,0"
			+"\n************************** 레코드명:t1101OutBlock **************************"
			+"\nNo,한글명,필드명,영문명,레코드타입,데이터사이즈,옵셋,소수점"
			+"\n0,한글명,hname,hname,1,20,0,0"
			+"\n2,현재가,price,price,2,8,21,0"
			+"\n4,전일대비구분,sign,sign,1,1,30,0"
			+"\n6,전일대비,change,change,2,8,32,0"
			+"\n8,등락율,diff,diff,4,6,41,2"
			+"\n10,누적거래량,volume,volume,2,12,48,0"
			+"\n12,전일종가,jnilclose,jnilclose,2,8,61,0"
			+"\n14,매도호가1,offerho1,offerho1,2,8,70,0"
			+"\n16,매수호가1,bidho1,bidho1,2,8,79,0"
			+"\n18,매도호가수량1,offerrem1,offerrem1,2,12,88,0"
			+"\n20,매수호가수량1,bidrem1,bidrem1,2,12,101,0"
			+"\n22,직전매도대비수량1,preoffercha1,preoffercha1,2,12,114,0"
			+"\n24,직전매수대비수량1,prebidcha1,prebidcha1,2,12,127,0"
			+"\n26,매도호가2,offerho2,offerho2,2,8,140,0"
			+"\n28,매수호가2,bidho2,bidho2,2,8,149,0"
			+"\n30,매도호가수량2,offerrem2,offerrem2,2,12,158,0"
			+"\n32,매수호가수량2,bidrem2,bidrem2,2,12,171,0"
			+"\n34,직전매도대비수량2,preoffercha2,preoffercha2,2,12,184,0"
			+"\n36,직전매수대비수량2,prebidcha2,prebidcha2,2,12,197,0"
			+"\n38,매도호가3,offerho3,offerho3,2,8,210,0"
			+"\n40,매수호가3,bidho3,bidho3,2,8,219,0"
			+"\n42,매도호가수량3,offerrem3,offerrem3,2,12,228,0"
			+"\n44,매수호가수량3,bidrem3,bidrem3,2,12,241,0"
			+"\n46,직전매도대비수량3,preoffercha3,preoffercha3,2,12,254,0"
			+"\n48,직전매수대비수량3,prebidcha3,prebidcha3,2,12,267,0"
			+"\n50,매도호가4,offerho4,offerho4,2,8,280,0"
			+"\n52,매수호가4,bidho4,bidho4,2,8,289,0"
			+"\n54,매도호가수량4,offerrem4,offerrem4,2,12,298,0"
			+"\n56,매수호가수량4,bidrem4,bidrem4,2,12,311,0"
			+"\n58,직전매도대비수량4,preoffercha4,preoffercha4,2,12,324,0"
			+"\n60,직전매수대비수량4,prebidcha4,prebidcha4,2,12,337,0"
			+"\n62,매도호가5,offerho5,offerho5,2,8,350,0"
			+"\n64,매수호가5,bidho5,bidho5,2,8,359,0"
			+"\n66,매도호가수량5,offerrem5,offerrem5,2,12,368,0"
			+"\n68,매수호가수량5,bidrem5,bidrem5,2,12,381,0"
			+"\n70,직전매도대비수량5,preoffercha5,preoffercha5,2,12,394,0"
			+"\n72,직전매수대비수량5,prebidcha5,prebidcha5,2,12,407,0"
			+"\n74,매도호가6,offerho6,offerho6,2,8,420,0"
			+"\n76,매수호가6,bidho6,bidho6,2,8,429,0"
			+"\n78,매도호가수량6,offerrem6,offerrem6,2,12,438,0"
			+"\n80,매수호가수량6,bidrem6,bidrem6,2,12,451,0"
			+"\n82,직전매도대비수량6,preoffercha6,preoffercha6,2,12,464,0"
			+"\n84,직전매수대비수량6,prebidcha6,prebidcha6,2,12,477,0"
			+"\n86,매도호가7,offerho7,offerho7,2,8,490,0"
			+"\n88,매수호가7,bidho7,bidho7,2,8,499,0"
			+"\n90,매도호가수량7,offerrem7,offerrem7,2,12,508,0"
			+"\n92,매수호가수량7,bidrem7,bidrem7,2,12,521,0"
			+"\n94,직전매도대비수량7,preoffercha7,preoffercha7,2,12,534,0"
			+"\n96,직전매수대비수량7,prebidcha7,prebidcha7,2,12,547,0"
			+"\n98,매도호가8,offerho8,offerho8,2,8,560,0"
			+"\n100,매수호가8,bidho8,bidho8,2,8,569,0"
			+"\n102,매도호가수량8,offerrem8,offerrem8,2,12,578,0"
			+"\n104,매수호가수량8,bidrem8,bidrem8,2,12,591,0"
			+"\n106,직전매도대비수량8,preoffercha8,preoffercha8,2,12,604,0"
			+"\n108,직전매수대비수량8,prebidcha8,prebidcha8,2,12,617,0"
			+"\n110,매도호가9,offerho9,offerho9,2,8,630,0"
			+"\n112,매수호가9,bidho9,bidho9,2,8,639,0"
			+"\n114,매도호가수량9,offerrem9,offerrem9,2,12,648,0"
			+"\n116,매수호가수량9,bidrem9,bidrem9,2,12,661,0"
			+"\n118,직전매도대비수량9,preoffercha9,preoffercha9,2,12,674,0"
			+"\n120,직전매수대비수량9,prebidcha9,prebidcha9,2,12,687,0"
			+"\n122,매도호가10,offerho10,offerho10,2,8,700,0"
			+"\n124,매수호가10,bidho10,bidho10,2,8,709,0"
			+"\n126,매도호가수량10,offerrem10,offerrem10,2,12,718,0"
			+"\n128,매수호가수량10,bidrem10,bidrem10,2,12,731,0"
			+"\n130,직전매도대비수량10,preoffercha10,preoffercha10,2,12,744,0"
			+"\n132,직전매수대비수량10,prebidcha10,prebidcha10,2,12,757,0"
			+"\n134,매도호가수량합,offer,offer,2,12,770,0"
			+"\n136,매수호가수량합,bid,bid,2,12,783,0"
			+"\n138,직전매도대비수량합,preoffercha,preoffercha,2,12,796,0"
			+"\n140,직전매수대비수량합,prebidcha,prebidcha,2,12,809,0"
			+"\n142,수신시간,hotime,hotime,1,8,822,0"
			+"\n144,예상체결가격,yeprice,yeprice,2,8,831,0"
			+"\n146,예상체결수량,yevolume,yevolume,2,12,840,0"
			+"\n148,예상체결전일구분,yesign,yesign,1,1,853,0"
			+"\n150,예상체결전일대비,yechange,yechange,2,8,855,0"
			+"\n152,예상체결등락율,yediff,yediff,4,6,864,2"
			+"\n154,시간외매도잔량,tmoffer,tmoffer,2,12,871,0"
			+"\n156,시간외매수잔량,tmbid,tmbid,2,12,884,0"
			+"\n158,동시구분,ho_status,ho_status,1,1,897,0"
			+"\n160,단축코드,shcode,shcode,1,6,899,0"
			+"\n162,상한가,uplmtprice,uplmtprice,2,8,906,0"
			+"\n164,하한가,dnlmtprice,dnlmtprice,2,8,915,0"
			+"\n166,시가,open,open,2,8,924,0"
			+"\n168,고가,high,high,2,8,933,0"
			+"\n170,저가,low,low,2,8,942,0";
	
	TrInfo trinfo = new TrInfo();
	
	@Test
	public void parse() throws IOException {
		System.out.println("start..");
		trinfo.setTrCode("1111");
		trinfo.setTrDesc("2222");
		
		String blockName = null;
		BlockInfo blockInfo = null;
		int fieldIndex = 0;
		
		String line = null;
		BufferedReader reader = new BufferedReader(new StringReader(resdata));
		
		while ((line = reader.readLine()) != null) {
			if (line.charAt(0) == '*') {		// block-name
				blockName = getBlockName(line);
				fieldIndex = 0;
				
				System.out.println("blockName: " + blockName);
				blockInfo = trinfo.addBlockInfo(blockName);
				continue;
			}
			if (line.isEmpty() || line.charAt(0) == 'N') {		// desc
				continue;
			}
			
			String[] info = line.split(",");	// size: 8
			String f1 = info[2];	// name
			String f2 = info[1];	// desc
			String f3 = info[4];	// type
			String f4 = info[5];	// size
			System.out.println(f1 + " " + f2 + " " + f3 + " " + f4);
			
			
			FieldInfo fieldInfo = new FieldInfo();
			fieldInfo.setIndex(fieldIndex++);
			fieldInfo.setName(f1);
			fieldInfo.setDesc(f2);
			fieldInfo.setType(FieldType.CHAR);
			fieldInfo.setSize(Integer.parseInt(f4));
			
			blockInfo.addFieldInfo(fieldInfo);
		}
		
		reader.close();
		
		System.out.println(trinfo);
	}
	
	private String getBlockName(final String line) {
		int pos = line.indexOf(':') + 1;
		String str = line.substring(pos);
		pos = str.indexOf(' ');
		str = str.substring(0, pos);
		return str;
	}
}
