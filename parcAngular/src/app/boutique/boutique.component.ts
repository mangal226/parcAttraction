import { Component, OnInit } from '@angular/core';
import { Boutique } from '../model/boutique';
import { BoutiqueService } from '../services/boutique.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-boutique',
  templateUrl: './boutique.component.html',
  styleUrls: ['./boutique.component.css']
})
export class BoutiqueComponent implements OnInit {
  boutiques: Observable<Boutique[]> | null = null;
  source: string[]=[];

  constructor(private boutiqueService: BoutiqueService) {
  }


  ngOnInit(): void {
    this.boutiques=this.boutiqueService.getAll();
    this.source[0]="../assets/img/boutique.jpg";
    this.source[1]='../assets/img/boutique1.jpg';
    this.source[2]="../assets/img/forain.jpg";
    this.source[3]="../assets/img/echoppe.jpg";
    this.source[4]="../assets/img/boutique2.jpg";
    this.source[5]="../assets/img/boutique.jpg";
    this.source[6]="../assets/img/boutique.jpg";
    this.source[7]='../assets/img/boutique1.jpg';
    this.source[8]="../assets/img/boutique2.jpg";
    this.source[9]="../assets/img/boutique1.jpg";
    this.source[10]="../assets/img/boutique2.jpg";
    this.source[11]="../assets/img/boutique.jpg";

    }

  delete(id: number){
    this.boutiqueService.delete(id);
    this.boutiques=this.boutiqueService.getAll();
  }

}
