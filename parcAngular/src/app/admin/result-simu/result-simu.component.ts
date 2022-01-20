import { SimulationService } from './../../services/simulation.service';
import { Component, OnInit } from '@angular/core';
import { forkJoin, Observable } from 'rxjs';
import { Simulation } from 'src/app/model/simulation';

@Component({
  selector: 'app-result-simu',
  templateUrl: './result-simu.component.html',
  styleUrls: ['./result-simu.component.css']
})
export class ResultSimuComponent implements OnInit {
  simulation: Observable<Simulation[]> | null = null;
  simulationLocal!: Simulation[];


  constructor(private simulationService: SimulationService) { }

  ngOnInit(): void {
    forkJoin([
      this.simulationService.getAll(),
    ]).subscribe((result) => {
      this.simulationLocal = result[0];
      console.log(this.simulationLocal);
    })

  }

}
