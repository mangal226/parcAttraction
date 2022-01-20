import { Simulation } from './../../model/simulation';
import { SimulationService } from './../../services/simulation.service';
import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-simulation',
  templateUrl: './simulation.component.html',
  styleUrls: ['./simulation.component.css']
})
export class SimulationComponent implements OnInit {
  simulation: Simulation=new Simulation();
  simulationService!: SimulationService;





  constructor(private router?: Router) {
  }

  ngOnInit(): void {
  }

  save()
  {
    // if (!!this.simulation.id) {
    //   this.simulationService.put(this.simulation).subscribe((ok) => {
    //     this.router?.navigate(['/admin/simulation']);
    //   });
    // } else {
      console.log(this.simulation);
      this.simulationService.create(this.simulation).subscribe((ok) => {
        this.router?.navigate(['/admin/plan-modif']);
      })
    }
  }

