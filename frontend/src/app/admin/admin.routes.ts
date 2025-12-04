import { Routes } from '@angular/router';
import { AdminLayoutComponent } from './layout/admin-layout.component';

export const adminRoutes: Routes = [
  {
    path: '',
    component: AdminLayoutComponent,
    children: [
      {
        path: '',
        loadComponent: () => import('./pages/dashboard/dashboard.component').then(m => m.DashboardComponent)
      },
      {
        path: 'products',
        loadComponent: () => import('./pages/manage-products/manage-products.component').then(m => m.ManageProductsComponent)
      },
      {
        path: 'products/new',
        loadComponent: () => import('./pages/manage-products/product-form/product-form.component').then(m => m.ProductFormComponent)
      },
      {
        path: 'products/edit/:id',
        loadComponent: () => import('./pages/manage-products/product-form/product-form.component').then(m => m.ProductFormComponent)
      },
      {
        path: 'orders',
        loadComponent: () => import('./pages/manage-orders/manage-orders.component').then(m => m.ManageOrdersComponent)
      },
      {
        path: 'users',
        loadComponent: () => import('./pages/manage-users/manage-users.component').then(m => m.ManageUsersComponent)
      }
    ]
  }
];
