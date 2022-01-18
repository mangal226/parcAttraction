import { Boisson } from './boisson';
import { Coordonnees } from './coordonnees';
import { Plat } from './plat';

export class Restauration {
  public constructor(
    private _id?: number,
    private _nom?: string,
    private _boisson?: Boisson[],
    private _plat?: Plat[],
    private _coordonnees?: Coordonnees
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
  public set nom(value: string | undefined) {
    this._nom = value;
  }

  public get boisson(): Boisson[] | undefined {
    return this._boisson;
  }
  public set boisson(value: Boisson[] | undefined) {
    this._boisson = value;
  }

  public get plat(): Plat[] | undefined {
    return this._plat;
  }
  public set plat(value: Plat[] | undefined) {
    this._plat = value;
  }

  public get coordonnees(): Coordonnees | undefined {
    return this._coordonnees;
  }
  public set coordonnees(value: Coordonnees | undefined) {
    this._coordonnees = value;
  }
}
