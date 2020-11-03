import { ComponentFixture, TestBed } from '@angular/core/testing';
import { of } from 'rxjs';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { SurveillanceTestModule } from '../../../test.module';
import { SurveillanceTaskComponent } from 'app/entities/surveillance-task/surveillance-task.component';
import { SurveillanceTaskService } from 'app/entities/surveillance-task/surveillance-task.service';
import { SurveillanceTask } from 'app/shared/model/surveillance-task.model';

describe('Component Tests', () => {
  describe('SurveillanceTask Management Component', () => {
    let comp: SurveillanceTaskComponent;
    let fixture: ComponentFixture<SurveillanceTaskComponent>;
    let service: SurveillanceTaskService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SurveillanceTestModule],
        declarations: [SurveillanceTaskComponent],
      })
        .overrideTemplate(SurveillanceTaskComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SurveillanceTaskComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SurveillanceTaskService);
    });

    it('Should call load all on init', () => {
      // GIVEN
      const headers = new HttpHeaders().append('link', 'link;link');
      spyOn(service, 'query').and.returnValue(
        of(
          new HttpResponse({
            body: [new SurveillanceTask(123)],
            headers,
          })
        )
      );

      // WHEN
      comp.ngOnInit();

      // THEN
      expect(service.query).toHaveBeenCalled();
      expect(comp.surveillanceTasks && comp.surveillanceTasks[0]).toEqual(jasmine.objectContaining({ id: 123 }));
    });
  });
});
