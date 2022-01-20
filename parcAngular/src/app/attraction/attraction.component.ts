import { AttractionService } from './../services/attraction.service';
import { Coordonnees } from './../model/coordonnees';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Attraction } from '../model/attraction';
import { Observable } from 'rxjs';
import { CommonModule } from '@angular/common';



@Component({
  selector: 'app-attraction',
  templateUrl: './attraction.component.html',
  styleUrls: ['./attraction.component.css']
})
export class AttractionComponent implements OnInit {
  source : string []=[];

  attraction: Observable<Attraction[]> | null=null;

  constructor( private attractionService: AttractionService) { }


  ngOnInit(): void {
    this.attraction=this.attractionService.getAll();
    this.source[0]="../assets/img/auto_tamponneuse.jpg";
    this.source[1]='../assets/img/bateau.jpg';
    this.source[2]="../assets/img/train.jpg";
    this.source[3]="../assets/img/grande_roue.jpg";
    this.source[4]="../assets/img/pedalo.jpg";
    this.source[5]="../assets/img/splash.jpg";
    this.source[6]="../assets/img/auto_tamponeuse.jpg";
    this.source[7]='../assets/img/bateau.jpg';
    this.source[8]="../assets/img/grand_huit.jpg";
    this.source[9]="../assets/img/grande_roue.jpg";
    this.source[10]="../assets/img/pedalo.jpg";
    this.source[11]="../assets/img/splash.jpg";
  }

  delete(id: number){
    this.attractionService.delete(id);
    this.attraction=this.attractionService.getAll();
  }
}
