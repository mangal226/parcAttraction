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
    return this.http.get<Restauration[]>(RestaurationService.URL, {
      headers: this.auth.headers,
    });
  }


  public getById(id: number): Observable<Restauration> {
    return this.http.get<Restauration>(RestaurationService.URL + '/' + id, {
      headers: this.auth.headers,
    });
  }

  public update(boutique: Restauration): Observable<Restauration> {
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
}


