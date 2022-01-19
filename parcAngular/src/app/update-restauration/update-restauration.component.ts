import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Restauration } from '../model/restauration';
import { RestaurationService } from '../services/restauration.service';

@Component({
  selector: 'app-update-restauration',
  templateUrl: './update-restauration.component.html',
  styleUrls: ['./update-restauration.component.css'],
})
export class UpdateRestaurationComponent implements OnInit {
  restauration: Restauration = new Restauration();
  constructor(
    private restaurationService: RestaurationService,
    private activatedRoute: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.activatedRoute.params.subscribe((params) => {
      if (!!params['id']) {
        this.restaurationService.getById(params['id']).subscribe((result) => {
          this.restauration = result;
        });
      }
    });
  }

  byId(obj1: Restauration, obj2: Restauration) {
    if (!!obj2 && !!obj1) return obj1.id == obj2.id;
    if (obj1 == obj2) return true;
    return false;
  }

  save() {
    if (!!this.restauration.id) {
      this.restaurationService.put(this.restauration).subscribe((ok) => {
        this.router.navigate(['/plan-modif']);
      });
    } else {
      this.restaurationService.create(this.restauration).subscribe((ok) => {
        this.router.navigate(['/plan-modif']);
      });
    }
  }
}
