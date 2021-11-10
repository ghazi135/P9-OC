import {Component, Inject, OnInit} from '@angular/core';
import {MeetingService} from "../../../meeting/service/meeting.service";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {RdvElement} from "../../../meeting/model/Rdv";
import {NoteService} from "../../service/note.service";
import {Note, NoteElement} from "../../model/Note";
import {FormControl, FormGroup} from "@angular/forms";

@Component({
  selector: 'app-update-note-dialog',
  templateUrl: './update-note-dialog.component.html',
  styleUrls: ['./update-note-dialog.component.css']
})
export class UpdateNoteDialogComponent implements OnInit {

  Note!:FormGroup;
  NoteObject!: Note;

  constructor(private noteService: NoteService,
              public dialogRef: MatDialogRef<UpdateNoteDialogComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) { }

  ngOnInit(): void {
    this.NoteObject = this.data;
    this.Note = new FormGroup({
      note : new FormControl('')
    })
    this.noteService.getNotesById(this.data[0]).subscribe(data =>{
      this.NoteObject = data
    })
  }

  update(){
    this.noteService.updateNote(this.data[0],new Note(this.NoteObject.patientId,this.NoteObject.patientFirstName,this.NoteObject.patientLastName,this.Note.get('note')?.value,this.NoteObject.dateNote)).subscribe(()=>{
      console.log("note updated")
    })
  }
}
