package egovframework.example.selectBox.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import egovframework.example.selectBox.service.SelectBoxService;
import egovframework.rte.psl.dataaccess.util.EgovMap;

@Service
public class SelectBoxServiceImpl implements SelectBoxService {

	@Resource
	private SelectBoxMapper selectBoxMapper;
	
	@Override
	public List<EgovMap> selectParentBoxServiceList() throws Exception {
		return selectBoxMapper.selectParentBoxServiceList();
	}

	@Override
	public List<EgovMap> selectChildBoxServiceList(String param) throws Exception {
		return selectBoxMapper.selectChildBoxServiceList(param);
	}

}
