export class Coordonnees {
  public constructor(private _abscisse?: number, private _ordonnees?: number) {}

  public get abscisse(): number | undefined {
    return this._abscisse;
  }

  public set abscisse(value: number | undefined) {
    this._abscisse = value;
  }
  public get ordonnees(): number | undefined {
    return this._ordonnees;
  }

  public set ordonnees(value: number | undefined) {
    this._ordonnees = value;
  }
}
