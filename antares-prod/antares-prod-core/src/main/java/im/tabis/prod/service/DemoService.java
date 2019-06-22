package im.tabis.prod.service;

import im.tabis.core.service.CommonService;
import im.tabis.prod.entity.Demo;

import java.math.BigInteger;


public interface DemoService extends CommonService<Demo, BigInteger> {
	public static final String BEAN_NAME = "demoService";

}
