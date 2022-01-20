export class Plat {
  public constructor(
    private _id?: number,
    private _nom?: string,
    private _prix?: number,
    private _stock?: number,
    private _vente?: number
  ) {}
  public get id(): number | undefined {
    return this._id;
  }
  public set id(value: number | undefined) {
    this._id = value;
  }

  public get nom(): string | undefined {
    return this._nom;
  }

  public set id(value: number | undefined) {
    this._id = value;
  }

  public get id(): number | undefined {
    return this._id;
  }

  public set nom(value: string | undefined) {
    this._nom = value;
  }
  public get prix(): number | undefined {
    return this._prix;
  }

  public set prix(value: number | undefined) {
    this._prix = value;
  }
  public get stock(): number | undefined {
    return this._stock;
  }

  public set stock(value: number | undefined) {
    this._stock = value;
  }
  public get vente(): number | undefined {
    return this._vente;
  }

  public set vente(value: number | undefined) {
    this._vente = value;
  }
}
