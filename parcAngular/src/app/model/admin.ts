export class Admin {
  public constructor(
    private _id?: number,
    private _login?: string,
    private _password?: string,
    private _date_de_naissance?: string,
    private _numero_de_telephone?: string,
    private _localisation?: string,
    private _anciennete?: string,
    private _diplome?: string,
    private _email?: string,
    private _management?: string,
    private _analyse_financiere?: string,
    private _planification?: string,
    private _gestion_equipe?: string

  ) {}



  public get id(): number | undefined {
    return this._id;
  }

  public set id(value: number | undefined) {
    this._id = value;
  }

  public get login(): string | undefined {
    return this._login;
  }
  public set login(value: string | undefined) {
    this._login = value;
  }

  public get password(): string | undefined {
    return this._password;
  }
  public set password(value: string | undefined) {
    this._password = value;
  }

  public get date_de_naissance(): string | undefined {
    return this._date_de_naissance;
  }
  public set date_de_naissance(value: string | undefined) {
    this._date_de_naissance = value;
  }

  public get numero_de_telephone(): string | undefined {
    return this._numero_de_telephone;

  }
  public set numero_de_telephone(value: string | undefined) {
    this._numero_de_telephone = value;
  }

  public get localisation(): string | undefined {
    return this._localisation;
  }
  public set localisation(value: string | undefined) {
    this._localisation = value;
  }

  public get anciennete(): string | undefined {
    return this._anciennete;
  }
  public set anciennete(value: string | undefined) {
    this._anciennete = value;
  }

  public get diplome(): string | undefined {
    return this._diplome;
  }
  public set diplome(value: string | undefined) {
    this._diplome = value;
  }

  public get email(): string | undefined {
    return this._email;
  }
  public set email(value: string | undefined) {
    this._email = value;
  }

  public get management(): string | undefined {
    return this._management;
  }
  public set management(value: string | undefined) {
    this._management = value;
  }

  public get analyse_financiere(): string | undefined {
    return this._analyse_financiere;
  }
  public set analyse_financiere(value: string | undefined) {
    this._analyse_financiere = value;
  }

  public get planification(): string | undefined {
    return this._planification;

  }
  public set planification(value: string | undefined) {
    this._planification = value;
  }

  public get gestion_equipe(): string | undefined {
    return this._gestion_equipe;
  }
  public set gestion_equipe(value: string | undefined) {
    this._gestion_equipe = value;
  }






}


