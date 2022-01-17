import { Boutique } from '../model/boutique';
import { Component, OnInit } from '@angular/core';
import { BoutiqueService } from '../services/boutique.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-boutique',
  templateUrl: './boutique.component.html',
  styleUrls: ['./boutique.component.css'],
})
export class BoutiqueComponent implements OnInit {
  boutiques: Observable<Boutique[]> | null = null;

  constructor(private boutiqueService: BoutiqueService) {}

  ngOnInit(): void {
    this.boutiques=this.boutiqueService.getAll();
    }


  delete(id: number){
    this.boutiqueService.delete(id);
    this.boutiques=this.boutiqueService.getAll();
  }
}
