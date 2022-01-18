import { RestaurationService } from './../services/restauration.service';
import { Coordonnees } from './../model/coordonnees';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Restauration } from '../model/restauration';
import { Boisson } from '../model/boisson';
import { Plat } from '../model/plat';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-restauration',
  templateUrl: './restauration.component.html',
  styleUrls: ['./restauration.component.css']
})
export class RestaurationComponent implements OnInit {

  restauration: Observable<Restauration[]> | null=null;

  constructor( private restaurationService: RestaurationService) { }


  ngOnInit(): void {
    this.restauration=this.restaurationService.getAll();
  }

  delete(id: number){
    this.restaurationService.delete(id);
    this.restauration=this.restaurationService.getAll();
  }

 /* onFormSubmit(userForm:NgForm){
   console.log(userForm);

  }
resetUserForm(UserForm:NgForm){
  UserForm.resetForm;
}*/


}
