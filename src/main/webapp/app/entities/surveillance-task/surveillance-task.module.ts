import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SurveillanceSharedModule } from 'app/shared/shared.module';
import { SurveillanceTaskComponent } from './surveillance-task.component';
import { SurveillanceTaskDetailComponent } from './surveillance-task-detail.component';
import { SurveillanceTaskUpdateComponent } from './surveillance-task-update.component';
import { SurveillanceTaskDeleteDialogComponent } from './surveillance-task-delete-dialog.component';
import { surveillanceTaskRoute } from './surveillance-task.route';

@NgModule({
  imports: [SurveillanceSharedModule, RouterModule.forChild(surveillanceTaskRoute)],
  declarations: [
    SurveillanceTaskComponent,
    SurveillanceTaskDetailComponent,
    SurveillanceTaskUpdateComponent,
    SurveillanceTaskDeleteDialogComponent,
  ],
  entryComponents: [SurveillanceTaskDeleteDialogComponent],
})
export class SurveillanceSurveillanceTaskModule {}
