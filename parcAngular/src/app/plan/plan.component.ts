import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Attraction } from '../model/attraction';
import { Boutique } from '../model/boutique';
import { Coordonnees } from '../model/coordonnees';
import { Restauration } from '../model/restauration';

@Component({
  selector: 'app-plan',
  templateUrl: './plan.component.html',
  styleUrls: ['./plan.component.css'],
})
export class PlanComponent implements OnInit {
  abscisse: number[] = [1, 2, 3, 4, 5, 6];
  ordonnee: number[] = [1, 2, 3, 4, 5, 6];
  coordonnees: Coordonnees = new Coordonnees();
  attractions: Observable<Attraction[]> | null = null;
  boutiques: Observable<Boutique[]> | null = null;
  restaurations: Observable<Restauration[]> | null = null;
  coordonneesUtilises: Coordonnees[] = [];

  // constructor(private attractionService, private boutiqueService, private restaurationService) {}
  constructor() {}
  ngOnInit(): void {}
  // ngOnInit(): void {
  //   this.attractions=this.attractionService.getAll();
  //   this.boutiques=this.boutiqueService.getAll();
  //   this.restaurations=this.restaurationService.getAll();
  // }

  checkVide(coordonnes: Coordonnees) {
    if (this.coordonneesUtilises.indexOf(coordonnes) == -1) {
      return false;
    }
    return true;
  }

  // coordonneesUtilisees(){
  //   this.attractions.forEach(attraction =>
  //     coordonneesUtilises.add(attraction.getCoordonnees());)
  //   this.boutiques.forEach(boutique =>
  //     coordonneesUtilises.add(boutique.getCoordonnees());)
  //   this.restaurations.forEach(restauration =>
  //     coordonneesUtilises.add(restauration.getCoordonnees());)
  // }
}
