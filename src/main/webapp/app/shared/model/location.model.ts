export interface ILocation {
  id?: number;
  name?: string;
  city?: string;
  state?: string;
  country?: string;
  postalCode?: string;
}

export class Location implements ILocation {
  constructor(
    public id?: number,
    public name?: string,
    public city?: string,
    public state?: string,
    public country?: string,
    public postalCode?: string
  ) {}
}
