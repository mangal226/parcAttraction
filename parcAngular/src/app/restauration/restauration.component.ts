import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'app-restauration',
  templateUrl: './restauration.component.html',
  styleUrls: ['./restauration.component.css']
})
export class RestaurationComponent implements OnInit {

  constructor() { }

  ngOnInit(): void {
  }
  onFormSubmit(userForm:NgForm){
   console.log(userForm);

  }
resetUserForm(UserForm:NgForm){
  UserForm.resetForm;
}


}
