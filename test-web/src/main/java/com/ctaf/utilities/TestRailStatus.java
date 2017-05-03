package com.ctaf.utilities;

import java.util.Map;
import java.util.HashMap;
import org.json.simple.JSONObject;
import com.ctaf.utilities.*;
import com.gurock.testrail.*;
public class TestRailStatus {

	public static void GetStatusTestRail(int Status,int TestCaseID, int TestRunID) throws Exception {
		APIClient client = new APIClient("https://hooqtv.testrail.net");
		client.setUser("dilip.sanchora@cigniti.com");
		client.setPassword("Unique@123");
		Map data = new HashMap();
		data.put("status_id", Status);
		data.put("custom_phone_number", "+91");
		data.put("custom_device_type", 7);
		data.put("custom_app_version", "Web");
		data.put("custom_country", 2);
		data.put("custom_browser", 1);
		//data.put("comment", "This test worked fine!");
		JSONObject r = (JSONObject) client.sendPost("add_result_for_case/"+TestRunID+"/" + TestCaseID, data);
}
}
