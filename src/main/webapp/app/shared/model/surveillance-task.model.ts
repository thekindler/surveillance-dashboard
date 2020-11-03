export interface ISurveillanceTask {
  id?: number;
  type?: string;
  modelName?: string;
  versionId?: string;
}

export class SurveillanceTask implements ISurveillanceTask {
  constructor(public id?: number, public type?: string, public modelName?: string, public versionId?: string) {}
}
