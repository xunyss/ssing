package io.xunyss.ssing.api.tr;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 
 * @author XUNYSS
 */
public class TrInfo {
	
	private String trCode;
	private String trDesc;
	private List<BlockInfo> blockInfos;
	
	public String getTrCode() {
		return trCode;
	}
	public void setTrCode(String trCode) {
		this.trCode = trCode;
	}
	public String getTrDesc() {
		return trDesc;
	}
	public void setTrDesc(String trDesc) {
		this.trDesc = trDesc;
	}
	public List<BlockInfo> getBlockInfos() {
		return blockInfos;
	}
	public void setBlockInfos(List<BlockInfo> blockInfos) {
		this.blockInfos = blockInfos;
	}
	
	//--------------------------------------------------------------------------
	
	public TrInfo() {
		blockInfos = new ArrayList<>();
	}
	
	//--------------------------------------------------------------------------
	
	public BlockInfo addBlockInfo(String blockName) {
		BlockInfo blockInfo = new BlockInfo(blockName);
		blockInfos.add(blockInfo);
		return blockInfo;
	}
	
	public void getBlockNames(BlockInfo.IO io) {
		List<String> blockNames = new ArrayList<>();
		
		Iterator<BlockInfo> blocks = blockInfos.iterator();
		BlockInfo blockInfo;
		while (blocks.hasNext()) {
			blockInfo = blocks.next();
			if (blockInfo.getIo() == io) {
				blockNames.add(blockInfo.getName());
				///////////////////////////////////////////
				// TODO
			}
		}
	}
	
	public BlockInfo getBlockInfo(String blockName) {
		Iterator<BlockInfo> blocks = blockInfos.iterator();
		BlockInfo blockInfo;
		while (blocks.hasNext()) {
			blockInfo = blocks.next();
			if (blockName.equals(blockInfo.getName())) {
				return blockInfo;
			}
		}
		
		return null;
	}
}
