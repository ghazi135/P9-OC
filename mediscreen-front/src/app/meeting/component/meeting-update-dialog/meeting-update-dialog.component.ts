import {Component, Inject, OnInit} from '@angular/core';
import {Rdv, RdvElement} from "../../model/Rdv";
import {FormControl, FormGroup} from "@angular/forms";
import {MeetingService} from "../../service/meeting.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";

@Component({
  selector: 'app-meeting-update-dialog',
  templateUrl: './meeting-update-dialog.component.html',
  styleUrls: ['./meeting-update-dialog.component.css']
})
export class MeetingUpdateDialogComponent implements OnInit {

  RdvObject!: RdvElement;
  Rdv!: FormGroup;

  constructor(private meetingService: MeetingService,
              public dialogRef: MatDialogRef<MeetingUpdateDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: RdvElement) {

  }

  ngOnInit(): void {
    this.Rdv = new FormGroup({
      namePatient: new FormControl(this.data.namePatient),
      datePriseRdv: new FormControl(this.data.datePriseRdv),
      noteRdv: new FormControl(this.data.noteRdv)
    })
  }

  update() {
    this.meetingService.updateRdv(this.data.idRdv, new Rdv(this.data.idRdv, this.Rdv.get('namePatient')?.value, this.Rdv.get('datePriseRdv')?.value, this.Rdv.get('noteRdv')?.value)).subscribe(() => {
      console.log("meeting updated");
    })
  }

}
