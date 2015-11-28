import javax.servlet.*;  
import javax.servlet.http.*;  
import java.io.*;  
import java.util.*;  
import org.apache.commons.fileupload.*;  
import org.apache.commons.fileupload.servlet.*;  
import org.apache.commons.fileupload.disk.*;  
 
// Servlet �ļ��ϴ�  
public class UploadandfilterServlet extends HttpServlet  
{  
    private String filePath;    // �ļ����Ŀ¼  
    private String tempPath;    // ��ʱ�ļ�Ŀ¼  
 
    // ��ʼ��  
    public void init(ServletConfig config) throws ServletException  
    {  
        super.init(config);  
        // �������ļ��л�ó�ʼ������  
        filePath = "";
        tempPath = "";
 
        ServletContext context = getServletContext();  
 
        filePath =config.getInitParameter("filepath");
        tempPath = config.getInitParameter("filepath");
        System.out.println(tempPath);
        System.out.println("�ļ����Ŀ¼����ʱ�ļ�Ŀ¼׼����� ...");  
    }  
      
    // doPost  
    public void doPost(HttpServletRequest req, HttpServletResponse res)  
        throws IOException, ServletException  
    {  
        res.setContentType("text/plain;charset=utf-8");  
        PrintWriter pw = res.getWriter();  
        try{  
            DiskFileItemFactory diskFactory = new DiskFileItemFactory();  
            // threshold ���ޡ��ٽ�ֵ����Ӳ�̻��� 1M  
            diskFactory.setSizeThreshold(20 *1024* 1024);  
            // repository �����ң�����ʱ�ļ�Ŀ¼  
            diskFactory.setRepository(new File(tempPath));  
          
            ServletFileUpload upload = new ServletFileUpload(diskFactory);  
            // ���������ϴ�������ļ���С 4M  
            upload.setSizeMax(20 * 1024 * 1024);  
            // ����HTTP������Ϣͷ  
            List fileItems = upload.parseRequest(req);  
            Iterator iter = fileItems.iterator();  
            while(iter.hasNext())  
            {  
                FileItem item = (FileItem)iter.next();  
                if(item.isFormField())  
                {  
                    System.out.println("������������ ...");  
                    processFormField(item, pw);  
                }else{  
                    System.out.println("�����ϴ����ļ� ...");  
                    processUploadFile(item, pw);  
                }  
            }// end while()  
 
            
            
            
            
            res.sendRedirect("Index.jsp");
        }catch(Exception e){  
            System.out.println("ʹ�� fileupload ��ʱ�����쳣 ...");  
            e.printStackTrace();  
        }// end try ... catch ...  
    }// end doPost()  
 
 
    // ������������  
    private void processFormField(FileItem item, PrintWriter pw)  
        throws Exception  
    {  
        String name = item.getFieldName();  
        String value = item.getString();          
        pw.println(name + " : " + value + "\r\n");  
    }  
      
    // �����ϴ����ļ�  
    private void processUploadFile(FileItem item, PrintWriter pw)  
        throws Exception  
    {  
        // ��ʱ���ļ���������������·������ע��ӹ�һ��  
        String filename = item.getName();         
        System.out.println("�������ļ�����" + filename);  
        int index = filename.lastIndexOf("\\");  
        filename = filename.substring(index + 1, filename.length());  
 
        long fileSize = item.getSize();  
 
        if("".equals(filename) && fileSize == 0)  
        {             
            System.out.println("�ļ���Ϊ�� ...");  
            return;  
        }  
 
        File uploadFile = new File(filePath + "/" + filename);  
        item.write(uploadFile);  
        pw.println(filename + " �ļ�������� ...");  
        pw.println("�ļ���СΪ ��" + fileSize + "\r\n");  
        Filter filt=new Filter();
        filt.filterdata(filePath + "/" + filename);
        System.out.println("filt success");
        
        
    }  
    
    
    
   
      
    // doGet  
    public void doGet(HttpServletRequest req, HttpServletResponse res)  
        throws IOException, ServletException  
    {  
        doPost(req, res);  
            }  
} 