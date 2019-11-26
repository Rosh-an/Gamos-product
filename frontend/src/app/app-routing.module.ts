import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


import { AppComponent } from './app.component';
import { HomeComponent } from './home/home.component';
import { DashboardComponent } from './dashboard/dashboard.component';
import { ProfileComponent } from './profile/profile.component';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { AuthGuardService as AuthGuard} from './auth-guard-service';
import { SubscriptionComponent } from './subscription/subscription.component';
import { VideoBotComponent } from './video-bot/video-bot.component';
import { SocketComponent } from './socket/socket.component';
import { VideoComponent } from './video/video.component';
import { ShowProfileComponent } from './show-profile/show-profile.component';

const routes: Routes = [
  {path: 'home', component: HomeComponent,
  children: [
    {
      path: 'login',
      loadChildren: () => import('./login/login.module').then(m => m.LoginModule)
    }
  ]
  },
  {path: '', component: HomeComponent,
  children: [
    {
      path: 'login',
      loadChildren: () => import('./login/login.module').then(m => m.LoginModule)
    }
  ]
  },
  {path: '#', component: HomeComponent,
  children: [
    {
      path: 'login',
      loadChildren: () => import('./login/login.module').then(m => m.LoginModule)
    }
  ]
  },
  {path: 'dashboard', component: DashboardComponent, canActivate: [AuthGuard]},
  {path: 'profile', component: ProfileComponent},
  {path: 'user-profile', component: UserProfileComponent, canActivate: [AuthGuard]},
  {path: 'chat', component: SocketComponent},
  {path: 'settings', component: SubscriptionComponent, canActivate: [AuthGuard]},
  // {path: 'animation', component: HammerCardComponent},
  {path: 'show-profile', component: ShowProfileComponent, canActivate: [AuthGuard]},
  {path: 'video', component: VideoComponent},
  {path: 'video-bot', component: VideoBotComponent}
];


@NgModule({
  imports: [RouterModule.forRoot(routes, {useHash: true})],
  exports: [RouterModule]
})
export class AppRoutingModule {}
export const routingComponents = [AppComponent, DashboardComponent, ProfileComponent];
