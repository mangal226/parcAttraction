import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Boisson } from '../model/boisson';
import { AuthenticationService } from './authentification.service';

@Injectable({
  providedIn: 'root',
})
export class BoissonService {
  private static URL: string = 'http://localhost:8080/lotr/api/boisson';
  constructor(private http: HttpClient, private auth: AuthenticationService) {}

  public getAll(): Observable<Boisson[]> {
    return this.http.get<Boisson[]>(BoissonService.URL);
  }
}
