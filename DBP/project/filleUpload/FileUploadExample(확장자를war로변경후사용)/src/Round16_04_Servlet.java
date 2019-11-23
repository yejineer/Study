import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;

//파일 업로드를 위한 API를 사용하기 위해...
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
//파일 용량 초과에 대한 Exception 클래스를 FileUploadBase 클래스의 Inner 클래스로 처리
import org.apache.commons.fileupload.servlet.*;


@SuppressWarnings("serial")
public class Round16_04_Servlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String name = "";
		String id = "";
		String pw = "";
		String filename = ""; 
	
		boolean check = ServletFileUpload.isMultipartContent(request);
		//전송된 데이터의 인코드 타입이 multipart 인지 여부를 체크한다.
		//만약 multipart가 아니라면 파일 전송을 처리하지 않는다.
		
		if(check) {//파일 전송이 포함된 상태가 맞다면
			ServletContext context = this.getServletContext();
			String path = context.getRealPath("/upload");
			File dir = new File(path);
			if(!dir.exists()) dir.mkdir();
			//전송된 파일을 저장할 실제 경로를 만든다.
			
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
                //파일 전송에 대한 기본적인 설정 Factory 클래스를 생성한다.
                factory.setSizeThreshold(10 * 1024);
                //메모리에 한번에 저장할 데이터의 크기를 설정한다.
                //10kb 씩 메모리에 데이터를 읽어 들인다.
                factory.setRepository(dir);
                //전송된 데이터의 내용을 저장할 임시 폴더를 지정한다.                
    
                ServletFileUpload upload = new ServletFileUpload(factory);
                //Factory 클래스를 통해 실제 업로드 되는 내용을 처리할 객체를 선언한다.
                upload.setSizeMax(10 * 1024 * 1024);
                //업로드 될 파일의 최대 용량을 10MB까지 전송 허용한다.
                upload.setHeaderEncoding("EUC-KR");
                //업로드 되는 내용의 인코딩을 설정한다.
                                
                List items = (List)upload.parseRequest(request);
                //upload 객체에 전송되어 온 모든 데이터를 Collection 객체에 담는다.
                for(int i = 0; i < items.size(); ++i) {
                	FileItem item = (FileItem)items.get(i);
                	//commons-fileupload를 사용하여 전송받으면 
                	//모든 parameter는 FileItem 클래스에 하나씩 저장된다.
                	
                	String value = item.getString("euc-kr");
                	//넘어온 값에 대한 한글 처리를 한다.
                	
                	if(item.isFormField()) {//일반 폼 데이터라면...                		
                		if(item.getFieldName().equals("name")) name = value;
                		//key 값이 name이면 name 변수에 값을 저장한다.
                		else if(item.getFieldName().equals("id")) id = value;
                		//key 값이 id이면 id 변수에 값을 저장한다.
                		else if(item.getFieldName().equals("pw")) pw = value;
                		//key 값이 pw이면 pw 변수에 값을 저장한다.
                	}
                	else {//파일이라면...
                		if(item.getFieldName().equals("picture")) {
                		//key 값이 picture이면 파일 저장을 한다.
                			filename = item.getName();//파일 이름 획득 (자동 한글 처리 됨)
                			if(filename == null || filename.trim().length() == 0) continue;
                			//파일이 전송되어 오지 않았다면 건너 뛴다.
                			filename = filename.substring(filename.lastIndexOf("\\") + 1);
                			//파일 이름이 파일의 전체 경로까지 포함하기 때문에 이름 부분만 추출해야 한다.
                			//실제 C:\Web_Java\aaa.gif라고 하면 aaa.gif만 추출하기 위한 코드이다.
                			File file = new File(dir, filename);
                			item.write(file);
                			//파일을 upload 경로에 실제로 저장한다.
                			//FileItem 객체를 통해 바로 출력 저장할 수 있다.
                		}
                	}
                }
                
			}catch(SizeLimitExceededException e) {
			//업로드 되는 파일의 크기가 지정된 최대 크기를 초과할 때 발생하는 예외처리
				e.printStackTrace();           
            }catch(FileUploadException e) {
            //파일 업로드와 관련되어 발생할 수 있는 예외 처리
                e.printStackTrace();
            }catch(Exception e) {            
                e.printStackTrace();
            }
            
            response.setContentType("text/html;charset=euc-kr");
            PrintWriter out = response.getWriter();
            
            out.println("<html><body>");
            out.println("회원 가입할 이름 : " + name + "<br/>");
            out.println("회원 가입할 ID : " + id + "<br/>");
            out.println("회원 가입할 비번 : " + pw + "<br/>");
            out.println("회원 가입할 사진 저장 경로 : " + dir + "<br/>");
            out.println("회원 가입할 사진 파일 이름 : " + filename + "<br/>");
            out.println("<img src=\"" + dir + "\\" + filename + "\"/>");
            out.println("</body></html>");
		}		
	}
}
