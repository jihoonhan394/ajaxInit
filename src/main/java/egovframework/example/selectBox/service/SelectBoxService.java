package egovframework.example.selectBox.service;

import java.util.List;

import egovframework.rte.psl.dataaccess.util.EgovMap;

public interface SelectBoxService {

	List<EgovMap> selectParentBoxServiceList() throws Exception;

	List<EgovMap> selectChildBoxServiceList(String param) throws Exception;

}
