import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { AuthenticationService } from './authentification.service';
import { Simulation } from '../model/simulation';
import { Observable } from 'rxjs';
import { SimulationComponent } from '../admin/simulation/simulation.component';

@Injectable({
  providedIn: 'root'
})
export class SimulationService {
  private static URL: string = 'http://localhost:8080/lotr/api/simulation';
  constructor(private http: HttpClient, private auth: AuthenticationService) {}


  public getAll(): Observable<Simulation[]> {
    return this.http.get<Simulation[]>(SimulationService.URL);
  }

  public getById(id: number): Observable<Simulation> {
    return this.http.get<Simulation>(SimulationService.URL + '/' + id, {
      headers: this.auth.headers,
    });
  }

  private formatSimulationToJson(simulation: Simulation): Object {
    const a = {
      nbFamilles: simulation.nbFamilles,
      nbJours: simulation.nbJours
    };
    if (!!simulation.id) {
      Object.assign(a, { id: simulation.id });
    }
    return a;
  }


create(simulation: Simulation): Observable<Simulation> {
  console.log(simulation);
  return this.http.post<Simulation>(
    SimulationService.URL,
    this.formatSimulationToJson(simulation),
    // {
    //   headers: this.auth.headers,
    // }
  );
}

put(simulation: Simulation): Observable<Simulation> {
  return this.http.put<Simulation>(
    SimulationService.URL + '/' + simulation.id,
    (simulation),
    // {
    //   headers: this.auth.headers,
    // }
  );
}


}
