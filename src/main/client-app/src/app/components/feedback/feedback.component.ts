import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ToastrService } from 'ngx-toastr';
import { MasterService } from 'src/app/services/master.service';

@Component({
  selector: 'app-feedback',
  templateUrl: './feedback.component.html',
  styleUrls: ['./feedback.component.scss']
})
export class FeedbackComponent implements OnInit {

  @ViewChild('feedbackForm', {static: false}) feedbackForm: NgForm;

  feedbackFormData = {
    firstName: null,
    lastName: null,
    emailId: null,
    rating: null,
    feedback: null,
  };

  constructor(private masterService: MasterService, private toastrService: ToastrService) { }

  ngOnInit() {
  }

  submitFeedback() {
    if(this.feedbackForm.valid) {
      this.masterService.submitFeedback(this.feedbackFormData).subscribe(() => {
        this.toastrService.success("Feedback submitted sucessfully");
      }, err => {
        this.toastrService.error("Unable to submit feedback due to error: " + err.message);
      });
    }
  }

}
