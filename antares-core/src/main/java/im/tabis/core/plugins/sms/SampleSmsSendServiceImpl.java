package im.tabis.core.plugins.sms;

import com.github.qcloudsms.SmsSingleSender;
import com.github.qcloudsms.SmsSingleSenderResult;
import im.tabis.core.web.ResponseError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Profile;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.Map;

//import org.springframework.beans.factory.annotation.Autowired;


/**
 * 
 * @Desc：短信发送实现类 demo
 * @作者 LKG
 */
@Component(value = SmsSendService.BEAN_NAME)
public class SampleSmsSendServiceImpl implements SmsSendService {
	protected static final Logger logger = LoggerFactory.getLogger(SampleSmsSendServiceImpl.class);

	public static void main(String[] args) throws Exception{
		int appId = 1400197686;
		String[] phoneNumbers={"18668169331"};
		String appKey = "f9f4cc6fd9ff57f332760650c5d1c755"; //sdkappid 对应的 appkey，需要业务方高度保密
		String[] params = {"5678"};//数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
		int templateId = 5678;
		String smsSign = "腾讯云";//数组具体的元素个数和模板中变量个数必须一致，例如事例中templateId:5678对应一个变量，参数数组中元素个数也必须是一个
		SmsSingleSender ssender = new SmsSingleSender(appId, appKey);
		SmsSingleSenderResult result = ssender.sendWithParam("86", phoneNumbers[0],
				templateId, params, smsSign, "", "");
	}
	
	@Profile("prod")
	@Override
	@Async
	public ResponseError sendSms(String mobilePhone, String content) {
		logger.info("模拟发送短信啦................................");
		return null;
	}

	@Override
	@Async
	public ResponseError sendSms(Map<String, Object> model, String templatePath,
                                 String[] mobileTo) {
		logger.info("模拟发送短信啦................................");
		return null;
	}


}
