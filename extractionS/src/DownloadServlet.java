import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class DownloadServlet extends HttpServlet {
	private String contentType = "application/x-msdownload";
	private String enc = "utf-8";
	private String fileRoot = "";

	/**
	 * ��ʼ��contentType��enc��fileRoot
	 */
	public void init(ServletConfig config) throws ServletException {
		String tempStr = config.getInitParameter("contentType");
		if (tempStr != null && !tempStr.equals("")) {
			contentType = tempStr;
		}
		tempStr = config.getInitParameter("enc");
		if (tempStr != null && !tempStr.equals("")) {
			enc = tempStr;
		}
		tempStr = config.getInitParameter("fileRoot");
		if (tempStr != null && !tempStr.equals("")) {
			fileRoot = tempStr;
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String filepath = request.getParameter("filepath");
		String fullFilePath = fileRoot + filepath;
		System.out.println(fullFilePath);
		/* ��ȡ�ļ� */
		File file = new File(fullFilePath);
		/* ����ļ����� */
		if (file.exists()) {
			String filename = URLEncoder.encode(file.getName(), enc);
			response.reset();
			response.setContentType(contentType);
			
			response.addHeader("Content-Disposition", "attachment; filename=\"" + filename + "\"");
			int fileLength = (int) file.length();
			response.setContentLength(fileLength);
			/* ����ļ����ȴ���0 */
			if (fileLength != 0) {
				/* ���������� */
				InputStream inStream = new FileInputStream(file);
				byte[] buf = new byte[4096];
				/* ��������� */
				ServletOutputStream servletOS = response.getOutputStream();
				int readLength;
				while (((readLength = inStream.read(buf)) != -1)) {
					servletOS.write(buf, 0, readLength);
				}
				inStream.close();
				servletOS.flush();
				servletOS.close();
			}
		}
	}
}