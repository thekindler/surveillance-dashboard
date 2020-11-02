import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ICamera, Camera } from 'app/shared/model/camera.model';
import { CameraService } from './camera.service';
import { CameraComponent } from './camera.component';
import { CameraDetailComponent } from './camera-detail.component';
import { CameraUpdateComponent } from './camera-update.component';

@Injectable({ providedIn: 'root' })
export class CameraResolve implements Resolve<ICamera> {
  constructor(private service: CameraService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ICamera> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((camera: HttpResponse<Camera>) => {
          if (camera.body) {
            return of(camera.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new Camera());
  }
}

export const cameraRoute: Routes = [
  {
    path: '',
    component: CameraComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Cameras',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: CameraDetailComponent,
    resolve: {
      camera: CameraResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Cameras',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: CameraUpdateComponent,
    resolve: {
      camera: CameraResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Cameras',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: CameraUpdateComponent,
    resolve: {
      camera: CameraResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'Cameras',
    },
    canActivate: [UserRouteAccessService],
  },
];
