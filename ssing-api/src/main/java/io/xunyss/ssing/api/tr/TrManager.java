package io.xunyss.ssing.api.tr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author XUNYSS
 */
public class TrManager {
	
	//--------------------------------------------------------------------------
	// singleton
	//--------------------------------------------------------------------------
	
	private static class InstanceHolder {
		private static final TrManager instance = new TrManager();
	}
	
	public static TrManager getInstance() {
		return InstanceHolder.instance;
	}
	
	private TrManager() {
		trMap = Collections.synchronizedMap(new HashMap<String, TrInfo>());
	}
	
	
	//--------------------------------------------------------------------------
	// TrManager
	//--------------------------------------------------------------------------
	
	private Map<String, TrInfo> trMap = null;
	
	public void register(String trCode, String trDesc, String resData) {
		TrInfo trInfo = trMap.get(trCode);
		if (trInfo == null) {
			trInfo = parseResData(trCode, trDesc, resData);
			trMap.put(trCode, trInfo);
		}
	}
	
	public TrInfo getTrInfo(String trCode) {
		return trMap.get(trCode);
	}
	
	private TrInfo parseResData(String trCode, String trDesc, String resData) {
		TrInfo trInfo = new TrInfo();
		trInfo.setTrCode(trCode);
		trInfo.setTrDesc(trDesc);
		
		String blockName = null;
		BlockInfo blockInfo = null;
		int fieldIndex = 0;
		
		String line = null;
		BufferedReader reader = new BufferedReader(new StringReader(resData));
		
		try {
			while ((line = reader.readLine()) != null) {
				if (line.charAt(0) == '*') {
					blockName = getBlockName(line);
					blockInfo = trInfo.addBlockInfo(blockName);
					fieldIndex = 0;
					
					continue;
				}
				if (line.isEmpty() || line.charAt(0) == 'N') {
					continue;
				}
				
				String[] fieldData = line.split(",");
				FieldInfo fieldInfo = new FieldInfo();
				fieldInfo.setIndex(fieldIndex++);
				fieldInfo.setName(fieldData[2]);
				fieldInfo.setDesc(fieldData[1]);
				fieldInfo.setType(FieldType.valueOfCode(fieldData[4]));
				fieldInfo.setSize(Integer.parseInt(fieldData[5]));
				
				blockInfo.addFieldInfo(fieldInfo);
			}
		}
		catch (IOException ioe) {
			ioe.printStackTrace();
		}
		finally {
			try {
				reader.close();
			} catch (IOException ignored) {}
		}
		
		return trInfo;
	}
	
	private String getBlockName(final String line) {
		int pos = line.indexOf(':') + 1;
		String str = line.substring(pos);
		pos = str.indexOf(' ');
		str = str.substring(0, pos);
		return str;
	}
}
