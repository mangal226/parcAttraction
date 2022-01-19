import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthenticationService } from './authentification.service';

@Injectable({
  providedIn: 'root'
})
export class SimulationService {
  private static URL: string = 'http://localhost:8080/lotr/api/simulation';
  constructor(private http: HttpClient, private auth: AuthenticationService) {}

  public create (nbJours : number, nbFamilles : number, )


}
