import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AdminLayoutComponent } from './layouts/admin-layout/admin-layout.component';
import { AuthLayoutComponent } from './layouts/auth-layout/auth-layout.component';
import { UserRegistrationComponent } from './pages/user-registration/user-registration.component';
import { AuthGuard } from './provider/services/auth/auth.guard';

const routes: Routes = [
  {
    path: 'app',
    component: AuthLayoutComponent,
    children: [
      {
        canActivateChild: [AuthGuard],
        path: '',
        loadChildren: () => import('src/app/layouts/auth-layout/auth-layout.module').then(m => m.AuthLayoutModule) 
      }
    ]
  },
  { path: '', redirectTo: '/tabs/home', pathMatch: 'full'},
  { path: 'register', component: UserRegistrationComponent },
  { path: 'tabs',
  component: AdminLayoutComponent,
  children: [
    {  canActivateChild: [AuthGuard], canLoad: [AuthGuard], path: 'orders', loadChildren: () => import('src/app/pages/order-detail/order-detail.module').then(m => m.OrderDetailModule)},
    {  canActivateChild: [AuthGuard], canLoad: [AuthGuard], path: 'bmg', loadChildren: () => import('src/app/pages/bmg-for-your/bmg-for-your.module').then(m => m.BmgForYouModule)},
    {  canActivateChild: [AuthGuard], canLoad: [AuthGuard], path: 'dashboard', loadChildren: () => import('src/app/pages/dashboard/dashboard.module').then(m => m.DashboardModule)},
    {  canActivateChild: [AuthGuard], canLoad: [AuthGuard], path: 'home', loadChildren: () => import('src/app/pages/home/home.module').then(m => m.HomeModule)},
    {  canActivateChild: [AuthGuard], canLoad: [AuthGuard], path: 'chat', loadChildren: () => import('src/app/pages/chat/chat.module').then(m => m.ChatModule)},
    {  canActivateChild: [AuthGuard], canLoad: [AuthGuard], path: 'products/product-list', loadChildren: () => import('src/app/pages/product-list/product-list.module').then(m => m.ProductListModule)},
  ]
}
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [RouterModule]
})
export class AppRoutingModule {}
