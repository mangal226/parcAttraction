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
  simulation: Simulation =new Simulation();



  // router!: Router;





  constructor(private simulationService: SimulationService, private router: Router){
  }

  ngOnInit(): void {
  }

  save()
  {
    if (!!this.simulation.id) {
      console.log(this.simulation);
      this.simulationService.put(this.simulation).subscribe((ok) => {
        this.router.navigate(['/admin/result-simu']);
      });
    } else {
      console.log(this.simulation);
      this.simulationService.create(this.simulation).subscribe((ok) => {
        this.router.navigate(['/admin/result-simu']);
      })
    }
  }
}
