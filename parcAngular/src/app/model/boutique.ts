import { Coordonnees } from './coordonnees';
import { Marchandise } from './marchandise';

export class Boutique {
  public constructor(
    private _id?: number,
    private _nom?: string,
    private _description?: string,
    private _coordonnees?: Coordonnees,
    private _enVente?: Marchandise[]
  ) {}

  public get id(): number | undefined {
    return this._id;
  }

  public set id(value: number | undefined) {
    this._id = value;
  }

  public get coordonnees(): Coordonnees | undefined {
    return this._coordonnees;
  }

  public set coordonnees(value: Coordonnees | undefined) {
    this._coordonnees = value;
  }

  public get nom(): string | undefined {
    return this._nom;
  }

  public set nom(value: string | undefined) {
    this._nom = value;
  }

  public get description(): string | undefined {
    return this._description;
  }

  public set description(value: string | undefined) {
    this._description = value;
  }

  public get enVente(): Marchandise[] | undefined {
    return this._enVente;
  }

  public set enVente(value: Marchandise[] | undefined) {
    this._enVente = value;
  }
}
