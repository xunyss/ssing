package io.xunyss.ssing.boot.mapper;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TradeMapper {
	
	@Insert("insert into jongmok values(#{shcode}, #{hname}, sysdate())")
	public int insertJongMok(Jong jong);
}
