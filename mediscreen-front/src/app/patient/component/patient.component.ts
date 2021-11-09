import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {PatientService} from "../service/patient.service";
import {Patient, PatientElement} from "../model/Patient";
import {Router} from "@angular/router";
import {FormControl, FormGroup} from "@angular/forms";
import {MatDialog} from "@angular/material/dialog";
import {PatientUpdateDialogComponent} from "./patient-update-dialog/patient-update-dialog.component";

@Component({
  selector: 'app-patient',
  templateUrl: './patient.component.html',
  styleUrls: ['./patient.component.css']
})
export class PatientComponent implements OnInit {

  displayedColumns: string[] = ['idPatient', 'lastName', 'firstName', 'dateOfBirth', 'address', 'phoneNumber','sex','Delete','Show','update'];
  dataSource = new MatTableDataSource<PatientElement>();
  patientForm!: FormGroup;
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  constructor(private patientService: PatientService, private router: Router, public dialog: MatDialog) { }


  openDialog(id: number, firstName: string, lastName: string, dateOfBirth: string, address: string,phoneNumber: number, sex: string): void {

    const dialogRef = this.dialog.open(PatientUpdateDialogComponent, {
      width: '250px',
      data: new Patient(id,lastName,firstName,dateOfBirth,address,phoneNumber,sex),
    });
    dialogRef.afterClosed().subscribe(result => {

        this.ngOnInit();
        this.ngOnInit();

      })
  }



  ngOnInit(): void {
    this.patientForm = new FormGroup({
      firstName: new FormControl(''),
      lastName: new FormControl(''),
      dateOfBirth: new FormControl(''),
      address: new FormControl(''),
      phoneNumber: new FormControl(null),
      sex: new FormControl('')
    })

    this.patientService.getPatients().subscribe(data => {
      console.log(data)
      this.dataSource = new MatTableDataSource<PatientElement>(data)
    })
  }

  insertPatient() {
    this.patientService.insertPatient(new Patient(null ,this.patientForm.get('firstName')?.value,this.patientForm.get('lastName')?.value,this.patientForm.get('dateOfBirth')?.value,this.patientForm.get('address')?.value,this.patientForm.get('phoneNumber')?.value,this.patientForm.get('sex')?.value)).subscribe(() =>{
      this.patientService.getPatients().subscribe(data => {
        console.log(data)
        this.dataSource = new MatTableDataSource<PatientElement>(data);
      });
    });
  }


  deletePatient(id : number){
    this.patientService.deletePatient(id).subscribe(()=>{
      this.patientService.getPatients().subscribe(data => {
        console.log(data)
        this.dataSource = new MatTableDataSource<PatientElement>(data);
      });
    })
  }
  getPatientDetails(id: number): void{
    this.router.navigate(['' + 'patient' + '/' + id]);
  }
}
