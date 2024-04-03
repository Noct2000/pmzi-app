import { Routes } from '@angular/router';

export const routes: Routes = [
  {
    path: '',
    pathMatch: 'full',
    redirectTo: '/menu'
  },
  {
    path: 'menu',
    loadChildren: () => import('./pages/menu/menu.routes').then(m => m.MENU_ROUTES)
  },
  {
    path: 'login',
    loadChildren: () => import('./pages/login/login.routes').then(m => m.LOGIN_ROUTES)
  }
];
