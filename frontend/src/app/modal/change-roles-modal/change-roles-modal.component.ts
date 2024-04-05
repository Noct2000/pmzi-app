import {Component, inject, Input, OnInit} from '@angular/core';
import {RolesService} from "../../roles.service";
import {FormsModule, ReactiveFormsModule, UntypedFormBuilder, UntypedFormGroup, Validators} from "@angular/forms";
import {NZ_MODAL_DATA, NzModalFooterDirective, NzModalRef} from "ng-zorro-antd/modal";
import {User} from "../../model/user";
import {NzFormControlComponent, NzFormDirective, NzFormItemComponent, NzFormLabelComponent} from "ng-zorro-antd/form";
import {NzColDirective} from "ng-zorro-antd/grid";
import {NzButtonComponent} from "ng-zorro-antd/button";
import {NzOptionComponent, NzSelectComponent} from "ng-zorro-antd/select";
import {CommonModule} from "@angular/common";
import {UsersService} from "../../users.service";
import {NzMessageService} from "ng-zorro-antd/message";

@Component({
  selector: 'app-change-roles-modal',
  templateUrl: './change-roles-modal.component.html',
  styleUrl: './change-roles-modal.component.css',
  standalone: true,
  imports: [
    NzFormControlComponent,
    NzFormItemComponent,
    NzFormLabelComponent,
    NzColDirective,
    NzButtonComponent,
    NzModalFooterDirective,
    ReactiveFormsModule,
    NzSelectComponent,
    FormsModule,
    NzOptionComponent,
    NzFormDirective,
    CommonModule,
  ],
})
export class ChangeRolesModalComponent implements OnInit {
  listOfOption!: string[];
  changeRolesForm!: UntypedFormGroup;

  readonly nzModalData: {user: User} = inject(NZ_MODAL_DATA);

  constructor(
    private rolesService: RolesService,
    private formBuilder: UntypedFormBuilder,
    private usersService: UsersService,
    private nzMessageService: NzMessageService,
    private nzModalRef: NzModalRef,
  ) { }

  ngOnInit(): void {
    this.listOfOption = this.rolesService.getRoles().map(
      role => role.roleName
    );
    this.changeRolesForm = this.formBuilder.group({
      roleNames: [this.nzModalData.user.roles, [Validators.required]],
    });
  }

  handleOk(): void {
    if (this.changeRolesForm.valid) {
      const { roleNames } = this.changeRolesForm.value;
      this.usersService.changeUserRoles(this.nzModalData.user.id, roleNames);
      this.nzMessageService.success(`Successful role changes for user ${this.nzModalData.user.username}`);
    } else {
      Object.values(this.changeRolesForm.controls).forEach(control => {
        if (control.invalid) {
          control.markAsDirty();
          control.updateValueAndValidity({ onlySelf: true });
        }
      });
    }
    this.nzModalRef.destroy();
  }

  handleCancel(): void {
    this.nzModalRef.destroy();
  }
}
