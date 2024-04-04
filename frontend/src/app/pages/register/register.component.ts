import {Component, OnInit} from '@angular/core';
import {NzContentComponent, NzLayoutComponent} from "ng-zorro-antd/layout";
import {NzFlexDirective} from "ng-zorro-antd/flex";
import {NzButtonComponent} from "ng-zorro-antd/button";
import {NzColDirective, NzRowDirective} from "ng-zorro-antd/grid";
import {NzFormControlComponent, NzFormDirective, NzFormItemComponent} from "ng-zorro-antd/form";
import {NzInputDirective, NzInputGroupComponent} from "ng-zorro-antd/input";
import {
  FormBuilder,
  FormControl,
  FormGroup,
  ReactiveFormsModule,
  Validators
} from "@angular/forms";
import {FormValidationService} from "../../form-validation.service";

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [
    NzLayoutComponent,
    NzContentComponent,
    NzFlexDirective,
    NzButtonComponent,
    NzColDirective,
    NzFormControlComponent,
    NzFormDirective,
    NzFormItemComponent,
    NzInputDirective,
    NzInputGroupComponent,
    NzRowDirective,
    ReactiveFormsModule
  ],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css'
})
export class RegisterComponent implements OnInit{
  registerForm!: FormGroup<{
    userName: FormControl<string>;
    password: FormControl<string>;
    repeatPassword: FormControl<string>;
  }>;

  submitForm(): void {
    if (this.registerForm.valid) {
      console.log('submit', this.registerForm.value);
    } else {
      Object.values(this.registerForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
  }

  constructor(
    private formBuilder: FormBuilder,
    private formValidationService: FormValidationService,
  ) {
  }

  ngOnInit(): void {
    this.registerForm = this.formBuilder.group({
      userName: ['', [Validators.required]],
      password: ['', [
        Validators.required,
        this.formValidationService.getPasswordValidator()
      ]],
      repeatPassword: ['', [Validators.required]]
    },
      {validator: [
          this.formValidationService.getPasswordMatchValidator(
            'password',
            'repeatPassword'
          )
        ] }
    );
  }
}
