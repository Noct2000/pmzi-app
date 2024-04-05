import { Injectable } from '@angular/core';
import {User} from "./model/user";

@Injectable({
  providedIn: 'root'
})
export class UsersService {

  private createData = () => {
    const res: User[] = []
    for (let i = 0; i < 10; i++) {
      res.push({
        id: i,
        username: 'username' + i,
        roles: ['USER'],
        blocked: false,
        password: i.toString() + '4t4t4t4t4tj4it4iuhtu4htu4huth4uthu4tuh4tu4htuh4t'
      })
    }
    return res;
  };

  private users: Array<User> = this.createData();

  getUsers(): User[] {
    return this.users;
  }

  changeUserStatus(user: User, status: boolean): User[] {
    const filteredUsers = this.users.filter(u => u.id !== user.id)
    const changedUser: User = {...user, blocked: status};
    this.users = [... filteredUsers, changedUser];
    return this.users;
  }

  changeUserRoles(userId: number, roles: string[]): User[] {
    console.log(`userId: ${userId}, roles: ${roles}`)
    const filteredUsers = this.users.filter(u => u.id !== userId)
    const user = this.users.find(u => u.id === userId);
    console.log(`user: ${JSON.stringify(user)}`)
    if (user) {
      const changedUser: User = {...user, roles: [... roles]};
      console.log(`changedUser: ${JSON.stringify(changedUser)}`)
      this.users = [... filteredUsers, changedUser];
      console.log(`this.users: ${JSON.stringify(this.users)}`)
    }
    return this.users;
  }
}
