import {RouterModule, Routes} from "@angular/router";
import {MenuComponent} from "./menu.component";
import {NgModule} from "@angular/core";

export const MENU_ROUTES: Routes = [
  {
    path: '',
    component: MenuComponent,
    children: [
      {
        path: 'welcome',
        loadChildren: () => import('../welcome/welcome.routes').then(m => m.WELCOME_ROUTES)
      },
      {
        path: 'about',
        loadChildren: () => import('../about/about.routes').then(m => m.ABOUT_ROUTES)
      },
      {
        path: 'users',
        loadChildren: () => import('../users/users.routes').then(m => m.USERS_ROUTES)
      }
    ],
  }
];
