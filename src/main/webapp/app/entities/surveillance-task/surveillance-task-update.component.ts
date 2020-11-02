import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ISurveillanceTask, SurveillanceTask } from 'app/shared/model/surveillance-task.model';
import { SurveillanceTaskService } from './surveillance-task.service';

@Component({
  selector: 'jhi-surveillance-task-update',
  templateUrl: './surveillance-task-update.component.html',
})
export class SurveillanceTaskUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    type: [],
    modename: [],
    version: [],
  });

  constructor(
    protected surveillanceTaskService: SurveillanceTaskService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ surveillanceTask }) => {
      this.updateForm(surveillanceTask);
    });
  }

  updateForm(surveillanceTask: ISurveillanceTask): void {
    this.editForm.patchValue({
      id: surveillanceTask.id,
      type: surveillanceTask.type,
      modename: surveillanceTask.modename,
      version: surveillanceTask.version,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const surveillanceTask = this.createFromForm();
    if (surveillanceTask.id !== undefined) {
      this.subscribeToSaveResponse(this.surveillanceTaskService.update(surveillanceTask));
    } else {
      this.subscribeToSaveResponse(this.surveillanceTaskService.create(surveillanceTask));
    }
  }

  private createFromForm(): ISurveillanceTask {
    return {
      ...new SurveillanceTask(),
      id: this.editForm.get(['id'])!.value,
      type: this.editForm.get(['type'])!.value,
      modename: this.editForm.get(['modename'])!.value,
      version: this.editForm.get(['version'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ISurveillanceTask>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
