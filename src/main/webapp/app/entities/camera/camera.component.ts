import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ICamera } from 'app/shared/model/camera.model';
import { CameraService } from './camera.service';
import { CameraDeleteDialogComponent } from './camera-delete-dialog.component';

@Component({
  selector: 'jhi-camera',
  templateUrl: './camera.component.html',
})
export class CameraComponent implements OnInit, OnDestroy {
  cameras?: ICamera[];
  eventSubscriber?: Subscription;

  constructor(protected cameraService: CameraService, protected eventManager: JhiEventManager, protected modalService: NgbModal) {}

  loadAll(): void {
    this.cameraService.query().subscribe((res: HttpResponse<ICamera[]>) => (this.cameras = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInCameras();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ICamera): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInCameras(): void {
    this.eventSubscriber = this.eventManager.subscribe('cameraListModification', () => this.loadAll());
  }

  delete(camera: ICamera): void {
    const modalRef = this.modalService.open(CameraDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.camera = camera;
  }
}
