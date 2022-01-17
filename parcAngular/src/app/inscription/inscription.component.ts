import { Router } from '@angular/router';
import { InscriptionService } from '../services/inscription.service';
import {
  AbstractControl,
  FormControl,
  FormGroup,
  ValidationErrors,
  Validators,
} from '@angular/forms';
import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-inscription',
  templateUrl: './inscription.component.html',
  styleUrls: ['./inscription.component.css'],
})
export class InscriptionComponent implements OnInit {
  form: FormGroup;

  constructor(
    private inscriptionService: InscriptionService,
    private router: Router
  ) {
    this.form = new FormGroup({
      login: new FormControl('', Validators.required),
      passwordGroup: new FormGroup(
        {
          password: new FormControl('', Validators.required),
          confirm: new FormControl(''),
        },
        this.checkEquals
      ),
    });
  }

  checkEquals(group: AbstractControl): ValidationErrors | null {
    let formGroup: FormGroup = group as FormGroup;
    return formGroup.controls['password'].value !=
      formGroup.controls['confirm'].value
      ? { checkequals: true }
      : null;
  }

  ngOnInit(): void {}

  validate() {
    let group = this.form.controls['passwordGroup'] as FormGroup;
    let user = {
      login: this.form.controls['login'].value,
      password: group.controls['password'].value,
    };
    this.inscriptionService.inscription(user).subscribe((ok) => {
      this.router.navigate(['']);
    });
  }
}
