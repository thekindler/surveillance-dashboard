import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared/util/request-util';
import { ISurveillanceTask } from 'app/shared/model/surveillance-task.model';

type EntityResponseType = HttpResponse<ISurveillanceTask>;
type EntityArrayResponseType = HttpResponse<ISurveillanceTask[]>;

@Injectable({ providedIn: 'root' })
export class SurveillanceTaskService {
  public resourceUrl = SERVER_API_URL + 'api/surveillance-tasks';

  constructor(protected http: HttpClient) {}

  create(surveillanceTask: ISurveillanceTask): Observable<EntityResponseType> {
    return this.http.post<ISurveillanceTask>(this.resourceUrl, surveillanceTask, { observe: 'response' });
  }

  update(surveillanceTask: ISurveillanceTask): Observable<EntityResponseType> {
    return this.http.put<ISurveillanceTask>(this.resourceUrl, surveillanceTask, { observe: 'response' });
  }

  find(id: number): Observable<EntityResponseType> {
    return this.http.get<ISurveillanceTask>(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }

  query(req?: any): Observable<EntityArrayResponseType> {
    const options = createRequestOption(req);
    return this.http.get<ISurveillanceTask[]>(this.resourceUrl, { params: options, observe: 'response' });
  }

  delete(id: number): Observable<HttpResponse<{}>> {
    return this.http.delete(`${this.resourceUrl}/${id}`, { observe: 'response' });
  }
}
