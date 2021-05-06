import {Component, Inject, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from "@angular/forms";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-edit-user-dialog',
  templateUrl: './edit-user-dialog.component.html',
  styleUrls: ['./edit-user-dialog.component.css']
})
export class EditUserDialogComponent implements OnInit {

  form: FormGroup;

  constructor(private dialogRef: MatDialogRef<EditUserDialogComponent>,
              @Inject(MAT_DIALOG_DATA) private data) { }

  ngOnInit(): void {
    // Form Validation
    this.form = new FormGroup({
      id: new FormControl(this.data ? this.data.id : '', [
        Validators.required,
      ]),
      username: new FormControl(this.data ? this.data.email : '', [
        Validators.required,
      ]),
      email: new FormControl(this.data ? this.data.username : '', [
        Validators.required,
      ]),
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
