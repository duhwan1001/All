package kr.or.ddit.basic;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * 서블릿 3부터 지원하는 Part 인터페이스를 이용한 파일 업로드 예제
 */
@WebServlet(name ="UploadServlet3", urlPatterns="/upload3") // web.xml에 안올려도됨
@MultipartConfig(fileSizeThreshold = 1024*1024, maxFileSize=1024*1024*5, maxRequestSize=1024*1024*5*5)
public class UploadServlet3 extends HttpServlet{
	
	private static final String UPLOAD_DIR = "upload_files";
	
	/**
	 * Part 객체 파싱하여 파일이름 추출하기
	 * @param part
	 * @return null
	 */
	private String getFileName(Part part) {
	
		/**
		 * multipart body를 위한 헤더 정보로 사용 되는 경우 ...ex) 파일업로드
		 * Content-Disposition : form-data
		 * Content-Disposition : form-data; name="fieldName";
		 * Content-Disposition : form-data; name="fieldName";"fieldname)
		 */
		for(String content : part.getHeader("Content-Disposition").split(";")) {	
			return content.substring(content.indexOf("=")+1).trim().replace("\"", "");
		}
		return null;
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		String uploadPath = getServletContext().getRealPath("") + File.separator + UPLOAD_DIR;
		
		File uploadDir = new File(UPLOAD_DIR);
		if(!uploadDir.exists()) {
			uploadDir.mkdir();
		}
		
		try {
			String fileName = "";
			for(Part part : req.getParts()) {
				System.out.println(part.getHeader("Content-Disposition"));
				
				fileName = getFileName(part);
				
				if(fileName != null && !fileName.equals("")) {
					// 폼필드가 아니거나 파일이 비어있지 않은 경우..
					// 파일 저장
					part.write(uploadPath + File.separator + fileName);
					System.out.println("파일명 : " + fileName + "업로드 완료");
				}
			}
		} catch(FileNotFoundException ex) {
			ex.printStackTrace();
		} 
			System.out.println("파라미터 값 : " + req.getParameter("sender"));
			
			resp.setContentType("text/html");
			resp.getWriter().println("업로드 완료");
	}
	
	
	
	
	
	
	
	
	
	
	
	
}
