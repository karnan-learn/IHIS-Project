package com.admin.api.utils;


import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

@Component
public class SendEmail {
	
	@Autowired
	private JavaMailSender mailSender;

	public boolean send(
			String to,String cc[],String bcc[],String subject,String text,Resource[] files
			)
	{
		boolean sent = false;
		try {
			/*1. Create Empty message using JavaMailSender
		   	MimeMessage (MIME - MultiPurpose Internet Mail Extension)*/
			MimeMessage message = mailSender.createMimeMessage();

			/*2. Fill details using MimeMessageHelper class
			to, cc, bcc, subject, text..etc*/
			MimeMessageHelper helper = new MimeMessageHelper(message, (files!=null && files.length>0) ? true : false);

			helper.setTo(to);
			if(cc!=null)
				helper.setCc(cc);
			if(bcc!=null)
				helper.setBcc(bcc);
			helper.setSubject(subject);
			helper.setText(text,true);

			/*3. Add attachments using MimeMessageHelper class	Resource[]*/
			if(files!=null && files.length>0) {
				for(Resource file:files) {
					helper.addAttachment(file.getFilename(), file);
				}
			}
			/*4. Send Email using JavaMailSender*/
			mailSender.send(message);

			sent = true;
		} catch (Exception e) {
			e.printStackTrace();
		}

		return sent;
	}
	
}

