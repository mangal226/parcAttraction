import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Marchandise } from '../model/marchandise';
import { AuthenticationService } from './authentification.service';

@Injectable({
  providedIn: 'root',
})
export class MarchandiseService {
  private static URL: string = 'http://localhost:8080/lotr/api/marchandise';
  constructor(private http: HttpClient, private auth: AuthenticationService) {}

  public getAll(): Observable<Marchandise[]> {
    return this.http.get<Marchandise[]>(MarchandiseService.URL);
  }
}
