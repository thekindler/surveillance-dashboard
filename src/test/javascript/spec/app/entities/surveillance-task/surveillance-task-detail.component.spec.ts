import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { SurveillanceTestModule } from '../../../test.module';
import { SurveillanceTaskDetailComponent } from 'app/entities/surveillance-task/surveillance-task-detail.component';
import { SurveillanceTask } from 'app/shared/model/surveillance-task.model';

describe('Component Tests', () => {
  describe('SurveillanceTask Management Detail Component', () => {
    let comp: SurveillanceTaskDetailComponent;
    let fixture: ComponentFixture<SurveillanceTaskDetailComponent>;
    const route = ({ data: of({ surveillanceTask: new SurveillanceTask(123) }) } as any) as ActivatedRoute;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SurveillanceTestModule],
        declarations: [SurveillanceTaskDetailComponent],
        providers: [{ provide: ActivatedRoute, useValue: route }],
      })
        .overrideTemplate(SurveillanceTaskDetailComponent, '')
        .compileComponents();
      fixture = TestBed.createComponent(SurveillanceTaskDetailComponent);
      comp = fixture.componentInstance;
    });

    describe('OnInit', () => {
      it('Should load surveillanceTask on init', () => {
        // WHEN
        comp.ngOnInit();

        // THEN
        expect(comp.surveillanceTask).toEqual(jasmine.objectContaining({ id: 123 }));
      });
    });
  });
});
