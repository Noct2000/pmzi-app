import { Injectable } from '@angular/core';
import {Role} from "./model/role";

@Injectable({
  providedIn: 'root'
})
export class RolesService {
  private roles: Role[] = [
    {id: 1, roleName: 'ADMIN'},
    {id: 2, roleName: 'USER'},
  ];

  getRoles(): Role[] {
    return [... this.roles];
  }
}
