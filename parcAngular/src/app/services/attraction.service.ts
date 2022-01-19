import { Injectable } from '@angular/core';
import { AuthenticationService } from './authentification.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';


@Injectable({
  providedIn: 'root',
})
export class AttractionService {
  private static URL: string = 'http://localhost:8080/lotr/api/attraction';
  constructor(private http: HttpClient, private auth: AuthenticationService) {}

  public getAll(): Observable<Attraction[]> {
    return this.http.get<Attraction[]>(AttractionService.URL);
  }

  public getById(id: number): Observable<Attraction> {
    return this.http.get<Attraction>(AttractionService.URL + '/' + id, {
      headers: this.auth.headers,
    });
  }

  public getByNom(nom: string): Observable<Attraction> {
    return this.http.get<Attraction>(AttractionService.URL + '/' + nom, {
      headers: this.auth.headers,
    });
  }

  public update(attraction: Attraction): Observable<Attraction> {
    return this.http.put<Attraction>(
      AttractionService.URL + '/' + attraction.id,
      attraction,
      {
        headers: this.auth.headers,
      }
    );
  }

  public delete(id: number): Observable<void> {
    return this.http.delete<void>(AttractionService.URL + '/' + id, {
      headers: this.auth.headers,
    });
  }

  private formatAttractionToJson(attraction: Attraction): Object {
    const a = {
      nom: attraction.nom,
      duree: attraction.duree,
      capacite: attraction.capacite,
      tailleMax: attraction.tailleMax,
      tailleMin: attraction.tailleMin,
      restHandi: attraction.restHandi,
      coordonnees: {
        x: attraction.coordonnees!.x,
        y: attraction.coordonnees!.y,
      },
    };
    if (!!attraction.id) {
      Object.assign(a, { id: attraction.id });
    }
    return a;
  }
  create(attraction: Attraction): Observable<Attraction> {
    return this.http.post<Attraction>(
      AttractionService.URL,
      this.formatAttractionToJson(attraction),
      {
        headers: this.auth.headers,
      }
    );
  }

  put(attraction: Attraction): Observable<Attraction> {
    return this.http.put<Attraction>(
      AttractionService.URL + '/' + attraction.id,
      this.formatAttractionToJson(attraction),
      {
        headers: this.auth.headers,
      }
    );
  }
}
