import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Restauration } from '../model/restauration';
import { RestaurationService } from '../services/restauration.service';
import { Coordonnees } from '../model/coordonnees';
import { FormGroup, FormBuilder, FormArray } from '@angular/forms';
import { Plat } from '../model/plat';
import { forkJoin } from 'rxjs';
import { PlatService } from '../services/plat.service';
import { BoissonService } from '../services/boisson.service';
import { Boisson } from '../model/boisson';

@Component({
  selector: 'app-update-restauration',
  templateUrl: './update-restauration.component.html',
  styleUrls: ['./update-restauration.component.css'],
})
export class UpdateRestaurationComponent implements OnInit {
  form: FormGroup;
  coordonnees: Coordonnees = new Coordonnees(0, 0);
  restauration: Restauration = new Restauration();
  platLocal: Plat[] = [];
  boissonLocal: Boisson[] = [];
  boissonAEnvoyerVraiment: Boisson[] = [];
  platAEnvoyerVraiment: Plat[] = [];
  constructor(
    private restaurationService: RestaurationService,
    private platService: PlatService,
    private boissonService: BoissonService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.restauration.coordonnees = this.coordonnees;
    this.form = this.formBuilder.group({
      platAEnvoyer: new FormArray([]),
      boissonAEnvoyer: new FormArray([]),
    });
  }

  onCheckboxChangePlat(event: any) {
    let index: number = event.target.value;
    if (event.target.checked) {
      this.platAEnvoyerVraiment.push(this.platLocal[index]);
    } else {
      for (let p of this.platAEnvoyerVraiment) {
        if (p.nom == this.platAEnvoyerVraiment[index].nom) {
          this.platAEnvoyerVraiment.splice(index, 1);
        }
      }
    }
  }
  onCheckboxChangeBoisson(event: any) {
    let index: number = event.target.value;
    if (event.target.checked) {
      this.boissonAEnvoyerVraiment.push(this.boissonLocal[index]);
    } else {
      for (let b of this.boissonAEnvoyerVraiment) {
        if (b.nom == this.boissonAEnvoyerVraiment[index].nom) {
          this.boissonAEnvoyerVraiment.splice(index, 1);
        }
      }
    }
  }

  ngOnInit(): void {
    forkJoin([
      this.boissonService.getAll(),
      this.platService.getAll(),
    ]).subscribe((result) => {
      this.boissonLocal = result[0];
      this.platLocal = result[1];
    });
    this.activatedRoute.params.subscribe((params) => {
      if (!!params['id']) {
        this.restaurationService.getById(params['id']).subscribe((result) => {
          this.restauration = result;
        });
      }
    });
  }

  byId(obj1: Restauration, obj2: Restauration) {
    if (!!obj2 && !!obj1) return obj1.id == obj2.id;
    if (obj1 == obj2) return true;
    return false;
  }

  save() {
    this.restauration.boisson = this.boissonAEnvoyerVraiment;
    this.restauration.plat = this.platAEnvoyerVraiment;
    if (!!this.restauration.id) {
      this.restaurationService.put(this.restauration).subscribe((ok) => {
        this.router.navigate(['//admin/plan-modif']);
      });
    } else {
      this.restaurationService.create(this.restauration).subscribe((ok) => {
        this.router.navigate(['/admin/plan-modif']);
      });
    }
  }
}
