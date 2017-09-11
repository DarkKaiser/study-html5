package kr.co.darkkaiser;

import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.lowagie.text.Chapter;
import com.lowagie.text.Document;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;

@Component
public class HelloPdfView extends AbstractPdfView {

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		/* �ּ� �κ��� �����ϸ� PDF ������ ���������� �ٷ� �������� �ʰ� �ٿ�ε� �ǵ��� �� �� �ִ�.
		String userAgent = request.getHeader("User-Agent");
		String fileName = "test.pdf";
		  
		if (userAgent.indexOf("MSIE") > -1){
		   fileName = URLEncoder.encode(fileName, "utf-8");
		} else {
		   fileName = new String(fileName.getBytes("utf-8"), "iso-8859-1");
		}
		  
		response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		*/
		  
		Chapter chapter = new Chapter(new Paragraph("Spring Message"), 1);
		chapter.add(new Paragraph("message"));
		document.add(chapter);
	}

}
