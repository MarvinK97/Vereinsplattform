import {Component, OnInit} from '@angular/core';
import { Validators, FormGroup, FormControl} from '@angular/forms';
import { MatDialogRef } from '@angular/material/dialog';

@Component({
  selector: 'app-add-club-dialog',
  templateUrl: './add-club-dialog.component.html',
  styleUrls: ['./add-club-dialog.component.css']
})
export class AddClubDialogComponent implements OnInit {

  form: FormGroup;

  constructor(private dialogRef: MatDialogRef<AddClubDialogComponent>){  }


  ngOnInit(): void {
    // Form Validation
    this.form = new FormGroup({
      name: new FormControl('', [
        Validators.required,
      ]),
      street: new FormControl('', [
        Validators.required,
      ]),
      zipcode: new FormControl('', [
        Validators.required,
        Validators.maxLength(5)
      ]),
      city: new FormControl('', [
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

  }

}
