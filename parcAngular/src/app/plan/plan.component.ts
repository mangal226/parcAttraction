import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-plan',
  templateUrl: './plan.component.html',
  styleUrls: ['./plan.component.css'],
})
export class PlanComponent implements OnInit {
  abscisse: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];
  ordonnee: number[] = [1, 2, 3, 4, 5, 6, 7, 8, 9, 10];

  constructor() {}

  ngOnInit(): void {}
}
