package im.tabis.frame.service;

import im.tabis.core.service.CommonService;
import im.tabis.frame.entity.FrameDictItem;

import java.math.BigInteger;
import java.util.List;

/**
 * 
 * @author gg
 * @desc  数据字典子表对外接口
 */
public interface FrameDictItemService extends CommonService<FrameDictItem, BigInteger> {
	public static final String BEAN_NAME = "frameDictItemService";

	public List<FrameDictItem> findByDictCode(String dictCode);
	
	public List<FrameDictItem> findByDictId(BigInteger dictId);
}
