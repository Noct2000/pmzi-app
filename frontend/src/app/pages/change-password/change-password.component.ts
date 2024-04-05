import {Component, OnInit} from '@angular/core';
import {FormBuilder, FormControl, FormGroup, FormsModule, ReactiveFormsModule, Validators} from "@angular/forms";
import {NzButtonComponent} from "ng-zorro-antd/button";
import {NzColDirective, NzRowDirective} from "ng-zorro-antd/grid";
import {NzContentComponent, NzLayoutComponent} from "ng-zorro-antd/layout";
import {NzFlexDirective} from "ng-zorro-antd/flex";
import {NzFormControlComponent, NzFormDirective, NzFormItemComponent} from "ng-zorro-antd/form";
import {NzInputDirective, NzInputGroupComponent} from "ng-zorro-antd/input";
import {NzTypographyComponent} from "ng-zorro-antd/typography";
import {FormValidationService} from "../../form-validation.service";

@Component({
  selector: 'app-change-password',
  standalone: true,
  imports: [
    FormsModule,
    NzButtonComponent,
    NzColDirective,
    NzContentComponent,
    NzFlexDirective,
    NzFormControlComponent,
    NzFormDirective,
    NzFormItemComponent,
    NzInputDirective,
    NzInputGroupComponent,
    NzLayoutComponent,
    NzRowDirective,
    ReactiveFormsModule,
    NzTypographyComponent
  ],
  templateUrl: './change-password.component.html',
  styleUrl: './change-password.component.css'
})
export class ChangePasswordComponent implements OnInit {
  changePasswordForm!: FormGroup<{
    password: FormControl<string>;
    repeatPassword: FormControl<string>;
  }>;

  submitForm(): void {
    if (this.changePasswordForm.valid) {
      console.log('submit', this.changePasswordForm.value);
    } else {
      Object.values(this.changePasswordForm.controls).forEach(control => {
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
    this.changePasswordForm = this.formBuilder.group({
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
