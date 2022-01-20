export class Simulation {
  public constructor (
    private _id?: number,
    private _nbJours?: number,
    private _nbFamilles?: number,
    private _bilanFinancier?: number,
    private _nbBoissons?: number,
    private _nbMarchandises?: number,
    private _nbPlats?: number,
    private _nbVisiteurs?: number

  ){}

  public get id(): number| undefined{
    return this._id;
  }

  public set id(value: number | undefined) {
    this._id = value;
  }

  public get nbFamilles(): number| undefined{
    return this._nbFamilles;
  }

  public set bilanFinancier(value: number | undefined) {
    this._bilanFinancier = value;
  }

  public get bilanFinancier(): number| undefined{
    return this._bilanFinancier;
  }

  public set nbFamilles(value: number | undefined) {
    this._nbFamilles = value;
  }

  public get nbJours(): number | undefined {
    return this._nbJours;
  }
  public set nbJours(value: number | undefined) {
    this._nbJours = value;
  }

  public get nbVisiteurs(): number | undefined {
    return this._nbVisiteurs;
  }

  public set nbVisiteurs(value: number | undefined) {
    this._nbVisiteurs = value;
  }

  public get nbBoissons(): number | undefined {
    return this._nbBoissons;
  }

  public set nbBoissons(value: number | undefined) {
    this._nbBoissons = value;
  }

  public get nbPlats(): number | undefined {
    return this._nbPlats;
  }

  public set nbPlats(value: number | undefined) {
    this._nbPlats = value;
  }

  public get nbMarchandises(): number | undefined {
    return this._nbMarchandises;
  }

  public set nbMarchandises(value: number | undefined) {
    this._nbMarchandises = value;
  }

}
