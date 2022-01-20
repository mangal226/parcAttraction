import { Injectable } from '@angular/core';
import { AuthenticationService } from './authentification.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Plat } from '../model/plat';


@Injectable({
  providedIn: 'root'
})
export class PlatService {
  private static URL: string = 'http://localhost:8080/lotr/api/plat';
  constructor(private http: HttpClient, private auth: AuthenticationService) {}

  public getAll(): Observable<Plat[]> {
    return this.http.get<Plat[]>(PlatService.URL);
  }


  public getById(id: number): Observable<Plat> {
    return this.http.get<Plat>(PlatService.URL + '/' + id, {
      headers: this.auth.headers,
    });
  }

  public getByNom(nom: string): Observable<Plat> {
    return this.http.get<Plat>(PlatService.URL + '/' + nom, {
      headers: this.auth.headers,
    });
  }

  public getByDescription(description: string): Observable<Plat> {
    return this.http.get<Plat>(PlatService.URL + '/' + description, {
      headers: this.auth.headers,
    });
  }

  /*public update(restauration: Plat): Observable<Plat> {
    return this.http.put<Plat>(
      PlatService.URL + '/' + plat.id,
      plat,
      {
        headers: this.auth.headers,
      }
    );
  }*/

  public create(plat: Plat): Observable<Plat> {
    const o = {
      nom: plat.nom,
    };
    return this.http.post<Plat>(PlatService.URL, o, {
      headers: this.auth.headers,
    });
  }

  public delete(id: number): Observable<void> {
    return this.http.delete<void>(PlatService.URL + '/' + id, {
      headers: this.auth.headers,
    });
  }


private formatRestaurationToJson(plat: Plat): Object {
  const p = {
    id: plat.id,
    nom: plat.nom,
    prix: plat.prix,
    stock: plat.stock,
    vente: plat.vente

  };
  if (!!plat.id) {
    Object.assign(p, { id: plat.id });
  }
  return p;
}


put(plat: Plat): Observable<Plat> {
  return this.http.put<Plat>(
    PlatService.URL + '/' + plat.id,
    this.formatRestaurationToJson(plat),
    {
      headers: this.auth.headers,
    }
  );
}
}

