package com.fintech.helper;

import java.io.IOException;

import org.testng.annotations.DataProvider;

import com.fintech.config.Filepaths;
import com.fintech.utils.ConfigManager;
import com.fintech.utils.ExcelDataReader;

public class DataProviderHelper {
	
	
	ExcelDataReader excel = new ExcelDataReader();
	
	@DataProvider(name = "userIDProvider")
	public Object[][] userIdDigiLocker() {
		
		Object[][] data = null;
		
		try {
			data = excel.getData(Filepaths.REQUEST_EXCEL, ConfigManager.getInstance().getProperty("digilockerSheetName"));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return data;
	}

}
