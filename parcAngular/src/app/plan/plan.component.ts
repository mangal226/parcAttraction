import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Attraction } from '../model/attraction';
import { Boutique } from '../model/boutique';
import { Coordonnees } from '../model/coordonnees';
import { Restauration } from '../model/restauration';
import { BoutiqueService } from '../services/boutique.service';

@Component({
  selector: 'app-plan',
  templateUrl: './plan.component.html',
  styleUrls: ['./plan.component.css'],
})
export class PlanComponent implements OnInit {
  abscisse: number[] = [0, 1, 2, 3, 4, 5];
  ordonnee: number[] = [0, 1, 2, 3, 4, 5];
  coordonnees: Coordonnees = new Coordonnees();
  attractions: Observable<Attraction[]> | null = null;
  boutiques: Observable<Boutique[]> | null = null;
  restaurations: Observable<Restauration[]> | null = null;
  coordonneesUtilises: Coordonnees[] = [];

  constructor(private boutiqueService: BoutiqueService) {}
  // constructor() {}
  // ngOnInit(): void {}
  ngOnInit(): void {
    //   this.attractions=this.attractionService.getAll();
    this.boutiques = this.boutiqueService.getAll();
    this.coordonneesUtilisees();
    //   this.restaurations=this.restaurationService.getAll();
  }

  checkVide(a: number, b: number) {
    let coordonneesLocal: Coordonnees = new Coordonnees(a, b);
    console.log(coordonneesLocal);
    console.log(this.coordonneesUtilises[0]);
    console.log(
      this.coordonneesUtilises.findIndex(
        (x) => x.x == coordonneesLocal.x && x.y == coordonneesLocal.y
      )
    );
    if (
      this.coordonneesUtilises.findIndex(
        (x) => x.x == coordonneesLocal.x && x.y == coordonneesLocal.y
      ) == -1
    ) {
      return false;
    }
    return true;
  }

  coordonneesUtilisees() {
    // this.attractions!.forEach(attraction =>
    //   this.coordonneesUtilises.push(attraction.getCoordonnees());)
    let index: number;
    this.boutiques!.subscribe((ok) => {
      for (let index in ok) {
        this.coordonneesUtilises.push(ok[index].coordonnees!);
      }
    });

    // this.restaurations!.forEach(restauration =>
    //   this.coordonneesUtilises.push(restauration.getCoordonnees());)
  }

  checkLequel(coordonnes: Coordonnees) {
    let choix: number = 0;
    let index: number;
    this.boutiques!.subscribe((ok) => {
      for (let index in ok) {
        if (ok[index].coordonnees == coordonnes) {
          choix = 1;
        }
      }
    });

    // this.attractions!.forEach((attraction) =>{
    //   if (attraction.getCoordonnees()==coordonnes) {
    //     return attraction;
    //    }
    // });
    // this.restaurations!.forEach((restauration) =>{
    //   if (restauration.getCoordonnees()==coordonnes) {
    //     return restauration;
    //    }
    //   });
    if (choix == 1) {
      this.boutiques!.subscribe((ok) => {
        return ok[index];
      });
    }
  }
}
