import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Attraction } from '../model/attraction';
import { Coordonnees } from '../model/coordonnees';
import { AttractionService } from '../services/attraction.service';

@Component({
  selector: 'app-update-attraction',
  templateUrl: './update-attraction.component.html',
  styleUrls: ['./update-attraction.component.css'],
})
export class UpdateAttractionComponent implements OnInit {
  coordonnees: Coordonnees = new Coordonnees(0, 0);
  attraction: Attraction = new Attraction();
  constructor(
    private attractionService: AttractionService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {
    this.attraction.coordonnees = this.coordonnees;
  }

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      if (!!params['id']) {
        this.attractionService.getById(params['id']).subscribe((result) => {
          this.attraction = result;
        });
      }
    });
  }

  byId(obj1: Attraction, obj2: Attraction) {
    if (!!obj2 && !!obj1) return obj1.id == obj2.id;
    if (obj1 == obj2) return true;
    return false;
  }

  save() {
    if (!!this.attraction.id) {
      this.attractionService.put(this.attraction).subscribe((ok) => {
        this.router.navigate(['/admin/plan-modif']);
      });
    } else {
      this.attractionService.create(this.attraction).subscribe((ok) => {
        this.router.navigate(['/admin/plan-modif']);
      });
    }
  }
}
