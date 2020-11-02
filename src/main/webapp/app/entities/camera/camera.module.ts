import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

import { SurveillanceSharedModule } from 'app/shared/shared.module';
import { CameraComponent } from './camera.component';
import { CameraDetailComponent } from './camera-detail.component';
import { CameraUpdateComponent } from './camera-update.component';
import { CameraDeleteDialogComponent } from './camera-delete-dialog.component';
import { cameraRoute } from './camera.route';

@NgModule({
  imports: [SurveillanceSharedModule, RouterModule.forChild(cameraRoute)],
  declarations: [CameraComponent, CameraDetailComponent, CameraUpdateComponent, CameraDeleteDialogComponent],
  entryComponents: [CameraDeleteDialogComponent],
})
export class SurveillanceCameraModule {}
