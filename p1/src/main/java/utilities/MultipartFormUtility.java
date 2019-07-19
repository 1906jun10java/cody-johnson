package utilities;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultipartFormUtility {
	public static List<FileItem> getItems(HttpServletRequest req) {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(factory);
		List<FileItem> items = new ArrayList<>();
		try {
			items = upload.parseRequest(req);
		} catch (FileUploadException e) {
			e.printStackTrace();
		}

		return items;
	}

	public static Map<String,String> parseFormData(List<FileItem> items) {
		Map<String,String> formData = new HashMap<>();
		for (FileItem item : items) {
			if (item.isFormField()) {
				formData.put(item.getFieldName(), item.getString());
			}
		}

		return formData;
	}

	public static byte[] parseFile(List<FileItem> items) {
		byte[] file = null;
		for (FileItem item : items) {
			if (!item.isFormField()) {
				file = item.get();
			}
		}

		return file;
	}
}
