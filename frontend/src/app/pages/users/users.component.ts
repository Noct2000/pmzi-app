import {Component, OnInit} from '@angular/core';
import {User} from "../../model/user";
import {NzTableComponent, NzTdAddOnComponent, NzThAddOnComponent, NzTrExpandDirective} from "ng-zorro-antd/table";
import {Column} from "./column";
import {NgForOf, NgIf} from "@angular/common";
import {NzButtonComponent} from "ng-zorro-antd/button";
import {UsersService} from "../../users.service";
import {ChangeRolesModalComponent} from "../../modal/change-roles-modal/change-roles-modal.component";
import {NzModalService} from "ng-zorro-antd/modal";

@Component({
  selector: 'app-users',
  standalone: true,
  imports: [
    NzTableComponent,
    NzThAddOnComponent,
    NgForOf,
    NzButtonComponent,
    NzTdAddOnComponent,
    NzTrExpandDirective,
    NgIf
  ],
  providers: [ChangeRolesModalComponent, NzModalService,],
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent implements OnInit{
  users: Array<User> = [];
  listOfColumns!: Array<Column>;
  expandSet = new Set<number>();

  constructor(
    private usersService: UsersService,
    private nzModalService: NzModalService,
  ) {
  }
  onExpandChange(id: number, checked: boolean): void {
    if (checked) {
      this.expandSet.add(id);
    } else {
      this.expandSet.delete(id);
    }
  }

    ngOnInit(): void {
      this.listOfColumns = [
        {title: 'Options'},
        {title: 'Id'},
        {title: 'Username'},
        {title: 'Roles'},
        {title: 'Blocked'},
        {title: 'Password'},
      ];

      // todo: replace with real service
      this.users = this.usersService.getUsers();
    }

  changeUserStatus(data: User, status: boolean) {
    this.users = this.usersService.changeUserStatus(data, status).sort((a,b) =>  a.id - b.id)
    console.log(`change user=${JSON.stringify(data)} status to ${status}`)
  }

  showChangeRolesModal(data: User) {
    this.showModal(data);
  }

  private showModal(user: User): void {
    this.nzModalService.closeAll();
    this.nzModalService.create({
      nzTitle: `Edit roles for user ${user.username}`,
      nzWidth: '40%',
      nzMaskClosable: false,
      nzContent: ChangeRolesModalComponent,
      nzClosable: false,
      nzData: {
        user: user
      },
    });
  }
}
