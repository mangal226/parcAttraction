import { Component, OnInit } from '@angular/core';
import { forkJoin, Observable } from 'rxjs';
import { Simulation } from 'src/app/model/simulation';
import { SimulationService } from 'src/app/services/simulation.service';

@Component({
  selector: 'app-result-simu2',
  templateUrl: './result-simu2.component.html',
  styleUrls: ['./result-simu2.component.css']
})
export class ResultSimu2Component implements OnInit {
  simulation: Observable<Simulation[]> | null = null;
  simulationLocal?: Simulation[];

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

