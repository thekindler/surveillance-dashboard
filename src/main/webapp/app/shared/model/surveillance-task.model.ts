export interface ISurveillanceTask {
  id?: number;
  type?: string;
  modename?: string;
  version?: number;
}

export class SurveillanceTask implements ISurveillanceTask {
  constructor(public id?: number, public type?: string, public modename?: string, public version?: number) {}
}
