package com.projects.informatics.services;

import java.util.HashMap;
import java.util.Map;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;

@Service
public class EmailService {

	@Autowired
	private JavaMailSender emailSender;

	@Autowired
	private SpringTemplateEngine thymeleafTemplateEngine;

	public void sendFeedbackMessage(String firstName, String lastName, String recipientEmail, String feedback) {
		Map<String, Object> templateModel = new HashMap<>();
		templateModel.put("firstName", firstName);
		templateModel.put("lastName", lastName);
		templateModel.put("text", "Thank you for providing us with a feedback.");
		templateModel.put("feedbackMessage", feedback);

		Context thymeleafContext = new Context();
		thymeleafContext.setVariables(templateModel);
		String htmlBody = thymeleafTemplateEngine.process("feedback-template.html", thymeleafContext);

		sendEmail(recipientEmail, "Feedback Received", htmlBody);
	}

	private void sendEmail(String to, String subject, String body) {
		try {
			MimeMessage message = emailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");
//			helper.setFrom(NOREPLY_ADDRESS);
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);

			emailSender.send(message);
		} catch (MessagingException e) {
			e.printStackTrace();
		}

//		SimpleMailMessage emailContext = new SimpleMailMessage();
//		emailContext.setTo(to);
//		emailContext.setSubject(subject);
//		emailContext.setText(body);
//		emailSender.send(emailContext);
	}

}
