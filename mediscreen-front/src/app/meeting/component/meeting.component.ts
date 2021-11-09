import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {FormControl, FormGroup} from "@angular/forms";
import {MatPaginator} from "@angular/material/paginator";
import {Rdv, RdvElement} from "../model/Rdv";
import {MeetingService} from "../service/meeting.service";
import {MeetingUpdateDialogComponent} from "./meeting-update-dialog/meeting-update-dialog.component";
import {MatDialog} from "@angular/material/dialog";

@Component({
  selector: 'app-meeting',
  templateUrl: './meeting.component.html',
  styleUrls: ['./meeting.component.css']
})
export class MeetingComponent implements OnInit {
  displayedColumns: string[] = ['idRdv','namePatient','datePriseRdv','noteRdv','Delete','update'];
  dataSource = new MatTableDataSource<RdvElement>();
  RdvForm!: FormGroup;
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;

  constructor(private meetingService: MeetingService, public dialog: MatDialog) { }

  openDialog(id: number, patientName: string, dateOfMeeting: string, noteRdv: string): void {
    const dialogRef = this.dialog.open(MeetingUpdateDialogComponent, {
      width: '250px',
      data: new Rdv(id,patientName,dateOfMeeting,noteRdv),
    });
    dialogRef.afterClosed().subscribe(result => {
      console.log("result rdv:",result)
      this.ngOnInit();
      this.ngOnInit();
    })
  }
  ngOnInit(): void {
    this.RdvForm = new FormGroup({
      namePatient: new FormControl(''),
      datePriseRdv:  new FormControl(''),
      noteRdv:  new FormControl('')
    })
    this.meetingService.getMeetings().subscribe(data => {
      this.dataSource = new MatTableDataSource<RdvElement>(data);
    })
  }

  insertMeeting(){
    this.meetingService.insertMeeting(new Rdv(null! ,this.RdvForm.get('namePatient')?.value,this.RdvForm.get('datePriseRdv')?.value,this.RdvForm.get('noteRdv')?.value)).subscribe(()=>{
      this.meetingService.getMeetings().subscribe(data => {
        this.dataSource = new MatTableDataSource<RdvElement>(data);
      })
    })
  }
  deleteMeeting(id: number){

    this.meetingService.deletePatient(id).subscribe(()=>{
      this.meetingService.getMeetings().subscribe(data => {
        this.dataSource = new MatTableDataSource<RdvElement>(data);
      })
    })
  }
}
