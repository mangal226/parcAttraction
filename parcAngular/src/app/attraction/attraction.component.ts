import { Component, OnInit } from '@angular/core';
import { Attraction } from '../model/attraction';
import { AttractionService } from '../services/attraction.service';
import { Observable } from 'rxjs';

@Component({
  selector: 'app-attraction',
  templateUrl: './attraction.component.html',
  styleUrls: ['./attraction.component.css']
})
export class AttractionComponent implements OnInit {
  attraction: Observable<Attraction[]> | null=null;

  constructor( private attractionService: AttractionService) { }


  ngOnInit(): void {
    this.attraction=this.attractionService.getAll();
  }

  delete(id: number){
    this.attractionService.delete(id);
    this.attraction=this.attractionService.getAll();
  }
}
