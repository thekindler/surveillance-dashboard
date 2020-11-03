import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';

@NgModule({
  imports: [
    RouterModule.forChild([
      {
        path: 'location',
        loadChildren: () => import('./location/location.module').then(m => m.SurveillanceLocationModule),
      },
      {
        path: 'camera',
        loadChildren: () => import('./camera/camera.module').then(m => m.SurveillanceCameraModule),
      },
      {
        path: 'surveillance-task',
        loadChildren: () => import('./surveillance-task/surveillance-task.module').then(m => m.SurveillanceSurveillanceTaskModule),
      },
      /* jhipster-needle-add-entity-route - JHipster will add entity modules routes here */
    ]),
  ],
})
export class SurveillanceEntityModule {}
