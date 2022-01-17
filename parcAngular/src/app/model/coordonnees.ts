export class Coordonnees {
  public constructor(private _x?: number, private _y?: number) {}

  public get x(): number | undefined {
    return this._x;
  }

  public set x(value: number | undefined) {
    this._x = value;
  }
  public get y(): number | undefined {
    return this._y;
  }

  public set y(value: number | undefined) {
    this._y = value;
  }
}
