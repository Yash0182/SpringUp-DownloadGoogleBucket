package com.springnew;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.ByteBuffer;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.BucketInfo;
import com.google.cloud.storage.Storage;
import com.google.cloud.storage.StorageOptions;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;


@Controller
public class DownloadController {
	
	

	


	
		//final String fileLocation = "/Users/tkmajdt/Documents/workspace/File1POC1/";
		final String[][] contentTypes = {{"xml","text/xml"},{"pdf","application/pdf"},{"doc","text/doc"}};
	    
		@RequestMapping("/Download")
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
		{   
			String fileName="";
			
			/*
			//ServletContext context = getServletContext();
			//String role=(String)context.getAttribute("roles");
			//boolean flag = roleChecker(role);
			if(flag)
			{
			*/
			Object requestedFile = request.getParameter("filename");
			/*if(requestedFile==null)
			{
				response.getWriter().print("File Not found");
			}
			
			else
			{*/
				fileName = (String)requestedFile;
				
				/*String contentType = getContentType(fileName.split("\\.")[1]);
				File file = new File(fileName);
				response.addHeader("Content-Disposition", "attachment; filename=" +fileName);
				response.setContentLength((int)file.length());
				ServletOutputStream sOS = response.getOutputStream();
				BufferedInputStream bIS = new BufferedInputStream(new FileInputStream(file));
				int bytesRead = bIS.read();
				while(bytesRead!=-1)
				{
					sOS.write(bytesRead);
					bytesRead = bIS.read();
				}
				if(sOS!=null)
				{
					sOS.close();
				}
				if(bIS!=null)
				{
					bIS.close();
				}
				String targetFileStr ="";*/
				Storage storage = StorageOptions.getDefaultInstance().getService();
				String bucketName = "vendor-bucket14";  // "my-bucket";
				//Bucket bucket = storage.create(BucketInfo.of(bucketName));
			
				BlobId blobId = BlobId.of(bucketName, fileName);
				
				//targetFileStr = new String(Files.readAllBytes(Paths.get("/Users/tkmajdt/Documents/workspace/File1POC1/" + f.getName())));
			    
				
	//			Blob blob = bucket.create("my_blob_name", targetFileStr.getBytes("UTF-8"), "text/plain");
				Blob blob1 = storage.get(blobId);
				System.out.println(blob1);
			    System.out.println("The name of the file is " + blob1.getName());
			    if (blob1 != null) {
			      byte[] prevContent = blob1.getContent();
			      System.out.println(new String(prevContent, "UTF-8"));
			      WritableByteChannel channel = blob1.writer();
			      channel.write(ByteBuffer.wrap("Updated content".getBytes("UTF-8")));
			      channel.close();
			    
			    }
			
			
			}
		/*	}
			else
			{
				response.getWriter().print("You are not authorized for this action");
			}
			PrintWriter out = response.getWriter();
			response.setContentType("text/html");
			out.println("<html>");
			out.println("<body>");
			out.println("<br/>");
			out.println("<a href='index.jsp'>Home</a>");
			out.println("</body>");
			out.println("</html>");
		}
			*/
		private String getContentType(String fileType)
			{
				String returnType = null;
				for(int i=0;i<contentTypes.length;i++)
				{
					if(fileType.equals(contentTypes[i][0]))
					{
						returnType = contentTypes[i][1];
					}
				}
				return returnType;
			}
		private boolean roleChecker(String name)
		{
			String role="admin";
			
			if (role.equals(name))
			{
				return true;
			}
			else
			{
			return false;
			}
		}
			
			
		

	}



