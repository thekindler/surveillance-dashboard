import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ISurveillanceTask } from 'app/shared/model/surveillance-task.model';

@Component({
  selector: 'jhi-surveillance-task-detail',
  templateUrl: './surveillance-task-detail.component.html',
})
export class SurveillanceTaskDetailComponent implements OnInit {
  surveillanceTask: ISurveillanceTask | null = null;

  constructor(protected activatedRoute: ActivatedRoute) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ surveillanceTask }) => (this.surveillanceTask = surveillanceTask));
  }

  previousState(): void {
    window.history.back();
  }
}
