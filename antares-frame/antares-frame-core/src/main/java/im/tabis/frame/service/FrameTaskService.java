package im.tabis.frame.service;

import im.tabis.core.service.CommonService;
import im.tabis.frame.entity.FrameTask;

import java.math.BigInteger;
import java.util.List;

/**
 * 
 * @author gg
 * @desc 动态任务操作接口
 */
public interface   FrameTaskService extends CommonService<FrameTask, BigInteger> {
	
	public static final String BEAN_NAME = "frameTaskService";
	public List<FrameTask> findAll();
	public void delete(BigInteger... ids);
}
