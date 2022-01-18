import { RestaurationService } from './../services/restauration.service';
import { Component, OnInit } from '@angular/core';
import { Observable } from 'rxjs';
import { Attraction } from '../model/attraction';
import { Boutique } from '../model/boutique';
import { Coordonnees } from '../model/coordonnees';
import { Restauration } from '../model/restauration';
import { BoutiqueService } from '../services/boutique.service';
import { AttractionService } from '../services/attraction.service';

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
  attractionsLocal: Attraction[] = [];
  boutiques: Observable<Boutique[]> | null = null;
  boutiquesLocal: Boutique[] = [];
  restaurations: Observable<Restauration[]> | null = null;
  restaurationsLocal: Restauration[] = [];
  coordonneesUtilisesLocal: Coordonnees[] = [];

  constructor(
    private boutiqueService: BoutiqueService,
    private restaurationService: RestaurationService,
    private attractionService: AttractionService
  ) {}
  ngOnInit(): void {
    this.attractionService.getAll().subscribe((result) => {
      this.attractionsLocal = result;
    });
    this.boutiqueService.getAll().subscribe((result) => {
      this.boutiquesLocal = result;
    });
    this.restaurationService.getAll().subscribe((result) => {
      this.restaurationsLocal = result;
      this.coordonneesUtilisees();
    });

    // this.boutiques = this.boutiqueService.getAll();
    // this.restaurations = this.restaurationService.getAll();
    // this.attractions=this.attractionService.getAll();
  }

  checkVide(a: number, b: number) {
    let coordonneesLocal: Coordonnees = new Coordonnees(a, b);
    if (
      this.coordonneesUtilisesLocal.findIndex(
        (choix) =>
          choix.x == coordonneesLocal.x && choix.y == coordonneesLocal.y
      ) == -1
    ) {
      return false;
    }
    return true;
  }

  coordonneesUtilisees() {
    for (let index in this.boutiquesLocal) {
      this.coordonneesUtilisesLocal.push(
        this.boutiquesLocal[index].coordonnees!
      );
    }
    for (let index in this.attractionsLocal) {
      this.coordonneesUtilisesLocal.push(
        this.attractionsLocal[index].coordonnees!
      );
    }
    for (let index in this.restaurationsLocal) {
      this.coordonneesUtilisesLocal.push(
        this.restaurationsLocal[index].coordonnees!
      );
    }
    // this.boutiques!.subscribe((boutique) => {
    //   for (let index in boutique) {
    //     this.coordonneesUtilisesLocal.push(boutique[index].coordonnees!);
    //   }
    // });
    // this.restaurations!.subscribe((restau) => {
    //   for (let index2 in restau) {
    //     this.coordonneesUtilisesLocal.push(restau[index2].coordonnees!);
    //   }
    // });
    // this.attractions!.subscribe((attraction) => {
    //   for (let index3 in attraction) {
    //     this.coordonneesUtilisesLocal.push(attraction[index3].coordonnees!);
    //   }
    // });
  }

  checkBoutique(a: number, b: number) {
    let coordonneesLocal: Coordonnees = new Coordonnees(a, b);
    for (let index in this.boutiquesLocal) {
      if (
        this.boutiquesLocal[index].coordonnees!.x == coordonneesLocal.x &&
        this.boutiquesLocal[index].coordonnees!.y == coordonneesLocal.y
      ) {
        return true;
      }
    }
    return false;
  }

  checkAttraction(a: number, b: number) {
    let coordonneesLocal: Coordonnees = new Coordonnees(a, b);
    for (let index in this.attractionsLocal) {
      if (
        this.attractionsLocal[index].coordonnees!.x == coordonneesLocal.x &&
        this.attractionsLocal[index].coordonnees!.y == coordonneesLocal.y
      ) {
        return true;
      }
    }
    return false;
  }
}
