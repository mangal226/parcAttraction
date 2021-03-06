import { Injectable } from '@angular/core';

import { AuthenticationService } from './authentification.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Restauration } from '../model/restauration';

@Injectable({
  providedIn: 'root',
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
    return this.http.get<Restauration>(
      RestaurationService.URL + '/' + description,
      {
        headers: this.auth.headers,
      }
    );
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

  public delete(id: number): Observable<void> {
    console.log(id);
    return this.http.delete<void>(RestaurationService.URL + '/' + id, {
      headers: this.auth.headers,
    });
  }

  public create(restauration: Restauration): Observable<Restauration> {
    return this.http.post<Restauration>(
      RestaurationService.URL,
      this.formatRestaurationToJson(restauration),
      {
        headers: this.auth.headers,
      }
    );
  }

  private formatRestaurationToJson(restauration: Restauration): Object {
    const p = {
      id: restauration.id,
      nom: restauration.nom,
      description: restauration.description,
      coordonnees: {
        x: restauration.coordonnees!.x,
        y: restauration.coordonnees!.y,
      },
      plat: [
        restauration.plat![0],
        restauration.plat![1],
        restauration.plat![2],
        restauration.plat![3],
      ],
      boisson: [
        restauration.boisson![0],
        restauration.boisson![1],
        restauration.boisson![2],
        restauration.boisson![3],
      ],
    };
    if (!!restauration.id) {
      Object.assign(p, { id: restauration.id });
    }
    console.log(p);
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
