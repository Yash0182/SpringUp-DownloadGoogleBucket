package com.springnew;

import com.google.cloud.storage.Acl;

import com.google.cloud.storage.Blob;

import com.google.cloud.storage.BlobInfo;

import com.google.cloud.storage.Storage;

import com.google.cloud.storage.StorageOptions;



import java.io.IOException;

import java.util.ArrayList;

import java.util.List;



import javax.servlet.ServletException;

import javax.servlet.annotation.MultipartConfig;

import javax.servlet.annotation.WebServlet;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import javax.servlet.http.Part;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;




// [START example]

//@SuppressWarnings("serial")


//@MultipartConfig()
@Controller

public class TestUploadController {
  //private static final String BUCKET_NAME = System.getenv("BUCKET_NAME");
	private static final String BUCKET_NAME = "vendor-bucket14";
  //private static Storage storage = null;
  private static Storage storage = StorageOptions.getDefaultInstance().getService();


@RequestMapping(value="/TestBucket",method = RequestMethod.POST)
  //public void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException,
public void doPost(@RequestParam("file") MultipartFile file1) throws IOException,

      ServletException {

    //final Part filePart = req.getPart("file");

   // final String fileName = filePart.getSubmittedFileName();

	 final String fileName = file1.getName();// getSubmittedFileName();
	 //System.out.println(fileName);

    // Modify access list to allow all users with link to read file

    List<Acl> acls = new ArrayList();

    acls.add(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));

    // the inputstream is closed by default, so we don't need to close it here

    Blob blob =

        storage.create(

            BlobInfo.newBuilder(BUCKET_NAME, fileName).setAcl(acls).build(),

            file1.getInputStream());



    // return the public download link

   // resp.getWriter().print(blob.getMediaLink());

  }

}

