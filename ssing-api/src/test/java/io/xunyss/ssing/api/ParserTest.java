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
	
	String resdata = "************************** ���ڵ��:t1101InBlock **************************"
			+"\nNo,�ѱ۸�,�ʵ��,������,���ڵ�Ÿ��,�����ͻ�����,�ɼ�,�Ҽ���"
			+"\n0,�����ڵ�,shcode,shcode,1,6,0,0"
			+"\n************************** ���ڵ��:t1101OutBlock **************************"
			+"\nNo,�ѱ۸�,�ʵ��,������,���ڵ�Ÿ��,�����ͻ�����,�ɼ�,�Ҽ���"
			+"\n0,�ѱ۸�,hname,hname,1,20,0,0"
			+"\n2,���簡,price,price,2,8,21,0"
			+"\n4,���ϴ�񱸺�,sign,sign,1,1,30,0"
			+"\n6,���ϴ��,change,change,2,8,32,0"
			+"\n8,�����,diff,diff,4,6,41,2"
			+"\n10,�����ŷ���,volume,volume,2,12,48,0"
			+"\n12,��������,jnilclose,jnilclose,2,8,61,0"
			+"\n14,�ŵ�ȣ��1,offerho1,offerho1,2,8,70,0"
			+"\n16,�ż�ȣ��1,bidho1,bidho1,2,8,79,0"
			+"\n18,�ŵ�ȣ������1,offerrem1,offerrem1,2,12,88,0"
			+"\n20,�ż�ȣ������1,bidrem1,bidrem1,2,12,101,0"
			+"\n22,�����ŵ�������1,preoffercha1,preoffercha1,2,12,114,0"
			+"\n24,�����ż�������1,prebidcha1,prebidcha1,2,12,127,0"
			+"\n26,�ŵ�ȣ��2,offerho2,offerho2,2,8,140,0"
			+"\n28,�ż�ȣ��2,bidho2,bidho2,2,8,149,0"
			+"\n30,�ŵ�ȣ������2,offerrem2,offerrem2,2,12,158,0"
			+"\n32,�ż�ȣ������2,bidrem2,bidrem2,2,12,171,0"
			+"\n34,�����ŵ�������2,preoffercha2,preoffercha2,2,12,184,0"
			+"\n36,�����ż�������2,prebidcha2,prebidcha2,2,12,197,0"
			+"\n38,�ŵ�ȣ��3,offerho3,offerho3,2,8,210,0"
			+"\n40,�ż�ȣ��3,bidho3,bidho3,2,8,219,0"
			+"\n42,�ŵ�ȣ������3,offerrem3,offerrem3,2,12,228,0"
			+"\n44,�ż�ȣ������3,bidrem3,bidrem3,2,12,241,0"
			+"\n46,�����ŵ�������3,preoffercha3,preoffercha3,2,12,254,0"
			+"\n48,�����ż�������3,prebidcha3,prebidcha3,2,12,267,0"
			+"\n50,�ŵ�ȣ��4,offerho4,offerho4,2,8,280,0"
			+"\n52,�ż�ȣ��4,bidho4,bidho4,2,8,289,0"
			+"\n54,�ŵ�ȣ������4,offerrem4,offerrem4,2,12,298,0"
			+"\n56,�ż�ȣ������4,bidrem4,bidrem4,2,12,311,0"
			+"\n58,�����ŵ�������4,preoffercha4,preoffercha4,2,12,324,0"
			+"\n60,�����ż�������4,prebidcha4,prebidcha4,2,12,337,0"
			+"\n62,�ŵ�ȣ��5,offerho5,offerho5,2,8,350,0"
			+"\n64,�ż�ȣ��5,bidho5,bidho5,2,8,359,0"
			+"\n66,�ŵ�ȣ������5,offerrem5,offerrem5,2,12,368,0"
			+"\n68,�ż�ȣ������5,bidrem5,bidrem5,2,12,381,0"
			+"\n70,�����ŵ�������5,preoffercha5,preoffercha5,2,12,394,0"
			+"\n72,�����ż�������5,prebidcha5,prebidcha5,2,12,407,0"
			+"\n74,�ŵ�ȣ��6,offerho6,offerho6,2,8,420,0"
			+"\n76,�ż�ȣ��6,bidho6,bidho6,2,8,429,0"
			+"\n78,�ŵ�ȣ������6,offerrem6,offerrem6,2,12,438,0"
			+"\n80,�ż�ȣ������6,bidrem6,bidrem6,2,12,451,0"
			+"\n82,�����ŵ�������6,preoffercha6,preoffercha6,2,12,464,0"
			+"\n84,�����ż�������6,prebidcha6,prebidcha6,2,12,477,0"
			+"\n86,�ŵ�ȣ��7,offerho7,offerho7,2,8,490,0"
			+"\n88,�ż�ȣ��7,bidho7,bidho7,2,8,499,0"
			+"\n90,�ŵ�ȣ������7,offerrem7,offerrem7,2,12,508,0"
			+"\n92,�ż�ȣ������7,bidrem7,bidrem7,2,12,521,0"
			+"\n94,�����ŵ�������7,preoffercha7,preoffercha7,2,12,534,0"
			+"\n96,�����ż�������7,prebidcha7,prebidcha7,2,12,547,0"
			+"\n98,�ŵ�ȣ��8,offerho8,offerho8,2,8,560,0"
			+"\n100,�ż�ȣ��8,bidho8,bidho8,2,8,569,0"
			+"\n102,�ŵ�ȣ������8,offerrem8,offerrem8,2,12,578,0"
			+"\n104,�ż�ȣ������8,bidrem8,bidrem8,2,12,591,0"
			+"\n106,�����ŵ�������8,preoffercha8,preoffercha8,2,12,604,0"
			+"\n108,�����ż�������8,prebidcha8,prebidcha8,2,12,617,0"
			+"\n110,�ŵ�ȣ��9,offerho9,offerho9,2,8,630,0"
			+"\n112,�ż�ȣ��9,bidho9,bidho9,2,8,639,0"
			+"\n114,�ŵ�ȣ������9,offerrem9,offerrem9,2,12,648,0"
			+"\n116,�ż�ȣ������9,bidrem9,bidrem9,2,12,661,0"
			+"\n118,�����ŵ�������9,preoffercha9,preoffercha9,2,12,674,0"
			+"\n120,�����ż�������9,prebidcha9,prebidcha9,2,12,687,0"
			+"\n122,�ŵ�ȣ��10,offerho10,offerho10,2,8,700,0"
			+"\n124,�ż�ȣ��10,bidho10,bidho10,2,8,709,0"
			+"\n126,�ŵ�ȣ������10,offerrem10,offerrem10,2,12,718,0"
			+"\n128,�ż�ȣ������10,bidrem10,bidrem10,2,12,731,0"
			+"\n130,�����ŵ�������10,preoffercha10,preoffercha10,2,12,744,0"
			+"\n132,�����ż�������10,prebidcha10,prebidcha10,2,12,757,0"
			+"\n134,�ŵ�ȣ��������,offer,offer,2,12,770,0"
			+"\n136,�ż�ȣ��������,bid,bid,2,12,783,0"
			+"\n138,�����ŵ���������,preoffercha,preoffercha,2,12,796,0"
			+"\n140,�����ż���������,prebidcha,prebidcha,2,12,809,0"
			+"\n142,���Žð�,hotime,hotime,1,8,822,0"
			+"\n144,����ü�ᰡ��,yeprice,yeprice,2,8,831,0"
			+"\n146,����ü�����,yevolume,yevolume,2,12,840,0"
			+"\n148,����ü�����ϱ���,yesign,yesign,1,1,853,0"
			+"\n150,����ü�����ϴ��,yechange,yechange,2,8,855,0"
			+"\n152,����ü������,yediff,yediff,4,6,864,2"
			+"\n154,�ð��ܸŵ��ܷ�,tmoffer,tmoffer,2,12,871,0"
			+"\n156,�ð��ܸż��ܷ�,tmbid,tmbid,2,12,884,0"
			+"\n158,���ñ���,ho_status,ho_status,1,1,897,0"
			+"\n160,�����ڵ�,shcode,shcode,1,6,899,0"
			+"\n162,���Ѱ�,uplmtprice,uplmtprice,2,8,906,0"
			+"\n164,���Ѱ�,dnlmtprice,dnlmtprice,2,8,915,0"
			+"\n166,�ð�,open,open,2,8,924,0"
			+"\n168,��,high,high,2,8,933,0"
			+"\n170,����,low,low,2,8,942,0";
	
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
