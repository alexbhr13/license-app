import { Component } from '@angular/core';
import {
  AbstractControl,
  Form,
  FormBuilder, FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  ValidatorFn,
  Validators
} from "@angular/forms";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatIcon} from "@angular/material/icon";
import {MatIconButton} from "@angular/material/button";
import {MatInput} from "@angular/material/input";
import {RestapiService} from "../../services/restapi.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-forgot-password',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormField,
    MatIcon,
    MatIconButton,
    MatInput,
    MatLabel
  ],
  templateUrl: './forgot-password.component.html',
  styleUrl: './forgot-password.component.css'
})
export class ForgotPasswordComponent {

  constructor(private formBuilder: FormBuilder,
              private restApiService: RestapiService,
              private router: Router,
              private route: ActivatedRoute
              ) {
    this.forgotPasswordForm = this.formBuilder.group({
      oldPassword: ['', [Validators.required]],
      newPassword: ['', [Validators.required,
        Validators.minLength(8),
        Validators.pattern("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,64}$")]],
      confirmNewPassword: ['', [Validators.required, this.passwordMatchValidator]]
    }, {validator: this.passwordMatchValidator})
  }

  forgotPasswordForm: FormGroup;
  onSubmit() {
    let token = '';
    this.route.queryParams.subscribe(params => {
      token = params['token'];
      console.log(token); // Use the token as needed
    });
    const body={
      newPassword: this.forgotPasswordForm.get('newPassword')?.value
    };
    this.restApiService.pwdResReset(token,body)
      .subscribe(
        response => {
          console.log(response)
        }
      );
  }

  hide =  true;
  clickEvent(event: MouseEvent) {
    this.hide = !this.hide;
    event.stopPropagation();
  }

  passwordMatchValidator: ValidatorFn = (formGroup: AbstractControl): ValidationErrors | null => {
    const password = formGroup.get('password')?.value;
    const confirmPassword = formGroup.get('confirmPassword')?.value;
    console.log(password, confirmPassword);
    if (password && confirmPassword && password !== confirmPassword) {
      return { match: true };
    }
    return null;
  }

}
