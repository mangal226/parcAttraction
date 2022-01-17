import { Component, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthenticationService } from '../services/authentification.service';

@Component({
  selector: 'app-connexion',
  templateUrl: './connexion.component.html',
  styleUrls: ['./connexion.component.css'],
})
export class ConnexionComponent implements OnInit {
  form: FormGroup;
  error: boolean = false;
  constructor(private auth: AuthenticationService, private router: Router) {
    this.form = new FormGroup({
      login: new FormControl('', Validators.required),
      password: new FormControl('', Validators.required),
    });
  }

  ngOnInit(): void {}

  validate() {
    let login = this.form.controls['login'].value;
    let password = this.form.controls['password'].value;
    this.auth.login(login, password).subscribe(
      (ok) => {
        //authentifier
        localStorage.setItem('token', btoa(login + ':' + password));
        localStorage.setItem('login', login);
        this.router.navigate(['/admin/accueil-admin']);
      },
      (error) => {
        this.error = true;
      }
    );
  }
}
