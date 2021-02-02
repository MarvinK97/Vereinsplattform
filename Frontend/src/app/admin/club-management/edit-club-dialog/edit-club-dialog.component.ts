import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {MAT_DIALOG_DATA, MatDialogRef} from '@angular/material/dialog';

@Component({
  selector: 'app-edit-club-dialog',
  templateUrl: './edit-club-dialog.component.html',
  styleUrls: ['./edit-club-dialog.component.css']
})
export class EditClubDialogComponent implements OnInit {

  form: FormGroup;

  constructor(private dialogRef: MatDialogRef<EditClubDialogComponent>,
              @Inject(MAT_DIALOG_DATA) private data) { }

  ngOnInit(): void {
    // Form Validation
    this.form = new FormGroup({
      id: new FormControl(this.data ? this.data.id : '', [
        Validators.required,
      ]),
      name: new FormControl(this.data ? this.data.name : '', [
        Validators.required,
      ]),
      street: new FormControl(this.data ? this.data.street : '', [
        Validators.required,
      ]),
      zipcode: new FormControl(this.data ? this.data.zipcode : '', [
        Validators.required,
        Validators.maxLength(5)
      ]),
      city: new FormControl(this.data ? this.data.city : '', [
        Validators.required,
      ])
    });
  }

  submit(form): void {
    if (form.status === 'VALID') {
      this.dialogRef.close(JSON.stringify(form.value));
    }
  }

  close(): void {
    this.dialogRef.close(false);
  }

  delete(): void {
    this.dialogRef.close('delete');
  }
}
