
package im.tabis.frame.tags;

import com.google.common.collect.Lists;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModelException;
import im.tabis.common.context.ContextManager;
import im.tabis.core.tags.BaseDirective;
import im.tabis.core.utils.StringUtilsEx;
import im.tabis.frame.entity.FrameDictItem;
import im.tabis.frame.service.FrameDictItemService;
import im.tabis.frame.vo.FrameDictItemVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;

@Component
public class FrameDictItemTag extends BaseDirective {
	protected static final Logger logger = LoggerFactory.getLogger(FrameDictItemTag.class);

	@Override
	public void render(Environment env, Map<?, ?> params, TemplateDirectiveBody body)
			throws IOException, TemplateException {
		String dictCode=super.getParam(params, "dictCode");
		if(StringUtilsEx.isNotBlank(dictCode)){
			List<FrameDictItemVO> itemVOs=Lists.newArrayList();
			FrameDictItemService dictItemService = (FrameDictItemService) ContextManager.getBean(FrameDictItemService.BEAN_NAME);
			List<FrameDictItem> items= dictItemService.findByDictCode(dictCode);
			for(FrameDictItem po :items){
				itemVOs.add(new FrameDictItemVO(po));
			}
			setVariable("items",itemVOs,env);
		}
		super.renderBody(env,body);
	}
	@Override
	protected void verifyParameters(Map<?, ?> params) throws TemplateModelException {
		 
       
	}

}
