import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Plat } from '../model/plat';
import { AuthenticationService } from './authentification.service';

@Injectable({
  providedIn: 'root',
})
export class PlatService {
  private static URL: string = 'http://localhost:8080/lotr/api/plat';
  constructor(private http: HttpClient, private auth: AuthenticationService) {}

  public getAll(): Observable<Plat[]> {
    return this.http.get<Plat[]>(PlatService.URL);
  }
}
