import { Injectable } from '@angular/core';

import { AuthenticationService } from './authentification.service';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Admin } from '../model/admin';

@Injectable({
  providedIn: 'root'
})
export class AdminService {

  private static URL: string = 'http://localhost:8080/lotr/api/admin/accueil-admin';
  constructor(private http: HttpClient, private auth: AuthenticationService) {}

  public getAll(): Observable<Admin[]> {
    return this.http.get<Admin[]>(AdminService.URL);
  }


  public getById(id: number): Observable<Admin> {
    return this.http.get<Admin>(AdminService.URL + '/' + id, {
      headers: this.auth.headers,
    });
  }

  public getByNom(nom: string): Observable<Admin> {
    return this.http.get<Admin>(AdminService.URL + '/' + nom, {
      headers: this.auth.headers,
    });
  }

  public getByDescription(description: string): Observable<Admin> {
    return this.http.get<Admin>(AdminService.URL + '/' + description, {
      headers: this.auth.headers,
    });
  }

  public update(admin: Admin): Observable<Admin> {
    return this.http.put<Admin>(
      AdminService.URL + '/' + admin.id,
      admin,
      {
        headers: this.auth.headers,
      }
    );
  }

  public create(admin: Admin): Observable<Admin> {
    const o = {
      id: admin.id,
    };
    return this.http.post<Admin>(AdminService.URL, o, {
      headers: this.auth.headers,
    });
  }

  public delete(id: number): Observable<void> {
    return this.http.delete<void>(AdminService.URL + '/' + id, {
      headers: this.auth.headers,
    });
  }


private formatAdminToJson(admin: Admin): Object {
  const p = {
    id: admin.id,
    login: admin.login,
    password: admin.password,
    date_de_naissance: admin.date_de_naissance,
    numero_de_telephone: admin.numero_de_telephone,
    localisation: admin.localisation,
    anciennete: admin.anciennete,
    diplome: admin.diplome,
    email: admin.email,
    management: admin.management,
    analyse_financiere: admin.analyse_financiere,
    planification:admin.planification,
    gestion_equipe: admin.gestion_equipe


  };
  if (!!admin.id) {
    Object.assign(p, { id: admin.id });
  }
  return p;
}


put(admin: Admin): Observable<Admin> {
  return this.http.put<Admin>(
    AdminService.URL + '/' + admin.id,
    this.formatAdminToJson(admin),
    {
      headers: this.auth.headers,
    }
  );
}
}

