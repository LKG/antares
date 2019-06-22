package im.tabis.frame.service.impl;

import im.tabis.core.plugins.persistence.DynamicSpecifications;
import im.tabis.core.plugins.persistence.SearchFilter;
import im.tabis.core.plugins.persistence.SearchFilter.Operator;
import im.tabis.core.service.impl.CommonServiceImpl;
import im.tabis.frame.entity.FrameTpl;
import im.tabis.frame.repository.FrameTplRepository;
import im.tabis.frame.service.FrameTplService;
import im.tabis.frame.service.TemplateService;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerProperties;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.ServletContextAware;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;
import java.math.BigInteger;
import java.util.Collection;
import java.util.HashSet;

/**
 *
 * @author gg
 * @desc : FrameTask Service 实现类
 */
@Service(value = FrameTplService.BEAN_NAME)
@Transactional(propagation = Propagation.SUPPORTS,rollbackFor = Exception.class)
public class FrameTplServiceImpl extends CommonServiceImpl<FrameTpl, BigInteger> implements FrameTplService, TemplateService<FrameTpl, BigInteger>,ServletContextAware {
	protected static final Logger logger = LoggerFactory.getLogger(FrameTplServiceImpl.class);
	/** servletContext */
	private ServletContext servletContext;
	@Autowired
	private FrameTplRepository frameTplRepository;
	
	@Autowired
	private FreeMarkerProperties properties;
	
	@Override
	public boolean exists(String tplCode) {
		final Collection<SearchFilter> filters = new HashSet<SearchFilter>();
		filters.add(new SearchFilter("tplCode", Operator.EQ, tplCode));
		Specification<FrameTpl> spec = DynamicSpecifications.bySearchFilter(filters, FrameTpl.class);
		long count = this.frameTplRepository.count(spec);
		if(count>0){
			return true;
		}
		return false;
	}
	@Override
	public String read(BigInteger id) {
		FrameTpl frameTpl = this.findById(id);
		return read(frameTpl);
	}
	@Override
	public String read(FrameTpl template) {
		String templatePath =getRealPath(template.getTplPath());
		File templateFile = new File(templatePath);
		String templateContent = null;
		try {
			templateContent = FileUtils.readFileToString(templateFile, "UTF-8");
		} catch (IOException e) {
			logger.warn("[TemplateServiceImpl]-" + e.getMessage());
		}
		return templateContent;
	}
	@Override
	public void write(BigInteger id, String content) {
		FrameTpl template = this.findById(id);
		write(template, content);
	}
	@Override
	public void write(FrameTpl template, String content) {
		String templatePath =getRealPath(template.getTplPath());
		File templateFile = new File(templatePath);
		try {
			FileUtils.writeStringToFile(templateFile, content, "UTF-8");
		} catch (IOException e) {
			logger.warn("[TemplateServiceImpl]-" + e.getMessage());
		}
	}
	protected String getRealPath(String tplPath){
		return this.servletContext.getRealPath(properties.getTemplateLoaderPath()[0]+ tplPath);
	}  
	
	@Override
	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

}
