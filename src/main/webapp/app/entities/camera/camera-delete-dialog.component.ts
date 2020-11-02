import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICamera } from 'app/shared/model/camera.model';
import { CameraService } from './camera.service';

@Component({
  templateUrl: './camera-delete-dialog.component.html',
})
export class CameraDeleteDialogComponent {
  camera?: ICamera;

  constructor(protected cameraService: CameraService, public activeModal: NgbActiveModal, protected eventManager: JhiEventManager) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.cameraService.delete(id).subscribe(() => {
      this.eventManager.broadcast('cameraListModification');
      this.activeModal.close();
    });
  }
}
