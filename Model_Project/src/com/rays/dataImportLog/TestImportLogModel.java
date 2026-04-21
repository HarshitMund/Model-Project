package com.rays.dataImportLog;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class TestImportLogModel {

	public static void main(String[] args) throws Exception {
		
//		testAdd();
//		testUpdate();
//		testDelete();
		testSearch();

	}
	
	public static void testAdd() throws Exception {
		
		ImportLogBean bean = new ImportLogBean();
		ImportLogModel model = new ImportLogModel();
		
		bean.setImportLogCode("DIL016");
		bean.setFileName("exam_data.csv");
		bean.setImportedBy("Operator");
		bean.setStatus("Success");
		
		model.add(bean);
	}

	public static void testUpdate() throws Exception {
		
		ImportLogBean bean = new ImportLogBean();
		ImportLogModel model = new ImportLogModel();
		
		bean.setImportLogId(16);
		bean.setImportLogCode("DIL016");
		bean.setFileName("class_data.csv");
		bean.setImportedBy("System");
		bean.setStatus("Pending");
		
		model.update(bean);
	}
	
	public static void testDelete() throws Exception {
		
		ImportLogBean bean = new ImportLogBean();
		ImportLogModel model = new ImportLogModel();
		
		bean.setImportLogId(16);
	
		model.delete(bean);
	}
	
	public static void testSearch() throws Exception {
		
		ImportLogBean bean = new ImportLogBean();
		ImportLogModel model = new ImportLogModel();
		List<ImportLogBean> list = new ArrayList<ImportLogBean>();
		
		list = model.search(bean, 1, 5);
		
		Iterator<ImportLogBean> it = list.iterator();
		
		while(it.hasNext()) {
			bean = it.next();
			
			System.out.print(bean.getImportLogId());
			System.out.print("\t" + bean.getImportLogCode());
			System.out.print("\t" + bean.getFileName());
			System.out.print("\t" + bean.getImportedBy());
			System.out.println("\t" + bean.getStatus());
		}
	}
}
