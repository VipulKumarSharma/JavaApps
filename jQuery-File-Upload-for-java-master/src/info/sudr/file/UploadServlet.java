package info.sudr.file;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.imgscalr.Scalr;
import org.json.JSONArray;
import org.json.JSONObject;

public class UploadServlet extends HttpServlet {
        
	private static final long serialVersionUID = 9196723809206800320L;

	@SuppressWarnings("unchecked")
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (!ServletFileUpload.isMultipartContent(request)) {
            throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
        }
        response.setContentType("application/json");
        
        ServletFileUpload uploadHandler 			= null;
        JSONArray json 								= null;
        HashMap<String, Object> fileData 			= null;
        ArrayList<HashMap<String, Object>> filesData= null;
        List<FileItem> items 						= null;
        PrintWriter writer 							= null;
        
        String reqMstId								= "";
        String docReference							= "";
        String reqMstDocId							= "";
        String reqDocType							= "";
        String reqSiteId							= "";
        
        try {
        	uploadHandler 	= new ServletFileUpload(new DiskFileItemFactory());
        	json 			= new JSONArray();
        	filesData		= new ArrayList<HashMap<String, Object>>();
            items 			= uploadHandler.parseRequest(request);
            writer 			= response.getWriter();
            
            for (FileItem item : items) {
                if (!item.isFormField()) {
                	String fileName = item.getName();
                	String browser	= getBrowserInfo(request.getHeader("User-Agent"));
                	//System.out.println("\nBrowser : "+browser);
                	
                	if(browser.startsWith("IE") || browser.startsWith("MSIE")) {
                		fileName = fileName.substring(fileName.lastIndexOf("\\")+1);
                	}
                	
                    File file = new File(request.getServletContext().getRealPath("/")+"imgs/", fileName);
                    item.write(file);
                    
                    byte[] byteArray = new byte[(int) file.length()];
                    byteArray = FileUtils.readFileToByteArray(file);
                    
                    int fileSizeInBytes = (int) item.getSize();
                    int fileSizeInKB 	= (int) (fileSizeInBytes / 1024);
                    
                    fileData = new HashMap<String, Object>();
                    fileData.put("fileName", 		fileName);
                    fileData.put("fileSizeinKB", 	fileSizeInKB);
                    fileData.put("fileBytes", 		byteArray);
                    filesData.add(fileData);
                    
                    JSONObject jsono = new JSONObject();
                    jsono.put("name",	fileName);
                    jsono.put("size", 	fileSizeInBytes);
                    jsono.put("url", 	"UploadServlet?getfile=" + item.getName());
                    
                    String fileExtension = FilenameUtils.getExtension(fileName);
                    
                    if (fileExtension.endsWith("png") || fileExtension.endsWith("jpeg")|| fileExtension.endsWith("jpg") || fileExtension.endsWith("gif")) {
                    	jsono.put("thumbnail_url", "UploadServlet?getthumb=" + fileName);
                    
                    } else if(fileExtension.toLowerCase().endsWith("doc") || fileExtension.toLowerCase().endsWith("docx")) {
                    	jsono.put("thumbnail_url", "img/word-25.png");
                    	
                    } else if(fileExtension.toLowerCase().endsWith("xls") || fileExtension.toLowerCase().endsWith("xlsx")) {
                    	jsono.put("thumbnail_url", "img/excel-25.png");
                    	
                    } else if(fileExtension.toLowerCase().endsWith("ppt") || fileExtension.toLowerCase().endsWith("pptx")) {
                    	jsono.put("thumbnail_url", "img/powerpoint-25.png");
                    	
                    } else if(fileExtension.toLowerCase().endsWith("pdf")) {
                    	jsono.put("thumbnail_url", "img/pdf-25.png");
                    	
                    } else if(fileExtension.toLowerCase().endsWith("txt")) {
                    	jsono.put("thumbnail_url", "img/txt-25.png");
                    	
                    } else {
                    	jsono.put("thumbnail_url", "img/document-25.png");
                    }
                    jsono.put("delete_url", "UploadServlet?delfile=" + fileName);
                    jsono.put("delete_type", "GET");
                    json.put(jsono);
                
                } else {
                    if(item.getFieldName() != null && !"".equals(item.getFieldName().toString()) && "reqMstId".equals(item.getFieldName().toString())) {
                		reqMstId = item.getString();
                	}
                    if(item.getFieldName() != null && !"".equals(item.getFieldName().toString()) && "docReference".equals(item.getFieldName().toString())) {
                    	docReference = item.getString();
                	}
                    if(item.getFieldName() != null && !"".equals(item.getFieldName().toString()) && "reqMstDocId".equals(item.getFieldName().toString())) {
                    	reqMstDocId = item.getString();
                	}
                    if(item.getFieldName() != null && !"".equals(item.getFieldName().toString()) && "reqDocType".equals(item.getFieldName().toString())) {
                    	reqDocType = item.getString();
                	}
                    if(item.getFieldName() != null && !"".equals(item.getFieldName().toString()) && "reqSiteId".equals(item.getFieldName().toString())) {
                    	reqSiteId = item.getString();
                	}
                }
            }
            System.out.println("\n********************************************************************************************");
            System.out.println("reqMstId = "+reqMstId);
            System.out.println("docReference = "+docReference);
            System.out.println("reqMstDocId = "+reqMstDocId);
            System.out.println("reqDocType = "+reqDocType);
            System.out.println("reqSiteId = "+reqSiteId);
            
            for(HashMap<String, Object> file_Data : filesData) {
            	String fileName 	= file_Data.get("fileName").toString();
            	int fileSizeinKB	= Integer.parseInt(file_Data.get("fileSizeinKB").toString());
            	byte[] fileBytes	= (byte[]) file_Data.get("fileBytes");
            	
            	System.out.println(fileName+"	("+fileSizeinKB+" KB) -- "+fileBytes);
            }
            Thread.sleep(1000);
            
        } catch (FileUploadException e) {
                throw new RuntimeException(e);
        } catch (Exception e) {
                throw new RuntimeException(e);
        } finally {
            writer.write(json.toString());
            writer.close();
        }
    }
	
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        if (request.getParameter("getfile") != null && !request.getParameter("getfile").isEmpty()) {
            File file = new File(request.getServletContext().getRealPath("/")+"imgs/"+request.getParameter("getfile"));
            if (file.exists()) {
                int bytes = 0;
                ServletOutputStream op = response.getOutputStream();

                response.setContentType(getMimeType(file));
                response.setContentLength((int) file.length());
                response.setHeader( "Content-Disposition", "inline; filename=\"" + file.getName() + "\"" );

                byte[] bbuf = new byte[1024];
                DataInputStream in = new DataInputStream(new FileInputStream(file));

                while ((in != null) && ((bytes = in.read(bbuf)) != -1)) {
                    op.write(bbuf, 0, bytes);
                }

                in.close();
                op.flush();
                op.close();
            }
        
        } else if (request.getParameter("delfile") != null && !request.getParameter("delfile").isEmpty()) {
            File file = new File(request.getServletContext().getRealPath("/")+"imgs/"+ request.getParameter("delfile"));
            if (file.exists()) {
                file.delete();
            } 
        
        } else if (request.getParameter("getthumb") != null && !request.getParameter("getthumb").isEmpty()) {
            File file = new File(request.getServletContext().getRealPath("/")+"imgs/"+request.getParameter("getthumb"));
            
            if (file.exists()) {
                //System.out.println(file.getAbsolutePath());
                String mimetype = getMimeType(file);
                
                if (mimetype.endsWith("png") || mimetype.endsWith("jpeg")|| mimetype.endsWith("jpg") || mimetype.endsWith("gif")) {
                    BufferedImage im = ImageIO.read(file);
                    
                    if (im != null) {
                        BufferedImage thumb 		= Scalr.resize(im, 25); 
                        ByteArrayOutputStream os 	= new ByteArrayOutputStream();
                        
                        if (mimetype.endsWith("png")) {
                            ImageIO.write(thumb, "PNG" , os);
                            response.setContentType("image/png");
                        } else if (mimetype.endsWith("jpeg")) {
                            ImageIO.write(thumb, "jpg" , os);
                            response.setContentType("image/jpeg");
                        } else if (mimetype.endsWith("jpg")) {
                            ImageIO.write(thumb, "jpg" , os);
                            response.setContentType("image/jpeg");
                        } else {
                            ImageIO.write(thumb, "GIF" , os);
                            response.setContentType("image/gif");
                        }
                        
                        ServletOutputStream srvos = response.getOutputStream();
                        response.setContentLength(os.size());
                        response.setHeader( "Content-Disposition", "inline; filename=\"" + file.getName() + "\"" );
                        os.writeTo(srvos);
                        srvos.flush();
                        srvos.close();
                    }
                }
            }
    
    	} else {
    		PrintWriter writer = response.getWriter();
    		writer.write("call POST with multipart form data");
    	}
	}
    
    public String getBrowserInfo(String browser) {
		String browserType = "";
		
		 if (browser.indexOf("rv") >0 && browser.indexOf("Trident") >0 && browser.indexOf("Chrome") ==-1 && browser.indexOf("Firefox") ==-1 && browser.indexOf("Safari") ==-1 && browser.indexOf("AppleWebKit") ==-1) {
			 browserType = "IE v"+browser.substring(browser.indexOf("rv")+3,browser.indexOf(")"));
		 }
		 if (browser.indexOf("Edge") >0 && browser.indexOf("Trident") ==-1 && browser.indexOf("Firefox") ==-1) {
			 browserType = "EDGE v"+browser.substring(browser.indexOf("Edge")+5);
		 }
		 if (browser.indexOf("Chrome") >0 && browser.indexOf("Trident") ==-1 &&browser.indexOf("Edge") ==-1 && browser.indexOf("Firefox") ==-1) {
			 browserType = "CHROME v"+browser.substring(browser.indexOf("Chrome")+7,browser.indexOf(" Safari"));
		 }
		 if (browser.indexOf("Firefox") >0 && browser.indexOf("Trident") ==-1 && browser.indexOf("Chrome") ==-1 && browser.indexOf("Safari") ==-1 && browser.indexOf("AppleWebKit") ==-1) {
			 browserType = "FIREFOX v"+browser.substring(browser.indexOf("Firefox")+8);
		 }
		 if (browserType.equals("")) {
			 String browsername 		= "";
			 String browserversion 		= "";
			 String subsString 			= "";
			 String browserSubString[] 	= { "MSIE", "Firefox", "Chrome", "Opera","Netscape", "Safari" };
   		 
			if (browser.contains("MSIE")) {
							subsString = browser.substring(browser.indexOf("MSIE"));
							String Info[] = (subsString.split(";")[0]).split(" ");
							browsername = Info[0];
							browserversion = Info[1];
			} else {
				for (int index = 1; index < browserSubString.length; index++) {
					if (browser.contains(browserSubString[index])) {
						subsString = browser.substring(browser.indexOf(browserSubString[index]));
						String Info[] = (subsString.split(" ")[0]).split("/");
						browsername = Info[0];

						if (browser.indexOf("Version") != -1) {
							browserversion = browser.substring(browser.indexOf("Version")).split(" ")[0].split(";")[0].split("/")[1];
						} else {
							browserversion = Info[1];
						}
						break;
					}
				}
				if (browsername.equals("")) {
					subsString = browser.substring(browser.indexOf("Windows NT"));
					browsername = subsString.split(";")[0];
					subsString = browser.substring(browser.indexOf("Trident"));
					browserversion = subsString.split(";")[0];
				}
			}
			browserType = browsername + " " + browserversion;
		 }
		 return browserType;
	}
    
    private String getMimeType(File file) {
        String mimetype = "";
        if (file.exists()) {
            if (getSuffix(file.getName()).equalsIgnoreCase("png")) {
                mimetype = "image/png";
            }else if(getSuffix(file.getName()).equalsIgnoreCase("jpg")){
                mimetype = "image/jpg";
            }else if(getSuffix(file.getName()).equalsIgnoreCase("jpeg")){
                mimetype = "image/jpeg";
            }else if(getSuffix(file.getName()).equalsIgnoreCase("gif")){
                mimetype = "image/gif";
            }else {
                javax.activation.MimetypesFileTypeMap mtMap = new javax.activation.MimetypesFileTypeMap();
                mimetype  = mtMap.getContentType(file);
            }
        }
        return mimetype;
    }
    
    private String getSuffix(String filename) {
        String suffix = "";
        int pos = filename.lastIndexOf('.');
        if (pos > 0 && pos < filename.length() - 1) {
            suffix = filename.substring(pos + 1);
        }
        return suffix;
    }
}