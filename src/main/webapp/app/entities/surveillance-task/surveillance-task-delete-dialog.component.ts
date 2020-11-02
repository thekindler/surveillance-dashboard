import { Component } from '@angular/core';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ISurveillanceTask } from 'app/shared/model/surveillance-task.model';
import { SurveillanceTaskService } from './surveillance-task.service';

@Component({
  templateUrl: './surveillance-task-delete-dialog.component.html',
})
export class SurveillanceTaskDeleteDialogComponent {
  surveillanceTask?: ISurveillanceTask;

  constructor(
    protected surveillanceTaskService: SurveillanceTaskService,
    public activeModal: NgbActiveModal,
    protected eventManager: JhiEventManager
  ) {}

  cancel(): void {
    this.activeModal.dismiss();
  }

  confirmDelete(id: number): void {
    this.surveillanceTaskService.delete(id).subscribe(() => {
      this.eventManager.broadcast('surveillanceTaskListModification');
      this.activeModal.close();
    });
  }
}
