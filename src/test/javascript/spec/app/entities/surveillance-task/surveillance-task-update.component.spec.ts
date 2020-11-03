import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { FormBuilder } from '@angular/forms';
import { of } from 'rxjs';

import { SurveillanceTestModule } from '../../../test.module';
import { SurveillanceTaskUpdateComponent } from 'app/entities/surveillance-task/surveillance-task-update.component';
import { SurveillanceTaskService } from 'app/entities/surveillance-task/surveillance-task.service';
import { SurveillanceTask } from 'app/shared/model/surveillance-task.model';

describe('Component Tests', () => {
  describe('SurveillanceTask Management Update Component', () => {
    let comp: SurveillanceTaskUpdateComponent;
    let fixture: ComponentFixture<SurveillanceTaskUpdateComponent>;
    let service: SurveillanceTaskService;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [SurveillanceTestModule],
        declarations: [SurveillanceTaskUpdateComponent],
        providers: [FormBuilder],
      })
        .overrideTemplate(SurveillanceTaskUpdateComponent, '')
        .compileComponents();

      fixture = TestBed.createComponent(SurveillanceTaskUpdateComponent);
      comp = fixture.componentInstance;
      service = fixture.debugElement.injector.get(SurveillanceTaskService);
    });

    describe('save', () => {
      it('Should call update service on save for existing entity', fakeAsync(() => {
        // GIVEN
        const entity = new SurveillanceTask(123);
        spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.update).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));

      it('Should call create service on save for new entity', fakeAsync(() => {
        // GIVEN
        const entity = new SurveillanceTask();
        spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
        comp.updateForm(entity);
        // WHEN
        comp.save();
        tick(); // simulate async

        // THEN
        expect(service.create).toHaveBeenCalledWith(entity);
        expect(comp.isSaving).toEqual(false);
      }));
    });
  });
});
