package com.projects.informatics.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.projects.informatics.entities.Feedback;
import com.projects.informatics.repositories.FeedbackRepository;

@Service
public class FeedbackService {

	@Autowired
	FeedbackRepository feedbackRepository;

	@Autowired
	EmailService emailService;

	public Feedback submitFeedback(Feedback feedback) {
		Assert.notNull(feedback, "Empty feedback provided");
		Assert.hasText(feedback.getFirstName(), "First name not provided");
		Assert.hasText(feedback.getLastName(), "Last name not provided");
		Assert.hasText(feedback.getEmailId(), "Email id not provided");
		Assert.hasText(feedback.getFeedback(), "Feedback not provided");
		Assert.notNull(feedback.getRating(), "Rating not provided");

		Feedback savedFeedback = feedbackRepository.save(feedback);
		emailService.sendFeedbackMessage(feedback.getFirstName(), feedback.getLastName(), feedback.getEmailId(),
				feedback.getFeedback());
		return savedFeedback;
	}

}
