package im.tabis.prod.service.impl;

import im.tabis.core.service.impl.CommonServiceImpl;
import im.tabis.prod.entity.Demo;
import im.tabis.prod.repository.DemoRepository;
import im.tabis.prod.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
@Service(value = DemoService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
@CacheConfig(cacheNames = DemoServiceImpl.CACHE_NAMES)
public class DemoServiceImpl extends CommonServiceImpl<Demo, BigInteger> implements DemoService {

	public static final String CACHE_NAMES = "demos-cache";
	@Autowired
	private DemoRepository demoRepository;


}
