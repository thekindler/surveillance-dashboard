import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, Routes, Router } from '@angular/router';
import { Observable, of, EMPTY } from 'rxjs';
import { flatMap } from 'rxjs/operators';

import { Authority } from 'app/shared/constants/authority.constants';
import { UserRouteAccessService } from 'app/core/auth/user-route-access-service';
import { ISurveillanceTask, SurveillanceTask } from 'app/shared/model/surveillance-task.model';
import { SurveillanceTaskService } from './surveillance-task.service';
import { SurveillanceTaskComponent } from './surveillance-task.component';
import { SurveillanceTaskDetailComponent } from './surveillance-task-detail.component';
import { SurveillanceTaskUpdateComponent } from './surveillance-task-update.component';

@Injectable({ providedIn: 'root' })
export class SurveillanceTaskResolve implements Resolve<ISurveillanceTask> {
  constructor(private service: SurveillanceTaskService, private router: Router) {}

  resolve(route: ActivatedRouteSnapshot): Observable<ISurveillanceTask> | Observable<never> {
    const id = route.params['id'];
    if (id) {
      return this.service.find(id).pipe(
        flatMap((surveillanceTask: HttpResponse<SurveillanceTask>) => {
          if (surveillanceTask.body) {
            return of(surveillanceTask.body);
          } else {
            this.router.navigate(['404']);
            return EMPTY;
          }
        })
      );
    }
    return of(new SurveillanceTask());
  }
}

export const surveillanceTaskRoute: Routes = [
  {
    path: '',
    component: SurveillanceTaskComponent,
    data: {
      authorities: [Authority.USER],
      pageTitle: 'SurveillanceTasks',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/view',
    component: SurveillanceTaskDetailComponent,
    resolve: {
      surveillanceTask: SurveillanceTaskResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'SurveillanceTasks',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: 'new',
    component: SurveillanceTaskUpdateComponent,
    resolve: {
      surveillanceTask: SurveillanceTaskResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'SurveillanceTasks',
    },
    canActivate: [UserRouteAccessService],
  },
  {
    path: ':id/edit',
    component: SurveillanceTaskUpdateComponent,
    resolve: {
      surveillanceTask: SurveillanceTaskResolve,
    },
    data: {
      authorities: [Authority.USER],
      pageTitle: 'SurveillanceTasks',
    },
    canActivate: [UserRouteAccessService],
  },
];
