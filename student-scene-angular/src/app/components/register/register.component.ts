import { Component } from '@angular/core';
import {
  AbstractControl, AsyncValidator, AsyncValidatorFn,
  FormBuilder, FormGroup,
  ReactiveFormsModule,
  ValidationErrors,
  ValidatorFn,
  Validators
} from "@angular/forms";
import {RestapiService} from "../../services/restapi.service";
import {finalize, map} from "rxjs";
import {Router} from "@angular/router";
import {HttpResponse} from "@angular/common/http";
import {MatFormField, MatLabel} from "@angular/material/form-field";
import {MatInput} from "@angular/material/input";
import {MatIconButton} from "@angular/material/button";
import {MatIcon} from "@angular/material/icon";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    MatFormField,
    MatInput,
    MatLabel,
    MatIconButton,
    MatIcon],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent {
  constructor(private formBuilder: FormBuilder,
              private restApiService: RestapiService,
              private router: Router) {
    this.registerForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required,
        Validators.minLength(8),
        Validators.pattern("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,64}$")]],
      confirmPassword: ['', [Validators.required, this.passwordMatchValidator]],
      isAdmin: [false]
    }, {validator: this.passwordMatchValidator});
  }

  registerForm: FormGroup;

  onSubmit() {
    const body = {
      email: this.registerForm.get('email')?.value,
      password: this.registerForm.get('password')?.value,
      isAdmin: this.registerForm.get('isAdmin')?.value
    };
    this.restApiService.register(body)
      .pipe(
        map((response: HttpResponse<any>) => {
          console.log('HTTP Response:', response);  // Debug log
          return response.status === 200 && response.body.message === 'User created';
        })).subscribe(
      (isRegistered: boolean) => {
        if (isRegistered) {
          console.log('Registration successful');
          // Handle success (e.g., show a success message, navigate to another page)
          this.router.navigate(['/confirm']).then();
        } else {
          console.log('Registration failed');
          // Handle failure (e.g., show an error message)
        }
      },
      (error) => {
        console.error('An error occurred:', error);
        // Handle error (e.g., show a generic error message)
      }
    );
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


  hide = true;
  clickEvent(event: MouseEvent) {
    this.hide = !this.hide;
    event.stopPropagation();
  }
}
