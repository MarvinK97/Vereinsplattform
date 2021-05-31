import { Component, OnInit } from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-add-event-dialog',
  templateUrl: './add-event-dialog.component.html',
  styleUrls: ['./add-event-dialog.component.css']
})
export class AddEventDialogComponent implements OnInit {

  form: FormGroup;

  constructor(private dialogRef: MatDialogRef<AddEventDialogComponent>) { }

  ngOnInit(): void {
    // Form Validation
    this.form = new FormGroup({
      start: new FormControl('', [
        Validators.required,
      ]),
      end: new FormControl('', [
        Validators.required,
      ]),
      title: new FormControl('', [
        Validators.required,
      ]),
      color: new FormControl('', [
        Validators.required,
      ]),
      allDay: new FormControl('', [
      ])
    });
  }

  submit(form): void {
    if (form.status === 'VALID') {
      this.dialogRef.close(JSON.stringify(form.value));
    }
  }

  close(): void {

  }

}
