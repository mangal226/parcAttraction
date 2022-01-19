import { RestaurationService } from './../services/restauration.service';
import { Component, OnInit } from '@angular/core';
import { forkJoin, Observable } from 'rxjs';
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
    forkJoin([
      this.attractionService.getAll(),
      this.boutiqueService.getAll(),
      this.restaurationService.getAll(),
    ]).subscribe((result) => {
      this.attractionsLocal = result[0];
      this.boutiquesLocal = result[1];
      this.restaurationsLocal = result[2];
      this.coordonneesUtilisees();
    });
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
