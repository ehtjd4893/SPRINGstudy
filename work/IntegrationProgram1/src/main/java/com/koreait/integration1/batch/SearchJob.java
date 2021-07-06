package com.koreait.integration1.batch;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;

import com.koreait.integration1.domain.Search;
import com.koreait.integration1.repository.SearchRepository;

public class SearchJob implements Job{

	private String[] arr = {"코미디", "공포", "멜로", "드라마", "SF"};
	
	@Autowired
	SearchRepository repository;
	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		
		
		String keyword = arr[(int)(Math.random() * arr.length)];
		Map<String, String> map = new HashMap<String, String>();
		map.put("column", "TITLE");
		map.put("query", keyword);
		List<Search> list = repository.selectQuery(map);
		if(list.size() == 0) {
			try {
				byte[] b = new String(keyword + " 검색 결과가 없습니다.").getBytes();
				FileWriter fw = new FileWriter(new File("error.txt"));
				fw.write(new String(keyword + " 검색 결과가 없습니다."));
				fw.flush();
				System.out.println("파일생성");
				fw.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				System.out.println("파일 생성 실패");
			}
		}
	}

}
