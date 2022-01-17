import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  ActivatedRouteSnapshot,
  CanActivate,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class AuthenticationService implements CanActivate {
  constructor(private http: HttpClient) {}

  canActivate(): boolean {
    if (localStorage.getItem('token') != null) {
      return true;
    } else {
      return false;
    }
  }

  public get headers(): HttpHeaders {
    if (localStorage.getItem('token') != null) {
      return new HttpHeaders({
        'Content-Type': 'application/json',
        Authorization: 'Basic ' + localStorage.getItem('token'),
      });
    }
    return new HttpHeaders();
  }

  public login(login: string, password: string): Observable<any> {
    let headers = new HttpHeaders({
      'Content-Type': 'application/json',
      Authorization: 'Basic ' + btoa(login + ':' + password),
    });
    return this.http.get<any>('http://localhost:8080/lotr/api/user', {
      headers: headers,
    });
  }
}
