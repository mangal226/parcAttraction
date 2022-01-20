import { PlatService } from './../services/plat.service';
import { Coordonnees } from './../model/coordonnees';
import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

import { Boisson } from '../model/boisson';
import { Plat } from '../model/plat';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-plat',
  templateUrl: './plat.component.html',
  styleUrls: ['./plat.component.css']
})
export class PlatComponent implements OnInit {
  source : string []=[];

  plat: Observable<Plat[]> | null=null;


  constructor( private platService: PlatService) { }


  ngOnInit(): void {
    this.plat=this.platService.getAll();
    this.source[0]="../assets/img/le-chalet.jpg";
    this.source[1]='../assets/img/mcfat.png';
    this.source[2]="../assets/img/boutique2.jpg";
    this.source[3]="../assets/img/boutique1.jpg";
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
    this.platService.delete(id);
    this.plat=this.platService.getAll();
  }

 /* onFormSubmit(userForm:NgForm){
   console.log(userForm);

  }
resetUserForm(UserForm:NgForm){
  UserForm.resetForm;
}*/


}
