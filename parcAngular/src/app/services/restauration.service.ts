import { Injectable } from '@angular/core';

import { AuthenticationService } from './authentification.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Restauration } from '../model/restauration';

@Injectable({
  providedIn: 'root'
})
export class RestaurationService {

  private static URL: string = 'http://localhost:8080/lotr/api/restauration';
  constructor(private http: HttpClient, private auth: AuthenticationService) {}

  public getAll(): Observable<Restauration[]> {
    return this.http.get<Restauration[]>(RestaurationService.URL);
  }


  public getById(id: number): Observable<Restauration> {
    return this.http.get<Restauration>(RestaurationService.URL + '/' + id, {
      headers: this.auth.headers,
    });
  }

  public getByNom(nom: string): Observable<Restauration> {
    return this.http.get<Restauration>(RestaurationService.URL + '/' + nom, {
      headers: this.auth.headers,
    });
  }

  public getByDescription(description: string): Observable<Restauration> {
    return this.http.get<Restauration>(RestaurationService.URL + '/' + description, {
      headers: this.auth.headers,
    });
  }

  public update(restauration: Restauration): Observable<Restauration> {
    return this.http.put<Restauration>(
      RestaurationService.URL + '/' + restauration.id,
      restauration,
      {
        headers: this.auth.headers,
      }
    );
  }

  public create(restauration: Restauration): Observable<Restauration> {
    const o = {
      nom: restauration.nom,
    };
    return this.http.post<Restauration>(RestaurationService.URL, o, {
      headers: this.auth.headers,
    });
  }

  public delete(id: number): Observable<void> {
    return this.http.delete<void>(RestaurationService.URL + '/' + id, {
      headers: this.auth.headers,
    });
  }


private formatRestaurationToJson(restauration: Restauration): Object {
  const p = {
    id: restauration.id,
    nom: restauration.nom,
    boisson: restauration.boisson,
    plat: restauration.plat,
    coordonnees: restauration.coordonnees,
    description: restauration.description,

  };
  if (!!restauration.id) {
    Object.assign(p, { id: restauration.id });
  }
  return p;
}


put(restauration: Restauration): Observable<Restauration> {
  return this.http.put<Restauration>(
    RestaurationService.URL + '/' + restauration.id,
    this.formatRestaurationToJson(restauration),
    {
      headers: this.auth.headers,
    }
  );
}
}

