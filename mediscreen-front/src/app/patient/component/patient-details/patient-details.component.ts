import {Component, OnInit, ViewChild} from '@angular/core';
import {MatTableDataSource} from "@angular/material/table";
import {MatPaginator} from "@angular/material/paginator";
import {PatientService} from "../../service/patient.service";
import {Note, NoteElement} from "../../../note/model/Note";
import {PatientElement} from "../../model/Patient";
import {ActivatedRoute} from "@angular/router";
import {NoteService} from "../../../note/service/note.service";
import {RepportService} from "../../../repport/service/repport.service";
import {FormControl, FormGroup} from "@angular/forms";
import {DatePipe} from "@angular/common";
import {RepportElement} from "../../../repport/model/Repport";
import {MatDialog} from "@angular/material/dialog";
import {UpdateNoteDialogComponent} from "../../../note/component/update-note-dialog/update-note-dialog.component";

@Component({
  selector: 'app-patient-details',
  templateUrl: './patient-details.component.html',
  styleUrls: ['./patient-details.component.css'],
  providers: [DatePipe]
})
export class PatientDetailsComponent implements OnInit {
  displayedColumns: string[] = ['idPatient', 'lastName', 'firstName', 'dateOfBirth', 'address', 'phoneNumber', 'sex'];
  displayedNoteColumns: string[] = ['note', 'dateNote', 'Delete', 'update'];
  displayedReportColumns: string[] = ['age', 'firstName', 'lastName', 'sex', 'status'];
  dataSource!: PatientElement[];
  noteDataSource!: NoteElement[];
  reportDataSource!: RepportElement[];
  myDate = new Date();
  formNote!: FormGroup;
  @ViewChild(MatPaginator)
  paginator!: MatPaginator;
  firstName: string = '';
  lastName: string = '';
  dataToUpdate!: any;

  constructor(private patientService: PatientService,
              private noteService: NoteService,
              private repportService: RepportService,
              private route: ActivatedRoute,
              private datePipe: DatePipe,
              public dialog: MatDialog) {
  }

  openDialog(idNote: string): void {

    this.noteService.getNotesById(idNote).subscribe(note => {
      this.dataToUpdate = note;
    })

    const dialogRef = this.dialog.open(UpdateNoteDialogComponent, {
      width: '250px',
      data: [idNote, this.dataToUpdate],
    });
    dialogRef.afterClosed().subscribe(result => {

      this.ngOnInit();
      this.ngOnInit();

    })
  }


  ngOnInit(): void {
    this.formNote = new FormGroup({
      note: new FormControl('')
    });


    this.patientService.getPatientById(Number(this.route.snapshot.paramMap.get('id'))).subscribe(data => {
      console.log(data)
      this.dataSource = new MatTableDataSource<PatientElement>().data.concat(data);
      this.noteService.getNotesByPatient(data.lastName, data.firstName).subscribe(notes => {
        console.log('notes:', notes);
        this.noteDataSource = new MatTableDataSource<NoteElement>().data.concat(notes);
      })
    })
  }

  insertNoteToPatient(): void {
    this.myDate = new Date();
    let latest_date = this.datePipe.transform(this.myDate, 'dd/MM/yyyy HH:mm:ss');
    let elm = this.dataSource[0];
    this.noteService.insertNote(new Note(Number(this.route.snapshot.paramMap.get('id')), elm.firstName, elm.lastName, this.formNote.get('note')?.value, String(latest_date))).subscribe(() => {
      this.patientService.getPatientById(Number(this.route.snapshot.paramMap.get('id'))).subscribe(data => {
        console.log(data)
        this.dataSource = new MatTableDataSource<PatientElement>().data.concat(data);
        this.noteService.getNotesByPatient(data.lastName, data.firstName).subscribe(notes => {
          console.log('notes:', notes);
          this.noteDataSource = new MatTableDataSource<NoteElement>().data.concat(notes);
        })
      })
    })
  }

  getReport() {
    this.patientService.getPatientById(Number(this.route.snapshot.paramMap.get('id'))).subscribe(data => {
      console.log(data)
      this.repportService.getReport(data.lastName, data.firstName).subscribe(report => {
        console.log("report", report);
        this.reportDataSource = new MatTableDataSource<RepportElement>().data.concat(report)
      })
    })
  }

  deleteNote(id: string) {
    this.noteService.deleteNote(id).subscribe(() => {
      this.patientService.getPatientById(Number(this.route.snapshot.paramMap.get('id'))).subscribe(data => {
        console.log(data)
        this.dataSource = new MatTableDataSource<PatientElement>().data.concat(data);
        this.noteService.getNotesByPatient(data.lastName, data.firstName).subscribe(notes => {
          console.log('notes:', notes);
          this.noteDataSource = new MatTableDataSource<NoteElement>().data.concat(notes);
        })
      })
    })
  }

}
