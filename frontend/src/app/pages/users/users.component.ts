import {Component, OnInit} from '@angular/core';
import {User} from "../../model/user";
import {NzTableComponent, NzTdAddOnComponent, NzThAddOnComponent, NzTrExpandDirective} from "ng-zorro-antd/table";
import {Column} from "./column";
import {NgForOf, NgIf} from "@angular/common";
import {NzButtonComponent} from "ng-zorro-antd/button";

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
  templateUrl: './users.component.html',
  styleUrl: './users.component.css'
})
export class UsersComponent implements OnInit{
  users: Array<User> = [];
  listOfColumns!: Array<Column>;
  expandSet = new Set<number>();
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

      // todo: replace with service
      const username = 'test';
      for (let i = 0; i < 10; i++) {
        this.users.push({
          id: i,
          username: username + i,
          roles: ['USER', 'ADMIN'],
          blocked: false,
          password: i.toString() + '4t4t4t4t4tj4it4iuhtu4htu4huth4uthu4tuh4tu4htuh4t'
        })
      }
    }

  changeUserStatus(data: User, status: boolean) {
    const filteredUsers = this.users.filter(u => u.id !== data.id)
    const changedUser: User = {...data, blocked: status};
    this.users = [... filteredUsers, changedUser].sort((a,b) =>  a.id - b.id)
    console.log(`change user=${JSON.stringify(data)} status to ${status}`)
  }

  showChangeRolesModal(data: User) {
    console.log('showChangeRolesModal')
  }
}
