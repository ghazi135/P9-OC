import {Component, Inject, OnInit} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Patient, PatientElement} from "../../model/Patient";
import { FormControl, FormGroup} from "@angular/forms";
import {PatientService} from "../../service/patient.service";

@Component({
  selector: 'app-patient-update-dialog',
  templateUrl: './patient-update-dialog.component.html',
  styleUrls: ['./patient-update-dialog.component.css']
})
export class PatientUpdateDialogComponent implements OnInit {

  patientObject!: PatientElement;
  Patient!: FormGroup;
  constructor(
    private patientService: PatientService,
    public dialogRef: MatDialogRef<PatientUpdateDialogComponent>,
    @Inject(MAT_DIALOG_DATA) public data: PatientElement,
  ) {}

  ngOnInit(): void {
    this.Patient = new FormGroup({
      lastName: new FormControl(this.data.lastName),
      firstName: new FormControl(this.data.firstName),
      dateOfBirth: new FormControl(this.data.dateOfBirth),
      address: new FormControl(this.data.address),
      phoneNumber: new FormControl(this.data.phoneNumber),
      sex: new FormControl(this.data.sex),
    })
  }

  update(){
    this.patientService.updatePatient(this.data.idPatient,new Patient(this.data.idPatient,this.Patient.get('lastName')?.value, this.Patient.get('firstName')?.value, this.Patient.get('dateOfBirth')?.value,this.Patient.get('address')?.value,this.Patient.get('phoneNumber')?.value,this.Patient.get('sex')?.value)).subscribe(()=>{
      console.log("data updated")
    })
  }

}
