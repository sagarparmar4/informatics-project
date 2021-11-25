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

	public Feedback submitFeedback(Feedback feedback) {
		Assert.notNull(feedback, "Empty feedback provided");
		Assert.hasText(feedback.getFirstName(), "Empty feedback provided");
		Assert.hasText(feedback.getLastName(), "Empty feedback provided");
		Assert.hasText(feedback.getEmailId(), "Empty feedback provided");
		Assert.hasText(feedback.getFeedback(), "Empty feedback provided");
		Assert.notNull(feedback.getRating(), "Empty feedback provided");

		return feedbackRepository.save(feedback);
	}

}
