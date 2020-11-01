import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { NgJhipsterModule } from 'ng-jhipster';
import { InfiniteScrollModule } from 'ngx-infinite-scroll';
import { FontAwesomeModule } from '@fortawesome/angular-fontawesome';

@NgModule({
  exports: [FormsModule, CommonModule, NgbModule, NgJhipsterModule, InfiniteScrollModule, FontAwesomeModule, ReactiveFormsModule],
})
export class SurveillanceSharedLibsModule {}
