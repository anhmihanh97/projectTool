/**
 * 
 */
package global.testingsystem.controller;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import global.testingsystem.entity.News;
import global.testingsystem.entity.SlideBanner;
import global.testingsystem.entity.Users;
import global.testingsystem.service.NewsService;
import global.testingsystem.util.ConstantPage;

/**
 * @author User
 *
 */

@CrossOrigin(origins = "*")
@RestController
public class NewsController  {	
	

	public NewsController() {
		super();
		// TODO Auto-generated constructor stub
	}

	final static Logger logger = LogManager.getLogger(NewsController.class);
	@Autowired
	private HttpServletResponse response;
	@Autowired
	private NewsService newsServiceImpl;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	private NewsService newService;
	@Autowired
    private ServletContext servletContext;

	/** 
	 * @author tgnghia 
	 * @created date Nov 15, 2018 
	 * @modified date Nov 15, 2018 
	 * @version 1.0 
	 * @description 
	 * @return
	 * @throws JSONException 
	 * @throws UnsupportedEncodingException 
	 */
	// MR DUC thêm ngày 13.01.2018 ** START **
	@GetMapping(value = ConstantPage.REST_API_UPDATE_NEWS_ACTIVE_STATUS, produces = {
            MediaType.APPLICATION_PROBLEM_JSON_VALUE })
	public void update(@PathVariable int status,@PathVariable int news_id) {
    newsServiceImpl.updateNewActiveStatus(status, news_id);
    }
	// MR DUC thêm ngày 13.01.2018 ** END **
	
	@GetMapping(value = ConstantPage.REST_API_GET_ALL_PAGENEWS_NEWS, produces = {
            MediaType.APPLICATION_PROBLEM_JSON_VALUE })
public List<Object> findPageNewsNews() {
    return newsServiceImpl.findPageNewsNews();
}
	@GetMapping(value = ConstantPage.REST_API_GET_ALL_PINNED_NEWS, produces = {
            MediaType.APPLICATION_PROBLEM_JSON_VALUE })
public List<Object> findAllPinnedNews() {
    return newsServiceImpl.findAllPinnedNews();
}
	
	
	@GetMapping(value = ConstantPage.REST_API_GET_ALL_NEW)
	public byte[] getAllNews() throws UnsupportedEncodingException, JSONException {
		List<News> allNews = newsServiceImpl.getAllNewsByOrderByNewsIdDesc();
		ArrayList<JSONObject> newsList = new ArrayList<>();
//		for (News n : allNews) {
//			if(n.isPinned()) {
//				newsList.add(n.convertToJson());
//			}
//		}
		for(News n : allNews) {
//			if(!n.isPinned()) {
				newsList.add(n.convertToJson());
//			}
		}
		JSONArray newsArray = new JSONArray(newsList);
		System.out.println(newsArray.toString(4));
		return newsArray.toString(4).getBytes("UTF-8");
	}

	/** 
	 * @author tgnghia 
	 * @created date Nov 15, 2018 
	 * @modified date Nov 15, 2018 
	 * @version 1.0 
	 * @description 
	 * @param body
	 * @return
	 */
//	@PostMapping(value = ConstantPage.REST_API_INSERT_NEW)
//	public String insert(@RequestBody String body) {
//		JSONObject obj = new JSONObject(body);
//		String title = obj.getString("title");
//		String description = obj.getString("description");
//		String content = obj.getString("content");
//		String imgUrl = obj.getString("linkimage");
//		int creator = obj.getInt("creator");
//		News addedNews = newsServiceImpl.addNews(title, description, content, creator,imgUrl);
//		logger.info("news added : " + addedNews.convertToJson().toString(4));
//		return addedNews.convertToJson().toString();
//	}
	@PostMapping(value = ConstantPage.REST_API_INSERT_NEW)
	public ResponseEntity<Object>insert(@RequestParam("image") MultipartFile image,
			@RequestParam("news") String news){
		  JSONObject jsonObject = new JSONObject(news);
		  String title = jsonObject.getString("title");
		  String description = jsonObject.getString("description");
		  String content = jsonObject.getString("content");
		  News n = new News();
		  n.setImgUrl(image.getOriginalFilename());
		  n.setTitle(title);
		  n.setDescription(description);
		  n.setContent(content);
		  n.setUpStatus("approve");
		  Date date = new Date();
		  java.sql.Date sqlDate = new java.sql.Date(date.getTime());
		  n.setCreateDate(sqlDate);
		  boolean isSuccess = newService.insertNew(n);
		  String pathToSave = servletContext.getRealPath(ConstantPage.PATH_SAVE_NEW);
	        File  imageFile = new File(pathToSave + "/" + image.getOriginalFilename());
	            try {
	                //transfer the received file to the given destination file
	                image.transferTo(imageFile);
	            } catch (IllegalStateException e) {
	            	logger.error("Co loi khi upload file");
	            } catch (IOException e) {
	            	logger.error("Sai duong dan file");
	            }
	        return new ResponseEntity<>(isSuccess, HttpStatus.OK);
		
	}
	/** 
	 * @author tgnghia 
	 * @created date Nov 15, 2018 
	 * @modified date Nov 15, 2018 
	 * @version 1.0 
	 * @description 
	 * @param id
	 * @param body
	 * @return
	 */
	
	@PostMapping(value = ConstantPage.REST_API_UPDATE_NEW)
	public  ResponseEntity<Object> update(@RequestParam(required = false) MultipartFile image,
			@RequestParam("news") String news,@RequestParam("idnews") int newId){
		JSONObject jsonObject = new JSONObject(news);
		String title = jsonObject.getString("title");
		String description = jsonObject.getString("description");
		String content = jsonObject.getString("content");
		News n = newService.findNewsById(newId);
		String pathToSave ="";
    	if (image != null) {
    		pathToSave= servletContext.getRealPath(ConstantPage.PATH_SAVE_SLIDEBANNER_UPLOAD);
        File  imageFile = new File(pathToSave + "/" + image.getOriginalFilename());
            try {
                image.transferTo(imageFile);
            } catch (IllegalStateException e) {
            	logger.error("Co loi khi upload file");
            } catch (IOException e) {
            	logger.error("Sai duong dan file");
            }
            n.setImgUrl(image.getOriginalFilename());
    	}
    	n.setTitle(title);
    	n.setDescription(description);
    	n.setContent(content);
    
        boolean isSuccess = newService.updateNews(n);
    	
		return new ResponseEntity<>(isSuccess, HttpStatus.OK);
    	
		
	}
	
	
	
	
	/** 
	 * @author tgnghia 
	 * @created date Nov 15, 2018 
	 * @modified date Nov 15, 2018 
	 * @version 1.0 
	 * @description 
	 * @param id
	 * @return
	 */
	@PostMapping(value=ConstantPage.REST_API_DELETE_NEW_BY_ID)
	public String delete(@PathVariable("newId") int id) {
		System.out.println(id);
		News deletedNews = newsServiceImpl.deleteNews(id);
		System.out.println(deletedNews.convertToJson().toString(4));
		logger.info("deleted news : " + deletedNews.convertToJson().toString(4));
		return deletedNews.convertToJson().toString();
	}
	
	/** 
	 * @author tgnghia 
	 * @created date Nov 15, 2018 
	 * @modified date Nov 15, 2018 
	 * @version 1.0 
	 * @description 
	 * @param keyword
	 * @return
	 * @throws JSONException 
	 * @throws UnsupportedEncodingException 
	 */
	@GetMapping(value=ConstantPage.REST_API_SEARCH_NEW_CMS)
	public byte[] searchAllNewsCMS(@RequestParam String keyword) throws UnsupportedEncodingException, JSONException {
		List<News> result = newsServiceImpl.searchAllNewsCMS(keyword);
		ArrayList<JSONObject> allNews = new ArrayList<>();
		for(News n : result) {
			allNews.add(n.convertToJson());
		}
		JSONArray newsArray = new JSONArray(allNews);
		logger.info("search result : " + newsArray.toString(4));
		return newsArray.toString(4).getBytes("UTF-8");
	}
	
	@GetMapping(value=ConstantPage.REST_API_SEARCH_NEW)
	public byte[] searchNews(@RequestParam String keyword) throws UnsupportedEncodingException, JSONException {
		List<News> result = newsServiceImpl.searchNews(keyword);
		ArrayList<JSONObject> allNews = new ArrayList<>();
		for(News n : result) {
			allNews.add(n.convertToJson());
		}
		JSONArray newsArray = new JSONArray(allNews);
		logger.info("search result : " + newsArray.toString(4));
		return newsArray.toString(4).getBytes("UTF-8");
	}
	/** 
	 * @author tgnghia 
	 * @created date Nov 15, 2018 
	 * @modified date Nov 15, 2018 
	 * @version 1.0 
	 * @description 
	 * @param indexProperty
	 * @param typeSort
	 * @return
	 */
	@GetMapping(value=ConstantPage.REST_API_SORT_NEW)
	public byte[] sortNews(@RequestParam int indexProperty,@RequestParam int typeSort,@RequestParam String keySearch) throws Exception{
		List<News> listNews=newsServiceImpl.sortByProperty(indexProperty,typeSort,keySearch);
		ArrayList<JSONObject> objs = new ArrayList<JSONObject>();
		for(News n : listNews) {
			objs.add(n.convertToJson());
		}
		JSONArray newsArr = new JSONArray(objs);
		return newsArr.toString(4).getBytes("UTF-8");
		
	}
	
	/** 
	 * @author tgnghia 
	 * @created date Nov 15, 2018 
	 * @modified date Nov 15, 2018 
	 * @version 1.0 
	 * @description 
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@GetMapping (value=ConstantPage.REST_API_FIND_NEW_BY_ID)
	public byte[] getOneNews(@PathVariable("newId") int id) throws Exception {
		News news = newsServiceImpl.findNewsById(id);
		System.out.println(news.convertToJson().toString(4));
		return news.convertToJson().toString(4).getBytes("UTF-8");
	}
	
	/** 
	 * @author tgnghia 
	 * @created date Nov 15, 2018 
	 * @modified date Nov 15, 2018 
	 * @version 1.0 
	 * @description 
	 * @param id
	 * @return
	 */
	@GetMapping (value= ConstantPage.REST_API_PIN_NEW)
	public String pinNews(@PathVariable("newsId") int id) {
				if (newsServiceImpl.pinNews(id)) {
			return "{\"status\":\"success\"}";
		}
		return "{\"status\":\"failed\"}";
	}
	
	@GetMapping(value=ConstantPage.REST_API_SEARCH_PRODUCT_NEW)
	public List<News> searchNewss(@PathVariable(value = "keyword") String keyword) {
		List<News> result = newsServiceImpl.searchNews(keyword);
		return result;
	}

}
