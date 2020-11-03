export interface ICamera {
  id?: number;
  make?: string;
  location?: string;
  surveillanceTask?: string;
  ip?: string;
  fps?: string;
}

export class Camera implements ICamera {
  constructor(
    public id?: number,
    public make?: string,
    public location?: string,
    public surveillanceTask?: string,
    public ip?: string,
    public fps?: string
  ) {}
}
