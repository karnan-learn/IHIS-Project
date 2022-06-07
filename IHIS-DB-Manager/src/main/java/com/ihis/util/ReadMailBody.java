package com.ihis.util;

import java.io.BufferedReader;
import java.io.FileReader;

import org.springframework.stereotype.Component;

import com.ihis.entity.CaseWorkersAcctEntity;

@Component
public class ReadMailBody {

	public String readMailBodyContent(String filename, CaseWorkersAcctEntity entity) {
		String mailBody=null;
		try {
			StringBuffer sb = new StringBuffer();
			FileReader fr =new FileReader(filename);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine(); // reading first line data
			while(line != null) {
				sb.append(line);
				line =  br.readLine();
			}
			mailBody =  sb.toString();
			mailBody = mailBody.replace("{FULLNAME}", entity.getFullName());
			mailBody = mailBody.replace("{TEMP-PWD}", entity.getPwd());
			mailBody = mailBody.replace("{EMAIL}", entity.getEmail());
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mailBody;
	}
}

