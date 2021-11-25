package com.projects.informatics.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projects.informatics.entities.Feedback;
import com.projects.informatics.services.FeedbackService;

@RestController
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	@PostMapping(value = "/submitFeedback")
	public Feedback submitFeedback(@RequestBody Feedback feedback) {
		return feedbackService.submitFeedback(feedback);
	}

}
