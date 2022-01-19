import { Component, OnInit, Output } from '@angular/core';
import { FormArray, FormBuilder, FormControl, FormGroup } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { forkJoin, Observable } from 'rxjs';
import { Boutique } from '../model/boutique';
import { Coordonnees } from '../model/coordonnees';
import { Marchandise } from '../model/marchandise';
import { BoutiqueService } from '../services/boutique.service';
import { MarchandiseService } from '../services/marchandise.service';

@Component({
  selector: 'app-update-boutique',
  templateUrl: './update-boutique.component.html',
  styleUrls: ['./update-boutique.component.css'],
})
export class UpdateBoutiqueComponent implements OnInit {
  form: FormGroup;
  coordonnees: Coordonnees = new Coordonnees(0, 0);
  attractions: Observable<Marchandise[]> | null = null;
  marchandisesLocal: Marchandise[] = [];
  boutique: Boutique = new Boutique();
  marchandisesAEnvoyerVraiment: Marchandise[] = [];
  liste: string[] = [];

  constructor(
    private marchandiseService: MarchandiseService,
    private boutiqueService: BoutiqueService,
    private activatedRoute: ActivatedRoute,
    private router: Router,
    private formBuilder: FormBuilder
  ) {
    this.boutique.coordonnees = this.coordonnees;
    this.form = this.formBuilder.group({
      marchandisesAEnvoyer: new FormArray([]),
    });
  }

  onCheckboxChange(event: any) {
    let index: number = event.target.value;
    // const marchandisesAEnvoyer = this.form.get([
    //   'marchandisesAEnvoyer',
    // ]) as FormArray;
    if (event.target.checked) {
      this.marchandisesAEnvoyerVraiment.push(this.marchandisesLocal[index]);
      // marchandisesAEnvoyer.push(new FormControl(event.target.value));
    } else {
      for (let m of this.marchandisesAEnvoyerVraiment) {
        if (m.nom == this.marchandisesAEnvoyerVraiment[index].nom) {
          this.marchandisesAEnvoyerVraiment.splice(index, 1);
        }
      }

      // const index = marchandisesAEnvoyer.controls.findIndex(
      //   (x) => x.value === event.target.value
      // );
      // marchandisesAEnvoyer.removeAt(index);
    }
  }

  ngOnInit(): void {
    forkJoin([this.marchandiseService.getAll()]).subscribe((result) => {
      this.marchandisesLocal = result[0];
    });

    this.activatedRoute.params.subscribe((params) => {
      if (!!params['id']) {
        this.boutiqueService.getById(params['id']).subscribe((result) => {
          this.boutique = result;
        });
      }
    });
  }

  byId(obj1: Boutique, obj2: Boutique) {
    if (!!obj2 && !!obj1) return obj1.id == obj2.id;
    if (obj1 == obj2) return true;
    return false;
  }

  save() {
    this.boutique.enVente = this.marchandisesAEnvoyerVraiment;
    if (!!this.boutique.id) {
      this.boutiqueService.put(this.boutique).subscribe((ok) => {
        this.router.navigate(['/admin/plan-modif']);
      });
    } else {
      this.boutiqueService.create(this.boutique).subscribe((ok) => {
        this.router.navigate(['/admin/plan-modif']);
      });
    }
  }

  submit() {}
}
