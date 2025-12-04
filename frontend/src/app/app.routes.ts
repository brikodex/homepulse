import { Routes } from '@angular/router';
import { frontRoutes } from './front/front.routes';
import { adminRoutes } from './admin/admin.routes';

export const routes: Routes = [
  {
    path: '',
    children: frontRoutes,
  },
  {
    path: 'admin',
    children: adminRoutes,
  },
];
