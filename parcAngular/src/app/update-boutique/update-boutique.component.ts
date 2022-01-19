import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Boutique } from '../model/boutique';
import { Marchandise } from '../model/marchandise';
import { BoutiqueService } from '../services/boutique.service';

@Component({
  selector: 'app-update-boutique',
  templateUrl: './update-boutique.component.html',
  styleUrls: ['./update-boutique.component.css'],
})
export class UpdateBoutiqueComponent implements OnInit {
  marchandises: Marchandise[] = [];
  boutique: Boutique = new Boutique();
  constructor(
    private boutiqueService: BoutiqueService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
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
    if (!!this.boutique.id) {
      this.boutiqueService.put(this.boutique).subscribe((ok) => {
        this.router.navigate(['/plan-modif']);
      });
    } else {
      this.boutiqueService.create(this.boutique).subscribe((ok) => {
        this.router.navigate(['/plan-modif']);
      });
    }
  }
}
