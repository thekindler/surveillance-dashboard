import { Component, OnInit, OnDestroy } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Subscription } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';
import { NgbModal } from '@ng-bootstrap/ng-bootstrap';

import { ISurveillanceTask } from 'app/shared/model/surveillance-task.model';
import { SurveillanceTaskService } from './surveillance-task.service';
import { SurveillanceTaskDeleteDialogComponent } from './surveillance-task-delete-dialog.component';

@Component({
  selector: 'jhi-surveillance-task',
  templateUrl: './surveillance-task.component.html',
})
export class SurveillanceTaskComponent implements OnInit, OnDestroy {
  surveillanceTasks?: ISurveillanceTask[];
  eventSubscriber?: Subscription;

  constructor(
    protected surveillanceTaskService: SurveillanceTaskService,
    protected eventManager: JhiEventManager,
    protected modalService: NgbModal
  ) {}

  loadAll(): void {
    this.surveillanceTaskService.query().subscribe((res: HttpResponse<ISurveillanceTask[]>) => (this.surveillanceTasks = res.body || []));
  }

  ngOnInit(): void {
    this.loadAll();
    this.registerChangeInSurveillanceTasks();
  }

  ngOnDestroy(): void {
    if (this.eventSubscriber) {
      this.eventManager.destroy(this.eventSubscriber);
    }
  }

  trackId(index: number, item: ISurveillanceTask): number {
    // eslint-disable-next-line @typescript-eslint/no-unnecessary-type-assertion
    return item.id!;
  }

  registerChangeInSurveillanceTasks(): void {
    this.eventSubscriber = this.eventManager.subscribe('surveillanceTaskListModification', () => this.loadAll());
  }

  delete(surveillanceTask: ISurveillanceTask): void {
    const modalRef = this.modalService.open(SurveillanceTaskDeleteDialogComponent, { size: 'lg', backdrop: 'static' });
    modalRef.componentInstance.surveillanceTask = surveillanceTask;
  }
}
