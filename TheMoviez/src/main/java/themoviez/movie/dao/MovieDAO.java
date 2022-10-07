package themoviez.movie.dao;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import themoviez.movie.entity.MovieDTO;


// 네이버 영화 크롤링

public class MovieDAO {
	
	DataSource dataFactory;
	
	public MovieDAO() {
		try {
			Context ctx = new InitialContext();
			dataFactory = (DataSource) ctx.lookup("java:comp/env/jdbc/Mysql8");
		} catch (NamingException ex) {
			ex.printStackTrace();
		}
	}
	
	
	// 베이스 URL
	final String baseUrl = "https://openapi.naver.com/v1/search/movie?query=";
	
	public String search(String clientId, String secret, String _url) {
		HttpURLConnection con = null;
		String result = "";
		
        try {
        	_url = URLEncoder.encode(_url, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("검색어 인코딩 실패",e);
        }
        
        try {
            URL url = new URL(baseUrl + _url + "&display=100");
            con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", secret);

            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) result = readBody(con.getInputStream());
            else result = readBody(con.getErrorStream());

        } catch (Exception e) {
            System.out.println("연결 오류 : " + e);
        } finally {
            con.disconnect();
        }

        return result;
	}
	
	
	// 결과를 읽음
	public String readBody(InputStream body){
		InputStreamReader streamReader = new InputStreamReader(body);

		try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();

            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }

            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
	
	
	// 결과 파싱
	public Map<String, Object> getResult(String response, String[] fields) {
		Map<String, Object> rtnObj = new HashMap<> ();
		
		try {
			JSONParser parser = new JSONParser();
			JSONObject result = (JSONObject) parser.parse(response);
			
			rtnObj.put("total", (long) result.get("total"));
			
			JSONArray items = (JSONArray) result.get("items");
			JSONObject title = (JSONObject) items.get(0);
			System.out.println(title.get("title"));
			
			List<Map<String, Object>> itemList = new ArrayList<> ();
			    
			for(int i = 0; i < items.size(); i++) {
				JSONObject item = (JSONObject) items.get(i);
				Map<String, Object> itemMap = new HashMap<> ();
				
				for(String field : fields) {
					itemMap.put(field, item.get(field));
				}
				itemList.add(itemMap);
			}
			
			rtnObj.put("result", itemList);
		} catch (Exception e) {
			System.out.println("getResult error -> " + "파싱 실패, " + e.getMessage());			
		}
		
		return rtnObj;
	}
	
	
	
	// 파싱한 데이터를 MovieDTO로 보냄
	public ArrayList<MovieDTO> searchResult(String _movie) {
		String id = "DJfTSwIW9elkXOTSv9bs";
        String secret = "yHnzkTPW0M";
        ArrayList<MovieDTO> movieList = new ArrayList<MovieDTO>();
        
        try {
	        MovieDAO crawler = new MovieDAO();
	        String response = crawler.search(id, secret, _movie);
	        
	        String[] fields = {"title", "link", "image", "subtitle", "pubDate", "director", "actor", "userRating"};
	        Map<String, Object> result = crawler.getResult(response, fields);
	        
	        if(result.size() > 0) {
	        	System.out.println("total -> " + result.get("total"));
	        }
	        
	        
			List<Map<String, Object>> items = (List<Map<String, Object>>) result.get("result");
	        for(Map<String, Object> item : items) {
	        	
	        	String title = (String) item.get("title");
    			String link = (String) item.get("link");
    			String image = (String) item.get("image");
    			String subtitle =(String) item.get("subtitle");
    			String pubDate =(String) item.get("pubDate");
    			String director = (String) item.get("director");
    			String actor = (String) item.get("actor");
    			String userRating =(String) item.get("userRating");
    			
	        	
    			MovieDTO data = new MovieDTO();
    			data.setTitle(title);
    			data.setLink(link);
    			data.setImage(image);
    			data.setSubtitle(subtitle);
    			data.setPubDate(pubDate);
    			data.setDirector(director);
    			data.setActor(actor);
    			data.setUserRating(userRating);
    			
    			movieList.add(data);
	    
	        }
        } catch (Exception e) {
        	e.printStackTrace();
        }
        
        return movieList;
	}
	
	
	
	
	
	public void movieWrite(String _title, String _link, String _image, String _subtitle,
			String _pubDate, String _director, String _actor, String _userRating) {
		Connection conn = null;
		PreparedStatement pstmt = null;
	
        
        try {
        	conn = dataFactory.getConnection();
			String sql = "INSERT IGNORE INTO movie (title, link, image, subtitle, pubDate, director, actor, userRating) VALUES (?,?,?,?,?,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _title);
			pstmt.setString(2, _link);
			pstmt.setString(3, _image);
			pstmt.setString(4, _subtitle);
			pstmt.setString(5, _pubDate);
			pstmt.setString(6, _director);
			pstmt.setString(7, _actor);
			pstmt.setString(8, _userRating);
			pstmt.executeUpdate();
        	
        } catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		
	}
	
	
	
	
	public MovieDTO movieReview(String _link) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MovieDTO data = new MovieDTO();
		
		try {
			conn = dataFactory.getConnection();
			String sql = "SELECT * FROM movie WHERE link = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, _link);
			rs = pstmt.executeQuery();
			
			if (rs.next()) {
				String title = rs.getString("title");
				String link = rs.getString("link");
				String image = rs.getString("image");
				String subtitle = rs.getString("subtitle");
				String pubDate = rs.getString("pubDate");
				String director = rs.getString("director");
				String actor = rs.getString("actor");
				String userRating = rs.getString("userRating");
				
				data.setTitle(title);
				data.setLink(link);
				data.setImage(image);
				data.setSubtitle(subtitle);
				data.setPubDate(pubDate);
				data.setDirector(director);
				data.setActor(actor);
				data.setUserRating(userRating);
				
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		} finally {
			try {
				if (rs != null)
					rs.close();
				if (pstmt != null)
					pstmt.close();
				if (conn != null)
					conn.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		
		return data;
	}
	
}

