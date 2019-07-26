package global.testingsystem.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import global.testingsystem.entity.News;
import global.testingsystem.entity.SlideBanner;
import global.testingsystem.repository.NewsRepository;
import global.testingsystem.repository.UsersRepository;
import global.testingsystem.service.NewsService;
import global.testingsystem.util.CustomComparator;

@Service
public class NewsServiceImpl implements NewsService {
	  private static Logger log = Logger.getLogger(NewsServiceImpl.class);
	@Autowired
	private NewsRepository newsRepo;

	@Autowired
	private UsersRepository usersRepository;



	@Override
	public News deleteNews(int id) {
		News deletedNews = newsRepo.findById(id).get();
		System.out.println("\n\n");
		System.out.println(deletedNews.convertToJson().toString(4));
		newsRepo.delete(deletedNews);
		return deletedNews;
	}

	/* (non-Javadoc)
	 * @see com.cmcglobal.rrcexample.service.NewsService#getAllNews()
	 */
	@Override
	public List<News> getAllNewsByOrderByNewsIdDesc() {
		return newsRepo.findAllByOrderByNewsIdDesc();
//		return newsRepo.findAllByOrderByconfirmDateDesc();
//		public List<StudentEntity> findAllByOrderByIdAsc();
	}

	/* (non-Javadoc)
	 * @see com.cmcglobal.rrcexample.service.NewsService#searchNews(java.lang.String)
	 * 
	 */
	@Override
	public List<News> searchAllNewsCMS(String keyword) {
		List<News> matchedTitle = newsRepo.searchAllNewsCMS(keyword);
		HashMap<Integer, News> idNews = new HashMap<>();
		for(News n : matchedTitle) {
			idNews.put(n.getNewsId(), n);
		}
		List<News> matched = new ArrayList<>();
		for(Integer i : idNews.keySet()) {
			matched.add(idNews.get(i));
		}
		return matched;
	}
	@Override
	public List<News> searchNews(String keyword) {
		// Tìm trong trường Title 
		List<News> matchedTitle = newsRepo.searchNewsByTitle(keyword);
	
		// Tìm trong trường Content
		List<News> matchedContent = newsRepo.searchNewsByContent(keyword);
		//System.out.println(matchedContent.size());
		// Tìm trong trường Descrip
		List<News> matchedDescription = newsRepo.searchNewsByDescription(keyword);
		//System.out.println(matchedDescription.size());
		// Put matched to HashMap idNews
		HashMap<Integer, News> idNews = new HashMap<>();
		for(News n : matchedTitle) {
			idNews.put(n.getNewsId(), n);
		}
		for(News n : matchedContent) {
			idNews.put(n.getNewsId(), n);
		}
		for(News n : matchedDescription) {
			idNews.put(n.getNewsId(), n);
		}
		List<News> matched = new ArrayList<>();
		for(Integer i : idNews.keySet()) {
			matched.add(idNews.get(i));
		}
		return matched;
	}
	
	/* (non-Javadoc)
	 * @see com.cmcglobal.rrcexample.service.NewsService#getNews(int)
	 */
	@Override
	public News findNewsById(int id) {
		return newsRepo.findByNewsId(id);
	}

	/* (non-Javadoc)
	 * @see com.cmcglobal.rrcexample.service.NewsService#sortByProperty(int)
	 */
	@Override
	public List<News> sortByProperty(int index,int typeSort,String keySearch) {

		List<News> matchedALL = newsRepo.searchAllNewsCMS(keySearch);
		HashMap<Integer, News> idNews = new HashMap<>();

		for(News n : matchedALL) {
			idNews.put(n.getNewsId(), n);
		}
		List<News> matched = new ArrayList<>();
		for(Integer i : idNews.keySet()) {
			matched.add(idNews.get(i));
		}
		CustomComparator custom=new CustomComparator(index,typeSort);
		Collections.sort(matched, custom);
		return matched;
	}

	@Override
	public boolean pinNews(int newsId) {
		News news = newsRepo.findByNewsId(newsId);
		if(news.isPinned()) {
			news.setPinned(false);
			newsRepo.save(news);
			return true;
		}
		List<News> pinned;
		pinned = newsRepo.findByPinnedOrderByNewsIdDesc(true);
		if (pinned.size() >= 20) {
			return false;
		}
		news.setPinned(true);
		newsRepo.save(news);
		return true;
	}

	@Override
	public List<Object> findAllPinnedNews() {
		 return newsRepo.findAllPinnedNews();
	}

	@Override
	public List<Object> findPageNewsNews() {
		 return newsRepo.findPageNewsNews();
	}

	// MR DUC thêm ngày 13.01.2018 ** START **
	@Override
	public void updateNewActiveStatus(int status, int news_id) {
		newsRepo.updateNewActiveStatus(status, news_id);
	}
	// MR DUC thêm ngày 13.01.2018 ** END **

	@Override
	public boolean insertNew(News s) {
		 boolean isSuccess = false;
		 News insertNew= newsRepo.save(s);
		 if (insertNew != null) {
	            isSuccess = true;
	        } else {
	            isSuccess = false;
	            log.error("insert fail ");
	        }
	        return isSuccess;
	}

	@Override
	public boolean updateNews(News s) {
		
		 boolean isSuccess = false;
		 News insertNew= newsRepo.save(s);
	        if (insertNew != null) {
	            isSuccess = true;
	        } else {
	            isSuccess = false;
	            log.error("insert fall ");
	        }
	        return isSuccess;
	}

	@Override
	public News getNewById(int id) {
		 return newsRepo.getOne(id);
	}

	

}
