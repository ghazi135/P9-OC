export interface NoteElement {
  patientId: number;
  note: string;
  patientFirstName: string;
  patientLastName: string;
  dateNote: string;
}

export class Note implements NoteElement{


    patientId: number;
    note: string;
    dateNote: string;
    patientFirstName: string;
    patientLastName: string;

  constructor(patientId: number, patientFirstName: string, patientLastName: string, note: string, dateNote: string) {

    this.patientId = patientId;
    this.patientLastName = patientLastName;
    this.patientFirstName = patientFirstName;
    this.note = note;
    this.dateNote = dateNote;
  }
}
