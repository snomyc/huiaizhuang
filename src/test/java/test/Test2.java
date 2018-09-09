package test;


import java.util.HashMap;
import java.util.Map;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;
import com.alibaba.fastjson.JSON;
public class Test2 {

	public static void main(String[] args) throws Exception {
//		List<Map<String,Object>> userList = new ArrayList<Map<String,Object>>();
//		Map<String,Object> map = new HashMap<String,Object>();
//		map.put("id", 1);
//		map.put("name", 1);
//		
//		userList.add(map);
//		Map<String,Object> map1 = new HashMap<String,Object>();
//		map1.put("id", 2);
//		map1.put("name", 2);
//		userList.add(map1);
//        Map<Object, Object> userInfoMap = userList.stream().collect(Collectors.toMap(n -> n.get("id"),n -> n));
//        System.out.println(userInfoMap);
		
		//String url = "https://t.sharingschool.com/wxapi/user/getLoginType";
		//String url = "https://t.questyes.com/wxapi/user/getLoginType";
//	    RestTemplate rest = new RestTemplate();
//	    MultiValueMap<String, Object> param = new LinkedMultiValueMap<>();
//	    param.add("validateTime", "{\"validateTime\":\"2018-05-30\"}");
//	    String string = rest.postForObject(url, param, String.class);
//	    System.out.println(string);
	    
	    
      //String url = "http://localhost:8080/sharingschool/wxapi/user/appUserlogin";
	  //String url = "https://t.questyes.com/wxapi/user/getLoginType";
	  String url = "https://t.sharingschool.com/wxapi/user/getLoginType";
      Map<String, Object> map = new HashMap<String, Object>();
	  map.put("validateTime", "2018-05-30");
      HttpHeaders headers = new HttpHeaders();
      headers.setContentType(MediaType.valueOf("application/json;UTF-8"));
      HttpEntity<String> strEntity = new HttpEntity<String>(JSON.toJSONString(map),headers);
      RestTemplate restTemplate = new RestTemplate();
      
      String result = restTemplate.postForObject(url,strEntity,String.class);
      System.out.println(result);
	    
//        
//        StringBuilder selSql = new StringBuilder();
//		selSql.append(" select sf.id,sf.biz_id,sf.create_time as answer_time,stf.create_time as feedbact_time,stf.content from scon_feedback sf ");
//		selSql.append(" left join scon_text_feedback_detail stf on sf.id = stf.feedback_id");
//		selSql.append(" where and stf.type = 4 and sf.biz_id in('");
//		
//		selSql = selSql.deleteCharAt(selSql.length()-1);
//		System.out.println(selSql.toString());
		
//		long start = System.currentTimeMillis();
//		Thread.sleep(61000);
//		long end = System.currentTimeMillis();
//		System.out.println(((end-start)/1000));
//		System.out.println(((end-start)%60000 == 0 ? (end-start)/60000 : ((end-start)/60000)+1));
		
	}
}
