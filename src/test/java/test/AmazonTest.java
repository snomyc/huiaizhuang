package test;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.snomyc.util.HttpClientHelper;
public class AmazonTest {

	public static void main(String[] args) {
//		String keyWord = "led a";
//		//将关键词转义，或者用+号(led+a)，或者用空格(led%20a) 否则会报错
//		keyWord= URLEncoder.encode(keyWord);
//		String searchUrl = "https://completion.amazon.com/search/complete?method=completion&mkt=1&search-alias=aps&q="+keyWord;
//		System.out.println(searchUrl);
//		String result = HttpClientHelper.httpGet(searchUrl);
//		System.out.println(result);
//		//转成json格式,并获取数组第二条记录
//		JSONArray arrayList = JSONArray.parseArray(result).getJSONArray(1);
//		System.out.println(arrayList);
//		Iterator<Object> it = arrayList.iterator();
//		while (it.hasNext()) {
//			String value = (String) it.next();
//			System.out.println(value);
//		}
		
		//通过词根 爬取亚马逊搜索接口 获取关键词集合 并入库
		String keyWordRoot = "projector screen";
		String searchUrl = "https://completion.amazon.com/search/complete?method=completion&mkt=1&search-alias=aps&q=";
		//转义关键词 拼接查询条件
		String url = searchUrl + URLEncoder.encode(keyWordRoot);
		String result = HttpClientHelper.httpGet(url);
		System.out.println(result);
		//通过词根+空格+(a-z)获取关键词集合并入库，去重
		int flag = 1;
		for (char i = 'a'; i <= 'z'; i++) {
			if(flag == 1) {
				System.out.println(i);
				String keyWordRootAdd = keyWordRoot + " "+i;
				System.out.println(keyWordRootAdd);
				url =  searchUrl + URLEncoder.encode(keyWordRootAdd);
				result = HttpClientHelper.httpGet(url);
				System.out.println(result);
			}else {
				break;
			}
			flag++;
			
		}
		
		//打印a-z
		for(char i='a';i<='z';i++) {  
            System.out.print(i+" ");  
        }
		for(char i='a';i<='z';i++) {  
            System.out.print(i);  
        }  
	}
}
