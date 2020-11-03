import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';

import './vendor';
import { SurveillanceSharedModule } from 'app/shared/shared.module';
import { SurveillanceCoreModule } from 'app/core/core.module';
import { SurveillanceAppRoutingModule } from './app-routing.module';
import { SurveillanceHomeModule } from './home/home.module';
import { SurveillanceEntityModule } from './entities/entity.module';
// jhipster-needle-angular-add-module-import JHipster will add new module here
import { MainComponent } from './layouts/main/main.component';
import { NavbarComponent } from './layouts/navbar/navbar.component';
import { FooterComponent } from './layouts/footer/footer.component';
import { PageRibbonComponent } from './layouts/profiles/page-ribbon.component';
import { ErrorComponent } from './layouts/error/error.component';
import { LoggerModule, NgxLoggerLevel } from 'ngx-logger';
import { DashboardComponent } from './dashboard/dashboard.component';

@NgModule({
  imports: [
    BrowserModule,
    SurveillanceSharedModule,
    SurveillanceCoreModule,
    SurveillanceHomeModule,
    // jhipster-needle-angular-add-module JHipster will add new module here
    SurveillanceEntityModule,
    SurveillanceAppRoutingModule,
    LoggerModule.forRoot({
      serverLoggingUrl: '/api/logs',
      level: NgxLoggerLevel.TRACE,
      serverLogLevel: NgxLoggerLevel.ERROR,
      disableConsoleLogging: false,
    }),
  ],
  declarations: [MainComponent, NavbarComponent, ErrorComponent, PageRibbonComponent, FooterComponent, DashboardComponent],
  bootstrap: [MainComponent],
})
export class SurveillanceAppModule {}
