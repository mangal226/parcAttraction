import { Coordonnees } from './coordonnees';

export class Attraction {
  public constructor(
    private _nom?: string,
    private _duree?: number,
    private _capacite?: number,
    private _tailleMin?: number,
    private _tailleMax?: number,
    private _restHandi?: boolean,
    private _nbrVisiteur?: number,
    private _coordonnees?: Coordonnees
  ) {}

  public get nom(): string | undefined {
    return this._nom;
  }

  public set nom(value: string | undefined) {
    this._nom = value;
  }

  public get duree(): number | undefined {
    return this._duree;
  }

  public set duree(value: number | undefined) {
    this._duree = value;
  }
  public get capacite(): number | undefined {
    return this._capacite;
  }

  public set capacite(value: number | undefined) {
    this._capacite = value;
  }
  public get tailleMind(): number | undefined {
    return this._tailleMin;
  }

  public set tailleMin(value: number | undefined) {
    this._tailleMin = value;
  }
  public get tailleMax(): number | undefined {
    return this._tailleMax;
  }

  public set tailleMax(value: number | undefined) {
    this._tailleMax = value;
  }

  public get restHandi(): boolean | undefined {
    return this._restHandi;
  }

  public set restHandi(value: boolean | undefined) {
    this._restHandi = value;
  }

  public get nbrVisiteur(): number | undefined {
    return this._nbrVisiteur;
  }

  public set nbrVisiteur(value: number | undefined) {
    this._nbrVisiteur = value;
  }

  public get coordonnees(): Coordonnees | undefined {
    return this._coordonnees;
  }

  public set coordonnees(value: Coordonnees | undefined) {
    this._coordonnees = value;
  }
}
