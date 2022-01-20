export class Simulation {
  public constructor (
    //private _id?: number,
    private _nbJours?: number,
    private _nbFamilles?: number,

  ){}

  // public get id(): number | undefined {
  //   return this._id;
  // }

  // public set id(value: number | undefined) {
  //   this._id = value;
  // }

  public get nbJours(): number | undefined {
    return this._nbJours;
  }
  public set nbJours(value: number | undefined) {
    this._nbJours = value;
  }

  public get nbFamilles(): number | undefined {
    return this._nbFamilles;
  }

  public set nbFamilles(value: number | undefined) {
    this._nbFamilles = value;
  }

}
