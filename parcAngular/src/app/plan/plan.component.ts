import { RestaurationService } from './../services/restauration.service';
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

  constructor(
    private boutiqueService: BoutiqueService,
    private restaurationService: RestaurationService
  ) {}
  // constructor() {}
  // ngOnInit(): void {}
  ngOnInit(): void {
    // this.attractions = this.attractionService.getAll();
    this.boutiques = this.boutiqueService.getAll();

    this.restaurations = this.restaurationService.getAll();
    this.coordonneesUtilisees();
  }

  checkVide(a: number, b: number) {
    let coordonneesLocal: Coordonnees = new Coordonnees(a, b);
    console.log();
    if (
      this.coordonneesUtilises.findIndex(
        (choix) =>
          choix.x == coordonneesLocal.x && choix.y == coordonneesLocal.y
      ) == -1
    ) {
      return false;
    }
    return true;
  }

  coordonneesUtilisees() {
    // this.attractions!.forEach(attraction =>
    //   this.coordonneesUtilises.push(attraction.getCoordonnees());)
    this.boutiques!.subscribe((boutique) => {
      for (let index in boutique) {
        this.coordonneesUtilises.push(boutique[index].coordonnees!);
      }
    });
    this.restaurations!.subscribe((restau) => {
      for (let index2 in restau) {
        this.coordonneesUtilises.push(restau[index2].coordonnees!);
      }
    });
  }

  checkBoutique(a: number, b: number) {
    let coordonneesLocal: Coordonnees = new Coordonnees(a, b);
    this.boutiques!.subscribe((sub) => {
      for (let index in sub) {
        if (
          sub[index].coordonnees!.x == coordonneesLocal.x &&
          sub[index].coordonnees!.y == coordonneesLocal.y
        ) {
          return true;
        }
      }
      return false;
    });
  }

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
  // }
}
