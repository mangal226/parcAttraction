export class Simulation {

  public constructor(
    private _familles?: number, private _duree?: number
  ){}

  public get familles (): number | undefined{
    return this._familles;
  }

  public set familles(value: number | undefined){
    this._familles=value;
  }

  public get duree (): number | undefined{
    return this._duree;
  }

  public set duree(value: number | undefined){
    this._duree=value;
  }
}
