import { Component } from '@angular/core';
import {FormBuilder, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {RestapiService} from "../../services/restapi.service";
import {Router} from "@angular/router";
import {map} from "rxjs";

@Component({
  selector: 'app-forgot-password-request',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    FormsModule,
    MatFormField,
    MatInput,
    MatLabel
  ],
  templateUrl: './forgot-password-request.component.html',
  styleUrl: './forgot-password-request.component.css'
})
export class ForgotPasswordRequestComponent {

  constructor(private formBuilder: FormBuilder, private restApiService: RestapiService, private router: Router) {
    this.pwdResRequestForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]]
    });
  }

  pwdResRequestForm: FormGroup;


  onSubmit() {
    const email = this.pwdResRequestForm.get('email')?.value;
    this.restApiService.pwdResRequest(email)
      .subscribe(
        response => {
          console.log(response) //response parsing error
        }
      )

  }
}
