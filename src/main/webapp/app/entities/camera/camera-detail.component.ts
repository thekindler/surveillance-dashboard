import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICamera } from 'app/shared/model/camera.model';

@Component({
  selector: 'jhi-camera-detail',
  templateUrl: './camera-detail.component.html',
})
export class CameraDetailComponent implements OnInit {
  camera: ICamera | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ camera }) => (this.camera = camera));
  }

  previousState(): void {
    window.history.back();
  }
}
