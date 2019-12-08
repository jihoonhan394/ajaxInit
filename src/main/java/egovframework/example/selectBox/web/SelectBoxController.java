package egovframework.example.selectBox.web;

import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.example.cmmn.JsonUtil;
import egovframework.example.selectBox.service.SelectBoxService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Controller
public class SelectBoxController {

	@Resource
	private SelectBoxService selectBoxService;
	
	@ModelAttribute("parentList")
	public List<EgovMap> parentList(ModelMap model) throws Exception {
		return selectBoxService.selectParentBoxServiceList();
	}
	
	@RequestMapping("/selectBox.do")
	public String selectBoxMain(ModelMap model) throws Exception {
		
		/*List<EgovMap> parentList = selectBoxService.selectParentBoxServiceList();
		
		model.addAttribute("parentList", parentList);*/
		
		return "selectBox/selectBox.tiles";
	}
	
	@RequestMapping("/childSelectBox.do")
	public void childSelectBox(@RequestBody String reqParam,
			HttpServletRequest req,
			HttpServletResponse res,
			ModelMap model) throws Exception {
		
		String paramKey = "",
				  param = "";
		
		/*Enumeration<String> name = req.getParameterNames();
		
		while (name.hasMoreElements()) {
			paramKey = name.nextElement();
		}*/
		/*paramKey = URLDecoder.decode(reqParam, "utf-8");
		
		paramKey = paramKey.substring(0, paramKey.length() -1);*/
		
		Map<String, Object> resMap = JsonUtil.JsonToMap(paramKey);
		
		param = (String) resMap.get("param");
		
		List<EgovMap> childList = selectBoxService.selectChildBoxServiceList(param);
		
		HashMap<String, Object> resultMap = new HashMap<String, Object>();
		
		resultMap.put("result", "SUCCESS");
		resultMap.put("childList", childList);
		
		res.setCharacterEncoding("utf-8");
		
		PrintWriter out = res.getWriter();
		
		String resultMapToJson = JsonUtil.HashMapToJson(resultMap);
		
		out.write(resultMapToJson);
	}
}
