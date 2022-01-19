import { Injectable } from '@angular/core';
import { AuthenticationService } from './authentification.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Boutique } from '../model/boutique';

@Injectable({
  providedIn: 'root'
})
export class BoutiqueService {

  private static URL: string = 'http://localhost:8080/lotr/api/boutique';
  constructor(private http: HttpClient, private auth: AuthenticationService) {}

  public getAll(): Observable<Boutique[]> {
    return this.http.get<Boutique[]>(BoutiqueService.URL, {
      headers: this.auth.headers,
    });
  }


  public getById(id: number): Observable<Boutique> {
    return this.http.get<Boutique>(BoutiqueService.URL + '/' + id, {
      headers: this.auth.headers,
    });
  }

  public getByDescription(description: string): Observable<Boutique> {
    return this.http.get<Boutique>(BoutiqueService.URL + '/' + description, {
      headers: this.auth.headers,
    });
  }

  public update(boutique: Boutique): Observable<Boutique> {
    return this.http.put<Boutique>(
      BoutiqueService.URL + '/' + boutique.id,
      boutique,
      {
        headers: this.auth.headers,
      }
    );
  }

  public create(boutique: Boutique): Observable<Boutique> {
    const o = {
      nom: boutique.nom,
    };
    return this.http.post<Boutique>(BoutiqueService.URL, o, {
      headers: this.auth.headers,
    });
  }

  public delete(id: number): Observable<void> {
    return this.http.delete<void>(BoutiqueService.URL + '/' + id, {
      headers: this.auth.headers,
    });
  }

  private formatBoutiqueToJson(boutique: Boutique): Object {
    const p = {
      id: boutique.id,
      nom: boutique.nom,
      description: boutique.description,
      coordonnees: boutique.coordonnees,
      marchandise: boutique.marchandise


    };
    if (!!boutique.id) {
      Object.assign(p, { id: boutique.id });
    }
    return p;
  }


  put(boutique: Boutique): Observable<Boutique> {
    return this.http.put<Boutique>(
      BoutiqueService.URL + '/' + boutique.id,
      this.formatBoutiqueToJson(boutique),
      {
        headers: this.auth.headers,
      }
    );
  }




}


