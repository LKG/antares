package im.tabis.demo.web;


import im.tabis.core.CommonConst;
import im.tabis.core.plugins.persistence.DynamicPageRequest;
import im.tabis.core.plugins.persistence.DynamicSpecifications;
import im.tabis.core.web.AbstractController;
import im.tabis.prod.entity.Demo;
import im.tabis.prod.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/admin")
public class AdminDemoController extends AbstractController {
	protected static final String apiVer = "/demo";
	protected static final String VIEW="admin/index";
	@Autowired
	DemoService  demoService;
	/**
	 *
	 * @Desc：查询所有
	 * @param request
	 * @param response
	 * @param jsoncallback
	 * @param page
	 * @param size
	 * @param sort
	 * @param order
	 * @param token
	 * @param model
	 * @return
	 */
	@RequestMapping(apiVer+"s")
	public ModelAndView list(HttpServletRequest request, HttpServletResponse response,
							 @RequestParam(value = CommonConst.RequestResult.JSON_CALLBACK, required = false) String jsoncallback,
							 @RequestParam(value = "page", required = false, defaultValue = CommonConst.Page.DEFAULT_PAGE+"") Integer page,
							 @RequestParam(value = "size", required = false, defaultValue = CommonConst.Page.DEFAULT_SIZE+"") Integer size,
							 @RequestParam(value = "sort", required = false ,defaultValue = "createTime") String sort,
							 @RequestParam(value = "order", required = false,defaultValue = CommonConst.Page.DEFAULT_ORDER) String order,
							 @RequestParam(value = CommonConst.RequestResult.ACCESS_TOKEN, required = false) String token,
							 ModelMap model) {
		Specification<Demo> spec= DynamicSpecifications.bySearchFilter(request, Demo.class);
		PageRequest pageRequest= DynamicPageRequest.buildPageRequest(page,size,sort,order, Demo.class);
		Page<Demo> pag = this.demoService.findAll(spec, pageRequest);
		model.put("qqq","111");
		super.success(model,pag);
		return new ModelAndView();
	}
}
