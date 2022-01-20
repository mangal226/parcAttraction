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
  source : string []=[];

  restauration: Observable<Restauration[]> | null=null;

  constructor( private restaurationService: RestaurationService) { }


  ngOnInit(): void {
    this.restauration=this.restaurationService.getAll();
    this.source[0]="../assets/img/le-chalet.jpg";
    this.source[1]='../assets/img/mcfat.png';
    this.source[2]="../assets/img/interieur.jpg";
    this.source[3]="../assets/img/western.jpg";
    this.source[4]="../assets/img/boutique2.jpg";
    this.source[5]="../assets/img/boutique.jpg";
    this.source[6]="../assets/img/boutique.jpg";
    this.source[7]='../assets/img/boutique1.jpg';
    this.source[8]="../assets/img/boutique2.jpg";
    this.source[9]="../assets/img/boutique1.jpg";
    this.source[10]="../assets/img/boutique2.jpg";
    this.source[11]="../assets/img/boutique.jpg";
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
