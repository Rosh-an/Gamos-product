import { ParamProfileService } from './param-profile.service';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { ScrollToModule } from '@nicky-lenaers/ngx-scroll-to';
import { SearchService } from './search.service';
import { NgModule } from '@angular/core';
import { AppRoutingModule, routingComponents } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import {MatProgressBarModule} from '@angular/material/progress-bar';
import { ToastrModule } from 'ngx-toastr';
import { MaterialModule } from './material/material.module';

import { DialogueOverviewComponent } from './dialogue-overview/dialogue-overview.component';
import { MatCarouselComponent, MatCarouselSlideComponent } from '@ngmodule/material-carousel';
import { RxSpeechRecognitionService} from '@kamiazya/ngx-speech-recognition';
import { HttpClientModule } from '@angular/common/http';
import { DashboardServiceService} from './dashboard-service.service';
import { CarouselModule } from 'ngx-carousel-lib';
import { SocialLoginModule, AuthServiceConfig } from 'angularx-social-login';
import { GoogleLoginProvider, FacebookLoginProvider } from 'angularx-social-login';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { MatVideoModule } from 'mat-video';
import { UserProfileComponent } from './user-profile/user-profile.component';
import { UserProfileService } from './user-profile.service';
import { TruncateTextPipe } from './truncate-text.pipe';
import { SubscriptionComponent, DialogOverviewExampleDialog, DialogOverviewExampleDialog2 } from './subscription/subscription.component';
import { VideoBotComponent } from './video-bot/video-bot.component';
import { SocketComponent } from './socket/socket.component';
import { SocketService } from './socket/socket.service';
import { VideoService } from './video.service';
import { VideoComponent } from './video/video.component';
import { ServiceWorkerModule } from '@angular/service-worker';
import { environment } from '../environments/environment';
import { ShowProfileComponent } from './show-profile/show-profile.component';
import { GamosComponent } from './gamos/gamos.component';
import { StatComponent } from './stat/stat.component';
import { TopProfileComponent } from './top-profile/top-profile.component';
import { StoryComponent } from './story/story.component';
import { FlexLayoutModule } from '@angular/flex-layout';
import { RecommendationComponent } from './recommendation/recommendation.component';
import { HammerCardComponent } from './hammer-card/hammer-card.component';
import { HammerGestureConfig, HAMMER_GESTURE_CONFIG} from '@angular/platform-browser';
import { HammertimeDirective } from './hammertime.directive';
import * as Hammer from 'hammerjs';
import { SearchComponent } from './search/search.component';
import { RouterModule } from '@angular/router';
import { NgxSpinnerModule } from 'ngx-spinner';
import { MyvidComponent } from './myvid/myvid.component';
import { DatePipe } from '@angular/common';


export class MyHammerConfig extends HammerGestureConfig  {
  overrides =  {
      // override hammerjs default configuration
      swipe: { direction: Hammer.DIRECTION_ALL  }
  } as any;
}




const config = new AuthServiceConfig([
  {
    id: GoogleLoginProvider.PROVIDER_ID,
    provider: new GoogleLoginProvider('566957196290-bb1ffe9i27vo90mg7pfst6aika2oruni.apps.googleusercontent.com')
  },
  {
    id: FacebookLoginProvider.PROVIDER_ID,
    provider: new FacebookLoginProvider('382602839292569')
  }
]);

export function provideConfig() {
  return config;
}

@NgModule({
  declarations: [
    MyvidComponent,SearchComponent, HammertimeDirective, AppComponent, DialogueOverviewComponent,
                DialogOverviewExampleDialog, DialogOverviewExampleDialog2, DialogueOverviewComponent,
                routingComponents, HomeComponent, ProfileComponent,
                UserProfileComponent, TruncateTextPipe, VideoBotComponent, VideoComponent, SubscriptionComponent,
                ShowProfileComponent, GamosComponent, StatComponent, TopProfileComponent, StoryComponent,
                RecommendationComponent, HammerCardComponent, SocketComponent
              ],

  entryComponents: [
                    SocketComponent,MyvidComponent,SearchComponent, ShowProfileComponent, DialogOverviewExampleDialog,
                    DialogOverviewExampleDialog2, DialogueOverviewComponent, MatCarouselComponent,
                    MatCarouselSlideComponent, SubscriptionComponent
                  ],

  imports: [
            BrowserAnimationsModule,MaterialModule,NgxSpinnerModule,MatProgressBarModule, CarouselModule,  FlexLayoutModule, NgbModule,
            ScrollToModule.forRoot(),  MatVideoModule, AppRoutingModule,
            FormsModule, SocialLoginModule, ToastrModule.forRoot({ timeOut: 3000 }),
            AppRoutingModule, HttpClientModule, MatVideoModule,
            ServiceWorkerModule.register('ngsw-worker.js', { enabled: environment.production }), RouterModule
          ],

  providers: [{
    provide: HAMMER_GESTURE_CONFIG,
    useClass: MyHammerConfig
    },
    {
    provide: AuthServiceConfig,
    useFactory: provideConfig,
    },
    DashboardServiceService, SearchService, ParamProfileService, UserProfileService,
    RxSpeechRecognitionService, SocketService, VideoService, DatePipe],

    bootstrap: [AppComponent],
})
export class AppModule {}


