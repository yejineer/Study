import java.io.*;

import javax.servlet.*;
import javax.servlet.http.*;

import java.util.*;

//���� ���ε带 ���� API�� ����ϱ� ����...
import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.FileUploadBase.SizeLimitExceededException;
//���� �뷮 �ʰ��� ���� Exception Ŭ������ FileUploadBase Ŭ������ Inner Ŭ������ ó��
import org.apache.commons.fileupload.servlet.*;


@SuppressWarnings("serial")
public class Round16_04_Servlet extends HttpServlet {
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		
		String name = "";
		String id = "";
		String pw = "";
		String filename = ""; 
	
		boolean check = ServletFileUpload.isMultipartContent(request);
		//���۵� �������� ���ڵ� Ÿ���� multipart ���� ���θ� üũ�Ѵ�.
		//���� multipart�� �ƴ϶�� ���� ������ ó������ �ʴ´�.
		
		if(check) {//���� ������ ���Ե� ���°� �´ٸ�
			ServletContext context = this.getServletContext();
			String path = context.getRealPath("/upload");
			File dir = new File(path);
			if(!dir.exists()) dir.mkdir();
			//���۵� ������ ������ ���� ��θ� �����.
			
			try {
				DiskFileItemFactory factory = new DiskFileItemFactory();
                //���� ���ۿ� ���� �⺻���� ���� Factory Ŭ������ �����Ѵ�.
                factory.setSizeThreshold(10 * 1024);
                //�޸𸮿� �ѹ��� ������ �������� ũ�⸦ �����Ѵ�.
                //10kb �� �޸𸮿� �����͸� �о� ���δ�.
                factory.setRepository(dir);
                //���۵� �������� ������ ������ �ӽ� ������ �����Ѵ�.                
    
                ServletFileUpload upload = new ServletFileUpload(factory);
                //Factory Ŭ������ ���� ���� ���ε� �Ǵ� ������ ó���� ��ü�� �����Ѵ�.
                upload.setSizeMax(10 * 1024 * 1024);
                //���ε� �� ������ �ִ� �뷮�� 10MB���� ���� ����Ѵ�.
                upload.setHeaderEncoding("EUC-KR");
                //���ε� �Ǵ� ������ ���ڵ��� �����Ѵ�.
                                
                List items = (List)upload.parseRequest(request);
                //upload ��ü�� ���۵Ǿ� �� ��� �����͸� Collection ��ü�� ��´�.
                for(int i = 0; i < items.size(); ++i) {
                	FileItem item = (FileItem)items.get(i);
                	//commons-fileupload�� ����Ͽ� ���۹����� 
                	//��� parameter�� FileItem Ŭ������ �ϳ��� ����ȴ�.
                	
                	String value = item.getString("euc-kr");
                	//�Ѿ�� ���� ���� �ѱ� ó���� �Ѵ�.
                	
                	if(item.isFormField()) {//�Ϲ� �� �����Ͷ��...                		
                		if(item.getFieldName().equals("name")) name = value;
                		//key ���� name�̸� name ������ ���� �����Ѵ�.
                		else if(item.getFieldName().equals("id")) id = value;
                		//key ���� id�̸� id ������ ���� �����Ѵ�.
                		else if(item.getFieldName().equals("pw")) pw = value;
                		//key ���� pw�̸� pw ������ ���� �����Ѵ�.
                	}
                	else {//�����̶��...
                		if(item.getFieldName().equals("picture")) {
                		//key ���� picture�̸� ���� ������ �Ѵ�.
                			filename = item.getName();//���� �̸� ȹ�� (�ڵ� �ѱ� ó�� ��)
                			if(filename == null || filename.trim().length() == 0) continue;
                			//������ ���۵Ǿ� ���� �ʾҴٸ� �ǳ� �ڴ�.
                			filename = filename.substring(filename.lastIndexOf("\\") + 1);
                			//���� �̸��� ������ ��ü ��α��� �����ϱ� ������ �̸� �κи� �����ؾ� �Ѵ�.
                			//���� C:\Web_Java\aaa.gif��� �ϸ� aaa.gif�� �����ϱ� ���� �ڵ��̴�.
                			File file = new File(dir, filename);
                			item.write(file);
                			//������ upload ��ο� ������ �����Ѵ�.
                			//FileItem ��ü�� ���� �ٷ� ��� ������ �� �ִ�.
                		}
                	}
                }
                
			}catch(SizeLimitExceededException e) {
			//���ε� �Ǵ� ������ ũ�Ⱑ ������ �ִ� ũ�⸦ �ʰ��� �� �߻��ϴ� ����ó��
				e.printStackTrace();           
            }catch(FileUploadException e) {
            //���� ���ε�� ���õǾ� �߻��� �� �ִ� ���� ó��
                e.printStackTrace();
            }catch(Exception e) {            
                e.printStackTrace();
            }
            
            response.setContentType("text/html;charset=euc-kr");
            PrintWriter out = response.getWriter();
            
            out.println("<html><body>");
            out.println("ȸ�� ������ �̸� : " + name + "<br/>");
            out.println("ȸ�� ������ ID : " + id + "<br/>");
            out.println("ȸ�� ������ ��� : " + pw + "<br/>");
            out.println("ȸ�� ������ ���� ���� ��� : " + dir + "<br/>");
            out.println("ȸ�� ������ ���� ���� �̸� : " + filename + "<br/>");
            out.println("<img src=\"" + dir + "\\" + filename + "\"/>");
            out.println("</body></html>");
		}		
	}
}
