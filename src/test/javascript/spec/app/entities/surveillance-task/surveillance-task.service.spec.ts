import { TestBed, getTestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';
import { SurveillanceTaskService } from 'app/entities/surveillance-task/surveillance-task.service';
import { ISurveillanceTask, SurveillanceTask } from 'app/shared/model/surveillance-task.model';

describe('Service Tests', () => {
  describe('SurveillanceTask Service', () => {
    let injector: TestBed;
    let service: SurveillanceTaskService;
    let httpMock: HttpTestingController;
    let elemDefault: ISurveillanceTask;
    let expectedResult: ISurveillanceTask | ISurveillanceTask[] | boolean | null;

    beforeEach(() => {
      TestBed.configureTestingModule({
        imports: [HttpClientTestingModule],
      });
      expectedResult = null;
      injector = getTestBed();
      service = injector.get(SurveillanceTaskService);
      httpMock = injector.get(HttpTestingController);

      elemDefault = new SurveillanceTask(0, 'AAAAAAA', 'AAAAAAA', 'AAAAAAA');
    });

    describe('Service methods', () => {
      it('should find an element', () => {
        const returnedFromService = Object.assign({}, elemDefault);

        service.find(123).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(elemDefault);
      });

      it('should create a SurveillanceTask', () => {
        const returnedFromService = Object.assign(
          {
            id: 0,
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.create(new SurveillanceTask()).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'POST' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should update a SurveillanceTask', () => {
        const returnedFromService = Object.assign(
          {
            type: 'BBBBBB',
            modelName: 'BBBBBB',
            versionId: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.update(expected).subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'PUT' });
        req.flush(returnedFromService);
        expect(expectedResult).toMatchObject(expected);
      });

      it('should return a list of SurveillanceTask', () => {
        const returnedFromService = Object.assign(
          {
            type: 'BBBBBB',
            modelName: 'BBBBBB',
            versionId: 'BBBBBB',
          },
          elemDefault
        );

        const expected = Object.assign({}, returnedFromService);

        service.query().subscribe(resp => (expectedResult = resp.body));

        const req = httpMock.expectOne({ method: 'GET' });
        req.flush([returnedFromService]);
        httpMock.verify();
        expect(expectedResult).toContainEqual(expected);
      });

      it('should delete a SurveillanceTask', () => {
        service.delete(123).subscribe(resp => (expectedResult = resp.ok));

        const req = httpMock.expectOne({ method: 'DELETE' });
        req.flush({ status: 200 });
        expect(expectedResult);
      });
    });

    afterEach(() => {
      httpMock.verify();
    });
  });
});
