import { Injectable } from '@angular/core';
import { AuthenticationService } from './authentification.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Attraction } from '../model/attraction';

@Injectable({
  providedIn: 'root'
})
export class AttractionService {

  private static URL: string = 'http://localhost:8080/lotr/api/attraction';
  constructor(private http: HttpClient, private auth: AuthenticationService) { }

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

  public create(attraction: Attraction): Observable<Attraction> {
    const o = {
      nom: attraction.nom,
    };
    return this.http.post<Attraction>(AttractionService.URL, o, {
      headers: this.auth.headers,
    });
  }

  public delete(id: number): Observable<void> {
    return this.http.delete<void>(AttractionService.URL + '/' + id, {
      headers: this.auth.headers,
    });
  }
}
