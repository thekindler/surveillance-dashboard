import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { ICamera, Camera } from 'app/shared/model/camera.model';
import { CameraService } from './camera.service';

@Component({
  selector: 'jhi-camera-update',
  templateUrl: './camera-update.component.html',
})
export class CameraUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    make: [],
    location: [],
    surveillanceTask: [],
    ip: [],
    fps: [],
  });

  constructor(protected cameraService: CameraService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ camera }) => {
      this.updateForm(camera);
    });
  }

  updateForm(camera: ICamera): void {
    this.editForm.patchValue({
      id: camera.id,
      make: camera.make,
      location: camera.location,
      surveillanceTask: camera.surveillanceTask,
      ip: camera.ip,
      fps: camera.fps,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const camera = this.createFromForm();
    if (camera.id !== undefined) {
      this.subscribeToSaveResponse(this.cameraService.update(camera));
    } else {
      this.subscribeToSaveResponse(this.cameraService.create(camera));
    }
  }

  private createFromForm(): ICamera {
    return {
      ...new Camera(),
      id: this.editForm.get(['id'])!.value,
      make: this.editForm.get(['make'])!.value,
      location: this.editForm.get(['location'])!.value,
      surveillanceTask: this.editForm.get(['surveillanceTask'])!.value,
      ip: this.editForm.get(['ip'])!.value,
      fps: this.editForm.get(['fps'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<ICamera>>): void {
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
